# Sistema de Parqueo Público

## Descripción

Este proyecto consiste en el desarrollo de una aplicación de escritorio en Java para la gestión de un parqueo público. El sistema permite registrar el ingreso y salida de vehículos, así como visualizar el estado actual del parqueo y el historial de registros.

El desarrollo se realizó utilizando una arquitectura por capas, garantizando una correcta separación de responsabilidades entre la interfaz gráfica, la lógica de negocio, el acceso a datos y las entidades.

---

## Tecnologías utilizadas

* Lenguaje: Java
* Interfaz gráfica: Swing (JFrame, JTable)
* IDE: NetBeans
* Sistema de construcción: Apache Ant
* Persistencia: Archivos de texto (.txt)

---

## Funcionalidades principales

### Registro de ingreso

* Registro de vehículos mediante placa y tipo (carro o moto).
* Asignación automática de la hora de entrada.
* Validación de datos obligatorios.
* Restricción de unicidad de vehículos activos.

### Registro de salida

* Selección de vehículos actualmente en el parqueo.
* Registro automático de la hora de salida.
* Cálculo del monto a pagar según el tiempo de permanencia.

### Visualización

* Tabla de vehículos activos dentro del parqueo.
* Tabla de historial de vehículos registrados.

### Gestión de historial

* Eliminación de registros históricos.

---

## Reglas de negocio implementadas

* No se permite el ingreso de vehículos con placas duplicadas activas.
* El cálculo del cobro se realiza por hora o fracción.
* Validación de campos obligatorios.
* Validación de formato de placa (XXX-111).
* Separación estricta entre capas según arquitectura definida.

---

## Arquitectura del sistema

El sistema sigue una arquitectura por capas:

* **Presentación**

  * Manejo de la interfaz gráfica (Swing).
  * Clases: MainFrame, PanelIngreso, PanelSalida, PanelHistorial, App.

* **Lógica de negocio**

  * Validaciones, reglas del sistema y cálculos.
  * Clase: ParqueoService.

* **Acceso a datos**

  * Lectura y escritura de archivos.
  * Clase: RegistroDAO.

* **Entidades**

  * Modelos de datos del sistema.
  * Clases: Vehiculo, Registro.

* **Utilidades**

  * Funciones auxiliares para la interfaz.
  * Clase: UtilidadesTablas.

---

## Estructura del proyecto

```
cr.ac.parqueo
│
├── presentacion
│   ├── App.java
│   ├── MainFrame.java
│   ├── PanelIngreso.java
│   ├── PanelSalida.java
│   └── PanelHistorial.java
│
├── logica
│   └── ParqueoService.java
│
├── datos
│   └── RegistroDAO.java
│
├── entidades
│   ├── Vehiculo.java
│   └── Registro.java
│
└── utilidades
    └── UtilidadesTablas.java
```

---

## Persistencia de datos

El sistema utiliza archivos de texto para almacenar la información:

* `activos.txt`: vehículos actualmente en el parqueo.
* `historial.txt`: registros históricos de vehículos.

Los datos se almacenan en formato CSV para facilitar su lectura y escritura.

---

## Restricciones del proyecto

* No se utiliza salida por consola (`System.out`).
* No se utilizan ventanas emergentes (`JOptionPane`).
* La capa de presentación no accede directamente a la capa de datos.
* Toda la lógica de negocio se implementa fuera de la interfaz gráfica.

---

## Ejecución del proyecto

1. Abrir el proyecto en NetBeans.
2. Compilar el proyecto utilizando Apache Ant.
3. Ejecutar la clase `App` ubicada en el paquete `presentacion`.

---

## Autor

Gabriel David Solano Chaves
