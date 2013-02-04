package de.hamann.legacydataimport.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import de.hamann.legacydataimport.model.PortfolioHolding;

public class DataHandlerPortfolioHolding  {
	
	public void savephs(List<PortfolioHolding> phList){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//		Connection conn= DataManagerMySQLSingleton.getInstance().getConnection();
//		PreparedStatement pstmt = null;
		
			for(int i=0;i<phList.size();i++){
				try {
					
					FileWriter fstream = new FileWriter("out.sql",true);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("INSERT INTO db_legacy.portfolioholding (cusip, reportdate, manager_number, typecode, sharehold_eoq, solevotingauthsharesheld, sharedvotingauthsharesheld, novotingauthsharesheld)"
							+ " VALUES ('" +
						phList.get(i).cusip.replace("'", "''")+"', '" +
						dateFormat.format(phList.get(i).reportdate)+"', '" +
						phList.get(i).manager_number+"', '" +
						phList.get(i).typecode.replace("'", "''")+"', '" +
						phList.get(i).sharehold_eoq+"', '" +
						phList.get(i).solevotingauthsharesheld+"', '" +
						phList.get(i).sharedvotingauthsharesheld+"', '" +
						phList.get(i).novotingauthsharesheld+"');\n");
					out.close();
					
//					pstmt = conn.prepareStatement("INSERT INTO db_legacy.portfolioholding (cusip, reportdate, manager_number, typecode, sharehold_eoq, solevotingauthsharesheld, sharedvotingauthsharesheld, novotingauthsharesheld)"
//							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
//						pstmt.setString(1, phList.get(i).cusip);
//						pstmt.setDate(2,new Date(phList.get(i).reportdate.getTime()));
//						pstmt.setInt(3, phList.get(i).manager_number);
//						pstmt.setString(4,phList.get(i).typecode);
//						pstmt.setLong(5,phList.get(i).sharehold_eoq);
//						pstmt.setLong(6,phList.get(i).solevotingauthsharesheld);
//						pstmt.setLong(7,phList.get(i).sharedvotingauthsharesheld);
//						pstmt.setLong(8, phList.get(i).novotingauthsharesheld);
//						pstmt.execute();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
//			//pstmt.executeBatch();
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
		
	}

}
