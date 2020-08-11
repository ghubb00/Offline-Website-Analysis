package edu.odu.cs.cs350;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
* 1 - Does this piece of code perform the operations
*     it was designed to perform?
*
* 2 - Does this piece of code do something it was not
*     designed to perform?
*
* 1 Test per mutator
* 
* MUTATORS:
* HTMLDocument(Path currentPath)
* addImage(Image newImage)
* addStyleSheet(StyleTag newStyleSheet)
* addScript(ScriptTag newScript)
* addDataFile(DataFile newDF)
* addLink(AnchorTag newLink)
* 
* ACCESSORS:
* getTotalLocalImages()
* getTotalExternalImages()
* getTotalStylesheets()
* getTotalScripts()
* getTotalImagesize()
* getTotalIntraPageLinks()
* getTotalInternalLinks()
* getTotalExternalLinks()
* getPageSize()
* 
* getImageIterator()
* getStyleIterator()
* getScriptIterator()
* getLinkIterator()
* getDataFileIterator()
*/

public class TestHTMLDocument {
	HTMLDocument D1;
	
	File path = new File("/home/tkennedy/directory/that/does/not/exist/something/page2.htm",
			"/home/tkennedy/directory/that/does/not/exist/");
	
	@Before
	public void setUp() 
	{
		D1 = new HTMLDocument(path);
	}
	
	@Test
	public void testConstructor()
	{
		//Iterator<>
		
		assertThat(D1.getTotalLocalImages(), is(0));
		assertThat(D1.getTotalExternalImages(), is(0));
		assertThat(D1.getTotalStylesheets(), is(0));
		assertThat(D1.getTotalScripts(), is(0));
		assertThat(D1.getTotalIntraPageLinks(), is(0));
		assertThat(D1.getTotalInternalLinks(), is(0));
		assertThat(D1.getTotalExternalLinks(), is(0));
		
		assertThat(D1.getPageSize(), is(not(0)));
		assertThat(D1.getTotalImageSize(), is(0));
		
		assertThat(D1.getImageIterator().hasNext(), is(false));
		assertThat(D1.getStyleIterator().hasNext(), is(false));
		assertThat(D1.getScriptIterator().hasNext(), is(false));
		assertThat(D1.getLinkIterator().hasNext(), is(false));
		assertThat(D1.getDataFileIterator().hasNext(), is(false));

		
	}
	
	@Test
	public void testAddLocalImage()
	{
		Image newImage = new Image();
		D1.addImage(newImage); 
		assertThat(D1.getTotalLocalImages(), is(1));
		assertThat(D1.getTotalExternalImages(), is(0));
		assertThat(D1.getTotalStylesheets(), is(0));
		assertThat(D1.getTotalScripts(), is(0));
		assertThat(D1.getTotalImageSize(), is(0));
		assertThat(D1.getTotalIntraPageLinks(), is(0));
		assertThat(D1.getTotalInternalLinks(), is(0));
		assertThat(D1.getTotalExternalLinks(), is(0));
		
		assertThat(D1.getPageSize(), is(not(0)));
		assertThat(D1.getTotalImageSize(), is(not(0)));
		
		assertThat(D1.getImageIterator().hasNext(), is(true));
		assertThat(D1.getStyleIterator().hasNext(), is(false));
		assertThat(D1.getScriptIterator().hasNext(), is(false));
		assertThat(D1.getLinkIterator().hasNext(), is(false));
		assertThat(D1.getDataFileIterator().hasNext(), is(false));
	}
	
	@Test
	public void testAddExternalImage()
	{
		Image newExternalImage = new Image();
		D1.addImage(newExternalImage); //external image
		assertThat(D1.getTotalLocalImages(), is(0));
		assertThat(D1.getTotalExternalImages(), is(1));
		assertThat(D1.getTotalStylesheets(), is(0));
		assertThat(D1.getTotalScripts(), is(0));
		assertThat(D1.getTotalIntraPageLinks(), is(0));
		assertThat(D1.getTotalInternalLinks(), is(0));
		assertThat(D1.getTotalExternalLinks(), is(0));
		
		assertThat(D1.getPageSize(), is(not(0)));
		assertThat(D1.getTotalImageSize(), is(not(0)));

		
		assertThat(D1.getImageIterator().hasNext(), is(true));
		assertThat(D1.getStyleIterator().hasNext(), is(false));
		assertThat(D1.getScriptIterator().hasNext(), is(false));
		assertThat(D1.getLinkIterator().hasNext(), is(false));
		assertThat(D1.getDataFileIterator().hasNext(), is(false));
	}
	
	@Test
	public void testAddStyleSheet()
	{
		StyleTag newStyleSheet = new StyleTag(Locality.INTERNAL,path);
		D1.addStyleSheet(newStyleSheet);
		assertThat(D1.getTotalLocalImages(), is(0));
		assertThat(D1.getTotalExternalImages(), is(0));
		assertThat(D1.getTotalStylesheets(), is(1));
		assertThat(D1.getTotalScripts(), is(0));
		assertThat(D1.getTotalIntraPageLinks(), is(0));
		assertThat(D1.getTotalInternalLinks(), is(0));
		assertThat(D1.getTotalExternalLinks(), is(0));
		
		assertThat(D1.getPageSize(), is(not(0)));
		assertThat(D1.getTotalImageSize(), is(0));

		
		assertThat(D1.getImageIterator().hasNext(), is(false));
		assertThat(D1.getStyleIterator().hasNext(), is(true));
		assertThat(D1.getScriptIterator().hasNext(), is(false));
		assertThat(D1.getLinkIterator().hasNext(), is(false));
		assertThat(D1.getDataFileIterator().hasNext(), is(false));
	}
	
	@Test
	public void testAddScript()
	{
		ScriptTag newScript = new ScriptTag(Locality.INTERNAL,path);
		D1.addScript(newScript);
		assertThat(D1.getTotalLocalImages(), is(0));
		assertThat(D1.getTotalExternalImages(), is(0));
		assertThat(D1.getTotalStylesheets(), is(0));
		assertThat(D1.getTotalScripts(), is(1));
		assertThat(D1.getTotalIntraPageLinks(), is(0));
		assertThat(D1.getTotalInternalLinks(), is(0));
		assertThat(D1.getTotalExternalLinks(), is(0));
		
		assertThat(D1.getPageSize(), is(not(0)));
		assertThat(D1.getTotalImageSize(), is(0));


		assertThat(D1.getImageIterator().hasNext(), is(false));
		assertThat(D1.getStyleIterator().hasNext(), is(false));
		assertThat(D1.getScriptIterator().hasNext(), is(false));
		assertThat(D1.getLinkIterator().hasNext(), is(false));
		assertThat(D1.getDataFileIterator().hasNext(), is(false));
	}
	
	@Test
	public void testAddDataFile()
	{
		DataFile newData = new DataFile(DataType.VIDEO,500,path);
		D1.addDataFile(newData);
		assertThat(D1.getTotalLocalImages(), is(0));
		assertThat(D1.getTotalExternalImages(), is(0));
		assertThat(D1.getTotalStylesheets(), is(0));
		assertThat(D1.getTotalScripts(), is(0));
		assertThat(D1.getTotalIntraPageLinks(), is(0));
		assertThat(D1.getTotalInternalLinks(), is(0));
		assertThat(D1.getTotalExternalLinks(), is(0));
		
		assertThat(D1.getPageSize(), is(not(0)));
		assertThat(D1.getTotalImageSize(), is(0));


		assertThat(D1.getImageIterator().hasNext(), is(false));
		assertThat(D1.getStyleIterator().hasNext(), is(false));
		assertThat(D1.getScriptIterator().hasNext(), is(false));
		assertThat(D1.getLinkIterator().hasNext(), is(false));
		assertThat(D1.getDataFileIterator().hasNext(), is(false));
	}
	
	@Test
	public void testAddLink() throws Exception
	{
		File path1 = new File("/home/tkennedy/directory/that/does/not/exist/", 
				"/home/tkennedy/directory/that/does/not/exist/something/page2.htm");
		URL hyperTextLink = new URL("https://www.cs.odu.edu/~tkennedy/cs350/sum20/");
		
		AnchorTag newLink = new AnchorTag(Locality.EXTERNAL, path1, hyperTextLink);
		D1.addLink(newLink);
		
		assertThat(D1.getTotalLocalImages(), is(0));
		assertThat(D1.getTotalExternalImages(), is(0));
		assertThat(D1.getTotalStylesheets(), is(0));
		assertThat(D1.getTotalScripts(), is(0));
		assertThat(D1.getTotalIntraPageLinks(), is(0));
		assertThat(D1.getTotalInternalLinks(), is(0));
		assertThat(D1.getTotalExternalLinks(), is(1));
		
		assertThat(D1.getPageSize(), is(not(0)));
		assertThat(D1.getTotalImageSize(), is(0));

		
		assertThat(D1.getImageIterator().hasNext(), is(false));
		assertThat(D1.getStyleIterator().hasNext(), is(false));
		assertThat(D1.getScriptIterator().hasNext(), is(false));
		assertThat(D1.getLinkIterator().hasNext(), is(true));
		assertThat(D1.getDataFileIterator().hasNext(), is(false));
	}
}


