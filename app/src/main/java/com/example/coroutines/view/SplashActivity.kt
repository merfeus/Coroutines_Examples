package com.example.coroutines.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutines.MainActivity
import com.example.coroutines.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Coroutines
         * Criamos um coroutine para esperar um timer de 3s
         * precisamos especificar o scope delas
         *
         * IO: para operações de entrada / saída de dados
         * Default: designado para operações que utilizam mais recursos de CPU
         * Main: utilizado em operações que manipulam componentes de UI
         * Unconfined: destinado para operações onde não existe a real necessidade de serem executadas em thread específica
         *
         */

        val retornoAsync = CoroutineScope(Dispatchers.Main).async {
            waitSplashScreenTimer()
        }

        /**
         * Coroutine para escutar o retorno do retornoAsync, dentro dela conseguimos colocar
         * um await na variavel.
         */

        CoroutineScope(Dispatchers.Main).launch {
            /**
             * Irá escutar o retorno da função -> waitSplashScreenTimer()
             */

            val result = retornoAsync.await()

            /**
             * Só executará esta parte do código para baixo depois que retornar do waitSplashScreenTimer()
             */
            if (result) {
                callNewScreen()
            }
        }
    }

    /**
     * Usamos o suspend para propagar o suspend do delay()
     * irá retornar sometente depois de executar o delay e neste
     * caso esperar os 3 segundos
     */

    private suspend fun waitSplashScreenTimer(): Boolean {
        delay(1000)
        return true
    }

    private fun callNewScreen() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }
}
