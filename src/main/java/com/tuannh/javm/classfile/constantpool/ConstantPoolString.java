package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.DebugPrintConstants;
import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

//    CONSTANT_String_info {
//        u1 tag;
//        u2 string_index;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolString extends ConstantPoolInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private int stringIndex;
    private String value;

    public ConstantPoolString(byte[] bytes) {
        super(ConstantPoolTag.STRING);
        this.stringIndex = ByteBufferUtils.getUnsignedShort(bytes);
    }

    public ConstantPoolString(short stringIndex) {
        super(ConstantPoolTag.STRING);
        this.stringIndex = Conversion.shortToInt(stringIndex);
    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        value = ((ConstantPoolUtf8)constantPool[stringIndex - 1]).getValue();
    }

    @Override
    public String debugPrint() {
        return String.format("%-25s#%d%15s %s", getTag(), stringIndex, DebugPrintConstants.SEPERATOR, value);
    }
}