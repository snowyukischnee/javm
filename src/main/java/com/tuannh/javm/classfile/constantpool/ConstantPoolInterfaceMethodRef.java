package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

@Getter
@ToString
public class ConstantPoolInterfaceMethodRef extends ConstantPoolInfo {
    //    CONSTANT_InterfaceMethodref_info {
    //        u1 tag;
    //        u2 class_index;
    //        u2 name_and_type_index;
    //    }
    private short classIndex;
    private short nameAndTypeIndex;

    public ConstantPoolInterfaceMethodRef(byte[] bytes) {
        super(ConstantPoolTag.INTERFACE_METHOD_REF);
        this.classIndex = ByteBuffer.wrap(ByteUtils.slice(bytes, 0, 2)).getShort();
        this.nameAndTypeIndex = ByteBuffer.wrap(ByteUtils.slice(bytes, 2, 4)).getShort();
    }

    public ConstantPoolInterfaceMethodRef(short classIndex, short nameAndTypeIndex) {
        super(ConstantPoolTag.INTERFACE_METHOD_REF);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}