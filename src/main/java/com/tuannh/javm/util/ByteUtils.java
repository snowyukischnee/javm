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

    public static byte[] concat(byte[] left, byte[] right) {
        byte[] ret = new byte[left.length + right.length];
        System.arraycopy(left, 0, ret, 0, left.length);
        System.arraycopy(right, 0, ret, left.length, right.length);
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
