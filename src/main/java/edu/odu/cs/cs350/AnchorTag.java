package edu.odu.cs.cs350;

import java.io.File;
import java.net.URL;

public class AnchorTag {

	private Locality classType;
	//"Links should be classified as Intra-page, Intra-site, External"
	private File path;
    //AnchorTag is used to define the beginning and end of a hypertext link.  This an import java.net.URL
	private URL hyperTextLink;
	
	/**
	 * Default constructor not needed
	 * Therefore making it private
	 */
	@SuppressWarnings("unused")
	private AnchorTag() {
	}
	
    /**
     * constructor for a "anchorTag" which refers to link related HTML tags
     * tags can either be classified as Intra-page, Intra-site, External
     *
     * @param type the specific classification of the link (Intra-page, Intra-site, External)
     * @param hyperLinks the specific path of the HTMLDocument page
     */
    public AnchorTag(Locality type, File path, URL hyperTextLink)
	    {
	    	this.classType = type;
	    	this.path = path;
	    	this.hyperTextLink = hyperTextLink;
	    }

	/**
	 * "the locality classification" of the anchorTag (Intra-page, Intra-site, External)
	 * @return classType the enum value of locality
	 */
	public Locality getType() 
	{
	    return classType;
	}

	/**
	 * allows for the change of locality type (Intra-page, Intra-site, External)
	 * @param newtype the enum value of locality
	 */

	public void setType(Locality newtype)
	{
	    this.classType = newtype;
	}
	
	/**
     * Retrieve the path for AnchorTag
     */
	public File getPath() 
	{
		return path;
	}
	
	/**
     * Change the AnchorTag path
     *
     * @param path desired for AnchorTag
     */
	public void setPath(File path)
	{
		this.path = path;
	}
	
	/**
     * Retrieve the hyper text link for AnchorTag
     */
	public URL getHyperTextLink()
	{
		return hyperTextLink;
	}
	
	/**
     * Change the AnchorTag hyper text link
     *
     * @param setting the desired hyper link for AnchorTag
     */
	public void setHyperTextLink(URL hyperTextLink)
	{
		this.hyperTextLink = hyperTextLink;
	}
	
	/**
	 * prints the Link tag information
	 * @return the AnchorTag object's values
	 */
	@Override
	public String toString()
	    {
		//uses java.net.URL toString method
	    return classType.name() + " Anchor Tag located at " + path.toString() + 
	    		" contains the hypertext link, " + hyperTextLink.toString();
	    }
}
