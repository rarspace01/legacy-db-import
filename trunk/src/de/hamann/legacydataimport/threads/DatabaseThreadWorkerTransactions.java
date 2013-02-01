package de.hamann.legacydataimport.threads;

import java.util.List;

import de.hamann.legacydataimport.data.DataHandlerTransactions;
import de.hamann.legacydataimport.model.Transactions;

public class DatabaseThreadWorkerTransactions implements Runnable {

	private DatabaseThreadHandler dtm_=null;
	private List<Transactions> listTs_;
	
	public DatabaseThreadWorkerTransactions(DatabaseThreadHandler databaseThreadphs,
			List<Transactions> listTs) {
		dtm_=databaseThreadphs;
		this.listTs_=listTs;
	}

	@Override
	public void run() {
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()+1);
		
		long start=System.currentTimeMillis();
		
		System.out.println("[ThreadM] got Transactions: "+ listTs_.size());
		
		new DataHandlerTransactions().saveTs(listTs_);
		
		System.out.println("Finished Transactions-Thread after ["+(System.currentTimeMillis()-start)+"]ms - Speed:"+((System.currentTimeMillis()-start)/(listTs_.size()))+"ms/statement");
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()-1);
		
	}

}
