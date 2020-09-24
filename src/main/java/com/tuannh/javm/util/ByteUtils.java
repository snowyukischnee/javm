package com.tuannh.javm.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteUtils {
    private ByteUtils() {}

    // get slice [start, end)
    public static byte[] slice(byte[] arr, int start, int end) {
        return Arrays.copyOfRange(arr, start, end + 1);
    }

    public static byte[] readBytes(DataInputStream stream, int nBytes) throws IOException {
        byte[] ret = new byte[nBytes];
        for (int i = 0; i < nBytes; i++) {
            ret[i] = stream.readByte();
        }
        return ret;
    }
}
