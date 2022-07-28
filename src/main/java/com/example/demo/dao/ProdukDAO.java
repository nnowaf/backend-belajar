package com.example.demo.dao;

import com.example.demo.dto.ProdukDTO;
import com.example.demo.entity.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdukDAO {

    @Autowired
    private NamedParameterJdbcTemplate jbdcTemplate;

    //melihat semua data yang ada
    public List<Produk> findAll() {
        String query = "SELECT id, nama, jenis, berat\n" +
                "FROM produk;";

        return jbdcTemplate.query(query, new BeanPropertyRowMapper<>(Produk.class));
    }

    //melihat data dengan parameter id
    public Optional<Produk> findById(Integer id) {
        String query = "SELECT id, nama, jenis, berat\n" +
                "FROM produk where id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jbdcTemplate.queryForObject(query, map, new RowMapper<Optional<Produk>>() {
            @Override
            public Optional<Produk> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produk produk = new Produk();
                produk.setId(rs.getInt("id"));
                produk.setNama(rs.getString("nama"));
                produk.setJenis(rs.getString("jenis"));
                produk.setBerat(rs.getInt("berat"));
                return Optional.of(produk);
            }
        });
    }

    //input data ke database
    public ProdukDTO.New save(ProdukDTO.New produk) {
        String query = "INSERT INTO produk\n" +
                "(nama, jenis, berat)\n" +
                "VALUES(:nama, :jenis, :berat);\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", produk.getNama());
        map.addValue("jenis", produk.getJenis());
        map.addValue("berat", produk.getBerat());
        jbdcTemplate.update(query, map);
        return produk;
    }

    //update data ke database
    public ProdukDTO.Update update(ProdukDTO.Update produk) {
        String query = "UPDATE produk\n" +
                "SET nama=:nama, jenis=:jenis, berat=:berat\n" +
                "WHERE id=:id\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", produk.getId());
        map.addValue("nama", produk.getNama());
        map.addValue("jenis", produk.getJenis());
        map.addValue("berat", produk.getBerat());
        jbdcTemplate.update(query, map);
        return produk;
    }

    //delete data database dari parameter id
    public void delete(Integer id) {
        String query = "DELETE FROM produk WHERE id=:id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        jbdcTemplate.update(query, map);
    }
}
