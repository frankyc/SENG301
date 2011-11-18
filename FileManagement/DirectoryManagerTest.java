package FileManagement;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DirectoryManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File x = new File("./null/res/Admin1/course/1/OnTime");
		if(x.exists())
			x.delete();
		x = new File("./null/res/Admin1/course/1/Late");
		if(x.exists())
			x.delete();
		x = new File("./null/res/Admin1/course/1");
		if(x.exists())
			x.delete();
		x = new File("./null/res/Admin1/course");
		if(x.exists())
			x.delete();
		x = new File("./null/res/Admin1");
		if(x.exists())
			x.delete();
		x = new File("./null/res");
		if(x.exists())
			x.delete();
		x = new File("./null");
		if(x.exists())
			x.delete();
		File submissionfolder = new File("../test/");
		if(!submissionfolder.exists());
			submissionfolder.mkdir();
	}

	@Test
	public void testDirectoryManager() {
		File x = new File("./null/res/Admin1/");
		if(x.exists())
			x.delete();
		assertTrue(!x.exists());
		DirectoryManager dM = new DirectoryManager("Admin1");
		assertTrue(x.exists());
		x.delete();
	}

	@Test
	public void testCreateCourseDir() {
		File x = new File("./null/res/Admin1/course");
		if(x.exists()){
			x.delete();
		}
		assertTrue(!x.exists());
		DirectoryManager dM = new DirectoryManager("Admin1");
		dM.createCourseDir("course");
		assertTrue(x.exists());
		x.delete();
		
	}

	@Test
	public void testCreateAssignDir() {
		File x = new File("./null/res/Admin1/course");
		if(x.exists()){
			x.delete();
		}
		assertTrue(!x.exists());
		DirectoryManager dM = new DirectoryManager("Admin1");
		dM.createCourseDir("course");
		assertTrue(x.exists());
		x = new File("./null/res/Admin1/course/1");
		assertTrue(!x.exists());
		x = new File("./null/res/Admin1/course/1/Late");
		assertTrue(!x.exists());
		x = new File("./null/res/Admin1/course/1/OnTime");
		assertTrue(!x.exists());
		dM.createAssignDir("course", 1);
		assertTrue(x.exists());
		x.delete();
		x = new File("./null/res/Admin1/course/1/Late");
		assertTrue(x.exists());
		x.delete();
		x = new File("./null/res/Admin1/course/1");
		assertTrue(x.exists());
		x.delete();
		x = new File("./null/res/Admin1/course");
		assertTrue(x.exists());
		x.delete();
	}

	@Test
	public void testCreateSubmissionDirs() {
		File x = new File("../test/");
		assertTrue(x.exists());
		DirectoryManager dM = new DirectoryManager("Admin1");
		dM.createSubmissionDirs("../test/");
		x = new File("../test/Late");
		assertTrue(x.exists());
		x.delete();
		x = new File("../test/OnTime");
		assertTrue(x.exists());
		x.delete();
	}
}
