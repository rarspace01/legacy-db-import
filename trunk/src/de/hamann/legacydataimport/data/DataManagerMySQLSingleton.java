package de.hamann.legacydataimport.data;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**Die Klasse DataManager dient zum Verwalten der Verbindungen mit der Datenbank*/
public class DataManagerMySQLSingleton {
        private static DataManagerMySQLSingleton uniqueInstance;
		private java.sql.Connection conn;
        private Statement stmt;
        
        DataManagerConfig dbmc;
        
        private DataManagerMySQLSingleton() {
        		dbmc=new DataManagerConfig();
                try {
                        Class.forName("com.mysql.jdbc.Driver"); 
                        conn = DriverManager.getConnection("jdbc:mysql://"+dbmc.getIp()+"/"+dbmc.getDatabase()+"",dbmc.getUsername(),dbmc.getPassword());
                        //conn.setAutoCommit(false);
                        stmt = conn.createStatement();
                        
                } catch (SQLException e) {
                        
                        e.printStackTrace();
                        //HelperClass.err(e);
                } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }

        public static DataManagerMySQLSingleton getInstance()
        {
                if(uniqueInstance== null)
                {
            	uniqueInstance=null;
                uniqueInstance=new DataManagerMySQLSingleton();
                }
                return uniqueInstance;  
        }
        
        //Methode für normale Select Operationen
        public ResultSet select(String SQLString) throws SQLException
        {
                ResultSet rs=null;
                
                try {
                        
                        rs=stmt.executeQuery(SQLString);

                } catch (SQLException e) {
                	e.printStackTrace();
                	//HelperClass.err(e);
                }
                return rs;
        }

        //Methode für alles außer select Operationen
        public int execute(String SQLString) throws SQLException
        {
                int i=-1;
                
                try {
                        
                        i=stmt.executeUpdate(SQLString);

                } catch (SQLException e) {
                	if(e.getMessage().contains("Duplicate entry")){
                		System.out.println("Duplicate Entry ON ["+SQLString+"]");
                	}else{
                		e.printStackTrace();
                	}
                }
                return i;
        }
      
    
        
    /**
		 * @return the stmt
		 */
		public Statement getStmt() {
			return stmt;
		}

	public Connection getConnection() {
        return conn;
    }
	
	public void dispose(){
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt=null;
		}
		if(conn!=null){
			try {
				conn.close();
				conn=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}