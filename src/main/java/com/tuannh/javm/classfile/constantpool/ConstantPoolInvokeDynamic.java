package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_InvokeDynamic_info {
//        u1 tag;
//        u2 bootstrap_method_attr_index;
//        u2 name_and_type_index;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolInvokeDynamic extends ConstantPoolInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    public ConstantPoolInvokeDynamic(byte[] bytes) {
        super(ConstantPoolTag.METHOD_TYPE);
        this.bootstrapMethodAttrIndex = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
        this.nameAndTypeIndex = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 2, 4));
    }

    public ConstantPoolInvokeDynamic(short bootstrapMethodAttrIndex, short nameAndTypeIndex) {
        super(ConstantPoolTag.METHOD_TYPE);
        this.bootstrapMethodAttrIndex = Conversion.shortToInt(bootstrapMethodAttrIndex);
        this.nameAndTypeIndex = Conversion.shortToInt(nameAndTypeIndex);
    }

    @Override
    public void resolve(ConstantPoolInfo[] obj) {
        // TODO
    }

    @Override
    public String debugPrint() {
        // TODO
        return toString();
    }
}
