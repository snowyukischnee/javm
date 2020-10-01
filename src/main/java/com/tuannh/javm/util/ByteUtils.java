package com.tuannh.javm.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteUtils {
    private ByteUtils() {}

    // get slice [start, end)
    public static byte[] slice(byte[] arr, int start, int end) {
        return Arrays.copyOfRange(arr, start, end);
    }

    public static byte[] concat(byte[]... bytesArr) {
        int len = 0;
        for (byte[] bytes : bytesArr) {
            if (bytes != null) {
                len += bytes.length;
            }
        }
        byte[] ret = new byte[len];
        int currentLen = 0;
        for (byte[] bytes : bytesArr) {
            if (bytes != null) {
                System.arraycopy(bytes, 0, ret, currentLen, bytes.length);
                currentLen += bytes.length;
            }
        }
        return ret;
    }

    public static byte[] readBytes(DataInputStream stream, int nBytes) throws IOException {
        byte[] ret = new byte[nBytes];
        for (int i = 0; i < nBytes; i++) {
            ret[i] = stream.readByte();
        }
        return ret;
    }

    public static int[] readUnsignedShorts(DataInputStream stream, int nBytes) throws IOException {
        int[] ret = new int[nBytes];
        for (int i = 0; i < nBytes; i++) {
            ret[i] = stream.readUnsignedShort();
        }
        return ret;
    }
}
