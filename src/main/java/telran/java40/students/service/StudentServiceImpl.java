package telran.java40.students.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java40.students.dao.StudentRepository;
import telran.java40.students.dto.ScoreDto;
import telran.java40.students.dto.StudentDto;
import telran.java40.students.dto.StudentsCredentialsDto;
import telran.java40.students.dto.UpdateStudentDto;
import telran.java40.students.dto.exceptions.StudentNotFoundException;
import telran.java40.students.model.Student;

@Service
//@Component
//application context
public class StudentServiceImpl implements StudentService {

	// to get a link from @Component in StudentRepositoryImpl
	// better to do it with interface
	StudentRepository studentRepository;
	ModelMapper modelMapper;

	@Autowired
	// better write @Autowired with constructor,
	// it will help u to do a tests
	// because u can't write a test with fields like repository
	public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean add(StudentsCredentialsDto studentsCredentialsDto) {
		if (studentRepository.existsById(studentsCredentialsDto.getId())) {
			return false;
		}
		Student student = modelMapper.map(studentsCredentialsDto, Student.class);
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto deleteStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		studentRepository.deleteById(id);
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentsCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		if (updateStudentDto.getName() != null) {
			student.setName(updateStudentDto.getName());
			
		}
		if (updateStudentDto.getPassword() != null) {
			student.setPassword(updateStudentDto.getPassword());
		}
		studentRepository.save(student);
		return modelMapper.map(student, StudentsCredentialsDto.class);
	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		if (scoreDto.getExamName() == null) {
			return false;
		}
		student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return true;
	}

	@Override
	public List<StudentDto> findStudentsByName(String name) {
		return studentRepository.findByNameIgnoreCase(name)
					.map(s -> modelMapper.map(s, StudentDto.class))
					.collect(Collectors.toList());
	}

	@Override
	public long getStudentsNamesQuantity(List<String> name) {
		return studentRepository.countByNameInIgnoreCase(name);
	}

	@Override
	public List<StudentDto> getStudentsByExamScore(String exam, int score) {
		return studentRepository.findByExamAndScoreGreateEqualsThan(exam, score)
					.map(s -> modelMapper.map(s, StudentDto.class))
					.collect(Collectors.toList());
//					.filter(s -> s.getScores().get(exam) > score)
//					.map(s -> StudentDto.builder()
//										.id(s.getId())
//										.name(s.getName())
//										.scores(s.getScores())
//										.build())
//					.collect(Collectors.toList());
	}

}
