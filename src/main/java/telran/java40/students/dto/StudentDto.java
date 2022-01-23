package telran.java40.students.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StudentDto {
	Integer id;
	String name;
	@Singular
	Map<String, Integer> scores;
}
