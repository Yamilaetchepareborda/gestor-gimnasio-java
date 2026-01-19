package gym.managment.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo Actividad.
 * Ahora puede almacenar referencia a entrenador y opcionalmente a un cliente (fk_cliente).
 */
public class Actividad {
    private int idActividad;
    private String nombreActividad;
    private String descripcion;
    private int duracionMinutos;
    private int cupoMaximo;
    private Entrenador entrenador; // relación 1:1 (puede ser null)
    private Integer fkCliente;     // FK opcional a clientes (puede ser null)

    // Constructores
    public Actividad() {
        this.descripcion = "";
    }

    public Actividad(int idActividad, String nombreActividad, String descripcion,
                     int duracionMinutos, int cupoMaximo, Entrenador entrenador, Integer fkCliente) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.duracionMinutos = duracionMinutos;
        this.cupoMaximo = cupoMaximo;
        this.entrenador = entrenador;
        this.fkCliente = fkCliente;
    }

    // Getters / Setters
    public int getIdActividad() { return idActividad; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }

    public String getNombreActividad() { return nombreActividad; }
    public void setNombreActividad(String nombreActividad) { this.nombreActividad = nombreActividad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    public int getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }

    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }

    public Integer getFkCliente() { return fkCliente; }
    public void setFkCliente(Integer fkCliente) { this.fkCliente = fkCliente; }

    @Override
    public String toString() {
        String ent = (entrenador != null) ? entrenador.getNombreEntrenador() : "Sin entrenador";
        return idActividad + " - " + nombreActividad + " (" + ent + ")";
    }
}