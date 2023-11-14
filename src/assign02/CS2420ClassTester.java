package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This class contains tests for CS2420Class.
 * 
 * @author Aaron Wood and ??
 * @version 2023-08-31 
 */
public class CS2420ClassTester {

	private CS2420Class emptyClass, verySmallClass, smallClass, largeClass;

	@BeforeEach
	void setUp() throws Exception {
		emptyClass = new CS2420Class();

		verySmallClass = new CS2420Class();
		verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

		smallClass = new CS2420Class();
		smallClass.addAll("src/assign02/a_small_2420_class.txt");

		// FILL IN -- Extend this tester to add more tests for the CS 2420 classes above, as well as to
		// create and test larger CS 2420 classes.
		// (HINT: For larger CS 2420 classes, generate random names, uNIDs, contact info, and scores in a 
		// loop, instead of typing one at a time.)

		largeClass = new CS2420Class();
		for (int i = 1; i <= 100; i++) {
			String firstName = "Student" + i;
			String lastName = "Lastname" + i;
			int uNID = 1000000 + i;
			EmailAddress contactInfo = new EmailAddress(firstName, "gmail.com");

			CS2420Student student = new CS2420Student(firstName, lastName, uNID, contactInfo);
			for (int j = 0; j < 5; j++) {
				student.addScore(i, "assignment");
				student.addScore(i, "exam");
				student.addScore(i, "lab");
				student.addScore(i, "quiz");
			}
			largeClass.addStudent(student);
		}
	}

	// Empty CS 2420 class tests --------------------------------------------------------------------------
    @Test
	public void testEmptyAddStudent() {
		CS2420Student student = new CS2420Student("Thu", "Ha", 1234567, new EmailAddress("thuha", "gmail.com"));
		assertTrue(emptyClass.addStudent(student));
	}

	@Test
	public void testEmptyAddMultipleStudent() {
		CS2420Student student = new CS2420Student("Thu", "Ha", 1234567, new EmailAddress("thuha", "gmail.com"));
		CS2420Student student2 = new CS2420Student("Thu", "Ha", 1234567, new EmailAddress("thuha", "gmail.com"));
		CS2420Student student3 = new CS2420Student("John", "Doe", 1010101, new EmailAddress("john.doe", "example.com"));
		emptyClass.addStudent(student);
		assertFalse(emptyClass.addStudent(student2));
		assertTrue(emptyClass.addStudent(student3));
	}

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}

	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420Student> students = emptyClass.lookup(new EmailAddress("hello", "gmail.com"));
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
		ArrayList<EmailAddress> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420Student actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}

	@Test
	public void testVerySmallLookUpNonExistingStudent() {
		CS2420Student actual = verySmallClass.lookup(1429766);
		assertNull(actual);
	}

	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}

	@Test
	public void testVerySmallLookupContactInfoNonExistingStudent() {
		EmailAddress givenEmail = new EmailAddress("thuha", "gmail.com");
		ArrayList<CS2420Student> actual = verySmallClass.lookup(givenEmail);
		assertTrue(actual.isEmpty());
	}

	@Test
	public void testVerySmallLookUpContactInfo() {
		EmailAddress givenEmail = new EmailAddress("hello", "gmail.com");
		ArrayList<CS2420Student> actual = verySmallClass.lookup(givenEmail);

		CS2420Student expectedStudent = new CS2420Student("Riley", "Nguyen", 4545454, givenEmail);
		assertTrue(actual.contains(expectedStudent));
	}

	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101,
				new EmailAddress("hi", "gmail.com")));
		assertFalse(actual);
	}

	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010100,
				new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}

	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}

	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
		CS2420Student student = verySmallClass.lookup(2323232);
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
	public void testFinalScoreInvalidCatergory() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "Assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}

	@Test
	public void testVerySmallComputeAverage() {
		// The expected average can be computed manually based on student scores
		double expectedAverage = (0 + 0 + 0) / 3.0;
		assertEquals(expectedAverage, verySmallClass.computeClassAverage(), 0.001);
	}



	// Small CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupContactInfo() {
		UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
		UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

		ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
		assertEquals(2, actualStudents.size());
		assertTrue(actualStudents.contains(expectedStudent1));
		assertTrue(actualStudents.contains(expectedStudent2));
	}

	@Test
	public void testSmallGetContactList() {
		ArrayList<EmailAddress> actual = smallClass.getContactList();
		assertEquals(9, actual.size());
	}

	@Test
	public void testSmallStudentFinalScore() {
		CS2420Student student = smallClass.lookup(333333);
		assertEquals(95.6027, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testSmallComputeClassAverage() {
		assertEquals(78.319, smallClass.computeClassAverage(), 0.001);
	}

	// Large CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testLargeLookupContactInfo() {
		// Select a random student's contact info to look up
		int uNID = 1000001;
		CS2420Student randomStudent = largeClass.lookup(uNID);
		EmailAddress contactToLookup = randomStudent.getContactInfo();

		// Use the lookup method to retrieve students with the same contact info
		ArrayList<CS2420Student> actualStudents = largeClass.lookup(contactToLookup);

		// Check if the random student is in the list
		assertTrue(actualStudents.contains(randomStudent));

		// Check if the list size is correct (at least one student with the same contact info)
		assertTrue(actualStudents.size() >= 1);
	}

	@Test
	public void testLargeGetContactList() {
		// Retrieve the actual contact list
		ArrayList<EmailAddress> actualContacts = largeClass.getContactList();

		// Create an array of expected email addresses
		ArrayList<EmailAddress> expectedContacts = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			EmailAddress email = new EmailAddress("Student" + i, "gmail.com");
			expectedContacts.add(email);
		}

		// Check if each expected email address is in the actual contact list
		for (EmailAddress expectedContact : expectedContacts) {
			assertTrue(actualContacts.contains(expectedContact));
		}

		// Check if the contact list size matches the expected size
		assertEquals(expectedContacts.size(), actualContacts.size());
	}

	@Test
	public void testLargeStudentFinalScore() {
		CS2420Student student = new CS2420Student("Thu", "Ha", 1429766, new EmailAddress("thu.ha", "gmail.com"));
		student.addScore(100.0, "exam");
		student.addScore(100.0, "assignment");
		student.addScore(95.0, "lab");
		student.addScore(95.0, "quiz");
		largeClass.addStudent(student);
		double expectedFinalScore = (100 * 0.45) + (100 * 0.35) + (95 * 0.10) + (95 * 0.10);
		assertEquals(expectedFinalScore, student.computeFinalScore(), 0.001);
	}


	@Test
	public void testLargeComputeClassAverage() {
		assertEquals(50.5, largeClass.computeClassAverage(), 0.001);
	}

	@Test
	public void testLargeComputeAverageDifferentScores() {
		// The expected average can be computed manually based on student scores
		double expectedAverage = 0.45 * 50.5 + 0.35 * 50.5 + 0.10 * 50.5 + 0.10 * 50.5 ;
		assertEquals(expectedAverage, largeClass.computeClassAverage(), 0.001);
	}

	@Test
	public void testAddStudentsFromFile() {
		CS2420Student student = smallClass.lookup(111111);
		assertNotNull(student);
		assertEquals("Samantha", student.getFirstName());
		assertEquals("Smith", student.getLastName());
		assertEquals(111111, student.getUNID());
		assertEquals(new EmailAddress("sam.smith", "geemail.com"), student.getContactInfo());
	}
}
