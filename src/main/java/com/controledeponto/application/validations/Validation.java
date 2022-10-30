package com.controledeponto.application.validations;

import com.controledeponto.application.anonation.NotEmpty;

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
                        System.out.println("Campo Vazio");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
