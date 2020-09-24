package com.tuannh.javm.classfile.constantpool;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConstantPoolInteger extends ConstantPoolInfo {
    //    CONSTANT_Integer_info {
    //        u1 tag;
    //        u4 bytes;
    //    }
    private byte[] bytes;

    public ConstantPoolInteger(byte[] bytes) {
        super(ConstantPoolTag.INTEGER);
        this.bytes = bytes;
    }
}