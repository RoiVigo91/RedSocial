package com.campusdual.redsocial.model;

import java.time.LocalDate;

public class Comentario {

    // propiedades
    private String textoComentario;
    private LocalDate fechaComentario;
    private Usuario usuarioPropietarioComentario;

    // constructores
    public Comentario(final String textoComentario, final Usuario usuarioPropietarioComentario) {
        this.textoComentario = textoComentario;
        this.fechaComentario = LocalDate.now();
        this.usuarioPropietarioComentario = usuarioPropietarioComentario;
    }

    // getters y setters
    public String getTextoComentario() {
        return this.textoComentario;
    }

    public void setTextoComentario(final String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public LocalDate getFechaComentario() {
        return this.fechaComentario;
    }

    public void setFechaComentario(final LocalDate fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public Usuario getUsuarioPropietarioComentario() {
        return this.usuarioPropietarioComentario;
    }

    public void setUsuarioPropietarioComentario(final Usuario usuarioPropietarioComentario) {
        this.usuarioPropietarioComentario = usuarioPropietarioComentario;
    }

}
