package dominguez.cesar.calculadoracetes

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dominguez.cesar.calculadoracetes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var calculadoraViewModel: CalculadoraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sbPlazo.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Actualiza el texto del TextView 'etMeses' con el valor del SeekBar
                binding.etMeses.text = "Meses: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Este método se llama cuando el usuario toca el SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Este método se llama cuando el usuario deja de tocar el SeekBar
            }
        })

        calculadoraViewModel = ViewModelProvider(this).get(CalculadoraViewModel::class.java)

        binding.btnCalcular.setOnClickListener {
            realizarCalculo()
        }
    }
    fun realizarCalculo() {
        val plazo = binding.sbPlazo.progress
        val cantidadTexto = binding.etCantidad.text.toString()

        if (cantidadTexto.isBlank()) {
            Toast.makeText(
                this,
                "Ingresa un número válido en la cantidad",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val cantidad = cantidadTexto.toDouble()

        if (cantidad == 0.0 || plazo == 0) {
            Toast.makeText(
                this,
                "Valores inválidos, por favor, ingrese valores válidos",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            // Realizar el cálculo si los valores son válidos
            val resultado = calcularREndimiento(plazo, cantidad)

            // Redondear el resultado a dos decimales
            val resultadoRedondeado = (resultado * 100).toDouble().toInt() / 100.0

            // Mostrar el resultado en tvResultado
            binding.tvResultado.text = "$" + resultadoRedondeado.toString()
        }
    }

    fun calcularREndimiento(plazo: Int, cantidad: Double): Double {
        val interesPorMes = (11.0 / 12.0) / 100
        return cantidad + (interesPorMes * cantidad * plazo)
    }
}





