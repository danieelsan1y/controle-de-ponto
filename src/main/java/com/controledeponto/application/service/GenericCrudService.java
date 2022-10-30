package com.controledeponto.application.service;
import com.controledeponto.application.validations.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericCrudService<EntityName, TypePk> extends Validation<EntityName> {


    public abstract JpaRepository<EntityName, TypePk> getRepository();

    public abstract void initInsert(EntityName entityName);

    public EntityName insert(EntityName entityName) {
        this.verifyNullFiled(entityName);
        this.verifyUniqueElement(entityName);
        this.toUppercase(entityName);
        this.initInsert(entityName);
        return getRepository().save(entityName);
    }

    public List<EntityName> findAll() {
        return getRepository().findAll();
    }

    public EntityName findbyId(TypePk id) {
        return getRepository().findById(id).get();
    }

    public abstract void verifyUniqueElement (EntityName entityName);


}
