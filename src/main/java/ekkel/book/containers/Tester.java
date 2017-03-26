package ekkel.book.containers;

import java.util.List;

/**
 * Created by cresh on 19.03.17.
 */
class Tester<C> {
    private static final int defFieldWidth = 8;
    private static final int defSizeWidth = 5;
    private static final TestParam[] defaultParams =
            TestParam.getTestParams(10, 1, 100, 5000, 1000, 5000, 10_000, 500);

    static <C> void run(C container, List<Test<C>> tests) {
        new Tester<>(container, tests).timedTest();
    }

    static <C> void run(C container, List<Test<C>> tests, TestParam[] testParams) {
        new Tester<>(container, tests, testParams).timedTest();
    }

    C container;
    private int fieldWidth = defFieldWidth;
    private int sizeWidth = defSizeWidth;
    private String headline = "";
    private List<Test<C>> tests;
    private TestParam[] testParams = defaultParams;

    Tester(C container, List<Test<C>> tests) {
        this.container = container;
        this.tests = tests;
    }

    Tester(C container, List<Test<C>> tests, TestParam[] testParams) {
        this(container, tests);
        this.testParams = testParams;
    }

    C initialize(int size) {
        return container;
    }

    void setFieldWidth(int fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    void setSizeWidth(int sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setTestParams(TestParam[] testParams) {
        this.testParams = testParams;
    }

    private String getStringField() {
        return "%" + fieldWidth + "s";
    }

    private String getNumberField() {
        return "%" + fieldWidth + "d";
    }

    private String getSizeField() {
        return "%" + sizeWidth + "s";
    }


    private void displayHeader() {
        int width = fieldWidth * tests.size() + sizeWidth;
        int dashLength = width - headline.length() - 1;
        StringBuilder head = new StringBuilder(width);

        for (int i = 0; i < dashLength / 2; i++) {
            head.append('-');
        }

        head.append(' ');
        head.append(headline);
        head.append(' ');

        for (int i = 0; i < dashLength / 2; i++) {
            head.append('-');
        }

        System.out.println(head);
        System.out.printf(getSizeField(), "size");
        for (Test<C> test : tests) {
            System.out.printf(getStringField(), test.getName());
        }

        System.out.println();
    }

    void timedTest() {
        displayHeader();
        for (TestParam testParam : testParams) {
            System.out.printf(getSizeField(), testParam.getSize());
            for (Test<C> test : tests) {
                C container = initialize(testParam.getSize());
                long start = System.nanoTime();
                int replies = test.test(container, testParam);
                long duration = System.nanoTime() - start;
                long timePerCircle = duration / replies;
                System.out.printf(getNumberField(), timePerCircle);
            }
            System.out.println();
        }
    }
}
