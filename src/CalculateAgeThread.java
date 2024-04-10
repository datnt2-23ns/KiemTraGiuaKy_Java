import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class CalculateAgeThread implements Runnable {
	private static final List<Student> results = new ArrayList<>();

	public static List<Student> getResults() {
		return results;
	}

	@Override
	public void run() {
		List<Student> students = ReadXMLThread.getStudents();

		for (Student student : students) {
			int age = calculateAge(student.getDateOfBirth());
			Student result = new Student(student.getId(), student.getName(), student.getAddress(),
					student.getDateOfBirth());
			results.add(result);
		}

		System.out.println("Thread 2 đã tính tuổi cho các student.");
	}

	private int calculateAge(String dateOfBirth) {
		LocalDate birthDate = LocalDate.parse(dateOfBirth);
		LocalDate currentDate = LocalDate.now();

		Period period = Period.between(birthDate, currentDate);
		return period.getYears();
	}
}