package com.example.testingbackground.Entity;

import lombok.Data;

/**
 * @author wjq
 */
//测试用例模板

@Data
public class QuestionThreeTest {
    private int host;
    private int monitor;
    private int IO;
    private int expectSales;
    private int actualSales;
    private double expectCommission;
    private double actualCommission;
    private int ifCorrect;
}
