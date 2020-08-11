package edu.odu.cs.cs350;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TestImage 
{
	@Test
	public void testParameterizedConstructor() throws URISyntaxException, MalformedURLException 
	{
		Locality testType = Locality.INTERNAL;
		File testPath = new File("Say");
		URL testLocalURL = new URL("www.say.com");
		int testFileSize = 100;

		Image testImage = new Image(testType, testPath, testLocalURL, testFileSize);
		
		assertThat(testImage.getImageType(), is(Locality.INTERNAL));
		assertThat(testImage.getImagePath(), is("Say"));
		assertThat(testImage.getURL(), is("www.say.com"));
		assertThat(testImage.getImageSize(), is(100));
	}
	
	@Test
	public void testSetImageType() throws MalformedURLException, URISyntaxException 
	{
		Locality testType = Locality.INTERNAL;
		File testPath = new File("Says");
		URL testLocalURL = new URL("www.says.com");
		int testImageSize = 2211;

		Image testImage = new Image(testType, testPath, testLocalURL, testImageSize);
		
		assertThat(testImage.getImageType(), is(Locality.INTERNAL));
		assertThat(testImage.getImagePath(), is("Says"));
		assertThat(testImage.getURL(), is("www.says.com"));
		assertThat(testImage.getImageSize(), is(2211));
		
		testImage.setImageType(Locality.EXTERNAL);
		
		assertThat(testImage.getImageType(), is(Locality.INTERNAL));
		assertThat(testImage.getImagePath(), is("Says"));
		assertThat(testImage.getURL(), is("www.says.com"));
		assertThat(testImage.getImageType(), is(Locality.EXTERNAL));
	}
	
	/*
	 * @Test public void testSetImageName() { Locality testType = Locality.EXTERNAL;
	 * File testPath = new File("Hello"); URL testLocalURL = new
	 * URL("www.hello.com"); int testFileSize = 2030;
	 * 
	 * Image testImage = new Image(testType, testPath, testLocalURL, testFileSize);
	 * 
	 * assertThat(testImage.getImageType(), is(Locality.EXTERNAL));
	 * assertThat(testImage.getImagePath(), is("Hello"));
	 * assertThat(testImage.getURL(), is("www.hello.com"));
	 * assertThat(testImage.getImageSize(), is(2030));
	 * 
	 * 
	 * testImage.setImageName("To");
	 * 
	 * assertThat(testImage.getImagePath(), is("To"));
	 * assertThat(testImage.getImageSize(), is(2030));
	 * assertThat(testImage.getImageType(), is(ImageType.EXTERNAL)); }
	 */
		
	/*
	 * @Test public void testSetImageSize() { String name = "My"; int testImageSize
	 * = 99; ImageType testType = ImageType.INTERNAL;
	 * 
	 * Image testImage = new Image(name, testImageSize, testType);
	 * 
	 * assertThat(testImage.getImagePath(), is("My"));
	 * assertThat(testImage.getImageSize(), is(99));
	 * assertThat(testImage.getImageType(), is(ImageType.INTERNAL));
	 * 
	 * testImage.setImageSize(40302);
	 * 
	 * assertThat(testImage.getImagePath(), is("My"));
	 * assertThat(testImage.getImageSize(), is(40302));
	 * assertThat(testImage.getImageType(), is(ImageType.INTERNAL)); }
	 */
}
