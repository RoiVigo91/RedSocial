package com.campusdual.redsocial.model.menu.utils;

import com.campusdual.redsocial.model.Usuario;
import com.campusdual.redsocial.model.menu.MenuBienvenida;
import com.campusdual.redsocial.model.menu.OpcionesIniciarSesion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ayuda {

    // metodos
    /* devuelve true si el número de fallos superó 3, entonces tendrá que limpiar la pantalla, o si el fallo ocurrió
    en las opciones de un menú, volver a iniciar ese módulo del menú con un return -1 (código de repetición) */
    public static boolean demasiadosFallos(int fallos) {
        final int FALLOS_PERMITIDOS = 3;
        boolean check = false;
        if (fallos >= FALLOS_PERMITIDOS) {
            check = true;
            Ayuda.limpiarPantalla();
        }
        return check;
    }

    // imprime una serie de líneas en blanco para simular el borrado de la consola
    public static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // devuelve true si alguien ha escrito "c | C" durante una operacion, indicando que quiere cancelarla
    public static boolean cancelar(String texto) {
        if ("c".equalsIgnoreCase(texto)) {
            if ("s".equalsIgnoreCase(Validacion.siNo("Desea Cancelar la operación?"))) {
                Ayuda.limpiarPantalla();
                return true;
            }
        }
        return false;
    }

    /* mensaje que se muestra al finalizar una operación */
    public static void mostrarMensajeFinalizacion(String mensaje) {
        Ayuda.limpiarPantalla();
        System.out.println("✨ " + mensaje + " ✨\n");
    }

    // devuelve false si el nombre de usuario ya existe, si el usuario no existe devuelve true
    public static boolean usuarioNoExiste(String nombre) {
        return obtenerUsuario(nombre) == null;
    }

    // devuelve el Usuario de un nombre de usuario
    public static Usuario obtenerUsuario(String nombre) {
        for (Usuario usuario : MenuBienvenida.getListaUsuarios()) {
            if (nombre.equals(usuario.getNombreUsuario())) {
                return usuario;
            }
        }
        return null;
    }

    // devuelve true si el usuario autenticado sigue al usuario pasado por parametro
    public static boolean usuarioSigueA(Usuario comprobar, Usuario usuario) {
        return comprobar.getListaSeguidosUsuario().contains(usuario);
    }

    public static String formatearFecha(LocalDate fecha) {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dTF.format(fecha);
    }

}
