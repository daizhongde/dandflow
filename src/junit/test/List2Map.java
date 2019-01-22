package junit.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class List2Map {
	public static Map toMap2(List list){
		Map map  = new HashMap();
		for(int i=0,j=list.size(); i<j; i++){
			map.put(list.get(0), list.get(1));
		}
		return map;
	}
	public static Map toMap(List list){
		
		Map map = new HashMap();
		Map temp = new HashMap();
		for(int i=0,j=list.size(); i<j; i++){
			temp = (Map)list.get(i);
			map.put(temp.get(0), temp.get(1));
		}
		System.out.println("");
		return map;
	}
	
	public static void main(String args[]){
		String s="[{'tname':'TB1','comments':'表1'}," +
				"{ tname:'TB2',comments:null}]";
		
		JSONArray ja = JSONArray.fromObject(s);  
		
		List list = JSONArray.toList(ja);
		
		System.out.println("list.size():"+list.size());
		
		Map map = new HashMap();
		
		for(int i=0,j=list.size(); i<j; i++){
			Map temp = new HashMap();
			temp = (Map)JSONObject.fromObject(list.get(i));
			map.put(temp.get("tname"), temp.get("comments"));
		}
		System.out.println("map.size():"+map.size());
		System.out.println("list:"+JSONArray.fromObject(list).toString() );
		System.out.println("map:"+JSONObject.fromObject(map).toString());
		
		
		
	}
	
}
