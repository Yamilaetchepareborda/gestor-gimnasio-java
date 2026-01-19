package gym.managment.controller;

import gym.managment.dao.ActividadDAO;
import gym.managment.model.Actividad;
import gym.managment.model.Entrenador;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador principal para la gestión de actividades del gimnasio.
 * Coordina las operaciones CRUD sobre las actividades e interactúa con la capa DAO.
 * Actúa como intermediario entre la interfaz gráfica (Swing) y la base de datos.
 */
public class ActividadController {

    private final ActividadDAO dao;

    /**
     * Inicializa el controlador y su correspondiente acceso a datos.
     */
    public ActividadController() {
        this.dao = new ActividadDAO();
    }

    /**
     * Registra una nueva actividad en la base de datos, validando los datos principales antes de la inserción.
     *
     * @param nombre nombre asignado a la actividad (obligatorio).
     * @param descripcion texto descriptivo de la actividad.
     * @param duracionMinutos duración de la actividad en minutos.
     * @param cupoMaximo número máximo de participantes permitidos.
     * @param entrenador entrenador asociado a la actividad.
     * @param fkCliente identificador opcional del cliente vinculado (puede ser null).
     * @throws SQLException si ocurre un error durante la inserción.
     * @throws IllegalArgumentException si el nombre es nulo o vacío.
     */
    public void agregarActividad(String nombre, String descripcion, int duracionMinutos, int cupoMaximo,
                                 Entrenador entrenador, Integer fkCliente) throws SQLException {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Nombre obligatorio");

        Actividad a = new Actividad();
        a.setNombreActividad(nombre.trim());
        a.setDescripcion(descripcion == null ? "" : descripcion.trim());
        a.setDuracionMinutos(duracionMinutos);
        a.setCupoMaximo(cupoMaximo);
        a.setEntrenador(entrenador);
        a.setFkCliente(fkCliente);
        dao.insertar(a);
    }

    /**
     * Recupera todas las actividades registradas en el sistema.
     *
     * @return lista de objetos {@link Actividad}.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    public List<Actividad> listarActividades() throws SQLException {
        return dao.listar();
    }

    /**
     * Actualiza los datos de una actividad existente en la base de datos.
     *
     * @param a objeto {@link Actividad} con la información modificada.
     * @throws SQLException si ocurre un error durante la actualización.
     */
    public void actualizarActividad(Actividad a) throws SQLException {
        dao.actualizar(a);
    }

    /**
     * Elimina una actividad del sistema según su identificador único.
     *
     * @param idActividad identificador de la actividad a eliminar.
     * @throws SQLException si ocurre un error durante la eliminación.
     */
    public void eliminarActividad(int idActividad) throws SQLException {
        dao.eliminar(idActividad);
    }
}