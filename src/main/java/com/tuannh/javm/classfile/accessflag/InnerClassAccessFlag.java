package com.tuannh.javm.classfile.accessflag;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.6-300-D.1-D.1

//Flag Name	Value	Interpretation
//ACC_PUBLIC	0x0001	Marked or implicitly public in source.
//ACC_PRIVATE	0x0002	Marked private in source.
//ACC_PROTECTED	0x0004	Marked protected in source.
//ACC_STATIC	0x0008	Marked or implicitly static in source.
//ACC_FINAL	0x0010	Marked final in source.
//ACC_INTERFACE	0x0200	Was an interface in source.
//ACC_ABSTRACT	0x0400	Marked or implicitly abstract in source.
//ACC_SYNTHETIC	0x1000	Declared synthetic; not present in the source code.
//ACC_ANNOTATION	0x2000	Declared as an annotation type.
//ACC_ENUM	0x4000	Declared as an enum type.

@AllArgsConstructor
@Getter
public enum InnerClassAccessFlag {
    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    ACC_FINAL(0x0010),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000);

    private int value;

    public static InnerClassAccessFlag[] getAccessFlags(int flags) {
        List<InnerClassAccessFlag> list = new ArrayList<>();
        for (InnerClassAccessFlag flag : InnerClassAccessFlag.values()) {
            if ((flag.getValue() & flags) != 0) {
                list.add(flag);
            }
        }
        InnerClassAccessFlag[] ret = new InnerClassAccessFlag[list.size()];
        ret = list.toArray(ret);
        return ret;
    }
}
