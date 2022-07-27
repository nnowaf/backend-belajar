package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProdusenDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class New {
        @NotNull
        @NotEmpty
        private String nama;

        @NotNull
        @NotEmpty
        private String kode;

        @NotNull
        @NotEmpty
        private String alamat;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @NotNull
        private int id;

        @NotNull
        @NotEmpty
        private String nama;

        @NotNull
        @NotEmpty
        private String kode;

        @NotNull
        @NotEmpty
        private  String alamat;
    }
}
