/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package garage;

public class Trabajo {
    
    protected int idTrabajo;
    protected String descripcion;
    protected int numHoras;//cantidad de horas trabajadas
    protected boolean finalizado;

    public Trabajo(int idTrabajo, String descripcion) {
        this.idTrabajo = idTrabajo;
        this.descripcion = descripcion;
        this.numHoras = 0; // Inicia en 0 como pide el caso
        this.finalizado = false; // En proceso inicia como NO FINALIZADO
    }

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }
    //Para variables booleanas normalmente se usa is en lugar de get.
    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public void aumentarHoras(int horas) {
    if (finalizado) {
        System.out.println("Error: El trabajo ya está finalizado.");//no permite modificar las horas.
    } else if (horas <= 0) {//No permite sumar horas negativas o cero.
        System.out.println("Error: Las horas deben ser mayores que cero.");
    } else {
        numHoras += horas;
        System.out.println("-> Se aumentaron " + horas +
                           " horas al trabajo ID " + idTrabajo);
    }
}
    public void finalizarTrabajo() {
        if (finalizado) {
            System.out.println("El trabajo ya estaba finalizado.");
            return;
        }
        finalizado = true;
        System.out.println("Trabajo finalizado correctamente.");
    }

    public double calcularCostoFijo() {
        return numHoras * 30.0;//Se usa 30.0 (con decimal) para que el resultado sea de tipo double
    }
}