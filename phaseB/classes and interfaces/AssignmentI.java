package pd;

/**
 * @author Praveen Singh
 */
public interface AssignmentI {

	// To create a new assignment
    public void createAssignment();

    // To create an assignment URL
    public void createAssignmentURL();

    // To check for plagiarism between assignments
    public void checkPlag();

    // To delete a created assignment
    public void deleteAssignment();
}
