package com.okay.family.common.exception

import com.okay.family.fun.base.exception.FailException

class CommonException extends FailException {

    private static final long serialVersionUID = 48949841651;

    public CommonException() {
        super("公共方法错误!");
    }

    protected CommonException(String message) {
        super(message);
    }

    public static void fail() {
        throw new CommonException();
    }

    public static void fail(String message) {
        throw new CommonException(message);
    }



}
