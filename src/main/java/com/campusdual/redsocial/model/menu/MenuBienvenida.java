package com.campusdual.redsocial.model.menu;

import com.campusdual.redsocial.model.Usuario;
import com.campusdual.redsocial.model.menu.utils.Ayuda;
import com.campusdual.redsocial.model.menu.utils.Validacion;

import java.util.ArrayList;

public class MenuBienvenida {

    // propiedades
    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    // getter
    public static ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    // metodos
    public static void bienvenida() {
        boolean repetirMenu = true; // se puede repetirOperacion por fallos o por éxito
        while (repetirMenu) {
            System.out.println("🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃");
            System.out.println("🍃                                                  🍃");
            System.out.println("🍃 1. ¿Todavía no tiene una cuenta? -> Registrarse  🍃");
            System.out.println("🍃 2. Iniciar sesión                                🍃");
            System.out.println("🍃 3. Salir                                         🍃");
            System.out.println("🍃                                                  🍃");
            System.out.println("🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃🍃");
            repetirMenu = escogerOpcionBienvenida(Validacion.validarMenu(3));
        }
    }

    static boolean escogerOpcionBienvenida(int opcion) {
        Ayuda.limpiarPantalla();
        switch (opcion) {
            case 1:
                OpcionesBienvenida.registrarse();
                break;
            case 2:
                OpcionesBienvenida.iniciarSesion();
                break;
            case 3:
                Ayuda.limpiarPantalla();
                Validacion.getScan().close();
                System.out.println("\n✨ Hasta la próxima ✨");
                System.exit(0);
            default:    // código de repetición -1 : repetición del menú de bienvenida por fallos
                break;
        }
        return true;
    }

    public static void crearDatosIniciales() {

        // USUARIOS
        getListaUsuarios().add(new Usuario("Roi111"));
        getListaUsuarios().add(new Usuario("Alfonso111"));
        getListaUsuarios().add(new Usuario("Jorge111"));
        getListaUsuarios().add(new Usuario("Adrian111"));
        getListaUsuarios().add(new Usuario("Jorge222"));
        getListaUsuarios().add(new Usuario("Ana789"));
        getListaUsuarios().add(new Usuario("Pedro321"));
        getListaUsuarios().add(new Usuario("Laura555"));
        getListaUsuarios().add(new Usuario("Luis999"));
        getListaUsuarios().add(new Usuario("Sofia777"));
        getListaUsuarios().add(new Usuario("Diego333"));
        getListaUsuarios().add(new Usuario("Valeria888"));
        getListaUsuarios().add(new Usuario("Andres111"));
        getListaUsuarios().add(new Usuario("Carla666"));
        getListaUsuarios().add(new Usuario("Raul444"));
        getListaUsuarios().add(new Usuario("Elena222"));
        getListaUsuarios().add(new Usuario("Miguel101"));
        getListaUsuarios().add(new Usuario("Patricia202"));
        getListaUsuarios().add(new Usuario("David303"));
        getListaUsuarios().add(new Usuario("Lucia404"));
        getListaUsuarios().add(new Usuario("Manuel505"));
        getListaUsuarios().add(new Usuario("Isabel606"));

        // POSTS
        // roi -> deporte
        getListaUsuarios().get(0).crearPost(1, "Partido de fútbol", new String[]{
                "Golazo en el minuto 90"});
        getListaUsuarios().get(0).crearPost(2, "Maratón", new String[]{"1024", "768"});
        getListaUsuarios().get(0).crearPost(3, "Entrenamiento", new String[]{"FHD", "200"});
        getListaUsuarios().get(0).crearPost(1, "Gimnasio", new String[]{"Rutina de pesas intensiva"});

        // alfonso -> viajes
        getListaUsuarios().get(1).crearPost(1, "En París", new String[]{"Visitando la Torre Eiffel"});
        getListaUsuarios().get(1).crearPost(2, "Montañas Suizas", new String[]{"1920", "1080"});
        getListaUsuarios().get(1).crearPost(3, "Viaje en tren", new String[]{"4K", "360"});
        getListaUsuarios().get(1).crearPost(1, "Roma", new String[]{"Comiendo pizza en el Coliseo"});

        // jorge -> naturaleza
        getListaUsuarios().get(2).crearPost(1, "Bosque encantado", new String[]{
                "Descubriendo árboles centenarios"});
        getListaUsuarios().get(2).crearPost(2, "Lago cristalino", new String[]{"1280", "720"});
        getListaUsuarios().get(2).crearPost(3, "Cascada", new String[]{"HD", "150"});
        getListaUsuarios().get(2).crearPost(1, "Desierto dorado", new String[]{
                "Atrapado en una tormenta de arena"});

        // adrian -> (no hay publicaciones)

        // jorge222 -> naturaleza
        getListaUsuarios().get(4).crearPost(2, "Perdidos en las vegas", new String[]{"1024", "768"});
        getListaUsuarios().get(4).crearPost(2, "Skywolker", new String[]{"1024", "768"});

        // COMENTARIOS

        getListaUsuarios().get(0).getListaPostsUsuario().get(0).crearComentario(
                "Increíble partido, ¡emocionante hasta el final!", getListaUsuarios().get(1));
        getListaUsuarios().get(0).getListaPostsUsuario().get(0).crearComentario(
                "¡Qué golazo! ¿Ya practicaste ese tiro antes?", getListaUsuarios().get(2));
        getListaUsuarios().get(0).getListaPostsUsuario().get(0).crearComentario(
                "Me hizo recordar un gol que hice hace unos años 😅", getListaUsuarios().get(3));
        getListaUsuarios().get(0).getListaPostsUsuario().get(1).crearComentario(
                "¡Qué resistencia tienes! Me inspiras a entrenar más 💪", getListaUsuarios().get(2));
        getListaUsuarios().get(0).getListaPostsUsuario().get(1).crearComentario(
                "¡Impresionante! ¿Cuánto tiempo te tomó?", getListaUsuarios().get(3));
        getListaUsuarios().get(0).getListaPostsUsuario().get(1).crearComentario(
                "¡Yo también quiero correr una maratón algún día!", getListaUsuarios().get(0));
        getListaUsuarios().get(0).getListaPostsUsuario().get(2).crearComentario(
                "¡Esa rutina se ve intensa, mucho ánimo!", getListaUsuarios().get(1));
        getListaUsuarios().get(0).getListaPostsUsuario().get(2).crearComentario(
                "La rutina está genial, ¿tienes alguna recomendación para principiantes?",
                getListaUsuarios().get(3));


        getListaUsuarios().get(1).getListaPostsUsuario().get(0).crearComentario(
                "¡Qué vista tan espectacular de la Torre Eiffel! 🗼", getListaUsuarios().get(0));
        getListaUsuarios().get(1).getListaPostsUsuario().get(0).crearComentario(
                "Siempre he soñado con ir a París", getListaUsuarios().get(2));
        getListaUsuarios().get(1).getListaPostsUsuario().get(1).crearComentario(
                "Las montañas se ven hermosas, ¡me encantaría ir!", getListaUsuarios().get(2));
        getListaUsuarios().get(1).getListaPostsUsuario().get(1).crearComentario(
                "¡Qué paisaje tan increíble! ¿Cuál es el mejor mes para visitarlas?",
                getListaUsuarios().get(3));
        getListaUsuarios().get(1).getListaPostsUsuario().get(3).crearComentario(
                "¡Pizza y Coliseo, combinación perfecta! 🍕", getListaUsuarios().get(0));
        getListaUsuarios().get(1).getListaPostsUsuario().get(1).crearComentario(
                "¡Esas montañas parecen el lugar perfecto para desconectar!",
                getListaUsuarios().get(0));
        getListaUsuarios().get(1).getListaPostsUsuario().get(3).crearComentario(
                "Roma es un lugar mágico, ¿qué otros lugares visitaste?", getListaUsuarios().get(2));


        getListaUsuarios().get(2).getListaPostsUsuario().get(0).crearComentario(
                "Ese bosque parece sacado de un cuento de hadas 🌳✨", getListaUsuarios().get(1));
        getListaUsuarios().get(2).getListaPostsUsuario().get(0).crearComentario(
                "¡Qué maravilla de lugar! ¿Dónde exactamente está?", getListaUsuarios().get(3));
        getListaUsuarios().get(2).getListaPostsUsuario().get(2).crearComentario(
                "¡Esa cascada se ve impresionante! 💦", getListaUsuarios().get(3));
        getListaUsuarios().get(2).getListaPostsUsuario().get(2).crearComentario(
                "¡Qué belleza natural! Me gustaría ver eso en persona", getListaUsuarios().get(1));
        getListaUsuarios().get(2).getListaPostsUsuario().get(0).crearComentario(
                "Amo los bosques, ¿me podrías decir cómo llegar a este?", getListaUsuarios().get(0));
        getListaUsuarios().get(2).getListaPostsUsuario().get(0).crearComentario(
                "¿Este lugar tiene algún tipo de leyenda o historia asociada?",
                getListaUsuarios().get(1));
        getListaUsuarios().get(2).getListaPostsUsuario().get(2).crearComentario(
                "¿Sabes si este lugar está abierto al público todo el año?", getListaUsuarios().get(0));


        // SEGUIDOS
        // Roi111 -> 5 seguidos, 3 seguidores (Jorge111, Adrian111, Laura555)
        getListaUsuarios().get(0).seguirUsuario(getListaUsuarios().get(2));
        getListaUsuarios().get(0).seguirUsuario(getListaUsuarios().get(3));
        getListaUsuarios().get(0).seguirUsuario(getListaUsuarios().get(7));
        getListaUsuarios().get(0).seguirUsuario(getListaUsuarios().get(10));
        getListaUsuarios().get(0).seguirUsuario(getListaUsuarios().get(15));

        // Alfonso111 -> 3 seguidos, 2 seguidores (Adrian111, Diego333)
        getListaUsuarios().get(1).seguirUsuario(getListaUsuarios().get(3));
        getListaUsuarios().get(1).seguirUsuario(getListaUsuarios().get(6));
        getListaUsuarios().get(1).seguirUsuario(getListaUsuarios().get(18));

        // Jorge111 -> 7 seguidos, 4 seguidores (Roi111, Adrian111, Valeria888, Patricia202)
        getListaUsuarios().get(2).seguirUsuario(getListaUsuarios().get(0));
        getListaUsuarios().get(2).seguirUsuario(getListaUsuarios().get(3));
        getListaUsuarios().get(2).seguirUsuario(getListaUsuarios().get(4));
        getListaUsuarios().get(2).seguirUsuario(getListaUsuarios().get(5));
        getListaUsuarios().get(2).seguirUsuario(getListaUsuarios().get(12));
        getListaUsuarios().get(2).seguirUsuario(getListaUsuarios().get(14));
        getListaUsuarios().get(2).seguirUsuario(getListaUsuarios().get(21));

        // Adrian111 -> 8 seguidos, 3 seguidores (Alfonso111, Jorge111, Sofia777)
        getListaUsuarios().get(3).seguirUsuario(getListaUsuarios().get(1));
        getListaUsuarios().get(3).seguirUsuario(getListaUsuarios().get(2));
        getListaUsuarios().get(3).seguirUsuario(getListaUsuarios().get(7));
        getListaUsuarios().get(3).seguirUsuario(getListaUsuarios().get(10));
        getListaUsuarios().get(3).seguirUsuario(getListaUsuarios().get(13));
        getListaUsuarios().get(3).seguirUsuario(getListaUsuarios().get(16));
        getListaUsuarios().get(3).seguirUsuario(getListaUsuarios().get(18));
        getListaUsuarios().get(3).seguirUsuario(getListaUsuarios().get(20));

        // Jorge222 -> 2 seguidos, 1 seguidor (Lucia404)
        getListaUsuarios().get(4).seguirUsuario(getListaUsuarios().get(11));
        getListaUsuarios().get(4).seguirUsuario(getListaUsuarios().get(19));

        // Ana789 -> 4 seguidos, 0 seguidores
        getListaUsuarios().get(5).seguirUsuario(getListaUsuarios().get(3));
        getListaUsuarios().get(5).seguirUsuario(getListaUsuarios().get(8));
        getListaUsuarios().get(5).seguirUsuario(getListaUsuarios().get(14));
        getListaUsuarios().get(5).seguirUsuario(getListaUsuarios().get(21));

        // Pedro321 -> 6 seguidos, 2 seguidores (Alfonso111, Miguel101)
        getListaUsuarios().get(6).seguirUsuario(getListaUsuarios().get(4));
        getListaUsuarios().get(6).seguirUsuario(getListaUsuarios().get(7));
        getListaUsuarios().get(6).seguirUsuario(getListaUsuarios().get(11));
        getListaUsuarios().get(6).seguirUsuario(getListaUsuarios().get(13));
        getListaUsuarios().get(6).seguirUsuario(getListaUsuarios().get(17));
        getListaUsuarios().get(6).seguirUsuario(getListaUsuarios().get(18));

        // Laura555 -> 1 seguido, 3 seguidores (Roi111, Adrian111, Sofia777)
        getListaUsuarios().get(7).seguirUsuario(getListaUsuarios().get(5));

        // Luis999 -> 0 seguidos, 1 seguidor (Pedro321)

        // Sofia777 -> 3 seguidos, 2 seguidores (Adrian111, Laura555)
        getListaUsuarios().get(9).seguirUsuario(getListaUsuarios().get(6));
        getListaUsuarios().get(9).seguirUsuario(getListaUsuarios().get(14));
        getListaUsuarios().get(9).seguirUsuario(getListaUsuarios().get(20));

        // Diego333 -> 7 seguidos, 1 seguidor (Alfonso111)
        getListaUsuarios().get(10).seguirUsuario(getListaUsuarios().get(0));
        getListaUsuarios().get(10).seguirUsuario(getListaUsuarios().get(1));
        getListaUsuarios().get(10).seguirUsuario(getListaUsuarios().get(4));
        getListaUsuarios().get(10).seguirUsuario(getListaUsuarios().get(9));
        getListaUsuarios().get(10).seguirUsuario(getListaUsuarios().get(16));
        getListaUsuarios().get(10).seguirUsuario(getListaUsuarios().get(19));
        getListaUsuarios().get(10).seguirUsuario(getListaUsuarios().get(21));

        // Valeria888 -> 8 seguidos, 1 seguidor (Jorge222)
        getListaUsuarios().get(11).seguirUsuario(getListaUsuarios().get(1));
        getListaUsuarios().get(11).seguirUsuario(getListaUsuarios().get(2));
        getListaUsuarios().get(11).seguirUsuario(getListaUsuarios().get(5));
        getListaUsuarios().get(11).seguirUsuario(getListaUsuarios().get(9));
        getListaUsuarios().get(11).seguirUsuario(getListaUsuarios().get(10));
        getListaUsuarios().get(11).seguirUsuario(getListaUsuarios().get(15));
        getListaUsuarios().get(11).seguirUsuario(getListaUsuarios().get(17));
        getListaUsuarios().get(11).seguirUsuario(getListaUsuarios().get(21));

        // Andres111 -> 4 seguidos, 2 seguidores (Jorge111, David303)
        getListaUsuarios().get(12).seguirUsuario(getListaUsuarios().get(4));
        getListaUsuarios().get(12).seguirUsuario(getListaUsuarios().get(7));
        getListaUsuarios().get(12).seguirUsuario(getListaUsuarios().get(13));
        getListaUsuarios().get(12).seguirUsuario(getListaUsuarios().get(20));

        // Carla666 -> 3 seguidos, 3 seguidores (Laura555, Sofia777, Manuel505)
        getListaUsuarios().get(13).seguirUsuario(getListaUsuarios().get(0));
        getListaUsuarios().get(13).seguirUsuario(getListaUsuarios().get(3));
        getListaUsuarios().get(13).seguirUsuario(getListaUsuarios().get(12));

        // Raul444 -> 5 seguidos, 1 seguidor (Elena222)
        getListaUsuarios().get(14).seguirUsuario(getListaUsuarios().get(2));
        getListaUsuarios().get(14).seguirUsuario(getListaUsuarios().get(5));
        getListaUsuarios().get(14).seguirUsuario(getListaUsuarios().get(7));
        getListaUsuarios().get(14).seguirUsuario(getListaUsuarios().get(11));
        getListaUsuarios().get(14).seguirUsuario(getListaUsuarios().get(18));

        // Elena222 -> 6 seguidos, 2 seguidores (Roi111, Andres111)
        getListaUsuarios().get(15).seguirUsuario(getListaUsuarios().get(4));
        getListaUsuarios().get(15).seguirUsuario(getListaUsuarios().get(6));
        getListaUsuarios().get(15).seguirUsuario(getListaUsuarios().get(9));
        getListaUsuarios().get(15).seguirUsuario(getListaUsuarios().get(12));
        getListaUsuarios().get(15).seguirUsuario(getListaUsuarios().get(13));
        getListaUsuarios().get(15).seguirUsuario(getListaUsuarios().get(18));

        // Miguel101 -> 2 seguidos, 3 seguidores (Adrian111, Pedro321, Diego333)
        getListaUsuarios().get(16).seguirUsuario(getListaUsuarios().get(5));
        getListaUsuarios().get(16).seguirUsuario(getListaUsuarios().get(15));

        // Patricia202 -> 3 seguidos, 1 seguidor (Jorge111)
        getListaUsuarios().get(17).seguirUsuario(getListaUsuarios().get(3));
        getListaUsuarios().get(17).seguirUsuario(getListaUsuarios().get(7));
        getListaUsuarios().get(17).seguirUsuario(getListaUsuarios().get(11));

        // David303 -> 7 seguidos, 3 seguidores (Alfonso111, Diego333, Raul444)
        getListaUsuarios().get(18).seguirUsuario(getListaUsuarios().get(0));
        getListaUsuarios().get(18).seguirUsuario(getListaUsuarios().get(2));
        getListaUsuarios().get(18).seguirUsuario(getListaUsuarios().get(5));
        getListaUsuarios().get(18).seguirUsuario(getListaUsuarios().get(8));
        getListaUsuarios().get(18).seguirUsuario(getListaUsuarios().get(13));
        getListaUsuarios().get(18).seguirUsuario(getListaUsuarios().get(16));
        getListaUsuarios().get(18).seguirUsuario(getListaUsuarios().get(20));

        // Lucia404 -> 4 seguidos, 2 seguidores (Valeria888, Raul444)
        getListaUsuarios().get(19).seguirUsuario(getListaUsuarios().get(1));
        getListaUsuarios().get(19).seguirUsuario(getListaUsuarios().get(6));
        getListaUsuarios().get(19).seguirUsuario(getListaUsuarios().get(9));
        getListaUsuarios().get(19).seguirUsuario(getListaUsuarios().get(14));

        // Manuel505 -> 5 seguidos, 2 seguidores (Elena222, Patricia202)
        getListaUsuarios().get(20).seguirUsuario(getListaUsuarios().get(2));
        getListaUsuarios().get(20).seguirUsuario(getListaUsuarios().get(4));
        getListaUsuarios().get(20).seguirUsuario(getListaUsuarios().get(10));
        getListaUsuarios().get(20).seguirUsuario(getListaUsuarios().get(12));
        getListaUsuarios().get(20).seguirUsuario(getListaUsuarios().get(19));

        // Isabel606 -> 6 seguidos, 1 seguidor (Miguel101)
        getListaUsuarios().get(21).seguirUsuario(getListaUsuarios().get(3));
        getListaUsuarios().get(21).seguirUsuario(getListaUsuarios().get(5));
        getListaUsuarios().get(21).seguirUsuario(getListaUsuarios().get(7));
        getListaUsuarios().get(21).seguirUsuario(getListaUsuarios().get(8));
        getListaUsuarios().get(21).seguirUsuario(getListaUsuarios().get(11));
        getListaUsuarios().get(21).seguirUsuario(getListaUsuarios().get(20));


        // llamada a bienvenida
        Ayuda.limpiarPantalla();
        bienvenida();
    }

}