package de.hamann.legacydataimport;

import java.io.File;

public class init {

	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		final String rev = "$Rev$";
		
		DHL.print("Parser started - "+rev);
		
		DHL.print("Checking Environment");
		
		if(!isValidEnvironment()){
			DHL.print("Error, please create Folder 'db' and 'out' in the same directory");
			DHL.print("Put the database files into 'db' retrive the output from the 'out' folder");
			System.exit(1);
		}else{
			DHL.print("Environment ok - 'db' & 'out' folders exist");
		}
		
		String tmpLevel="";
		
		if(args.length>0){
			for(int i=0;i<args.length;i++){
				//check for csv output
				if(args[i].contains("-csv")){
					DHL.print("CSV Output activated");
					Config.isCSV=true;
				}
				if(args[i].contains("-level")){
					tmpLevel=args[i].substring(args[i].indexOf("-level")+"-level".length());
					Config.depthLevel=Integer.parseInt(tmpLevel);
					DHL.print("FileDepth-Level active: "+Config.depthLevel);
				}
			}
		}
		
		ImportController importController=new ImportController();
		importController.import_Db();
		
	}
	
	public static boolean isValidEnvironment(){
		boolean isValid=false;
		File folder1=new File("out/");
		File folder2=new File("db/");
		if(!folder1.exists()){
			folder1.mkdir();
		}
		if(!folder2.exists()){
			folder2.mkdir();
		}
		
		if(folder1.exists()&&folder2.exists()){
			isValid=true;
		}
		
		return isValid;
	}
	
}
