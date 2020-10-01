package com.tuannh.javm.classfile.methodinfo;

//method_info {
//    u2             access_flags;
//    u2             name_index;
//    u2             descriptor_index;
//    u2             attributes_count;
//    attribute_info attributes[attributes_count];
//}

import com.tuannh.javm.classfile.accessflag.MethodAccessFlag;
import com.tuannh.javm.classfile.attributeinfo.AttributeInfo;
import com.tuannh.javm.classfile.common.DebugPrint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

@SuppressWarnings("java:S125")
@AllArgsConstructor
@Getter
@ToString
public class MethodInfo implements DebugPrint {
    private MethodAccessFlag[] accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private AttributeInfo[] attributes;

    private String name;
    private String descriptor;


    @Override
    public String debugPrint(int padding) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s%s%n", name, descriptor));
        builder.append(String.format("%sdescriptor: %s%n", PADDING[padding], descriptor));
        builder.append(String.format("%sflags: %s%n", PADDING[padding], Arrays.toString(accessFlag)));
        builder.append(String.format("%sAttributes:%n", PADDING[padding]));
        for (int i = 0; i < attributesCount; i++) {
            if (attributes[i] != null) {
                builder.append(String.format("%s\t%s%n", PADDING[padding], attributes[i].debugPrint(padding + 1)));
            }
        }
        return builder.toString();
    }
}
