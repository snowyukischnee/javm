package com.tuannh.javm.classfile.instruction;

import lombok.AllArgsConstructor;
import lombok.Getter;

//https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html
//https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-7.html

@Getter
@AllArgsConstructor
public enum Opcode {
    // Constants
    NOP((byte)0x00, 0),
    ACONST_NULL((byte)0x01, 0),
    ICONST_M1((byte)0x02, 0),
    ICONST_0((byte)0x03, 0),
    ICONST_1((byte)0x04, 0),
    ICONST_2((byte)0x05, 0),
    ICONST_3((byte)0x06, 0),
    ICONST_4((byte)0x07, 0),
    ICONST_5((byte)0x08, 0),
    LCONST_0((byte)0x09, 0),
    LCONST_1((byte)0x0a, 0),
    FCONST_0((byte)0x0b, 0),
    FCONST_1((byte)0x0c, 0),
    FCONST_2((byte)0x0d, 0),
    DCONST_0((byte)0x0e, 0),
    DCONST_1((byte)0x0f, 0),
    BIPUSH((byte) 0x10, 1), // byte
    SIPUSH((byte)0x11, 2), // byte1, byte2
    LDC((byte) 0x12, 1), // index
    LDC_W((byte) 0x13, 2), // indexbyte1, indexbyte2
    LDC2_W((byte)0x14, 2), // indexbyte1, indexbyte2
    // Loads
    ILOAD((byte)0x15, 1), // index
    LLOAD((byte)0x16, 1), // index
    FLOAD((byte)0x17, 1), // index
    DLOAD((byte)0x18, 1), // index
    ALOAD((byte)0x19, 1), // index
    ILOAD_0((byte)0x1a, 0),
    ILOAD_1((byte)0x1b, 0),
    ILOAD_2((byte)0x1c, 0),
    ILOAD_3((byte)0x1d, 0),
    LLOAD_0((byte)0x1e, 0),
    LLOAD_1((byte)0x1f, 0),
    LLOAD_2((byte)0x20, 0),
    LLOAD_3((byte)0x21, 0),
    FLOAD_0((byte)0x22, 0),
    FLOAD_1((byte)0x23, 0),
    FLOAD_2((byte)0x24, 0),
    FLOAD_3((byte)0x25, 0),
    DLOAD_0((byte)0x26, 0),
    DLOAD_1((byte)0x27, 0),
    DLOAD_2((byte)0x28, 0),
    DLOAD_3((byte)0x29, 0),
    ALOAD_0((byte)0x2a, 0),
    ALOAD_1((byte)0x2b, 0),
    ALOAD_2((byte)0x2c, 0),
    ALOAD_3((byte)0x2d, 0),
    IALOAD((byte)0x2e, 0),
    LALOAD((byte)0x2f, 0),
    FALOAD((byte)0x30, 0),
    DALOAD((byte)0x31, 0),
    AALOAD((byte)0x32, 0),
    BALOAD((byte)0x33, 0),
    CALOAD((byte)0x34, 0),
    SALOAD((byte)0x35, 0),
    // Stores
    ISTORE((byte)0x36, 1), // index
    LSTORE((byte)0x37, 1), // index
    FSTORE((byte)0x38, 1), // index
    DSTORE((byte)0x39, 1), // index
    ASTORE((byte)0x3a, 1), // index
    ISTORE_0((byte)0x3b, 0),
    ISTORE_1((byte)0x3c, 0),
    ISTORE_2((byte)0x3d, 0),
    ISTORE_3((byte)0x3e, 0),
    LSTORE_0((byte)0x3f, 0),
    LSTORE_1((byte)0x40, 0),
    LSTORE_2((byte)0x41, 0),
    LSTORE_3((byte)0x42, 0),
    FSTORE_0((byte)0x43, 0),
    FSTORE_1((byte)0x44, 0),
    FSTORE_2((byte)0x45, 0),
    FSTORE_3((byte)0x46, 0),
    DSTORE_0((byte)0x47, 0),
    DSTORE_1((byte)0x48, 0),
    DSTORE_2((byte)0x49, 0),
    DSTORE_3((byte)0x4a, 0),
    ASTORE_0((byte)0x4b, 0),
    ASTORE_1((byte)0x4c, 0),
    ASTORE_2((byte)0x4d, 0),
    ASTORE_3((byte)0x4e, 0),
    IASTORE((byte)0x4f, 0),
    LASTORE((byte)0x50, 0),
    FASTORE((byte)0x51, 0),
    DASTORE((byte)0x52, 0),
    AASTORE((byte)0x53, 0),
    BASTORE((byte)0x54, 0),
    CASTORE((byte)0x55, 0),
    SASTORE((byte)0x56, 0),
    // Stack
    POP((byte)0x57, 0),
    POP2((byte)0x58, 0),
    DUP((byte)0x59, 0),
    DUP_X1((byte)0x5a, 0),
    DUP_X2((byte)0x5b, 0),
    DUP2((byte)0x5c, 0),
    DUP2_X1((byte)0x5d, 0),
    DUP2_X2((byte)0x5e, 0),
    SWAP((byte)0x5f, 0),
    // Math
    IADD((byte)0x60, 0),
    LADD((byte)0x61, 0),
    FADD((byte)0x62, 0),
    DADD((byte)0x63, 0),
    ISUB((byte)0x64, 0),
    LSUB((byte)0x65, 0),
    FSUB((byte)0x66, 0),
    DSUB((byte)0x67, 0),
    IMUL((byte)0x68, 0),
    LMUL((byte)0x69, 0),
    FMUL((byte)0x6a, 0),
    DMUL((byte)0x6b, 0),
    IDIV((byte)0x6c, 0),
    LDIV((byte)0x6d, 0),
    FDIV((byte)0x6e, 0),
    DDIV((byte)0x6f, 0),
    IREM((byte)0x70, 0),
    LREM((byte)0x71, 0),
    FREM((byte)0x72, 0),
    DREM((byte)0x73, 0),
    INEG((byte)0x74, 0),
    LNEG((byte)0x75, 0),
    FNEG((byte)0x76, 0),
    DNEG((byte)0x77, 0),
    ISHL((byte)0x78, 0),
    LSHL((byte)0x79, 0),
    ISHR((byte)0x7a, 0),
    LSHR((byte)0x7b, 0),
    IUSHR((byte)0x7c, 0),
    LUSHR((byte)0x7d, 0),
    IAND((byte)0x7e, 0),
    LAND((byte)0x7f, 0),
    IOR((byte)0x80, 0),
    LOR((byte)0x81, 0),
    IXOR((byte)0x82, 0),
    LXOR((byte)0x83, 0),
    IINC((byte)0x84, 2), // index, const
    // Conversions
    I2L((byte)0x85, 0),
    I2F((byte)0x86, 0),
    I2D((byte)0x87, 0),
    L2I((byte)0x88, 0),
    L2F((byte)0x89, 0),
    L2D((byte)0x8a, 0),
    F2I((byte)0x8b, 0),
    F2L((byte)0x8c, 0),
    F2D((byte)0x8d, 0),
    D2I((byte)0x8e, 0),
    D2L((byte)0x8f, 0),
    D2F((byte)0x90, 0),
    I2B((byte)0x91, 0),
    I2C((byte)0x92, 0),
    I2S((byte)0x93, 0),
    // Comparisons
    LCMP((byte)0x94, 0),
    FCMPL((byte)0x95, 0),
    FCMPG((byte)0x96, 0),
    DCMPL((byte)0x97, 0),
    DCMPG((byte)0x98, 0),
    IFEQ((byte)0x99, 2), // branchbyte1, branchbyte2
    IFNE((byte)0x9a, 2), // branchbyte1, branchbyte2
    IFLT((byte)0x9b, 2), // branchbyte1, branchbyte2
    IFGE((byte)0x9c, 2), // branchbyte1, branchbyte2
    IFGT((byte)0x9d, 2), // branchbyte1, branchbyte2
    IFLE((byte)0x9e, 2), // branchbyte1, branchbyte2
    IF_ICMPEQ((byte)0x9f, 2), // branchbyte1, branchbyte2
    IF_ICMPNE((byte)0xa0, 2), // branchbyte1, branchbyte2
    IF_ICMPLT((byte)0xa1, 2), // branchbyte1, branchbyte2
    IF_ICMPGE((byte)0xa2, 2), // branchbyte1, branchbyte2
    IF_ICMPGT((byte)0xa3, 2), // branchbyte1, branchbyte2
    IF_ICMPLE((byte)0xa4, 2), // branchbyte1, branchbyte2
    IF_ACMPEQ((byte)0xa5, 2), // branchbyte1, branchbyte2
    IF_ACMPNE((byte)0xa6, 2), // branchbyte1, branchbyte2
    // References
    GETSTATIC((byte)0xb2, 2), // indexbyte1, indexbyte2
    PUTSTATIC((byte)0xb3, 2), // indexbyte1, indexbyte2
    GETFIELD((byte)0xb4, 2), // indexbyte1, indexbyte2
    PUTFIELD((byte)0xb5, 2), // indexbyte1, indexbyte2
    INVOKEVIRTUAL((byte)0xb6, 2), // indexbyte1, indexbyte2
    INVOKESPECIAL((byte)0xb7, 2), // indexbyte1, indexbyte2
    INVOKESTATIC((byte)0xb8, 2), // indexbyte1, indexbyte2
    INVOKEINTERFACE((byte)0xb9, 4), // indexbyte1, indexbyte2, count, 0
    INVOKEDYNAMIC((byte)0xba, 4), // indexbyte1, indexbyte2, 0, 0
    NEW((byte)0xbb, 2), // indexbyte1, indexbyte2
    NEWARRAY((byte)0xbc, 1), // atype
    ANEWARRAY((byte)0xbd, 2), // indexbyte1, indexbyte2
    ARRAYLENGTH((byte)0xbe, 0),
    ATHROW((byte)0xbf, 0),
    CHECKCAST((byte)0xc0, 2), // indexbyte1, indexbyte2
    INSTANCEOF((byte)0xc1, 2), // indexbyte1, indexbyte2
    MONITORENTER((byte)0xc2, 0),
    MONITOREXIT((byte)0xc3, 0),
    // Control
    GOTO((byte)0xa7, 2), // branchbyte1, branchbyte2
    JSR((byte)0xa8, 2), // branchbyte1, branchbyte2
    RET((byte)0xa9, 1), // index
    TABLESWITCH((byte)0xaa, -1), // variable-length instruction
    LOOKUPSWITCH((byte)0xab, -1), // variable-length instruction
    IRETURN((byte)0xac, 0),
    LRETURN((byte)0xad, 0),
    FRETURN((byte)0xae, 0),
    DRETURN((byte)0xaf, 0),
    ARETURN((byte)0xb0, 0),
    RETURN((byte)0xb1, 0),
    // Extended
    WIDE((byte)0xc4, -1), // has 2 formats
    MULTIANEWARRAY((byte)0xc5, 3), // indexbyte1, indexbyte2, dimensions
    IFNULL((byte)0xc6, 2), // branchbyte1, branchbyte2
    IFNONNULL((byte)0xc7, 2), // branchbyte1, branchbyte2
    GOTO_W((byte)0xc8, 4), // branchbyte1, branchbyte2, branchbyte3, branchbyte4
    JSR_W((byte)0xc9, 4), // branchbyte1, branchbyte2, branchbyte3, branchbyte4
    // Reserved
    BREAKPOINT((byte)0xca, -2),
    IMPDEP1((byte)0xfe, -2),
    IMPDEP2((byte)0xff, -2);

    private byte op;
    private int argc;

    public static Opcode getOpcode(byte op) {
        for (Opcode opc : Opcode.values()) {
            if (opc.getOp() == op) {
                return opc;
            }
        }
        throw new RuntimeException(String.format("Opcode 0x%02x is not defined", op));
    }
}
