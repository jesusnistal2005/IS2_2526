# Practica 6 - Refactorizaciones

## 1. Refactorizaciones aplicadas (ordenadas)

1. **Creacion de proyecto Maven legado**: organizacion de clases originales en `src/main/java` y tests en `src/test/java`.
2. **Aislamiento de dependencias de GUI**: inclusion de stubs `fundamentos` para compilar y probar sin libreria externa.
3. **Extract Class + Replace Conditional with Polymorphism**: division de `Transporte` en jerarquia (`TransportePersonas`, `TransporteMercancias`, `TransporteMercanciasPeligrosas`) para eliminar la logica `switch` de `Conductor`.
4. **Encapsulate Collection**: encapsulacion de transportes en `Conductor` con API controlada (`addTransporte`, `getTransportes`).
5. **Rename + mejoras de legibilidad**: `gestionTransportes` -> `GestionTransportes` (en refactor), nombres de metodos y uso de `Map` por DNI.
6. **Extract Factory**: creacion de `TransporteFactory` para centralizar la construccion de transportes en la GUI.
7. **Correccion funcional en GUI**: correccion del apellido mostrado para mejor conductor (`getApellido1` en vez de repetir nombre).
8. **Ampliacion de tests**: adaptacion de tests originales y nuevos tests para factory y gestion de conductores.

## 2. Metricas

> Nota: valores de WMC y CCog estan anotados en comentarios dentro del codigo.  
> CBO incluye clases de dominio y apoyo relevantes (estimacion manual orientativa).

### Proyecto legado (`transportes-legacy`)

| Clase | WMC | CCog | CBO (aprox.) | DIT | NOC |
|---|---:|---:|---:|---:|---:|
| `CategoriaTransporte` | 0 | 0 | 0 | 1 | 0 |
| `Transporte` | 8 | 3 | 2 | 1 | 0 |
| `Conductor` | 22 | 16 | 4 | 1 | 0 |
| `gestionTransportes` | 6 | 4 | 3 | 1 | 0 |
| `GestionTransportesGUI` | 17 | 45 | 9 | 1 | 0 |

### Proyecto refactorizado (`transportes-refactor`)

| Clase | WMC | CCog | CBO (aprox.) | DIT | NOC |
|---|---:|---:|---:|---:|---:|
| `CategoriaTransporte` | 0 | 0 | 0 | 1 | 0 |
| `Transporte` | 5 | 1 | 1 | 1 | 2 |
| `TransporteMercancias` | 4 | 1 | 2 | 2 | 1 |
| `TransporteMercanciasPeligrosas` | 2 | 0 | 1 | 3 | 0 |
| `TransportePersonas` | 5 | 2 | 1 | 2 | 0 |
| `Conductor` | 16 | 2 | 2 | 1 | 0 |
| `GestionTransportes` | 5 | 2 | 2 | 1 | 0 |
| `TransporteFactory` | 4 | 3 | 4 | 1 | 0 |
| `GestionTransportesGUI` | 19 | 42 | 9 | 1 | 0 |

## 3. Analisis de mejora

- Se reduce de forma clara la complejidad cognitiva de `Conductor` al mover reglas de negocio a clases especializadas de transporte.
- El acoplamiento se distribuye mejor: `Conductor` ya no depende de detalles de cada categoria.
- El coste estructural sube ligeramente en numero de clases (normal en refactorizacion por polimorfismo), pero mejora mantenibilidad y extensibilidad.
- La cobertura funcional de conductores/transportes se mantiene con tests adaptados y ampliados.

## 4. Comandos de verificacion

```bash
cd Practica6/transportes-legacy
mvn test

cd ../transportes-refactor
mvn test
mvn package
```

El jar ejecutable del proyecto refactorizado queda en:

- `Practica6/transportes-refactor/target/transportes-refactor-1.0-SNAPSHOT.jar`
