package com.tuannh.javm.cli;

import com.tuannh.javm.classfile.ClassFile;
import com.tuannh.javm.classfile.Parser;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        final String classPath = "/home/tuannh/git/javm/target0/classes/com/tuannh/javm/cli/TestParser.class";
        ClassFile classFile = Parser.parseClassFile(classPath);
        System.out.println(classFile);
    }
}
