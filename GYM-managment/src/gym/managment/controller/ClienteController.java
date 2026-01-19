package gym.managment.controller;

import gym.managment.dao.ClienteDAO;
import gym.managment.model.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones CRUD de la entidad {@link Cliente}.
 * Actúa como intermediario entre la capa de vista y la capa de acceso a datos (DAO),
 * garantizando la separación de responsabilidades dentro de la arquitectura MVC.
 */
public class ClienteController {
    private final ClienteDAO dao = new ClienteDAO();

    /**
     * Inserta un nuevo cliente en la base de datos.
     * 
     * @param c objeto {@link Cliente} con los datos a registrar.
     * @throws SQLException si ocurre un error durante la inserción.
     */
    public void crear(Cliente c) throws SQLException { dao.insertar(c); }

    /**
     * Recupera la lista completa de clientes almacenados.
     * 
     * @return lista de objetos {@link Cliente}.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    public List<Cliente> listar() throws SQLException { return dao.listar(); }

    /**
     * Actualiza la información de un cliente existente.
     * 
     * @param c objeto {@link Cliente} con los datos actualizados.
     * @throws SQLException si ocurre un error durante la actualización.
     */
    public void actualizar(Cliente c) throws SQLException { dao.actualizar(c); }

    /**
     * Elimina un cliente identificado por su ID.
     * 
     * @param id identificador único del cliente a eliminar.
     * @throws SQLException si ocurre un error durante la eliminación.
     */
    public void eliminar(int id) throws SQLException { dao.eliminar(id); }
}