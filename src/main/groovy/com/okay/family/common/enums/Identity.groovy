package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

enum Identity {

    STUDENT(1, "学生"),
    TEACHER(2, "教师"),
    PARENT(3, "家长")

    int code

    def desc

    Identity(int code, def desc) {
        this.code = code
        this.desc = desc
    }

    static JSONArray getAll() {
        def all = []
        def stu = [code: STUDENT.getCode(), desc: STUDENT.getDesc()] as JSONObject
        stu.putAll(data:Student.getAll())
        all << stu
        def tea = [code:TEACHER.getCode(),desc: TEACHER.getDesc()] as JSONObject
        tea.putAll(data:  Teacher.getAll())
        all << tea
        all
    }

    enum Student {

        STU_PAD(11, "学生pad"),
        STU_WEB(12, "学生空间")

        int code

        String desc

        Student(int code, String desc) {
            this.code = code
            this.desc = desc
        }

        static JSONObject getAll() {
            Student.values().stream().collect(Collectors.toMap({x -> x.getCode()}, {x -> x.getDesc()}))
        }
    }

    enum Teacher {

        TEA_PAD(13, "老师pad"),
        TEA_WEB(14, "教师空间")

        int code

        String desc

        Teacher(int code, String desc) {
            this.code = code
            this.desc = desc
        }

        static JSONObject getAll() {
            Teacher.values().stream().collect(Collectors.toMap({x -> x.getCode()}, {x -> x.getDesc()}))
        }

    }

    enum Parent {

        TEA_PAD(15, "家长pad"),
        TEA_WEB(16, "家长空间")

        int code

        String desc

        Parent(int code, String desc) {
            this.code = code
            this.desc = desc
        }

        static JSONObject getAll() {
            Parent.values().stream().collect(Collectors.toMap({x -> x.getCode()}, {x -> x.getDesc()}))
        }
    }


}