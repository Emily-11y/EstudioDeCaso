/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package garage;

public class Reparacion extends Trabajo {
    protected double precioMaterial;

    public Reparacion(int idTrabajo, String descripcion) {
        super(idTrabajo, descripcion);
        this.precioMaterial = 0.0; 
    }

    public double getPrecioMaterial() {
        return precioMaterial;
    }

    public void setPrecioMaterial(double precioMaterial) {
        this.precioMaterial = precioMaterial;
    }

    public void aumentarPrecioMaterial(double costo) {
        if (!finalizado) {
            precioMaterial += costo;
            System.out.println("-> Se añadieron $" + costo + " en materiales al trabajo ID " + idTrabajo);
        } else {
            System.out.println("Error: El trabajo está finalizado. No se puede cambiar el precio del material.");
        }
    }
    
}

