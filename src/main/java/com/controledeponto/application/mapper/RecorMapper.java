package com.controledeponto.application.mapper;

import com.controledeponto.application.dto.PersonDTO;
import com.controledeponto.application.dto.RecordDTO;
import com.controledeponto.application.model.Person;
import org.modelmapper.ModelMapper;

public class RecorMapper {

    private static  final ModelMapper MODEL_MAPPER = new ModelMapper();

    public RecordDTO toRecordDTO(Record record) {
        return MODEL_MAPPER.map(record, RecordDTO.class);
    }

    public Record recordDTOToRecord(RecordDTO recordDTO) {
        return  MODEL_MAPPER.map(recordDTO, Record.class);
    }
}
