package de.hamann.legacydataimport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataManagerFileIndex {

	private String rootpath_;
	private String currentFile_;
	private List<String> fileList_ = new ArrayList<String>();
	private int currentIndex=0;
	
	public DataManagerFileIndex(String rootpath) {
		
	 File folder = new File(rootpath);
	 File[] listOfFiles = folder.listFiles();
	 
	 
	 for(int i=0;i<listOfFiles.length;i++){
		 fileList_.add(listOfFiles[i].getAbsolutePath());
	 }
		
	}
	
	public String getNextFile(){
		String returnString=null;
		if(currentIndex<fileList_.size()){
			returnString=fileList_.get(currentIndex);
			currentIndex++;
		}
		return returnString;
	}

	public int getFilesCount(){
		return fileList_.size();
	}
	
	public String getCurrentFile_() {
		return currentFile_;
	}
	
	
	
}
