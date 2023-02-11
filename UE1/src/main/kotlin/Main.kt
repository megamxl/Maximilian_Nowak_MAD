import java.util.Scanner

data class NMReturn(var n: Int, var m: Int)

fun main(args: Array<String>) {
    val computedGuess: String? = generateANonRepeatingNumber(4)

    if (computedGuess != null) {
        println("Welcome to guess the 4 random digits ")
        println("Write x to stop the program ")
        println("Write a 4 digit number to guess ")
        val reader: Scanner = Scanner(System.`in`)
        while (true) {
            print("User input: ")
            val input: String = reader.next()
            if (input == "x") {
                println("The computed guess was $computedGuess")
                return
            }
            val curr: NMReturn = checkInputString(input, computedGuess)
            if (curr.m == 4 && curr.n == 4){
                println("You won the computed guess was $computedGuess")
                return
            }
            println("Output: ${curr.n}:${curr.m}")
        }
    }
}

fun generateANonRepeatingNumber(size: Int): String? {
    if (size > 9) return null

    var possibleNumbers = (1..9).toMutableList()
    var randomSequence: String = ""

    for (i in 1..size) {
        val rand = (0 until possibleNumbers.size).random()
        randomSequence += possibleNumbers.removeAt(rand)
    }

    return randomSequence
}

fun checkInputString(input: String, solution: String): NMReturn {
    if (input.length != solution.length) return NMReturn(0, 0)
    var n: Int = 0
    var m: Int = 0

    //number of digits guessed correctly
    var cleanInput: List<Char> = input.toList().distinct()
    for (chr in cleanInput) {
        if (chr in solution) {
            n++
        }
    }

    //the number of digits guessed correctly at their correct position
    for (i in 0 until input.length) {
        if (input.get(i) == solution.get(i)) {
            m++
        }
    }

    return NMReturn(n, m)
}