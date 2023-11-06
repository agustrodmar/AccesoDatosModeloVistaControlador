@file:Suppress("SpellCheckingInspection")

/**
 * Función principal del programa. LLama a la Vista para que se inicie.
 */
fun main() {
    val model = Modelo()
    val controller = Controlador(model)
    val view = Vista(controller)
    view.iniciar()
}