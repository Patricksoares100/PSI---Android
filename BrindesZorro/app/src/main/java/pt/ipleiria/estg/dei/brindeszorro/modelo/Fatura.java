package pt.ipleiria.estg.dei.brindeszorro.modelo;

import java.time.LocalDateTime;

public class Fatura {

    private int id;
    private int valorFatura; // Agora é um int
    private String estado;

    public Fatura(int id, int valorFatura, String estado) {
        this.id = id;
        //this.data = data;
        this.valorFatura = valorFatura;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getValorFatura() {
        return valorFatura;
    }

    public void setValorFatura(int valorFatura) {
        this.valorFatura = valorFatura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
