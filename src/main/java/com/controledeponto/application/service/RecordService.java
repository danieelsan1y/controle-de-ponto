package com.controledeponto.application.service;

import com.controledeponto.application.dto.RecordDTO;
import com.controledeponto.application.enums.StatusPerson;
import com.controledeponto.application.enums.TypeRecord;
import com.controledeponto.application.exceptions.service.ServiceException;
import com.controledeponto.application.message.Messages;
import com.controledeponto.application.model.Person;
import com.controledeponto.application.model.Record;
import com.controledeponto.application.repositories.PersonRepository;
import com.controledeponto.application.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService extends GenericCrudService<Record, Long> {

    DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public JpaRepository<Record, Long> getRepository() {
        return this.recordRepository;
    }

    @Override
    public void initInsert(Record record) {
    }

    @Override
    public void verifyUniqueElement(Record record) {
    }

    @Override
    public void validateEnums(Record record) {
    }

    public Long insert(RecordDTO recordDTO) {
        Person person = this.validateLogin(recordDTO);
        verifyStatus(person.getStatus());

        Record record = super.insert(new Record(
                     null,
                        this.returnTipe(person),
                        LocalDateTime.now(),
                        person));

        return record.getId();
    }

    public Person validateLogin(RecordDTO personLoginDTO) {

        Person person = Optional.ofNullable(personRepository.findByLogin(personLoginDTO.getLogin()))
                .flatMap(it -> it)
                .orElseThrow(() -> new ServiceException(Messages.AUTHENTICATION_ERROR.getDescription()));

        if (!person.getPassword().equals(personLoginDTO.getPassword())) {
            throw new ServiceException(Messages.AUTHENTICATION_ERROR.getDescription());
        }
        return person;
    }

    public void verifyStatus(StatusPerson statusPerson) {
        if (statusPerson.equals(StatusPerson.INATIVO)) {
            throw new ServiceException(Messages.PERSON_INACTIVE_RECORD.getDescription());
        }
    }

    public TypeRecord returnTipe(Person person) {
        LocalDate date = LocalDate.now();
        LocalDateTime finalDate = LocalDateTime.of(date.getYear(),
                date.getMonth(),
                date.getDayOfMonth(),
                23,
                59),
                initialDate = date.atStartOfDay();

        List<Record> records = recordRepository
                .findOneDay(initialDate, finalDate, person.getId());

        if (records.size() >= 4) {
            throw new ServiceException(Messages.FOUR_RECORS.getDescription());
        }

        if (records.isEmpty()) {
            return TypeRecord.ENTRADA;
        } else if (records.get(records.size() - 1).getType().equals(TypeRecord.ENTRADA)) {
            return TypeRecord.SAIDA;
        }

        return TypeRecord.ENTRADA;
    }
}
