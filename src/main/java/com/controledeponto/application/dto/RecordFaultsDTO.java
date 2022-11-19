package com.controledeponto.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecordFaultsDTO {


    private String fault;
    private String dayOfWeek;
}
