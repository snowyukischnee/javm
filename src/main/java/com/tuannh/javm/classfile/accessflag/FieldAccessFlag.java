package com.tuannh.javm.classfile.accessflag;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.5

//ACC_PUBLIC	0x0001	Declared public; may be accessed from outside its package.
//ACC_PRIVATE	0x0002	Declared private; usable only within the defining class.
//ACC_PROTECTED	0x0004	Declared protected; may be accessed within subclasses.
//ACC_STATIC	0x0008	Declared static.
//ACC_FINAL	0x0010	Declared final; never directly assigned to after object construction (JLS §17.5).
//ACC_VOLATILE	0x0040	Declared volatile; cannot be cached.
//ACC_TRANSIENT	0x0080	Declared transient; not written or read by a persistent object manager.
//ACC_SYNTHETIC	0x1000	Declared synthetic; not present in the source code.
//ACC_ENUM	0x4000	Declared as an element of an enum.

@AllArgsConstructor
@Getter
public enum FieldAccessFlag {
    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    ACC_FINAL(0x0010),
    ACC_VOLATILE(0x0040),
    ACC_TRANSIENT(0x0080),
    ACC_SYNTHETIC(0x1000),
    ACC_ENUM(0x4000);

    private int value;

    public static FieldAccessFlag[] getAccessFlags(int flags) {
        List<FieldAccessFlag> list = new ArrayList<>();
        for (FieldAccessFlag flag : FieldAccessFlag.values()) {
            if ((flag.getValue() & flags) != 0) {
                list.add(flag);
            }
        }
        FieldAccessFlag[] ret = new FieldAccessFlag[list.size()];
        ret = list.toArray(ret);
        return ret;
    }
}
