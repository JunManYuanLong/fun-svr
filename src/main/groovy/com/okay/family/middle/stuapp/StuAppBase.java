package com.okay.family.middle.stuapp;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.exception.CommonException;
import com.okay.family.fun.base.bean.RequestInfo;
import com.okay.family.fun.base.interfaces.IBase;
import com.okay.family.fun.config.SysInit;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.fun.frame.httpclient.FanLibrary;
import com.okay.family.fun.utils.DecodeEncode;
import com.okay.family.middle.common.Common;
import com.okay.family.middle.common.MiddleConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.File;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class StuAppBase extends SourceCode implements IBase {

    private static Logger logger = LoggerFactory.getLogger(StuAppBase.class);

    public static String LOGIN = "/sapi/passport/user/login";

    public static String USERINFO = "/sapi/passport/profile/student_info";

    public static Map<Integer, String> hosts = new HashMap<Integer, String>((int) OkayConstant.ENV) {
        private static final long serialVersionUID = 6225476787661844L;

        {
            put(1, MiddleConstant.STUAPP_HOTFIX);
            put(2, MiddleConstant.STUAPP_DEV);
            put(3, MiddleConstant.STUAPP_STRESS);
//            put(4, "https://stupad-dev.xk12.cn");
            //todo:完成host,于数据校验
        }
    };

    public String HOST;

    public int envId;

    public String token;

    public String account;

    public String phone;

    public String pwd;

    private StuAppBase() {

    }

    public StuAppBase(String account, String pwd, int envId) {
        this.account = account;
        this.envId = envId;
        this.pwd = pwd;
        this.HOST = hosts.get(envId);
        this.phone = "";
        login();
    }

    public StuAppBase(String certificate, int envId) {
        this.token = certificate;
        this.envId = envId;
        this.HOST = hosts.get(envId);
    }

    public StuAppBase(StuAppBase base) {
        this.account = base.account;
        this.token = base.token;
        this.phone = base.phone;
        this.HOST = base.HOST;
        this.pwd = base.pwd;
    }


//    public String getDefaultPhone() {
//        return 14 + account.substring(2);
//    }


    /**
     * 默认账号密码登录
     */
    @Override
    public void login() {
        String url = LOGIN;
        JSONObject params = getParams();
        params.put("login_mode", 1);
        params.put("params", getJson("account=" + account, "pwd=" + getPwd(pwd)));
        JSONObject response = getPostResponse(url, params);
        output(response);
        if (isRight(response)) {
            token = response.getJSONObject("data").getString("token");
            logger.info("账号 {} 登录成功", account);
        } else {
            CommonException.fail("账号登录失败" + account);
        }
    }


    /**
     * 获取加密密文
     *
     * @param pwd
     * @return
     * @throws Exception
     */
    protected String getPwd(String pwd) {
        try {
            String salt = MiddleConstant.salts.get(envId);
            String key = StringUtils.substring(salt, 0, salt.length() / 8 * 8);
            // 生成一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MiddleConstant.DES);
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(MiddleConstant.DES);
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            byte[] bt = cipher.doFinal(pwd.getBytes());
            return DecodeEncode.base64Encode(bt);
        } catch (Exception e) {
            return EMPTY;
        }
    }


    @Override
    public HttpGet getGet(String s) {
        return FanLibrary.getHttpGet(HOST + s);
    }

    @Override
    public HttpGet getGet(String s, JSONObject jsonObject) {
        return FanLibrary.getHttpGet(HOST + s, jsonObject);
    }

    @Override
    public HttpPost getPost(String s) {
        return FanLibrary.getHttpPost(HOST + s);
    }

    @Override
    public HttpPost getPost(String s, JSONObject jsonObject) {
        return FanLibrary.getHttpPost(HOST + s, jsonObject.toString());
    }

    @Override
    public HttpPost getPost(String s, JSONObject jsonObject, File file) {
        return FanLibrary.getHttpPost(HOST + s, jsonObject, file);
    }

    @Override
    public JSONObject getResponse(HttpRequestBase httpRequestBase) {
        setHeaders(httpRequestBase);
        JSONObject response = FanLibrary.getHttpResponse(httpRequestBase);
        handleResponseHeader(response);
        return response;
    }

    @Override
    public JSONObject getGetResponse(String s) {
        return getResponse(getGet(s));
    }

    @Override
    public JSONObject getGetResponse(String s, JSONObject jsonObject) {
        return getResponse(getGet(s, jsonObject));
    }

    @Override
    public JSONObject getPostResponse(String s) {
        return getResponse(getPost(s));
    }

    @Override
    public JSONObject getPostResponse(String s, JSONObject jsonObject) {
        return getResponse(getPost(s, jsonObject));
    }

    @Override
    public JSONObject getPostResponse(String s, JSONObject jsonObject, File file) {
        return getResponse(getPost(s, jsonObject, file));
    }

    @Override
    public boolean isRight(JSONObject jsonObject) {
        try {
            return jsonObject.getJSONObject("meta").getInteger("ecode") == 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int checkCode(JSONObject jsonObject, RequestInfo requestInfo) {
        int code = TEST_ERROR_CODE;
        if (SysInit.isBlack(requestInfo.getHost())) return code;
        try {
            code = jsonObject.getJSONObject("meta").getInteger("ecode");
        } catch (Exception e) {
            logger.warn("非标准响应:{}", jsonObject.toString());
        }
        return code;
    }


    @Override
    public void setHeaders(HttpRequestBase httpRequestBase) {
        httpRequestBase.addHeader(Common.getRequestIdHeader());
        httpRequestBase.addHeader(FanLibrary.getHeader("token", token));
    }

    @Override
    public void handleResponseHeader(JSONObject response) {
    }

    @Override
    public JSONObject getParams() {
        JSONObject p = JSONObject.parseObject("{\"vs\":\"9\",\"vc\":2963,\"vn\":\"2.9.6.3-debug\",\"ua\":\"MI 9\",\"os\":\"Android\",\"sw\":\"1080\",\"sh\":\"2135\",\"serial\":\"7bbf1226\",\"channel\":\"okay\",\"udid\":\"67DB2FBC26E700294280031E1D8DB254\",\"imei\":\"99001343459597\",\"mac\":\"02:00:00:00:00:00\",\"contype\":3,\"role\":\"0\",\"jpushid\":\"1507bfd3f7b59d0f851\"}");
        p.put("token", this.token);
        p.put("uid", this.account);
        return p;
    }

    @Override
    public void init(JSONObject jsonObject) {

    }

    @Override
    public void recordRequest() {

    }

    @Override
    public HttpRequestBase getRequest() {
        return null;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public boolean checkLoginStatus() {
        String url = USERINFO;
        JSONObject params = getParams();
        JSONObject response = getPostResponse(url, params);
        output(response);
        return isRight(response);
    }

    @Override
    public String getCertificate() {
        return token;
    }


}