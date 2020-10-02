package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.DebugPrintConstants;
import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

//    CONSTANT_MethodHandle_info {
//        u1 tag;
//        u1 reference_kind;
//        u2 reference_index;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolMethodHandle extends ConstantPoolInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private byte referenceKind;
    private int referenceIndex;

    private MethodHandleReferenceKind referenceKindValue;
    private ConstantPoolInfo reference;

    public ConstantPoolMethodHandle(byte[] bytes) {
        super(ConstantPoolTag.METHOD_HANDLE);
        this.referenceKind = ByteBuffer.wrap(ByteUtils.slice(bytes, 0, 1)).get();
        this.referenceIndex = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 1, 3));
        referenceKindValue = MethodHandleReferenceKind.fromByte(referenceKind);
    }

    public ConstantPoolMethodHandle(byte referenceKind, short referenceIndex) {
        super(ConstantPoolTag.METHOD_HANDLE);
        this.referenceKind = referenceKind;
        this.referenceIndex = Conversion.shortToInt(referenceIndex);
        referenceKindValue = MethodHandleReferenceKind.fromByte(referenceKind);
    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        reference = constantPool[referenceIndex - 1];
    }

    @SuppressWarnings("java:S112")
    @Override
    public String debugPrint(int padding) {
        final String FMT = "%-25s%d.#%d%15s %s %s.%s:%s";
        switch (referenceKindValue) {
            case REF_GETFIELD:
            case REF_GETSTATIC:
            case REF_PUTFIELD:
            case REF_PUTSTATIC:
                return String.format(
                        FMT,
                        getTag(),
                        referenceKind,
                        referenceIndex,
                        DebugPrintConstants.SEPERATOR,
                        reference.getTag(),
                        ((ConstantPoolFieldRef)reference).getClass().getName(),
                        ((ConstantPoolFieldRef)reference).getNameAndType().getName(),
                        ((ConstantPoolFieldRef)reference).getNameAndType().getDescriptor()
                );
            case REF_INVOKEVIRTUAL:
            case REF_NEWINVOKESPECIAL:
                return String.format(
                        FMT,
                        getTag(),
                        referenceKind,
                        referenceIndex,
                        DebugPrintConstants.SEPERATOR,
                        reference.getTag(),
                        ((ConstantPoolMethodRef)reference).getClass().getName(),
                        ((ConstantPoolMethodRef)reference).getNameAndType().getName(),
                        ((ConstantPoolMethodRef)reference).getNameAndType().getDescriptor()
                );
            case REF_INVOKESTATIC:
            case REF_INVOKESPECIAL:
                if (reference instanceof ConstantPoolMethodRef) {
                    return String.format(
                            FMT,
                            getTag(),
                            referenceKind,
                            referenceIndex,
                            DebugPrintConstants.SEPERATOR,
                            reference.getTag(),
                            ((ConstantPoolMethodRef)reference).getClass().getName(),
                            ((ConstantPoolMethodRef)reference).getNameAndType().getName(),
                            ((ConstantPoolMethodRef)reference).getNameAndType().getDescriptor()
                    );
                } else if (reference instanceof ConstantPoolInterfaceMethodRef) {
                    return String.format(
                            FMT,
                            getTag(),
                            referenceKind,
                            referenceIndex,
                            DebugPrintConstants.SEPERATOR,
                            reference.getTag(),
                            ((ConstantPoolInterfaceMethodRef)reference).getClass().getName(),
                            ((ConstantPoolInterfaceMethodRef)reference).getNameAndType().getName(),
                            ((ConstantPoolInterfaceMethodRef)reference).getNameAndType().getDescriptor()
                    );
                }
                break;
            case REF_INVOKEINTERFACE:
                return String.format(
                        FMT,
                        getTag(),
                        referenceKind,
                        referenceIndex,
                        DebugPrintConstants.SEPERATOR,
                        reference.getTag(),
                        ((ConstantPoolInterfaceMethodRef)reference).getClass().getName(),
                        ((ConstantPoolInterfaceMethodRef)reference).getNameAndType().getName(),
                        ((ConstantPoolInterfaceMethodRef)reference).getNameAndType().getDescriptor()
                );
            default:
                throw new RuntimeException("Unrecognized ReferenceKind");
        }
        throw new RuntimeException("Unexpected error");
    }
}
