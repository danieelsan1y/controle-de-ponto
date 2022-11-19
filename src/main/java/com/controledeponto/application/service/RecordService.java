package com.controledeponto.application.service;

import com.controledeponto.application.dto.RecordDTO;
import com.controledeponto.application.dto.RecordFaultsDTO;
import com.controledeponto.application.enums.DayOfWeek;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RecordService extends GenericCrudService<Record, Long> {

    DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    DateTimeFormatter sdf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
                .findPeriod(initialDate, finalDate, person.getId());

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

    public List<RecordFaultsDTO> listOflack(LocalDate fristPeriod, LocalDate secondPeriod, Long id) {
        LocalDateTime initialDate = returnLocalDateTime(fristPeriod, 00, 00);
        LocalDateTime finalDate = returnLocalDateTime(secondPeriod, 23, 59);

        Optional<Person> person = personRepository.findById(id);
        vefifiPeriodPerson(person);

        List<Record> records = recordRepository.findPeriod(initialDate, finalDate, person.get().getId());
        verifyPeriod(records);


        return getFaults(fristPeriod, secondPeriod, records);

    }

    private LocalDateTime returnLocalDateTime(LocalDate localDate, Integer hour, Integer minute) {
        return LocalDateTime.of(localDate.getYear(),
                localDate.getMonth(),
                localDate.getDayOfMonth(),
                hour,
                minute);
    }

    private List<RecordFaultsDTO> getFaults(LocalDate fristPeriod, LocalDate secondPeriod, List<Record> records) {
        List<String> workingDays = workingDays(fristPeriod, secondPeriod);
        Set<String> workedDays = workedDays(records);
        Map<String, String> faultes = new HashMap<>();

        workingDays.stream().forEach(it -> {
            if (!workedDays.contains(it)) {
                LocalDate localDate = LocalDate.parse(it, sdf2);
                faultes.put(it, localDate.getDayOfWeek().toString());
            }
        });
        System.out.println(faultes.keySet());
        return faultes.entrySet()
                .stream().
                map(it -> new RecordFaultsDTO(it.getKey(),
                        DayOfWeek.dayForWeekBrazil(it.getValue()))).collect(Collectors.toList());
    }

    private List<String> workingDays(LocalDate fristPeriod, LocalDate secondPeriod) {
        List<String> periodDays = new ArrayList<>();
        LocalDate dateAux = fristPeriod;

        while (dateAux.compareTo(secondPeriod) <= 0) {
            if (!DayOfWeek.returnCode(String.valueOf(dateAux.getDayOfWeek())).equals(0)
                    && !DayOfWeek.returnCode(String.valueOf(dateAux.getDayOfWeek())).equals(6)) {
                periodDays.add(sdf2.format(dateAux));
            }
            dateAux = dateAux.plusDays(1);
        }
        return periodDays;
    }

    private Set<String> workedDays(List<Record> records) {
        Set<String> datesWorking = new LinkedHashSet<>();
        records.stream().forEach(it -> {
            String dateString = it.getInstantRecord().getDayOfMonth()
                    + "/" + it.getInstantRecord().getMonth().getValue()
                    + "/" + it.getInstantRecord().getYear();
            datesWorking.add(dateString);
        });
        return datesWorking;
    }

    private void verifyPeriod(List<Record> dates) {
        if (dates.isEmpty()) {
            throw new ServiceException(Messages.EMPTY_PERIOD.getDescription());
        }
    }

    private void vefifiPeriodPerson(Optional<Person> person) {
        if (!person.isPresent()) {
            throw new ServiceException(Messages.UNREGISTERED_PERSON.getDescription());
        }
    }

}
