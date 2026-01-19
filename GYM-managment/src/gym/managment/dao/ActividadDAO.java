

package gym.managment.dao;

import gym.managment.model.Actividad;
import gym.managment.model.Entrenador;
import gym.managment.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla actividades.
 * Columnas esperadas en la tabla `actividades`:
 * idActividad, nombreActividad, descripcion, duracionMinutos, cupoMaximo, idEntrenador, fk_cliente
 */
public class ActividadDAO {

    public void insertar(Actividad a) throws SQLException {
        String sql = "INSERT INTO actividades (nombreActividad, descripcion, duracionMinutos, cupoMaximo, idEntrenador, fk_cliente) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, a.getNombreActividad());
            ps.setString(2, a.getDescripcion());
            ps.setInt(3, a.getDuracionMinutos());
            ps.setInt(4, a.getCupoMaximo());
            if (a.getEntrenador() != null) ps.setInt(5, a.getEntrenador().getIdEntrenador());
            else ps.setNull(5, Types.INTEGER);
            if (a.getFkCliente() != null) ps.setInt(6, a.getFkCliente());
            else ps.setNull(6, Types.INTEGER);

            ps.executeUpdate();

            try (ResultSet gk = ps.getGeneratedKeys()) {
                if (gk.next()) {
                    a.setIdActividad(gk.getInt(1));
                }
            }
        }
    }

    public List<Actividad> listar() throws SQLException {
        List<Actividad> lista = new ArrayList<>();
        String sql = """
            SELECT a.idActividad, a.nombreActividad, a.descripcion, a.duracionMinutos, a.cupoMaximo,
                   a.idEntrenador, e.nombreEntrenador, e.especialidad,
                   a.fk_cliente
            FROM actividades a
            LEFT JOIN entrenadores e ON a.idEntrenador = e.idEntrenador
            ORDER BY a.idActividad DESC
        """;
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Entrenador entrenador = null;
                int idEnt = rs.getInt("idEntrenador");
                if (!rs.wasNull()) {
                    entrenador = new Entrenador(idEnt, rs.getString("nombreEntrenador"), rs.getString("especialidad"));
                }

                Integer fkCliente = rs.getInt("fk_cliente");
                if (rs.wasNull()) fkCliente = null;

                Actividad a = new Actividad(
                    rs.getInt("idActividad"),
                    rs.getString("nombreActividad"),
                    rs.getString("descripcion"),
                    rs.getInt("duracionMinutos"),
                    rs.getInt("cupoMaximo"),
                    entrenador,
                    fkCliente
                );
                lista.add(a);
            }
        }
        return lista;
    }


    public void actualizar(Actividad a) throws SQLException {
        String sql = "UPDATE actividades SET nombreActividad=?, descripcion=?, duracionMinutos=?, cupoMaximo=?, idEntrenador=?, fk_cliente=? WHERE idActividad=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNombreActividad());
            ps.setString(2, a.getDescripcion());
            ps.setInt(3, a.getDuracionMinutos());
            ps.setInt(4, a.getCupoMaximo());
            if (a.getEntrenador() != null) ps.setInt(5, a.getEntrenador().getIdEntrenador());
            else ps.setNull(5, Types.INTEGER);
            if (a.getFkCliente() != null) ps.setInt(6, a.getFkCliente());
            else ps.setNull(6, Types.INTEGER);
            ps.setInt(7, a.getIdActividad());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idActividad) throws SQLException {
        String sql = "DELETE FROM actividades WHERE idActividad = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idActividad);
            ps.executeUpdate();
        }
    }
}

