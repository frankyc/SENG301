import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import DBMS.*;
import FileManagement.*;
import Management.*;
import Users.*;
@RunWith(Suite.class)
@Suite.SuiteClasses({DirectoryManagerTest.class, FileManagerTest.class,CourseAssignmentTest.class,StudentAccessTest.class,
	DueDateTest.class,CourseManagerTest.class,AssignmentDbmsTest.class,BaseDbmsTest.class,InstructorDbmsTest.class,
	StudentDbmsTest.class,TADbmsTest.class,UserDbmsTest.class,UserAssignmentDbmsTest.class,StudentTest.class})
public class TestSuite {
  //nothing
}