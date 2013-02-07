package de.hamann.legacydataimport;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger class
 * @author denis
 *
 */
public class DHL {

	public static void print(String message){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tmpString="["+sdf.format(new Date())+"]"+message+"\r\n";
		FileWriter fstream;
		try {
			fstream = new FileWriter("legacydb.log",true);
			BufferedWriter out = new BufferedWriter(fstream);
			System.out.print(tmpString);
			out.write(tmpString);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
