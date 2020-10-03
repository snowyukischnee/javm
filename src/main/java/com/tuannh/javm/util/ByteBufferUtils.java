package com.tuannh.javm.util;

import java.nio.ByteBuffer;

public class ByteBufferUtils {
    private ByteBufferUtils() {}

    public static int getUnsignedShort(byte[] bytes) {
        // length must be 2
        return ByteBuffer.wrap(bytes).getShort() & 0xffff;
    }
}
