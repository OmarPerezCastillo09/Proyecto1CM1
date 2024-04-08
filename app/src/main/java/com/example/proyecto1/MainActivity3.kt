package com.example.proyecto1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.proyecto1.databinding.ActivityMain3Binding
import kotlin.random.Random

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding3 : ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                binding3 = ActivityMain3Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding3.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val params = intent.extras
        val probability = Random.nextInt(1,5)//numero random del 1 al 4
        if(params!= null){
            val name = params.getString("name","")
            val valor = params.getInt("monto",0)
            val correo= params.getString("correo","")
            if(probability !=1){
                //Compra existosa si no es 1 (2,3 y 4), probabilidad del 75%
               //binding3.tvComprado.text = " Monto Total de tu compra \$$valor"
                binding3.tvComprado.text = getString(R.string.Monto_Total,valor)
               //binding3.tvProbability.text = "Felicidades $name tu compra fue exitosa "
               binding3.tvProbability.text = getString(R.string.Compra,name)
                binding3.tvMosCorreo.text = getString(R.string.CorreoCompra,correo)

            }else{
                //Compra no exitosa si es 1, probabilidad del 25%
                binding3.tvProbability.text = getString(R.string.NoCompra,name)
                binding3.tvMosCorreo.text = getString(R.string.NoCorreo,correo)

            }
        }

    }
}