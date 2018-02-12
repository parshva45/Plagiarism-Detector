package pd;

/**
 * @author Praveen Singh
 */
public class UserFactory {
    public User getUser(){
        return new CourseStaff();
    }
}
