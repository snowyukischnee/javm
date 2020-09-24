package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConstantPoolDouble extends ConstantPoolInfo {
//    CONSTANT_Double_info {
//        u1 tag;
//        u4 high_bytes;
//        u4 low_bytes;
//    }
    private byte[] highBytes;
    private byte[] lowBytes;

    public ConstantPoolDouble(byte[] bytes) {
        super(ConstantPoolTag.DOUBLE);
        this.highBytes = ByteUtils.slice(bytes, 0, 4);
        this.lowBytes = ByteUtils.slice(bytes, 4, 8);
    }

    public ConstantPoolDouble(byte[] highBytes, byte[] lowBytes) {
        super(ConstantPoolTag.DOUBLE);
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }
}