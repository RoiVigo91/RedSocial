package com.campusdual.redsocial.model.menu.utils;

import static com.campusdual.redsocial.model.menu.utils.Ayuda.usuarioNoExiste;

public class Fallos {

    // metodos
    // si el nombre introducido NO es correcto devuelve el error a mostrar
    // si el nombre introducido es correcto devuelve "" -> no hay ningún error que mostrar
    public static String obtenerUltimoMensaje(String nombre, String mensajeCancelacion) {
        // SI NO HAY NINGUN MENSAJE POST CANCELACION SIGNIFICA QUE ESTAMOS EN EL REGISTRO
        if (mensajeCancelacion.isEmpty()) {
            if (!caracteresValidos(nombre)) {
                return "El nombre no debe contener espacios ni caracteres extraños excepto \".\" y \"_\" ->\t";
            }
            if (!longitudValidaNombre(nombre)) {
                return "El nombre debe tener al menos 6 caracteres ->\t";
            }
            if (!usuarioNoExiste(nombre)) {
                return "El usuario " + nombre + " ya existe. Seleccione otro nombre de usuario ->\t";
            }
        } else {
            // SI EL USUARIO CANCELA PERO LUEGO SE VUELVE ATRÁS Y PULSA n | N -> DEVUELVE MENSAJE CANCELACIÓN
            if ("c".equalsIgnoreCase(nombre)) {
                return mensajeCancelacion;
            }
            if (usuarioNoExiste(nombre)) {
                return "No se ha podido encontrar el usuario " + nombre + ". Seleccione otro nombre de usuario ->\t";
            }
        }
        return "";
    }

    //devuelve un string vacío si el texto no tiene ningún fallo, sino devuelve el fallo
    public static String obtenerUltimoMensajeString(String datoAValidar, String texto, boolean checkEntero,
                                                    int longitudMaxima) {
        String articulo = ("duración".equals(datoAValidar)) ? "La " : "El ";
        String segundos = ("duración".equals(datoAValidar)) ? " segundos" : "";
        if (texto.isEmpty()) {
            return articulo + datoAValidar + " no se puede dejar en blanco ->\t";
        }
        if (checkEntero) {
            try {
                if (Integer.parseInt(texto) > longitudMaxima) {
                    return articulo + datoAValidar + " máxima para los vídeos es de " +
                            longitudMaxima + segundos + " ->\t";
                }
            } catch (NumberFormatException e) {
                return articulo + datoAValidar + " debe ser un número entero ->\t";
            }
        } else {
            if (texto.length() > longitudMaxima) {
                return articulo + datoAValidar + " no puede ser superior a " + longitudMaxima +
                        " caracteres ->\t";
            }
        }
        return "";
    }

    // devuelve true si la longitud del nombre introducido es válida
    public static boolean longitudValidaNombre(String nombre) {
        return nombre.length() > 5;
    }

    // devuelve true si el nombre no contiene espacios o caracteres en blanco
    public static boolean caracteresValidos(String nombre) {
        return nombre.matches("[a-zA-Z0-9._]+");
    }

}
