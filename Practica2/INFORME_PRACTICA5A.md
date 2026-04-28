# Informe de Pruebas - Practica 5A

## 1. Fases realizadas

1. Pruebas unitarias de dominio:
- Clase `Seguro` (metodo `precio()`).
- Clase `Cliente` (metodo `totalSeguros()`).

2. Pruebas de integracion:
- Clase `VistaAgente` integrada con negocio (`GestionSeguros`) y persistencia H2 (`ClientesDAO`, `SegurosDAO`).
- Cobertura funcional del caso de uso de consulta de cliente.

## 2. Tecnicas aplicadas

- Caja negra:
  - Particion equivalente.
  - Analisis de valores limite (AVL).
- Caja blanca:
  - Cobertura de sentencias.
  - Cobertura de decision/condicion en las ramas de calculo de precio, descuento, cliente existente/no existente y lista de seguros.

## 3. Casos de prueba implementados

### 3.1 Unitarias (`SegurosCommon`)

- `SeguroTest`:
  - Fecha nula.
  - Cobertura nula.
  - Fecha futura.
  - Potencia `< 90`, `= 90`, `= 110`, `> 110`.
  - Coberturas `TERCEROS`, `TODO_RIESGO`, `TERCEROS_LUNAS`.
  - Descuento durante primer anho y en frontera de aniversario.

- `ClienteTest`:
  - Cliente sin seguros.
  - Cliente con seguros sin minusvalia.
  - Cliente con minusvalia (aplicacion del descuento del 25% a cada seguro).

### 3.2 Integracion (`SegurosGUI`)

- `VistaAgenteIT`:
  - Consulta de cliente valido (`11111111A`): nombre, total y contenido de lista de seguros.
  - Consulta de cliente inexistente: mensaje de DNI invalido y limpieza de campos.
  - Consulta de cliente sin seguros (`33333333A`): total `0.0` y lista vacia.

## 4. Configuracion Maven

- Se anhadio `maven-failsafe-plugin` en el `pom.xml` padre para ejecutar clases `*IT` en la fase `verify`.
- Se anhadieron dependencias de prueba:
  - `junit:junit`.
  - `org.assertj:assertj-swing-junit`.
- Se configuraron argumentos `--add-opens` para compatibilidad de AssertJ Swing con Java 17.

## 5. Ejecucion y resultados

Comando ejecutado:

```bash
mvn verify
```

Resultado:
- Build final: **SUCCESS**.
- Unitarias (`SeguroTest`, `ClienteTest`): **OK**.
- Integracion (`VistaAgenteIT`): **OK**.
