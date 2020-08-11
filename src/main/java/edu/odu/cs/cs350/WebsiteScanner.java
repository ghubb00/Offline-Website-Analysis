package edu.odu.cs.cs350;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class WebsiteScanner {

	private Website web;
	//private PageScanner scanner = new PageScanner();
	static String temp = "";

	private WebsiteScanner() {
		super();
	}

	public WebsiteScanner(File folder) throws Throwable {
		super();
		URL dummyURL = new URL("https://www.blackboard.odu.edu/ultra/institution-page"); //will be changed later
		web = new Website(folder, dummyURL);
		walk(folder);
		
		if (sizeChecker()) 
		{ //website should only be parsed through if it fits the size criteria.
			//pageParser(web,);
		}
		
		Iterator<HTMLDocument> iterator = web.getPageIterator();
		while(iterator.hasNext())
		{
		PageScanner htmlscan = new PageScanner(web, iterator.next());
		htmlscan.Parse();
		}
		System.out.println();
	}
	
	public Website getWebsiteObject() {
		return web;
	}
	

	/**
	 * Checks the amount of HTML pages the website has
	 * @return boolean True means that the website fits the criteria of 1-1000 pages. False
	 * means that the website does not fit the criteria.
	 */
	public boolean sizeChecker() 
	{
		int size = web.getNumberOfPages();
		if (size > 1000 || size < 1) {
			System.out.println("Invalid site. Number of pages must be between 1-1000.");
			return false;
		}
		
		return true;
		
		//this loop below proves that the HTML documents are actually being created by reprinted the HTMLDoc list
		/**
		 * 
		Iterator i = (Iterator) web.getPageIterator();
		while(i.hasNext())
		{
			//i.next()
			System.out.println(i.next().toString());
		}
		sizeChecker();
		*/

	}
	
	/**
	 * @return boolean True if nested path is broken/doesn't work
	 * should be called by website scanner method privately
	 */
	private boolean PathisBroken(File folder) {
		if(folder.exists() == false)
		{
		return false;
		}
		return true;
	}
/**
 * "walks" the passed folder to identify HTML and HTM files.  
 * Creates a new HTMLDocument object for each .html page based on File path
 * 
 */
	private void walk(File folder) 
	{
		// loop for all files inside a folder (website)
		for (final File fileEntry : folder.listFiles()) {

			// loop in case of nested files
			if (fileEntry.isDirectory()) 
			{
				walk(fileEntry);
			}

			// lists files inside the directory
			else {
				if (fileEntry.isFile()) {
					temp = fileEntry.getName();
					// looks for ".html" files
					if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("html"))
					{
						//test print
						System.out.println("File= " + folder.getAbsolutePath() + "/" + fileEntry.getName());
						File addFile = new File(folder.getAbsolutePath() + "/" + fileEntry.getName());
						HTMLDocument addDoc = new HTMLDocument(addFile);
						//adds HTML doc to collection of HTML pages inside Website.java
						web.addPage(addDoc);	
					}
					
					// looks for ".htm" files
					if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("htm"))
					{
						//test print
						System.out.println("File= " + folder.getAbsolutePath() + "/" + fileEntry.getName());
						File addFile = new File(folder.getAbsolutePath() + "/" + fileEntry.getName());
						HTMLDocument addDoc = new HTMLDocument(addFile);
						//adds HTM doc to collection of HTML pages inside Website.java
						web.addPage(addDoc);
					}
				}

			}
		}
	}
	
	/**
	 * pageParser() calls the PageScanner Parse(Website, HTMLDocument) for each HTMLDocument associated
	 * with the website.
	 *
	private void pageParser() {
		while(web.getPageIterator().hasNext()) {
			HTMLDocument doc = web.getPageIterator().next();
			try {
				PageScanner(web, doc);
			} catch (IOException e) {
				System.out.println("There was an issue with file at: " + doc.getPath().getAbsolutePath() + "\n");
			}	
		}
	}
	*/


}
