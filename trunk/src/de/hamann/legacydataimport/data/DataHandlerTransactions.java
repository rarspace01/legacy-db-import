package de.hamann.legacydataimport.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import de.hamann.legacydataimport.model.PortfolioHolding;

public class DataHandlerTransactions  {
	
	public void savephs(List<PortfolioHolding> phList){
		
		DataManagerMySQL dmmysql=new DataManagerMySQL();
		Connection conn= dmmysql.getConnection();
		PreparedStatement pstmt = null;
		
			for(int i=0;i<phList.size();i++){
				try {
					pstmt = conn.prepareStatement("INSERT INTO db_legacy.portfolioholding (cusip, reportdate, manager_number, typecode, sharehold_eoq, solevotingauthsharesheld, sharedvotingauthsharesheld, novotingauthsharesheld)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
						pstmt.setString(1, phList.get(i).cusip);
						pstmt.setDate(2,new Date(phList.get(i).reportdate.getTime()));
						pstmt.setInt(3, phList.get(i).manager_number);
						pstmt.setString(4,phList.get(i).typecode);
						pstmt.setLong(5,phList.get(i).sharehold_eoq);
						pstmt.setLong(6,phList.get(i).solevotingauthsharesheld);
						pstmt.setLong(7,phList.get(i).sharedvotingauthsharesheld);
						pstmt.setLong(8, phList.get(i).novotingauthsharesheld);
						pstmt.execute();
				
			} catch (SQLException e) {
				if(!e.getMessage().contains("Duplicate entry")){
					System.out.println(phList.get(0).reportdate.toString());
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
