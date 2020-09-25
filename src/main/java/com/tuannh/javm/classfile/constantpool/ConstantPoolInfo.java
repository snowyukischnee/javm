package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.DebugStr;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

// cp_info {
//    u1 tag;
//    u1 info[];
//}
@SuppressWarnings("java:S125")
@AllArgsConstructor
@Getter
@ToString
public abstract class ConstantPoolInfo implements DebugStr {
    private ConstantPoolTag tag;
}
