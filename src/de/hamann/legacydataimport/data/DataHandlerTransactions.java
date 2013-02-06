package de.hamann.legacydataimport.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import de.hamann.legacydataimport.model.Transactions;

public class DataHandlerTransactions  {
	
	public void saveTs(List<Transactions> tList){
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		
//		Connection conn= DataManagerMySQLSingleton.getInstance().getConnection();
//		PreparedStatement pstmt = null;
		
			for(int i=0;i<tList.size();i++){
				try {
					
					if(tList.get(i).sharesheld==0){
					
						FileWriter fstream = new FileWriter("out.sql",true);
						BufferedWriter out = new BufferedWriter(fstream);
						out.write("INSERT INTO db_legacy.transactions (cusip, reportdate, manager_number, typecode, netchange, sharesheld)"+
						" VALUES ('" +
						tList.get(i).cusip.replace("'", "''")+"', '" +
						dateFormat.format(tList.get(i).reportdate)+"', '" +
						tList.get(i).manager_number+"', '" +
						tList.get(i).typecode.replace("'", "''")+"', '" +
						tList.get(i).netChange+"', '" +
						tList.get(i).sharesheld+"');\n");
						out.close();
					
					}else{
						FileWriter fstream = new FileWriter("out.sql",true);
						BufferedWriter out = new BufferedWriter(fstream);
						out.write("INSERT INTO db_legacy.transactions (cusip, reportdate, manager_number, typecode, netchange, sharesheld)"+
						" VALUES ('" +
						tList.get(i).cusip.replace("'", "''")+"', '" +
						dateFormat.format(tList.get(i).reportdate)+"', '" +
						tList.get(i).manager_number+"', '" +
						tList.get(i).typecode.replace("'", "''")+"', '" +
						tList.get(i).netChange+"', '" +
						tList.get(i).sharesheld+"') " +
								"ON DUPLICATE KEY UPDATE sharesheld='"+tList.get(i).sharesheld+"';\n");
						out.close();
					}
					
//					pstmt = conn.prepareStatement("INSERT INTO db_legacy.transactions (cusip, reportdate, manager_number, typecode, netchange, sharesheld)"
//							+ " VALUES (?, ?, ?, ?, ?, ?);");
//						pstmt.setString(1, tList.get(i).cusip);
//						pstmt.setDate(2,new Date(tList.get(i).reportdate.getTime()));
//						pstmt.setInt(3, tList.get(i).manager_number);
//						pstmt.setString(4,tList.get(i).typecode);
//						pstmt.setLong(5,tList.get(i).netChange);
//						pstmt.setLong(6,tList.get(i).sharesheld);
//						pstmt.execute();
				
					} catch (IOException ioe) {
						// TODO Auto-generated catch block
						ioe.printStackTrace();
					} catch (Exception e){
						System.out.println("Exception on dump to file");
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

	}

}
