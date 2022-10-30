package com.controledeponto.application.service;


import com.controledeponto.application.anonation.ServiceName;
import com.controledeponto.application.validations.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericCrudService<EntityName, typePk> extends Validation<EntityName> {

    Class<?> entity;

    Class<?> service;

    public abstract JpaRepository<EntityName, typePk> getRepository();

    public abstract void initInsert(EntityName entityName);

    public EntityName insert(EntityName entityName) {
        this.verifyNullFiled(entityName);
        this.initInsert(entityName);
        return getRepository().save(entityName);
    }

    public List<EntityName> findAll() {
        return getRepository().findAll();
    }

    public EntityName findbyId(typePk id) {
        return getRepository().findById(id).get();
    }

    public void initServiceAndEntity(EntityName entityName) {
        this.entity = entityName.getClass();
        ServiceName serviceName = entity.getAnnotation(ServiceName.class);

        if (this.entity.isAnnotationPresent(ServiceName.class)) {
            System.out.println(serviceName.getClass());
            try {
                service= Class.forName(serviceName.name());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
