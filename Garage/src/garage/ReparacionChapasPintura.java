/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package garage;

public class ReparacionChapasPintura extends Reparacion {

    public ReparacionChapasPintura(int idTrabajo, String descripcion) {
        super(idTrabajo, descripcion);
    }

    public double calcularPrecioChapasPintura() {
        return calcularCostoFijo() + (precioMaterial * 1.3);
    }

    public void mostrarPlazo() {
        System.out.println("Plazo máximo de entrega: 21 días.");
    }
}
