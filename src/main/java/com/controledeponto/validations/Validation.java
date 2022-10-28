package com.controledeponto.validations;

import com.controledeponto.anonation.Empty;
import org.aspectj.apache.bcel.generic.Type;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class Validation implements ConstraintValidator<Empty,Validation> {

    public static void verifyNullFiled(Validation validation) {
        Class<?> clas = validation.getClass();
        Field[] atributes = clas.getDeclaredFields();

        for (Field attr : atributes) {
            if (attr.isAnnotationPresent(Empty.class)) {
                try {
                        if(attr.get(validation).toString().isEmpty()) {
                            System.out.println("Campo Vazio");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    public boolean isValid(Validation value, ConstraintValidatorContext context) {
        return false;
    }
}
