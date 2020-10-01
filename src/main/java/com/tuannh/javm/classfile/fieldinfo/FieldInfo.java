package com.tuannh.javm.classfile.fieldinfo;

import com.tuannh.javm.classfile.accessflag.FieldAccessFlag;
import com.tuannh.javm.classfile.attributeinfo.AttributeInfo;
import com.tuannh.javm.classfile.common.DebugPrint;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.constantpool.ConstantPoolUtf8;
import lombok.Getter;
import lombok.ToString;

import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

//field_info {
//    u2             access_flags;
//    u2             name_index;
//    u2             descriptor_index;
//    u2             attributes_count;
//    attribute_info attributes[attributes_count];
//}
@SuppressWarnings("java:S125")
@Getter
@ToString
public class FieldInfo implements DebugPrint {
    private FieldAccessFlag[] accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private AttributeInfo[] attributes;
    private String name;
    private String descriptor;

    private ConstantPoolInfo[] constantPool;

    public FieldInfo(FieldAccessFlag[] accessFlag, int nameIndex, int descriptorIndex, int attributesCount, AttributeInfo[] attributes, ConstantPoolInfo[] constantPool) {
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = attributes;

        this.constantPool = constantPool;
        name = ((ConstantPoolUtf8)constantPool[nameIndex - 1]).getValue();
        descriptor = ((ConstantPoolUtf8)constantPool[descriptorIndex - 1]).getValue();
    }

    @Override
    public String debugPrint(int padding) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("name: %-10s%n", name));
        builder.append(String.format("%sdescriptor: %-10s%n", PADDING[padding], descriptor));
        builder.append(String.format("%sattributes(count=%d):%n", PADDING[padding], attributesCount));
        for (int i = 0; i < attributesCount; i++) {
            builder.append(String.format("%s\t%s%n", PADDING[padding], attributes[i].debugPrint(padding + 1)));
        }
        return builder.toString();
    }
}
