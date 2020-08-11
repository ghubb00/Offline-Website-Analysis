package edu.odu.cs.cs350;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

/**
 * Class object that takes in a HTMLDocument within a Website, parses it
 * and populates the hashSets within it
 *
 */
public class PageScanner {
	
	private Website ws;
	private HTMLDocument htmlDoc;
	
	/**
	 * Constructor.  Assigns values to HTLMDocument and Website objects within the class
	 * @param website the website that is the focus of the analysis
	 * @param htmldoc the HTMLDocument object that will be parsed
	 */
	public PageScanner(Website website, HTMLDocument htmldoc)
	{
		this.ws = website;
		this.htmlDoc = htmldoc;
	}
	
	/**
	 *calls private helper functions to better organize the tag identification process
	 * @throws Throwable 
	 */
	public void Parse () throws Throwable
	{
		/**
		 * Original init of doc assumes a URL is passed.  HTMLDocument contains local files.  URL must be attached in second param
		 */
		Document doc = Jsoup.parse(htmlDoc.getPath(), "UTF-8");
		
		if(check404(doc))
			System.out.print("404 ERROR PAGE");
		else
		{
			Elements css = doc.getElementsByTag("link");
	        Elements js = doc.getElementsByTag("script");
	        Elements links = doc.getElementsByTag("a");
	        Elements images = doc.getElementsByTag("img");
	        Elements datafiles = doc.getElementsByTag("source");
	        
	        findStylesheets(css);
	        findScripts(js);
	        findLinks(links);
	        findImages(images);
	        findDataFile(datafiles);
		}
	}
	
	private boolean check404(Document doc)
	{
	     String title = doc.title(); 
	     if(title.contains("404"))
	     {
	    	 return true;
	     }
	     	return false;
	}
	
	/**
	 *identifies local html <Img> tags and adds them to the htmlDocument's hashSet
	 */
	private void findImages(Elements elements)
	{
		// Add image instances to htmlDocument hashSet
        Locality loc;
        
        for (Element src : elements) 
        {
        	File temp = new File(src.attr("src"));
        	
        	if (temp.toPath().toAbsolutePath() == htmlDoc.getPath().toPath().toAbsolutePath())
        		loc = Locality.INTRAPAGE;
        	else if (temp.toPath().getRoot() == ws.getPath().toPath().getRoot())
        		loc = Locality.INTERNAL;
        	else
        		loc = Locality.EXTERNAL;
        	
        	try {
				htmlDoc.addImage(new Image(loc, temp, temp.toURL(), (double)temp.length()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	/**
	 *identifies html <css> tags and adds them to the htmlDocument's hashSet
	 */
	private void findStylesheets(Elements elements)
	{
		// Add Cascading Style Sheet instances to htmlDocument hashSet
        Locality loc;
        
        for (Element src : elements) 
        {
        	if (src.attr("rel").contains("stylesheet"))
        	{	
	        	File temp = new File(src.attr("href"));
	        	
	        	if (temp.toPath().toAbsolutePath() == htmlDoc.getPath().toPath().toAbsolutePath())
	        		loc = Locality.INTRAPAGE;
	        	else if (temp.toPath().getRoot() == ws.getPath().toPath().getRoot())
	        		loc = Locality.INTERNAL;
	        	else
	        		loc = Locality.EXTERNAL;
	        		
	        	htmlDoc.addStyleSheet(new StyleTag(loc, temp, htmlDoc));
        	}
        }
	}
	/**
	 *identifies html <script> tags and adds them to the htmlDocument's hashSet
	 */
	private void findScripts(Elements elements)
	{
		// Add Javascript instances to htmlDocument hashSet
        Locality loc;
        
        for (Element src : elements) 
        {
        	File temp = new File(src.attr("src"));
        	
        	if (temp.toPath().toAbsolutePath() == htmlDoc.getPath().toPath().toAbsolutePath())
        		loc = Locality.INTRAPAGE;
        	else if (temp.toPath().getRoot() == ws.getPath().toPath().getRoot())
        		loc = Locality.INTERNAL;
        	else
        		loc = Locality.EXTERNAL;
        		
        	htmlDoc.addScript(new ScriptTag(loc, temp, htmlDoc));
        }
	}
	
	/**
	 *identifies html link <a> tags and adds them to the htmlDocument's hashSet
	 */
	private void findLinks(Elements elements)
	{
		// Add AnchorTag/Hyperlink instances to htmlDocument hashSet
        Locality loc;
        
        for (Element src : elements) 
        {
        	File temp = new File(src.attr("HREF"));
        	
        	if (temp.toPath().toAbsolutePath() == htmlDoc.getPath().toPath().toAbsolutePath())
        		loc = Locality.INTRAPAGE;
        	else if (temp.toPath().getRoot() == ws.getPath().toPath().getRoot())
        		loc = Locality.INTERNAL;
        	else
        		loc = Locality.EXTERNAL;
        	
        	try {
				htmlDoc.addLink(new AnchorTag(loc, temp, temp.toURL()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }   
	}
	
	/**
	 *identifies html data file tags and adds them to the htmlDocument's hashSet
	 */
	private void findDataFile(Elements elements)
	{
		// Add DataFile instances to htmlDocument hashSet
        Locality loc;
        
        for (Element src : elements) 
        {
        	File temp = new File(src.attr("src"));
        	DataType type;
        	
        	if (temp.toPath().toAbsolutePath() == htmlDoc.getPath().toPath().toAbsolutePath())
        		loc = Locality.INTRAPAGE;
        	else if (temp.toPath().getRoot() == ws.getPath().toPath().getRoot())
        		loc = Locality.INTERNAL;
        	else
        		loc = Locality.EXTERNAL;
        	
        	if (src.attr("src").contains(".zip") ||
        			src.attr("src").contains(".tar.gz") || 
        			src.attr("src").contains(".tar") ||
        			src.attr("src").contains(".7z"))
        		type = DataType.ARCHIVES;
        	else if (src.attr("src").contains(".mkv") ||
        			src.attr("src").contains(".mp4"))
        		type = DataType.VIDEO;
        	else if (src.attr("src").contains(".m4a") ||
        			src.attr("src").contains(".mka") || 
        			src.attr("src").contains(".ogg"))
        		type = DataType.AUDIO;
        	else
        		type = DataType.OTHER;
        	
        	
        	htmlDoc.addDataFile(new DataFile(type, temp.length(), temp));
        }   
	}
	/**
	 * @param Path internal/external/intra page link <a> is broken
	 * @return boolean True if path is broken/doesn't work
	 * should be called by findInternalLinks(),findInternalLinks(), findExternalLinks()
	 */
	private boolean linkIsBroken(File a)
	{
		if(a.exists() == false)
		{
		return false;
		}
		return true;
	}
}
