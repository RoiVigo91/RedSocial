package com.campusdual.redsocial.model.post;

import com.campusdual.redsocial.model.Usuario;

public class PostVideo extends Post {

    // propiedadas
    // se pone la resolucion formada por 2 campos numericos tipo int por si interesa manejarlas sin tener que
    // andar cortando cadenas de texto.
    // duracion en segundos
    private String calidadPostVideo;
    private int duracionPostVideo;

    // constructores
    public PostVideo(final String titulo, final Usuario usuarioPropietarioPost, final String calidadPostVideo, final int duracionPostVideo) {
        super(titulo, usuarioPropietarioPost);
        this.calidadPostVideo = calidadPostVideo;
        this.duracionPostVideo = duracionPostVideo;
    }

    // getters y setters
    public String getCalidadPostVideo() {
        return this.calidadPostVideo;
    }

    // duracion en segundos
    public int getDuracionPostVideo() {
        return this.duracionPostVideo;
    }

    public void setDuracionPostVideo(final int duracionPostVideo) {
        this.duracionPostVideo = duracionPostVideo;
    }

    @Override
    public String obtenerTipoPost() {
        return "Video";
    }
}