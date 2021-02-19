package miranda.fernanda.asignacion4_calculadoraimc_miranda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //Variables
    var estatura : Float = 0F
    var peso : Float = 0F
    var resultado : Float = 0F
    var rango : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calcularIMC: Button = findViewById(R.id.btnCalcular)
        val weight : EditText = findViewById(R.id.weight) as EditText
        val height : EditText = findViewById(R.id.height) as EditText
        val resultadoIMC: TextView = findViewById(R.id.tvIMC)
        val rangoPeso: TextView = findViewById(R.id.tvRange)

        calcularIMC.setOnClickListener {
            peso = weight.getText().toString().toFloat()
            estatura = height.getText().toString().toFloat()
            if (validarDatos()){
                resultado = calcularIMC()
                resultadoIMC.visibility= View.VISIBLE
                resultadoIMC.setText(resultado.toString())
                rango(resultado, rangoPeso)
            }else{
                resultadoIMC.setText("Datos incorrectos")
                resultadoIMC.visibility= View.VISIBLE
            }
        }
        limpiarValores()
    }
    fun limpiarValores(){
        estatura = 0F
        peso = 0F
        resultado = 0F
        rango = ""
    }

    fun validarDatos(): Boolean{
        return estatura > 0 && peso > 0
    }

    fun rango(resultado: Float, rangoPeso:TextView){
        if(resultado < 18.5){
            rangoPeso.setText("Bajo peso")
            rangoPeso.setBackgroundResource(R.color.colorBrown)
        }else if(resultado >= 18.5  && resultado < 25F){
            rangoPeso.setText("Normal")
            rangoPeso.setBackgroundResource(R.color.colorGreen)
        }else if(resultado >= 25F && resultado < 30F){
            rangoPeso.setText("Sobre Peso")
            rangoPeso.setBackgroundResource(R.color.colorGreenish)
        }else if(resultado >= 30F && resultado < 35F){
            rangoPeso.setText("Obesidad grado 1")
            rangoPeso.setBackgroundResource(R.color.colorOrange)
        }else if(resultado >= 35F && resultado < 40F){
            rangoPeso.setText("Obesidad grado 2")
            rangoPeso.setBackgroundResource(R.color.colorRed)
        }else if(resultado >= 40F){
            rangoPeso.setText("Obesidad grado 3")
            rangoPeso.setBackgroundResource(R.color.teal_700)
        }
    }

    fun calcularIMC ():Float{
        var imc:Float = peso / (estatura * estatura)
        return imc
    }
}

