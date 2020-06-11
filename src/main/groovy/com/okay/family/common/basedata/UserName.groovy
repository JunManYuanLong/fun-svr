package com.okay.family.common.basedata

import com.okay.family.fun.frame.SourceCode

/**
 * 用户信息处理类,请求family接口,放在内存中
 */
class UserName extends SourceCode {

    static Map<Integer, String> names = new HashMap<>()


    static String getUname(int id) {
        names.getOrDefault(id, DEFAULT_STRING)
    }
}
