package com.okay.family.middle.common

import com.alibaba.fastjson.JSONObject
import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.config.HttpClientConstant
import com.okay.family.fun.frame.httpclient.FanLibrary
import com.okay.family.fun.utils.Regex
import org.apache.http.client.methods.HttpGet
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * cas服务验证类，主要解决web端登录验证功能
 */
class CasCredential extends FanLibrary {

    static final String OR = "/"

    private static Logger logger = LoggerFactory.getLogger(CasCredential.class)

    public static Map<Integer, String> hosts = new HashMap<Integer, String>((int) OkayConstant.ENV) {

        private static final long serialVersionUID = 62275063453465844L;

        {
            put(OkayConstant.HOTFIX, MiddleConstant.CAS_HOTFIX);
            put(OkayConstant.DEV, MiddleConstant.CAS_DEV);
            put(OkayConstant.STRESS, MiddleConstant.CAS_STRESS);
            put(OkayConstant.ONLINE, MiddleConstant.CAS_ONLINE)
        }
    };


    /**
     * 校验值，随机一次性，从login返回页面中获取
     */
    String lt
    /**
     * 校验值，随机一次性，从login返回页面中获取，正常值长度在4000+，低于4000请检查请求连接是否传入了回调服务的地址
     */
    String execution

/**
 * 从cas服务的login页面获取到令牌对，此处正则暂时可用，二期会修改表单提交
 */
    CasCredential(String host, int envId) {
        def get = getHttpGet(hosts.get(envId) + (host.endsWith(OR) ? host : host + OR))
        def response = getHttpResponse(get)
        def string = response.getString("content")
        this.lt = Regex.getRegex(string, "<input type=\"hidden\" name=\"lt\" value=\".*?\" />")
        this.execution = Regex.getRegex(string, "<input type=\"hidden\" name=\"execution\" value=\".*?\" />")
//        logger.info("cas服务登录host：{},lt：{}，execution：{}", host, lt, execution)
    }

/**
 * 各个服务端参数一致，由各个服务自己把参数拼好之后传过来，之后在去cas服务拿到令牌对
 * @param host 服务的host地址，回调由各个服务自己完成，二次验证也是，此处的host不做兼容，有cascredential做处理
 * @param params 拼好的参数
 * @return
 */
    static JSONObject getTGC(String host, JSONObject params, int envId) {
        def credential = new CasCredential(host,envId)
        params.put("lt", credential.getLt());
        params.put("execution", credential.getExecution())
        params.put("_eventId", "submit");
        def post = FanLibrary.getHttpPost(hosts.get(envId) + (host.endsWith(OR) ? host : host + OR), params)
        FanLibrary.getHttpResponse(post)
    }

/**
 * 通过用户st连接获取cookie
 * @param url
 * @return
 */
    public static JSONObject verifyST(String url) {
        HttpGet location = FanLibrary.getHttpGet(url);
        JSONObject httpResponse = FanLibrary.getHttpResponse(location);
        httpResponse.getJSONObject(HttpClientConstant.COOKIE) as JSONObject
    }
}
