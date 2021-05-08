package com.example.testingbackground.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.testingbackground.Entity.*;
import java.util.*;

/**
 * @author xjt
 */
@Service
public class QuestionQneService {


//    public dataReturn getByActorInfo(String actor){
//
//        dataReturn result = new dataReturn();
//        long startTime = System.currentTimeMillis();
//        List<actorInfo> actorList = actorInfoRepository.findByActor(actor);
//        long endTime = System.currentTimeMillis();
//        result.duration = endTime - startTime;
//        result.data = actorList;
//        if(result.data != null){
//            result.code = actorList.size();
//            result.message = "已经返回所有的该人主演的电影信息！";
//        } else{
//            result.code = 0;
//            result.message = "不存在该人主演的电影！";
//        }
//        return result;
//    }
}
