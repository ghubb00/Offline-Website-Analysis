package edu.odu.cs.cs350;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPageScanner {
	File websiteFolder;
	Website website;
	
	HTMLDocument htmlDoc;
	File htmlFile;
	
	File brokenFile;
	
	PageScanner pScanner;
	
	@Before
	public void setup()
	{
		websiteFolder = new File("\\avocado6\\oduAdmissionWebsite\\www.odu.edu");
		try {
			website = new Website(websiteFolder, websiteFolder.toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		brokenFile = new File("\\Broken\\File\\Does\\Not\\Exist");
	}
	
	@Test
	public void testParse() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testFindImages() throws Throwable 
	{
		htmlFile = new File("\\avocado6\\oduAdmissionWebsite\\www.odu.edu\\admission.html");
		htmlDoc = new HTMLDocument(htmlFile);
		
		Document doc = Jsoup.parse(htmlDoc.getPath(), null);
		Elements images = doc.getElementsByTag("img");
		
		pScanner = new PageScanner(website, htmlDoc);
		pScanner.Parse();
		
		// Check manually for number of occurrences ensure hashSet has that many images
		assertThat(htmlDoc.getTotalLocalImages(), is(4));
		
	}
	
	@Test
	public void testFindStylesheets() 
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindScripts() 
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindLinks() 
	{
		
		// /avocado6/oduAdmissionWebsite/www.odu.edu/index.html
		fail("Not yet implemented");
	}
	
	@Test
	public void testFindDataFile() 
	{
		fail("Not yet implemented");
	}
	
}
