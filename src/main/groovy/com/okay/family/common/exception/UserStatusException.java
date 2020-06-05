package com.okay.family.common.exception;

import com.okay.family.fun.base.exception.FailException;

public class UserStatusException extends FailException {

    private static final long serialVersionUID = -6307468548130665053L;

    public UserStatusException() {
        super("用户状态错误!");
    }

    protected UserStatusException(String message) {
        super(message);
    }

    public static void fail() {
        throw new UserStatusException();
    }

    public static void fail(String message) {
        throw new UserStatusException(message);
    }


}
