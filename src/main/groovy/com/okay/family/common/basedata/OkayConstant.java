package com.okay.family.common.basedata;

import com.okay.family.fun.config.Constant;
import com.okay.family.fun.frame.SourceCode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 常量配置类
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

    public static final String RUN_RESULT_TEXT = "<校验结果>";

    public static final String PATTEN_ERROR_TEXT = "正则表达式错误";

    public static final String MOCO_KEY = "name";

    public static final String MOCO_VALUE = "demo";

    public static final String MOCO_CHILDREN = "children";

    public static final String DEFAULT_VERIFY_DEC = "默认验证";

    public static final String RULE = "规则一：value填写'uid=123'，表示使用id为123的用户账号其登录token值" + LINE_HTML + "规则二：可在header以及上行参数的value中填写随机数，使用语法如下。" + LINE_HTML + "例：random=1000,2000" + LINE_HTML + "注：后者值需大于前者,否则生成随机数功能不可用";

    public static final String SIMPLER_RESULT_KEY_ID = "id";

    public static final String SIMPLER_RESULT_KEY_NAME = "name";

    /**
     * 用于处理list<object>对象的写入和读取分隔
     */
    public static final String MYSQL_SEPARATE = Constant.PERCENT[1];

    /**
     * 用户身份数量
     */
    public static final long ROLE = 6L;

    /**
     * 用户状态数量
     */
    public static final long USER_STATUS = 4L;

    /**
     * 环境数量
     */
    public static final long ENV = 5L;

    /**
     * 用户凭证过期时间
     */
    public static final long CERTIFICATE_TIMEOUT = 10 * 60 * 1000L;

    /**
     * 域名信息缓存时间,s
     */
    public static final int HOST_TIMEOUT = 1_000;

    /**
     * 分布式锁过期时间,ms
     */
    public static final int LOCK_TIMEOUT = 60_000;

    /**
     * 分布式锁等待间隔毫秒
     */
    public static final int WAIT_INTERVAL = 200;

    /**
     * 分布式锁最大等待次数
     */
    public static final int WAIT_MAX_TIME = 15;

    /**
     * 节点锁
     */
    public static final long NODE_LOCK = 1L;

    /**
     * 异步线程等待时间
     */
    public static final long SYNC_WAIT_TIMEOUT = 10L;

    /**
     * 生成全局唯一变量,用于用例运行id
     */
    public static AtomicInteger RUN_MARK;

    /**
     * 生成全局唯一变量,用于用例集用例id
     */
    public static AtomicInteger COLLECTION_MARK;

    /**
     * 节点
     */
    public static int node;

    public static final int HOTFIX = 1;

    public static final int DEV = 2;

    public static final int STRESS = 3;

    public static final int ONLINE = 4;


}
