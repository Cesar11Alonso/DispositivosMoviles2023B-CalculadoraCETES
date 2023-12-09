package dominguez.cesar.calculadoracetes

import androidx.lifecycle.ViewModel

class CalculadoraViewModel : ViewModel() {
    var plazo = 3
    var cantidad = 100.0

    val rendimiento: Double
        get() {
            val interes_por_mes = (11.0 / 12.0) / 100
            val rendimiento = interes_por_mes * cantidad * plazo
            return cantidad + rendimiento
        }
}
