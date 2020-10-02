package com.tuannh.javm.classfile.attributeinfo;

//Code_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 max_stack;
//    u2 max_locals;
//    u4 code_length;
//    u1 code[code_length];
//    u2 exception_table_length;
//    {   u2 start_pc;
//        u2 end_pc;
//        u2 handler_pc;
//        u2 catch_type;
//    } exception_table[exception_table_length];
//    u2 attributes_count;
//    attribute_info attributes[attributes_count];
//}
//
//max_stack
//    The value of the max_stack item gives the maximum depth of the operand stack of this method (§2.6.2) at
//    any point during execution of the method.
//max_locals
//    The value of the max_locals item gives the number of local variables in the local variable array
//    allocated upon invocation of this method (§2.6.1), including the local variables used to pass parameters
//    to the method on its invocation.
//    The greatest local variable index for a value of type long or double is max_locals - 2. The
//    greatest local variable index for a value of any other type is max_locals - 1.
//code_length
//    The value of the code_length item gives the number of bytes in the code array for this method. The value of
//    code_length must be greater than zero; the code array must not be empty.
//code[]
//    The code array gives the actual bytes of Java Virtual Machine code that implement the method.
//    When the code array is read into memory on a byte-addressable machine, if the first byte of the array is aligned
//    on a 4-byte boundary, the tableswitch and lookupswitch 32-bit offsets will be 4-byte aligned.
//    (Refer to the descriptions of those instructions for more information on the consequences of code array alignment.)
//    The detailed constraints on the contents of the code array are extensive and are given in a separate section (§4.9).
//exception_table_length
//    The value of the exception_table_length item gives the number of entries in the exception_table table.
//exception_table[]
//    Each entry in the exception_table array describes one exception handler in the code array. The order of the handlers
//    in the exception_table array is significant (§2.10).
//    Each exception_table entry contains the following four items:
//
//    start_pc, end_pc
//        The values of the two items start_pc and end_pc indicate the ranges in the code array at which the exception
//        handler is active. The value of start_pc must be a valid index into the code array of the opcode of an instruction.
//        The value of end_pc either must be a valid index into the code array of the opcode of an instruction or must be
//        equal to code_length, the length of the code array. The value of start_pc must be less than the value of end_pc.
//
//        The start_pc is inclusive and end_pc is exclusive; that is, the exception handler must be active while the
//        program counter is within the interval [start_pc, end_pc).
//
//        The fact that end_pc is exclusive is a historical mistake in the design of the Java Virtual Machine: if the
//        Java Virtual Machine code for a method is exactly 65535 bytes long and ends with an instruction that is
//        1 byte long, then that instruction cannot be protected by an exception handler. A compiler writer can
//        work around this bug by limiting the maximum size of the generated Java Virtual Machine code for any method,
//        instance initialization method, or static initializer (the size of any code array) to 65534 bytes.
//    handler_pc
//        The value of the handler_pc item indicates the start of the exception handler. The value of the item must be a
//        valid index into the code array and must be the index of the opcode of an instruction.
//    catch_type
//        If the value of the catch_type item is nonzero, it must be a valid index into the constant_pool table.
//        The constant_pool entry at that index must be a CONSTANT_Class_info structure (§4.4.1) representing a
//        class of exceptions that this exception handler is designated to catch. The exception handler will be called
//        only if the thrown exception is an instance of the given class or one of its subclasses.
//        If the value of the catch_type item is zero, this exception handler is called for all exceptions.
//        This is used to implement finally (§3.13).
//attributes_count
//    The value of the attributes_count item indicates the number of attributes of the Code attribute.
//attributes[]
//    Each value of the attributes table must be an attribute structure (§4.7). A Code attribute can have any number
//    of optional attributes associated with it.
//    The only attributes defined by this specification as appearing in the attributes table of a
//    Code attribute are the LineNumberTable (§4.7.12), LocalVariableTable (§4.7.13),
//    LocalVariableTypeTable (§4.7.14), and StackMapTable (§4.7.4) attributes.
//    If a Java Virtual Machine implementation recognizes class files whose version number is 50.0 or above,
//    it must recognize and correctly read StackMapTable (§4.7.4) attributes found in the attributes table of a
//    Code attribute of a class file whose version number is 50.0 or above.
//    A Java Virtual Machine implementation is required to silently ignore any or all attributes in the
//    attributes table of a Code attribute that it does not recognize. Attributes not defined in this specification are
//    not allowed to affect the semantics of the class file, but only to provide additional descriptive information (§4.7.1).

import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import com.tuannh.javm.classfile.instruction.Instruction;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.nio.ByteBuffer;

import static com.tuannh.javm.classfile.attributeinfo.AttributeInfoParser.parseAttributeInfo;
import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;
import static com.tuannh.javm.classfile.instruction.InstructionParser.parseInstruction;

@SuppressWarnings("java:S125")
@Getter
@ToString
public class AttributeInfoCode extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLength;
    private byte[] code;
    private int exceptionTableLength;
    private ExceptionTableEntry[] exceptionTable;
    private int attributesCount;
    private AttributeInfo[] attributes;

    private Instruction[] instructions;

    @SuppressWarnings("java:S112")
    public AttributeInfoCode(int attributeNameIndex, int attributeLength, byte[] bytes, ConstantPoolInfo[] constantPool) throws IOException {
        super(attributeNameIndex, attributeLength, constantPool);
        maxStack = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
        maxLocals = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 2, 4));
        codeLength = ByteBuffer.wrap(ByteUtils.slice(bytes, 4, 8)).getInt();
        code = ByteUtils.slice(bytes, 8, 8 + codeLength);
        instructions = parseInstruction(code, codeLength);
        int currentRead = 8 + codeLength;
        exceptionTableLength = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, currentRead, currentRead + 2));
        exceptionTable = new ExceptionTableEntry[exceptionTableLength];
        currentRead = currentRead + 2;
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionTableEntry(
                    ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, currentRead, currentRead + 2)),
                    ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, currentRead + 2, currentRead + 4)),
                    ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, currentRead + 4, currentRead + 6)),
                    ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, currentRead + 6, currentRead + 8)),
                    constantPool
            );
            currentRead += 8;
        }
        attributesCount = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, currentRead, currentRead + 2));
        attributes = parseAttributeInfo(ByteUtils.slice(bytes, currentRead + 2, bytes.length), attributesCount, constantPool);
    }

    @Override
    public String debugPrint(int padding) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Code:---------------------------------------------------------------------------%n"));
        // ---------------------------------------------------------------------
        builder.append(PADDING[padding]);
        builder.append(String.format("max_stack: %d\t", maxStack));
        builder.append(String.format("max_local: %d\t", maxLocals));
        builder.append("\n");
        // ---------------------------------------------------------------------
        final String TABLE_ITEM_FMT = "%s\t#%d: %s%n";
        for (int i = 0; i < codeLength; i++) {
            if (instructions[i] != null) {
                builder.append(String.format(TABLE_ITEM_FMT, PADDING[padding], i, instructions[i].debugPrint(padding + 1)));
            }
        }
        builder.append(String.format("%sexception_table(count=%d):%n", PADDING[padding], exceptionTableLength));
        for (int i = 0; i < exceptionTableLength; i++) {
            builder.append(String.format(TABLE_ITEM_FMT, PADDING[padding], i, exceptionTable[i].debugPrint(padding + 1)));
        }
        builder.append(String.format("%sattributes(count=%d):%n", PADDING[padding], attributesCount));
        for (int i = 0; i < attributesCount; i++) {
            if (attributes[i] != null) {
                builder.append(String.format(TABLE_ITEM_FMT, PADDING[padding], i, attributes[i].debugPrint(padding + 1)));
            }
        }
        builder.append(String.format("%s--------------------------------------------------------------------------------", PADDING[padding]));
        return builder.toString();
    }
}
