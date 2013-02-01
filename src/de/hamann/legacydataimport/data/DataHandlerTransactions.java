package de.hamann.legacydataimport.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import de.hamann.legacydataimport.model.Transactions;

public class DataHandlerTransactions  {
	
	public void saveTs(List<Transactions> tList){
		
		DataManagerMySQL dmmysql=new DataManagerMySQL();
		Connection conn= dmmysql.getConnection();
		PreparedStatement pstmt = null;
		
			for(int i=0;i<tList.size();i++){
				try {
					pstmt = conn.prepareStatement("INSERT INTO db_legacy.transactions (cusip, reportdate, manager_number, typecode, netchange, sharesheld)"
							+ " VALUES (?, ?, ?, ?, ?, ?);");
						pstmt.setString(1, tList.get(i).cusip);
						pstmt.setDate(2,new Date(tList.get(i).reportdate.getTime()));
						pstmt.setInt(3, tList.get(i).manager_number);
						pstmt.setString(4,tList.get(i).typecode);
						pstmt.setLong(5,tList.get(i).netChange);
						pstmt.setLong(6,tList.get(i).sharesheld);
						pstmt.execute();
				
			} catch (SQLException e) {
				if(!e.getMessage().contains("Duplicate entry")){
					System.out.println(tList.get(0).reportdate.toString());
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
