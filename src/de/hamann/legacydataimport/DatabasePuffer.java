package de.hamann.legacydataimport;

import java.util.ArrayList;

public class DatabasePuffer {

	private ArrayList<String> sqlArray= new ArrayList<String>();
	
	public void addSQL(String sSQL){
		sqlArray.add(sSQL);
	}
	
	public String get(int entry){
		return sqlArray.get(entry);
	}
	
	public int size(){
		return sqlArray.size();
	}

	public ArrayList<String> getAll() {
		// TODO Auto-generated method stub
		return sqlArray;
	}
	
//	public void getAll(){
//		for(int i=0;i<sqlArray.size();i++){
//			sqlArray.get(i);
//		}
//	}
		                         
}
