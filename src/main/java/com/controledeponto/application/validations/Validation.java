package com.controledeponto.application.validations;

import com.controledeponto.application.anonation.NotEmpty;
import com.controledeponto.application.anonation.NotUpperCase;
import com.controledeponto.application.exceptions.service.ServiceException;
import net.bytebuddy.implementation.bind.annotation.Empty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Validation<Entity> {

    public void verifyNullFiled(Entity entity) {
        Field[] atributes = this.getAtributes(entity);

        for (Field attr : atributes) {
            if (attr.isAnnotationPresent(NotEmpty.class)) {
                try {
                    attr.setAccessible(true);
                    if (attr.get(entity).toString().isEmpty()) {
                        throw new ServiceException("Campo(s) obrigatórios em branco!");
                    }
                } catch (IllegalAccessException e) {
                    throw new ServiceException("Ocorreu um erro inesperado");
                }
            }
        }
    }


    public void toUppercase(Entity entity) {
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
                throw new ServiceException("Ocorreu um erro inesperado");
            }
        });
    }

    public Field[] getAtributes(Entity entity) {
        Class<?> clas = entity.getClass();
        Field[] atributes = clas.getDeclaredFields();
        return atributes;
    }
}
