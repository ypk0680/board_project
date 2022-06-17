/*
 * package com.twbh.weather.controller.action;
 * 
 * import java.text.SimpleDateFormat; import java.util.Date;
 * 
 * public class timeTrans { public String TransTime(){ Date date = new Date();
 * SimpleDateFormat format2 = new SimpleDateFormat("kkmm");
 * 
 * String time = format2.format(date); //시간 형식에 맞게 (문자형) int time_int =
 * Integer.parseInt(time); //(비교를 위해 숫자형으로 변환) 0200 ->200 String result; //최종 결과
 * 다시 문자형
 * 
 * System.out.println(time_int); //1400
 * 
 * 
 * //0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회) if(time_int>=200 &&
 * time_int<500){ //숫자로 비교하고 결과는 다시 string으로 result="0200";
 * System.out.println("변환완료:"+result); }else if(time_int>=500 && time_int<800 ){
 * result="0500"; System.out.println("변환완료:"+result); }else if(time_int>=800 &&
 * time_int<1100 ){ result="0800"; System.out.println("변환완료:"+result); }else
 * if(time_int>=1100 && time_int<1400 ){ result="1100";
 * System.out.println("변환완료:"+result); }else if(time_int>=1400 && time_int<1700
 * ){ result="1400"; System.out.println("변환완료:"+result); }else if(time_int>=1700
 * && time_int<2000 ){ result="1700"; System.out.println("변환완료:"+result); }else
 * if(time_int>=2000 && time_int<2300 ){ result="2000";
 * System.out.println("변환완료:"+result); }else { result="2300";
 * System.out.println("변환완료:"+result); } return result;
 * 
 * }
 * 
 * public String TransDate(){ Date date = new Date(); SimpleDateFormat format =
 * new SimpleDateFormat("yyyyMMdd");
 * 
 * return format.format(date); } }
 */