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

    private static final int[] TEST_CASE_ID = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    private static final int[] CALL_TIME = new int[]{0,1,59,60,61,119,120,121,179,180,181,299,300,301,44639,44640,44640,44641,-1,400,400};
    private static final int[] UNPAID_NUM = new int[]{0,1,1,1,2,2,2,3,3,3,4,4,5,6,7,11,12,12,12,13,-1};
    private static final String[] EXPECTED_OUT_PUT = new String[]{"error","25.15","33.76","33.91","34.01","42.58","42.73","42.79","51.31","51.46","52.15","69.85","70.00","68.80","6720.85","6721","6721","error","error","error","error"};
    private static final String[] ACTUAL_OUT_PUT = new String[]{"error","25.15","33.76","33.91","34.01","42.58","42.73","42.79","51.31","51.46","52.15","69.85","70.00","68.80","6720.85","6721","6721","error","error","error","error"};
    private static final boolean[] CORRECTNESS = new boolean[]{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true};
    private static final String[] TESTER = new String[]{"anonymous", "anonymous", "anonymous", "anonymous","anonymous", "anonymous", "anonymous", "anonymous","anonymous", "anonymous", "anonymous", "anonymous","anonymous", "anonymous", "anonymous", "anonymous","anonymous", "anonymous", "anonymous", "anonymous","anonymous"};


    private static final int[] TEST_CASE_ID2 = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
    private static final int[] CALL_TIME2 = new int[]{60,120,180,300,400,400,-10,44650,60,60,120,180,300,400,-10,44650,-10,300};
    private static final int[] UNPAID_NUM2 = new int[]{1,2,3,5,6,7,1,1,-10,20,1,2,3,5,-10,20,12,-10};
    private static final String[] EXPECTED_OUT_PUT2 = new String[]{"33.91","42.73","51.46","68.88","83.2","85","error","error","error","error","42.73","51.46","68.88","83.2","error","error","error","error"};
    private static final String[] ACTUAL_OUT_PUT2 = new String[]{"error","25.15","33.76","33.91","34.01","42.58","42.73","42.79","51.31","51.46","52.15","69.85","70.00","68.80","6720.85","6721","6721","error","error","error","error"};
    private static final boolean[] CORRECTNESS2 = new boolean[]{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
    private static final String[] TESTER2 = new String[]{"anonymous", "anonymous", "anonymous", "anonymous","anonymous", "anonymous", "anonymous", "anonymous","anonymous", "anonymous", "anonymous", "anonymous","anonymous", "anonymous", "anonymous", "anonymous","anonymous", "anonymous"};

    public boolean validate (EleCharge charge){
        int callTime = charge.getCallTime();
        int unpaidNum = charge.getUnpaidNum();
        int monthLimit = 44640;
        return callTime >= 0 && callTime <= monthLimit && unpaidNum >= 0;
    }
    public double costCal (EleCharge charge){
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
        else{
            cost += callTime * minFee;
        }
        return cost;
    }
    public List<Q6Test> boundary(){
        int len = TEST_CASE_ID.length;
        return getList(len, TEST_CASE_ID, CALL_TIME, UNPAID_NUM, EXPECTED_OUT_PUT, ACTUAL_OUT_PUT, CORRECTNESS, TESTER);
    }
    public List<Q6Test> equivalence(){
        int len = TEST_CASE_ID.length;
        return getList(len, TEST_CASE_ID2, CALL_TIME2, UNPAID_NUM2, EXPECTED_OUT_PUT2, ACTUAL_OUT_PUT2, CORRECTNESS, TESTER2);
    }
    public List<Q6Test> decision(){
        int len = TEST_CASE_ID.length;
        return getList(len, TEST_CASE_ID, CALL_TIME, UNPAID_NUM, EXPECTED_OUT_PUT, ACTUAL_OUT_PUT, CORRECTNESS, TESTER);
    }
    private List<Q6Test> getList(int len, int[] testCaseId, int[]callTime, int[]unpaidNum, String[]expectedOutput, String[]actualOutput, boolean[] correctness, String[] tester){

        List<Q6Test> result = new ArrayList<>();
        for(int i = 0; i<len; i++){
            Q6Test tmp = new Q6Test();

            tmp.setTestCaseId(testCaseId[i]);
            tmp.setCallTime(callTime[i]);
            tmp.setUnpaidNum(unpaidNum[i]);
            tmp.setExpectedOutput(expectedOutput[i]);
            tmp.setActualOutput(actualOutput[i]);
            tmp.setCorrectness(correctness[i]);
            tmp.setTester(tester[i]);

            result.add(tmp);
        }
        return result;
    }
}
