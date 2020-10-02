package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_MethodHandle_info {
//        u1 tag;
//        u1 reference_kind;
//        u2 reference_index;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolMethodHandle extends ConstantPoolInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private byte referenceKind;
    private int referenceIndex;

    public ConstantPoolMethodHandle(byte[] bytes) {
        super(ConstantPoolTag.METHOD_HANDLE);
        this.referenceKind = ByteBuffer.wrap(ByteUtils.slice(bytes, 0, 1)).get();
        this.referenceIndex = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 1, 3));
    }

    public ConstantPoolMethodHandle(byte referenceKind, short referenceIndex) {
        super(ConstantPoolTag.METHOD_HANDLE);
        this.referenceKind = referenceKind;
        this.referenceIndex = Conversion.shortToInt(referenceIndex);
    }

    @Override
    public void resolve(ConstantPoolInfo[] obj) {
        // TODO implement resolve method
    }

    @Override
    public String debugPrint(int padding) {
        // TODO debug print
        return toString();
    }
}
