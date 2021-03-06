package edu.odu.cs.cs350;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class StyleTag {
	// "Links should be classified as Internal, External, INTERPAGE NOT AN OPTION"
	private Locality classType;
	private File path;
	/*
	 * the collection of HTMLpage information will be stored in a set so duplicates
	 * will not be recorded
	 */
	private Set<HTMLDocument> allPages = new HashSet<HTMLDocument>(); 

	
	@SuppressWarnings("unused")
	private StyleTag() {}
	
	/**
     * Create a class StyleTag with a specified
     * locality and path
     * INTERPAGE NOT AN OPTION, will output error message if user tries 
     * inputting INTERPAGE
     *
     * @param type - Locality (Internal, External)
     * @param path - Path (Location of styletag on local system)
     */
	public StyleTag(Locality type, File path)
	{
		this.classType = type;
		this.path = path;	
	}

	/**
     * Create a class StyleTag with a specified
     * locality, path, and page it is on
     * INTERPAGE NOT AN OPTION, will output error message if user tries 
     * inputting INTERPAGE
     *
     * @param type - Locality (Internal, External)
     * @param path - Path (Location of styletag on local system)
     * @param page - HTML document it is referenced on
     */
	public StyleTag(Locality type, File path, HTMLDocument page)
	{
		this.classType = type;
		this.path = path;
		allPages.add(page);
	}

	/**
     * Retrieve the type for StyleTag (Internal, External)
     */
	public Locality getType()
	{
		return this.classType;
		
	}

	/**
     * Change the StyleTag type (Internal, External)
     * INTERPAGE NOT AN OPTION, will output error message if user tries 
     * inputting INTERPAGE
     *
     * @param classType desired for SyleTag (Internal, External)
     */
	public void setType(Locality classType)
	{
		this.classType = classType;
	}

	/**
     * Retrieve the path for StyleTag
     * @return return the StyleTag path
     */
	public File getPath()
	{
		return this.path;
	}

	/**
     * Change the StyleTag path
     *
     * @param path desired for StyleTag
     */
	public void setPath(File path)
	{
		this.path = path;
	}

	/**
	 * Attempt to add page which StyleTag is referenced on
	 * Rule: page cannot be added if already in the collection (no duplicates).
	 * 
	 * @param page - HTML document it is referenced on
	 * @return false if failed, returns true if successful
	 */
	public void addPage(HTMLDocument page)
	{
		allPages.add(page);
	}
	
	/**
	 * provides an iterator pointing to the first page in collection of pages
	 * @return iterator
	 */
	public Iterator<HTMLDocument> iterator(){
		return allPages.iterator();
	}
	
    @Override
    public int hashCode()
    {
    	return Objects.hash(classType, path);
    }
    
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof StyleTag)
		{
			if(obj != null)
			{
				StyleTag other = (StyleTag) obj;
				return Objects.equals(classType, other.classType) 
						&& Objects.equals(path, other.path);
			}
		}
			
		return false;
	}
	
	/**
	 * Generates a String containing the StyleTag's type, and path the pages it is referenced on
	 * followed by the actual ScriptTag content.
	 */
	@Override
	public String toString()
	{
		return classType.name() + " Script Tag located at " + path.toString();
	}

}
