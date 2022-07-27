package com.example.demo.dao;

import com.example.demo.dto.ProdusenDTO;
import com.example.demo.entity.Produsen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class ProdusenDAO {

    @Autowired
    private NamedParameterJdbcTemplate jbdcTemplate;

    public List<Produsen> findAll() {
        String query = "SELECT id, nama, kode, alamat\n" +
                "FROM produsen;";

        return jbdcTemplate.query(query, new BeanPropertyRowMapper<>(Produsen.class));
    }

    public ProdusenDTO.New save(ProdusenDTO.New produsen) {
        String query = "INSERT into produsen\n" +
                "(nama, kode, alamat)\n" +
                "VALUES(:nama, :kode, :alamat);";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", produsen.getNama());
        map.addValue("kode", produsen.getKode());
        map.addValue("alamat", produsen.getAlamat());
        jbdcTemplate.update(query, map);
        return  produsen;
    }

    public ProdusenDTO.Update update(ProdusenDTO.Update produsen) {
        String query = "UPDATE produsen\n" +
                "SET nama=:nama, kode=:kode, alamat=:alamat\n" +
                "WHERE id=:id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", produsen.getId());
        map.addValue("nama", produsen.getNama());
        map.addValue("kode", produsen.getKode());
        map.addValue("alamat", produsen.getAlamat());
        jbdcTemplate.update(query, map);
        return  produsen;
    }

    public void delete(Integer id) {
        String query = "delete from produsen where id=:id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        jbdcTemplate.update(query, map);
    }


}
