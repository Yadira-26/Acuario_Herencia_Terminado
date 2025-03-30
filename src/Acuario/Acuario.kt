package Acuario

import kotlin.math.PI

open class Acuario(
    open var largo: Int = 100,
    open var ancho: Int = 20,
    open var alto: Int = 40
) {
    // Constructor secundario basado en el número de peces
    constructor(numeroDePeces: Int) : this() {
        val volumenRequerido = numeroDePeces * 2000 // 2000 cm³ por pez
        val altoRequerido = volumenRequerido / (largo * ancho)
        alto = if (altoRequerido > alto) altoRequerido else alto
    }

    // Propiedad volumen con getter y setter
    open var volumen: Int
        get() = ancho * alto * largo / 1000 // Convertimos cm³ a litros
        set(valor) {
            alto = (valor * 1000) / (ancho * largo) // Recalcula la altura en función del volumen deseado
        }

    // Nueva propiedad open para la forma
    open val forma: String = "rectángulo"

    // Nueva propiedad open para calcular el agua disponible (90% del volumen)
    open var agua: Double
        get() = volumen * 0.9
        set(_) {} // Setter vacío para evitar modificaciones manuales

    init {
        println("Inicializando acuario")
    }

    fun imprimirTamano() {
        println(forma) // Imprimir la forma del acuario
        println("Ancho: $ancho cm " +
                "Largo: $largo cm " +
                "Alto: $alto cm ")
        println("Volumen: $volumen l Agua: $agua l (${agua / volumen * 100.0}% lleno)")
    }
}

// Subclase TanqueTorre
class TanqueTorre(override var alto: Int, var diametro: Int) : Acuario(alto = alto, ancho = diametro, largo = diametro) {
    override var volumen: Int
        get() = (ancho / 2 * largo / 2 * alto / 1000 * PI).toInt() // Volumen de un cilindro
        set(valor) {
            alto = ((valor * 1000 / PI) / (ancho / 2 * largo / 2)).toInt() // Ajustar altura
        }

    override var agua = volumen * 0.8 // 80% del volumen total
    override val forma = "cilindro"

    init {
        println("Inicializando Tanque Torre")
    }
}

