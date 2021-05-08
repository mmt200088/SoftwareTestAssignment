package com.example.testingbackground.Controller;


import com.example.testingbackground.Entity.ComputerSale;
import com.example.testingbackground.Entity.Salesman;
import com.example.testingbackground.Service.QuestionThreeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wjq
 */
@RestController
@RequestMapping("/api")
public class QuestionThreeController {

    @Autowired
    private QuestionThreeService question3Service;

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
       "sales":""       //销售佣金
    }
     */

    @RequestMapping(value = "/manualtest/salesys", method = RequestMethod.POST)
    public Object getSales(@RequestBody JSONObject jsonObject){
        Map<String,Object> result=new HashMap<>();
        try{
            Integer host=jsonObject.getInt("host");
            //传入数据为空时会报错，建议在前端界面中进行控制
            Integer monitor=jsonObject.getInt("monitor");
            Integer io=jsonObject.getInt("io");

            Salesman salesman=new Salesman();
            ComputerSale computerSale=new ComputerSale();
            computerSale.setHost(host);
            computerSale.setMonitor(monitor);
            computerSale.setIO(io);
            salesman.setComputerSale(computerSale);

            String check=question3Service.ifValidate(salesman);
            if(!check.equals("销售数量符合要求")){
                result.put("status",0);
                result.put("message",check);
                result.put("sales",0);
                return result;
            }
            double sales=question3Service.calculate(salesman);
            result.put("status",200);
            result.put("message","成功计算出佣金");
            result.put("sales",sales);
            return result;
        }
        catch (Exception e){
            System.out.println(e);
            result.put("status",500);
            result.put("message","服务器端出错");
            result.put("sales",0);
            return result;
        }
    }
}
