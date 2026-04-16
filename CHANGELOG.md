# CHANGELOG

## v1.0

* Creación de la estructura base del proyecto.
* Implementación de las clases de entidades:

  * Vehiculo
  * Registro
* Definición de atributos y constructores necesarios.
* Preparación del modelo de datos para el manejo de vehículos y registros.

---

## v1.1

* Implementación de la capa de lógica de negocio (ParqueoService).

  * Registro de ingreso de vehículos.
  * Registro de salida de vehículos.
  * Cálculo de monto por tiempo (tarifa por hora).
  * Validación de datos obligatorios.
  * Control de unicidad de placas activas.

* Implementación de la capa de presentación (Swing):

  * MainFrame con navegación por pestañas.
  * PanelIngreso para registrar vehículos.
  * PanelSalida para gestionar salidas.
  * PanelHistorial para visualizar registros.

* Integración entre interfaz y lógica respetando la arquitectura por capas.

---

## v1.2

* Implementación de clase utilitaria:

  * UtilidadesTablas para manejo de JTable.

* Mejoras en la interfaz:

  * Tablas no editables.
  * Ajuste automático de columnas.
  * Mensajes en pantalla mediante JLabel (sin uso de JOptionPane).

* Correcciones de comportamiento:

  * Actualización dinámica de tablas al cambiar de pestañas.
  * Refresco correcto de datos en salida e historial.

* Validaciones adicionales:

  * Formato de placa (XXX-111) mediante expresión regular.
  * Normalización de datos (uso de mayúsculas en placas).

* Mejoras generales en experiencia de usuario.
