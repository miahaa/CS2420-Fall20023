package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This class contains tests for CS2420ClassGeneric.
 * 
 * @author Aaron Wood and ??
 * @version 2023-08-31 
 */
public class CS2420ClassGenericTester {

	private CS2420ClassGeneric<String> emptyClass;
	private CS2420ClassGeneric<MailingAddress> verySmallClass;
	private CS2420ClassGeneric<PhoneNumber> largeClass;
	private CS2420ClassGeneric<Integer> phase3Class;
	private CS2420ClassGeneric<MailingAddress> oneStudentClass;

	@BeforeEach
	void setUp() throws Exception {
		emptyClass = new CS2420ClassGeneric<>();
		
		verySmallClass = new CS2420ClassGeneric<MailingAddress>();
		verySmallClass.addStudent(new CS2420StudentGeneric<MailingAddress>("Jane", "Doe", 1010101, 
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036)));
		verySmallClass.addStudent(new CS2420StudentGeneric<MailingAddress>("Drew", "Hall", 2323232, 
				new MailingAddress("156 Main St.", "Lebanon", "VA", 24266)));
		verySmallClass.addStudent(new CS2420StudentGeneric<MailingAddress>("Riley", "Nguyen", 4545454, 
				new MailingAddress("2044 State St.", "Lebanon", "PA", 17042)));

		largeClass = new CS2420ClassGeneric<>();
		PhoneNumber[] sharedNums = new PhoneNumber[5]; 
		sharedNums[0] = new PhoneNumber("801-555-1234");
		sharedNums[1] = new PhoneNumber("801-555-5678");
		sharedNums[2] = new PhoneNumber("801-555-9012");
		sharedNums[3] = new PhoneNumber("801-555-3456");
		sharedNums[4] = new PhoneNumber("801-555-7890");
		for(int i = 0; i < 500; i++) {
			String first = (char)('A' + i % 26) + "" + (char)('b' + i % 26);
			String last = (char)('C' + i % 26) + "" + (char)('d' + i % 26);
			int uNID = 1000000 + i;
			CS2420StudentGeneric<PhoneNumber> student = new CS2420StudentGeneric<PhoneNumber>(first, last, uNID, sharedNums[i % 5]);
			largeClass.addStudent(student);
			student.addScore(80 + i % 20, "assignment");
			student.addScore(75, "exam");
			student.addScore(90 + i % 10, "lab");
			student.addScore(80, "lab");
			student.addScore(80 + i % 20, "quiz");
			student.addScore(70, "quiz");
		}
		
		phase3Class = new CS2420ClassGeneric<Integer>();
		phase3Class.addStudent(new CS2420StudentGeneric<Integer>("A", "C", 3, 3));
		phase3Class.addStudent(new CS2420StudentGeneric<Integer>("A", "B", 2, 2));
		phase3Class.addStudent(new CS2420StudentGeneric<Integer>("A", "C", 1, 1));
		phase3Class.addStudent(new CS2420StudentGeneric<Integer>("D", "E", 4, 4));
		phase3Class.addScore(1, 70, "assignment");
		phase3Class.addScore(1, 70, "exam");
		phase3Class.addScore(1, 70, "lab");   phase3Class.addScore(1, 70, "lab");
		phase3Class.addScore(1, 70, "quiz");  phase3Class.addScore(1, 70, "quiz");
		phase3Class.addScore(2, 30, "assignment");
		phase3Class.addScore(2, 30, "exam");
		phase3Class.addScore(2, 30, "lab");   phase3Class.addScore(2, 30, "lab");
		phase3Class.addScore(2, 30, "quiz");  phase3Class.addScore(2, 30, "quiz");
		phase3Class.addScore(3, 70, "assignment");
		phase3Class.addScore(3, 70, "exam");
		phase3Class.addScore(3, 70, "lab");   phase3Class.addScore(3, 70, "lab");
		phase3Class.addScore(3, 70, "quiz");  phase3Class.addScore(3, 70, "quiz");
		phase3Class.addScore(4, 90, "assignment");
		phase3Class.addScore(4, 90, "exam");
		phase3Class.addScore(4, 90, "lab");   phase3Class.addScore(4, 90, "lab");
		phase3Class.addScore(4, 90, "quiz");  phase3Class.addScore(4, 90, "quiz");
	}
	
	// Empty CS 2420 class tests --------------------------------------------------------------------------

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}
	
	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420StudentGeneric<String>> students = emptyClass.lookup("hello");
		assertEquals(0, students.size());
	}
	
	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}
	
	@Test
	public void testEmptyContactList() {
		ArrayList<String> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420StudentGeneric<MailingAddress> actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}

	@Test
	public void testMultipleStudentsWithSameUNID() {
		CS2420StudentGeneric<MailingAddress> otherStudent = new CS2420StudentGeneric<>("Thu", "Ha", 1010101, new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036));
		verySmallClass.addStudent(otherStudent);
		CS2420StudentGeneric<MailingAddress> actual = verySmallClass.lookup(1010101);
		UofUStudent expected = new UofUStudent("Jane", "Doe", 1010101);
		assertEquals(expected, actual);
	}

	@Test
	public void testComputeFinalScoreForNonExistentStudent() {

		// Attempt to add scores for a student with uNID 1234567 (doesn't exist)
		verySmallClass.addScore(1234567, 90, "assignment");
		verySmallClass.addScore(1234567, 85, "exam");
		verySmallClass.addScore(1234567, 95, "lab");
		verySmallClass.addScore(1234567, 88, "quiz");

		// Ensure no exceptions are thrown and no students are added
		CS2420StudentGeneric<MailingAddress> student = new CS2420StudentGeneric<>("Thu", "Ha", 1234567, new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036));
		assertEquals(0, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testComputeFinalGradeForNonExistentStudent() {

		// Attempt to add scores for a student with uNID 1234567 (doesn't exist)
		verySmallClass.addScore(1234567, 90, "assignment");
		verySmallClass.addScore(1234567, 85, "exam");
		verySmallClass.addScore(1234567, 95, "lab");
		verySmallClass.addScore(1234567, 88, "quiz");

		// Ensure no exceptions are thrown and no students are added
		CS2420StudentGeneric<MailingAddress> student = new CS2420StudentGeneric<>("Thu", "Ha", 1234567, new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036));
		assertEquals("N/A", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420StudentGeneric<MailingAddress>> actualStudents = verySmallClass.lookup(
				new MailingAddress("2044 State St.", "Lebanon", "PA", 17042));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}
	
	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420StudentGeneric<MailingAddress>("Jane", "Doe", 1010101, 
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036)));
		assertFalse(actual);
	}
	
	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420StudentGeneric<MailingAddress>("Jane", "Doe", 1010100, 
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036)));
		assertTrue(actual);		
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	
	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(55, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(55, student.computeFinalScore(), 0.001);
	}
	
	@Test
	public void testVerySmallStudentFinalGrade() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentComputeScoreTwice() {
		CS2420StudentGeneric<MailingAddress> student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore(); 
		student.addScore(70, "lab");
		student.addScore(54.5, "exam");				
		assertEquals(64.75, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420StudentGeneric<MailingAddress>> students = verySmallClass.lookup(
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}
	
	// Large CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testLargeLookupContactInfo() {
		ArrayList<CS2420StudentGeneric<PhoneNumber>> actualStudents = largeClass.lookup(new PhoneNumber("801-555-1234"));
		assertEquals(100, actualStudents.size());
	}
	
	@Test
	public void testLargeGetContactList() {
		ArrayList<PhoneNumber> actual = largeClass.getContactList();
		assertEquals(5, actual.size());
		assertTrue(actual.contains(new PhoneNumber("801-555-1234")));
		assertTrue(actual.contains(new PhoneNumber("801-555-5678")));
		assertTrue(actual.contains(new PhoneNumber("801-555-9012")));
		assertTrue(actual.contains(new PhoneNumber("801-555-3456")));
		assertTrue(actual.contains(new PhoneNumber("801-555-7890")));
	}
		
	@Test
	public void testLargeStudentFinalScore() {
		CS2420StudentGeneric<PhoneNumber> student = largeClass.lookup(1000000);
		assertEquals(77.75, student.computeFinalScore(), 0.1);
	}
		
	@Test
	public void testLargeComputeClassAverage() {
		assertEquals(81.775, largeClass.computeClassAverage(), 0.1);
	}
	
	// Phase 3 tests -----------------------------------------------------------------------------------

	@Test
	public void testOrderedByUNID() {
		ArrayList<CS2420StudentGeneric<Integer>> actual = phase3Class.getOrderedByUNID();
		assertEquals(4, actual.size());
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 3, 3), actual.get(2));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "B", 2, 2), actual.get(1));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 1, 1), actual.get(0));
		assertEquals(new CS2420StudentGeneric<Integer>("D", "E", 4, 4), actual.get(3));
	}

	@Test
	public void testOrderByUnidEmptyList() {
		ArrayList<CS2420StudentGeneric<String>> emptyList = emptyClass.getOrderedByUNID();

		assertTrue(emptyList.isEmpty());
	}

	@Test
	public void TestOrderByUnidSingleStudent() {
		CS2420StudentGeneric<MailingAddress> student = new CS2420StudentGeneric<>("Thu", "Ha", 1234567, new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036));
		oneStudentClass = new CS2420ClassGeneric<>();
		oneStudentClass.addStudent(student);

		ArrayList<CS2420StudentGeneric<MailingAddress>> list = oneStudentClass.getOrderedByUNID();

		assertEquals(1, list.size());
		assertEquals(student, list.get(0));
	}
	
	@Test
	public void testOrderedByName() {
		ArrayList<CS2420StudentGeneric<Integer>> actual = phase3Class.getOrderedByName();
		assertEquals(4, actual.size());
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 3, 3), actual.get(2));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "B", 2, 2), actual.get(0));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 1, 1), actual.get(1));
		assertEquals(new CS2420StudentGeneric<Integer>("D", "E", 4, 4), actual.get(3));
	}

	@Test
	public void testOrderByNameSameLastName() {
		CS2420StudentGeneric<MailingAddress> student2 = new CS2420StudentGeneric<>("Thu", "Doe", 1000000, new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036));
		verySmallClass.addStudent(student2);

		ArrayList<CS2420StudentGeneric<MailingAddress>> actual = verySmallClass.getOrderedByName();

		assertEquals(4, actual.size());
		assertEquals(new CS2420StudentGeneric<>("Jane", "Doe", 1010101,
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036)), actual.get(0));
	}

	@Test
	public void testOrderByNameSameFirstName() {
		CS2420StudentGeneric<MailingAddress> student2 = new CS2420StudentGeneric<>("Jane", "Ha", 1000000, new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036));
		verySmallClass.addStudent(student2);

		ArrayList<CS2420StudentGeneric<MailingAddress>> actual = verySmallClass.getOrderedByName();

		assertEquals(4, actual.size());
		assertEquals(new CS2420StudentGeneric<>("Jane", "Doe", 1010101,
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036)), actual.get(0));
	}

	@Test
	public void testOrderDifferentUnidSameName() {
		CS2420StudentGeneric<MailingAddress> student2 = new CS2420StudentGeneric<>("Jane", "Doe", 1000000, new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036));
		verySmallClass.addStudent(student2);

		ArrayList<CS2420StudentGeneric<MailingAddress>> actual = verySmallClass.getOrderedByName();

		assertEquals(4, actual.size());
		assertEquals(new CS2420StudentGeneric<>("Jane", "Doe", 1010101,
				new MailingAddress("101 Cherry St.", "Lebanon", "OH", 45036)), actual.get(1));
	}
	
	@Test
	public void testOrderedByScore() {
		ArrayList<CS2420StudentGeneric<Integer>> actual = phase3Class.getOrderedByScore(0);
		assertEquals(4, actual.size());
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 3, 3), actual.get(2));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "B", 2, 2), actual.get(3));
		assertEquals(new CS2420StudentGeneric<Integer>("A", "C", 1, 1), actual.get(1));
		assertEquals(new CS2420StudentGeneric<Integer>("D", "E", 4, 4), actual.get(0));
	}

	@Test
	public void testOrderedByScoreEmptyClass() {
		ArrayList<CS2420StudentGeneric<String>> result = emptyClass.getOrderedByScore(65.0);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testOrderedByScoreWithNoOneHigherThanCutoff() {
		ArrayList<CS2420StudentGeneric<PhoneNumber>> result = largeClass.getOrderedByScore(100);
		assertTrue(0 == result.size());
	}
 }