package com.example.testingbackground.Service;
import com.example.testingbackground.Entity.Salesman;
import org.springframework.stereotype.Service;


@Service
public class QuestionThreeService {

    //判断输入数据合法性
    public String ifValidate(Salesman salesman){
        try{
            int host=salesman.getComputerSale().getHost();
            int monitor=salesman.getComputerSale().getMonitor();
            int io=salesman.getComputerSale().getIO();
            if(host<0||monitor<0||io<0){                       //销售数量为负
                return "输入数据不能为负";
            }
            else if(host==0||monitor==0||io==0){               //销售数量未凑够一台电脑
                return "销售业绩不合格";
            }
            else if(host>70||monitor>80||io>90){               //销售数量超出上限
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

    //计算佣金
    public double calculate(Salesman salesman){
        try{
            int host=salesman.getComputerSale().getHost();
            int monitor=salesman.getComputerSale().getMonitor();
            int io=salesman.getComputerSale().getIO();
            int sales=host*25+monitor*30+io*45;
            if(sales<=1000){
                return sales*0.1;
            }
            else if(sales<=1800){
                return sales*0.15;
            }
            else {
                return sales*0.20;
            }
        }
        catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}
