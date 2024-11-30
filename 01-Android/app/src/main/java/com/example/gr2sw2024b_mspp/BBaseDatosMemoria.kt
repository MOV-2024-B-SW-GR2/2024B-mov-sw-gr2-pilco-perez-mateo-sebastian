package com.example.gr2sw2024b_mspp

class BBaseDatosMemoria {
    companion object {
        var arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador.add(BEntrenador(1,"Adrian", "a@a.com"))
            arregloBEntrenador.add(BEntrenador(2,"Mateo", "m@m.com"))
            arregloBEntrenador.add(BEntrenador(3,"Patricio", "p@p.com"))
        }
    }
}