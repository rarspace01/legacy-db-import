package de.hamann.legacydataimport.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import de.hamann.legacydataimport.model.PortfolioHolding;
import de.hamann.legacydataimport.model.Transactions;

public class DataHandlerPortfolioHolding  {
	
	public void saveTransactions(List<Transactions> tList){
		
		DataManagerMySQL dmmysql=new DataManagerMySQL();
		Connection conn= dmmysql.getConnection();
		PreparedStatement pstmt = null;
		
			for(int i=0;i<tList.size();i++){
				try {
					pstmt = conn.prepareStatement("INSERT INTO db_legacy.transactions (cusip, reportdate, manager_number, typecode, sharehold_eoq, solevotingauthsharesheld, sharedvotingauthsharesheld, novotingauthsharesheld)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
						pstmt.setString(1, tList.get(i).cusip);
						pstmt.setDate(2,new Date(tList.get(i).reportdate.getTime()));
						pstmt.setInt(3, tList.get(i).manager_number);
						pstmt.setString(4,tList.get(i).typecode);
						pstmt.setLong(5,tList.get(i).sharehold_eoq);
						pstmt.setLong(6,tList.get(i).solevotingauthsharesheld);
						pstmt.setLong(7,tList.get(i).sharedvotingauthsharesheld);
						pstmt.setLong(8, tList.get(i).novotingauthsharesheld);
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
