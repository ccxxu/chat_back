package com.test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import club.mzwh.common.util.StringUtil;

public class TestVisit {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		Stack<Map<String, String>> stack = new Stack<Map<String,String>>();
    	for (int i = 0; i < 12; i++) {
    		Map<String, String> visitMap = new HashMap<String, String>();
			cal.add(Calendar.MONTH, -1);
			Date date = cal.getTime();
			System.out.println(StringUtil.dateToString(date, "yyyy年MM月"));
			Random ran = new Random();
			System.out.println(ran.nextInt(1000));
			visitMap.put("month", StringUtil.dateToString(date, "yyyy年MM月"));
			stack.push(visitMap);
		}
    	
    	int size = stack.size();
    	for (int i = 0; i < size; i++) {
			System.out.println(stack.pop());
		}
    	
    	float sd = (float)(Math.round(8653*100))/10000;
    	System.out.println(sd);
	}
	
}
