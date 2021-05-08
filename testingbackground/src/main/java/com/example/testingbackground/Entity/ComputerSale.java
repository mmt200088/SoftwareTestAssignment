package com.example.testingbackground.Entity;

import lombok.Data;

/**
 * @author wjq
 */
//销售业绩实体类

@Data
public class ComputerSale {
    private Integer host;
    //售出主机数

    private Integer monitor;
    //售出显示器数

    private Integer IO;
    //售出外设数
}
