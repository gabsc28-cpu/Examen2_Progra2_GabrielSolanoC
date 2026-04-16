/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;
import entidades.Registro;
import entidades.Vehiculo;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabsc
 */

public class RegistroDAO {

    private final String ARCHIVO_ACTIVOS = "activos.txt";
    private final String ARCHIVO_HISTORIAL = "historial.txt";

    // =========================
    // GUARDAR INGRESO
    // =========================
    public void guardarIngreso(Registro registro) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ACTIVOS, true))) {

            String linea = registro.getVehiculo().getPlaca() + "," +
                           registro.getVehiculo().getTipo() + "," +
                           registro.getHoraEntrada();

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // OBTENER ACTIVOS
    // =========================
    public List<Registro> obtenerActivos() {
        List<Registro> lista = new ArrayList<>();

        File archivo = new File(ARCHIVO_ACTIVOS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                Vehiculo v = new Vehiculo(datos[0], datos[1]);
                LocalDateTime entrada = LocalDateTime.parse(datos[2]);

                lista.add(new Registro(v, entrada));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================
    // REGISTRAR SALIDA
    // =========================
    public void registrarSalida(Registro registroSalida) {

        List<Registro> activos = obtenerActivos();
        List<Registro> nuevosActivos = new ArrayList<>();

        for (Registro r : activos) {
            if (!r.getVehiculo().getPlaca().equals(registroSalida.getVehiculo().getPlaca())) {
                nuevosActivos.add(r);
            }
        }

        sobrescribirActivos(nuevosActivos);
        guardarEnHistorial(registroSalida);
    }

    // =========================
    // SOBRESCRIBIR ACTIVOS
    // =========================
    private void sobrescribirActivos(List<Registro> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ACTIVOS))) {

            for (Registro r : lista) {
                String linea = r.getVehiculo().getPlaca() + "," +
                               r.getVehiculo().getTipo() + "," +
                               r.getHoraEntrada();

                bw.write(linea);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // GUARDAR EN HISTORIAL
    // =========================
    private void guardarEnHistorial(Registro r) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_HISTORIAL, true))) {

            String linea = r.getVehiculo().getPlaca() + "," +
                           r.getVehiculo().getTipo() + "," +
                           r.getHoraEntrada() + "," +
                           r.getHoraSalida() + "," +
                           r.getMonto();

            bw.write(linea);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // OBTENER HISTORIAL
    // =========================
    public List<Registro> obtenerHistorial() {
        List<Registro> lista = new ArrayList<>();

        File archivo = new File(ARCHIVO_HISTORIAL);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                Vehiculo v = new Vehiculo(datos[0], datos[1]);
                LocalDateTime entrada = LocalDateTime.parse(datos[2]);
                LocalDateTime salida = LocalDateTime.parse(datos[3]);
                double monto = Double.parseDouble(datos[4]);

                lista.add(new Registro(v, entrada, salida, monto));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================
    // ELIMINAR HISTORIAL
    // =========================
    public void eliminarHistorial() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_HISTORIAL))) {
            // sobrescribe vacío
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
