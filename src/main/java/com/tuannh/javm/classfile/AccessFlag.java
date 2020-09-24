package com.tuannh.javm.classfile;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1-200-E.1

//Flag Name	Value	Interpretation
//ACC_PUBLIC	0x0001	Declared public; may be accessed from outside its package.
//ACC_FINAL	0x0010	Declared final; no subclasses allowed.
//ACC_SUPER	0x0020	Treat superclass methods specially when invoked by the invokespecial instruction.
//ACC_INTERFACE	0x0200	Is an interface, not a class.
//ACC_ABSTRACT	0x0400	Declared abstract; must not be instantiated.
//ACC_SYNTHETIC	0x1000	Declared synthetic; not present in the source code.
//ACC_ANNOTATION	0x2000	Declared as an annotation type.
//ACC_ENUM	0x4000	Declared as an enum type.

@AllArgsConstructor
@Getter
public enum AccessFlag {
    ACC_PUBLIC(0x0001),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000);

    private int value;

    public static AccessFlag[] getAccessFlags(int flags) {
        List<AccessFlag> list = new ArrayList<>();
        for (AccessFlag flag : AccessFlag.values()) {
            if ((flag.getValue() & flags) != 0) {
                list.add(flag);
            }
        }
        AccessFlag[] ret = new AccessFlag[list.size()];
        ret = list.toArray(ret);
        return ret;
    }
}
