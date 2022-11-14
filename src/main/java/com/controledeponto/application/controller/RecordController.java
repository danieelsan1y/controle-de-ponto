package com.controledeponto.application.controller;

import com.controledeponto.application.dto.RecordDTO;
import com.controledeponto.application.dto.RecordReturnDTO;
import com.controledeponto.application.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
public class RecordController {


    @Autowired
    RecordService recordService;


    @PostMapping
    public ResponseEntity<RecordReturnDTO> insert (@RequestBody RecordDTO recordDTO) {
        RecordReturnDTO recordReturnDTO = new RecordReturnDTO( recordService.register(recordDTO));
        return ResponseEntity
                .ok()
                .body(recordReturnDTO);

    }
}
