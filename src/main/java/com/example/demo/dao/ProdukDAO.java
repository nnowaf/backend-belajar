package com.example.demo.dao;

import com.example.demo.dto.ProdukDTO;
import com.example.demo.entity.Produk;
import com.example.demo.entity.Produsen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String query = "select\n" +
                "\tp.id as produkId,\n" +
                "\tp.nama as produkNama,\n" +
                "\tp.jenis as produkJenis,\n" +
                "\tp.berat as produkBerat,\n" +
                "\tp2.id as produsenId,\n" +
                "\tp2.nama as produsenNama,\n" +
                "\tp2.kode as produsenKode,\n" +
                "\tp2.alamat as produsenAlamat\n" +
                "from\n" +
                "\tproduk p\n" +
                "left join produsen p2 on\n" +
                "\tp.id = p2.id";

        return jbdcTemplate.query(query, new RowMapper<Produk>() {
            @Override
            public Produk mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produk produk = new Produk();
                produk.setId(rs.getInt("produkId"));
                produk.setNama(rs.getString("produkNama"));
                produk.setJenis(rs.getString("produkJenis"));
                produk.setBerat(rs.getInt("produkBerat"));

                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("produsenId"));
                produsen.setNama(rs.getString("produsenNama"));
                produsen.setKode(rs.getString("produsenKode"));
                produsen.setAlamat(rs.getString("produsenAlamat"));

                produk.setProdusen(produsen);
                return produk;
            }
        });
    }

    //melihat data dengan parameter id
    public Optional<Produk> findById(Integer id)  {
        String query = "select\n" +
                "\tp.id as produkId,\n" +
                "\tp.nama as produkNama,\n" +
                "\tp.jenis as produkJenis,\n" +
                "\tp.berat as produkBerat,\n" +
                "\tp2.id as produsenId,\n" +
                "\tp2.nama as produsenNama,\n" +
                "\tp2.kode as produsenKode,\n" +
                "\tp2.alamat as produsenAlamat\n" +
                "from\n" +
                "\tproduk p\n" +
                "left join produsen p2 on\n" +
                "\tp.id = p2.id\n" +
                "where\n" +
                "\tp.id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jbdcTemplate.queryForObject(query, map, new RowMapper<Optional<Produk>>() {
            @Override
            public Optional<Produk> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produk produk = new Produk();
                produk.setId(rs.getInt("produkId"));
                produk.setNama(rs.getString("produkNama"));
                produk.setJenis(rs.getString("produkJenis"));
                produk.setBerat(rs.getInt("produkBerat"));

                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("produsenId"));
                produsen.setNama(rs.getString("produsenNama"));
                produsen.setKode(rs.getString("produsenKode"));
                produsen.setAlamat(rs.getString("produsenAlamat"));

                produk.setProdusen(produsen);
                return Optional.of(produk);
            }
        });
    }

    //input data ke database
    public ProdukDTO.New save(ProdukDTO.New produk) {
        String query = "INSERT INTO produk\n" +
                "(nama, jenis, berat, produsen_id)\n" +
                "VALUES(:nama, :jenis, :berat, :produsenId);\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", produk.getNama());
        map.addValue("jenis", produk.getJenis());
        map.addValue("berat", produk.getBerat());
        map.addValue("produsenId", produk.getProdusen().getId());
        jbdcTemplate.update(query, map);
        return produk;
    }

    //update data ke database
    public ProdukDTO.Update update(ProdukDTO.Update produk) {
        String query = "UPDATE produk\n" +
                "SET nama=:nama, jenis=:jenis, berat=:berat, produsen_id=:produsenId\n" +
                "WHERE id=:id\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", produk.getId());
        map.addValue("nama", produk.getNama());
        map.addValue("jenis", produk.getJenis());
        map.addValue("berat", produk.getBerat());
        map.addValue("produsenId", produk.getProdusen().getId());
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
