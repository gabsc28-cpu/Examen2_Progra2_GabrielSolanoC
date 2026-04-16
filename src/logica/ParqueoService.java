/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import datos.RegistroDAO;
import entidades.Registro;
import entidades.Vehiculo;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author gabsc
 */

public class ParqueoService {

    private final RegistroDAO dao;
    private final double TARIFA_POR_HORA = 500;

    public ParqueoService() {
        this.dao = new RegistroDAO();
    }

    // =========================
    // REGISTRAR INGRESO
    // =========================
    public void registrarIngreso(String placa, String tipo) {

    if (placa == null || placa.trim().isEmpty()) {
        throw new IllegalArgumentException("La placa es obligatoria");
    }

    // Normalizar a mayúscula
    placa = placa.toUpperCase();

    // Validar formato XXX-111
    if (!placa.matches("^[A-Z]{3}-\\d{3}$")) {
        throw new IllegalArgumentException("Formato de placa inválido (XXX-111)");
    }

    if (tipo == null || tipo.trim().isEmpty()) {
        throw new IllegalArgumentException("El tipo es obligatorio");
    }

    List<Registro> activos = dao.obtenerActivos();

    for (Registro r : activos) {
        if (r.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
            throw new IllegalArgumentException("El vehículo ya está dentro del parqueo");
        }
    }

    Vehiculo vehiculo = new Vehiculo(placa, tipo);
    Registro registro = new Registro(vehiculo, LocalDateTime.now());

    dao.guardarIngreso(registro);
    }
    // =========================
    // REGISTRAR SALIDA
    // =========================
    public void registrarSalida(String placa) {

        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa es obligatoria");
        }

        List<Registro> activos = dao.obtenerActivos();
        Registro encontrado = null;

        for (Registro r : activos) {
            if (r.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                encontrado = r;
                break;
            }
        }

        if (encontrado == null) {
            throw new IllegalArgumentException("Vehículo no encontrado en el parqueo");
        }

        LocalDateTime salida = LocalDateTime.now();
        double monto = calcularMonto(encontrado.getHoraEntrada(), salida);

        encontrado.setHoraSalida(salida);
        encontrado.setMonto(monto);

        dao.registrarSalida(encontrado);
    }

    // =========================
    // OBTENER ACTIVOS
    // =========================
    public List<Registro> obtenerActivos() {
        return dao.obtenerActivos();
    }

    // =========================
    // OBTENER HISTORIAL
    // =========================
    public List<Registro> obtenerHistorial() {
        return dao.obtenerHistorial();
    }

    // =========================
    // ELIMINAR HISTORIAL
    // =========================
    public void eliminarHistorial() {
        dao.eliminarHistorial();
    }

    // =========================
    // CALCULAR MONTO
    // =========================
    public double calcularMonto(LocalDateTime entrada, LocalDateTime salida) {

        long minutos = Duration.between(entrada, salida).toMinutes();

        long horas = (long) Math.ceil(minutos / 60.0);

        if (horas == 0) horas = 1;

        return horas * TARIFA_POR_HORA;
    }
}