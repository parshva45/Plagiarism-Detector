package pd;

import java.util.ArrayList;

public class Student extends User {

	public int secId; // section ID
	public boolean flag; // Plagiarism flag
	public ArrayList<Integer> pAIds; // List of assignments in which
	// the student has been found plagiarising previously.
	public ArrayList<Integer> pUIds; // List of students with whom this student
	// has already been flagged plagiarising.
	
	public void reqChangeRole() {
		
	}

}
