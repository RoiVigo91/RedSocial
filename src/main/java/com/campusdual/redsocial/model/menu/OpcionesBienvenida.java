package com.campusdual.redsocial.model.menu;

import com.campusdual.redsocial.model.Usuario;
import com.campusdual.redsocial.model.menu.utils.Ayuda;
import com.campusdual.redsocial.model.menu.utils.Validacion;
import com.campusdual.redsocial.model.post.Post;

import java.util.List;

public class OpcionesBienvenida {

    // metodos
    static void registrarse() {
        boolean repetirOperacion = true;
        // nombre válido si se ha logrado validar, código de repetición "-1" o repetirOperacion ""
        String[] usuarioYUltimoMensaje = {"", "Introduzca un nombre de usuario ->\t",};
        while (repetirOperacion) {
            System.out.println("🍃 REGISTRARSE (Cancelar: C)\n");
            usuarioYUltimoMensaje = Validacion.validarUsuario(usuarioYUltimoMensaje[1], "");
            // SI EL USUARIO NO HA COMETIDO DEMASIADOS FALLOS, NO TIENE QUE repetirOperacion EL REGISTRO
            if (!"-1".equals(usuarioYUltimoMensaje[0])) {
                repetirOperacion = false;
            }
        }
        /* SI EL USUARIO HA CANCELADO CON SI, SIMPLEMENTE VUELVE AL MENU DE OPCIONES DE BIENVENIDA,
         SINO ES QUE EL USUARIO ES CORRECTO Y LO REGISTRA */
        if (!"c".equals(usuarioYUltimoMensaje[0])) {
            MenuBienvenida.getListaUsuarios().add(new Usuario(usuarioYUltimoMensaje[0]));
            Ayuda.mostrarMensajeFinalizacion("El usuario " + usuarioYUltimoMensaje[0] + " ha sido registrado " +
                    "con éxito.");
        }
    }

    static void iniciarSesion() {
        boolean repetirOperacion = true;
        String[] usuarioYUltimoMensaje = {"", "Introduzca su usuario ->\t",};
        while (repetirOperacion) {
            System.out.println("🍃 INICIAR SESIÓN (Cancelar: C)\n");
            usuarioYUltimoMensaje = Validacion.validarUsuario(usuarioYUltimoMensaje[1],
                    "Introduzca su usuario ->\t");
            // SI EL USUARIO NO HA COMETIDO DEMASIADOS FALLOS, NO TIENE QUE repetirOperacion EL INICIO DE SESIÓN
            if (!"-1".equals(usuarioYUltimoMensaje[0])) {
                repetirOperacion = false;    // si el usuario NO cometio fallos -> NO se repite
            }
        }
        // SI EL USUARIO NO CANCELÓ, INICIA SESIÓN, SINO VUELVE AL MENÚ DE OPCIONES DE BIENVENIDA
        if (!"c".equals(usuarioYUltimoMensaje[0])) {
            OpcionesIniciarSesion.setUsuarioAutenticado(Ayuda.obtenerUsuario(usuarioYUltimoMensaje[0]));
            boolean repetirMenu = true;
            int opcion = 0;
            while (repetirMenu) {
                Ayuda.limpiarPantalla();
                System.out.println("✨ Bienvenido " + OpcionesIniciarSesion.getUsuarioAutenticado().getNombreUsuario()
                        + " ✨\n");
                // muro
                List<Post> muro = OpcionesIniciarSesion.getUsuarioAutenticado().mostrarMuro(10);
                //
                System.out.println("1. Información de la cuenta");
                System.out.println("2. Mis Posts");
                System.out.println("3. Buscar usuarios");
                System.out.println("\n0. Cerrar sesión");
                opcion = Validacion.validarMenu(muro.size() + 4);
                repetirMenu = escogerOpcionIniciarSesion(opcion, muro);
            }
        }
    }

    static boolean escogerOpcionIniciarSesion(int opcion, List<Post> muro) {
        Ayuda.limpiarPantalla();
        int postEscogido = 0;
        if(opcion > 3){
            postEscogido = opcion - 4;
            opcion = 4;
        }
        switch (opcion) {
            case 0:
                if ("s".equalsIgnoreCase(Validacion.siNo("Desea realmente cerrar sesión?"))) {
                    Ayuda.limpiarPantalla();
                    return false;
                }
                break;
            case 1:
                if (OpcionesIniciarSesion.informacionCuenta(OpcionesIniciarSesion.getUsuarioAutenticado())) {
                    while (OpcionesIniciarSesion.getUsuarioABuscar() != null) {
                        Usuario seguidoOSeguidorABuscar = OpcionesIniciarSesion.getUsuarioABuscar();
                        OpcionesIniciarSesion.setUsuarioABuscar(null);
                        if (!OpcionesIniciarSesion.informacionCuenta(seguidoOSeguidorABuscar)) {
                            return false;
                        } else {
                            if (OpcionesIniciarSesion.getUsuarioABuscar() == null) {
                                if (seguidoOSeguidorABuscar == OpcionesIniciarSesion.getUsuarioAutenticado()) {
                                    break;
                                } else {
                                    OpcionesIniciarSesion.setUsuarioABuscar(
                                            OpcionesIniciarSesion.getUsuarioAutenticado());
                                }
                            }
                        }
                    }
                    break;
                } else {
                    return false;
                }
            case 2:
                OpcionesIniciarSesion.informacionPosts(OpcionesIniciarSesion.getUsuarioAutenticado());
                break;
            case 3:
                OpcionesIniciarSesion.buscarUsuarios();
                break;
            case 4:
                OpcionesMisPosts.verPost(muro.get(postEscogido));
                break;
            default:
                // SI SE HAN COMETIDO DEMASIADOS FALLOS (-1) : ENTONCES REPITE EL MENÚ DE INICIO DE SESIÓN
                break;
        }
        return true;
    }

}
