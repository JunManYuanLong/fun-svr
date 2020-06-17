package com.okay.family.common.exception;

import com.okay.family.fun.base.exception.FailException;

public class CaseException extends FailException {

    private static final long serialVersionUID = 6515648130665053L;

    public CaseException() {
        super("用例错误!");
    }

    protected CaseException(String message) {
        super(message);
    }

    public static void fail() {
        throw new CaseException();
    }

    public static void fail(String message) {
        throw new CaseException(message);
    }


}
