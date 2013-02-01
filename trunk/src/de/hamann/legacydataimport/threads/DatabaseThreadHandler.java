package de.hamann.legacydataimport.threads;

import java.util.List;

import de.hamann.legacydataimport.Config;
import de.hamann.legacydataimport.model.Manager;
import de.hamann.legacydataimport.model.PortfolioHolding;
import de.hamann.legacydataimport.model.Stock;
import de.hamann.legacydataimport.model.Transactions;

public class DatabaseThreadHandler {

	private int iWorkingThreads=0;
	private static DatabaseThreadHandler uniqueInstance_=null;
	
	public static DatabaseThreadHandler getInstance(){
		if(uniqueInstance_ == null){
			uniqueInstance_=new DatabaseThreadHandler();
		}
		return uniqueInstance_;
	}
	
	public void addManagers(List<Manager> manager){
		try {
			Thread.sleep(25);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(getiWorkingThreads()>=Config.MAX_THREADS){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int iCur=getiWorkingThreads();
		Thread t1=new Thread(new DatabaseThreadWorkerManager(this, manager));
		t1.start();
		System.out.println("Thread started ["+(iCur+1)+"/"+Config.MAX_THREADS+"]");
	}
	
	public void addStocks(List<Stock> stocks){
		try {
			Thread.sleep(25);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(getiWorkingThreads()>Config.MAX_THREADS){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int iCur=getiWorkingThreads();
		Thread t1=new Thread(new DatabaseThreadWorkerStocks(this, stocks));
		t1.start();
		System.out.println("Thread started ["+(iCur+1)+"/"+Config.MAX_THREADS+"]");
	}

	public void addphs(List<PortfolioHolding> phs){
		try {
			Thread.sleep(25);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(getiWorkingThreads()>Config.MAX_THREADS){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int iCur=getiWorkingThreads();
		Thread t1=new Thread(new DatabaseThreadWorkerPortfolioHolding(this, phs));
		t1.start();
		System.out.println("Thread started ["+(iCur+1)+"/"+Config.MAX_THREADS+"]");
	}
	
	public void addTransactions(List<Transactions> tList){
		try {
			Thread.sleep(25);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(getiWorkingThreads()>Config.MAX_THREADS){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int iCur=getiWorkingThreads();
		Thread t1=new Thread(new DatabaseThreadWorkerTransactions(this, tList));
		t1.start();
		System.out.println("Thread started ["+(iCur+1)+"/"+Config.MAX_THREADS+"]");
	}
	
	public int getiWorkingThreads() {
		return iWorkingThreads;
	}

	public void setiWorkingThreads(int iWorkingThreads) {
		this.iWorkingThreads = iWorkingThreads;
	}
	
}
