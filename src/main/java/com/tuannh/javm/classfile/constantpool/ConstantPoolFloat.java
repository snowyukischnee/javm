package com.tuannh.javm.classfile.constantpool;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConstantPoolFloat extends ConstantPoolInfo {
    //    CONSTANT_Float_info {
    //        u1 tag;
    //        u4 bytes;
    //    }
    private byte[] bytes;

    public ConstantPoolFloat(byte[] bytes) {
        super(ConstantPoolTag.FLOAT);
        this.bytes = bytes;
    }
}