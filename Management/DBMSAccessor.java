package Management;

import Users.*;
import FileManagement.*;
import DBMS.*;

public interface DBMSAccessor {
	InstructorDbms iDbms = new InstructorDbms();
	TADbms tDbms = new TADbms();
	UserDbms uDbms = new UserDbms();
	StudentDbms sDbms = new StudentDbms ();
}
