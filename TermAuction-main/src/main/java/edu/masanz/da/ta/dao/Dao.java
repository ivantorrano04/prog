package edu.masanz.da.ta.dao;

import edu.masanz.da.ta.dto.*;
import edu.masanz.da.ta.utils.Security;

import java.lang.reflect.Array;
import java.sql.Wrapper;
import java.util.*;

import static edu.masanz.da.ta.conf.Ctes.*;
import static edu.masanz.da.ta.conf.Ini.*;

/**
 * Clase que simula la capa de acceso a datos. Cuando veamos las interfaces crearemos una interfaz para esta clase.
 * También crearemos una clase que implemente esa interfaz y que se conecte a una base de datos relacional.
 * Y una clase servicio que podrá utilizar cualquiera de las dos implementaciones, la simulada, la real u otra.
 * Por ahora, simplemente es una clase con métodos estáticos que simulan la interacción con una base de datos.
 */
public class Dao {


    //region Colecciones que simulan la base de datos
    private static Map<String, Usuario> mapaUsuarios;

    private static Map<Long, Item> mapaItems;

    private static Map<Long, List<Puja>> mapaPujas;
    //endregion

    //region Inicialización de la base de datos simulada
    public static void ini() {
        iniMapaUsuarios();
        iniMapaItems();
        iniMapaPujas();
    }

    private static void iniMapaUsuarios() {
        mapaUsuarios = new HashMap<>();
        for (String usuario1 : USUARIOS) {
            String[] cachos = usuario1.split("");
            String nombre = cachos[0];
            String sal = cachos[1];
            String has = cachos[2];
            String rol = cachos[3];
            Usuario nuevoUsuario = new Usuario(nombre, sal, has, rol);
            mapaUsuarios.put(nombre, nuevoUsuario);
        }
    }

    private static void iniMapaItems() {
        mapaItems = new HashMap<>();
        for (String recorrer : ITEMS) {
            String[] cachos2 = recorrer.split("");
            long id = Long.parseLong((cachos2[0]));
            String nombre = cachos2[1];
            String descripcion = cachos2[2];
            int precioInicio = Integer.parseInt(cachos2[3]);
            String urlImagen = cachos2[4];
            String nombreUsuario = cachos2[5];
            int estado = Integer.parseInt(cachos2[6]);
            boolean historico = Boolean.parseBoolean(cachos2[7]);

            Item nuevoItem = new Item(id, nombre, descripcion, precioInicio, urlImagen, nombreUsuario, estado, historico);
            mapaItems.put(id, nuevoItem);
        }
    }

    private static void iniMapaPujas() {
        mapaPujas = new HashMap<>();
        ArrayList<Puja> milista = new ArrayList<>();
        long contador = 0;
        for (String puj1 : PUJAS) {
            String[] cachos3 = puj1.split("");
            long idItem = Long.parseLong(cachos3[0]);
            String nombreUsuario = cachos3[1];
            int precioPujado = Integer.parseInt(cachos3[2]);
            String instanteTiempo = cachos3[3];
            Puja puja = new Puja(idItem, nombreUsuario, precioPujado, instanteTiempo);
            milista.add(puja);
            mapaPujas.put(contador, milista);
            contador++;
        }
    }
    //endregion

    //region Usuarios
    public static boolean autenticar(String nombreUsuario, String password) {
//        return password.equals("1234");
        String prueba = String.valueOf(mapaUsuarios.get(nombreUsuario));
        if (mapaUsuarios.containsKey(nombreUsuario)) {
            if (Objects.equals(prueba, password)) {
                System.out.println("El usuario existe");
            }
        } else {
            System.out.println("El usuario introducido no se encuentra en la base de datos ");
        }
        // TODO 04 autenticar
        return false;
    }

    public static boolean esAdmin(String nombreUsuario) {
//        return nombreUsuario.equalsIgnoreCase("Admin");
        Usuario usuario = mapaUsuarios.get(nombreUsuario);
        if (usuario == null) {
            return false;
        }
        return Objects.equals(usuario.getRol(), ROL_ADMIN);
    }

    public static List<Usuario> obtenerUsuarios() {
        // TODO 06 obtenerUsuarios
        ArrayList<Usuario> listarUsuarios = new ArrayList<>();
        for (Usuario uusario : mapaUsuarios.values()) {
            System.out.println(uusario);
            listarUsuarios.add(uusario);
        } return listarUsuarios;
    }

    public static boolean crearUsuario(String nombre, String password, boolean esAdmin) {
        if (mapaUsuarios.containsKey(nombre)) {
            return false;
        } else {
            String admin = "";
            if (esAdmin) {
                admin = ROL_ADMIN;
            } else {
                admin = ROL_USER;
            }
            String sal = Security.generateSalt();
            String has = Security.hash(sal+password);
            Usuario usuario = new Usuario(nombre, sal, has, admin);
                mapaUsuarios.put(nombre, usuario);

        }
        return true;
    }

    public static boolean modificarPasswordUsuario(String nombre, String password) {
        // TODO 08 modificarPasswordUsuario
        return false;
    }

    public static boolean modificarRolUsuario(String nombre, String rol) {
        // TODO 09 modificarRolUsuario
        return false;
    }

    public static boolean eliminarUsuario(String nombre) {
        // TODO 10 eliminarUsuario
        return true;
    }

    //endregion

    //region Validación de artículos
    public static List<Item> obtenerArticulosPendientes() {
        // TODO 11 obtenerArticulosPendientes
        return null;
    }

    public static boolean validarArticulo(long id, boolean valido) {
        // TODO 12 validarArticulo
        return false;
    }

    public static boolean validarTodos() {
        // TODO 13 validarTodos
        return true;
    }
    //endregion

    //region Gestión de artículos y pujas de administrador
    public static List<ItemPujas> obtenerArticulosConPujas() {
        // TODO 14 obtenerArticulosConPujas
        return null;
    }

    public static boolean resetearSubasta() {
        // TODO 15 resetearSubasta
        return true;
    }

    public static List<PujaItem> obtenerHistoricoGanadores() {
        // TODO 16 obtenerHistoricoGanadores
        return null;
    }
    //endregion

    //region Acciones por parte de usuario normal (no admin)

    public static Item obtenerArticuloPujable(long idArt) {
        // TODO 17 obtenerArticuloPujable
        return null;
    }

    public static List<Item> obtenerArticulosPujables() {
        // TODO 18 obtenerArticulosPujables
        return null;
    }

    public static boolean pujarArticulo(long idArt, String nombre, int precio) {
        // TODO 19 pujarArticulo
        return false;
    }

    public static List<PujaItem> obtenerPujasVigentesUsuario(String nombreUsuario) {
        // TODO 20 obtenerPujasVigentesUsuario
        return null;
    }

    public static boolean ofrecerArticulo(Item item) {
        // TODO 21 ofrecerArticulo
        return true;
    }

    //endregion
}
