package fa.training.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Check {
	public static <T> void isValid(T obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<T>> violations2 = validator.validate(obj);
		
		if(violations2.size() > 0) {
			for(ConstraintViolation<?> violation : violations2) {
				System.out.println("["+violation.getInvalidValue() +"]"+ " : " + violation.getMessage());
			}
		}else {
			System.out.println("valid object");
		}
	}
}
