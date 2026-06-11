/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package garage;

public class ReparacionMecanica extends Reparacion {

    public ReparacionMecanica(int idTrabajo, String descripcion) {
        super(idTrabajo, descripcion);
    }

    public double calcularPrecioMecanica() {
        return calcularCostoFijo() + (precioMaterial * 1.1);
    }

    public void mostrarPlazo() {
        System.out.println("Plazo máximo de entrega: 14 días.");
    }
}
