package com.controledeponto.application.service;

import com.controledeponto.application.exceptions.service.ServiceException;
import com.controledeponto.application.message.Messages;
import com.controledeponto.application.validations.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class GenericCrudService<EntityName, TypePk> extends Validation<EntityName> {


    public abstract JpaRepository<EntityName, TypePk> getRepository();

    public abstract void initInsert(EntityName entityName);

    public EntityName insert(EntityName entityName) {
        this.verifyNullFiled(entityName);
        this.verifyUniqueElement(entityName);
        this.toUppercase(entityName);
        this.initInsert(entityName);
        this.validateEnums(entityName);
        return getRepository().save(entityName);
    }

    public List<EntityName> findAll() {
        return getRepository().findAll();
    }

    public EntityName findbyId(TypePk typePk) {
        return Optional.ofNullable(getRepository().findById(typePk))
                .flatMap(it -> it)
                .orElseThrow(() -> new ServiceException(Messages.UNREGISTERED_PERSON.getDescription()));
    }

    public void update(TypePk typePk, EntityName newEntity) {
        this.verifyNullFiled(newEntity);
        this.validateEnums(newEntity);
        final EntityName oldEntity = getRepository().findById(typePk)
                .map(it -> {
                    if (it != null) {
                        Field[] oldEntityAtributes = getAtributes(it);
                        Field[] newEntityAtributes = getAtributes(newEntity);

                        Arrays.stream(oldEntityAtributes)
                                .forEach(oldField -> {
                                    Arrays.stream(newEntityAtributes)
                                            .forEach(newField -> {
                                                if (!oldField.isAnnotationPresent(Id.class) && !newField.isAnnotationPresent(Id.class)) {
                                                    if (oldField.equals(newField)) {
                                                        try {
                                                            oldField.setAccessible(true);
                                                            newField.setAccessible(true);
                                                            if(newField.get(newEntity) != null) {
                                                                oldField.set(it,newField.get(newEntity));
                                                            }

                                                        } catch (IllegalAccessException e) {
                                                            throw new ServiceException(Messages.UNEXPECTED_ERROR.getDescription());
                                                        }
                                                    }
                                                }
                                            });
                                });

                        return it;
                    }

                    return null;
                })
                .orElseThrow(() -> new ServiceException("Elemento pesquisado não existe"));
        this.toUppercase(oldEntity);
        getRepository().save(oldEntity);
    }

    public abstract void verifyUniqueElement(EntityName entityName);

    public abstract void validateEnums (EntityName entityName);


}
