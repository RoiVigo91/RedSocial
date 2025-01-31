package com.campusdual.redsocial.model.menu;

import com.campusdual.redsocial.model.Usuario;
import com.campusdual.redsocial.model.menu.utils.Ayuda;
import com.campusdual.redsocial.model.menu.utils.Validacion;
import com.campusdual.redsocial.model.post.Post;

import java.util.ArrayList;

public class SubopcionesInformacionCuenta {

    static int eliminarCuenta(Usuario usuarioAutenticado) {
        Ayuda.limpiarPantalla();
        if ("s".equals(Validacion.siNo("¿Está completamente seguro de que quiere eliminar su cuenta?"))) {
            //se elimina al que elimina la cuenta de la lista de seguidores de usuarios seguidos no seguidores
            for (Usuario usuarioSeguido : usuarioAutenticado.getListaSeguidosUsuario()) {
                usuarioSeguido.dejarDeSeguirUsuario(usuarioAutenticado, true);
            }
            //aqui recorro la lista de usuarios que siguen al que elimina la cuenta (seguidores)
            for (Usuario usuarioSeguidor : usuarioAutenticado.getListaSeguidoresUsuario()) {
                usuarioSeguidor.dejarDeSeguirUsuario(usuarioAutenticado, true);
            }
            MenuBienvenida.getListaUsuarios().remove(usuarioAutenticado);
            Ayuda.mostrarMensajeFinalizacion("Su cuenta ha sido eliminada.");
            return 2;
        } else {
            return 1;
        }
    }

    static void misSeguidosYSeguidores(Usuario usuario, String seguidosOSeguidores) {
        boolean repetirMenu = true;
        Ayuda.limpiarPantalla();
        while (repetirMenu) {
            int numeroOpciones;
            int otroUsuario = OpcionesIniciarSesion.getUsuarioAutenticado() == usuario ? 0 : 1;
            System.out.println("seguidos".equals(seguidosOSeguidores) ?
                    otroUsuario == 0 ? "🍃 SIGUIENDO 🍃\n" : "🍃 " + usuario.getNombreUsuario() + " SIGUE A 🍃\n" :
                    otroUsuario == 0 ? "🍃 MIS SEGUIDORES 🍃\n" : "🍃 SEGUIDORES de " + usuario.getNombreUsuario() +
                            " 🍃\n");
            numeroOpciones = "seguidos".equals(seguidosOSeguidores) ? usuario.mostrarMisSeguidos() :
                    usuario.mostrarMisSeguidores();
            System.out.println("\n0. Volver atrás");
            int opcion = Validacion.validarMenu(numeroOpciones);
            if (opcion == 0) {
                repetirMenu = false;
            } else if (opcion != -1) {
                Ayuda.limpiarPantalla();
                OpcionesIniciarSesion.setUsuarioABuscar("seguidos".equals(seguidosOSeguidores) ?
                        usuario.getListaSeguidosUsuario().get(opcion - 1) :
                        usuario.getListaSeguidoresUsuario().get(opcion - 1));
                repetirMenu = false;
            }
        }
    }

    static void todosMisComentarios(Usuario usuarioAutenticado) {
        Ayuda.limpiarPantalla();
        boolean repetirMenu = true;
        while (repetirMenu) {
            System.out.println("💫 TODOS MIS COMENTARIOS 💫\n");
            ArrayList<Post> listaPosts = usuarioAutenticado.mostrarListaComentariosUsuario();
            System.out.println("0. Volver atrás");
            int opcion = Validacion.validarMenu(listaPosts.size() + 1);
            if (opcion == 0) {
                repetirMenu = false;
            }else{
                OpcionesMisPosts.verPost(listaPosts.get(opcion - 1));
            }
        }
    }

}
