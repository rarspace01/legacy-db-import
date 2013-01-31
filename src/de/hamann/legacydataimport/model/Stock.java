package de.hamann.legacydataimport.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stock {

	public String cusip;
	public String name;
	public String tickersymbol;
	public String extendedtickersymbol;
	
	public String exchangecode;
	public String stockclasscode;
	public String stockclassdescr;
	
	public String industrycode;
	
	public long sharesoustanding=0;
	public long sharesoustanding_eoq=0;
	public double shareprice_eoq=0.0;
	
	public Date reportdate;

	public void printAll() {
		
		SimpleDateFormat df=new SimpleDateFormat("dd.MM.yyyy");
		String reportdate="";
		if(this.reportdate!=null)
			reportdate=df.format(this.reportdate);
		
		System.out.println(cusip+" - "
				+name+" - "
				+tickersymbol+" - "
				+extendedtickersymbol+" - "
				+exchangecode+" - "
				+stockclasscode+" - "
				+stockclassdescr+" - "
				+industrycode+" - "
				+sharesoustanding+" - "
				+sharesoustanding_eoq+" - "
				+shareprice_eoq+" - "
				+reportdate
				);
		
		
	}
	
}
