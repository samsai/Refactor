package com.refactor.case3.util;

import java.util.HashMap;
import java.util.Map;

public class ConditionType {
	
	public final static Map<String,String> map = new HashMap<String,String>();  
	static {  
	    map.put("AND", "并且");  
	    map.put("OR", "或者");  
	} 
	private String name;
	private String logic;
	
	public ConditionType(String logic){
		this.logic = logic;
		this.name = map.get(logic);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogic() {
		return logic;
	}
	public void setLogic(String logic) {
		this.logic = logic;
	}
	
	
}
