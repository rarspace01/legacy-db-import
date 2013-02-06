package de.hamann.legacydataimport.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hamann.legacydataimport.DHL;
import de.hamann.legacydataimport.FW;
import de.hamann.legacydataimport.ImportController;
import de.hamann.legacydataimport.data.DataHandlerPortfolioHolding;

public class ModelHandlerPortfolioHolding {

	private String fullPath_="";
	
	public void importFile(String fullPath){
		this.fullPath_=fullPath;		
		List<PortfolioHolding> phList=new ArrayList<PortfolioHolding>();
		DataHandlerPortfolioHolding dhph=new DataHandlerPortfolioHolding();
		try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(fullPath);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  
			  PortfolioHolding tmpPortfolioHolding1;
//			  PortfolioHolding tmpPortfolioHolding2;
			  
			  while ((strLine = br.readLine()) != null)   {
				  tmpPortfolioHolding1=null;
//				  tmpPortfolioHolding2=null;
				  
				  tmpPortfolioHolding1=new PortfolioHolding();
				  
				  phImport_Basic(tmpPortfolioHolding1,strLine);
				  
//				  if(ImportController.getYear(fullPath_)==2000&&ImportController.getQuarterShort(fullPath_)==3){
//					  phImport_Basic(tmpPortfolioHolding2, strLine.substring(25));
//					  phList.add(tmpPortfolioHolding2);
//				  }else{
					  //import adv
					  
					  if(FW.getPos(strLine, 26, 36).trim().length()>0&&FW.getPos(strLine, 26, 36).trim().matches("[0-9]*")){
						  tmpPortfolioHolding1.solevotingauthsharesheld=Long.parseLong(FW.getPos(strLine, 26, 36).trim());
					  }
					  
					  if(FW.getPos(strLine, 37, 47).trim().length()>0&&FW.getPos(strLine, 37, 47).trim().matches("[0-9]*")){
						  tmpPortfolioHolding1.sharedvotingauthsharesheld=Long.parseLong(FW.getPos(strLine, 37, 47).trim());
					  }

					  if(FW.getPos(strLine, 48, 58).trim().length()>0&&FW.getPos(strLine, 48, 58).trim().matches("[0-9]*")){
						  tmpPortfolioHolding1.novotingauthsharesheld=Long.parseLong(FW.getPos(strLine, 48, 58).trim());
					  }
					  
//				  }
				  
				  phList.add(tmpPortfolioHolding1);
				  
				  dhph.savephs(phList,fullPath_);
				  phList.clear();
			  }
			  //Close the input stream
			  in.close();
			    }catch (Exception e){//Catch exception if any
			    	DHL.print("[MHPH]Exception");
					DHL.print(e.getMessage());
					e.printStackTrace();
			  }
	}
	
	
	public void phImport_Basic(PortfolioHolding tmpPh, String sLine){
		String sWorkString=""+sLine;
		if(sWorkString!=null&&sWorkString.length()>0){
			//System.out.println("L"+sWorkString.length());
			tmpPh.cusip=FW.getPos(sWorkString, 1, 8).trim();
			
			if(FW.getPos(sWorkString, 9, 13).trim().length()>0&&FW.getPos(sWorkString, 9, 13).trim().matches("[0-9]*")){
				tmpPh.manager_number=Integer.parseInt(FW.getPos(sWorkString, 9, 13).trim());
			}
			tmpPh.typecode=FW.getPos(sWorkString, 14, 14).trim();
			
			if(FW.getPos(sWorkString, 15, 25).trim().length()>0&&FW.getPos(sWorkString, 15, 25).trim().matches("[0-9]*")){
				tmpPh.sharehold_eoq=Long.parseLong(FW.getPos(sWorkString, 15, 25).trim());
			}
			
			SimpleDateFormat reportDateFormatCustom=new SimpleDateFormat("dd.MM.yyyy");
			
			try {
				
					Calendar cal=Calendar.getInstance();
					cal.set(ImportController.getYear(fullPath_), (ImportController.getQuarterShort(fullPath_)-1), 1);
					String sDate=cal.getActualMaximum(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR);
					
					tmpPh.reportdate= reportDateFormatCustom.parse(sDate);
			} catch (ParseException e) {
				DHL.print("[MHPH]Exception - on ["+sLine+"]");
				DHL.print(e.getMessage());
				e.printStackTrace();
			}
		
		}
	}
}
