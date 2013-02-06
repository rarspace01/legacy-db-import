package de.hamann.legacydataimport.threads;

import java.util.List;

import de.hamann.legacydataimport.data.DataHandlerStocks;
import de.hamann.legacydataimport.model.Stock;

public class DatabaseThreadWorkerStocks implements Runnable {

	private DatabaseThreadHandler dtm_=null;
	private List<Stock> stocks;
	
	public DatabaseThreadWorkerStocks(DatabaseThreadHandler databaseThreadStocks,
			List<Stock> stocks) {
		dtm_=databaseThreadStocks;
		this.stocks=stocks;
	}

	@Override
	public void run() {
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()+1);
		
		long start=System.currentTimeMillis();
		
		System.out.println("[ThreadM] got Stocks: "+ stocks.size());
		
		new DataHandlerStocks().saveStocks(stocks,"");
		
		System.out.println("Finished Stocks-Thread after ["+(System.currentTimeMillis()-start)+"]ms - Speed:"+((System.currentTimeMillis()-start)/(stocks.size()))+"ms/statement");
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()-1);
		
	}

}
