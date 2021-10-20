import kotlin.system.measureNanoTime

/**
 *  Programa para comprobar el tiempo en nanosegundos que tarda la búsqueda binaria mediante dos métodos: ciclo while y recursividad
 */
fun main(args: Array<String>) {
    // Declaración de variables
    var cantidad_DeDatos: Int? = 0
    var numero_Buscar: Int = 0
    var bandera = false

    do {
        try {
            println("##################################################")
            print("-Ingrese la cantidad de datos a registrar: ")
            cantidad_DeDatos = readLine()!!.toInt()

            // Declaración de arreglo de números enteros ordenado
            var arreglo = Array(cantidad_DeDatos, { i -> i + 1 })

            print("-Ingrese el número a buscar en un rango del 1-$cantidad_DeDatos: ")
            numero_Buscar = readLine()!!.toInt()
            if (numero_Buscar <= 0) {
                throw NumberFormatException("Número fuera de rango $numero_Buscar")
            }

            bandera = true

            println("--------------------------------------------------")

            // Cálculo del tiempo que tarda el método iterativo
            println("Ciclo:")
            var tiempo_Ciclo = measureNanoTime {
                busqueda_Ciclo(arreglo, numero_Buscar)
            }
            println("Tiempo de Ejecución: $tiempo_Ciclo nanosegundos")
            println("--------------------------------------------------")

            // Cálculo del tiempo que tarda el método recursivo
            println("Recursivo:")
            var tiempo_Recursivo = measureNanoTime {
                var busqueda: Int = busqueda_Recursiva(arreglo, 0, arreglo.size, numero_Buscar)
                if (busqueda == -1) {
                    println("No se encontró el número [$numero_Buscar] en el arreglo")
                } else {
                    println("Número: ${arreglo[busqueda]} Posición: $busqueda")
                }
            }
            println("Tiempo de Ejecución: $tiempo_Recursivo nanosegundos")
            println("##################################################")

        } catch (e: NumberFormatException) {
            println("Error de datos: ${e.message}") // Mensaje de error para números
        } catch (b: NegativeArraySizeException) {
            println("Error de datos: ${b.message}") // Mensaje de error para la declaración de la longitud del arreglo
        }
    } while (!bandera)
}

/**
 * Busqueda Binaria Iterativa
 */
fun busqueda_Ciclo(arreglo: Array<Int>, numero: Int) {
    var inicio = 0
    var fin = arreglo.size - 1
    var centro = 0
    var estado = false

    while (inicio <= fin) {
        centro = (inicio + fin) / 2

        if (numero == arreglo[centro]) {
            estado = true
            break
        }
        if (numero < arreglo[centro]) {
            fin = centro - 1
        }
        if (numero > arreglo[centro]) {
            inicio = centro + 1
        }
    }

    if (estado) {
        println("Número: ${arreglo[centro]} Posición: $centro")
    } else {
        println("El número no se encontró en el arreglo")
    }
}

/**
 * Busqueda Binaria Recursiva
 */
fun busqueda_Recursiva(arreglo: Array<Int>, inicio: Int, fin: Int, numero: Int): Int {
    if (fin >= inicio && inicio < arreglo.size - 1) {
        var centro: Int = (inicio + fin) / 2
        if (numero == arreglo[centro]) {
            return centro
        } else {
            if (numero < arreglo[centro])
                return busqueda_Recursiva(arreglo, inicio, centro, numero)
            return busqueda_Recursiva(arreglo, centro, fin, numero)
        }
    }
    return -1
}