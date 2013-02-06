package de.hamann.legacydataimport.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import de.hamann.legacydataimport.Config;
import de.hamann.legacydataimport.ImportController;
import de.hamann.legacydataimport.model.Stock;

public class DataHandlerStocks  {
	
	public void saveStocks(List<Stock> stockList, String fullPath_){
		
		String outputString="";
		
		switch(Config.depthLevel){
		case 1:		outputString="stock";
					break;
		case 2:		outputString="stock_"+ImportController.getYear(fullPath_)+"_"+ImportController.getQuarterShort(fullPath_);
					break;
		default:	outputString="out";
					break;
		}
		
		if(Config.isCSV){
			outputString+=".csv";
		}else{
			outputString+=".sql";
		}
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		
//		DataManagerMySQL dmmysql=new DataManagerMySQL();
//		Connection conn= dmmysql.getConnection();
//		PreparedStatement pstmt = null;
		
			for(int i=0;i<stockList.size();i++){
				try {
					
					FileWriter fstream = new FileWriter(outputString,true);
					BufferedWriter out = new BufferedWriter(fstream);
					
					if(Config.isCSV){
						
						out.write(stockList.get(i).cusip+";" +
								stockList.get(i).name+";" +
								stockList.get(i).tickersymbol+";" +
								stockList.get(i).extendedtickersymbol+";" +
								stockList.get(i).exchangecode+";" +
								stockList.get(i).stockclasscode+";" +
								stockList.get(i).stockclassdescr+";" +
								stockList.get(i).industrycode+";" +
								stockList.get(i).sharesoustanding+";" +
								stockList.get(i).sharesoustanding_eoq+";" +
								stockList.get(i).shareprice_eoq+";" +
								dateFormat.format(stockList.get(i).reportdate)+";\n");
						
					}else{
						
						out.write("INSERT INTO db_legacy.stocks (cusip, name, tickersymbol, extendedtickersymbol, exchangecode, stockclasscode, stockclassdescr, industrycode, sharesoustanding, sharesoustanding_eoq, shareprice_eoq, reportdate)"+
								" VALUES ('"+stockList.get(i).cusip.replace("'", "''")+"'," +
								" '"+stockList.get(i).name.replace("'", "''")+"'," +
								" '"+stockList.get(i).tickersymbol.replace("'", "''")+"'," +
								" '"+stockList.get(i).extendedtickersymbol.replace("'", "''")+"'," +
								" '"+stockList.get(i).exchangecode.replace("'", "''")+"'," +
								" '"+stockList.get(i).stockclasscode.replace("'", "''")+"'," +
								" '"+stockList.get(i).stockclassdescr.replace("'", "''")+"'," +
								" '"+stockList.get(i).industrycode.replace("'", "''")+"'," +
								" '"+stockList.get(i).sharesoustanding+"'," +
								" '"+stockList.get(i).sharesoustanding_eoq+"'," +
								" '"+stockList.get(i).shareprice_eoq+"'," +
								" '"+dateFormat.format(stockList.get(i).reportdate)+"');\n");
					}
					
					
					out.close();
					
//					pstmt = conn.prepareStatement("INSERT INTO db_legacy.stocks (cusip, name, tickersymbol, extendedtickersymbol, exchangecode, stockclasscode, stockclassdescr, industrycode, sharesoustanding, sharesoustanding_eoq, shareprice_eoq, reportdate)"
//							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
//						pstmt.setString(1, stockList.get(i).cusip);
//						pstmt.setString(2, stockList.get(i).name);
//						pstmt.setString(3,stockList.get(i).tickersymbol);
//						pstmt.setString(4,stockList.get(i).extendedtickersymbol);
//						pstmt.setString(5,stockList.get(i).exchangecode);
//						pstmt.setString(6,stockList.get(i).stockclasscode);
//						pstmt.setString(7, stockList.get(i).stockclassdescr);
//						pstmt.setString(8,stockList.get(i).industrycode);
//						pstmt.setLong(9,stockList.get(i).sharesoustanding);
//						pstmt.setLong(10,stockList.get(i).sharesoustanding_eoq);
//						pstmt.setDouble(11,stockList.get(i).shareprice_eoq);
//						pstmt.setDate(12,new Date(stockList.get(i).reportdate.getTime()));
//						
//						pstmt.execute();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
			//pstmt.executeBatch();
//			if(pstmt!=null){
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			pstmt=null;
//			//System.out.println("added & executed querys"+objectList.size());
//
//			dmmysql.dispose();
//			dmmysql=null;
		
		
	}

}
