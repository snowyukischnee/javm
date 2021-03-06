package com.tuannh.javm.classfile.constantpool;

import com.tuannh.javm.classfile.common.ImmediatelyResolvable;
import com.tuannh.javm.util.ByteBufferUtils;
import com.tuannh.javm.util.ByteUtils;
import com.tuannh.javm.util.Conversion;
import lombok.Getter;
import lombok.ToString;

import java.nio.charset.StandardCharsets;

//    CONSTANT_Utf8_info {
//        u1 tag;
//        u2 length;
//        u1 bytes[length];
//    }
@SuppressWarnings("java:S125")
@Getter
@ToString
public class ConstantPoolUtf8 extends ConstantPoolInfo implements ImmediatelyResolvable {
    private int length;
    private byte[] bytes;
    private String value;

    public ConstantPoolUtf8(byte[] bytes) {
        super(ConstantPoolTag.UTF8);
        this.length = ByteBufferUtils.getUnsignedShort(ByteUtils.slice(bytes, 0, 2));
        this.bytes = ByteUtils.slice(bytes, 2, bytes.length);
    }

    public ConstantPoolUtf8(short length, byte[] bytes) {
        super(ConstantPoolTag.UTF8);
        this.length = Conversion.shortToInt(length);
        this.bytes = bytes;
    }

    @Override
    public void resolve() {
        value = new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public String debugPrint(int padding) {
        return String.format("%-25s%s", getTag(), value);
    }
}
