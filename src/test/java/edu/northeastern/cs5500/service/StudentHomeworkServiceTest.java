package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.StudentHomeWork;
import edu.northeastern.cs5500.repository.StudentHomeWorkRepository;
import edu.northeastern.cs5500.service.StudentHomeWorkService.HttpStatusException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.*;

/**
 * @author Parshva Shah
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentHomeworkServiceTest {
	
	@Mock
    private StudentHomeWorkRepository studentHomeWorkRepository;
	
	@InjectMocks
    private StudentHomeWorkService studentHomeWorkService;

	@Test
	public void testStudentHomeworkGetListOfHomeWorksByCourseIdAndHomeWorkId() {
		StudentHomeWork studentHomeWork1 = new StudentHomeWork().withCourseId(1)
				.withHomeWorkId(1).withId(1).withPath("/src/submission1.py").withUserId(1);
		StudentHomeWork studentHomeWork2 = new StudentHomeWork().withCourseId(1)
				.withHomeWorkId(1).withId(2).withPath("/src/submission2.py").withUserId(2);
		
		List<StudentHomeWork> studentHomeworkList1 = new ArrayList<StudentHomeWork>();
		studentHomeworkList1.add(studentHomeWork1);
		studentHomeworkList1.add(studentHomeWork2);
		
		when(studentHomeWorkRepository.findAllByCourseIdAndAndHomeWorkId(1,1))
		.thenReturn(studentHomeworkList1);
		
		List<StudentHomeWork> studentHomeworkList2 = studentHomeWorkService
				.getListOfHomeWorksByCourseIdAndHomeWorkId(1,1);
		verify(studentHomeWorkRepository, times(1)).findAllByCourseIdAndAndHomeWorkId(1,1);
		
		Assert.assertEquals(studentHomeworkList1, studentHomeworkList2);
		Assert.assertEquals(studentHomeworkList1.get(0).getHomeWorkId(), studentHomeworkList2.get(0).getHomeWorkId());
		
	}
	
	@Test
	public void testStudentHomeworkGetListOfHomeWorksByStudentId() {
		StudentHomeWork studentHomeWork1 = new StudentHomeWork().withCourseId(1)
				.withHomeWorkId(1).withId(1).withPath("/src/submission1.py").withUserId(1);
		StudentHomeWork studentHomeWork3 = new StudentHomeWork().withCourseId(1)
				.withHomeWorkId(2).withId(2).withPath("/src/submission3.py").withUserId(1);
		List<StudentHomeWork> studentHomeworkList3 = new ArrayList<StudentHomeWork>();
		studentHomeworkList3.add(studentHomeWork1);
		studentHomeworkList3.add(studentHomeWork3);
		when(studentHomeWorkRepository.findAllByUserId(1)).thenReturn(studentHomeworkList3);
		
		List<StudentHomeWork> studentHomeworkList4 = studentHomeWorkService.getListOfHomeWorksByStudentId(1);

		verify(studentHomeWorkRepository, times(1)).findAllByUserId(1);
		Assert.assertEquals(studentHomeworkList3, studentHomeworkList4);
		Assert.assertEquals(studentHomeworkList3.get(0).getUserId(), studentHomeworkList4.get(0).getUserId());
	}
	
	@Test
	public void testStudentHomeworkGetHomeWorkByStudentIdCourseIdAndHomeWorkId() {
		StudentHomeWork studentHomeWork2 = new StudentHomeWork().withCourseId(1)
				.withHomeWorkId(1).withId(2).withPath("/src/submission2.py").withUserId(2);
		List<StudentHomeWork> studentHomeworkList5 = new ArrayList<StudentHomeWork>();
		studentHomeworkList5.add(studentHomeWork2);
		when(studentHomeWorkRepository.findByUserIdAndCourseIdAndHomeWorkId(2,1,1)).thenReturn(studentHomeworkList5);
		
		List<StudentHomeWork> studentHomeworkList6 = studentHomeWorkService.getHomeWorkByStudentIdCourseIdAndHomeWorkId(2,1,1);

		verify(studentHomeWorkRepository, times(1)).findByUserIdAndCourseIdAndHomeWorkId(2,1,1);
		Assert.assertEquals(studentHomeworkList5, studentHomeworkList6);
		Assert.assertEquals(studentHomeworkList5.get(0).getCourseId(), studentHomeworkList6.get(0).getCourseId());
	}
	
	@Test(expected = HttpStatusException.class)
	public void testStudentHomeworkGetHomeWorkByStudentIdCourseIdAndHomeWorkIdForNullHomeworkId() {
		when(studentHomeWorkRepository.findByUserIdAndCourseIdAndHomeWorkId(3,5,8)).thenReturn(null);

		studentHomeWorkService.getHomeWorkByStudentIdCourseIdAndHomeWorkId(3,5,8);

		fail();
	}
	
	@Test
	public void testStudentHomeworkSubmitHomeWork() {
		StudentHomeWork studentHomeWork2 = studentHomeWorkService.submitHomeWork(1,1,1,"/src/submission1.py");
		Assert.assertEquals(1, studentHomeWork2.getHomeWorkId());
		Assert.assertEquals(1, studentHomeWork2.getCourseId());
		Assert.assertEquals("/src/submission1.py", studentHomeWork2.getFilePath());
		Assert.assertEquals(1, studentHomeWork2.getUserId());
		Assert.assertEquals(0, studentHomeWork2.getId());
	}

}
