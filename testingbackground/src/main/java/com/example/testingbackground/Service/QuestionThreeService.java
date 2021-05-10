package com.example.testingbackground.Service;
import com.example.testingbackground.Entity.ComputerSale;
import com.example.testingbackground.Entity.QuestionThreeTest;
import com.example.testingbackground.Entity.Salesman;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wjq
 */
@Service
public class QuestionThreeService {

    private static final int[] HOSTS_INPUT =new int[]{0,1,38,67,70,-1,72};
    private static final int[] MONITORS_INPUT =new int[]{0,1,45,78,80,-1,81};
    private static final int[] IOS_INPUT =new int[]{0,1,43,89,90,-1,92};
    private static final int[] EXPECTSALES_INPUT =new int[]{0, 100, 4235, 8020, 8200, 0, 0};
    private static final double[] EXPECTCOMMISSION_INPUT =new double[]{0.0, 10.0, 847.0, 1604.0, 1640.0, 0.0, 0.0};

    private static final int[] HOSTS_OUTPUT =new int[]{0,1,5,10,13,18,50,70,-1,72};
    private static final int[] MONITORS_OUTPUT =new int[]{0,1,6,10,14,18,50,80,-1,81};
    private static final int[] IOS_OUTPUT =new int[]{0,1,5,10,14,18,51,90,-1,92};
    private static final int[] EXPECTSALES_OUTPUT =new int[]{0, 100, 530, 1000, 1375, 1800, 5045, 8200, 0, 0};
    private static final double[] EXPECTCOMMISSION_OUTPUT =new double[]{0.0, 10.0, 53.0, 100.0, 206.25, 270.0, 1009.0, 1640.0, 0.0, 0.0};
    /**
     * 判断输入数据合法性
     */
    public String ifValidate(Salesman salesman){
        try{
            int host=salesman.getComputerSale().getHost();
            int monitor=salesman.getComputerSale().getMonitor();
            int io=salesman.getComputerSale().getIO();
            //销售数量为负
            if(host<0||monitor<0||io<0){
                return "输入数据不能为负";
            }
            //销售数量未凑够一台电脑
            else if(host==0||monitor==0||io==0){
                return "销售业绩不合格";
            }
            //销售数量超出上限
            else if(host>70||monitor>80||io>90){
                return "销售数量超出上限";
            }
            else{
                return "销售数量符合要求";
            }
        }
        catch (Exception e){
            System.out.println(e);
            return "服务器出错";
        }
    }

    /**
     * 计算销售额和佣金
     */
    public int calculate(Salesman salesman){
        try{
            int host=salesman.getComputerSale().getHost();
            int monitor=salesman.getComputerSale().getMonitor();
            int io=salesman.getComputerSale().getIO();
            int sales=host*25+monitor*30+io*45;
            if(sales<=1000){
                salesman.setCommission(sales*0.10);
            }
            else if(sales<=1800){
                salesman.setCommission(sales*0.15);
            }
            else {
                salesman.setCommission(sales*0.20);
            }
            return sales;
        }
        catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    /**
     * 根据输入划分边界值
     */
    public List boundaryInputTest(){
        List<QuestionThreeTest> result=new ArrayList<>();
        int len=HOSTS_INPUT.length;
        return getList(result, len, HOSTS_INPUT, MONITORS_INPUT, IOS_INPUT, EXPECTSALES_INPUT, EXPECTCOMMISSION_INPUT);
    }
    /**
     * 根据输出划分边界值
     */
    public List boundaryOutputTest(){
        List<QuestionThreeTest> result=new ArrayList<>();
        int len=HOSTS_OUTPUT.length;
        return getList(result, len, HOSTS_OUTPUT, MONITORS_OUTPUT, IOS_OUTPUT, EXPECTSALES_OUTPUT, EXPECTCOMMISSION_OUTPUT);
    }

    private List getList(List<QuestionThreeTest> result, int len, int[] hostsInput, int[] monitorsInput, int[] iosInput, int[] expectsalesInput, double[] expectcommissionInput) {
        int i;
        for(i=0; i<len; i++){
            QuestionThreeTest temp=new QuestionThreeTest();
            Salesman salesman=new Salesman();
            ComputerSale computerSale = new ComputerSale();

            temp.setHost(hostsInput[i]);
            temp.setMonitor(monitorsInput[i]);
            temp.setIO(iosInput[i]);
            temp.setExpectSales(expectsalesInput[i]);
            temp.setExpectCommission(expectcommissionInput[i]);

            computerSale.setHost(hostsInput[i]);
            computerSale.setMonitor(monitorsInput[i]);
            computerSale.setIO(iosInput[i]);

            salesman.setComputerSale(computerSale);

            String check=ifValidate(salesman);
            if(!"销售数量符合要求".equals(check)){
                temp.setActualSales(0);
                temp.setActualCommission(0);
            }
            else{
                int sales=calculate(salesman);
                temp.setActualSales(sales);
                temp.setActualCommission(salesman.getCommission());
            }

            if(temp.getActualSales()==temp.getExpectSales() && temp.getExpectCommission()==temp.getActualCommission()){
                temp.setIfCorrect(1);
            }
            else{
                temp.setIfCorrect(0);
            }
            result.add(temp);
        }
        return result;
    }

}
