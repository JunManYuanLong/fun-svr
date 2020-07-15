package com.okay.family.common.basedata;

public class NodeLock {

    private static final int USER_LOCK_PREFIX = 1_000_000;

    public static int getUserLock(int uid) {
        return USER_LOCK_PREFIX + uid;
    }

}
