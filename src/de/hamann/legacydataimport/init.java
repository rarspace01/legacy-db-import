package de.hamann.legacydataimport;

public class init {

	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		final String rev = "$Rev$";
		
		DHL.print("Parser started - "+rev);
		
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
	
}
