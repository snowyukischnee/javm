package com.tuannh.javm.classfile;
// https://en.wikipedia.org/wiki/Java_class_file

//Java SE 14 = 58 (0x3A hex),
//Java SE 13 = 57 (0x39 hex),
//Java SE 12 = 56 (0x38 hex),
//Java SE 11 = 55 (0x37 hex),
//Java SE 10 = 54 (0x36 hex),[3]
//Java SE 9 = 53 (0x35 hex),[4]
//Java SE 8 = 52 (0x34 hex),
//Java SE 7 = 51 (0x33 hex),
//Java SE 6.0 = 50 (0x32 hex),
//Java SE 5.0 = 49 (0x31 hex),
//JDK 1.4 = 48 (0x30 hex),
//JDK 1.3 = 47 (0x2F hex),
//JDK 1.2 = 46 (0x2E hex),
//JDK 1.1 = 45 (0x2D hex).

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VersionNumber {
    JAVA_SE_14((short)0x3A),
    JAVA_SE_13((short)0x39),
    JAVA_SE_12((short)0x38),
    JAVA_SE_11((short)0x37),
    JAVA_SE_10((short)0x36),
    JAVA_SE_9((short)0x35),
    JAVA_SE_8((short)0x34),
    JAVA_SE_7((short)0x33),
    JAVA_SE_6_0((short)0x32),
    JAVA_SE_5_0((short)0x31),
    JDK_1_4((short)0x30),
    JDK_1_3((short)0x2F),
    JDK_1_2((short)0x2E),
    JDK_1_1((short)0x2D),
    UNSPECIFIED((short)0);

    private short value;

    @SuppressWarnings("java:S112")
    public static VersionNumber fromShort(short value) {
        for (VersionNumber version : VersionNumber.values()) {
            if (value == version.getValue()) {
                return version;
            }
        }
        throw new RuntimeException(String.format("Not recognized version number %d", value));
    }
}
