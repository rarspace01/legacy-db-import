package de.hamann.legacydataimport;

import de.hamann.legacydataimport.model.ModelHandlerManager;
import de.hamann.legacydataimport.model.ModelHandlerPortfolioHolding;
import de.hamann.legacydataimport.model.ModelHandlerStocks;
import de.hamann.legacydataimport.threads.DatabaseThreadHandler;

public class ImportController {

	public void import_Db() {
		
		DataManagerFileIndex localIndex = new DataManagerFileIndex("C:/Users/denis/Documents/db/13F_History_s34");
		
		String currentFile;
		
		int iMax=localIndex.getFilesCount();
		int iCur=1;
		
		while((currentFile=localIndex.getNextFile())!=null){
			System.out.println("["+iCur+"/"+iMax+"] - ["+currentFile+"]");
			if(isValidFileName(currentFile)){
				//System.out.println(getFilename(currentFile)+" - "+getYear(currentFile)+" - "+getQuarter(currentFile));
				switch(getFileType(currentFile)){
				case 341: 	System.out.println("Detected Manager File");
							DatabaseThreadHandler.getInstance().addManagers(new ModelHandlerManager().importFile(currentFile));
							break;
				case 342:	System.out.println("Detected Stock File"); 
							DatabaseThreadHandler.getInstance().addStocks(new ModelHandlerStocks().importFile(currentFile));
							break;
				case 343: 	
							System.out.println("Detected PortfolioHolding File");
							DatabaseThreadHandler.getInstance().addphs(new ModelHandlerPortfolioHolding().importFile(currentFile));
							break;
				case 344: 	break;
				case 346: 	break;
				default:	break;
				}
				
			}
			iCur++;
		}
		
		while(DatabaseThreadHandler.getInstance().getiWorkingThreads()>0){
			System.out.println("Running Threads: "+DatabaseThreadHandler.getInstance().getiWorkingThreads());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static String getFilename(String fullPath){
		String returnString="";
		
		if(fullPath.indexOf("/")>=0){
			returnString=fullPath.substring(fullPath.lastIndexOf("/")+1);
		}
		else if(fullPath.indexOf("\\")>=0){
		returnString=fullPath.substring(fullPath.lastIndexOf("\\")+1);	
		}
		
		
		return returnString;
	}
	
	public boolean isValidFileName(String fullPath){
		boolean isValid=false;
		String workString="";
		workString=getFilename(fullPath);
		workString=workString.substring(workString.indexOf(".")+1);
		if((!workString.contains("save"))&&(!workString.contains("sp"))){
			isValid=true;
		}
		return isValid;
	}
	
	public static int getYear(String fullPath){
		String workString="";
		int iYear;
		
		workString=getFilename(fullPath);
		workString=workString.substring(workString.indexOf(".")+1);
		workString=workString.substring(0, 2);
		iYear=Integer.parseInt(workString);
		if(iYear>50){
			iYear=1900+iYear;
		}else{
			iYear=2000+iYear;
		}
		return iYear;
	}

	public static int getYearShort(String fullPath){
		String workString="";
		int iYear;
		
		workString=getFilename(fullPath);
		workString=workString.substring(workString.indexOf(".")+1);
		workString=workString.substring(0, 2);
		iYear=Integer.parseInt(workString);
		return iYear;
	}	
	
	public static int getQuarter(String fullPath){
		String workString="";
		int iQuarter;
		
		workString=getFilename(fullPath);
		workString=workString.substring(workString.indexOf("."));
		workString=workString.substring(3);
		iQuarter=Integer.parseInt(workString);
		if(iQuarter<=3){
			iQuarter=1;
		}else if(iQuarter>3&&iQuarter<=6){
			iQuarter=2;
		}else if(iQuarter>6&&iQuarter<=9){
			iQuarter=3;
		}else if(iQuarter>9&&iQuarter<=12){
			iQuarter=4;
		}
		return iQuarter;
	}
	
	public static int getQuarterShort(String fullPath){
		String workString="";
		int iQuarter;
		
		workString=getFilename(fullPath);
		workString=workString.substring(workString.indexOf("."));
		workString=workString.substring(3);
		iQuarter=Integer.parseInt(workString);
		return iQuarter;
	}
	
	public int getFileType(String fullPath){
		int iFiletype=-1;
		String workString="";
		workString=getFilename(fullPath);
		workString=workString.substring(1, workString.indexOf("."));
		if(workString.matches("[\\d]{3}"))
		{
			iFiletype=Integer.parseInt(workString);
		}
		return iFiletype;
	}
	
}
