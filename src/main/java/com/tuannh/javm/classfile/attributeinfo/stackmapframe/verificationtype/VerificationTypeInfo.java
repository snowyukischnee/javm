package com.tuannh.javm.classfile.attributeinfo.stackmapframe.verificationtype;

import com.tuannh.javm.classfile.common.DebugPrint;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class VerificationTypeInfo implements DebugPrint {
    private VerificationTypeInfoTag tag;
}
