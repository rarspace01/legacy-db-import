package de.hamann.legacydataimport.threads;

import de.hamann.legacydataimport.model.ModelHandlerTransactions;

public class DatabaseThreadWorkerTransactions implements Runnable {

	private DatabaseThreadHandler dtm_=null;
	private String sTs_;
	
	public DatabaseThreadWorkerTransactions(DatabaseThreadHandler databaseThreadphs,
			String sTs) {
		dtm_=databaseThreadphs;
		this.sTs_=sTs;
	}

	@Override
	public void run() {
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()+1);
		
		long start=System.currentTimeMillis();
		
		
		ModelHandlerTransactions mht=new ModelHandlerTransactions();
		mht.importFile(sTs_);
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()-1);
		
	}

}
