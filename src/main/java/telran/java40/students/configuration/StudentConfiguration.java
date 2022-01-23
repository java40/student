package telran.java40.students.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfiguration {

	@Bean
	// to put the method in application context
	// it's simple java object
	// object witch is placed in application context it's bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
						.setFieldMatchingEnabled(true)
						//to work with private fields
						.setFieldAccessLevel(AccessLevel.PRIVATE)
						.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	// how it works
	// if he will see what class has the same fields like the other class
	// u can use the mapper he will work automatically
	// Student student = modelMapper.map(studentsCredentialsDto, Student.class);
	// they have the same fields inside
}
