<?php
// Colocar este archivo, por ejemplo en C:\xampp\htdocs\miweb
// luego en el formulario poner action="http://localhost/miweb/procesar.php"
// Datos de conexión
$servername = "localhost";
$username   = "root";
$password   = "";
$dbname     = "eventos";

// Recoger dato del formulario, podría ser un get ($_GET)
if (isset($_POST["nombre"])&& isset($_POST["apellido"])) {
    $nombre = $_POST["nombre"];
	$apellido = $_POST["apellido"];
} else {
    $nombre = "";
	$apellido = "";
}


// Conectar a MySQL
$conn = new mysqli($servername, $username, $password, $dbname);

// Comprobar conexión
if ($conn->connect_error) {
    // Finaliza la ejecución del script y muestra el mensaje indicado al usuario
	// PHP cierra la conexión al finalizar el script, $conn se destruye automáticamente
	die("Error de conexión: " . $conn->connect_error);
}

// Evitar insertar vacío
if ($nombre === "" || $apellido === "") {
    // Finaliza la ejecución del script y muestra el mensaje indicado al usuario
	die("El nombre/apellido no puede estar vacío");
}

// Consulta SQL para insertar
$sql = "INSERT INTO inscripciones (nombre, apellido) VALUES ('$nombre', '$apellido')";

// Ejecutar
if ($conn->query($sql) === TRUE) {
    echo "<h2>¡Registro correcto!</h2>";
    echo "<p>Nombre insertado: " . $nombre . "</p>";
	echo "<p>Apellido insertado: " . $apellido . "</p>";
    echo '<p><a href="javascript:history.back()">Volver</a></p>';
} else {
    echo "Error: " . $conn->error;
}

// Cerrar conexión
$conn->close();
?>
