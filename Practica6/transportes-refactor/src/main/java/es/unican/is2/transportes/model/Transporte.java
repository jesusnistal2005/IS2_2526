package es.unican.is2.transportes.model;

import java.util.Objects;

/**
 * Transporte base con comportamiento común de cálculo de sueldo extra.
 * WMC (McCabe): ctor=2, calcularPago=1, getHoras=1, getCategoria=1, total=5
 * CCog: ctor=1, resto=0, total=1
 */
public abstract class Transporte {
    private final double horas;
    private final CategoriaTransporte categoria;

    protected Transporte(double horas, CategoriaTransporte categoria) {
        if (horas <= 0 || categoria == null) {
            throw new IllegalArgumentException("Horas y categoria deben ser validas");
        }
        this.horas = horas;
        this.categoria = Objects.requireNonNull(categoria);
    }

    public final double getHoras() {
        return horas;
    }

    public final CategoriaTransporte getCategoria() {
        return categoria;
    }

    public final double calcularPago() {
        return horas * 5 + calcularExtra();
    }

    protected abstract double calcularExtra();
}

