package com.example.testingbackground.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.testingbackground.Entity.*;
import java.util.*;

/**
 * @author xjt
 */
@Service
public class QuestionOneService {

     static final double[] side1 = new double[]{6,3.3,7,11,0,-1,0.1};
     static final double[] side2 = new double[]{6,3.3,8,5,1,1,1};
     static final double[] side3 = new double[]{6,4.4,9,5,1,1,1};


    public String ifValidate(triangle mytri) {
        if (mytri.getSide2() <= 0 || mytri.getSide1() <= 0 || mytri.getSide3() <= 0) {
            System.out.println("dasda");
            return "非法输入！有小于或等于0的边长";

        }
        if (mytri.getSide3() + mytri.getSide1() <= mytri.getSide2() || mytri.getSide3() + mytri.getSide2() <= mytri.getSide1() || mytri.getSide1() + mytri.getSide2() <= mytri.getSide3()) {
            return "两边之和大于等于第三边，不构成三角形！";
        }
        if (mytri.getSide3() == mytri.getSide2() || mytri.getSide3() == mytri.getSide1() || mytri.getSide2() == mytri.getSide1()) {
            if (mytri.getSide3() == mytri.getSide1() && mytri.getSide2() == mytri.getSide3()) {
                return "等边三角形！";
            }
            return "等腰三角形！";
        }
        return "一般三角形！";

    }

    public List boundaryInput() {
        List <QuestionOneTest> res = new ArrayList<>();
        for(int i=4;i<7;i++){
            triangle tri = new triangle();
            tri.setSide1(side1[i]);
            tri.setSide2(side2[i]);
            tri.setSide3(side3[i]);

            QuestionOneTest one = new QuestionOneTest();
            one.setSide1(side1[i]);
            one.setSide2(side2[i]);
            one.setSide3(side3[i]);

            one.setExpectType(-1);
            if(i==6){
                one.setExpectType(2);
            }


            String check = ifValidate(tri);

            if("非法输入！有小于或等于0的边长".equals(check)){
                one.setActualType(-1);
            }

            else if("两边之和大于等于第三边，不构成三角形！".equals(check)){
                one.setActualType(0);
            }

            else if("一般三角形！".equals(check)){
                one.setActualType(1);
            }

            else if("等腰三角形！".equals(check)){
                one.setActualType(2);
            }
            else{
                one.setActualType(3);
            }
            if(one.getExpectType()== one.getActualType()){
                one.setIfCorrect(1);
            }
            res.add(one);
        }
        return res;

    }

    public List equivalentInput() {
        List <QuestionOneTest> res = new ArrayList<>();
        for(int i=0;i<4;i++){
            triangle tri = new triangle();
            tri.setSide1(side1[i]);
            tri.setSide2(side2[i]);
            tri.setSide3(side3[i]);

            QuestionOneTest one = new QuestionOneTest();
            one.setSide1(side1[i]);
            one.setSide2(side2[i]);
            one.setSide3(side3[i]);

            if(i==0){
                one.setExpectType(3);
            }
            if(i==1){
                one.setExpectType(2);
            }
            if(i==2){
                one.setExpectType(1);
            }
            if(i==3){
                one.setExpectType(0);
            }

            String check = ifValidate(tri);

            if("非法输入！有小于或等于0的边长".equals(check)){
                one.setActualType(-1);
            }

            else if("两边之和大于等于第三边，不构成三角形！".equals(check)){
                one.setActualType(0);
            }

            else if("一般三角形！".equals(check)){
                one.setActualType(1);
            }

            else if("等腰三角形！".equals(check)){
                one.setActualType(2);
            }
            else{
                one.setActualType(3);
            }
            if(one.getExpectType()== one.getActualType()){
                one.setIfCorrect(1);
            }
            res.add(one);
        }
        return res;
    }
}
