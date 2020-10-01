package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

import com.tuannh.javm.classfile.constantpool.ConstantPoolClass;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteBufferUtils;
import lombok.Getter;

@Getter
public class VerificationTypeInfoObject extends VerificationTypeInfo {
    private int cpoolIndex;

    private ConstantPoolClass cpool;

    public VerificationTypeInfoObject(byte[] bytes, ConstantPoolInfo[] constantPool) {
        super(VerificationTypeInfoTag.OBJECT_VARIABLE_INFO);
        cpoolIndex = ByteBufferUtils.getUnsignedShort(bytes);

        cpool = (ConstantPoolClass) constantPool[cpoolIndex - 1];
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("Object_variable_info: %s", cpool);
    }
}
