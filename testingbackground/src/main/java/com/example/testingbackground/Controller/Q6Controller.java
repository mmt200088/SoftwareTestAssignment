package com.example.testingbackground.Controller;

import com.example.testingbackground.Entity.EleCharge;
import com.example.testingbackground.Service.Q6Service;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Raven
 */
@RestController
@RequestMapping("/api")
public class Q6Controller {

    @Autowired
    private Q6Service q6Service;

    @RequestMapping(value = "/Q6/manual", method = RequestMethod.POST)
    public Object getCost(@RequestBody JSONObject jsonObject){
        Map<String, Object> result = new HashMap<>();
        try{
            int callTime=jsonObject.getInt("time");
            int unpaidNum=jsonObject.getInt("unpaid");

            EleCharge eleCharge = new EleCharge();
            eleCharge.setCallTime(callTime);
            eleCharge.setUnpaidNum(unpaidNum);

            boolean validate = q6Service.validate(eleCharge);
            if(!validate){
                result.put("status",0);
                result.put("message","illegal data");
                result.put("cost",null);
                return result;
            }
            double cost = q6Service.costCal(eleCharge);
            result.put("status",200);
            result.put("message","success");
            result.put("cost",cost);
            return result;
        }
        catch (Exception e){
            System.out.println(e);
            result.put("status",500);
            result.put("message","server error");
            result.put("cost",null);
            return result;
        }
    }

    @RequestMapping(value = "/Q6/boundary",method = RequestMethod.POST)
    public Object boundary(@RequestBody JSONObject jsonObject){
        Map<String,Object> result=new HashMap<>();

        return result;
    }
    @RequestMapping(value = "/Q6/equivalence",method = RequestMethod.POST)
    public Object equivalence(@RequestBody JSONObject jsonObject){
        Map<String,Object> result=new HashMap<>();

        return result;
    }
    @RequestMapping(value = "/Q6/decision",method = RequestMethod.POST)
    public Object desion(@RequestBody JSONObject jsonObject){
        Map<String,Object> result=new HashMap<>();

        return result;
    }
}
