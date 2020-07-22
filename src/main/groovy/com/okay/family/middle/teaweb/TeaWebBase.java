package com.okay.family.middle.teaweb;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.FamilyConstant;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.fun.base.bean.RequestInfo;
import com.okay.family.fun.base.exception.LoginException;
import com.okay.family.fun.base.interfaces.IBase;
import com.okay.family.fun.config.HttpClientConstant;
import com.okay.family.fun.config.SysInit;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.fun.frame.httpclient.FanLibrary;
import com.okay.family.middle.common.CasCredential;
import com.okay.family.middle.common.Common;
import com.okay.family.middle.common.MiddleConstant;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 教师空间项目
 * qa项目base类
 */
public class TeaWebBase extends SourceCode implements IBase {

    private static Logger logger = LoggerFactory.getLogger(TeaWebBase.class);

    public static String LOGIN = "/login";

    public static final String CLASS_LIST = "/quizcenter/class_list";

    public static Map<Integer, String> hosts = new HashMap<Integer, String>((int) OkayConstant.ENV) {

        private static final long serialVersionUID = 622755375476661844L;

        {
            put(OkayConstant.HOTFIX, MiddleConstant.TEAWEB_HOTFIX_HOST);
            put(OkayConstant.DEV, MiddleConstant.TEAWEB_DEV_HOST);
            put(OkayConstant.STRESS, MiddleConstant.TEAWEB_STRESS_HOST);
            put(OkayConstant.ONLINE, MiddleConstant.TEAWEB_ONLINE_HOST);
        }
    };


    public String HOST;

    public int envId;

    public String password;

    public String username;

    public JSONObject cookies = new JSONObject();

    @Override
    public void login() {
        JSONObject params = new JSONObject();
        params.put("loginType", "1");
        params.put("platformType", "teacher");
        params.put("username", username);
        params.put("password", password);
        params.put("pictureVerifyCode", "");
        params.put("phone", "");
        params.put("traceno", "");
        params.put("phoneVerifyCode", "");
        JSONObject tgc = CasCredential.getTGC(HOST, params, envId);
        this.cookies = tgc.getJSONObject("cookie");
        String location = tgc.containsKey("location") ? tgc.getString("location") : EMPTY;
        if (!location.contains("ticket=ST-")) LoginException.fail(this.username);
        JSONObject getResponse = getGetResponse(location.replace(HOST, EMPTY));
        String location2 = getResponse.getString("location");
        /*修复由于增加cookie的值导致的报错,可以直接添加user_check_61951427267节点值方式,也可以请求"/"接口获取改cookie值*/
//        JSONObject location1 = getGetResponse(location2.replace(HOST, EMPTY));
//        output(cookies);
        cookies.put("user_check_" + username, "pwd_info_phone");
        logger.info("账号:{},cookie:{}", username, cookies.toJSONString());
    }

    public void login2() {
        String url = LOGIN;
        JSONObject params = new JSONObject();
        params.put("username", username);
        params.put("password", password);
        params.put("validate_code", EMPTY);
        JSONObject response = getPostResponse(url, params);
        output(response);
    }


    public TeaWebBase(String username, String password, int envId) {
        this.username = username;
        this.password = password;
        this.envId = envId;
        this.HOST = hosts.get(envId);
        if (envId == OkayConstant.STRESS && FamilyConstant.TEA_WEB_LOGIN) {
            login();
        } else {
            login2();
        }
    }

    public TeaWebBase(String certificate, int envId) {
        this.cookies.putAll(JSONObject.parseObject(certificate));
        this.envId = envId;
        this.HOST = hosts.get(envId);
    }

    private TeaWebBase() {
    }

    public TeaWebBase(TeaWebBase okayBase) {
        this.password = okayBase.password;
        this.username = okayBase.username;
        this.cookies = okayBase.cookies;
    }

    public JSONObject getParams() {
        return getJson("_=" + getMark());
    }


    @Override
    public void init(JSONObject jsonObject) {
        cookies.putAll(jsonObject);
        HttpGet get = FanLibrary.getHttpGet(CasCredential.hosts.get(envId) + HOST + OR);
        get.addHeader(FanLibrary.getCookies(jsonObject));
        JSONObject response = FanLibrary.getHttpResponse(get);
        JSONObject credential = CasCredential.verifyST(response.getString("location"));
        cookies.putAll(credential);
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
        return FanLibrary.getHttpPost(HOST + s, jsonObject);
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
        this.cookies.putAll(getJson("user_phone_check_" + this.username + "=true"));
        if (!cookies.isEmpty()) httpRequestBase.addHeader(FanLibrary.getCookies(cookies));
    }

    @Override
    public void handleResponseHeader(JSONObject response) {
        if (!response.containsKey(HttpClientConstant.COOKIE)) return;
        cookies.putAll(response.getJSONObject(HttpClientConstant.COOKIE));
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
        if (jsonObject.containsKey("success")) return jsonObject.getBoolean("success");
        int code = checkCode(jsonObject, new RequestInfo(getGet(HOST)));
        try {
            JSONObject data = jsonObject.getJSONObject("data");
            return code == 0 && !data.isEmpty();
        } catch (Exception e) {
            output(jsonObject);
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
        int code = TEST_ERROR_CODE;
        if (SysInit.isBlack(requestInfo.getHost())) return code;
        try {
            code = jsonObject.getInteger("code");
        } catch (Exception e) {
            logger.warn("非标准响应:{}", jsonObject.toString());
        }
        return code;
    }

    @Override
    public boolean checkLoginStatus() {
        String url = CLASS_LIST;
        JSONObject params = getParams();
        JSONObject response = getGetResponse(url, params);
        output(response);
        return isRight(response);
    }

    @Override
    public String getCertificate() {
        return cookies.toJSONString();
    }


}
