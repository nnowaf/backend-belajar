package com.example.demo.dto;

import com.example.demo.entity.Produk;
import com.example.demo.entity.Produsen;
import com.example.demo.entity.Transaksi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class TransaksiDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class New {
        @NotNull
        private Integer kuantitas;
        @NotNull
        private Produk produk;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @NotNull
        private Integer id;
        @NotNull
        private Integer kuantitas;
        @NotNull
        private Produk produk;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {

        private Integer id;
        private String produk;
        private String produsen;
        private Integer harga;
        private Integer kuantitas;
        private Double totalHarga;
    }
}
