package com.example.testingbackground.Entity;

import lombok.Data;

/**
 * @author xjt
 */
//测试用例模板

@Data
public class QuestionOneTest {
    private double side1;
    private double side2;
    private double side3;
    private int expectType;
    private int actualType;
    private int ifCorrect;

}
