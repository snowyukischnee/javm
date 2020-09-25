package com.tuannh.javm.util;

public class Conversion {
    private Conversion() {}

    public static int shortToInt(short x) {
        return x & 0xffff;
    }
}
