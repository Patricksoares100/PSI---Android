package pt.ipleiria.estg.dei.brindeszorro.modelo;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fatura {

    private int id;
    private String data;
    private double valorFatura; // Agora é um int
    private String estado;
    private int perfil_id;

    public Fatura(int id, String data, double valorFatura, String estado, int perfil_id) {
        this.id = id;
        this.data = data;
        this.valorFatura = valorFatura;
        this.estado = estado;
        this.perfil_id = perfil_id;
    }

    public Fatura() {
    }

    public enum EstadoFatura {
        PAGA,
        EMITIDA
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValorFatura() {
        return valorFatura;
    }

    public void setValorFatura(double valorFatura) {
        this.valorFatura = valorFatura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPerfil_id() {
        return perfil_id;
    }

    public void setPerfil_id(int perfil_id) {
        this.perfil_id = perfil_id;
    }
}
