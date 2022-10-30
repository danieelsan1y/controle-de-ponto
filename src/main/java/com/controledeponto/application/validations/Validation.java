package com.controledeponto.application.validations;

import com.controledeponto.application.anonation.NotEmpty;
import com.controledeponto.application.exceptions.service.ServiceException;

import java.lang.reflect.Field;

public class Validation<Entity> {

    public void verifyNullFiled(Entity entity) {
        Class<?> clas = entity.getClass();
        Field[] atributes = clas.getDeclaredFields();

        for (Field attr : atributes) {
            if (attr.isAnnotationPresent(NotEmpty.class)) {
                try {
                    attr.setAccessible(true);
                    if (attr.get(entity).toString().isEmpty()) {
                        new ServiceException("Campo(s) em branco!");
                    }
                } catch (IllegalAccessException e) {
                    throw new ServiceException("Ocorreu um erro inesperado");
                }
            }
        }

    }

}
