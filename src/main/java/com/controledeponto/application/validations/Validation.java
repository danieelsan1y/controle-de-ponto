package com.controledeponto.application.validations;

import com.controledeponto.application.anonation.NotUpperCase;
import com.controledeponto.application.anonation.ValidationFields;
import com.controledeponto.application.exceptions.service.ServiceException;
import com.controledeponto.application.message.Messages;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Validation<Entity> {

    public void verifyNullFiled(Entity entity) {
        Field[] atributes = this.getAtributes(entity);

        for (Field attr : atributes) {
            if (attr.isAnnotationPresent(ValidationFields.class)) {
                try {
                    ValidationFields anotation = attr.getAnnotation(ValidationFields.class);
                    attr.setAccessible(true);
                    if(anotation.notEmpty()) {
                        if (attr.get(entity).toString().isEmpty()) {
                            throw new ServiceException(Messages.REQUIRED_FIELDS.getDescription());
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new ServiceException(Messages.UNEXPECTED_ERROR.getDescription());
                }
            }
        }
    }


    public void toUppercase(Entity entity) {
        Class <?> clas = getClass(entity);
        Field[] atributes = this.getAtributes(entity);

        Arrays.stream(atributes).forEach(field -> {

            try {
                if (field.getType().equals(String.class)) {
                    if (!field.isAnnotationPresent(NotUpperCase.class)) {
                        field.setAccessible(true);
                        field.set(entity, field.get(entity).toString().toUpperCase());
                    }
                }
            } catch (IllegalAccessException e) {
                throw new ServiceException(Messages.UNEXPECTED_ERROR.getDescription());
            }
        });
    }


    public Field[] getAtributes(Entity entity) {
        Class<?> clas = getClass(entity);
        Field[] atributes = clas.getDeclaredFields();
        return atributes;
    }
    public Class<?> getClass (Entity entity) {
        return entity.getClass();
    }
}
