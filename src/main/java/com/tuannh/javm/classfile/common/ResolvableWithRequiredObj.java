package com.tuannh.javm.classfile.common;

public interface ResolvableWithRequiredObj<T> {
    void resolve(T obj);
}
