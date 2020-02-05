package com.example.model;

import java.sql.Date;

public class Empresa {

        private int id;
        private String nomeEmpresa;
        private int idOrganizacaoPai;
        private String tipoEmpresa;
        private String emailEmpresa;
        private String dominio;
        private Date dataCriacao;
        private Date dataAlteraca;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public int getIdOrganizacaoPai() {
        return idOrganizacaoPai;
    }

    public void setIdOrganizacaoPai(int idOrganizacaoPai) {
        this.idOrganizacaoPai = idOrganizacaoPai;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAlteraca() {
        return dataAlteraca;
    }

    public void setDataAlteraca(Date dataAlteraca) {
        this.dataAlteraca = dataAlteraca;
    }
}

