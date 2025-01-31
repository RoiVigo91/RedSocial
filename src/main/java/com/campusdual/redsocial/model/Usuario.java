package com.campusdual.redsocial.model;

import com.campusdual.redsocial.model.menu.MenuBienvenida;
import com.campusdual.redsocial.model.menu.OpcionesIniciarSesion;
import com.campusdual.redsocial.model.menu.utils.Ayuda;
import com.campusdual.redsocial.model.post.Post;
import com.campusdual.redsocial.model.post.PostImagen;
import com.campusdual.redsocial.model.post.PostTexto;
import com.campusdual.redsocial.model.post.PostVideo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {

    // propiedades
    private String nombreUsuario;
    private ArrayList<Usuario> listaSeguidosUsuario = new ArrayList<>();
    private ArrayList<Post> listaPostsUsuario = new ArrayList<>();
    private ArrayList<Usuario> listaSeguidoresUsuario = new ArrayList<>();

    // constructores
    public Usuario(final String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    // getters y setters
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(final String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public ArrayList<Usuario> getListaSeguidosUsuario() {
        return this.listaSeguidosUsuario;
    }

    public ArrayList<Post> getListaPostsUsuario() {
        return this.listaPostsUsuario;
    }

    public ArrayList<Usuario> getListaSeguidoresUsuario() {
        return this.listaSeguidoresUsuario;
    }

    // metodos
    public void crearPost(int opcion, String titulo, String[] datos) {
        switch (opcion) {
            case 1: // PostTexto
                crearPostTexto(titulo, datos[0]);
                break;
            case 2: // PostImagen
                crearPostImagen(titulo, Integer.parseInt(datos[0]), Integer.parseInt(datos[1]));
                break;
            default: // PostVideo
                crearPostVideo(titulo, datos[0], Integer.parseInt(datos[1]));
                break;
        }
    }

    public void crearPostTexto(String titulo, String contenido) {
        PostTexto nuevoPost = new PostTexto(titulo, this, contenido);
        publicarPost(nuevoPost, "texto");
    }

    public void crearPostImagen(String titulo, int ancho, int alto) {
        PostImagen nuevoPost = new PostImagen(titulo, this, ancho, alto);
        publicarPost(nuevoPost, "imagen");
    }

    public void crearPostVideo(String titulo, String calidadPostVideo, int duracion) {
        PostVideo nuevoPost = new PostVideo(titulo, this, calidadPostVideo, duracion);
        publicarPost(nuevoPost, "vídeo");
    }

    public void publicarPost(Post post, String tipoPost) {
        this.getListaPostsUsuario().add(post);
        Ayuda.mostrarMensajeFinalizacion("Post de " + tipoPost + " publicado con éxito.");
    }


    public void eliminarPost(Post post) {
        this.getListaPostsUsuario().remove(post);
        Ayuda.mostrarMensajeFinalizacion("Post eliminado.");
    }

    public void seguirUsuario(Usuario usuarioASeguir) {
        this.getListaSeguidosUsuario().add(usuarioASeguir);
        usuarioASeguir.getListaSeguidoresUsuario().add(this);
    }
/* Separar en dos metodos */
    public void dejarDeSeguirUsuario(Usuario usuarioADejarDeSeguir, boolean eliminandoCuenta) {
        this.getListaSeguidosUsuario().remove(usuarioADejarDeSeguir);
        if (eliminandoCuenta) {
            this.getListaSeguidoresUsuario().remove(usuarioADejarDeSeguir);
        } else {
            usuarioADejarDeSeguir.getListaSeguidoresUsuario().remove(this);
        }
    }

    public ArrayList<Post> mostrarListaComentariosUsuario() {
        ArrayList<Post> listaPosts = new ArrayList<>();
        for (Usuario usuario : MenuBienvenida.getListaUsuarios()) {
            for (int i = usuario.getListaPostsUsuario().size() - 1; i >= 0; i--) {
                Post post = usuario.getListaPostsUsuario().get(i);
                boolean hayComentarios = false;
                for (int a = post.getListaComentariosPost().size() - 1; a >= 0; a--) {
                    Comentario comentario = post.getListaComentariosPost().get(a);
                    if (comentario.getUsuarioPropietarioComentario().equals(
                            OpcionesIniciarSesion.getUsuarioAutenticado())) {
                        if (!hayComentarios) {
                            listaPosts.add(post);
                            String propietarioComentario = post.getUsuarioPropietarioPost() ==
                                    OpcionesIniciarSesion.getUsuarioAutenticado() ?
                                    "Tú" : post.getUsuarioPropietarioPost().getNombreUsuario();
                            System.out.println("🍃 " + listaPosts.size() + ". (" + propietarioComentario + ") " +
                                    post.getTituloPost().toUpperCase() + " -> " +
                                    Ayuda.formatearFecha(post.getFechaPost()) + " (" + post.obtenerTipoPost() + ")");
                            hayComentarios = true;
                        }
                        System.out.println("- " + comentario.getTextoComentario());
                    }
                }
                if (hayComentarios) {
                    System.out.println("\n");
                }
            }
        }
        return listaPosts;
    }

    public String mostrarInformacionCuenta(String mostrarSiguiendo) {

        String nombreClaseTexto = PostTexto.class.getSimpleName();
        String nombreClaseImagen = PostImagen.class.getSimpleName();
        String nombreClaseVideo = PostVideo.class.getSimpleName();

        int contadorPostTexto = 0;
        int contadorPostImagen = 0;
        int contadorPostVideo = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("✨ Nombre de usuario ->  ").append(this.getNombreUsuario()).append(mostrarSiguiendo).append("\n");
        sb.append("✨ Seguidores ->  ").append(this.getListaSeguidoresUsuario().size()).append("\n");
        sb.append("✨ Seguidos ->  ").append(this.getListaSeguidosUsuario().size()).append("\n");
        for (Post post : this.getListaPostsUsuario()) {
            if (nombreClaseTexto.equals(post.getClass().getSimpleName())) {
                contadorPostTexto++;
            } else if (nombreClaseImagen.equals(post.getClass().getSimpleName())) {
                contadorPostImagen++;
            } else if (nombreClaseVideo.equals(post.getClass().getSimpleName())) {
                contadorPostVideo++;
            }
        }
        sb.append("✨ Post subidos  (").append(this.getListaPostsUsuario().size()).append(") \n");
        sb.append("\tPost de texto  ->  ").append(contadorPostTexto).append("\n");
        sb.append("\tPost de imagen ->  ").append(contadorPostImagen).append("\n");
        sb.append("\tPost de video  ->  ").append(contadorPostVideo).append("\n");

        return sb.toString();
    }

    public int mostrarMisSeguidos() {
        int contadorOpciones = 1;
        if (this.getListaSeguidosUsuario().isEmpty()) {
            System.out.println(OpcionesIniciarSesion.getUsuarioAutenticado() == this ?
                    "Actualmente no estás siguiendo a nadie.\n" :
                    "Actualmente " + this.getNombreUsuario() + " no sigue a nadie.\n");
        } else {
            String mostrarTu;
            for (Usuario seguido : this.getListaSeguidosUsuario()) {
                mostrarTu = seguido == OpcionesIniciarSesion.getUsuarioAutenticado() ? "(Tú) " : "";
                System.out.println(contadorOpciones + ". " + mostrarTu + seguido.getNombreUsuario());
                contadorOpciones++;
            }
        }
        return contadorOpciones;
    }

    public int mostrarMisSeguidores() {
        int contadorOpciones = 1;
        if (this.getListaSeguidoresUsuario().isEmpty()) {
            System.out.println(OpcionesIniciarSesion.getUsuarioAutenticado() == this ?
                    "Actualmente no tienes seguidores.\n" :
                    this.getNombreUsuario() + " no tiene seguidores.\n");
        } else {
            String mostrarTu;
            for (Usuario seguidor : this.getListaSeguidoresUsuario()) {
                mostrarTu = seguidor == OpcionesIniciarSesion.getUsuarioAutenticado() ? "(Tú) " : "";
                System.out.println(contadorOpciones + ". " + mostrarTu + seguidor.getNombreUsuario());
                contadorOpciones++;
            }
        }
        return contadorOpciones;
    }

    //sugerencias de amistad

    public ArrayList<Usuario> mostrarSugerenciasAmistad() {
        int precisionDeSugerencia = 3; /* cuantas personas a las que sigues tienen que seguir a la persona para que se
        te sugiera */
        System.out.println("🍃 ---------- PERSONAS QUE QUIZÁS CONOZCAS ---------- 🍃\n");
        ArrayList<Usuario> listaSeguidosUsuario = this.getListaSeguidosUsuario();
        ArrayList<Usuario> listaSugerencias = new ArrayList<>();
        for (Usuario seguido : listaSeguidosUsuario) {
            for (Usuario seguidoDeSeguido : seguido.getListaSeguidosUsuario()) {
                // candidato potencial para sugerencia
                if (!Ayuda.usuarioSigueA(this, seguidoDeSeguido) && seguidoDeSeguido != this) {
                    int contador = 0;
                    for (Usuario miSeguidoSigue : listaSeguidosUsuario) {
                        if (Ayuda.usuarioSigueA(miSeguidoSigue, seguidoDeSeguido)) {
                            contador++;
                            if (contador >= precisionDeSugerencia) {
                                if(!listaSugerencias.contains(seguidoDeSeguido)){
                                    listaSugerencias.add(seguidoDeSeguido);
                                    System.out.println(seguidoDeSeguido.getNombreUsuario() + "\n");
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("🍃 -------------------------💫------------------------- 🍃\n");
        return  listaSugerencias;
    }

    //muro
    public List<Post> mostrarMuro(int cantidadDeseadaPostsEnMuro) {
        List<Post> muro = crearMuro(cantidadDeseadaPostsEnMuro);
        muro = ordenarMuroPorFechaDescendiente(muro);
        return darFormatoAMuro(muro);
    }

    private List<Post> crearMuro(int cantidadDeseadaPostsEnMuro) {
        if (0 > cantidadDeseadaPostsEnMuro) cantidadDeseadaPostsEnMuro = 1;

        List<Post> listaMuro = new ArrayList<>();
        List<Post> listaPosts;
        int indicePublicacion = 0;

        do {
            boolean seAgregoPost = false;
            for (Usuario seguidor : this.getListaSeguidosUsuario()) {
                listaPosts = seguidor.getListaPostsUsuario();

                if (listaPosts.size() > indicePublicacion) {
                    Post post = listaPosts.get(indicePublicacion);

                    if (!listaMuro.contains(post)) {
                        listaMuro.add(post);
                        seAgregoPost = true;
                    }

                    if (cantidadDeseadaPostsEnMuro == listaMuro.size()) {
                        break;
                    }
                }
            }
            if (!seAgregoPost) {
                break;
            }
            indicePublicacion++;

        } while (cantidadDeseadaPostsEnMuro > listaMuro.size());

        return listaMuro;
    }

    private List<Post> ordenarMuroPorFechaDescendiente(List<Post> muro) {
        Collections.sort(muro);
        return muro;
    }

    private List<Post> darFormatoAMuro(List<Post> muro) {
        StringBuilder sb = new StringBuilder();
        int contadorPost = 3;
        if (!muro.isEmpty()) {
            sb.append("🍃 ---------- ÚLTIMAS PUBLICACIONES ---------- 🍃\n");
            sb.append(String.format("\n%-15s \t%-20s \t%-35s", "Usuario", "Fecha", "Título del post"));
            sb.append("\n____________________________________________________________");
            for (int i = 0; i < muro.size(); i++) {
                sb.append(String.format("\n%-15s \t%-20s \t%-35s\n",
                        muro.get(i).getUsuarioPropietarioPost().getNombreUsuario(),
                        Ayuda.formatearFecha(muro.get(i).getFechaPost()),
                        ++contadorPost + ". " + muro.get(i).getTituloPost()));
            }
            sb.append("\n🍃 ---------------------------💫--------------------------- 🍃\n");
        } else {
            if (this.getListaSeguidosUsuario().isEmpty()) {
                sb.append("🍃 ---- SIGUE A USUARIOS PARA VER LAS ÚLTIMAS PUBLICACIONES ---- 🍃\n");
            } else {
                sb.append("🍃 ---- LOS USUARIOS A LOS QUE SIGUES AÚN NO HAN PUBLICADO NADA ---- 🍃\n");
            }
        }
        System.out.println(sb.toString());
        return muro;
    }
}