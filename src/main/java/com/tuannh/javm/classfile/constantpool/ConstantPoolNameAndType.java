package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_NameAndType_info {
//        u1 tag;
//        u2 name_index;
//        u2 descriptor_index;
//    }
//
//name_index
//        The value of the name_index item must be a valid index into the constant_pool table.
//        The constant_pool entry at that index must be a CONSTANT_Utf8_info structure (§4.4.7)
//        representing either the special method name <init> (§2.9) or a valid unqualified name
//        denoting a field or method (§4.2.2).
//
//descriptor_index
//        The value of the descriptor_index item must be a valid index into the constant_pool table.
//        The constant_pool entry at that index must be a CONSTANT_Utf8_info structure (§4.4.7)
//        representing a valid field descriptor or method descriptor (§4.3.2, §4.3.3).
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolNameAndType extends ConstantPoolInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private short nameIndex;
    private short descriptorIndex;
    private String name;
    private String descriptor;

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

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        name = ((ConstantPoolUtf8)constantPool[nameIndex - 1]).getValue();
        descriptor = ((ConstantPoolUtf8)constantPool[descriptorIndex - 1]).getValue();
    }
}
