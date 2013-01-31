package de.hamann.legacydataimport.model;

import java.text.SimpleDateFormat;
import java.util.Date;



public class Manager {

	public int number;
	public String name = "";
	public int type;
	public Date reportdate;
	public Date priorreportdate;
	public String permanentkey = "";
	public String country = "";
	
	public void printAll(){
		SimpleDateFormat df=new SimpleDateFormat("dd.MM.yyyy");
		String reportdate="";
		String priodate="";
		if(this.reportdate!=null)
			reportdate=df.format(this.reportdate);
		if(this.priorreportdate!=null)
			priodate=df.format(this.priorreportdate);
		System.out.println(number+" - "
				+name+" - "
				+type+" - R["
				+reportdate+"] - P["
				+priodate+"] - "
				+permanentkey+" - "
				+country
				
				
				
				);
	}

}
