package com.example.demo.controller;

import com.example.demo.dto.ProdukDTO;
import com.example.demo.entity.Produk;
import com.example.demo.service.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produk")
public class ProdukController {

    @Autowired
    private ProdukService service;

    @GetMapping("/list")
    public List<Produk> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(
            @PathVariable("id")
            Integer id
    ) {
        Optional<Produk> response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody
            @Valid
            ProdukDTO.New produk
    ) {
       try {
          ProdukDTO.New response = service.save(produk);
          return ResponseEntity.ok(response);
       } catch (DataAccessException dae) {
           return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(
            @RequestBody
            @Valid
            ProdukDTO.Update produk
    ) {
        try {
            ProdukDTO.Update response = service.update(produk);
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
