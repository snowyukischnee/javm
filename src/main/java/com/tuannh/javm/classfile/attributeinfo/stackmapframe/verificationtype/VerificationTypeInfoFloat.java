package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

public class VerificationTypeInfoFloat extends VerificationTypeInfo {
    public VerificationTypeInfoFloat() {
        super(VerificationTypeInfoTag.FLOAT_VARIABLE_INFO);
    }

    @Override
    public String debugPrint(int padding) {
        return "Float_variable_info";
    }
}
