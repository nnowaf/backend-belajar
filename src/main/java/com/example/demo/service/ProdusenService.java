package com.example.demo.service;

import com.example.demo.dao.ProdusenDAO;
import com.example.demo.dto.ProdusenDTO;
import com.example.demo.entity.Produsen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdusenService {

    @Autowired
    private ProdusenDAO dao;

    public List<Produsen> findAll() {
        return dao.findAll();
    }

    public ProdusenDTO.New save(ProdusenDTO.New produsen) {
        return dao.save(produsen);
    }

    public ProdusenDTO.Update update(ProdusenDTO.Update produsen) {
        return dao.update(produsen);
    }

    public void delete(Integer id) {
        dao.delete(id);
    }


}
