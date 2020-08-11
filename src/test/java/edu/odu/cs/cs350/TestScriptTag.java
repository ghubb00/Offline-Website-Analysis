package edu.odu.cs.cs350;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;


/**
* 1 - Does this piece of code perform the operations
*     it was designed to perform?
*
* 2 - Does this piece of code do something it was not
*     designed to perform?
*
* 1 Test per mutator
*/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestScriptTag {
	//Declaring Locality variables
	Locality local1 = Locality.EXTERNAL;
	Locality local2 = Locality.INTERNAL;
	
	//Declaring Path variables 
	File path1 = new File("/home/tkennedy/directory/that/does/not/exist/", 
			"/home/tkennedy/directory/that/does/not/exist/something/page2.htm");
	
	File path2 = new File("/home/pfracz/directory/that/does/not/exist/", 
			"/home/pfracz/directory/that/does/not/exist/something/page54.htm");
	
	File path3 = new File("/home/tdurden/directory/that/does/not/exist/",
			"/home/tdurden/directory/that/does/not/exist/something/page77.htm");
	
	//Declaring ScriptTags variables
	ScriptTag withoutPage;
	ScriptTag withPage;
	
	//Declaring HTMLDocument variable
	HTMLDocument page = new HTMLDocument(path3);
	

	@Before
	public void setUp() throws Exception {
		withoutPage = new ScriptTag(local1, path1);
		withPage = new ScriptTag(local2, path2, page);
	}

	@Test
	public final void testScriptTagLocalityPath() {
		//test that attributes were updated
		assertThat(withoutPage.getPath(), is(path1));
		assertThat(withoutPage.getType(), is(local1));
		
		assertFalse(withoutPage.iterator().hasNext()); //should not have a reference to a page
		
		//test toString
		assertThat(withoutPage.toString(), containsString(path1.toString()));
		assertThat(withoutPage.toString(), containsString(local1.name()));
	}

	@Test
	public final void testScriptTagLocalityPathHTMLDocument() {
		//test that path and type were updated
		assertThat(withPage.getPath(), is(path2));
		assertThat(withPage.getType(), is(local2));
		assertThat(withPage.iterator().hasNext(),is(true));
		
		//test toString
		assertThat(withPage.toString(), containsString(path2.toString()));
		assertThat(withPage.toString(), containsString(local2.name()));
	}

	@Test
	public final void testSetType() {
		//setting new type
		withoutPage.setType(local2);
		
		//test that attributes were updated
		assertThat(withoutPage.getPath(), is(path1));
		assertThat(withoutPage.getType(), is(local2));
		assertFalse(withoutPage.iterator().hasNext()); //should not have a reference to a page
		
		//test toString
		assertThat(withoutPage.toString(), containsString(path1.toString()));
		assertThat(withoutPage.toString(), containsString(local2.name()));
	}

	@Test
	public final void testSetPath() {
		withPage.setPath(path1);
		
		assertThat(withPage.getPath(), is(path1));
		assertThat(withPage.getType(), is(local2));
		assertThat(withPage.iterator().hasNext(),is(true));
		
		//test toString
		assertThat(withPage.toString(), containsString(path1.toString()));
		assertThat(withPage.toString(), containsString(local2.name()));
	}

	@Test
	public final void testAddPage() {
		withoutPage.addPage(page);
		
		assertThat(withoutPage.getPath(), is(path1));
		assertThat(withoutPage.getType(), is(local1));
		assertThat(withoutPage.iterator().hasNext(),is(true));
		
		//test toString
		assertThat(withoutPage.toString(), containsString(path1.toString()));
		assertThat(withoutPage.toString(), containsString(local1.name()));
	}

}
