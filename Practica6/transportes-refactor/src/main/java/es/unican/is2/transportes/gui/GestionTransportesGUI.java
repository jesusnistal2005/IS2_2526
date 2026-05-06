package es.unican.is2.transportes.gui;

import java.util.LinkedList;
import java.util.List;

import es.unican.is2.transportes.model.Conductor;
import es.unican.is2.transportes.model.GestionTransportes;
import es.unican.is2.transportes.model.Transporte;
import es.unican.is2.transportes.model.TransporteFactory;
import fundamentos.Lectura;
import fundamentos.Menu;
import fundamentos.Mensaje;

/**
 * WMC (McCabe): main=18, mensaje=1, total=19
 * CCog: main=42, mensaje=0, total=42
 */
public class GestionTransportesGUI {

    public static void main(String[] args) {
        final int ANHADE_CONDUCTOR = 0;
        final int ANHADE_TRANSPORTE = 1;
        final int SUELDO_CONDUCTOR = 2;
        final int MEJOR_CONDUCTOR = 3;

        String dni;
        Lectura lect;
        Conductor conductor;
        TransporteFactory transporteFactory = new TransporteFactory();

        GestionTransportes gt = new GestionTransportes();

        Menu menu = new Menu("Transportes");
        menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
        menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
        menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
        menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);

        while (true) {
            int opcion = menu.leeOpcion();
            switch (opcion) {
                case ANHADE_CONDUCTOR:
                    lect = new Lectura("Datos Conductor");
                    lect.creaEntrada("DNI", "");
                    lect.creaEntrada("Nombre", "");
                    lect.creaEntrada("Apellido1", "");
                    lect.creaEntrada("Apellido2", "");
                    lect.creaEntrada("Direccion", "");
                    lect.esperaYCierra();
                    dni = lect.leeString("DNI");
                    String nombre = lect.leeString("Nombre");
                    String apellido1 = lect.leeString("Apellido1");
                    String apellido2 = lect.leeString("Apellido2");
                    String direccion = lect.leeString("Direccion");
                    if (!gt.anhadeConductor(dni, nombre, apellido1, apellido2, direccion)) {
                        mensaje("ERROR", "Ya existe un conductor con DNI " + dni);
                    }
                    break;
                case ANHADE_TRANSPORTE:
                    lect = new Lectura("Nuevo transporte");
                    lect.creaEntrada("DNI", "");
                    lect.creaEntrada("Tipo Transporte: P | M | MP", "");
                    lect.creaEntrada("Horas", 0);
                    lect.creaEntrada("Personas", 0);
                    lect.creaEntrada("Toneladas", 0);
                    lect.esperaYCierra();
                    dni = lect.leeString("DNI");
                    String tipo = lect.leeString("Tipo Transporte: P | M | MP");
                    int horas = lect.leeInt("Horas");
                    int personas = lect.leeInt("Personas");
                    int toneladas = lect.leeInt("Toneladas");
                    conductor = gt.buscaConductor(dni);
                    if (conductor != null) {
                        Transporte transporte = transporteFactory.crear(tipo, horas, personas, toneladas);
                        conductor.addTransporte(transporte);
                    } else {
                        mensaje("ERROR", "No existe un conductor con DNI " + dni);
                    }
                    break;
                case SUELDO_CONDUCTOR:
                    lect = new Lectura("Sueldo Conductor");
                    lect.creaEntrada("DNI", "");
                    lect.esperaYCierra();
                    dni = lect.leeString("DNI");
                    conductor = gt.buscaConductor(dni);
                    if (conductor != null) {
                        mensaje("Sueldo", "El sueldo del conductor es: " + conductor.calcularSueldo());
                    } else {
                        mensaje("ERROR", "No existe un conductor con DNI " + dni);
                    }
                    break;
                case MEJOR_CONDUCTOR:
                    List<Conductor> resultado = new LinkedList<>();
                    double maxSueldo = 0.0;
                    for (Conductor c : gt.conductores()) {
                        double sueldo = c.calcularSueldo();
                        if (sueldo > maxSueldo) {
                            maxSueldo = sueldo;
                            resultado.clear();
                            resultado.add(c);
                        } else if (sueldo == maxSueldo) {
                            resultado.add(c);
                        }
                    }
                    StringBuilder msj = new StringBuilder();
                    if (resultado.isEmpty()) {
                        msj.append("No hay conductores");
                    } else {
                        for (Conductor c : resultado) {
                            msj.append(c.getNombre()).append(" ").append(c.getApellido1()).append("\n");
                        }
                    }
                    mensaje("MEJOR CONDUCTOR", msj.toString());
                    break;
                default:
                    mensaje("ERROR", "Opcion no valida");
                    break;
            }
        }
    }

    private static void mensaje(String titulo, String txt) {
        Mensaje msj = new Mensaje(titulo);
        msj.escribe(txt);
    }
}

