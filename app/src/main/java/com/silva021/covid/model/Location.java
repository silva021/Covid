package com.silva021.covid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties({"id", "regiao"})

public class Location implements Serializable {
    @JsonIgnore
    public static final String BASE_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/";
    @JsonIgnore
    public static final int REQUEST_CODE = 21;
    @JsonIgnore
    public static final String KEY = "LOCATION_OBJECT";

    private String sigla, nome;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
