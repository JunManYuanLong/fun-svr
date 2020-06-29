package com.okay.family.middle.common;

import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.fun.frame.httpclient.FanLibrary;
import com.okay.family.fun.utils.Time;
import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.util.UUID;

public class Common extends SourceCode {

    public static Logger logger = LoggerFactory.getLogger(Common.class);


    public static String getRequestId(String proId) {
        if (proId == null) {
            proId = "00";
        }
        NumberFormat nf = NumberFormat.getInstance();
        //设置是否使用分组
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(10);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(10);
        return "fun_" + proId + nf.format(UUID.randomUUID().hashCode() & (0x7fffffff));
    }

    public static Header getRequestIdHeader() {
        Header header = FanLibrary.getHeader(OkayConstant.REQUEST_ID, OkayConstant.REQUEST_ID_PREFIX + Time.getTimeStamp());
        logger.info("requestid: {}", header.getValue());
        return header;
    }


}
