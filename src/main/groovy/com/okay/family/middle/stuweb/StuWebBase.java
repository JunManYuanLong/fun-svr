package com.okay.family.middle.stuweb;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.fun.base.bean.RequestInfo;
import com.okay.family.fun.base.interfaces.IBase;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.fun.frame.httpclient.FanLibrary;
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

public class StuWebBase extends SourceCode implements IBase {

    private static Logger logger = LoggerFactory.getLogger(StuWebBase.class);

    public static String LOGIN = "/user/login";

    public static final String PACKAGE_LIST = "/preview/pkg_wait_list";

    public static Map<Integer, String> hosts = new HashMap<Integer, String>((int) OkayConstant.ENV) {
        private static final long serialVersionUID = 6227506693666661844L;

        {
            put(1, MiddleConstant.STUWEB_HOTFIX_HOST);
            put(2, MiddleConstant.STUWEB_DEV_HOST);
            put(3, MiddleConstant.STUWEB_STRESS_HOST);
//            put(4, "https://stupad-dev.xk12.cn");
            //todo:完成host,于数据校验
        }
    };

    public String HOST;

    public int envId;

    public JSONObject loginResponse;

    public String uid;

    public String pwd;

    public String token;

    private StuWebBase() {

    }

    public StuWebBase(String uid, String pwd, int envId) {
        this.uid = uid;
        this.pwd = pwd;
        this.envId = envId;
        this.HOST = hosts.get(envId);
        login();
    }

    public StuWebBase(String token, int envId) {
        this.envId = envId;
        this.HOST = hosts.get(envId);
        this.token = token;
    }

    @Override
    public void login() {
        String url = LOGIN;
        JSONObject params = getParams();
        params.put("uid", uid);
        params.put("pwd", pwd);
        loginResponse = getPostResponse(url, params);
        if (isRight(loginResponse)) {
            JSONObject data = loginResponse.getJSONObject("data");
            token = data.getString("token");
            logger.warn("学生空间账号{},登录成功!", uid);
        } else {
            logger.warn("学生空间账号{},登录失败!", uid);
            output(loginResponse);
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
        return FanLibrary.getHttpPost(HOST + s, jsonObject);
    }

    @Override
    public HttpPost getPost(String s, JSONObject jsonObject, File file) {
        return FanLibrary.getHttpPost(s, jsonObject, file);
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

    public JSONObject getPostResponse(String s, String jsonObject) {
        return getResponse(FanLibrary.getHttpPost(HOST + s, jsonObject));
    }

    @Override
    public JSONObject getPostResponse(String s, JSONObject jsonObject, File file) {
        return getResponse(getPost(s, jsonObject, file));
    }

    @Override
    public boolean isRight(JSONObject jsonObject) {
        return ((int) jsonObject.getOrDefault("code", TEST_ERROR_CODE)) == 0;
    }

    @Override
    public int checkCode(JSONObject jsonObject, RequestInfo requestInfo) {
        int code = TEST_ERROR_CODE;
        try {
            code = jsonObject.getInteger("code");
        } catch (Exception e) {
            logger.warn("非标准响应:{}", jsonObject.toString());
        }
        return code;
    }

    public boolean checkLoginStatus() {
        String url = PACKAGE_LIST;
        JSONObject params = getParams();
        params.put("page", 1);
        params.put("page_size", 20);
        JSONObject response = getGetResponse(url, params);
        output(response);
        return isRight(response);
    }

    public String getCertificate() {
        return token;
    }

    @Override
    public void setHeaders(HttpRequestBase httpRequestBase) {
        httpRequestBase.addHeader(Common.getRequestIdHeader());
    }

    @Override
    public void handleResponseHeader(JSONObject response) {
    }

    @Override
    public JSONObject getParams() {
        return new JSONObject();
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


}
