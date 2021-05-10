package com.example.testingbackground.Controller;


import com.example.testingbackground.Entity.ComputerSale;
import com.example.testingbackground.Entity.QuestionThreeTest;
import com.example.testingbackground.Entity.Salesman;
import com.example.testingbackground.Service.QuestionThreeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wjq
 */
@RestController
@RequestMapping("/api")
public class QuestionThreeController {

    @Autowired
    private QuestionThreeService questionThreeService;

    /*请求体格式
    {
      "host":"0",       //主机数
      "monitor":"0",    //显示器数
      "io":"0"          //外设数
    }

    返回体格式
    {
       "status":"",     //状态值，0为输入值不合法，200为输入合法，500为服务器错误
       "message":"",    //提示信息
       "sales":""       //销售额
       "commission":""  //销售佣金
    }
     */

    @RequestMapping(value = "/questionThree/manualtest", method = RequestMethod.POST)
    public Object getSales(@RequestBody JSONObject jsonObject){
        Map<String,Object> result=new HashMap<>();
        try{
            //传入数据为空时会报错，建议在前端界面中进行控制
            Integer host=jsonObject.getInt("host");
            Integer monitor=jsonObject.getInt("monitor");
            Integer io=jsonObject.getInt("io");

            Salesman salesman=new Salesman();
            ComputerSale computerSale=new ComputerSale();
            computerSale.setHost(host);
            computerSale.setMonitor(monitor);
            computerSale.setIO(io);
            salesman.setComputerSale(computerSale);

            String check=questionThreeService.ifValidate(salesman);
            if(!"销售数量符合要求".equals(check)){
                result.put("status",0);
                result.put("message",check);
                result.put("sales",0);
                result.put("commission",0);
                return result;
            }
            int sales=questionThreeService.calculate(salesman);
            result.put("status",200);
            result.put("message","成功计算出佣金");
            result.put("sales",sales);
            result.put("commission",salesman.getCommission());
            return result;
        }
        catch (Exception e){
            System.out.println(e);
            result.put("status",500);
            result.put("message","服务器端出错");
            result.put("sales",0);
            result.put("commission",0);
            return result;
        }
    }

    /**
    *边界值输入测试
    */
    @RequestMapping(value = "/questionThree/boundaryInput", method = RequestMethod.POST)
    public Object boundaryInput(){
        Map<String,Object> result=new HashMap<>();
        try{
            List list=questionThreeService.boundaryInputTest();
            result.put("status",200);
            result.put("data",list);
        }
        catch (Exception e){
            result.put("status",500);
        }
        return result;
    }

    /**
     *边界值输出测试
     */
    @RequestMapping(value = "/questionThree/boundaryOutput", method = RequestMethod.POST)
    public Object boundaryOutput(){
        Map<String,Object> result=new HashMap<>();
        try{
            List list=questionThreeService.boundaryOutputTest();
            result.put("status",200);
            result.put("data",list);
        }
        catch (Exception e){
            result.put("status",500);
        }
        return result;
    }
}
