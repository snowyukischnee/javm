package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

public class VerificationTypeInfoInteger extends VerificationTypeInfo {
    public VerificationTypeInfoInteger() {
        super(VerificationTypeInfoTag.INTEGER_VARIABLE_INFO);
    }

    @Override
    public String debugPrint(int padding) {
        return "Integer_variable_info";
    }
}
