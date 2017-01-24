package com.refactor.case3.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionConvertor {

	
	
	/**
	 * 获取表达式中计算符号（AND   OR）
	 * @param expression
	 * @return
	 * @throws Exception
	 */
	public static ConditionType getExpressionType(String expression){
		Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))"); //正则表达式，匹配括号内容
		Matcher m=pattern.matcher(expression);
		while(m.find()){
			expression = expression.replace("("+m.group()+")", "");
		}
		if(expression.contains("AND") && !expression.contains("OR")){
			
			return new ConditionType("AND");
		}else if (!expression.contains("AND") && expression.contains("OR")){
			return new ConditionType("OR");
		}else{
			return new ConditionType("AND");
		}
	}
	
	/**
	 * 截取括号中表达式
	 * @param expression
	 * @return
	 * @throws Exception
	 */
	public static List<String> getSecondExpression(String expression) throws Exception{
		Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))"); //正则表达式，匹配括号内容
		ArrayList<String> list=new ArrayList<String>();
		Matcher m=pattern.matcher(expression);
		while(m.find()){
			expression = expression.replace("("+m.group()+")", "");
			list.add(m.group());
		}
		return list;
	}
	
	/**
	 * 截取括号中表达式
	 * @param expression
	 * @return
	 * @throws Exception
	 */
	public static List<String> getNode(String expression) throws Exception{
		String expressType = getExpressionType(expression).getLogic();
		Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))"); //正则表达式，匹配括号内容
		ArrayList<String> list=new ArrayList<String>();
		Matcher m=pattern.matcher(expression);
		while(m.find()){
			expression = expression.replace("("+m.group()+")", "");
		}
		String[] firstNode = expression.split(expressType);
		return Arrays.asList(firstNode);
	}
	
	
	public static void main(String[] args) throws Exception {
		String s="A AND ( B OR C ) AND (C AND D)"; //示例文本
		System.out.println("一级表达式logic is :"+getExpressionType(s));
		List<String> list = getSecondExpression(s);
		for(String second : list){
			
			System.out.println("二级表达式logic is :"+getExpressionType(second));
		}
		
	}
}
