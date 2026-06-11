/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package garage;

public class Revision extends Trabajo {

    public Revision(int idTrabajo, String descripcion) {
        super(idTrabajo, descripcion);
    }

    public double calcularPrecioRevision() {
        return calcularCostoFijo() + 20.0;
    }

    public void mostrarPlazo() {
        System.out.println("Plazo máximo de entrega: 7 días.");
    }
}
