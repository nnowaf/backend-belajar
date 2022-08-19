package com.example.demo.dao;

import com.example.demo.dto.TransaksiDTO;
import com.example.demo.entity.Produk;
import com.example.demo.entity.Produsen;
import com.example.demo.entity.Transaksi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TransaksiDAO {

    @Autowired
    private NamedParameterJdbcTemplate jbdcTemplate;

    public List<Transaksi> findAll() {
        String query = "select\n" +
                "\tt.id as transaksiId,\n" +
                "\tt.produk_id as produkId,\n" +
                "\tt.kuantitas as transaksiKuantitas,\n" +
                "\t\n" +
                "\tp.id as produkId,\n" +
                "\tp.nama as produkNama,\n" +
                "\tp.jenis as produkJenis,\n" +
                "\tp.berat as produkBerat,\n" +
                "\tp.harga as produkHarga,\n" +
                "\tp.produsen_id as produsenId,\n" +
                "\t\n" +
                "\tp2.id as produsenId,\n" +
                "\tp2.nama as produsenNama,\n" +
                "\tp2.kode as produsenKode,\n" +
                "\tp2.alamat as produsenAlamat\n" +
                "from\n" +
                "\ttransaksi t\n" +
                "left join produk p on\n" +
                "\tt.id = p.id\n" +
                "left join produsen p2 on\n" +
                "\tt.id = p2.id ;";
        return jbdcTemplate.query(query, new RowMapper<Transaksi>() {
            @Override
            public Transaksi mapRow(ResultSet rs, int rowNum) throws SQLException {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(rs.getInt("transaksiId"));
                transaksi.setKuantitas(rs.getInt("transaksiKuantitas"));

                Produk produk = new Produk();
                produk.setId(rs.getInt("produkId"));
                produk.setNama(rs.getString("produkNama"));
                produk.setJenis(rs.getString("produkJenis"));
                produk.setBerat(rs.getInt("produkBerat"));
                produk.setHarga(rs.getDouble("produkHarga"));
                produk.setHarga(rs.getDouble("produkHarga"));

                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("produsenId"));
                produsen.setNama(rs.getString("produsenNama"));
                produsen.setKode(rs.getString("produsenKode"));
                produsen.setAlamat(rs.getString("produsenAlamat"));

                transaksi.setProduk(produk);
                produk.setProdusen(produsen);
                return transaksi;
            }
        });
    }

    public TransaksiDTO.Detail detailTransaksi(Integer id) {
        String query = "select\n" +
                "\tt.id as id,\n" +
                "\tp.nama as produk,\n" +
                "\tp2.nama as produsen,\n" +
                "\tp.harga as harga,\n" +
                "\tt.kuantitas as kuantitas,\n" +
                "\t(harga * kuantitas) as totalHarga\n" +
                "from\n" +
                "\ttransaksi t, produk p , produsen p2 \n" +
                "where t.id =:id\n" +
                "group by\n" +
                "\tt.id ,\n" +
                "\tp.nama ,\n" +
                "\tp2.nama ,\n" +
                "\tp.harga ,\n" +
                "\tt.kuantitas;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jbdcTemplate.queryForObject(query, map, new RowMapper<TransaksiDTO.Detail>() {
            @Override
            public TransaksiDTO.Detail mapRow(ResultSet rs, int rowNum) throws SQLException {
                TransaksiDTO.Detail detailTransaksi = new TransaksiDTO.Detail();
                detailTransaksi.setId(rs.getInt("id"));
                detailTransaksi.setProduk(rs.getString("produk"));
                detailTransaksi.setProdusen(rs.getString("produsen"));
                detailTransaksi.setHarga(rs.getInt("harga"));
                detailTransaksi.setKuantitas(rs.getInt("kuantitas"));
                detailTransaksi.setTotalHarga(rs.getDouble("totalHarga"));
                return detailTransaksi;
            }
        });
    }

    public Optional<Transaksi> findById(Integer id) {
        String query = "select\n" +
                "\tt.id as transaksiId,\n" +
                "\tt.produk_id as transaksiProduk,\n" +
                "\tt.kuantitas as transaksiKuantitas,\n" +
                "\t\n" +
                "\tp.id as produkId,\n" +
                "\tp.nama as produkNama,\n" +
                "\tp.jenis as produkJenis,\n" +
                "\tp.berat as produkBerat,\n" +
                "\tp.harga as produkHarga,\n" +
                "\tp.produsen_id as produsenId,\n" +
                "\t\n" +
                "\tp2.id as produsenId,\n" +
                "\tp2.nama as produsenNama,\n" +
                "\tp2.kode as produsenKode,\n" +
                "\tp2.alamat as produsenAlamat\n" +
                "from\n" +
                "\ttransaksi t\n" +
                "left join produk p on\n" +
                "\tt.id = p.id\n" +
                "left join produsen p2 on\n" +
                "\tt.id = p2.id\n" +
                "\twhere t.id=:id;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jbdcTemplate.queryForObject(query, map, new RowMapper<Optional<Transaksi>>() {
            @Override
            public Optional<Transaksi> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(rs.getInt("transaksiId"));
                transaksi.setKuantitas(rs.getInt("transaksiKuantitas"));

                Produk produk = new Produk();
                produk.setId(rs.getInt("produkId"));
                produk.setNama(rs.getString("produkNama"));
                produk.setJenis(rs.getString("produkJenis"));
                produk.setBerat(rs.getInt("produkBerat"));
                produk.setHarga(rs.getDouble("produkHarga"));
                produk.setHarga(rs.getDouble("produkHarga"));

                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("produsenId"));
                produsen.setNama(rs.getString("produsenNama"));
                produsen.setKode(rs.getString("produsenKode"));
                produsen.setAlamat(rs.getString("produsenAlamat"));

                transaksi.setProduk(produk);
                produk.setProdusen(produsen);
                return Optional.of(transaksi);
            }
        });
    }

    public TransaksiDTO.New save(TransaksiDTO.New transaksi) {
        String query = "INSERT INTO transaksi\n" +
                "(kuantitas, produk_id)\n" +
                "VALUES(:kuantitas, :produkId);";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kuantitas", transaksi.getKuantitas());
        map.addValue("produkId", transaksi.getProduk().getId());
        jbdcTemplate.update(query, map);
        return transaksi;
    }

    public TransaksiDTO.Update update(TransaksiDTO.Update transaksi) {
        String query = "update\n" +
                "\ttransaksi\n" +
                "set\n" +
                "\tkuantitas =:kuantitas,\n" +
                "\tproduk_id =:produkId\n" +
                "where\n" +
                "\tid = :id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", transaksi.getId());
        map.addValue("produkId", transaksi.getProduk().getId());
        map.addValue("kuantitas", transaksi.getKuantitas());
        jbdcTemplate.update(query, map);
        return transaksi;
    }

    public void delete(Integer id) {
        String query = "DELETE FROM transaksi WHERE id=:id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        jbdcTemplate.update(query, map);
    }

}
