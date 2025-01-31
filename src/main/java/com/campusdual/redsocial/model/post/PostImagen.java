package com.campusdual.redsocial.model.post;

import com.campusdual.redsocial.model.Usuario;

public class PostImagen extends Post {

    // propiedadas
    private int anchoPostImagen;
    private int altoPostImagen;

    // constructores
    public PostImagen(final String titulo, final Usuario usuarioPropietarioPost, final int anchoPostImagen, final int altoPostImagen) {
        super(titulo, usuarioPropietarioPost);
        this.anchoPostImagen = anchoPostImagen;
        this.altoPostImagen = altoPostImagen;
    }

    // getters y setters
    public int getAnchoPostImagen() {
        return this.anchoPostImagen;
    }

    public void setAnchoPostImagen(final int anchoPostImagen) {
        this.anchoPostImagen = anchoPostImagen;
    }

    public int getAltoPostImagen() {
        return this.altoPostImagen;
    }

    public void setAltoPostImagen(final int altoPostImagen) {
        this.altoPostImagen = altoPostImagen;
    }

    @Override
    public String obtenerTipoPost() {
        return "Imagen";
    }
}
