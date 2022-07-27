package com.example.demo.controller;

import com.example.demo.dto.ProdusenDTO;
import com.example.demo.entity.Produsen;
import com.example.demo.service.ProdusenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produsen")
public class ProdusenController {

    @Autowired
    private ProdusenService service;

    @GetMapping("/list")
    public List<Produsen> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(
            @PathVariable("id")
            Integer id
    ) {
        Optional<Produsen> response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody
            @Valid
            ProdusenDTO.New produsen
    ) {
        try {
            ProdusenDTO.New response = service.save(produsen);
            return ResponseEntity.ok(produsen);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(
            @RequestBody
            @Valid
            ProdusenDTO.Update produsen
    ) {
        try {
            ProdusenDTO.Update response = service.update(produsen);
            return ResponseEntity.ok(produsen);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id")
            Integer id
    ) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
