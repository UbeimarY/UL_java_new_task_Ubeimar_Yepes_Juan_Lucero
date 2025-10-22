package com.ucc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.ucc.connection.DatabaseConnection;

public class Insert {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DatabaseConnection.getInstanceConnection()) {
            String sql = "INSERT INTO sakila.actor(first_name, last_name) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Nombre2 Aleatorio");
            ps.setString(2, "Apellido2 Aleatorio");
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Se creó el actor correctamente");
        } catch (Exception e) {
            System.out.println("Conexión fallida");
        }
    }
}
