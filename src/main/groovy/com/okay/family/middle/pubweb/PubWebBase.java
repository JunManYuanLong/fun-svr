package com.okay.family.middle.pubweb;

import com.alibaba.fastjson.JSONObject;
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

public class PubWebBase extends SourceCode implements IBase {

    private static Logger logger = LoggerFactory.getLogger(PubWebBase.class);

    public static final String GET_YEARS = "/api_courseware/statistics/schoolgrade/schoolyear";

    public static Map<Integer, String> hosts = new HashMap<Integer, String>((int) OkayConstant.ENV) {

        private static final long serialVersionUID = 61212321316661844L;

        {
            put(1, MiddleConstant.PUBWEB_HOTFIX_HOST);
            put(2, MiddleConstant.PUBWEB_DEV_HOST);
            put(3, MiddleConstant.PUBWEB_STRESS_HOST);
//            put(4, "https://stupad-dev.xk12.cn");
            //todo:完成host,于数据校验
        }
    };

    public String HOST;

    public String account;

    public String password;

    public int envId;

    public JSONObject cookies = new JSONObject();

    private PubWebBase() {

    }

    public PubWebBase(String account, String password, int envId) {
        this.account = account;
        this.password = password;
        this.envId = envId;
        this.HOST = hosts.get(envId);
        login();
    }

    public PubWebBase(String certificate, int envId) {
        this.cookies = JSONObject.parseObject(certificate);
        this.envId = envId;
        this.HOST = hosts.get(envId);
    }

    public PubWebBase(PubWebBase base) {
        this.cookies = base.cookies;
        this.account = base.account;
        this.password = base.password;
        this.HOST = base.HOST;
        this.envId = base.envId;
    }


    @Override
    public void login() {
//        /**
//         * 单点登录登录方式
        JSONObject params = new JSONObject();
        params.put("loginType", "1");
        params.put("platformType", "org");
        params.put("username", account);
        params.put("password", password);
        params.put("pictureVerifyCode", MiddleConstant.CAPTCHA);
        params.put("phone", "");
        params.put("traceno", "");
        params.put("phoneVerifyCode", "");
        JSONObject tgc = CasCredential.getTGC(HOST, params,envId);
        this.cookies = tgc.getJSONObject("cookie");
        String location = tgc.containsKey("location") ? tgc.getString("location") : EMPTY;
        if (!location.contains("ticket=ST-")) LoginException.fail(this.account);
        getGetResponse(location.replace(HOST, EMPTY));
        logger.info("账号：{}，登录成功！", account);
//         */
//        String url = UserApi.LOGIN;
//        JSONObject params = new JSONObject();
//        params.put("captcha", Profile.CAPTCHA);
//        params.put("account", Profile.ACCOUNT);
//        params.put("password", Profile.PWD);
//        JSONObject response = getPostResponse(url, params);
//        if (!isRight(response)) output(response);
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

    public HttpPost getPost(String s, String params) {
        return FanLibrary.getHttpPost(HOST + s, params);
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

    public JSONObject getPostResponse(String s, String params) {
        return getResponse(getPost(s, params));
    }

    @Override
    public JSONObject getPostResponse(String s, JSONObject jsonObject, File file) {
        return getResponse(getPost(s, jsonObject, file));
    }

    @Override
    public boolean isRight(JSONObject jsonObject) {
        try {
            if (jsonObject.containsKey("success")) return jsonObject.getBoolean("success");
            int code = jsonObject.getInteger("code");
            return code == 0;
        } catch (Exception e) {
            logger.warn("响应错误:{}", jsonObject.toString());
            return false;
        }
    }

    @Override
    public int checkCode(JSONObject jsonObject, RequestInfo requestInfo) {
        int code = TEST_ERROR_CODE;
        if (SysInit.isBlack(requestInfo.getHost())) return code;
        try {
            code = jsonObject.containsKey("code") ? jsonObject.getInteger("code") : jsonObject.getBoolean("success") == true ? 0 : TEST_ERROR_CODE;
        } catch (Exception e) {
            logger.warn("非标准响应:{}", jsonObject.toString());
        }
        return code;
    }


    @Override
    public void setHeaders(HttpRequestBase httpRequestBase) {
        httpRequestBase.addHeader(Common.getRequestIdHeader());
        httpRequestBase.addHeader(HttpClientConstant.X_Requested_KWith);
        if (!cookies.isEmpty()) httpRequestBase.addHeader(FanLibrary.getCookies(cookies));
    }

    @Override
    public void handleResponseHeader(JSONObject response) {
        if (!response.containsKey(HttpClientConstant.COOKIE)) return;
        cookies.putAll(response.getJSONObject(HttpClientConstant.COOKIE));
        response.remove(HttpClientConstant.COOKIE);
    }

    @Override
    public JSONObject getParams() {
        JSONObject params = getJson("_=" + getMark());
        return params;
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


    public boolean checkLoginStatus() {
        String url = GET_YEARS;
        JSONObject params = getParams();
        JSONObject response = getGetResponse(url, params);
        output(response);
        return isRight(response);
    }

    public String getCertificate() {
        return cookies.toJSONString();
    }


}
