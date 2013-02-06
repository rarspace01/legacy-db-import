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
import de.hamann.legacydataimport.data.DataHandlerStocks;

public class ModelHandlerStocks {

	private String fullPath_="";
	
	public void importFile(String fullPath){
		this.fullPath_=fullPath;		
		List<Stock> stockList=new ArrayList<Stock>();
		try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(fullPath);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  
			  Stock tmpStock;
			  
			  while ((strLine = br.readLine()) != null)   {
				  // Print the content on the console
//				  System.out.println (iLinenumber);
//				  iLinenumber++;
				  tmpStock=null;
				  tmpStock=new Stock();
				  
				  stockImport_Basic(tmpStock,strLine);
				  
				  if(ImportController.getYear(fullPath_)<2004){
					  if(FW.getPos(strLine, 41, 44).trim().length()>0&&FW.getPos(strLine, 41, 44).trim().matches("[0-9]*")){
						  tmpStock.sharesoustanding_eoq = Long.parseLong(FW.getPos(strLine, 41, 44).trim());
					  }
					  if(FW.getPos(strLine, 45, 50).trim().length()>0&&FW.getPos(strLine, 45, 50).trim().matches("[0-9]*")){
						  tmpStock.shareprice_eoq = Long.parseLong(FW.getPos(strLine, 45, 50).trim());
					  }
					  tmpStock.extendedtickersymbol = FW.getPos(strLine, 51, 56);
					  tmpStock.exchangecode = FW.getPos(strLine, 57, 57);
					  tmpStock.stockclasscode = FW.getPos(strLine, 58, 58);
					  tmpStock.stockclassdescr = FW.getPos(strLine, 59, 62);
					  if(FW.getPos(strLine, 63, 70).trim().length()>0&&FW.getPos(strLine, 63, 70).trim().matches("[0-9]*")){
						  tmpStock.sharesoustanding = Long.parseLong(FW.getPos(strLine, 63, 70).trim());
					  }
					  tmpStock.industrycode = FW.getPos(strLine, 71, 75);
				  }else{
					  if(FW.getPos(strLine, 41, 46).trim().length()>0&&FW.getPos(strLine, 41, 46).trim().matches("[0-9]*")){
						  tmpStock.sharesoustanding_eoq = Long.parseLong(FW.getPos(strLine, 41, 46).trim());
					  }
					  if(FW.getPos(strLine, 47, 52).trim().length()>0&&FW.getPos(strLine, 47, 52).trim().matches("[0-9]*")){
						  tmpStock.shareprice_eoq = Long.parseLong(FW.getPos(strLine, 47, 52).trim());
					  }
					  tmpStock.extendedtickersymbol = FW.getPos(strLine, 53, 58);
					  tmpStock.exchangecode = FW.getPos(strLine, 59, 59);
					  tmpStock.stockclasscode = FW.getPos(strLine, 60, 60);
					  tmpStock.stockclassdescr = FW.getPos(strLine, 61, 63);
					  if(FW.getPos(strLine, 64, 72).trim().length()>0&&FW.getPos(strLine, 64, 72).trim().matches("[0-9]*")){
						  tmpStock.sharesoustanding = Long.parseLong(FW.getPos(strLine, 64, 72).trim());
					  }
					  tmpStock.industrycode = FW.getPos(strLine, 73, 77); 
				  }
				  
				  //dbp.addSQL(tmpManager.printSQL());
					  stockList.add(tmpStock);
				  
			  }
			  //Close the input stream
			  in.close();
			    }catch (Exception e){//Catch exception if any
			    	DHL.print("[MHS]Exception");
					DHL.print(e.getMessage());
			  }
		new DataHandlerStocks().saveStocks(stockList,fullPath_);
		//return stockList;
	}
	
	
	public void stockImport_Basic(Stock tmpStock, String sLine){
		String sWorkString=sLine;
		
		tmpStock.cusip=FW.getPos(sWorkString, 1, 8).trim();
		tmpStock.name=FW.getPos(sWorkString, 9, 36).trim();
		tmpStock.tickersymbol=FW.getPos(sWorkString, 37, 40).trim();
		
		SimpleDateFormat reportDateFormatCustom=new SimpleDateFormat("dd.MM.yyyy");
		
		try {
			
				Calendar cal=Calendar.getInstance();
				cal.set(ImportController.getYear(fullPath_), (ImportController.getQuarterShort(fullPath_)-1), 1);
				String sDate=cal.getActualMaximum(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR);
				
				tmpStock.reportdate= reportDateFormatCustom.parse(sDate);
		} catch (ParseException e) {
			DHL.print("[MHS]Exception - on ["+sLine+"]");
			DHL.print(e.getMessage());
		}
		
		}
	
}
