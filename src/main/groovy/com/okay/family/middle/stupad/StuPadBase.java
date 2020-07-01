package com.okay.family.middle.stupad;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.fun.base.bean.RequestInfo;
import com.okay.family.fun.base.interfaces.IBase;
import com.okay.family.fun.config.HttpClientConstant;
import com.okay.family.fun.config.SysInit;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.fun.frame.httpclient.FanLibrary;
import com.okay.family.middle.common.Common;
import com.okay.family.middle.common.RSAUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * qa项目base类
 */
public class StuPadBase extends SourceCode implements IBase {

    private static Logger logger = LoggerFactory.getLogger(StuPadBase.class);

    public static final String PUBLICK_KEY_API = "/api/pad/user/encryptinfo";

    public static String LOGIN = "/api/pad/user/login";

    public static String USER_INFO = "/api/pad/user/info";


    public static Map<Integer, String> hosts = new HashMap<Integer, String>((int) OkayConstant.ENV) {
        private static final long serialVersionUID = 6227506693666661844L;

        {
            put(1, "https://stupad-dev.xk12.cn");
            put(2, "https://stupad-dev.xk12.cn");
            put(3, "https://stupad-dev.xk12.cn");
            put(4, "https://stupad-dev.xk12.cn");
//        put(2, "https://stupad-hotfix.xk12.cn");
            //todo:完成host,于数据校验
        }
    };


    public String HOST;

    public int envId;

    public JSONObject loginResponse;

    public int uid;

    public String token;

    public String uname;

    public String pwd;

    @Override
    public void login() {
        String url = LOGIN;
        JSONObject params = getJson("uname=" + uname, "pwd=" + getPassword(pwd));
        loginResponse = getPostResponse(url, params);
        if (isRight(loginResponse)) {
            JSONObject data = loginResponse.getJSONObject("data");
            uid = data.getInteger("uid");
            token = data.getString("token");
            uname = data.getString("uname");
            logger.info("用户：{}，登录成功！", uname);
        } else {
            logger.info("用户：{}，登录失败！", uname);
            output(loginResponse);
        }
    }


    public StuPadBase(String uname, String pwd, int envId) {
        this.uname = uname;
        this.pwd = pwd;
        this.envId = envId;
        this.HOST = hosts.get(envId);
        login();
    }

    public StuPadBase(String token, int envId) {
        this.envId = envId;
        this.HOST = hosts.get(envId);
        this.token = token;
    }

    private StuPadBase() {
    }

    public String getPassword(String pwd) {
        return RSAUtils.getPassword(pwd, envId);
    }

    public StuPadBase(StuPadBase okayBase) {
        this.pwd = okayBase.pwd;
        this.uid = okayBase.uid;
        this.uname = okayBase.uname;
        this.token = okayBase.token;
    }

    public JSONObject getParams() {
        JSONObject json = getJson("uid=" + uid, "token=" + token, "uname=" + uname);
        json.putAll(JSON.parseObject("{\"mac\": \"88:88:88:88:88:88\",\"os\": \"X510\",\"vc\": 100,\"sh\": \"1200\",\"sw\": \"1920\",\"vs\": \"2.1.3\",\"ua\": \"OKAY_EBOOK_4.0.0_OKUI_5.2.0.1_20191128_T\",\"serial\": \"d389f0dd\",\"channel\": \"tv_yst\"}"));
        return json;
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
    public void setHeaders(HttpRequestBase httpRequestBase) {
        httpRequestBase.addHeader(Common.getRequestIdHeader());
        httpRequestBase.addHeader(FanLibrary.getHeader("token", token));
        httpRequestBase.addHeader(FanLibrary.getHeader("u", uname));
        httpRequestBase.addHeader(FanLibrary.getHeader("appversion", "2.1.3"));
        httpRequestBase.addHeader(FanLibrary.getHeader("m", "tv_yst"));
        httpRequestBase.addHeader(FanLibrary.getHeader("appName", "com.example.demoapp"));
        httpRequestBase.addHeader(FanLibrary.getHeader("rom", "OKAY_EBOOK_4.0.0_OKUI_5.2.0.1_20191128_T"));
    }

    @Override
    public void handleResponseHeader(JSONObject response) {
        if (!response.containsKey(HttpClientConstant.COOKIE)) return;
        response.remove(HttpClientConstant.COOKIE);
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
            int code = jsonObject.getJSONObject("meta").getInteger("ecode");
            JSONObject data = jsonObject.getJSONObject("data");
            return code == 0 && !data.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取并检查code
     *
     * @param jsonObject
     * @return
     */
    public int checkCode(JSONObject jsonObject, RequestInfo requestInfo) {
        if (SysInit.isBlack(requestInfo.getHost())) return TEST_ERROR_CODE;
        try {
            return jsonObject.getJSONObject("meta").getInteger("ecode");
        } catch (Exception e) {
            logger.warn("非标准响应:{}", jsonObject.toString());
        }
        return TEST_ERROR_CODE;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public boolean checkLoginStatus() {
        String api = USER_INFO;
        JSONObject params = getParams();
        JSONObject response = getPostResponse(api, params);
        output(response);
        return isRight(response);
    }

    public String getCertificate() {
        return token;
    }


}
