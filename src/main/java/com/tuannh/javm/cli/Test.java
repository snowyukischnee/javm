package com.tuannh.javm.cli;

import com.tuannh.javm.classfile.ClassFile;
import com.tuannh.javm.classfile.ClassfileParser;
import com.tuannh.javm.util.ByteUtils;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        final String classPath = "/home/tuannh/git/javm/target/classes/com/tuannh/javm/cli/TestParser.class";
        ClassFile classFile = ClassfileParser.parseClassFile(classPath);
        System.out.println(classFile.debugPrint(0));
    }
}
