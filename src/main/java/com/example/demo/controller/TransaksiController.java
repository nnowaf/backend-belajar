package com.example.demo.controller;

import com.example.demo.dto.TransaksiDTO;
import com.example.demo.entity.Produk;
import com.example.demo.entity.Transaksi;
import com.example.demo.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiService service;

    @GetMapping("/list")
    public List<Transaksi> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(
            @PathVariable("id")
            Integer id) {
        Optional<Transaksi> response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<?> detailTransaksi(
            @PathVariable("id")
            Integer id) {
        try {
            TransaksiDTO.Detail response = service.detailTransaksi(id);
            return ResponseEntity.ok(response);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody
            @Valid
            TransaksiDTO.New transaksi
    ) {
        try {
            TransaksiDTO.New response = service.save(transaksi);
            return ResponseEntity.ok(response);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(
            @RequestBody
            @Valid
            TransaksiDTO.Update transaksi
    ) {
        try {
            TransaksiDTO.Update response = service.update(transaksi);
            return ResponseEntity.ok(response);
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
