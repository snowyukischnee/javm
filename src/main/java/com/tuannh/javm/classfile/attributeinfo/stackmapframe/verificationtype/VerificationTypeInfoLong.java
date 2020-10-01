package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

public class VerificationTypeInfoLong extends VerificationTypeInfo {
    public VerificationTypeInfoLong() {
        super(VerificationTypeInfoTag.LONG_VARIABLE_INFO);
    }

    @Override
    public String debugPrint(int padding) {
        return "Long_variable_info";
    }
}
