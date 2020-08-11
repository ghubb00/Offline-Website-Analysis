/**
 * 
 */
package edu.odu.cs.cs350;

import java.util.Objects;

/**
 * @author Paul Fracz, Naomi Filio
 *
 */

/**
 * CLASS WILL NO LONGER BE USED IN FAVOR OF JAVA.FILE 
 *-week 4
 */
public class Path {
	//private String fullPath, siteRoot, relativePath;
	private String siteRoot;
	private String relativePath;
	private String fullPath;
	
	public Path() {
		super();
		this.siteRoot = "";
		this.relativePath = "";
		this.fullPath = siteRoot + this.relativePath;
	}
	
	public Path(String siteRoot) {
		super();
		this.siteRoot = siteRoot;
		this.relativePath = "";
		this.fullPath = siteRoot + this.relativePath;
	}
	
	public Path(String siteRoot, String relativePath) {
		super();
		this.siteRoot = siteRoot;
		this.relativePath = relativePath;
		this.fullPath = siteRoot + "/" + relativePath;
	}

	public String getSiteRoot() {
		return siteRoot;
	}

	public void setSiteRoot(String siteRoot) {
		this.siteRoot = siteRoot;
		this.fullPath = siteRoot + "/" + relativePath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
		this.fullPath = siteRoot + "/" + relativePath;
	}
	
	//fullPath is read-only as the app itself will concatenate the siteRoot and relativePath
	public String getFullPath() {
		return fullPath;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fullPath, relativePath, siteRoot);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Path)
		{
			if(obj != null)
			{
				Path other = (Path) obj;
				return Objects.equals(fullPath, other.fullPath) 
						&& Objects.equals(relativePath, other.relativePath)
						&& Objects.equals(siteRoot, other.siteRoot);
			}
			else
				return false;
				
		}
			
		return false;
	}

	@Override
	public String toString() {
		return fullPath;
		
		/*return "Path [siteRoot=" + siteRoot + ","
				+ " relativePath=" + relativePath 
				+ ", fullPath=" + fullPath + "]";
				*/
	}
	
	@Override
    public Object clone()
    {
		if(relativePath == "")
		{	
			if(siteRoot == "")
			{
				return new Path();
			}
			else
				return new Path(siteRoot);
		}
		
		return new Path(siteRoot, relativePath);
    }
}
//test