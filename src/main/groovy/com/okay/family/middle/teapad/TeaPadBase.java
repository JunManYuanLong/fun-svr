package com.okay.family.middle.teapad;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.fun.base.bean.RequestInfo;
import com.okay.family.fun.base.interfaces.IBase;
import com.okay.family.fun.config.SysInit;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.fun.frame.httpclient.FanLibrary;
import com.okay.family.middle.common.Common;
import com.okay.family.middle.common.MiddleConstant;
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
public class TeaPadBase extends SourceCode implements IBase {

    private static Logger logger = LoggerFactory.getLogger(TeaPadBase.class);

    public static String LOGIN = "/api/t_pad/user/login";

    public static String USER_INFO = "/api/t_pad/user/info";

    public static Map<Integer, String> hosts = new HashMap<Integer, String>((int) OkayConstant.ENV) {
        private static final long serialVersionUID = 6227506693666661844L;

        {
            put(1, MiddleConstant.TEAPAD_HOTFIX_HOST);
            put(2, MiddleConstant.TEAPAD_DEV_HOST);
            put(3,MiddleConstant.TEAPAD_STRESS_HOST);
//            put(4, "https://stupad-dev.xk12.cn");
            //todo:完成host,于数据校验
        }
    };

    public String HOST;

    /**
     * 登录响应
     */
    JSONObject loginResponse;

    public int envId;

    String token;

    String uname;

    String password;

    @Override
    public void login() {
        String url = LOGIN;
        JSONObject params = getJson("uname=" + uname, "pwd=" + getPassword());
        loginResponse = getPostResponse(url, params);
        output(loginResponse);
        if (isRight(loginResponse)) {
            JSONObject data = loginResponse.getJSONObject("data");
            token = data.getString("token");
            uname = data.getString("name");
            logger.info("教师：{}，密码：{}，登录成功!", uname, password);
        } else {
            logger.info("教师：{}，密码：{}，登录失败!", uname, password);
        }
    }

    public TeaPadBase(String uname, String pwd, int envId) {
        this.uname = uname;
        this.password = pwd;
        this.envId = envId;
        this.HOST = hosts.get(envId);
        login();
    }

    public TeaPadBase(String token, int envId) {
        this.envId = envId;
        this.HOST = hosts.get(envId);
        this.token = token;
    }

    private TeaPadBase() {
    }

    public String getPassword() {
        return RSAUtils.getPassword(password, envId);
    }

    public JSONObject getParams() {
        JSONObject json = getJson("uid=" + uname, "token=" + token);
        json.put("ua", "OKAY_Test_4.00_TCOKUI_4.3.0.48_20190125_T");
        json.put("imei", "isFaked");
        json.put("serial", "d360f0f5");
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

    public JSONObject getLoginResponse() {
        return loginResponse;
    }

    public String getToken() {
        return token;
    }

    public String getUname() {
        return uname;
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
    public void setHeaders(HttpRequestBase httpRequestBase) {
        httpRequestBase.addHeader(Common.getRequestIdHeader());
    }

    @Override
    public JSONObject getResponse(HttpRequestBase httpRequestBase) {
        setHeaders(httpRequestBase);
        JSONObject response = FanLibrary.getHttpResponse(httpRequestBase);
        handleResponseHeader(response);
        return response;
    }

    @Override
    public void handleResponseHeader(JSONObject response) {
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
            return 0 == jsonObject.getJSONObject("meta").getInteger("ecode");
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
