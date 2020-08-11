package edu.odu.cs.cs350;
import java.io.*; 

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Website {
	
	//private Path path; //path to the local offline website copy
	private File folder; //path to local offline website copy 
	private List<HTMLDocument> pages = new ArrayList<HTMLDocument>(); //list of all the html pages associated with the website
	private List<URL> siteRoots = new ArrayList<URL>();
	
	/**
	 * Default constructor not needed
	 * Therefore making it private
	 */
	@SuppressWarnings("unused")
	private Website() {
	}
	
	/**
	 * constructor for a "website" which contains all the HTML files
	 *
	 * @param Path - path to the local offline copy of the website
	 * @param URL - site root for the website
	 */
	
	public Website(File folder, URL siteRoot) 
	{
		
		this.folder = folder; //the current folder where the local website is stored
		siteRoots.add(siteRoot); //the URL of the website stored locally 
		
	}
	
	
	/**
     * Retrieve the path for website
     */
	public File getPath() {
		return folder;
	}

	/**
	 * Set a new for the offline website dir
	 * @param path of the new dir
	 */
	public void setPath(File newfolder) {
		this.folder = newfolder;
	}

	/**
     * Retrieve the iterator for HTMLDocuments list
     */
	public Iterator<HTMLDocument> getPageIterator() {
		return pages.iterator();
	}
	
	/**
	 * Add a new HTML page to HTMLDocument List
	 * @param page
	 */
	public void addPage(HTMLDocument page) {
		this.pages.add(page);
	}
	
	/**
     * Retrieve the number of HTMLDocuments in the Website
     */
	public int getNumberOfPages() {
		return pages.size();
	}
	
	/**
	 * Retrieves the iterartor for Site Root list
	 * @return
	 */
	public Iterator<URL> getSiteRootIterator() {
		return siteRoots.iterator();
	}
	
	/**
	 * Add a new Site page to HTMLDocument List
	 * @param page
	 */
	public void addSiteRoot(URL siteRoot) {
		this.siteRoots.add(siteRoot);
	}
	
	/**
	 * prints the website location in dir and how many HTML pages it has
	 */
	@Override
	public String toString() {
		return "Website located at " + folder.toString() 
		+ " has " + pages.size() + " HTML pages";
	}
	
	/**
	 * Checks the amount of HTML pages the website has
	 * 
	 * @return boolean True means that the website fits the criteria of 1-1000
	 *         pages. False means that the website does not fit the criteria.
	 */
	public boolean sizeChecker() {
		int size = getNumberOfPages();
		if (size > 1000 || size < 1) {
			System.out.println("Invalid site. Number of pages must be between 1-1000.");
			return false;
		}
		return true;
	}
}
