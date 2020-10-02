package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.constantpool.ConstantPoolClass;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

//Exceptions_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 number_of_exceptions;
//    u2 exception_index_table[number_of_exceptions];
//}
//
//number_of_exceptions
//    The value of the number_of_exceptions item indicates the number of entries in the exception_index_table.
//
//exception_index_table[]
//    Each value in the exception_index_table array must be a valid index into the constant_pool table.
//    The constant_pool entry at that index must be a CONSTANT_Class_info structure (§4.4.1) representing
//    a class type that this method is declared to throw.

@SuppressWarnings("java:S125")
@Getter
@ToString
public class AttributeInfoExceptions extends AttributeInfo {
    private int numberOfExceptions;
    private int[] exceptionIndexTable;

    private ConstantPoolClass[] exceptionsTable;

    @SuppressWarnings("java:S112")
    public AttributeInfoExceptions(int attributeNameIndex, int attributeLength, byte[] bytes, ConstantPoolInfo[] constantPool) {
        super(attributeNameIndex, attributeLength, constantPool);
        this.numberOfExceptions = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
        exceptionIndexTable = new int[numberOfExceptions];
        for (int i = 0; i < numberOfExceptions; i++) {
            exceptionIndexTable[i] = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, (i + 1) << 1, (i + 2) << 1));
        }
        exceptionsTable = new ConstantPoolClass[numberOfExceptions];
        for (int i = 0; i < numberOfExceptions; i++) {
            exceptionsTable[i] = (ConstantPoolClass) constantPool[exceptionIndexTable[i] - 1];
        }
    }

    @Override
    public String debugPrint(int padding) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Exceptions(number_of_exceptions=%d):---------------------------------------------%n", numberOfExceptions));
        for (int i = 0; i < numberOfExceptions; i++) {
            builder.append(String.format("%s\t%s%n", PADDING[padding], exceptionsTable[i].debugPrint(0)));
        }
        builder.append(String.format("%s--------------------------------------------------------------------------------", PADDING[padding]));
        return builder.toString();
    }
}
