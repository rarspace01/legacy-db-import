package de.hamann.legacydataimport.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import de.hamann.legacydataimport.model.Manager;

public class DataHandlerManager  {
	
	public void saveManagers(List<Manager> objectList){
		
		DataManagerMySQL dmmysql=new DataManagerMySQL();
		Connection conn= dmmysql.getConnection();
		PreparedStatement pstmt = null;
		
			for(int i=0;i<objectList.size();i++){
				try {
					if(objectList.get(i).priorreportdate!=null){
					pstmt = conn.prepareStatement("INSERT INTO db_legacy.manager (number, name, typecode, reportdate, permanentkey, prioreportdate, country)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?);");
						pstmt.setInt(1, objectList.get(i).number);
						pstmt.setString(2, objectList.get(i).name);
						pstmt.setInt(3, objectList.get(i).type);
						pstmt.setDate(4, new Date(objectList.get(i).reportdate.getTime()));
						pstmt.setString(5, objectList.get(i).permanentkey);
						pstmt.setDate(6, new Date(objectList.get(i).priorreportdate.getTime()));
						pstmt.setString(7, objectList.get(i).country);
						pstmt.execute();
						
					}else{
						pstmt = conn.prepareStatement("INSERT INTO db_legacy.manager (number, name, typecode, reportdate, permanentkey,  country)"
								+ " VALUES (?, ?, ?, ?, ?, ?);");
						pstmt.setInt(1, objectList.get(i).number);
						pstmt.setString(2, objectList.get(i).name);
						pstmt.setInt(3, objectList.get(i).type);
						pstmt.setDate(4, new Date(objectList.get(i).reportdate.getTime()));
						pstmt.setString(5, objectList.get(i).permanentkey);
						pstmt.setString(6, objectList.get(i).country);
						pstmt.execute();
						
					}
				
			} catch (SQLException e) {
				if(!e.getMessage().contains("Duplicate entry")){
					System.out.println(objectList.get(0).reportdate.toString());
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
