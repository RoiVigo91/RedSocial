package com.campusdual.redsocial.model.menu;

import com.campusdual.redsocial.model.Comentario;
import com.campusdual.redsocial.model.menu.utils.Ayuda;
import com.campusdual.redsocial.model.menu.utils.Validacion;
import com.campusdual.redsocial.model.post.Post;

public class SubopcionesCadaPost {

    // metodos
    static void crearComentario(Post postEscogido) {
        String repetirOperacion = "";
        while (repetirOperacion.isEmpty()) {
            repetirOperacion = Validacion.validarString("comentario",
                    "Introduzca su comentario ->\t\t", false, 1000);
        }
        if (!repetirOperacion.equalsIgnoreCase("c")) {
            postEscogido.getListaComentariosPost().add(new Comentario(repetirOperacion,
                    OpcionesIniciarSesion.getUsuarioAutenticado()));
            Ayuda.limpiarPantalla();
        }
    }

    static void eliminarComentario(Post postEscogido, Comentario comentarioEscogido) {
        Ayuda.limpiarPantalla();
        if ("s".equals(Validacion.siNo("Está seguro de que quiere eliminar el comentario?"))) {
            postEscogido.getListaComentariosPost().remove(comentarioEscogido);
        }
        Ayuda.limpiarPantalla();
    }

    static boolean eliminarPost(Post postEscogido) {
        Ayuda.limpiarPantalla();
        if ("s".equals(Validacion.siNo("Está seguro de que quiere eliminar el post?"))) {
            OpcionesIniciarSesion.getUsuarioAutenticado().getListaPostsUsuario().remove(postEscogido);
            Ayuda.mostrarMensajeFinalizacion("El post ha sido eliminado.");
            return false;
        } else {
            Ayuda.limpiarPantalla();
            return true;
        }
    }

}
