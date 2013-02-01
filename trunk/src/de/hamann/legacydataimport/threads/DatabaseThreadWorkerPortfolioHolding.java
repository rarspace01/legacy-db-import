package de.hamann.legacydataimport.threads;

import java.util.List;

import de.hamann.legacydataimport.data.DataHandlerPortfolioHolding;
import de.hamann.legacydataimport.model.PortfolioHolding;

public class DatabaseThreadWorkerPortfolioHolding implements Runnable {

	private DatabaseThreadHandler dtm_=null;
	private List<PortfolioHolding> phs_;
	
	public DatabaseThreadWorkerPortfolioHolding(DatabaseThreadHandler databaseThreadphs,
			List<PortfolioHolding> phs) {
		dtm_=databaseThreadphs;
		this.phs_=phs;
	}

	@Override
	public void run() {
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()+1);
		
		long start=System.currentTimeMillis();
		
		System.out.println("[ThreadM] got Stocks: "+ phs_.size());
		
		new DataHandlerPortfolioHolding().savephs(phs_);
		
		System.out.println("Finished Stocks-Thread after ["+(System.currentTimeMillis()-start)+"]ms - Speed:"+((System.currentTimeMillis()-start)/(phs_.size()))+"ms/statement");
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()-1);
		
	}

}
