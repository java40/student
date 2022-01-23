package telran.java40.students.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Student id not found", code = HttpStatus.NOT_FOUND)

// to do a custom exception 
public class StudentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4222237895860597634L;

	public StudentNotFoundException() {
	}

	public StudentNotFoundException(int id) {
		super("Student id " + id + " not found");
	}
}
