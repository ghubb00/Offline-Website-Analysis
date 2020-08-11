package edu.odu.cs.cs350;

import java.util.*;

import java.io.*;


public class HTMLDocument {

	private double PageSize;
	private File Path; // where the HTML page is stored

	private Set<Image> allImages = new HashSet<Image>();
	private Set<StyleTag> allStyles = new HashSet<StyleTag>();
	private Set<ScriptTag> allScripts = new HashSet<ScriptTag>();
	private Set<AnchorTag> allLinks = new HashSet<AnchorTag>();
	private Set<DataFile> allDataFiles = new HashSet<DataFile>();

	/**
	 * constructor for a HTMLDocument containing all containers for tag types
	 *
	 * @param currentPath the specific path of the HTMLDocument page
	 *
	 */
	HTMLDocument(File currentPath) {
		this.Path = currentPath;
		PageSize = currentPath.length(); //size of page in bytes
	}
	
	/**
	 * returns the file path of the HTMLDocument
	 * 
	 * @return Path - the local path of the file
	 */
	
	public File getPath() {
		return Path;
	}
	
	/**
	 * returns the total number of local images
	 * 
	 * @return count - number of local images
	 */
	public int getTotalLocalImages() {
		int count = 0;
		for(Image i : allImages)
		{
			if(i.getImageType() == Locality.INTERNAL)
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * returns the total number of external images
	 * 
	 * @return count - number of external images
	 */
	public int getTotalExternalImages() {
		int count = 0;
		for(Image i : allImages)
		{
			if(i.getImageType() == Locality.EXTERNAL)
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * returns the total number of styleSheets
	 * 
	 * @return count - set.size()
	 */
	public int getTotalStylesheets() {
		int count = 0;
		count = allStyles.size();
		return count;
	}

	/**
	 * returns the total number of scripts
	 * 
	 * @return count - set.size()
	 */
	public int getTotalScripts() {
		int count = 0;
		count = allScripts.size();
		return count;
	}

	/**
	 * returns the total number of intrapage links
	 * 
	 * @return count - the number of intrapage links
	 */
	public int getTotalIntraPageLinks() {
		int count = 0;
		for(AnchorTag i : allLinks)
		{
			if(i.getType() == Locality.INTRAPAGE)
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * returns the total number of internal links
	 * 
	 * @return count - the number of internal links
	 */
	public int getTotalInternalLinks() {
		int count = 0;
		for(AnchorTag i : allLinks)
		{
			if(i.getType() == Locality.INTERNAL)
			{
				count++;
			}
		}
		return count;
		}

	/**
	 * returns the total number of external links
	 * 
	 * @return count - the number of external links
	 */
	public int getTotalExternalLinks() {
		int count = 0;
		for(AnchorTag i : allLinks)
		{
			if(i.getType() == Locality.EXTERNAL)
			{
				count++;
			}
		}
		return count;
	}
		
	/**
	 * returns the total size of page
	 * 
	 * @return PageSize
	 */
	public double getPageSize() {
		return PageSize;
	}
	/**
	 * returns the total size of all images by adding each size of Images in a set
	 * 
	 * @return imagesum
	 */
	public double getTotalImageSize() {
		double imagesum = 0;
		
		for (Image i : allImages)
		{
			imagesum += i.getImageSize();
		}
		return imagesum;
	}

	/**
	 * adds an image to the set allImages
	 * updates total images count;
	 *
	 * @param newImage the image to be added
	 *
	 */
	public void addImage(Image newImage) {
		allImages.add(newImage);
	}

	/**
	 * adds an stylesheet to the set allStyles
	 *
	 * @param newStyleSheet the stylesheet to be added
	 *
	 */
	public void addStyleSheet(StyleTag newStyleSheet) {
		allStyles.add(newStyleSheet);
	}

	/**
	 * adds an script to the set allStyles
	 *
	 * @param newScript the script to be added
	 *
	 */
	public void addScript(ScriptTag newScript) {
		allScripts.add(newScript);
	}

	/**
	 * adds an datafile to the set allDataFIles
	 *
	 * @param newDF the datafile to be added
	 *
	 */
	public void addDataFile(DataFile newDF) {
		allDataFiles.add(newDF);
	}

	/**
	 * adds an link to the set allLinks
	 *
	 * @param newLink the datafile to be added
	 *
	 */
	public void addLink(AnchorTag newLink) // 4.5.1 Link Analysis will be performed in this method
	{
		allLinks.add(newLink);
	}

	/**
	 * Generates a String containing the page's path and page size
	 * 
	 * @return String
	 */
	
	@Override
	public String toString() {
		return Path.toString();
	}
	
	/**
	 * Iterator enables you to cycle through a collection of images,
	 * 
	 * @return allImages iterator
	 */
	public Iterator<Image> getImageIterator()
	{
		return allImages.iterator();
	}
	/**
	 * Iterator enables you to cycle through a collection of style tags,
	 * 
	 * @return allStyles iterator
	 */
	public Iterator<StyleTag> getStyleIterator()
	{
		return allStyles.iterator();
	}
	/**
	 * Iterator enables you to cycle through a collection of script tags,
	 * 
	 * @return allScrpts iterator
	 */
	public Iterator<ScriptTag> getScriptIterator()
	{
		return allScripts.iterator();
	}
	/**
	 * Iterator enables you to cycle through a collection of link tags,
	 * 
	 * @return allLinks iterator
	 */
	public Iterator<AnchorTag> getLinkIterator()
	{
		return allLinks.iterator();
	}
	/**
	 * Iterator enables you to cycle through a collection of data file tags,
	 * 
	 * @return allDatafile iterator
	 */
	public Iterator<DataFile> getDataFileIterator()
	{
		return allDataFiles.iterator();
	}


}
