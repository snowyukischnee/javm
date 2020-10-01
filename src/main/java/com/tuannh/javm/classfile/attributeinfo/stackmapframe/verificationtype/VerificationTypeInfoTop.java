package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

public class VerificationTypeInfoTop extends VerificationTypeInfo {
    public VerificationTypeInfoTop() {
        super(VerificationTypeInfoTag.TOP_VARIABLE_INFO);
    }

    @Override
    public String debugPrint(int padding) {
        return "Top_variable_info";
    }
}
