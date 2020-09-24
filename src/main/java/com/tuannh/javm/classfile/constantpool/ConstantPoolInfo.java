package com.tuannh.javm.classfile.constantpool;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

// cp_info {
//    u1 tag;
//    u1 info[];
//}
@AllArgsConstructor
@Getter
@ToString
public class ConstantPoolInfo {
    private ConstantPoolTag tag;
}
