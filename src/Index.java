import java.util.Scanner;

public class Index {

    private static int indiceTickets = 0;
    private static String[] tickets = new String[100];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Usuarios y contraseñas
        String[] usuarios = {"Leticia.shuan", "Katherin.romero", "Yamyle.ayala", "Admin.sistemas"};
        String[] contrasenas = {"123456", "123456", "123456", "Admin123"};

        // Inicio del proceso
        System.out.println("Bienvenido al Sistema de Incidencias");

        // Solicitar usuario y contraseña
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        // Validar usuario y contraseña
        int indiceUsuario = -1;
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i].equals(usuario) && contrasenas[i].equals(contrasena)) {
                indiceUsuario = i;
                break;
            }
        }

        if (indiceUsuario != -1) {
            System.out.println("¡Bienvenido, " + usuario + "!");
            System.out.println("Tipo de usuario: " + (indiceUsuario == usuarios.length - 1 ? "ADMINISTRADOR" : "NO ADMINISTRADOR"));

            // Proceso según el tipo de usuario
            if (indiceUsuario == usuarios.length - 1) {
                // Administrador
                System.out.print("Ingrese el nivel de prioridad de los tickets que desea mostrar en el informe: ");
                String nivelPrioridad = scanner.next();
                mostrarInforme(nivelPrioridad);
            } else {
                // No Administrador
                System.out.println("1. Registrar ticket");
                System.out.println("2. Consultar ticket");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                if (opcion == 1) {
                    // Registrar ticket
                    scanner.nextLine(); // Consumir el salto de línea pendiente
                    System.out.print("Ingrese resumen del incidente: ");
                    String resumen = scanner.nextLine();
                    System.out.print("Ingrese descripción del incidente: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Seleccione el nivel de prioridad (Alto, Medio, Bajo): ");
                    String nivelPrioridad = scanner.next();
                    System.out.print("Ingrese número telefónico del usuario: ");
                    String numeroTelefono = scanner.next();
                    System.out.print("Ingrese correo electrónico del usuario: ");
                    String correoElectronico = scanner.next();

                    // Generar el ticket y guardarlo en el array
                    String nuevoTicket = generarTicket(resumen, descripcion, nivelPrioridad, numeroTelefono, correoElectronico);
                    tickets[indiceTickets] = nuevoTicket;
                    indiceTickets++;

                    System.out.println("Resumen del ticket generado: " + nuevoTicket);
                } else if (opcion == 2) {
                    // Consultar ticket
                    System.out.print("Ingrese el número de ticket: ");
                    int numeroTicket = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea pendiente
                    consultarTicket(numeroTicket);
                } else {
                    System.out.println("Opción no válida.");
                }
            }
        } else {
            System.out.println("No existe usuario en la empresa o la contraseña es incorrecta.");
        }

        // Fin del proceso
        System.out.println("Fin del Sistema de Incidencias");
    }

    // Método para mostrar un informe de tickets según el nivel de prioridad
    private static void mostrarInforme(String nivelPrioridad) {
        System.out.println("Informe de tickets con prioridad " + nivelPrioridad + ":");
        for (String ticket : tickets) {
            if (ticket != null && obtenerNivelPrioridad(ticket).equals(nivelPrioridad)) {
                System.out.println(ticket);
            }
        }
    }

    // Método para consultar un ticket por su número
    private static void consultarTicket(int numeroTicket) {
        if (numeroTicket > 0 && numeroTicket <= indiceTickets && tickets[numeroTicket - 1] != null) {
            System.out.println("Datos del ticket " + numeroTicket + ": " + tickets[numeroTicket - 1]);
        } else {
            System.out.println("El número de ticket ingresado no es válido.");
        }
    }

    // Método para generar un nuevo ticket en formato secuencial
    private static String generarTicket(String resumen, String descripcion, String nivelPrioridad, String numeroTelefono, String correoElectronico) {
        String formatoSecuencial = "%03d";
        String numeroSecuencial = String.format(formatoSecuencial, indiceTickets + 1);
        return numeroSecuencial + " - Resumen: " + resumen + ", Descripción: " + descripcion + ", Prioridad: " + nivelPrioridad +
                ", Teléfono: " + numeroTelefono + ", Correo: " + correoElectronico;
    }

    // Método para obtener el nivel de prioridad de un ticket
    private static String obtenerNivelPrioridad(String ticket) {
        // Se asume que el nivel de prioridad está después de "Prioridad: "
        return ticket.split("Prioridad: ")[1].split(",")[0];
    }
}
