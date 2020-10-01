package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

public class VerificationTypeInfoUninitializedThis extends VerificationTypeInfo {
    public VerificationTypeInfoUninitializedThis() {
        super(VerificationTypeInfoTag.UNINITIALIZEDTHIS_VARIABLE_INFO);
    }

    @Override
    public String debugPrint(int padding) {
        return "UninitializedThis_variable_info";
    }
}
