package de.hamann.legacydataimport.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import de.hamann.legacydataimport.model.Stock;

public class DataHandlerStocks  {
	
	public void saveStocks(List<Stock> stockList){
		
		DataManagerMySQL dmmysql=new DataManagerMySQL();
		Connection conn= dmmysql.getConnection();
		PreparedStatement pstmt = null;
		
			for(int i=0;i<stockList.size();i++){
				try {
					pstmt = conn.prepareStatement("INSERT INTO db_legacy.stocks (cusip, name, tickersymbol, extendedtickersymbol, exchangecode, stockclasscode, stockclassdescr, industrycode, sharesoustanding, sharesoustanding_eoq, shareprice_eoq, reportdate)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
						pstmt.setString(1, stockList.get(i).cusip);
						pstmt.setString(2, stockList.get(i).name);
						pstmt.setString(3,stockList.get(i).tickersymbol);
						pstmt.setString(4,stockList.get(i).extendedtickersymbol);
						pstmt.setString(5,stockList.get(i).exchangecode);
						pstmt.setString(6,stockList.get(i).stockclasscode);
						pstmt.setString(7, stockList.get(i).stockclassdescr);
						pstmt.setString(8,stockList.get(i).industrycode);
						pstmt.setLong(9,stockList.get(i).sharesoustanding);
						pstmt.setLong(10,stockList.get(i).sharesoustanding_eoq);
						pstmt.setDouble(11,stockList.get(i).shareprice_eoq);
						pstmt.setDate(12,new Date(stockList.get(i).reportdate.getTime()));
						
						pstmt.execute();
				
			} catch (SQLException e) {
				if(!e.getMessage().contains("Duplicate entry")){
					System.out.println(stockList.get(0).reportdate.toString());
					e.printStackTrace();
				}
			}
			
			}
			//pstmt.executeBatch();
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			pstmt=null;
			//System.out.println("added & executed querys"+objectList.size());

			dmmysql.dispose();
			dmmysql=null;
		
		
	}

}
