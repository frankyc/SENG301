package FileManagement;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileManagerTest {

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFileManager() {
		FileManager fm = new FileManager( "Admin" );
		assertEquals("Admin should equal Admin after Initialization","Admin",fm.instructorId);
		assertEquals("Base Path should be the same",System.getProperty("user.dir")+ "/res/" + "Admin" + "/",fm.basePath );
		assertNotNull("This should not be null",fm.dirManager);
	}

	@Test
	public void testWriteGradeFileStringStringArray() {
		FileManager fm = new FileManager( "Admin" );
		String[] grade = {"A","B"};
			File x = new File("..//test//test.txt");
			if(x.exists())
				x.delete();
			try {
				fm.writeGradeFile("..//test//test.txt", grade);
				assertTrue(x.exists());
			} catch (FileExistsException e) {
				fail("File should not exist as it should be deleted first due to initalization");
			}
			x.delete();
			assertTrue("Should bot be true",!x.exists());
	}

	@Test
	public void testWriteGradeFileStringStringArrayBoolean() {
		FileManager fm = new FileManager( "Admin" );
		String[] grade = {"A","B"};
		long beforeSize = 0;
			File x = new File("..//test//test.txt");
			if(x.exists())
				x.delete();
			try {
				fm.writeGradeFile("..//test//test.txt", grade,false);
				assertTrue(x.exists());
				assertEquals(x.length(),4);
				beforeSize = x.length();
			} catch (FileExistsException e) {
				fail("File Should not exist");
			}
			try {
				grade = new String[] {"A","A","A","A","A","A"};
				fm.writeGradeFile("..//test//test.txt", grade,true);
				assertTrue(x.exists());
				assertTrue("SHOULD NOT BE bytes before overwrite anymore",x.length()!= beforeSize);
			} catch (FileExistsException e) {
				fail("FileShouldBeOverwritten");
			}
			x.delete();
	}

	@Test
	public void testReadGradeFile() {
		FileManager fm = new FileManager( "Admin" );
		String[] grade = {"A","B"},gradeStored = null;
		File x = new File("..//test//test.txt");
		
		if(x.exists())
			x.delete();
		try {
			fm.writeGradeFile("..//test//test.txt", grade,true);
			assertTrue(x.exists());
			assertEquals(x.length(),4);
		} catch (FileExistsException e) {
			fail("File Should not exist");
		}
		try {
			gradeStored = fm.readGradeFile("..//test//test.txt");
		} catch (FileNotFoundException e) {
			fail("File was not found");
		}
		assertNotNull("gradesStored in file should not be null as it is written",gradeStored);	
		for(int i =0; i< grade.length; i++)
		{
			assertTrue("Values Stored in file is read back same way",grade[i].compareTo(gradeStored[i]) == 0);
		}
			
	}

	@Test
	public void testDownloadAllSubmittedFiles() {
		FileManager fm = new FileManager( "Admin" );
		boolean downloadedTrueOrFalse = false;
		try {
			downloadedTrueOrFalse = fm.downloadAllSubmittedFiles("SENG301", 1 , "C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4" +
					"\\Test\\TestDownloadAllSubmittedFiles\\");
		} catch (FileNotFoundException e) {
			assertTrue(downloadedTrueOrFalse);
			fail("File Could Not be downloaded");
		}
			File x = new File("C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Test\\TestDownloadAllSubmittedFiles\\Late");
			assertTrue(x.exists());
			assertTrue(x.isDirectory());
			x = new File("C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Test\\TestDownloadAllSubmittedFiles\\OnTime");
			assertTrue(x.exists());
			assertTrue(x.isDirectory());
	}

	@Test
	public void testSubmitFile() {
		FileManager fM = new FileManager("Admin");
		long original = 0,copy;
		try {
			fM.submitFile("SENG301", 3, true, "C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Test\\" +
					"TestDownloadAllSubmittedFiles\\Late\\Student1-LoginListNonEncryptSubmitTest.txt", "Student1");
			original = new File("C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Test\\TestDownloadAllSubmittedFiles" +
					"\\Late\\Student1-LoginListNonEncryptSubmitTest.txt").length();

		} catch (FileNotFoundException e1) {
			fail("Error Could not Submit Late Assignment");
		}
			File x = new File("C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Assignment4\\res\\Admin\\SENG301\\3\\Late" +
					"\\Student1-Student1-LoginListNonEncryptSubmitTest.txt");
			assertTrue(x.exists());
			copy= new File("C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Assignment4\\res\\Admin\\SENG301\\3\\Late" +
					"\\Student1-Student1-LoginListNonEncryptSubmitTest.txt").length();
			assertEquals("size should be the same if its a copy",original,copy);
		try {
			fM.submitFile("SENG301", 3, false, "C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Test\\TestDownloadAllSubmittedFiles" +
					"\\OnTime\\Student1-LoginListNonEncryptSubmitTest.txt", "Student1");
			original = new File("C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Test\\TestDownloadAllSubmittedFiles" +
					"\\OnTime\\Student1-LoginListNonEncryptSubmitTest.txt").length();

		} catch (FileNotFoundException e) {
			fail("Error Could not Submit OnTime Assignment");
		}
			x = new File("C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Assignment4\\res\\Admin\\SENG301\\3" +
					"\\OnTime\\Student1-Student1-LoginListNonEncryptSubmitTest.txt");
			copy= new File("C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Assignment4\\res\\Admin\\SENG301\\3" +
					"\\OnTime\\Student1-Student1-LoginListNonEncryptSubmitTest.txt").length();
			
			assertTrue(x.exists());
			assertEquals("size should be the same if its a copy",original,copy);
	}

	@Test
	public void testDeleteSubmission() {
		FileManager fM = new FileManager("Admin");
		File x = null;
		try {
			fM.submitFile("SENG301", 4, false, "C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Test\\TestDownloadAllSubmittedFiles" +
					"\\OnTime\\Student1-LoginListNonEncryptSubmitTest.txt", "Student1");
			x = new File("C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Assignment4\\res\\Admin\\SENG301\\4\\OnTime" +
					"\\Student1-Student1-LoginListNonEncryptSubmitTest.txt");
			assertTrue(x.exists());
		} catch (FileNotFoundException e) {
			fail("Error could not submit OnTime File");
		}
			fM.deleteSubmission("SENG301", 4, false, "Student1");
			assertTrue(!x.exists());
			
		
		try {
			fM.submitFile("SENG301", 4, true, "C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Test\\TestDownloadAllSubmittedFiles" +
					"\\Late\\Student1-LoginListNonEncryptSubmitTest.txt", "Student1");
			x = new File("C:\\Users\\Franky\\Desktop\\3rd year\\Fall\\SENG301\\assign4\\Assignment4\\res\\Admin\\SENG301\\4\\Late" +
					"\\Student1-Student1-LoginListNonEncryptSubmitTest.txt");
			assertTrue(x.exists());
		} catch (FileNotFoundException e) {
			fail("Error could not submit late File");
		}
			fM.deleteSubmission("SENG301", 4, true, "Student1");
			assertTrue(!x.exists());
		
	}

}
