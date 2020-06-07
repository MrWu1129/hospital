package com.hospital.enums;

/**
 * @program: hospital-project
 * @description: YesOrNo 枚举
 * @author: wty
 * @create: 2020-06-06 23:38
 */
public enum YesOrNo {

    NO(0,"否"),
    YES(1,"是");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
