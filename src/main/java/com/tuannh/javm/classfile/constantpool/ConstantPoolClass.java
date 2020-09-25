package com.tuannh.javm.classfile.constantpool;

import java.nio.ByteBuffer;

import com.tuannh.javm.classfile.common.DebugPrintConstants;
import com.tuannh.javm.classfile.common.ResolvableWithRequiredObj;
import lombok.Getter;
import lombok.ToString;

//    CONSTANT_Class_info {
//        u1 tag;
//        u2 name_index;
//    }
//
//name_index
//The value of the name_index item must be a valid index into the constant_pool table.
// The constant_pool entry at that index must be a CONSTANT_Utf8_info structure (§4.4.7)
// representing a valid binary class or interface name encoded in internal form (§4.2.1).

@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolClass extends ConstantPoolInfo implements ResolvableWithRequiredObj<ConstantPoolInfo[]> {
    private short nameIndex;
    private String name;

    public ConstantPoolClass(byte[] bytes) {
        super(ConstantPoolTag.CLASS);
        this.nameIndex = ByteBuffer.wrap(bytes).getShort();
    }

    public ConstantPoolClass(short nameIndex) {
        super(ConstantPoolTag.CLASS);
        this.nameIndex = nameIndex;
    }

    @Override
    public void resolve(ConstantPoolInfo[] constantPool) {
        name = ((ConstantPoolUtf8)constantPool[nameIndex - 1]).getValue();
    }

    @Override
    public String debugPrint() {
        return String.format("%-25s#%d%15s %s", getTag(), nameIndex, DebugPrintConstants.SEPERATOR, name);
    }
}
