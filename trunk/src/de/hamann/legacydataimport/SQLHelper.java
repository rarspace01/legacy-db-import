package de.hamann.legacydataimport;

public class SQLHelper {

	public static String cleanForSQL(String sSQL){
		String sreturn="";
		sreturn=sSQL;
		sreturn=sreturn.replace("'", "");
		return sreturn;
	}
	
}
