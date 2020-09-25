package com.tuannh.javm.cli;

class TestInheritance {
    private static final String SPR = "AA";
}

interface TestInterface {
    void gg();
}

public class TestParser extends TestInheritance implements TestInterface {
    public static final String TESTSTR = "TESTDDD";
    public static final long MAGICTEST = 1452352345342345624L;
    public static final double FFF = 123456.789;

    public static void main(String[] args) {
        System.out.println(TESTSTR);
        System.out.println(MAGICTEST);
        System.out.println(FFF);
    }

    @Override
    public void gg() {
        System.out.println("GGG");
    }
}
