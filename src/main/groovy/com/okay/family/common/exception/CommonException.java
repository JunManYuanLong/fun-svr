package com.okay.family.common.exception;

import com.okay.family.fun.base.exception.FailException;

public class CommonException extends FailException {

    private static final long serialVersionUID = -2409699175834194030L;

    private CommonException() {
        super("通用错误!");
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
