package com.example.testingbackground.Service;
import com.example.testingbackground.Entity.EleCharge;
import com.example.testingbackground.Entity.Q6Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raven
 */
@Service
public class Q6Service {
    public boolean validate (EleCharge charge){
        int callTime = charge.getCallTime();
        int unpaidNum = charge.getUnpaidNum();
        int monthLimit = 44640;
        return callTime >= 0 && callTime <= monthLimit && unpaidNum >= 0;
    }
    public double costCal(EleCharge charge){
        double basicFee = 25.0;
        double minFee = 0.15;
        double cost = basicFee;
        int[] minTable = new int[]{60, 120, 180, 300};
        int[] numTable = new int[]{1, 2, 3, 6};
        double[] discountTable = new double[]{0.01, 0.015, 0.02, 0.025, 0.03};
        int callTime = charge.getCallTime();
        int unpaidNum = charge.getUnpaidNum();
        if(callTime<=minTable[0]&&unpaidNum<=numTable[0]){
            cost += callTime * minFee * discountTable[0];
        }
        else if (callTime<=minTable[1]&&unpaidNum<=numTable[1]){
            cost += callTime * minFee * discountTable[1];
        }
        else if (callTime<=minTable[2]&&unpaidNum<=numTable[2]){
            cost += callTime * minFee * discountTable[2];
        }
        else if (callTime<=minTable[3]&&unpaidNum<=numTable[2]){
            cost += callTime * minFee * discountTable[3];
        }
        else if (callTime>minTable[3]&&unpaidNum<=numTable[3]){
            cost += callTime * minFee * discountTable[4];
        }
        return cost;
    }
    private Q6Test getList(){
        Q6Test result = new Q6Test();

        return result;
    }
}
