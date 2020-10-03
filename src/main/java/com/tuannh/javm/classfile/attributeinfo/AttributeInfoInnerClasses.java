package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

//InnerClasses_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 number_of_classes;
//    {   u2 inner_class_info_index;
//        u2 outer_class_info_index;
//        u2 inner_name_index;
//        u2 inner_class_access_flags;
//    } classes[number_of_classes];
//}

//number_of_classes
//The value of the number_of_classes item indicates the number of entries in the classes array.
//
//classes[]
//Every CONSTANT_Class_info entry in the constant_pool table which represents a class or interface C that is
// not a package member must have exactly one corresponding entry in the classes array.
//
//If a class or interface has members that are classes or interfaces, its constant_pool table
// (and hence its InnerClasses attribute) must refer to each such member (JLS §13.1), even if that
// member is not otherwise mentioned by the class.
//
//In addition, the constant_pool table of every nested class and nested interface must refer to its enclosing class,
// so altogether, every nested class and nested interface will have InnerClasses information for each enclosing class
// and for each of its own nested classes and interfaces.
//
//Each entry in the classes array contains the following four items:
//
//inner_class_info_index
//The value of the inner_class_info_index item must be a valid index into the constant_pool table. The constant_pool
// entry at that index must be a CONSTANT_Class_info structure representing C. The remaining items in the classes
// array entry give information about C.
//
//outer_class_info_index
//If C is not a member of a class or an interface (that is, if C is a top-level class or interface (JLS §7.6) or a
// local class (JLS §14.3) or an anonymous class (JLS §15.9.5)), the value of the outer_class_info_index item must be zero.
//
//Otherwise, the value of the outer_class_info_index item must be a valid index into the constant_pool table, and the
// entry at that index must be a CONSTANT_Class_info structure representing the class or interface of which C is a member.
//
//inner_name_index
//If C is anonymous (JLS §15.9.5), the value of the inner_name_index item must be zero.
//
//Otherwise, the value of the inner_name_index item must be a valid index into the constant_pool table, and the entry
// at that index must be a CONSTANT_Utf8_info structure (§4.4.7) that represents the original simple name of C, as
// given in the source code from which this class file was compiled.
//
//inner_class_access_flags
//The value of the inner_class_access_flags item is a mask of flags used to denote access permissions to and properties
// of class or interface C as declared in the source code from which this class file was compiled. It is used by a
// compiler to recover the original information when source code is not available. The flags are specified in Table 4.7.6-A.

@SuppressWarnings("java:S125")
@Getter
@ToString
public class AttributeInfoInnerClasses extends AttributeInfo {
    private int numberOfClasses;
    private InnerClass[] classes;

    public AttributeInfoInnerClasses(int attributeNameIndex, int attributeLength, byte[] bytes, ConstantPoolInfo[] constantPool) {
        super(attributeNameIndex, attributeLength, constantPool);
        numberOfClasses = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
        classes = new InnerClass[numberOfClasses];
        int readByte = 2;
        for (int i = 0; i < numberOfClasses; i++) {
            classes[i] = new InnerClass(
                    ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, readByte, readByte + 2)),
                    ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, readByte + 2, readByte + 4)),
                    ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, readByte + 4, readByte + 6)),
                    ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, readByte + 6, readByte + 8)),
                    constantPool
            );
            readByte += 8;
        }
    }

    @Override
    public String debugPrint(int padding) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("InnerClasses(number_of_classes=%d):%n", numberOfClasses));
        for (int i = 0; i < numberOfClasses; i++) {
            builder.append(String.format("%s\t%s", PADDING[padding], classes[i].debugPrint(padding + 1)));
        }
        return builder.toString();
    }
}
