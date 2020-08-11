package edu.odu.cs.cs350;

import java.io.File;
import java.util.Objects;

/**
 * DataFile is the container for files which includes their: DataType, file size, and Path.
 * @author Alex Soliza
 *
 */
public class DataFile {
	private DataType type;
    private double fileSize;
    private File path; //where the Data File is stored

    
    /**
     * 
     * @param type - the type of file based on enum DataType
     * @param fileSize - double value
     * @param path - of Path object
     */
    public DataFile(DataType type, double fileSize, File path)
    {
    	super();
    	this.type = type;
    	this.fileSize = fileSize;
    	this.path = path;
    }
    
    /**
     * 
     * @return type - string value: the type of file
     */
    public DataType getType()
    {
    	return this.type;
    }
    
    /**
     * 
     * @return fileSize - int value
     */
    public double getFileSize()
    {
    	return this.fileSize;
    }
    
    /**
     * 
     * @return string value of the DataFile's path
     */
    public File getPath()
    {
    	return this.path;
    }
    
    /**
     * Sets variable type to parameter
     * @param type - of enum DataType
     */
    public void setType(DataType type)
    {
    	this.type = type;
    }
    
    /**
     * Sets variable fileSize to parameter
     * @param fileSize - double value
     */
    public void setFileSize(double fileSize)
    {
    	this.fileSize = fileSize;
    }
    
    /**
     * Sets variable path to parameter
     * @param path
     */
    public void setPath(File path)
    {
    	this.path = path;
    }
    
    @Override
    public int hashCode()
    {
    	return Objects.hash(type, fileSize, path);
    }
    
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DataFile)
		{
			if(obj != null)
			{
				DataFile other = (DataFile) obj;
				return Objects.equals(type, other.type) 
						&& Objects.equals(path, other.path)
						&& Objects.equals(fileSize, other.fileSize);
			}
		}
			
		return false;
	}
	
	@Override
	public String toString() {
		return "DataFile[ Path: " + path.toString() + 
				" | File Size: " + fileSize + 
				" Kb | Type: " + type.toString() +"]";
	}
	
	@Override
    public Object clone()
    {
		return new DataFile(type, fileSize, path);
    }
}