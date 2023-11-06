// Controlador
@Suppress("SpellCheckingInspection")
/**
 * Clase para controlar la lógica de la aplicación.
 *
 * @property model El modelo de datos de la aplicación.
 */
class Controlador(private val model: Modelo) {
    /**
     * Agrega un nuevo ciudadano.
     *
     * @param ciudadano El ciudadano que voy a agregar.
     */
    fun agregarCiudadano(ciudadano: Modelo.Ciudadano) {
        model.agregarCiudadano(ciudadano)
    }

    /**
     * Borra un ciudadano.
     *
     * @param dni El DNI del ciudadano que voy a borrar.
     */
    fun borrarCiudadano(dni: String) {
        model.borrarCiudadano(dni)
    }

    /**
     * Modifica los datos de un ciudadano.
     *
     * @param dni El DNI del ciudadano a modificar.
     * @param ciudadano Los nuevos datos del ciudadano.
     */
    fun modificarCiudadano(dni: String, ciudadano: Modelo.Ciudadano) {
        model.modificarCiudadano(dni, ciudadano)
    }

    /**
     * Obtiene todos los ciudadanos.
     *
     * @return Una lista de todos los ciudadanos.
     */
    fun obtenerCiudadanos(): List<Modelo.Ciudadano> {
        return model.obtenerCiudadanos()
    }
}