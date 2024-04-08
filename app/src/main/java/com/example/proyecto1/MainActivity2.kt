package com.example.proyecto1

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto1.databinding.ActivityMain2Binding
import java.util.Calendar
import kotlin.random.Random


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Declaracion de variables de  RadiButton
        val rM : RadioButton= findViewById(R.id.rb_MasterCard)
        val rV : RadioButton= findViewById(R.id.rb_Visa)
        val rA : RadioButton= findViewById(R.id.rb_American)

        // Numero random para el valor total entre 100 y 5001
        val total = Random.nextInt(100,5001)
        binding.tvTotal.text = getString(R.string.Pesos,total)



        binding.btnVerificar.setOnClickListener {

            val anioActual= Calendar.getInstance().get(Calendar.YEAR)
            val mesActual= Calendar.getInstance().get(Calendar.MONTH)


            val mesT = binding.etMesV.text.toString().toIntOrNull()?:0
            val anioT = binding.etAnioV.text.toString().toIntOrNull()?:0

            val verifi1 = binding.etNumTarjeta.text.toString()
            val verifi2 = binding.etCVV.text.toString()
            val email = binding.etCorreo.text.toString()

            if(binding.etNomTarjeta.text.isNotEmpty()&& binding.etNumTarjeta.text.isNotEmpty() && binding.etCVV.text.isNotEmpty() && verifi1.length==16 && verifi2.length>=3 && (rM.isChecked||rV.isChecked||rA.isChecked) && binding.etCorreo.text.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()&&binding.etMesV.text.isNotEmpty()&& binding.etAnioV.text.isNotEmpty()&& mesT<= 12 &&((anioT>anioActual&& anioT<=anioActual+5)||(anioT==anioActual&& mesT>=mesActual))){
                // Si todas las casillas estan llenas procedemos a pasar parametros a la siguiente
                Toast.makeText(this,resources.getString(R.string.Procesando_Pago), Toast.LENGTH_SHORT).show()
                val params = Bundle()
                params.putString("name",binding.etNomTarjeta.text.toString())
                params.putInt("monto",total)
                params.putString("correo",binding.etCorreo.text.toString())
                val intent = Intent(this, MainActivity3::class.java)
                intent.putExtras(params)
                startActivity(intent)

            }else{
                //Muestra de errores
                if(binding.etNomTarjeta.text.isEmpty()){
                    binding.etNomTarjeta.error = resources.getString(R.string.Ingrese_Dato)
                    binding.etNomTarjeta.requestFocus()
                }
                if(binding.etNumTarjeta.text.isEmpty()|| verifi1.length!=16 ){
                    binding.etNumTarjeta.error = resources.getString(R.string.Ingrese_Dato)
                    binding.etNumTarjeta.requestFocus()
                }
                if(binding.etCVV.text.isEmpty()||verifi2.length<3){
                    binding.etCVV.error = resources.getString(R.string.Ingrese_Dato)
                    binding.etCVV.requestFocus()

                }

                if(binding.rbAmerican.text.isEmpty()||binding.rbMasterCard.text.isEmpty()||binding.rbVisa.text.isEmpty()){
                    Toast.makeText(this,resources.getString(R.string.Ingrese_Dato), Toast.LENGTH_SHORT).show()
                }

                if(binding.etCorreo.text.isEmpty()){
                    binding.etCorreo.error = resources.getString(R.string.Ingrese_Dato)

                }else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.etCorreo.error = resources.getString(R.string.Ingrese_DatoValido)
                  }

                if(binding.etMesV.text.isEmpty()||binding.etAnioV.text.isEmpty()){
                    binding.etMesV.error = resources.getString(R.string.Ingrese_Dato)
                    binding.etMesV.requestFocus()
                    binding.etAnioV.error = resources.getString(R.string.Ingrese_Dato)
                    binding.etAnioV.requestFocus()
                }else if(anioT<anioActual || (anioT==anioActual&& mesT<=mesActual)){
                    binding.etMesV.error = resources.getString(R.string.Tarjeta_Vencida)
                    binding.etMesV.requestFocus()
                    binding.etAnioV.error = resources.getString(R.string.Tarjeta_Vencida)
                    binding.etAnioV.requestFocus()
                }else if(anioT>anioActual+5){
                    binding.etAnioV.error = resources.getString(R.string.Dato)
                    binding.etAnioV.requestFocus()
                    }else if(mesT>12){
                    binding.etMesV.error = resources.getString(R.string.Dato)
                    binding.etMesV.requestFocus()
                     }

            }

        }//FIN DEL binding

    }//fin over

}// fin funcion




