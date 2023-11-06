import java.sql.*

// Modelo
@Suppress("SpellCheckingInspection")

class Modelo {
    /**
     * Modelo de datos para un ciudadano.
     *
     * @property nombre El nombre del ciudadano.
     * @property apellidos Los apellidos del ciudadano.
     * @property ciudad La ciudad del ciudadano.
     * @property dni El DNI del ciudadano.
     * @property codigoPostal El código postal del ciudadano.
     */
    data class Ciudadano(
        val nombre: String,
        val apellidos: String,
        val ciudad: String,
        val dni: String,
        val codigoPostal: String
    )
    private val url = "jdbc:mysql://localhost:3306/xxxxx"
    private val user = "ponelusuario"
    private val password = "ponlacontraseña"

    private var connection: Connection? = null
    private var statement: Statement? = null

    init {
        connection = DriverManager.getConnection(url, user, password)
        statement = connection?.createStatement()

        val sql = """
            CREATE TABLE IF NOT EXISTS ciudadano (
                nombre VARCHAR(255),
                apellidos VARCHAR(255),
                ciudad VARCHAR(255),
                dni VARCHAR(255) PRIMARY KEY,
                codigoPostal VARCHAR(255)
            )
        """
        statement?.executeUpdate(sql)
    }

    /**
     * Agrega un nuevo ciudadano a la base de datos.
     *
     * @param ciudadano El ciudadano a agregar.
     */
    fun agregarCiudadano(ciudadano: Ciudadano) {
        try {
            val sql = """
            INSERT INTO Ciudadano (nombre, apellidos, ciudad, dni, codigoPostal)
            VALUES (?, ?, ?, ?, ?)
        """
            val preparedStatement = connection?.prepareStatement(sql)
            preparedStatement?.setString(1, ciudadano.nombre)
            preparedStatement?.setString(2, ciudadano.apellidos)
            preparedStatement?.setString(3, ciudadano.ciudad)
            preparedStatement?.setString(4, ciudadano.dni)
            preparedStatement?.setString(5, ciudadano.codigoPostal)
            preparedStatement?.executeUpdate()
        } catch (e: SQLIntegrityConstraintViolationException) {
            println("Error: Ya existe un ciudadano con el DNI ${ciudadano.dni}.")
        }
    }

    /**
     * Borra un ciudadano de la base de datos.
     *
     * @param dni El DNI del ciudadano a borrar.
     */
    fun borrarCiudadano(dni: String) {
        try {
            val sql = "DELETE FROM Ciudadano WHERE dni = ?"
            val preparedStatement = connection?.prepareStatement(sql)
            preparedStatement?.setString(1, dni)
            preparedStatement?.executeUpdate()
        } catch (e: SQLIntegrityConstraintViolationException) {
            println("Error: No existe un ciudadano con el DNI $dni.")
        }
    }

    /**
     * Modifica los datos de un ciudadano en la base de datos.
     *
     * @param dni El DNI del ciudadano a modificar.
     * @param ciudadano Los nuevos datos del ciudadano.
     */
    fun modificarCiudadano(dni: String, ciudadano: Ciudadano) {
        try {
            val sql = """
            UPDATE ciudadano
            SET nombre = ?, apellidos = ?, ciudad = ?, codigoPostal = ?
            WHERE dni = ?
        """
            val preparedStatement = connection?.prepareStatement(sql)
            preparedStatement?.setString(1, ciudadano.nombre)
            preparedStatement?.setString(2, ciudadano.apellidos)
            preparedStatement?.setString(3, ciudadano.ciudad)
            preparedStatement?.setString(4, ciudadano.codigoPostal)
            preparedStatement?.setString(5, dni)
            preparedStatement?.executeUpdate()
        } catch (e: SQLIntegrityConstraintViolationException) {
            println("Error: No existe un ciudadano con el DNI $dni.")
        }
    }

    /**
     * Sirve para encontrar los datos de los ciudadanos en la base de datos
     */
    fun obtenerCiudadanos(): List<Ciudadano> {
        val sql = "SELECT * FROM Ciudadano"
        val resultSet = statement?.executeQuery(sql)
        val ciudadanos = mutableListOf<Ciudadano>()
        while (resultSet?.next() == true) {
            val nombre = resultSet.getString("nombre")
            val apellidos = resultSet.getString("apellidos")
            val ciudad = resultSet.getString("ciudad")
            val dni = resultSet.getString("dni")
            val codigoPostal = resultSet.getString("codigoPostal")
            ciudadanos.add(Ciudadano(nombre, apellidos, ciudad, dni, codigoPostal))
        }
        return ciudadanos
    }
}
