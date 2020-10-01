package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.common.DebugPrint;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolUtf8;
import lombok.Getter;
import lombok.ToString;

import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

//attribute_info {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u1 info[attribute_length];
//}
@SuppressWarnings("java:S125")
@Getter
@ToString
public abstract class AttributeInfo implements DebugPrint {
    private int attributeNameIndex;
    private int attributeLength;
    private String attributeName;

    private ConstantPoolInfo[] constantPool;

    public AttributeInfo(int attributeNameIndex, int attributeLength, ConstantPoolInfo[] constantPool) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;

        this.constantPool = constantPool;
        attributeName = ((ConstantPoolUtf8)constantPool[attributeNameIndex - 1]).getValue();
    }
}
