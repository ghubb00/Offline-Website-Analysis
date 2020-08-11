package edu.odu.cs.cs350;

import static org.junit.Assert.*;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TestDataFile {

	@Test
	public void testDataFile() 
	{
		DataType testType = DataType.VIDEO;
		double testFileSize = 100.0;
		File testPath = new File("Hello");
		
		DataFile testDataFile = new DataFile(testType, testFileSize, testPath);
		DataFile copy = (DataFile) testDataFile.clone();
		int hashcode = testDataFile.hashCode();
		assertEquals(testDataFile.toString(),
				"DataFile[ Path: " + testDataFile.getPath() + 
				" | File Size: " + testDataFile.getFileSize() + 
				" Kb | Type: " + testDataFile.getType().toString() +"]");
		
		assertThat(testDataFile.getType(), is(DataType.VIDEO));
		assertThat(testDataFile.getFileSize(), is(100.0));
		assertThat(testDataFile.getPath(), is("Hello"));
		assertThat(testDataFile.hashCode(), is(hashcode));
	}

	@Test
	public void testSetType() 
	{
		DataType testType = DataType.AUDIO;
		double testFileSize = 200.0;
		File testPath = new File("There");
		
		DataFile testDataFile = new DataFile(testType, testFileSize, testPath);
		DataFile copy = (DataFile) testDataFile.clone();
		int hashcode = testDataFile.hashCode();
		
		assertThat(testDataFile.getType(), is(DataType.AUDIO));
		assertThat(testDataFile.getFileSize(), is(200.0));
		assertThat(testDataFile.getPath(), is("There"));
		assertTrue(testDataFile.equals(copy));
		assertEquals(testDataFile.toString(),
				"DataFile[ Path: " + testDataFile.getPath() + 
				" | File Size: " + testDataFile.getFileSize() + 
				" Kb | Type: " + testDataFile.getType().toString() +"]");
		
		testType = DataType.ARCHIVES;
		testDataFile.setType(testType);
		
		assertThat(testDataFile.getType(), is(DataType.ARCHIVES));
		assertThat(testDataFile.getFileSize(), is(200.0));
		assertThat(testDataFile.getPath(), is("There"));
		assertThat(testDataFile.hashCode(), not(hashcode));
	}

	@Test
	public void testSetFileSize() 
	{
		DataType testType = DataType.OTHER;
		double testFileSize = 300.0;
		File testPath = new File("General");
		
		DataFile testDataFile = new DataFile(testType, testFileSize, testPath);
		DataFile copy = (DataFile) testDataFile.clone();
		int hashcode = testDataFile.hashCode();
		
		assertThat(testDataFile.getType(), is(DataType.OTHER));
		assertThat(testDataFile.getFileSize(), is(300.0));
		assertThat(testDataFile.getPath(), is("General"));
		assertTrue(testDataFile.equals(copy));
		assertEquals(testDataFile.toString(),
				"DataFile[ Path: " + testDataFile.getPath() + 
				" | File Size: " + testDataFile.getFileSize() + 
				" Kb | Type: " + testDataFile.getType().toString() +"]");
		
		testFileSize = 4444.0;
		testDataFile.setFileSize(testFileSize);
		
		assertThat(testDataFile.getType(), is(DataType.OTHER));
		assertThat(testDataFile.getFileSize(), is(4444.0));
		assertThat(testDataFile.getPath(), is("General"));
		assertThat(testDataFile.hashCode(), not(hashcode));
	}

	@Test
	public void testSetPath() 
	{
		DataType testType = DataType.VIDEO;
		double testFileSize = 12345.0;
		File testPath = new File("Kanobi");
		
		DataFile testDataFile = new DataFile(testType, testFileSize, testPath);
		DataFile copy = (DataFile) testDataFile.clone();
		int hashcode = testDataFile.hashCode();
		
		assertThat(testDataFile.getType(), is(DataType.VIDEO));
		assertThat(testDataFile.getFileSize(), is(12345.0));
		assertThat(testDataFile.getPath(), is("Kanobi"));
		assertTrue(testDataFile.equals(copy));
		assertEquals(testDataFile.toString(),
				"DataFile[ Path: " + testDataFile.getPath() + 
				" | File Size: " + testDataFile.getFileSize() + 
				" Kb | Type: " + testDataFile.getType().toString() +"]");
		
		/** Updated from Path to File, this may not be needed any more? -Paul*/
		//testPath.setRelativePath(".");
		//testDataFile.setPath(testPath);
		
		assertThat(testDataFile.getType(), is(DataType.VIDEO));
		assertThat(testDataFile.getFileSize(), is(12345.0));
		assertThat(testDataFile.getPath(), is("Kanobi/."));
		assertThat(testDataFile.hashCode(), not(hashcode));
	}
}
