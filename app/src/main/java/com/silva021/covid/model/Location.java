package com.silva021.covid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {
    public static final String BASE_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/";
    public static final int REQUEST_CODE = 21;
    public static final String KEY = "LOCATION_OBJECT";

    @SerializedName("sigla")
    @Expose
    private String sigla;

    @SerializedName("nome")
    @Expose
    private String nome;

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
