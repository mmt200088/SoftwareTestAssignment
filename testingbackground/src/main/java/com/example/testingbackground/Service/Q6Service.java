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

    private static final int[] TEST_CASE_ID = new int[]{1,2,3,4};
    private static final int[] CALL_TIME = new int[]{1,2,3,4};
    private static final int[] UNPAID_NUM = new int[]{1,2,3,4};
    private static final String[] EXPECTED_OUT_PUT = new String[]{"1","2","3","4"};
    private static final String[] ACTUAL_OUT_PUT = new String[]{"1","2","3","4"};
    private static final boolean[] CORRECTNESS = new boolean[]{true, true, true, true};
    private static final String[] TESTER = new String[]{"anonymous", "anonymous", "anonymous", "anonymous"};


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
        return getList(len, TEST_CASE_ID, CALL_TIME, UNPAID_NUM, EXPECTED_OUT_PUT, ACTUAL_OUT_PUT, CORRECTNESS, TESTER);
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
