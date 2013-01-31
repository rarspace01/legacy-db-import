package de.hamann.legacydataimport;

public class FW {

	public static String getPos(String input, int iStart, int iStop){
		String tmpString="";
		if(iStart<=input.length()&&iStop<=input.length())
		{
			if(iStop<input.length()){
				tmpString=input.substring(iStart-1, iStop);
			}else if (iStop==input.length()){
				tmpString=input.substring(iStart-1);
			}
		}
		return tmpString;
	}
	
}
