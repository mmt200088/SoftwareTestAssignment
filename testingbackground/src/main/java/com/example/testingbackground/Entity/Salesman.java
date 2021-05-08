package com.example.testingbackground.Entity;


import lombok.Data;

/**
 * @author wjq
 */
//销售人员实体类

@Data
public class Salesman {
    private ComputerSale computerSale;
    //每月业绩

    private Double commission;
    //每月佣金
}
