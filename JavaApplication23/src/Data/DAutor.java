/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Entities.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author bradl
 */
public class DAutor {

    private Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;

    public void obtRegistros() throws SQLException {
        try {
            conn = Conexion.obtConexion();
            String tSQL = "Selec * from Autor";
            ps = conn.prepareStatement(tSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                    + ResultSet.HOLD_CURSORS_OVER_COMMIT
            );
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al obtener registros" + ex.getMessage());
        }
    }

    public ArrayList<Autor> listaAutor() {
        ArrayList<Autor> lista = new ArrayList<>();
        try {
            this.obtRegistros();
            while (rs.next()) {
                lista.add(new Autor(
                        rs.getInt("AuthorID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar el autor: " + ex.getMessage());
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

                if (rs != null) {
                    ps.close();
                }

                if (rs != null) {
                    Conexion.cerrarConexion(conn);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

        return lista;

    }

    public boolean guardarAutor(Autor a) {
        boolean guardado = false;
        try {
            this.obtRegistros();
            rs.moveToInsertRow();
            rs.updateString("FirstName", a.getFirstName());
            rs.updateString("Lastname", a.getLastName());
            rs.insertRow();
            rs.moveToCurrentRow();
        } catch (SQLException ex) {
            System.out.println("Error al guardar autor" + ex.getMessage());
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

                if (rs != null) {
                    ps.close();
                }

                if (rs != null) {
                    Conexion.cerrarConexion(conn);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return guardado;
    }

    public boolean existeAutor(int id) {
        boolean resp = false;
        try {
            this.obtRegistros();
            while (rs.next()) {
                if (rs.getInt("AuthorID") == id) {
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar autor: " + ex.getMessage());
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

                if (rs != null) {
                    ps.close();
                }

                if (rs != null) {
                    Conexion.cerrarConexion(conn);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return resp;
    }

    public boolean editarAutor(Autor a) {
        boolean resp = false;
        try {
            this.obtRegistros();
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("AuthorID") == a.getAuthorID()) {
                    rs.updateString("FirstName", a.getFirstName());
                    rs.updateString("LastName", a.getLastName());
                    rs.updateRow();
                    resp = true;
                    break;

                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al editar: " + ex.getMessage());
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }

                if (rs != null) {
                    ps.close();
                }

                if (rs != null) {
                    Conexion.cerrarConexion(conn);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return resp;
    }
    
    public boolean eliminarAutor(int id){
        boolean resp = false; 
        try{
           this.obtRegistros();
           rs.beforeFirst();
           while(rs.next()){
               if(rs.getInt("AuthorID") == id){
                   rs.deleteRow();
                   resp = true;
                   break;
               }
           }
           
        }catch(SQLException ex){
            System.out.println("Error al eliminar autor: " +ex.getMessage());
        }finally {

            try {
                if (rs != null) {
                    rs.close();
                }

                if (rs != null) {
                    ps.close();
                }

                if (rs != null) {
                    Conexion.cerrarConexion(conn);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resp;
    }
}
