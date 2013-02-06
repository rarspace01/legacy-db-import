package de.hamann.legacydataimport.model;

import java.util.Date;

public class Transactions {

	public String cusip="";
	public int manager_number;
	public String typecode="";
	public long netChange;
	public long sharesheld;
	public Date reportdate;
	
	public void printAll() {
		System.out.println(cusip+" - "
				+reportdate.toString()+" - "
				+manager_number+" - "
				+typecode + " - "
				+netChange+ " - "
				+sharesheld
				);
	}
}
