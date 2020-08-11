package edu.odu.cs.cs350;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TestPath {

	@Test
	public void testPathNoParam() {
		Path pathNoParam = new Path();
		Path copyPath = (Path)pathNoParam.clone();
		int hCode = pathNoParam.hashCode();
		
		assertThat(pathNoParam.getSiteRoot(), is(""));
		assertThat(pathNoParam.getRelativePath(), is(""));
		assertThat(pathNoParam.getFullPath(), is(""));
		assertThat(pathNoParam.hashCode(), is(hCode));
		assertTrue(pathNoParam.equals(copyPath));
		assertThat(pathNoParam.toString(), is(""));
		/*
		 * assertThat(pathNoParam.toString(), is("Path [siteRoot=," + " relativePath=,"
		 * + " fullPath=]"));
		 */
	}
	
	@Test
	public void testPathNoRel() {
		Path pathNoRel = new Path("Root");
		Path copyPath = (Path)pathNoRel.clone();
		int hCode = pathNoRel.hashCode();
		
		assertThat(pathNoRel.getSiteRoot(), is("Root"));
		assertThat(pathNoRel.getRelativePath(), is(""));
		assertThat(pathNoRel.getFullPath(), is("Root"));
		assertThat(pathNoRel.hashCode(), is(hCode));
		assertTrue(pathNoRel.equals(copyPath));
		assertThat(pathNoRel.toString(), is("Root"));
		/*assertThat(pathNoRel.toString(), is("Path [siteRoot=Root,"
				+ " relativePath=,"
				+ " fullPath=Root]"));
				*/
	}
	
	@Test
	public void testPathAllParam() {
		Path pathAllParam = new Path("Root","Rel");
		Path copyPath = (Path)pathAllParam.clone();
		int hCode = pathAllParam.hashCode();
		
		assertThat(pathAllParam.getSiteRoot(), is("Root"));
		assertThat(pathAllParam.getRelativePath(), is("Rel"));
		assertThat(pathAllParam.getFullPath(), is("Root/Rel"));
		assertThat(pathAllParam.hashCode(), is(hCode));
		assertTrue(pathAllParam.equals(copyPath));
		assertThat(pathAllParam.toString(), is("Root/Rel"));
		/*assertThat(pathAllParam.toString(), is("Path [siteRoot=Root,"
				+ " relativePath=Rel,"
				+ " fullPath=Root/Rel]"));
				*/
	}

	@Test
	public void testSetSiteRoot() {
		Path pathSetSiteRoot = new Path("Root","Rel");
		Path copyPath = (Path)pathSetSiteRoot.clone();
		
		pathSetSiteRoot.setSiteRoot("New Root");
		int hCode = pathSetSiteRoot.hashCode();
		
		assertThat(pathSetSiteRoot.getSiteRoot(), is("New Root"));
		assertThat(pathSetSiteRoot.getRelativePath(), is("Rel"));
		assertThat(pathSetSiteRoot.getFullPath(), is("New Root/Rel"));
		assertThat(pathSetSiteRoot.hashCode(), is(hCode));
		assertFalse(pathSetSiteRoot.equals(copyPath));
		assertThat(pathSetSiteRoot.toString(), is("New Root/Rel"));
		/*assertThat(pathSetSiteRoot.toString(), is("Path [siteRoot=New Root,"
				+ " relativePath=Rel,"
				+ " fullPath=New RootRel]"));
				*/
	}

	@Test
	public void testSetRelativePath() {
		Path pathSetRelPath = new Path("Root","Rel");
		Path copyPath = (Path)pathSetRelPath.clone();
		
		pathSetRelPath.setRelativePath("New Rel");
		int hCode = pathSetRelPath.hashCode();
		
		assertThat(pathSetRelPath.getSiteRoot(), is("Root"));
		assertThat(pathSetRelPath.getRelativePath(), is("New Rel"));
		assertThat(pathSetRelPath.getFullPath(), is("Root/New Rel"));
		assertThat(pathSetRelPath.hashCode(), is(hCode));
		assertFalse(pathSetRelPath.equals(copyPath));
		assertThat(pathSetRelPath.toString(), is("Root/New Rel"));
		/*
		 * assertThat(pathSetRelPath.toString(), is("Path [siteRoot=Root," +
		 * " relativePath=New Rel," + " fullPath=RootNew Rel]"));
		 */
	}
	
	@Test
	public void testClone() {
		Path pathClone = new Path("Root","Rel");
		Path copyPath = (Path)pathClone.clone();
		
		int hCode = pathClone.hashCode();
		
		assertThat(pathClone.getSiteRoot(), is("Root"));
		assertThat(pathClone.getRelativePath(), is("Rel"));
		assertThat(pathClone.getFullPath(), is("Root/Rel"));
		assertThat(pathClone.hashCode(), is(hCode));
		assertTrue(pathClone.equals(copyPath));
		assertThat(pathClone.toString(), is("Root/Rel"));
		/*
		 * assertThat(pathSetRelPath.toString(), is("Path [siteRoot=Root," +
		 * " relativePath=New Rel," + " fullPath=RootNew Rel]"));
		 */
	}
}
