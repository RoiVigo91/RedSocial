package com.campusdual.redsocial.model.menu;

import com.campusdual.redsocial.model.Usuario;
import com.campusdual.redsocial.model.menu.utils.Ayuda;
import com.campusdual.redsocial.model.menu.utils.Validacion;
import com.campusdual.redsocial.model.post.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class OpcionesIniciarSesion {

    // propiedades
    public static Scanner scan = new Scanner(System.in);
    private static Usuario usuarioAutenticado;
    private static Usuario usuarioABuscar = null;

    // getter y setter
    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public static void setUsuarioAutenticado(final Usuario usuarioAutenticado) {
        OpcionesIniciarSesion.usuarioAutenticado = usuarioAutenticado;
    }

    static Usuario getUsuarioABuscar() {
        return usuarioABuscar;
    }

    static void setUsuarioABuscar(final Usuario usuarioABuscar) {
        OpcionesIniciarSesion.usuarioABuscar = usuarioABuscar;
    }

    // metodos
    // informacionCuenta PUEDE MOSTRAR DATOS DE -> USUARIO AUTENTICADO O DE OTRO USUARIO (desde buscarUsuario)
    static boolean informacionCuenta(Usuario usuario) {
        int repetirMenu = 1;
        int otroUsuario = getUsuarioAutenticado() == usuario ? 0 : 1;
        while (repetirMenu == 1) {
            int numeroOpciones = 5;
            boolean siguiendo = Ayuda.usuarioSigueA(OpcionesIniciarSesion.getUsuarioAutenticado(), usuario);
            String mostrarSiguiendo = (otroUsuario == 1 && siguiendo) ? " (Siguiendo)" : "";
            System.out.println(otroUsuario == 0 ? "🍃 INFORMACIÓN DE LA CUENTA 🍃\n"
                    : "🍃 PERFIL DE " + usuario.getNombreUsuario() + " 🍃\n");
            System.out.println(usuario.mostrarInformacionCuenta(mostrarSiguiendo));
            if (otroUsuario == 0) {
                System.out.println("1. Eliminar cuenta");
                System.out.println("2. Mis seguidos");
                System.out.println("3. Mis seguidores");
                System.out.println("4. Todos mis comentarios");
                System.out.println("\n0. Volver atrás");
            } else {
                if (siguiendo) {
                    System.out.println("1. Dejar de seguir");
                    System.out.println("2. Ver seguidos");
                    System.out.println("3. Ver seguidores");
                    System.out.println("4. Ver posts de " + usuario.getNombreUsuario());
                    System.out.println("\n0. Volver atrás");
                } else {
                    numeroOpciones = 2;
                    System.out.println("1. Seguir");
                    System.out.println("\n0. Volver atrás");
                }
            }
            repetirMenu = escogerOpcionInformacionCuenta(Validacion.validarMenu(numeroOpciones), otroUsuario, usuario,
                    siguiendo);
        }
        return repetirMenu != 2; // repetirMenu == 2 -> false -> únicamente para eliminar la cuenta
    }

    static int escogerOpcionInformacionCuenta(int opcion, int otroUsuario, Usuario usuario, boolean siguiendo) {
        switch (opcion) {
            case 0:
                setUsuarioABuscar(null);
                Ayuda.limpiarPantalla();
                return 0;
            case 1:
                if (otroUsuario == 0) {
                    return SubopcionesInformacionCuenta.eliminarCuenta(getUsuarioAutenticado());
                } else {
                    if (siguiendo) {
                        getUsuarioAutenticado().dejarDeSeguirUsuario(usuario, false);
                    } else {
                        getUsuarioAutenticado().seguirUsuario(usuario);
                    }
                    Ayuda.limpiarPantalla();
                    break;
                }
            case 2:
                if (otroUsuario == 0) {
                    SubopcionesInformacionCuenta.misSeguidosYSeguidores(getUsuarioAutenticado(), "seguidos");
                } else {
                    SubopcionesInformacionCuenta.misSeguidosYSeguidores(usuario, "seguidos");
                }
                Ayuda.limpiarPantalla();
                break;
            case 3:
                if (otroUsuario == 0) {
                    SubopcionesInformacionCuenta.misSeguidosYSeguidores(getUsuarioAutenticado(), "seguidores");
                } else {
                    SubopcionesInformacionCuenta.misSeguidosYSeguidores(usuario, "seguidores");
                }
                Ayuda.limpiarPantalla();
                break;
            case 4:
                if (otroUsuario == 0) {
                    SubopcionesInformacionCuenta.todosMisComentarios(getUsuarioAutenticado());
                } else {
                    Ayuda.limpiarPantalla();
                    informacionPosts(usuario);
                }
                Ayuda.limpiarPantalla();
                break;
            default:
                break;
        }
        if (getUsuarioABuscar() != null) {
            return 0;
        }
        return 1;
    }

    // informacionPosts PUEDE MOSTRAR DATOS DE -> USUARIO AUTENTICADO O DE OTRO USUARIO (desde buscarUsuario)
    static void informacionPosts(Usuario usuario) {
        boolean repetirMenu = true;
        // DEPENDIENDO DE SI HEMOS ENTRADO COMO USUARIO AUTENTICADO O NO, TENDREMOS UNAS OPCIONES U OTRAS
        int otroUsuario = getUsuarioAutenticado() == usuario ? 0 : 1;
        String cabeceraPosts = otroUsuario == 0 ? "MIS POSTS" : "POSTS DE " + usuario.getNombreUsuario();
        Ayuda.limpiarPantalla();
        while (repetirMenu) {
            int contador = 0;
            int ajusteOpcionPost = otroUsuario == 0 ? 1 : 0;
            ArrayList<Post> listaPostsUsuario = new ArrayList<>();
            System.out.println("🍃 " + cabeceraPosts + " 🍃\n");
            if (!usuario.getListaPostsUsuario().isEmpty()) {
                listaPostsUsuario = usuario.getListaPostsUsuario();
                while (contador < listaPostsUsuario.size()) {
                    Post postOpcion = listaPostsUsuario.get(listaPostsUsuario.size() - 1 - contador);
                    System.out.println("- " + (++contador + ajusteOpcionPost) + ". " + postOpcion.getTituloPost() + " -> " +
                            Ayuda.formatearFecha(postOpcion.getFechaPost()) + " (" + postOpcion.obtenerTipoPost() + ")");
                }
            } else {
                if (otroUsuario == 0) {
                    System.out.println("Todavía no tiene ninguna publicación.");
                } else {
                    System.out.println("El usuario " + usuario.getNombreUsuario() + " no tiene ninguna publicación.");
                }
            }
            if (otroUsuario == 0) {
                contador++;
                System.out.println("1. [Crear nuevo post]");
            }
            System.out.println("\n0. Volver atrás");
            repetirMenu = escogerOpcionMisPosts(Validacion.validarMenu(++contador), listaPostsUsuario,
                    otroUsuario, ajusteOpcionPost);
        }

    }

    static boolean escogerOpcionMisPosts(int opcion, ArrayList<Post> listaPostsUsuario, int otroUsuario,
                                         int ajusteOpcionPost) {
        int postEscogido = 0;
        ajusteOpcionPost ^= 1;      //operación de bits -> alterna entre 0 y 1
        if (opcion > 1 - ajusteOpcionPost) {
            postEscogido = listaPostsUsuario.size() - 1 - (opcion - 2 + ajusteOpcionPost);
            System.out.println("POST ESCOGIDO VALE" + postEscogido);
            opcion = 2;
        }
        switch (opcion) {
            case 0:
                Ayuda.limpiarPantalla();
                return false;
            case 1:
                if (otroUsuario == 0) {
                    OpcionesMisPosts.crearNuevoPost();
                } else {
                    Ayuda.limpiarPantalla();
                    return false;
                }
                break;
            case 2:
                OpcionesMisPosts.verPost(listaPostsUsuario.get(postEscogido));
                break;
            default:
                break;
        }
        return true;
    }

    static void buscarUsuarios() {
        boolean repetirOperacion = true;
        String[] usuarioYUltimoMensaje = {"", "Introduzca el nombre de usuario que desea buscar ->\t\t",};
        while (repetirOperacion) {
            System.out.println("🍃 BUSCAR USUARIO (Cancelar: C)\n");
            getUsuarioAutenticado().mostrarSugerenciasAmistad();
            usuarioYUltimoMensaje = Validacion.validarUsuario(usuarioYUltimoMensaje[1],
                    "Introduzca el nombre de usuario que desea buscar ->\t\t");
            if ("c".equals(usuarioYUltimoMensaje[0])) {
                repetirOperacion = false;
            }
            if (!Ayuda.usuarioNoExiste(usuarioYUltimoMensaje[0])) {
                repetirOperacion = false;
                Usuario usuario = Ayuda.obtenerUsuario(usuarioYUltimoMensaje[0]);
                Ayuda.limpiarPantalla();
                setUsuarioABuscar(usuario);
                do {
                    Usuario usuarioABuscar = getUsuarioABuscar();
                    setUsuarioABuscar(null);
                    informacionCuenta(usuarioABuscar);
                } while (getUsuarioABuscar() != null);
            }
        }
    }
}