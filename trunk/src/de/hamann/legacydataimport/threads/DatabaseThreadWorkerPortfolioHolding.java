package de.hamann.legacydataimport.threads;

import de.hamann.legacydataimport.model.ModelHandlerPortfolioHolding;;

public class DatabaseThreadWorkerPortfolioHolding implements Runnable {

	private DatabaseThreadHandler dtm_=null;
	private String phs_;
	
	public DatabaseThreadWorkerPortfolioHolding(DatabaseThreadHandler databaseThreadphs,
			String sPath) {
		dtm_=databaseThreadphs;
		this.phs_=sPath;
	}

	@Override
	public void run() {
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()+1);
		
		long start=System.currentTimeMillis();
		
		ModelHandlerPortfolioHolding mhph=new ModelHandlerPortfolioHolding();
		mhph.importFile(phs_);
		//mht.
		
		System.out.println("Finished PortfolioThreads-Thread after ["+(System.currentTimeMillis()-start)+"]ms");
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()-1);
		
	}

}
