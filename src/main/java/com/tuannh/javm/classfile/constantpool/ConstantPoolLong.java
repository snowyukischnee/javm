package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.ImmediatelyResolvable;
import com.tuannh.javm.util.ByteUtils;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;

import static com.tuannh.javm.classfile.common.DebugPrintConstants.PADDING;

//    CONSTANT_Long_info {
//        u1 tag;
//        u4 high_bytes;
//        u4 low_bytes;
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolLong extends ConstantPoolInfo implements ImmediatelyResolvable {
    private byte[] highBytes;
    private byte[] lowBytes;
    private long value;

    public ConstantPoolLong(byte[] bytes) {
        super(ConstantPoolTag.LONG);
        this.highBytes = ByteUtils.slice(bytes, 0, 4);
        this.lowBytes = ByteUtils.slice(bytes, 4, 8);
    }

    public ConstantPoolLong(byte[] highBytes, byte[] lowBytes) {
        super(ConstantPoolTag.LONG);
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }

    @Override
    public void resolve() {
        value = ByteBuffer.wrap(ByteUtils.concat(highBytes, lowBytes)).getLong();
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("%-25s%dl", getTag(), value);
    }
}