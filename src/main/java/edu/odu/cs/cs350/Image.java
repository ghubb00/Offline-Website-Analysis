package edu.odu.cs.cs350;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.net.URI;
import java.net.URISyntaxException;

public class Image {
	private Locality type;
	// "Images should be classified as Internal or External"
	private File path;
	private URL localURL;
	private URI imageURI;
	private double fileSize;

	/**
	 * Default constructor
	 */
	public Image() {
		super();
	}

	/**
	 * Parameterized constructor Create an image with parameters initialized to
	 * blank values
	 * 
	 * @param name - The file path of the image
	 * @param type - the type of image (Locality)
	 * @param url  - the local page the image was referenced on
	 * @param fileSize  - the filesize of the image
	 * @throws URISyntaxException
	 */
	public Image(Locality type, File path, URL url, double fileSize) throws URISyntaxException {
		this.path = path;
		this.type = type;
		this.localURL = url;
		this.imageURI = url.toURI();
		this.fileSize = fileSize;
	}

	/**
	 * Returns the ImageType of the image
	 */
	public Locality getImageType() {
		return type;
	}

	/**
	 * Sets the ImageType of the image
	 * 
	 * @param type - The type of image
	 */
	public void setImageType(Locality type) {
		this.type = type;
	}

	/**
	 * Returns the name of the image
	 * 
	 * @return name - the File path
	 */
	public File getImagePath() {
		return path;
	}

	/**
	 * Returns the size of the image
	 * 
	 * @return size - the local size of the image file
	 */
	public double getImageSize() {
		return fileSize;
	}

	/**
	 * Returns the local page the Image was found on
	 * 
	 * @return url - the local page the Image was found on
	 */
	public URL getURL() {
		return localURL;
	}

	public URI getURI() {
		return imageURI;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, path, localURL);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DataFile) {
			if (obj != null) {
				Image other = (Image) obj;
				return Objects.equals(type, other.type) && Objects.equals(path, other.path)
						&& Objects.equals(localURL, other.localURL) && Objects.equals(fileSize, other.fileSize);
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "Image[ Type: " + type.toString() + " | Path: " + path.toString() + " | Type: " + type.toString()
				+ " | File Size: " + fileSize + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Image cloned = (Image) super.clone();
		return cloned;
	}
}
