/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package garage;
import java.util.ArrayList;
import java.util.Scanner;
//GRUPO N4  Emily Rivera,Ismael Sarango, Dario Minga

public class Ejecutor {
    
    private static ArrayList<Trabajo> listaTrabajos = new ArrayList<>();
    private static int idSiguiente = 0;

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n================ MENU GESTIÓN ================");
            System.out.println("1. Registrar Trabajo");
            System.out.println("2. Aumenta Horas");
            System.out.println("3. Aumenta Coste de Piezas/Materiales");
            System.out.println("4. Finaliza Trabajo");
            System.out.println("5. Muestra Trabajo");
            System.out.println("6. Consulta Plazo");
            System.out.println("7. Muestra Todos los Trabajos");
            System.out.println("8. RETO 5: El Supervisor de Horas (Avance Semanal)");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: "); 
            
            int opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    registrarNuevoTrabajo(entrada);
                    break;
                case 2:
                    incrementarHorasTrabajo(entrada);
                    break;
                case 3:
                    incrementarMaterialesTrabajo(entrada);
                    break;
                case 4:
                    marcarTrabajoFinalizado(entrada);
                    break;
                case 5:
                    consultarTrabajoIndividual(entrada);
                    break;
                case 6:
                    consultarPlazoEntrega(entrada);
                    break;
                case 7:
                    imprimirCatalogoTrabajos();
                    break;
                case 8:
                    ejecutarRetoSupervisorHoras();
                    break;
                case 9:
                    continuar = false;
                    System.out.println("Saliendo del Sistema Gestión...");
                    break;
                default:
                    System.out.println("Opción incorrecta. Intente de nuevo.");
            }
        }
        entrada.close();
    }

    private static void registrarNuevoTrabajo(Scanner entrada) {
        System.out.println("\n--- Tipo de Trabajo ---");
        System.out.println("1. Reparación Mecánica");
        System.out.println("2. Reparación de Chapas y Pintura");
        System.out.println("3. Revisión");
        System.out.print("Seleccione: ");
        int tipo = entrada.nextInt();
        entrada.nextLine();

        System.out.print("Ingrese descripción del trabajo: ");
        String desc = entrada.nextLine();

        if (tipo == 1) {
            listaTrabajos.add(new ReparacionMecanica(idSiguiente, desc));
            System.out.println("Mecánica Registrada con ID: " + idSiguiente);
            idSiguiente++;
        } else if (tipo == 2) {
            listaTrabajos.add(new ReparacionChapasPintura(idSiguiente, desc));
            System.out.println("Chapas y Pintura Registrada con ID: " + idSiguiente);
            idSiguiente++;
        } else if (tipo == 3) {
            listaTrabajos.add(new Revision(idSiguiente, desc));
            System.out.println("Revisión Registrada con ID: " + idSiguiente);
            idSiguiente++;
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void incrementarHorasTrabajo(Scanner entrada) {
        System.out.print("Ingrese ID del trabajo: ");
        int id = entrada.nextInt();
        System.out.print("Cantidad de horas a aumentar: ");
        int horas = entrada.nextInt();

        Trabajo t = encontrarTrabajoPorId(id);
        if (t != null) {
            t.aumentarHoras(horas);
        } else {
            System.out.println("Trabajo no localizado.");
        }
    }
    
    private static Trabajo encontrarTrabajoPorId(int id) {
        for (int i = 0; i < listaTrabajos.size(); i++) {
            if (listaTrabajos.get(i).getIdTrabajo() == id) {
                return listaTrabajos.get(i);
            }
        }
        return null;
    }

    private static void incrementarMaterialesTrabajo(Scanner entrada) {
        System.out.print("Ingrese ID del trabajo: ");
        int id = entrada.nextInt();
        System.out.print("Costo de piezas/materiales a sumar: ");
        double costo = entrada.nextDouble();
        Trabajo t = encontrarTrabajoPorId(id);
        if (t != null) {
            // Evitando polimorfismo dinámico: Validación explícita usando herencia base
            if (t instanceof ReparacionMecanica) {
                ((ReparacionMecanica) t).aumentarPrecioMaterial(costo);
            } else if (t instanceof ReparacionChapasPintura) {
                ((ReparacionChapasPintura) t).aumentarPrecioMaterial(costo);
            } else {
                System.out.println("Error: Las revisiones no admiten costes de materiales.");
            }
        } else {
            System.out.println("Trabajo no localizado.");
        }
    }

    private static void marcarTrabajoFinalizado(Scanner entrada) {
        System.out.print("Ingrese ID del trabajo a finalizar: ");
        int id = entrada.nextInt();
        Trabajo t = encontrarTrabajoPorId(id);
        if (t != null) {
            t.finalizarTrabajo();
            System.out.println("El trabajo con ID " + id + " ahora se encuentra FINALIZADO.");
        } else {
            System.out.println("Trabajo no localizado.");
        }
    }
    

    private static void consultarTrabajoIndividual(Scanner entrada) {
        System.out.print("Ingrese ID del trabajo: ");
        int id = entrada.nextInt();
        Trabajo t = encontrarTrabajoPorId(id);
        if (t != null) {
            desplegarInformacionDetallada(t);
        } else {
            System.out.println("Trabajo no localizado.");
        }
    }
    
    private static void desplegarInformacionDetallada(Trabajo t) {
        double totalCobro = 0;
        String estadoString = t.isFinalizado() ? "FINALIZADO (Valores Fijos)" : "EN PROCESO (Modificable)";

        if (t instanceof ReparacionMecanica) {
            ReparacionMecanica rm = (ReparacionMecanica) t;
            totalCobro = rm.calcularPrecioMecanica();
            System.out.println("ID: " + rm.getIdTrabajo() + " | Tipo: Reparación Mecánica | Estado: " + estadoString);
            System.out.println("Descripción: " + rm.getDescripcion());
            System.out.println("Horas Realizadas: " + rm.getNumHoras() + " hrs | Costo Materiales: $" + rm.getPrecioMaterial());
            System.out.println("PRECIO FINAL: $" + totalCobro);

        } else if (t instanceof ReparacionChapasPintura) {
            ReparacionChapasPintura rcp = (ReparacionChapasPintura) t;
            totalCobro = rcp.calcularPrecioChapasPintura();
            System.out.println("ID: " + rcp.getIdTrabajo() + " | Tipo: Reparación de Chapas y Pintura | Estado: " + estadoString);
            System.out.println("Descripción: " + rcp.getDescripcion());
            System.out.println("Horas Realizadas: " + rcp.getNumHoras() + " hrs | Costo Materiales: $" + rcp.getPrecioMaterial());
            System.out.println("PRECIO FINAL: $" + totalCobro);

        } else if (t instanceof Revision) {
            Revision rev = (Revision) t;
            totalCobro = rev.calcularPrecioRevision();
            System.out.println("ID: " + rev.getIdTrabajo() + " | Tipo: Revisión | Estado: " + estadoString);
            System.out.println("Descripción: " + rev.getDescripcion());
            System.out.println("Horas Realizadas: " + rev.getNumHoras() + " hrs | Costo Adicional Fijo: $20.0");
            System.out.println("PRECIO FINAL: $" + totalCobro);
        }
    }

    private static void consultarPlazoEntrega(Scanner entrada) {
        System.out.print("Ingrese ID del trabajo: ");
        int id = entrada.nextInt();
        Trabajo t = encontrarTrabajoPorId(id);
        if (t != null) {
            if (t instanceof ReparacionMecanica) {
                System.out.print("Tipo: Reparación Mecánica | ");
                ((ReparacionMecanica) t).mostrarPlazo();
            } else if (t instanceof ReparacionChapasPintura) {
                System.out.print("Tipo: Chapas y Pintura | ");
                ((ReparacionChapasPintura) t).mostrarPlazo();
            } else if (t instanceof Revision) {
                System.out.print("Tipo: Revisión | ");
                ((Revision) t).mostrarPlazo();
            }
        } else {
            System.out.println("Trabajo no localizado.");
        }
    }

    private static void imprimirCatalogoTrabajos() {
        if (listaTrabajos.isEmpty()) {
            System.out.println("No existen registros cargados en el sistema.");
            return;
        }
        System.out.println("\n================ LISTADO COMPLETO DETALLADO ================");
        for (int i = 0; i < listaTrabajos.size(); i++) {
            desplegarInformacionDetallada(listaTrabajos.get(i));
            System.out.println("------------------------------------------------------------");
        }
    }

    // =========================================================================
    // RETO 5: Misión - "El Supervisor de Horas"
    // Objetivo técnico: Estructura repetitiva para avanzar la semana laboral
    // =========================================================================
    private static void ejecutarRetoSupervisorHoras() {
        System.out.println("\n>>> INICIANDO RETO 5: 'El Supervisor de Horas' <<<");
        System.out.println("Simulando paso del tiempo laboral sobre trabajos en proceso...");
        
        boolean actualizacionRealizada = false;

        // Bucle "for" para iterar ordenadamente sobre la lista general de trabajos
        for (int i = 0; i < listaTrabajos.size(); i++) {
            Trabajo temp = listaTrabajos.get(i);

            // Filtro sistemático: Solo aumenta horas a los trabajos que NO estén finalizados
            if (!temp.isFinalizado()) {
                temp.aumentarHoras(8); // Se agregan 8 horas reglamentarias
                actualizacionRealizada = true;
            }
        }
        if (actualizacionRealizada) {
            System.out.println("¡Misión completada con éxito! Las horas semanales fueron actualizadas.");
        } else {
            System.out.println("No se detectaron trabajos en estado activo para actualizar.");
        }
    }


    
}
