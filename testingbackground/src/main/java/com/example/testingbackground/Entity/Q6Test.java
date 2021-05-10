package com.example.testingbackground.Entity;
import lombok.Data;

/**
 * @author Raven
 */
@Data
public class Q6Test {
    private int testCaseId;
    private int callTime;
    private int unpaidNum;
    private double expectedOutput;
    private double actualOutput;
    private boolean correctness;
    private String tester;
}
