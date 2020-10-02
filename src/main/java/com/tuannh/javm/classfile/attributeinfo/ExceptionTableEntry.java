package com.tuannh.javm.classfile.attributeinfo;

import com.tuannh.javm.classfile.common.DebugPrint;
import com.tuannh.javm.classfile.constantpool.ConstantPoolClass;
import com.tuannh.javm.classfile.constantpool.ConstantPoolInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ExceptionTableEntry implements DebugPrint {
    private int startPc;
    private int endPc;
    private int handlePc;
    private int catchType;

    private ConstantPoolInfo catchTypeValue;

    public ExceptionTableEntry(int startPc, int endPc, int handlePc, int catchType, ConstantPoolInfo[] constantPool) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlePc = handlePc;
        this.catchType = catchType;
        catchTypeValue = constantPool[this.catchType - 1];
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("start: %d,\t\tend: %d,\t\thandle: %d,\t\tcatch_type: %d\t// %s", startPc, endPc, handlePc, catchType, ((ConstantPoolClass)catchTypeValue).getName());
    }
}
