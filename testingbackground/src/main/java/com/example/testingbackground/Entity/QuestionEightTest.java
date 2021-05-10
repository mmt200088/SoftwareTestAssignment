package com.example.testingbackground.Entity;

import lombok.Data;

/**
 * @author wjq
 */
@Data
public class QuestionEightTest {
    private int year;
    private int month;
    private int day;
    private String expectOutput;
    private String actualOutput;
    private int ifCorrect;
}
