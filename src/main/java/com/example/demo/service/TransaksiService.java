package com.example.demo.service;

import com.example.demo.dao.TransaksiDAO;
import com.example.demo.dto.TransaksiDTO;
import com.example.demo.entity.Transaksi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransaksiService {

    @Autowired
    private TransaksiDAO dao;

    public List<Transaksi> findAll() {
        return dao.findAll();
    }

    public Optional<Transaksi> findById(Integer id) {
        return dao.findById(id);
    }

    public TransaksiDTO.New save(TransaksiDTO.New transaksi) {
        return dao.save(transaksi);
    }

    public TransaksiDTO.Update update(TransaksiDTO.Update transaksi) {
        return dao.update(transaksi);
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public TransaksiDTO.Detail detailTransaksi(Integer id) {
        return dao.detailTransaksi(id);
    }

}
