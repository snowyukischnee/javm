package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

//    CONSTANT_MethodType_info {
//        u1 tag;
//        u2 descriptor_index;
//    }
//
//descriptor_index
//        The value of the descriptor_index item must be a valid index into the constant_pool table.
//        The constant_pool entry at that index must be a CONSTANT_Utf8_info structure (§4.4.7)
//        representing a method descriptor (§4.3.3).
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolMethodType extends ConstantPoolInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private int descriptorIndex;
    private String descriptor;

    public ConstantPoolMethodType(byte[] bytes) {
        super(ConstantPoolTag.METHOD_TYPE);
        this.descriptorIndex = ByteBufferUtils.getUnsignedShort(bytes);
    }

    public ConstantPoolMethodType(short descriptorIndex) {
        super(ConstantPoolTag.METHOD_TYPE);
        this.descriptorIndex = Conversion.shortToInt(descriptorIndex);
    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        descriptor = ((ConstantPoolUtf8)constantPool[descriptorIndex - 1]).getValue();
    }

    @Override
    public String debugPrint() {
        // TODO
        return toString();
    }
}
