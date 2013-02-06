package de.hamann.legacydataimport.model;

import java.util.Date;

public class PortfolioHolding {

	public String cusip="";
	public Date reportdate;
	public int manager_number;
	public String typecode="";
	public long sharehold_eoq;
	public long solevotingauthsharesheld;
	public long sharedvotingauthsharesheld;
	public long novotingauthsharesheld;
	public void printAll() {
		
		System.out.println(cusip+" - "
				+reportdate.toString()+" - "
				+manager_number+" - "
				+typecode + " - "
				+sharehold_eoq+ " - "
				+solevotingauthsharesheld+ " - "
				+sharedvotingauthsharesheld+ " - "
				+novotingauthsharesheld
				);
		
	}
	
}
