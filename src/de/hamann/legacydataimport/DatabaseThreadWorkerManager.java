package de.hamann.legacydataimport;

import java.util.List;

import de.hamann.legacydataimport.data.DataHandlerManager;
import de.hamann.legacydataimport.model.Manager;

public class DatabaseThreadWorkerManager implements Runnable {

	private DatabaseThreadHandler dtm_=null;
	private List<Manager> manager;
	
	public DatabaseThreadWorkerManager(DatabaseThreadHandler databaseThreadManager,
			List<Manager> manager) {
		dtm_=databaseThreadManager;
		this.manager=manager;
	}

	@Override
	public void run() {
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()+1);
		
		long start=System.currentTimeMillis();
		
		System.out.println("[ThreadM] got Managers: "+ manager.size());
		
		new DataHandlerManager().saveManagers(manager);
		
		System.out.println("Finished Manager-Thread after ["+(System.currentTimeMillis()-start)+"]ms - Speed:"+((System.currentTimeMillis()-start)/(manager.size()))+"ms/statement");
		
		if(dtm_!=null)
		dtm_.setiWorkingThreads(dtm_.getiWorkingThreads()-1);
		
	}

}
