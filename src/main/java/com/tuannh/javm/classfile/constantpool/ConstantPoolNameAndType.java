package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

@Getter
@ToString
public class ConstantPoolNameAndType extends ConstantPoolInfo {
//    CONSTANT_NameAndType_info {
//        u1 tag;
//        u2 name_index;
//        u2 descriptor_index;
//    }
    private short nameIndex;
    private short descriptorIndex;

    public ConstantPoolNameAndType(byte[] bytes) {
        super(ConstantPoolTag.NAME_AND_TYPE);
        this.nameIndex = ByteBuffer.wrap(ByteUtils.slice(bytes, 0, 2)).getShort();
        this.descriptorIndex = ByteBuffer.wrap(ByteUtils.slice(bytes, 2, 4)).getShort();
    }

    public ConstantPoolNameAndType(short nameIndex, short descriptorIndex) {
        super(ConstantPoolTag.NAME_AND_TYPE);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }
}
