package com.controledeponto.application.controller;

import com.controledeponto.application.dto.RecordDTO;
import com.controledeponto.application.dto.RecordFaultsDTO;
import com.controledeponto.application.dto.RecordReturnDTO;
import com.controledeponto.application.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/record")
@CrossOrigin(origins = "http://localhost:4200")
public class RecordController {


    @Autowired
    RecordService recordService;


    @PostMapping
    public ResponseEntity<RecordReturnDTO> insert(@RequestBody RecordDTO recordDTO) {
        return ResponseEntity
                .ok()
                .body(new RecordReturnDTO(recordService.insert(recordDTO)));
    }

    @GetMapping(value = "/lacks")
    public ResponseEntity<List<RecordFaultsDTO>> listOflack(@RequestParam(name = "fristPeriod") LocalDate fristPeriod,
                                                            @RequestParam(name = "secondPeriod") LocalDate secondPeriod,
                                                            @RequestParam(name = "login") String login) {
        return ResponseEntity.ok().body(recordService.listOflack(fristPeriod, secondPeriod, login));
    }
}
