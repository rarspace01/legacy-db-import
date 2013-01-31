package de.hamann.legacydataimport;

import java.util.ArrayList;
import java.util.List;

import de.hamann.legacydataimport.model.Manager;
import de.hamann.legacydataimport.model.ModelHandlerManager;
import de.hamann.legacydataimport.model.ModelHandlerPortfolioHolding;
import de.hamann.legacydataimport.model.ModelHandlerStocks;
import de.hamann.legacydataimport.model.PortfolioHolding;
import de.hamann.legacydataimport.model.Stock;

public class Tests {

	public static void main(String[] args) {
		
		Tests mTest=new Tests();
		mTest.phTest();
		//mTest.managerTest();
		//mTest.stocksTest();
	}
	
	private void phTest(){
		List<PortfolioHolding> tmpPhs=new ArrayList<PortfolioHolding>();
		
		tmpPhs=new ModelHandlerPortfolioHolding().importFile("C:/Users/denis/Documents/db/13F_History_s34/s343.0606");
		
		System.out.println("size: "+tmpPhs.size());
		
		for(int i=0;i<tmpPhs.size();i++){
			tmpPhs.get(i).printAll();
		}
	}
	
	private void stocksTest() {
		List<Stock> tmpStocks=new ArrayList<Stock>();
		
		tmpStocks=new ModelHandlerStocks().importFile("C:/Users/denis/Documents/db/13F_History_s34/s342.0006");
		
		for(int i=0;i<tmpStocks.size();i++){
			tmpStocks.get(i).printAll();
		}
	}

	public void managerTest(){
		List<Manager> tmpManager=new ArrayList<Manager>();
		
		tmpManager=new ModelHandlerManager().importFile("C:/Users/denis/Documents/db/13F_History_s34/s341.7906");
		
		for(int i=0;i<tmpManager.size();i++){
			tmpManager.get(i).printAll();
		}
	}
	
}
