package com.campusdual.redsocial.model.menu;

import com.campusdual.redsocial.model.Comentario;
import com.campusdual.redsocial.model.menu.utils.Ayuda;
import com.campusdual.redsocial.model.menu.utils.Validacion;
import com.campusdual.redsocial.model.post.Post;

import java.util.ArrayList;

public class OpcionesMisPosts {

    // metodos
    static void verPost(Post postEscogido) {
        boolean repetirMenu = true;
        int otroUsuario = OpcionesIniciarSesion.getUsuarioAutenticado() == postEscogido.getUsuarioPropietarioPost() ?
                0 : 1;
        Ayuda.limpiarPantalla();
        String nombrePropietarioPost = "";
        int ajusteOpcionComentario = 1;
        while (repetirMenu) {
            if(otroUsuario == 1){
                nombrePropietarioPost = "(" + postEscogido.getUsuarioPropietarioPost().getNombreUsuario() + ") ";
                ajusteOpcionComentario = 0;
            }
            System.out.println("🍃 " + nombrePropietarioPost +
                    postEscogido.getTituloPost().toUpperCase() + " -> " +
                    Ayuda.formatearFecha(postEscogido.getFechaPost()) + " (" + postEscogido.obtenerTipoPost() + ")");
            System.out.println("\nCOMENTARIOS: (" + postEscogido.getNumeroDeComentarios() + ")");
            ArrayList<Comentario> listaComentariosPropios = postEscogido.mostrarComentarios(ajusteOpcionComentario);
            int numeroOpciones = listaComentariosPropios.size() + 2;
            System.out.println("1. [Añadir comentario]");
            if (otroUsuario == 0) {
                numeroOpciones++;
                System.out.println("\n2. Eliminar post");
                System.out.println("0. Volver atrás");
            }else{
                System.out.println("\n0. Volver atrás");
            }
            repetirMenu = escogerOpcionPost(Validacion.validarMenu(numeroOpciones), postEscogido,
                    listaComentariosPropios, ajusteOpcionComentario);
        }
    }

    static boolean escogerOpcionPost(int opcion, Post postEscogido,
                                     ArrayList<Comentario> listaComentariosPropios, int ajusteOpcionComentario) {
        int comentarioEscogido = 0;
        ajusteOpcionComentario ^= 1;    //operación de bits -> alterna entre 0 y 1
        if (opcion > 2 - ajusteOpcionComentario) {
            comentarioEscogido = opcion - 3 + ajusteOpcionComentario;
            opcion = 3;
        }
        switch (opcion) {
            case 0:
                Ayuda.limpiarPantalla();
                return false;
            case 1:
                SubopcionesCadaPost.crearComentario(postEscogido);
                break;
            case 2:
                return SubopcionesCadaPost.eliminarPost(postEscogido);
            case 3:
                SubopcionesCadaPost.eliminarComentario(postEscogido,
                        listaComentariosPropios.get(comentarioEscogido));
                break;

            default:
                break;
        }
        return true;
    }

    static void crearNuevoPost() {
        boolean repetirOperacion = true;
        while (repetirOperacion) {
            Ayuda.limpiarPantalla();
            System.out.println("¿Qué tipo de publicación desea crear?");
            System.out.println("1. Texto");
            System.out.println("2. Imagen");
            System.out.println("3. Vídeo");
            System.out.println("\n0. Cancelar operación");
            repetirOperacion = escogerOpcionCrearNuevoPost(Validacion.validarMenu(4));
        }
    }

    static boolean escogerOpcionCrearNuevoPost(int opcion) {
        switch (opcion) {
            case 0:
                Ayuda.limpiarPantalla();
                return false;
            case 1:
                opcionCrearPostTexto(Validacion.validarString("título",
                        "Introduzca un título para su texto ->\t",
                        false, 50));
                break;
            case 2:
                opcionCrearPostImagen(Validacion.validarString("título",
                        "Introduzca un título para su imagen ->\t",
                        false, 50));
                break;
            case 3:
                opcionCrearPostVideo(Validacion.validarString("título",
                        "Introduzca un título para su vídeo ->\t",
                        false, 50));
                break;
            default:
                return true;
        }
        return false;
    }

    static void opcionCrearPostTexto(String titulo) {
        if (!"c".equals(titulo)) {
            String[] datos = new String[1];
            datos[0] = Validacion.validarString("contenido",
                    "Introduzca un contenido para el texto ->\t",
                    false, 1500);
            if ("c".equals(datos[0])) {
                Ayuda.limpiarPantalla();
                return;
            }
            OpcionesIniciarSesion.getUsuarioAutenticado().crearPost(1, titulo, datos);
        }
        Ayuda.limpiarPantalla();
    }

    static void opcionCrearPostImagen(String titulo) {
        if (!"c".equals(titulo)) {
            String[] datos = new String[2];
            datos[0] = Validacion.validarString("ancho",
                    "Introduzca el ancho de su imagen ->\t",
                    true, 15000);
            if ("c".equals(datos[0])) {
                Ayuda.limpiarPantalla();
                return;
            }
            datos[1] = Validacion.validarString("alto",
                    "Introduzca el alto de su imagen ->\t",
                    true, 15000);
            if ("c".equals(datos[1])) {
                Ayuda.limpiarPantalla();
                return;
            }
            OpcionesIniciarSesion.getUsuarioAutenticado().crearPost(2, titulo, datos);
        }
        Ayuda.limpiarPantalla();
    }

    static void opcionCrearPostVideo(String titulo) {
        if (!"c".equals(titulo)) {
            String[] datos = new String[2];
            String resolucion = "";
            boolean repetirOperacion = true;
            while (repetirOperacion) {
                repetirOperacion = false;
                Ayuda.limpiarPantalla();
                System.out.println("¿Con qué resolución desea subir el vídeo?");
                System.out.println("1. 4K");
                System.out.println("2. FHD");
                System.out.println("3. HD");
                System.out.println("\n0. Cancelar operación");
                switch (Validacion.validarMenu(4)) {
                    case 0:
                        Ayuda.limpiarPantalla();
                        return;
                    case 1:
                        resolucion = "4K";
                        break;
                    case 2:
                        resolucion = "FHD";
                        break;
                    case 3:
                        resolucion = "HD";
                        break;
                    default:
                        repetirOperacion = true;
                }
            }
            datos[0] = resolucion;
            datos[1] = Validacion.validarString("duración",
                    "Introduzca la duración del vídeo (Segundos) ->\t",
                    true, 120);
            if ("c".equals(datos[1])) {
                Ayuda.limpiarPantalla();
                return;
            }
            OpcionesIniciarSesion.getUsuarioAutenticado().crearPost(3, titulo, datos);
        }
        Ayuda.limpiarPantalla();
    }

}
