package es.unican.is2.transportes.model;

/**
 * WMC (McCabe): ctor=2, getPersonas=1, calcularExtra=2, total=5
 * CCog: ctor=1, calcularExtra=1, total=2
 */
public class TransportePersonas extends Transporte {
    private final int personas;

    public TransportePersonas(double horas, int personas) {
        super(horas, CategoriaTransporte.PERSONAS);
        if (personas <= 0) {
            throw new IllegalArgumentException("El numero de personas debe ser mayor que 0");
        }
        this.personas = personas;
    }

    public int getPersonas() {
        return personas;
    }

    @Override
    protected double calcularExtra() {
        if (personas < 10) {
            return getHoras() * 0.5;
        }
        return getHoras();
    }
}

