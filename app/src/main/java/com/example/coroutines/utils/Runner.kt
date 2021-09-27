package com.example.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>){

    runBlocking {
        runFor()
    }
    println("Bem Vindo!")
}

suspend fun runFor(){
    for(i in 0..5){
        delay(3000)
        println("Inicia-se em $i")
    }
}