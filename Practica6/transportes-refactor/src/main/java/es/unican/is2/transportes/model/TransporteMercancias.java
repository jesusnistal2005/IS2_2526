package es.unican.is2.transportes.model;

/**
 * WMC (McCabe): ctor=2, getToneladas=1, calcularExtra=1, total=4
 * CCog: ctor=1, resto=0, total=1
 */
public class TransporteMercancias extends Transporte {
    private final int toneladas;

    public TransporteMercancias(double horas, int toneladas) {
        super(horas, CategoriaTransporte.MERCANCIAS);
        if (toneladas <= 0) {
            throw new IllegalArgumentException("Las toneladas deben ser mayores que 0");
        }
        this.toneladas = toneladas;
    }

    public int getToneladas() {
        return toneladas;
    }

    @Override
    protected double calcularExtra() {
        return toneladas * 2;
    }
}

