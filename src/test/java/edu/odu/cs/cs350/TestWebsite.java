package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.Before;
import org.junit.FixMethodOrder;

import java.io.File;
import java.net.URL;
import java.util.Iterator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestWebsite {
	
	//Declaring Path variables 
	File path1 = new File("/home/tkennedy/directory/that/does/not/exist/something/page2.htm",
			"/home/tkennedy/directory/that/does/not/exist/");
		
	File path2 = new File ("/home/pfracz/directory/that/does/not/exist/something/page54.htm",
			"/home/pfracz/directory/that/does/not/exist/");
	
	File path3 = new File("/home/tdurden/directory/that/does/not/exist/something/page77.htm",
			"/home/tdurden/directory/that/does/not/exist/");
	
	//Declaring HTMLDocument variable
	HTMLDocument page = new HTMLDocument(path3);
	
	//Declaring Website variable
	Website website;
	URL siteRoot1;
	
	@Before
	public void setUp() throws Exception {
		try {
			siteRoot1 = new URL("https://www.cs.odu.edu/~tkennedy/cs350/sum20/");
			website = new Website(path1, siteRoot1);
		} catch(Exception ex) {
			
		}
		
	}

	@Test
	public final void testConstructor(){
		//test that attributes were updated
		assertThat(website.getPath(), is(path1));
		assertThat(website.getSiteRootIterator().next(), is(siteRoot1));
	    assertThat(website.getNumberOfPages(), is(0));
		
		assertFalse(website.getPageIterator().hasNext()); //should not have a page
		
		//test toString
		assertThat(website.toString(), containsString(path1.toString()));
		assertThat(website.toString(), containsString("0"));
	}
	
	@Test
	public final void testSetPath() {
		//setting new path
		website.setPath(path2);
		
		//test that attributes were updated
		assertThat(website.getPath(), is(path2));
		assertThat(website.getSiteRootIterator().next(), is(siteRoot1));
		assertThat(website.getNumberOfPages(), is(0));
		
		assertFalse(website.getPageIterator().hasNext()); //should not have a page
				
		//test toString
		assertThat(website.toString(), containsString(path2.toString()));
		assertThat(website.toString(), containsString("0"));
	}
	
	@Test
	public final void testAddPage() {
		//adding page to website
		website.addPage(page);
		
		//test that attributes were updated
		assertThat(website.getPath(), is(path1));
		assertThat(website.getSiteRootIterator().next(), is(siteRoot1));
		assertThat(website.getPageIterator().next(), is (page));
		assertThat(website.getNumberOfPages(), is(1));
						
		//test toString
		assertThat(website.toString(), containsString(path1.toString()));
		assertThat(website.toString(), containsString("1"));
	}
	
	@Test
	public final void testAddSiteRoot() throws Exception {
		//adding another site root to website
		URL siteRoot2 = new URL("http://cs.odu.edu/~tkennedy/cs350/sum20/");
		website.addSiteRoot(siteRoot2);
		
		//test that attributes were updated
		assertThat(website.getPath(), is(path1));
		assertThat(website.getNumberOfPages(), is(0));
		
		Iterator<URL> it = website.getSiteRootIterator();
		assertThat(it.next(), is(siteRoot1)); //added during creation of website
		assertThat(it.next(), is(siteRoot2)); //added in this test
		
		assertFalse(website.getPageIterator().hasNext()); //should not have a page
		
		//test toString
		assertThat(website.toString(), containsString(path1.toString()));
		assertThat(website.toString(), containsString("0"));
	}
}

