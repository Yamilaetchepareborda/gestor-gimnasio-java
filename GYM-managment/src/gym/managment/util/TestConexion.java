package gym.managment.util;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection con = ConexionBD.getConnection()) {
            System.out.println("✅ Conexión exitosa a la base de datos Railway!");
        } catch (Exception e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
