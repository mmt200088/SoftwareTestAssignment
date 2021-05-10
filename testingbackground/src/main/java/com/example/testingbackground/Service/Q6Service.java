package com.example.testingbackground.Service;
import com.example.testingbackground.Entity.EleCharge;
import org.springframework.stereotype.Service;

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
            cost = callTime * minFee * discountTable[0];
        }
        return cost;
    }
}
