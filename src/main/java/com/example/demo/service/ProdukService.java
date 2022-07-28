package com.example.demo.service;

import com.example.demo.dao.ProdukDAO;
import com.example.demo.dto.ProdukDTO;
import com.example.demo.entity.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdukService {

    @Autowired
    private ProdukDAO dao;

    public List<Produk> findAll() {
        return dao.findAll();
    }

    public Optional<Produk> findById(Integer id) {
        return dao.findById(id);
    }


    public ProdukDTO.New save(ProdukDTO.New produk) {
        return dao.save(produk);
    }

    public ProdukDTO.Update update(ProdukDTO.Update produk) {
        return dao.update(produk);
    }

    public void delete(Integer id) {
        dao.delete(id);
    }
}
