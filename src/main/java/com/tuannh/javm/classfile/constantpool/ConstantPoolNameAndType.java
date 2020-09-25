package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.DebugPrintConstants;
import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

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
    private int nameIndex;
    private int descriptorIndex;
    private String name;
    private String descriptor;

    public ConstantPoolNameAndType(byte[] bytes) {
        super(ConstantPoolTag.NAME_AND_TYPE);
        this.nameIndex = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
        this.descriptorIndex = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 2, 4));
    }

    public ConstantPoolNameAndType(short nameIndex, short descriptorIndex) {
        super(ConstantPoolTag.NAME_AND_TYPE);
        this.nameIndex = Conversion.shortToInt(nameIndex);
        this.descriptorIndex = Conversion.shortToInt(descriptorIndex);
    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        name = ((ConstantPoolUtf8)constantPool[nameIndex - 1]).getValue();
        descriptor = ((ConstantPoolUtf8)constantPool[descriptorIndex - 1]).getValue();
    }

    @Override
    public String debugPrint() {
        return String.format("%-25s#%d.#%d%15s %s:%s", getTag(), nameIndex, descriptorIndex, DebugPrintConstants.SEPERATOR, name, descriptor);
    }
}
