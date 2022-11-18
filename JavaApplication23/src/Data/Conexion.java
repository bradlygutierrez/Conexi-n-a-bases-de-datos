/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.sql.*;


/**
 *
 * @author bradl
 */
public class Conexion {

    private static Conexion conx = null;
    private static Connection con = null;
    private static String url = "jdbc.sqlserver://localhost;"
            + "databaseName = BDLibros"
            + "Persist Security Info=True";
    private static String user = "sa";
    private static String password = "123";

    public Conexion() {
    }

    public static Conexion obtInstancia() {
        if (conx == null) {
            conx = new Conexion();
        }
        return conx;
    }

    public static Connection obtConexion() {
        if (estaConectado() == false) {
            Conexion.crearConexion();
        }
        return con;
    }

    @SuppressWarnings("UseSpecificCatch")
    public static boolean estaConectado() {
        boolean resp = false;
        try {
            resp = !((con == null) || (con.isClosed()));
        } catch (Exception ex) {
            System.out.println("Error al consultar el estado de la conexión: " + ex.getMessage());
        }
        return resp;
    }

    public static void crearConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            con = null;
            System.out.println("Error al cargar el driver: " + ex.getMessage());
        } catch (SQLException ex) {
            con = null;
            System.out.println("Error al establecer conexión: " + ex.getMessage());
        }
        
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public static void cerrarConexion(Connection con){
        if(estaConectado() != false){
            try{
                con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
    }

}
