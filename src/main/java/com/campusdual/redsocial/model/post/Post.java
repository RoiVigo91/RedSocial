package com.campusdual.redsocial.model.post;

import com.campusdual.redsocial.model.Comentario;
import com.campusdual.redsocial.model.Usuario;
import com.campusdual.redsocial.model.menu.OpcionesIniciarSesion;
import com.campusdual.redsocial.model.menu.utils.Ayuda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Post implements Comparable<Post> {


    //propiedades
    private String tituloPost;
    private LocalDate fechaPost;
    private Usuario usuarioPropietarioPost;
    private List<Comentario> listaComentariosPost = new ArrayList<>();

    //constructores
    public Post(final String titulo, final Usuario usuarioPropietarioPost) {
        this.tituloPost = titulo;
        this.usuarioPropietarioPost = usuarioPropietarioPost;
        this.fechaPost = LocalDate.now();
    }

    //getters y setters
    public abstract String obtenerTipoPost();

    public String getTituloPost() {
        return tituloPost;
    }

    public void setTituloPost(final String tituloPost) {
        this.tituloPost = tituloPost;
    }

    public Usuario getUsuarioPropietarioPost() {
        return this.usuarioPropietarioPost;
    }

    public void setUsuarioPropietarioPost(final Usuario usuarioPropietarioPost) {
        this.usuarioPropietarioPost = usuarioPropietarioPost;
    }

    public LocalDate getFechaPost() {
        return fechaPost;
    }

    public List<Comentario> getListaComentariosPost() {
        return this.listaComentariosPost;
    }

    // métodos
    public void crearComentario(String textoComentario, Usuario usuarioPropietarioComentario) {
        Comentario nuevoComentario = new Comentario(textoComentario, usuarioPropietarioComentario);
        publicarComentario(nuevoComentario);
    }

    public void publicarComentario(Comentario comentario) {
        this.getListaComentariosPost().add(comentario);
    }

    public void eliminarComentario(Comentario comentario) {
        this.getListaComentariosPost().remove(comentario);
    }

    public int getNumeroDeComentarios() {
        return this.getListaComentariosPost().size();
    }

    public ArrayList<Comentario> mostrarComentarios(int ajusteEliminarPost) {
        if (getListaComentariosPost().isEmpty()) {
            System.out.println("Esta publicación todavía no tiene comentarios.");
            return new ArrayList<Comentario>() {
            };
        } else {
            ArrayList<Comentario> listaComentariosPropios = new ArrayList<>();
            int contador = 1 + ajusteEliminarPost;
            for (int i = getListaComentariosPost().size() - 1; i >= 0; i--) {
                Comentario comentario = getListaComentariosPost().get(i);
                Usuario usuarioComentario = comentario.getUsuarioPropietarioComentario();
                String fechaComentario = "[" + Ayuda.formatearFecha(comentario.getFechaComentario()) + "]  ";
                String mostrarBorrar = "";
                if (OpcionesIniciarSesion.getUsuarioAutenticado() == usuarioComentario) {
                    listaComentariosPropios.add(comentario);
                    mostrarBorrar = "❌ " + ++contador + ". " + "(Tú) ";
                }
                String texto = comentario.getTextoComentario();
                System.out.println("- " + mostrarBorrar + usuarioComentario.getNombreUsuario() + ": " + fechaComentario
                        + texto);
            }
            return listaComentariosPropios;
        }
    }

    @Override
    public int compareTo(Post otroPost) {
        // Ordenar por fecha (más reciente primero)
        int fechaComparacion = otroPost.getFechaPost().compareTo(this.getFechaPost());
        // Si las fechas son iguales, compara por título (ascendente)
        if (fechaComparacion == 0) {
            return this.getTituloPost().compareTo(otroPost.getTituloPost());
        }
        return fechaComparacion;
    }
}

