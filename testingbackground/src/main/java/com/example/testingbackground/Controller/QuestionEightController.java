package com.example.testingbackground.Controller;

import com.example.testingbackground.Entity.CalendarInput;
import com.example.testingbackground.Service.QuestionEightService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wjq
 */
@RestController
@RequestMapping("/api")
public class QuestionEightController {
    @Autowired
    private QuestionEightService questionEightService;

    @RequestMapping(value = "/questionEight/manualtest", method = RequestMethod.POST)
    public Object getNextDay(@RequestBody JSONObject jsonObject){
        Map<String,Object> result=new HashMap<>();
        try{
            int year=jsonObject.getInt("year");
            int month=jsonObject.getInt("month");
            int day=jsonObject.getInt("day");
            CalendarInput calendar=new CalendarInput();
            calendar.setYear(year);
            calendar.setMonth(month);
            calendar.setDay(day);
            String nextday=questionEightService.nextday(calendar);
            result.put("status",200);
            result.put("nextday",nextday);
            return result;
        }
        catch (Exception e){
            result.put("status",500);
            return result;
        }
    }

    @RequestMapping(value = "/questionEight/boundaryTest", method = RequestMethod.POST)
    public Object boundaryTest(){
        Map<String,Object> result=new HashMap<>();
        try{
            List list=questionEightService.boundaryTest();
            result.put("status",200);
            result.put("data",list);
        }
        catch (Exception e){
            result.put("status",500);
        }
        return result;
    }

    @RequestMapping(value = "/questionEight/weakEquivalentTest", method = RequestMethod.POST)
    public Object weakEquivalentTest(){
        Map<String,Object> result=new HashMap<>();
        try{
            List list=questionEightService.weakEquivalentTest();
            result.put("status",200);
            result.put("data",list);
        }
        catch (Exception e){
            result.put("status",500);
        }
        return result;
    }

    @RequestMapping(value = "/questionEight/strongEquivalentTest", method = RequestMethod.POST)
    public Object strongEquivalentTest(){
        Map<String,Object> result=new HashMap<>();
        try{
            List list=questionEightService.strongEquivalentTest();
            result.put("status",200);
            result.put("data",list);
        }
        catch (Exception e){
            result.put("status",500);
        }
        return result;
    }
}
