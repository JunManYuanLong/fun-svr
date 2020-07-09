package com.okay.family.common.basedata;

import com.okay.family.fun.config.Constant;
import com.okay.family.fun.frame.SourceCode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 常量配置类,后期会放到数据库里面重新加载
 */
public class OkayConstant extends SourceCode {

    /**
     * 日志追踪header字段,不区分大小写
     */
    public static final String REQUEST_ID = "requestid";

    /**
     * 默认的requestid前缀
     */
    public static final String REQUEST_ID_PREFIX = "family";

    /**
     * 用户标记请求对象的header字段
     */
    public static final String MARK_HEADER = "mark";

    /**
     * 获取用户凭证标记
     */
    public static final String USER_CERTIFICATE_KEY = "uid=";

    public static final String RANDOM_KEY = "random=";

    public static final String RUN_RESULT_TEXT = "--校验结果:";

    public static final String MOCO_KEY = "name";

    public static final String MOCO_VALUE = "demo";

    public static final String MOCO_CHILDREN = "children";

    public static final String DEFAULT_VERIFY_DEC = "默认验证";

    public static final String RULE = "value填写'uid=123'表示使用用户id为123的测试账号登录状态" + LINE_HTML + "如果想设置随机数,参考:" + RANDOM_KEY + "1000,2000,后者大于前者,否者功能不可用";

    public static final String SIMPLER_RESULT_KEY_ID = "id";

    public static final String SIMPLER_RESULT_KEY_NAME = "name";

    /**
     * 用于处理list<object>对象的写入和读取分隔
     */
    public static final String MYSQL_SEPARATE = Constant.PERCENT[1];

    /**
     * 用户身份数量
     */
    public static final long ROLE = 4L;

    /**
     * 用户状态数量
     */
    public static final long USER_STATUS = 4L;

    /**
     * 环境数量
     */
    public static final long ENV = 4L;

    /**
     * 用户凭证过期时间
     */
    public static final long CERTIFICATE_TIMEOUT = 20 * 60 * 1000L;

    /**
     * 从记录任务列表中获取任务的超时时间单位是ms
     */
    public static final int MYSQLWORK_TIMEOUT = 200;

    /**
     * 生成全局唯一变量,用于用例运行id
     */
    public static AtomicInteger RUN_MARK = new AtomicInteger(getMark() % 100_000_000);

    /**
     * 生成全局唯一变量,用于用例集用例id
     */
    public static AtomicInteger COLLECTION_MARK = new AtomicInteger(RUN_MARK.get() / 100);


}
