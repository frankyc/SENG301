import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import FileManagement.*;
import Management.*;
@RunWith(Suite.class)
@Suite.SuiteClasses({DirectoryManagerTest.class, FileManagerTest.class,CourseAssignmentTest.class,StudentAccessTest.class,
	DueDateTest.class})
public class TestSuite {
  //nothing
}