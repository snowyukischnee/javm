package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

public class VerificationTypeInfoNull extends VerificationTypeInfo {
    public VerificationTypeInfoNull() {
        super(VerificationTypeInfoTag.NULL_VARIABLE_INFO);
    }

    @Override
    public String debugPrint(int padding) {
        return "Null_variable_info";
    }
}
