package com.example.testingbackground.Controller;


import com.example.testingbackground.Entity.ComputerSale;
import com.example.testingbackground.Entity.Salesman;
import com.example.testingbackground.Entity.triangle;
import com.example.testingbackground.Service.QuestionOneService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xjt
 */
@RestController
@RequestMapping(value = "api/")
public class QuestionOneController {

    @Autowired
    private QuestionOneService questionOneService;
    @RequestMapping(value = "/questionOne/manualtest", method = RequestMethod.POST)
    public Object ifValidate(@RequestBody JSONObject jsonObject){
        Map<String,Object> result=new HashMap<>();
        try {
            //传入数据为空时会报错，建议在前端界面中进行控制
            Double side1 = jsonObject.getDouble("side1");
            Double side2 = jsonObject.getDouble("side2");
            Double side3 = jsonObject.getDouble("side3");

            triangle tri = new triangle();
            tri.setSide1(side1);
            tri.setSide2(side2);
            tri.setSide3(side3);


            String check = questionOneService.ifValidate(tri);
            if ("非法输入！有小于或等于0的边长".equals(check)) {
                result.put("status", 0);
                result.put("message", check);
                result.put("type", -1);
            }

            else if ("两边之和大于等于第三边，不构成三角形！".equals(check)) {
                result.put("status", 200);
                result.put("message", check);
                result.put("type", 0);
            }

            else if ("一般三角形！".equals(check)) {
                result.put("status", 200);
                result.put("message", check);
                result.put("type", 1);
            }

            else if ("等腰三角形！".equals(check)) {
                result.put("status", 200);
                result.put("message", check);
                result.put("type", 2);
            } else {
                result.put("status", 200);
                result.put("message", check);
                result.put("type", 3);
            }
        } catch (Exception e){
                System.out.println(e);
                result.put("status",500);
                result.put("message","服务器端出错");
                result.put("sales",0);
                result.put("commission",0);
            }
            return result;
    }

    /**
     *边界值输入测试
     */
    @RequestMapping(value = "/questionOne/boundary", method = RequestMethod.POST)
    public Object boundaryInput(){
        Map<String,Object> result=new HashMap<>();
        try{
            List list=questionOneService.boundaryInput();
            result.put("status",200);
            result.put("data",list);
        }
        catch (Exception e){
            result.put("status",500);
        }
        return result;
    }

    /**
     *等价类输入测试
     */
    @RequestMapping(value = "/questionOne/equivalent", method = RequestMethod.POST)
    public Object equivalentInput(){
        Map<String,Object> result=new HashMap<>();
        try{
            List list=questionOneService.equivalentInput();
            result.put("status",200);
            result.put("data",list);
        }
        catch (Exception e){
            result.put("status",500);
        }
        return result;
    }
}
