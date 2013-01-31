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

public class ModelHandlerManager {

	private String fullPath_="";
	
	public List<Manager> importFile(String fullPath){
		this.fullPath_=fullPath;		
		List<Manager> managerList=new ArrayList<Manager>();
		try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(fullPath);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  
			  Manager tmpManager;
			  
			  while ((strLine = br.readLine()) != null)   {
				  // Print the content on the console
//				  System.out.println (iLinenumber);
//				  iLinenumber++;
				  tmpManager=null;
				  tmpManager=new Manager();
				  
				  managerImport_Basic(tmpManager,strLine);
				  
//				  if(ImportController.getYear(fullPath)>=2004){
//					  managerImportpriorReportDate(tmpManager,strLine,6);
//					  managerImportCountry(tmpManager,strLine,28);
//				  }else{
					  managerImportpriorReportDate(tmpManager,strLine,5);
					  managerImportCountry(tmpManager,strLine,29);
//				  }
				  
				  
				  //dbp.addSQL(tmpManager.printSQL());
					  managerList.add(tmpManager);
				  
			  }
			  //Close the input stream
			  in.close();
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		return managerList;
	}
	
	
	public void managerImport_Basic(Manager tmpManager, String sLine){
		String sWorkString=sLine;
		
		tmpManager.number=Integer.parseInt(FW.getPos(sWorkString, 1, 5).trim());
		tmpManager.name=FW.getPos(sWorkString, 6, 35).trim();
		tmpManager.type=Integer.parseInt(FW.getPos(sWorkString, 36, 36).trim());
		
		SimpleDateFormat reportDateFormat=new SimpleDateFormat("MMddyy");
		SimpleDateFormat reportDateFormatCustom=new SimpleDateFormat("dd.MM.yyyy");
		
		try {
			if(FW.getPos(sWorkString, 37, 42).matches("[\\s]*[\\d]{4,6}")){
				tmpManager.reportdate= reportDateFormat.parse(FW.getPos(sWorkString, 37, 42));
//				if(tmpManager.reportdate.getMonth()<3){
//					System.out.println("<"+sLine+"> - <"+FW.getPos(sWorkString, 37, 42)+">");
//				}
				//System.out.println("Got Date: "+FW.getPos(sWorkString, 37, 42));
			}else {
				System.out.println("sLine Size:"+sWorkString.length()+" <"+FW.getPos(sWorkString, 37, 42)+">");
				
				
				Calendar cal=Calendar.getInstance();
				cal.set(ImportController.getYear(fullPath_), (ImportController.getQuarterShort(fullPath_)-1), 1);
				String sDate=cal.getActualMaximum(Calendar.DAY_OF_MONTH)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.YEAR);
				
				System.out.println("Manual reportdate generation - "+sDate);
				tmpManager.reportdate= reportDateFormatCustom.parse(sDate);
			}
				
		} catch (ParseException e) {
			System.out.println("ERROR on ["+sLine+"]");
			e.printStackTrace();
		}
		
		// only if is available
		if(sLine.length()>=47)
		tmpManager.permanentkey=FW.getPos(sWorkString, 43, 47).trim();
		
		}

	public void managerImportpriorReportDate(Manager tmpManager,String sLine, int prioDateLength){
		String sWorkString=sLine;
		sWorkString=FW.getPos(sWorkString, 51, 51+prioDateLength).trim();
		SimpleDateFormat reportDateFormat=new SimpleDateFormat("MMddyy");
		if(sWorkString.length()>0&&sWorkString.matches("[\\d]{4,6}")){
			try {
				tmpManager.priorreportdate=reportDateFormat.parse(sWorkString);
			} catch (ParseException e) {
				System.out.println("ERROR ON:\n"+sLine);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void managerImportCountry(Manager tmpManager,String sLine, int countryLength){
		String sWorkString=sLine;
		if(sWorkString.length()>=86)
		tmpManager.country=FW.getPos(sWorkString, 86-countryLength, 86).trim();
	}
	
}
