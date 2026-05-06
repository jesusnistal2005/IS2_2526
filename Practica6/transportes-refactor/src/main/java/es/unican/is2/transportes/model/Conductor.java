package es.unican.is2.transportes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WMC (McCabe): ctor=2, calcularSueldo=1, addTransporte=2, getters(8x1), wrappers(3x1), total=16
 * CCog: ctor=1, addTransporte=1, resto=0, total=2
 */
public class Conductor {

    private static final double SUELDO_BASE = 700;

    private final List<Transporte> transportes;
    private final String dni;
    private final String nombre;
    private final String apellido1;
    private final String apellido2;
    private final String direccion;

    public Conductor(String dni, String nombre, String apellido1, String apellido2, String direccion) {
        if (dni == null || nombre == null || apellido1 == null || direccion == null) {
            throw new IllegalArgumentException("Los datos obligatorios del conductor no pueden ser nulos");
        }
        this.transportes = new ArrayList<>();
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Transporte> getTransportes() {
        return List.copyOf(transportes);
    }

    public void addTransporte(Transporte transporte) {
        if (transporte == null) {
            throw new IllegalArgumentException("El transporte no puede ser nulo");
        }
        transportes.add(transporte);
    }

    public double calcularSueldo() {
        return SUELDO_BASE + transportes.stream().mapToDouble(Transporte::calcularPago).sum();
    }

    // Compatibilidad con la API heredada
    public String dni() {
        return getDni();
    }

    public String apellido2() {
        return getApellido2();
    }

    public void anhadeTransporte(Transporte transporte) {
        addTransporte(transporte);
    }

    public double sueldo() {
        return calcularSueldo();
    }
}

