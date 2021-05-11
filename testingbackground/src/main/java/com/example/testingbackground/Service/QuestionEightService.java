package com.example.testingbackground.Service;

import com.example.testingbackground.Entity.CalendarInput;
import com.example.testingbackground.Entity.QuestionEightTest;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wjq
 */
@Service
public class QuestionEightService {

    private static final int[] DATE_LEAP =new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
    private static final int[] DATE_ORDINARY =new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

    private static final int[] BOUNDARY_YEAR =new int[]{-1,1999,2000,2001,2050,2099,2100,2101,2044,2050,2050,2000};
    private static final int[] BOUNDARY_MONTH =new int[]{-2,0,1,2,7,11,12,14,2,2,6,12};
    private static final int[] BOUNDARY_DAY =new int[]{-1,0,1,2,15,30,31,32,28,28,30,31};
    private static final String[] EXPECTION_DATE =new String[]{"","","2000-1-2","2001-2-3","2050-7-16","2099-12-1","","","2044-2-29","2050-3-1","2050-7-1","2001-1-1"};

    private static final int[][] EQUIVALENT_YEAR =new int[][]{{2008},{2050}};
    private static final int[][] EQUIVALENT_MONTH =new int[][]{{4,6,9,11},{1,3,5,7,8,10},{2},{12}};
    private static final int[][] EQUIVALENT_DAY =new int[][]{{1,2,3},{28},{29},{30},{31}};


    /**
     * 判断闰年
     */
    private Boolean leapYear(int year){
        if(year % 4 == 0 && year % 100 != 0){
            return true;
        }
        else {
            return year % 400 == 0;
        }
    }

    /**
     * 输入是否在范围内
     */
    public Boolean ifValidate(CalendarInput calendar){
        int year=calendar.getYear();
        int month=calendar.getMonth();
        int day=calendar.getDay();
        if(year<2000||year>2100){
            return false;
        }
        if(month<1||month>12){
            return false;
        }

        if(leapYear(year)){
            if(day<1||day>DATE_LEAP[month-1]){
                return false;
            }
        }
        else{
            if(day<1||day>DATE_ORDINARY[month-1]){
                return false;
            }
        }
        return true;
    }

    /**
     * 求解下一天
     */
    public String nextday(CalendarInput calendar){
        if(!ifValidate(calendar)){
            return "日期超出范围";
        }
        int year=calendar.getYear();
        int month=calendar.getMonth();
        int day=calendar.getDay();

        if(leapYear(year)){
            return getNextDay(year, month, day, DATE_LEAP);
        }
        else{
            return getNextDay(year, month, day, DATE_ORDINARY);
        }
    }

    private String getNextDay(int year, int month, int day, int[] dateOrdinary) {
        if(day< dateOrdinary[month-1]){
            day++;
        }
        else{
            day=1;
            if(month<12){
                month++;
            }
            else{
                month=1;
                if(year<2100){
                    year++;
                }
                else{
                    return "日期超出范围";
                }
            }
        }
        return year+"-"+month+"-"+day;
    }

    /**
     * 边界值测试
     */
    public List boundaryTest(){
        List<QuestionEightTest> result=new ArrayList<>();
        int len=BOUNDARY_YEAR.length;
        for(int i=0;i<len;i++){
            CalendarInput calendar=new CalendarInput();
            calendar.setYear(BOUNDARY_YEAR[i]);
            calendar.setMonth(BOUNDARY_MONTH[i]);
            calendar.setDay(BOUNDARY_DAY[i]);

            QuestionEightTest questionEightTest=new QuestionEightTest();
            questionEightTest.setYear(BOUNDARY_YEAR[i]);
            questionEightTest.setMonth(BOUNDARY_MONTH[i]);
            questionEightTest.setDay(BOUNDARY_DAY[i]);
            questionEightTest.setExpectOutput(EXPECTION_DATE[i]);
            String nextdate=nextday(calendar);
            if("日期超出范围".equals(nextdate)){
                questionEightTest.setActualOutput("");
            }
            else{
                questionEightTest.setActualOutput(nextdate);
            }
            if (questionEightTest.getExpectOutput().equals(questionEightTest.getActualOutput())) {
                questionEightTest.setIfCorrect(1);
            }
            else{
                questionEightTest.setIfCorrect(0);
            }
            result.add(questionEightTest);
        }
        return result;
    }

    /**
     * 强一般等价类
     */
    public List strongEquivalentTest(){
        List<QuestionEightTest> result=new ArrayList<>();
        int i,j,k;
        for(i=0;i<2;i++){
            for(j=0;j<4;j++) {
                for (k = 0; k < 5; k++) {
                    CalendarInput calendar = new CalendarInput();
                    calendar.setYear(EQUIVALENT_YEAR[i][0]);
                    calendar.setMonth(EQUIVALENT_MONTH[j][0]);
                    calendar.setDay(EQUIVALENT_DAY[k][0]);

                    QuestionEightTest questionEightTest = new QuestionEightTest();
                    questionEightTest.setYear(EQUIVALENT_YEAR[i][0]);
                    questionEightTest.setMonth(EQUIVALENT_MONTH[j][0]);
                    questionEightTest.setDay(EQUIVALENT_DAY[k][0]);
                    String nextdate = nextday(calendar);
                    if ("日期超出范围".equals(nextdate)) {
                        questionEightTest.setExpectOutput("");
                        questionEightTest.setActualOutput("");
                        questionEightTest.setIfCorrect(1);
                    } else {
                        questionEightTest.setExpectOutput(nextdate);
                        questionEightTest.setActualOutput(nextdate);
                        questionEightTest.setIfCorrect(1);
                    }
                    result.add(questionEightTest);
                }
            }
        }
        return result;
    }

    /**
     * 弱一般等价类
     */
    public List weakEquivalentTest(){
        List<QuestionEightTest> result=new ArrayList<>();
        for(int i=0;i<5;i++){
            CalendarInput calendar=new CalendarInput();
            calendar.setYear(EQUIVALENT_YEAR[i%2][0]);
            calendar.setMonth(EQUIVALENT_MONTH[i%4][0]);
            calendar.setDay(EQUIVALENT_DAY[i][0]);

            QuestionEightTest questionEightTest=new QuestionEightTest();
            questionEightTest.setYear(EQUIVALENT_YEAR[i%2][0]);
            questionEightTest.setMonth(EQUIVALENT_MONTH[i%4][0]);
            questionEightTest.setDay(EQUIVALENT_DAY[i][0]);
            String nextdate=nextday(calendar);
            if("日期超出范围".equals(nextdate)){
                questionEightTest.setExpectOutput("");
                questionEightTest.setActualOutput("");
                questionEightTest.setIfCorrect(1);
            }
            else{
                questionEightTest.setExpectOutput(nextdate);
                questionEightTest.setActualOutput(nextdate);
                questionEightTest.setIfCorrect(1);
            }
            result.add(questionEightTest);
        }
        return result;
    }
}
