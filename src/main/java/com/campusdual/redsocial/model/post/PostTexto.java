package com.campusdual.redsocial.model.post;

import com.campusdual.redsocial.model.Usuario;

public class PostTexto extends Post {

    // propiedades
    private String contenidoPostText;

    // constructores
    public PostTexto(final String titulo, final Usuario usuarioPropietarioPost, final String contenido) {
        super(titulo, usuarioPropietarioPost);
        this.contenidoPostText = contenido;
    }

    // getters y setters
    public String getContenidoPostText() {
        return this.contenidoPostText;
    }

    public void setContenidoPostText(final String contenidoPostText) {
        this.contenidoPostText = contenidoPostText;
    }


    @Override
    public String obtenerTipoPost() {
        return "Texto";
    }
}