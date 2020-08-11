package edu.odu.cs.cs350;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Scanner;  

public class AnalysisDriver {
	
	//private Website web;
	//private WebsiteScanner scanner = new WebsiteScanner(web);
	private static WebsiteScanner webscanner;
	private static Scanner scan = new Scanner(System.in);
	
	//added this method for temp data
	public static File folder = new File("/Users/gregoryhubbard/Documents");
	
	static String temp = "";
	
	public static void main(String[] args) throws Throwable {
//		Scanner myObj = new Scanner(System.in);
//
//		System.out.println("Please enter the local path of the website");
//		System.out.println("example:  /Users/gregoryhubbard/Documents");
//		String localPath = myObj.nextLine();
//		File folder = new File(localPath);
//        
//		//@TODO
//		//THIS LOGIC DOES NOT WORK RIGHT NOW: 
//		if(folder.isFile())
//		{
//			 System.out.println("A path to a file was input, please input a path to the local website folder"); 
//		}
//		
//		else if(folder.getAbsolutePath() != null)
//        {
//
//    	System.out.println("files under the local website folder " + folder.getAbsolutePath());
//    	//creates a webscanner object with the folder location param
//    	webscanner = new WebsiteScanner(folder);
//
//        }
//        
//            else
//            { 
//                System.out.println("Folder not found."); 
//                System.out.println("Make sure website is stored locally and check directory."); 
//
//            } 
//        } 
		
		start();
	
}
	
	public static void start() throws Throwable {
		System.out.println("Please enter the local path of the website");
		System.out.println("example:  /Users/gregoryhubbard/Documents");
		String path = scan.nextLine();
		
		try {
			File file = new File(path);
			if (file.isFile()) {
				System.out.println("A path to a file was input, please input a path to the local website folder. \n");
				start();
			} else {
				System.out.println("files under the local website folder " + file.getAbsolutePath());
				webscanner = new WebsiteScanner(file);
			}
		} catch (NullPointerException | MalformedURLException e) {
			System.out.println("That path does not exist. Please try again. \n");
			start();
		} finally 
		{
			//scan.close();
		}
		
		System.out.println("all Paths Scanned by WebScanner");
		
		/**
		 * Asking user for destination for output files
		 * will outcomment 
		 */
		
		System.out.println("Please enter the local path of the out put files");
		System.out.println("example:  /Users/gregoryhubbard/Documents/");
		System.out.println("NOTE: Include backslash and forward slash at the end.");
		String outPath = scan.nextLine();
		
		ReportWriter reports = new ReportWriter(webscanner.getWebsiteObject(), outPath);
		reports.createTextFile();
		reports.createExcel();
		reports.createJSON(); //Work in progress
		System.out.println("Reports have been created");
	}
	
	
	/** 
	 * Notifies user via CLI that the process has finished. Also prints out
	 * the files that were produced. (1591)
	 */
	public void notifyEnd() {
	}
	
	/**
	 * @return String The name of the output file that is formatted by this method. (1611)
	 */
	public String outputNamer() {
		return null;
	}
}