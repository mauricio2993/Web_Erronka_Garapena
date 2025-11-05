import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaJDBC {
    public static void main(String[] args) {
        try {
            // Carga explícitamente el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conecta con la base de datos
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/garapena_db", "root", ""
            );

            // Crea un Statement para ejecutar SQL
            Statement statement = connection.createStatement();

            // Ejecuta la consulta
            ResultSet resultSet = statement.executeQuery("SELECT * FROM inscripciones");

            // Lista para guardar las inscripciones
            ArrayList<String[]> misInscripciones = new ArrayList<>();

            // Contadores por cada evento
            int contIA = 0;
            int contDB = 0;
            int contSOS = 0;
            int contDIG = 0;

            String mievento;

            // Recorre el ResultSet fila por fila
            while (resultSet.next()) {
                mievento = resultSet.getString("evento");

                // Incrementar contadores según el evento
                if (mievento.equals("Inteligencia Artificial")) {
                    contIA++;
                } else if (mievento.equals("Base de Datos")) {
                    contDB++;
                } else if (mievento.equals("Sostenibilidad")) {
                    contSOS++;
                } else if (mievento.equals("Digitalizacion")) {
                    contDIG++;
                }

                // Guardar los datos en el ArrayList
                String[] fila = {
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getString("evento")
                };
                misInscripciones.add(fila);

                // Mostrar los datos de cada fila
                System.out.println(
                    "ID: " + resultSet.getInt("id") +
                    " | Nombre: " + resultSet.getString("nombre") +
                    " | Apellido: " + resultSet.getString("apellido") +
                    " | Email: " + resultSet.getString("email") +
                    " | Evento: " + resultSet.getString("evento")
                );
            }

            // ---------- CÁLCULOS ----------
            System.out.println("\n📊 RESULTADOS DE INSCRIPCIONES\n");

            // 1️⃣ Total de personas inscritas
            System.out.println("Total de personas inscritas: " + misInscripciones.size());

            // 2️⃣ Número de personas por evento
            System.out.println("\nPersonas inscritas por evento:");
            System.out.println("- Inteligencia Artificial: " + contIA);
            System.out.println("- Base de Datos: " + contDB);
            System.out.println("- Sostenibilidad: " + contSOS);
            System.out.println("- Digitalizacion: " + contDIG);

            // 3️⃣ Listado de personas por evento
            System.out.println("\nListado de personas por evento:");

            System.out.println("\n[Inteligencia Artificial]");
            for (String[] fila : misInscripciones) {
                if (fila[2].equals("Inteligencia Artificial")) {
                    System.out.println("  - " + fila[0] + " " + fila[1]);
                }
            }

            System.out.println("\n[Base de Datos]");
            for (String[] fila : misInscripciones) {
                if (fila[2].equals("Base de Datos")) {
                    System.out.println("  - " + fila[0] + " " + fila[1]);
                }
            }

            System.out.println("\n[Sostenibilidad]");
            for (String[] fila : misInscripciones) {
                if (fila[2].equals("Sostenibilidad")) {
                    System.out.println("  - " + fila[0] + " " + fila[1]);
                }
            }

            System.out.println("\n[Digitalizacion]");
            for (String[] fila : misInscripciones) {
                if (fila[2].equals("Digitalizacion")) {
                    System.out.println("  - " + fila[0] + " " + fila[1]);
                }
            }

            // Cierra la conexión
            connection.close();
            System.out.println("\n✅ Conexión cerrada correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Error al conectar o consultar la base de datos:");
            e.printStackTrace();
        }
    }
}