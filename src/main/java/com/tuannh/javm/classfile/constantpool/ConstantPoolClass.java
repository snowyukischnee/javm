package com.tuannh.javm.classfile.constantpool;

import java.nio.ByteBuffer;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConstantPoolClass extends ConstantPoolInfo {
    //    CONSTANT_Class_info {
    //        u1 tag;
    //        u2 name_index;
    //    }
    private short nameIndex;

    public ConstantPoolClass(byte[] bytes) {
        super(ConstantPoolTag.CLASS);
        this.nameIndex = ByteBuffer.wrap(bytes).getShort();
    }

    public ConstantPoolClass(short nameIndex) {
        super(ConstantPoolTag.CLASS);
        this.nameIndex = nameIndex;
    }
}
