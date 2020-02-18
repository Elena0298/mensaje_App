/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensajes_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author elena
 */
public class MensajeDao {
    
    public static void crearMensajeDB(Mensaje mensaje){
        Conexion db_connect = new Conexion();
        
        try (Connection conexion = db_connect.get_connection()){
            PreparedStatement pS = null;
            
            try {
                String query = "INSERT INTO mensajes (mensaje, autor_mensaje) VALUES (?,?);";
                pS = conexion.prepareStatement(query);
                pS.setString(1, mensaje.getMensaje());
                pS.setString(2, mensaje.getAutor_mensaje());
                pS.executeUpdate();
                System.out.println("Mensaje creado");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
    
    public static void leerMensajeDB(){
        Conexion db_connect = new Conexion();
        
        PreparedStatement pS = null;
        ResultSet rs = null;
        
        try (Connection conexion = db_connect.get_connection()){
            String query = "SELECT * FROM mensajes";
            pS = conexion.prepareStatement(query);
            rs = pS.executeQuery();
            
            while(rs.next()){
                System.out.println("ID: " + rs.getInt("id_mensaje"));
                System.out.println("Mensaje: " + rs.getString("mensaje"));
                System.out.println("Auto: " + rs.getString("autor_mensaje"));
                System.out.println("Fecha: " + rs.getString("fecha_mensaje"));
                System.out.println("");
            }
        }catch(SQLException e){
            System.out.println("No se pudieron recuperar los mensajes.");
            System.out.println(e);
        }
        
    }
    
    public static void borrarMensaje(int id_mensaje){
        Conexion db_connect = new Conexion();
        
        try (Connection conexion = db_connect.get_connection()){
            PreparedStatement pS = null;
            
            try {
                String query = "DELETE FROM mensajes WHERE id_mensaje = ?";
                pS = conexion.prepareStatement(query);
                pS.setInt(1, id_mensaje);
                pS.executeUpdate();
                System.out.println("El mensaje ha sido borrado.");
                
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("No se pudo borrar el mensaje");
            }
            
        }catch(SQLException e){
            System.out.println(e);
            
        }
    }
    
    public static void actualizarMensajeDB(Mensaje mensaje){
        Conexion db_connect = new Conexion();
        
        try (Connection conexion = db_connect.get_connection()){
            PreparedStatement pS = null;
            
            try {
                String query = "UPDATE mensajes SET mensaje =? WHERE id_mensaje =?";
                pS = conexion.prepareStatement(query);
                pS.setString(1, mensaje.getMensaje());
                pS.setInt(2, mensaje.getId_mensaje());
                pS.executeUpdate();
                System.out.println("El mensaje se actualizó correctamente");

            } catch (SQLException ex) {
                System.out.println(ex);
                System.out.println("No se pudo actualizar el mensaje");
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
