package com.example.model;

import com.example.controlroom.ui.Salas;
import java.sql.Time;
import java.util.Date;

public class ControlSalas {

    private Salas sala;
    private Date dataReserva;
    private Time horarioUsoInicio;
    private Time horarioUsoFinal;
    private int quantidadePessosas;

    public Salas getSala() {
        return sala;
    }

    public void setSala(Salas sala) {
        this.sala = sala;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Time getHorarioUsoInicio() {
        return horarioUsoInicio;
    }

    public void setHorarioUsoInicio(Time horarioUsoInicio) {
        this.horarioUsoInicio = horarioUsoInicio;
    }

    public Time getHorarioUsoFinal() {
        return horarioUsoFinal;
    }

    public void setHorarioUsoFinal(Time horarioUsoFinal) {
        this.horarioUsoFinal = horarioUsoFinal;
    }

    public int getQuantidadePessosas() {
        return quantidadePessosas;
    }

    public void setQuantidadePessosas(int quantidadePessosas) {
        this.quantidadePessosas = quantidadePessosas;
    }
}



