package com.campusdual.redsocial.model.menu.utils;

import java.util.Scanner;

public class Validacion {

    // propiedades
    public static Scanner scan = new Scanner(System.in);

    // getter
    public static Scanner getScan() {
        return Validacion.scan;
    }

    // metodos
    // función general de validación de la operación registro -> devuelve un nombre válido a registrar
    public static String[] validarUsuario(String ultimoMensaje, String mensajeCancelacion) {
        boolean check = false;
        int cuentaFallos = 0;
        String nombreUsuario = "";
        while (!check) {
            // SI SE HAN COMETIDO DEMASIADOS FALLOS DEVUELVE "-1" Y EL ULTIMO MENSAJE
            if (Ayuda.demasiadosFallos(cuentaFallos++)) {
                return new String[]{"-1", ultimoMensaje};
            }
            System.out.print(ultimoMensaje);
            nombreUsuario = getScan().nextLine().trim();
            // SI EL USUARIO HA PULSADO CANCELAR Y HA DICHO QUE SI, DEVUELVE "c"
            if (Ayuda.cancelar(nombreUsuario)) {
                return new String[]{"c", ""};
            }
            // DEVUELVE EL ÚLTIMO MENSAJE A MOSTRAR EN FUNCIÓN DEL VALOR DE "NOMBRE"
            ultimoMensaje = Fallos.obtenerUltimoMensaje(nombreUsuario, mensajeCancelacion);
            // SI ÚLTIMO FALLO ESTÁ VACÍO SIGNIFICA QUE EL NOMBRE INTRODUCIDO ES CORRECTO, SI NO, REPITE
            check = ultimoMensaje.isEmpty();
        }
        return new String[]{nombreUsuario, ""};
    }

    /* devuelve la opción escogida del número de opciones indicado (1 a n) y regula que el usuario no introduzca
    opciónes inválidas */
    public static int validarMenu(int numeroOpciones) {
        String textoOpcionValida = "\nIntroduzca la opción deseada ->\t\t";
        boolean check = false;
        int opcion = 0;
        int cuentaFallos = 0;
        while (!check) {
            // SI SE HAN COMETIDO DEMASIADOS FALLOS DEVUELVE -1
            if (Ayuda.demasiadosFallos(cuentaFallos++)) {
                return -1;
            }
            System.out.print(textoOpcionValida);
            textoOpcionValida = "Introduzca una opción válida ->\t\t";
            try {
                opcion = Integer.parseInt(getScan().nextLine().trim());
                //SI SE HA INTRODUCIDO UN ENTERO DE ENTRE EL NÚMERO DE OPCIONES VÁLIDO LO DEVUELVE
                check = opcion >= 0 && opcion < numeroOpciones;
            } catch (NumberFormatException ignored) {
            }
        }
        return opcion;
    }

    // devuelve si o no, dependiendo de lo que haya escogido el usuario -> se le pasa el mensaje a mostrar
    public static String siNo(String mensajeAMostrar) {
        String sino = "";
        boolean check = false;
        int cuentaFallos = 0;
        while (!check) {
            // SI SE HAN COMETIDO DEMASIADOS FALLOS DEVUELVE -1
            if (Ayuda.demasiadosFallos(cuentaFallos++)) {
                cuentaFallos = 1;
            }
            System.out.print(mensajeAMostrar + " (S | N) ->\t");
            sino = getScan().nextLine().trim();
            // SI EL MENSAJE INTRODUCIDO ES SI O NO, DEVUELVE LO QUE EL USUARIO HA ESCOGIDO
            check = sino.equalsIgnoreCase("s") || sino.equalsIgnoreCase("n");
        }
        return sino;
    }

    // función general de validación de cualquier String -> devuelve un String válido para cualquier propiedad
    public static String validarString(String datoAValidar, String mensajeAMostrar, boolean validarEntero, int longitudMaxima) {
        String ultimoMensaje = mensajeAMostrar;
        boolean check = false;
        String texto = "";
        int cuentaFallos = -1;
        Ayuda.limpiarPantalla();
        System.out.println("🍃 " + datoAValidar.toUpperCase() + " (Cancelar: C)\n");
        while (!check) {
            if (Ayuda.demasiadosFallos(++cuentaFallos)) {
                System.out.println("🍃 " + datoAValidar.toUpperCase() + " (Cancelar: C)\n");
                cuentaFallos = 0;
            }
            System.out.print(ultimoMensaje);
            texto = Validacion.getScan().nextLine().trim();
            //SI EL USUARIO CANCELA Y LE DA QUE SI, AUTOMATICAMENTE SALE DEVOLVIENDO "c"
            if (Ayuda.cancelar(texto)) {
                return "c";
            }
            ultimoMensaje = Fallos.obtenerUltimoMensajeString(datoAValidar, texto, validarEntero, longitudMaxima);
            if (ultimoMensaje.isEmpty()) {
                //SI EL USUARIO CANCELÓ PERO LE PULSÓ A NO, TITULO SIGUE VALIIENDO "c", PERO ESE NO ES EL TÍTULO
                if (!"c".equalsIgnoreCase(texto)) {
                    //SI NO ES "c", EL TITULO ES VÁLIDO, SALE DEL BUCLE Y SE DEVUELVE
                    check = true;
                } else {
                    //SI SE ENTRA AQUÍ ES PORQUE EL USUARIO CANCELÓ Y PULSÓ NO (SE ARREPINTIÓ), MUESTRA MENSAJE INICIAL
                    ultimoMensaje = mensajeAMostrar;
                }
            }
        }
        return texto;
    }

}
