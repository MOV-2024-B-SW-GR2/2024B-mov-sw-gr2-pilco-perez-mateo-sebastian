package org.example

import java.util.Date
import kotlin.math.tan

fun main() {
    /* INMUTABLES (No se reasigna "=") */
    val inmutable: String = "Adrian";
    //inmutable = "Vicente"; //Error!!

    //MUTABLES
    var mutable: String = "Adrian";
    mutable = "Vicente"; //OK!

    // VAL > VAR

    //DUCK TYPING
    val ejemploVariable = "Mateo Pilco";    //En esta variable, Kotlin ya entiende que esta variable es un String
    ejemploVariable.trim();

    //Variables primitivas
    val nombreProfesor: String = "Adrian Eguez";
    val sueldo: Double = 1.2;
    val estadoCivil: Char = 'C';
    val mayorEdad: Boolean = true;
    val fechaNacimiento: Date = Date();

    //WHEN (Switch)
    val estadoCivilWhen = "C";
    when (estadoCivilWhen) {
        "C" -> {
            println("Casado");
        }
        "S" -> {
            println("Soltero");
        }
        else -> {
            println("No sabemos");
        }
    }

    val esSoltero = (estadoCivilWhen == "S");
    val coqueteo = if (esSoltero) "Si" else "No"

    calcularSueldo(10.00) //solo el parámetro requerido
    calcularSueldo(10.00, 15.00, 20.00) //parámetro requerido y sobreescribir param. opcionales
    //Named Parameters
    calcularSueldo(10.00, bonoEspecial = 20.00)
    //Gracias a los Named Parameters
    calcularSueldo(bonoEspecial = 10.00, sueldo = 20.00, tasa = 14.00)
        //Puedo intercambiar el orden de los parámetros gracias a los parámetros nombrados

    println(ejemploVariable)
    imprimirNombre(nombre = "Adrian");

    val sumaA = Suma(1,1)
    val sumaB = Suma(null,1)
    val sumaC = Suma(1,null)
    val sumaD = Suma(null,null)
    sumaA.sumar();
    sumaB.sumar();
    sumaC.sumar();
    sumaD.sumar();
    println(Suma.pi);
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //Arreglos estáticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3);
    println(arregloEstatico);
    //Arreglo dinámico
    var arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    );
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // FOR EACH -> UNIT
    // Iterar sobre un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    // "it" (en inglés "eso") significa el elemento iterado
    arregloDinamico.forEach { println("Valor actual (it): $it")}

    // MAP -> Muta (Modifica cambio) el arreglo
    // 1) Enviamos el nuevo valor de la iteracion
    // 2) Nos devuelve un NUEVO ARREGLO con valores de las iteraciones
    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }
    println(respuestaMapDos)

    //Filter -> Filtrar el arreglo
    // 1) Devolver una expresión (True o False)
    // 2) Nuevo arreglo Filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            //Expresion o condicion
            val mayoresACinco:Boolean = valorActual > 5
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter {it <= 5}
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    // OR -> ANY (Alguna cumple?)
    // OR -> ALL (Todas cumplen?)
    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //True
    val respuestaAll:Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //False

    // REDUCE -> valor acumulado
    // Valor acumulado = 0 (Siempre empieza en 0 en Kotlin)
    // [1,2,3,4,5] -> Acumular "SUMAR" estos valores del arreglo
    // valorIteracion1 = valorEmpiezo + 1 = 0 + 1 = 1 -> Iteracion1
    // valorIteracion2 = valorAcumuladoIteracion1 + 2 = 1 + 2 = 3 -> Iteracion2
    // valorIteracion3 = valorAcumuladoIteracion2 + 3 = 3 + 3 = 6 -> Iteracion3
    // valorIteracion4 = valorAcumuladoIteracion3 + 4 = 6 + 4 = 10 -> Iteracion4
    // valorIteracion5 = valorAcumuladoIteracion4 + 5 = 10 + 5 = 15 -> Iteracion5
    val respuestaReduce: Int = arregloDinamico
        .reduce { acumulado:Int, valorActual:Int ->
            return@reduce (acumulado + valorActual) // -> Cambiar o usar la lógica del negocio
        }
    println(respuestaReduce)
}

//Unit representa a las funciones que no devuelven nada, es decir, similar a un VOID
fun imprimirNombre(nombre: String): Unit{
    fun otraFuncionAdentro() {
        println("Otra funcion adentro");
    }
    println("Nombre: $nombre");
    otraFuncionAdentro();
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa:Double = 12.0, // Opcional
    bonoEspecial:Double? = null // Opcional (nullable)

):Double {
    if(bonoEspecial == null ) {
        return sueldo * (100/tasa)
    } else {
        return sueldo * (100/tasa) * bonoEspecial
    }
}

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno:Int,
        dos:Int
    ) {
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros( //Constructor Primario
    //Caso 1) Parametro normal
    //uno: Int, (parametro (sin modificar acceso))

    //Caso 2) Parametro y propiedad (atributo) (protected)
    //private var uno: Int (propiedad "instancia.uno")
    protected val numeroUno: Int, //instancia.numeroUno
    protected val numeroDos: Int, //instancia.numeroDos
    parametroNoUsadoNoPropiedadDeLaClase:Int? = null
) {
    init { //bloque constructor primario OPCIONAL
        this.numeroUno
        this.numeroDos
        println("Inicializando")
    }
}

//Clase 7 - 11-07-2024
class Suma(
    unoParametro: Int, //Parametros
    dosParametro: Int, //Parametros
): Numeros ( //Clase padre, Numeros (extendiendo)
    unoParametro,
    dosParametro
){
    //Modificador de acceso por defecto es "public"
    public val soyPublicoExplicito: String = "Publicas"
    val soyPublicoImplicito: String = "Publico implicito"
    init { //Bloque de constructor primario
        this.numeroUno
        this.numeroDos
        numeroUno //this. OPCIONAL
        numeroDos //this. OPCIONAL
    }
    constructor( //Constructor secundario
        uno: Int?, //Entero nullo
        dos: Int,
    ): this(
        if (uno == null) 0 else uno,
        dos
    ) {
        //Bloque de códido del constructor secundario
    }
    constructor( //Constructor secundario
        uno: Int,
        dos: Int?, //Entero nullo
    ): this(
        uno,
        if (dos == null) 0 else dos
    )
    constructor( //Constructor secundario
        uno: Int?, //Entero nullo
        dos: Int?, //Entero nullo
    ): this(
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos
    )

    fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    companion object { //Comparte entre todas las instancias, similar al STATIC
        //funciones, variables
        // Accesible mediante: NombreClase.metodo, NombreClase.funcion
        //Suma.pi
        val pi = 3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num*num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma:Int){
            historialSumas.add(valorTotalSuma)
        }
    }
}

