// Vista
@Suppress("SpellCheckingInspection", "MemberVisibilityCanBePrivate")
/**
 * Clase para la interfaz de usuario.
 *
 * @property controller El controlador que gestiona la lógica de la aplicación.
 */
class Vista(private val controller: Controlador) {
    /**
     * Inicia la interfaz de usuario.
     */
    fun iniciar() {
        var opcion: String
        do { // Establezco menú según modelo CRUD
            println("\nMenú:")
            println("1. Añadir ciudadano") // Escribir
            println("2. Ver ciudadanos") // Ver
            println("3. Borrar ciudadano") // Eliminar
            println("4. Modificar ciudadano") // Modificar
            println("5. Salir") // Salir
            print("Elige una opción: ")
            opcion = readln()

            when (opcion) {
                "1" -> anadirCiudadano()
                "2" -> verCiudadanos()
                "3" -> borrarCiudadano()
                "4" -> modificarCiudadano()
            }
        } while (opcion != "5")
    }

    /**
     * Añade un nuevo ciudadano.
     */
    fun anadirCiudadano() {
        println("Introduce los datos del ciudadano:")
        print("Nombre: ")
        val nombre = readln()
        print("Apellidos: ")
        val apellidos = readln()
        print("Ciudad: ")
        val ciudad = readln()
        print("DNI: ")
        val dni = readln()
        print("Código Postal: ")
        val codigoPostal = readln()

        val ciudadano = Modelo.Ciudadano(nombre, apellidos, ciudad, dni, codigoPostal)
        controller.agregarCiudadano(ciudadano)
    }

    /**
     * Muestra todos los ciudadanos.
     */
    fun verCiudadanos() {
        println("Ciudadanos:")
        val ciudadanos = controller.obtenerCiudadanos()
        ciudadanos.forEach { println(it) }
    }

    /**
     * Borra un ciudadano.
     */
    fun borrarCiudadano() {
        print("Introduce el DNI del ciudadano a borrar: ")
        val dni = readln()
        controller.borrarCiudadano(dni)
    }

    /**
     * Modifica los datos de un ciudadano.
     */
    fun modificarCiudadano() {
        print("Introduce el DNI del ciudadano a modificar: ")
        val dni = readln()

        println("Introduce los nuevos datos del ciudadano:")
        print("Nombre: ")
        val nombre = readln()
        print("Apellidos: ")
        val apellidos = readln()
        print("Ciudad: ")
        val ciudad = readln()
        print("Código Postal: ")
        val codigoPostal = readln()

        val ciudadano = Modelo.Ciudadano(nombre, apellidos, ciudad, dni, codigoPostal)
        controller.modificarCiudadano(dni, ciudadano)
    }
}
