package com.okay.family.common.exception;

import com.okay.family.fun.base.exception.FailException;

public class CaseCollecionException extends FailException {

    private static final long serialVersionUID = 6515648130665053L;

    public CaseCollecionException() {
        super("用例集错误!");
    }

    protected CaseCollecionException(String message) {
        super(message);
    }

    public static void fail() {
        throw new CaseCollecionException();
    }

    public static void fail(String message) {
        throw new CaseCollecionException(message);
    }


}
