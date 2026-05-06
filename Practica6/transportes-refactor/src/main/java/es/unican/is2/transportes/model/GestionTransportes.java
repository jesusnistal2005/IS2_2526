package es.unican.is2.transportes.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * WMC (McCabe): buscaConductor=2, anhadeConductor=2, conductores=1, total=5
 * CCog: buscaConductor=1, anhadeConductor=1, total=2
 */
public class GestionTransportes {

    private final Map<String, Conductor> conductoresPorDni = new LinkedHashMap<>();

    public Conductor buscaConductor(String dni) {
        if (dni == null) {
            return null;
        }
        return conductoresPorDni.get(dni);
    }

    public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) {
        if (buscaConductor(dni) != null) {
            return false;
        }
        conductoresPorDni.put(dni, new Conductor(dni, nombre, apellido1, apellido2, direccion));
        return true;
    }

    public List<Conductor> conductores() {
        return new ArrayList<>(conductoresPorDni.values());
    }
}

