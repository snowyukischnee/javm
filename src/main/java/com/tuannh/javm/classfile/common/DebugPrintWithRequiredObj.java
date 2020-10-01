package com.tuannh.javm.classfile.common;

public interface DebugPrintWithRequiredObj<T> {
    String debugPrint(int padding, T obj);
}
