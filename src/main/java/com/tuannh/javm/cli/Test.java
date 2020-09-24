package com.tuannh.javm.cli;

import com.tuannh.javm.classfile.ClassFile;
import com.tuannh.javm.classfile.Parser;
import com.tuannh.javm.util.ByteUtils;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        final String classPath = "/home/tuannh/Desktop/javm/target0/classes/com/tuannh/javm/cli/Test.class";
        ClassFile classFile = Parser.parseClassFile(classPath);
        System.out.println(classFile);
    }
}
