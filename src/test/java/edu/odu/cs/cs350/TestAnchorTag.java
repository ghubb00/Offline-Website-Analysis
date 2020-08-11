package edu.odu.cs.cs350;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.Before;
import org.junit.FixMethodOrder;

import static org.hamcrest.CoreMatchers.*;

/**
* 1 - Does this piece of code perform the operations
*     it was designed to perform?
*
* 2 - Does this piece of code do something it was not
*     designed to perform?
*
* 1 Test per mutator
* mutators:
* AnchorTag(Locality type, URL hyperLinks)
* setType(Locality newType)
* setPath(Path newPath)
* 
* accessors:
* toString()
* getType()
* getPath()
*/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAnchorTag {

	AnchorTag testAnchor1;
	
	//Declaring Path variables 
	File path1 = new File("/home/tkennedy/directory/that/does/not/exist/", 
			"/home/tkennedy/directory/that/does/not/exist/something/page2.htm");
	
	File path2 = new File("/home/ghubbard/directory/that/does/not/exist/", 
			"/home/ghubbard/directory/that/does/not/exist/something/page54.htm");
	
	//Declaring Locality variables
	Locality l1 = Locality.EXTERNAL;
	Locality l2 = Locality.INTERNAL;
	
	URL hyperTextLink;

	@Before
	public void setUp()
	{
		try {
		   hyperTextLink = new URL("https://www.cs.odu.edu/~tkennedy/cs350/sum20/");
		   testAnchor1 = new AnchorTag(l1, path1, hyperTextLink);
		} catch(Exception ex) {
			
		}
	}
	
	@Test
	public void testConstructor()
	{
		//test attributes are accurate
		assertThat(testAnchor1.getPath(), is(path1));
		assertThat(testAnchor1.getType(), is(l1));
		assertThat(testAnchor1.getHyperTextLink(), is(hyperTextLink));
		
		//test toString
		assertThat(testAnchor1.toString(), containsString(path1.toString()));
		assertThat(testAnchor1.toString(), containsString(l1.name()));
		assertThat(testAnchor1.toString(), containsString(hyperTextLink.toString()));
	}
	
	@Test
	public void testSetType()
	{
		//setting new type
		testAnchor1.setType(l2);
		
		//test attributes are accurate
		assertThat(testAnchor1.getPath(), is(path1));
		assertThat(testAnchor1.getType(), is(l2));
		assertThat(testAnchor1.getHyperTextLink(), is(hyperTextLink));
		
		//test toString
		assertThat(testAnchor1.toString(), containsString(path1.toString()));
		assertThat(testAnchor1.toString(), containsString(l2.name()));
		assertThat(testAnchor1.toString(), containsString(hyperTextLink.toString()));
	}
	
	@Test
	public void testSetPath()
	{
		//setting new path
		testAnchor1.setPath(path2);
		
		//test attributes are accurate
		assertThat(testAnchor1.getPath(), is(path2));
		assertThat(testAnchor1.getType(), is(l1));
		assertThat(testAnchor1.getHyperTextLink(), is(hyperTextLink));
		
		//test toString
		assertThat(testAnchor1.toString(), containsString(path2.toString()));
		assertThat(testAnchor1.toString(), containsString(l1.name()));
		assertThat(testAnchor1.toString(), containsString(hyperTextLink.toString()));
	}
	@Test
	public void TestSetHyperTextLink() throws Exception
	{
		//Change HyperText Link
		URL hyperTextLink2 = new URL("http://cs.odu.edu/~tkennedy/cs350/sum20/");
		testAnchor1.setHyperTextLink(hyperTextLink2);
		
		//test attributes are accurate
		assertThat(testAnchor1.getPath(), is(path1));
		assertThat(testAnchor1.getType(), is(l1));
		assertThat(testAnchor1.getHyperTextLink(), is(hyperTextLink2));
		
		//test toString
		assertThat(testAnchor1.toString(), containsString(path1.toString()));
		assertThat(testAnchor1.toString(), containsString(l1.name()));
		assertThat(testAnchor1.toString(), containsString(hyperTextLink2.toString()));
	}

}
