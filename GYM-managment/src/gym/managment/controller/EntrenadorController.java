package gym.managment.controller;

import gym.managment.dao.EntrenadorDAO;
import gym.managment.model.Entrenador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Controlador responsable de la gestión de entrenadores dentro del sistema.
 * Implementa las operaciones básicas de creación, consulta, actualización y eliminación (CRUD),
 * validando datos de entrada y mostrando mensajes informativos o de error al usuario mediante Swing.
 */
public class EntrenadorController {

    private EntrenadorDAO entrenadorDAO;
    private static final Logger LOGGER = Logger.getLogger(EntrenadorController.class.getName());

    /**
     * Constructor que inicializa la instancia del DAO para el manejo de entrenadores.
     */
    public EntrenadorController() {
        this.entrenadorDAO = new EntrenadorDAO();
    }

    /**
     * Agrega un nuevo entrenador al sistema tras validar los campos.
     * Muestra mensajes de retroalimentación en caso de éxito o error.
     * 
     * @param nombre nombre completo del entrenador.
     * @param especialidad área o disciplina en la que se especializa.
     */
    public void agregarEntrenador(String nombre, String especialidad) {
        if (nombre.isEmpty() || especialidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre y especialidad no pueden estar vacíos");
            return;
        }
        try {
            Entrenador e = new Entrenador(0, nombre, especialidad); // id auto-generado por BD
            entrenadorDAO.insertar(e);
            JOptionPane.showMessageDialog(null, "Entrenador agregado correctamente.");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al agregar entrenador", ex);
            JOptionPane.showMessageDialog(null, "Error al agregar entrenador:\n" + ex.getMessage());
        }
    }

    /**
     * Obtiene todos los entrenadores registrados en la base de datos.
     * En caso de error, devuelve una lista vacía y notifica al usuario.
     * 
     * @return lista de entrenadores disponibles.
     */
    public List<Entrenador> obtenerEntrenadores() {
        try {
            return entrenadorDAO.listar();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al listar entrenadores", ex);
            JOptionPane.showMessageDialog(null, "Error al obtener entrenadores:\n" + ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Actualiza los datos de un entrenador existente tras verificar la validez de los campos.
     * 
     * @param id identificador del entrenador a modificar.
     * @param nombre nuevo nombre del entrenador.
     * @param especialidad nueva especialidad asignada.
     */
    public void actualizarEntrenador(int id, String nombre, String especialidad) {
        if (nombre.isEmpty() || especialidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre y especialidad no pueden estar vacíos");
            return;
        }
        try {
            Entrenador e = new Entrenador(id, nombre, especialidad);
            entrenadorDAO.actualizar(e);
            JOptionPane.showMessageDialog(null, "Entrenador actualizado correctamente.");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al actualizar entrenador", ex);
            JOptionPane.showMessageDialog(null, "Error al actualizar entrenador:\n" + ex.getMessage());
        }
    }

    /**
     * Elimina un entrenador identificado por su ID.
     * Si ocurre un error, se muestra un mensaje al usuario.
     * 
     * @param id identificador único del entrenador a eliminar.
     */
    public void eliminarEntrenador(int id) {
        try {
            entrenadorDAO.eliminar(id);
            JOptionPane.showMessageDialog(null, "Entrenador eliminado correctamente.");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al eliminar entrenador", ex);
            JOptionPane.showMessageDialog(null, "Error al eliminar entrenador:\n" + ex.getMessage());
        }
    }
}