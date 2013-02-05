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

import de.hamann.legacydataimport.FW;
import de.hamann.legacydataimport.ImportController;
import de.hamann.legacydataimport.data.DataHandlerTransactions;

public class ModelHandlerBigTransactions {

	private String fullPath_="";
	
	public void importFile(String fullPath){
		DataHandlerTransactions dht=new DataHandlerTransactions();
		this.fullPath_=fullPath;		
		List<Transactions> tList=new ArrayList<Transactions>();
		try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(fullPath);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  
			  Transactions tmpTransaction1;
			  
			  while ((strLine = br.readLine()) != null)   {
				  tmpTransaction1=null;
				  
				  tmpTransaction1=new Transactions();
				  
				  tImport_Basic(tmpTransaction1,strLine);
				  
				  tList.add(tmpTransaction1);
				  
				  //directly put to database
				  dht.saveTs(tList);
				  tList.clear();
				  
			  }
			  //Close the input stream
			  in.close();
			    }catch (Exception e){//Catch exception if any
			    	e.printStackTrace();
			  System.err.println("Error: " + e.getMessage());
			  }
		//return tList;
	}
	
	
	public void tImport_Basic(Transactions tmpT, String sLine){
		String sWorkString=sLine;
		
		tmpT.cusip=FW.getPos(sWorkString, 1, 8).trim();
		
		if(FW.getPos(sWorkString, 9, 13).trim().length()>0&&FW.getPos(sWorkString, 9, 13).trim().matches("[0-9]*")){
			tmpT.manager_number=Integer.parseInt(FW.getPos(sWorkString, 9, 13).trim());
		}
		
		if(FW.getPos(sWorkString, 14, 24).trim().length()>0&&FW.getPos(sWorkString, 14, 24).trim().matches("[0-9]*")){
			tmpT.sharesheld=Integer.parseInt(FW.getPos(sWorkString, 14, 24).trim());
		}
		
		if(FW.getPos(sWorkString, 25, 35).trim().length()>0&&FW.getPos(sWorkString, 25, 35).trim().matches("[\\-]?[0-9]+*")){
			tmpT.netChange=Long.parseLong(FW.getPos(sWorkString, 25, 35).trim());
		}
		
		SimpleDateFormat reportDateFormatCustom=new SimpleDateFormat("dd.MM.yyyy");
		
		try {
			
				Calendar cal=Calendar.getInstance();
				cal.set(ImportController.getYear(fullPath_), (ImportController.getQuarterShort(fullPath_)-1), 1);
				String sDate=cal.getActualMaximum(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR);
				
				tmpT.reportdate= reportDateFormatCustom.parse(sDate);
		} catch (ParseException e) {
			System.out.println("ERROR on ["+sLine+"]");
			e.printStackTrace();
		}
		
		}
	
}
