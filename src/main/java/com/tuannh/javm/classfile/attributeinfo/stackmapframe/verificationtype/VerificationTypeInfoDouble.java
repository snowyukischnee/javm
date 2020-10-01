package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

public class VerificationTypeInfoDouble extends VerificationTypeInfo {
    public VerificationTypeInfoDouble() {
        super(VerificationTypeInfoTag.DOUBLE_VARIABLE_INFO);
    }

    @Override
    public String debugPrint(int padding) {
        return "Double_variable_info";
    }
}
