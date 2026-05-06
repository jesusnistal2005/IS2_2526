package es.unican.is2.transportes.model;

/**
 * WMC (McCabe): ctor=1, calcularExtra=1, total=2
 * CCog: total=0
 */
public class TransporteMercanciasPeligrosas extends TransporteMercancias {

    public TransporteMercanciasPeligrosas(double horas, int toneladas) {
        super(horas, toneladas);
    }

    @Override
    protected double calcularExtra() {
        return super.calcularExtra() + 50;
    }
}

