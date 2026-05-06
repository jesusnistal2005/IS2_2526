package es.unican.is2.transportes.model;

/**
 * Factoria para evitar switches de creacion en GUI.
 * WMC (McCabe): crear=4, total=4
 * CCog: crear=3
 */
public class TransporteFactory {

    public Transporte crear(String tipo, double horas, int personas, int toneladas) {
        if ("P".equals(tipo)) {
            return new TransportePersonas(horas, personas);
        }
        if ("M".equals(tipo)) {
            return new TransporteMercancias(horas, toneladas);
        }
        if ("MP".equals(tipo)) {
            return new TransporteMercanciasPeligrosas(horas, toneladas);
        }
        throw new IllegalArgumentException("Tipo de transporte no valido: " + tipo);
    }
}

