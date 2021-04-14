package tictactoe

import java.util.*

fun printGrid(str: Array<Array<String>>): Unit {
    println("---------")
    for (i in 0..2) {
        print("| ")
        for (k in 0..2) {
            print("${str[i][k]} ")
        }
        println("|")
    }
    println("---------")
}

fun analyze(str: Array<Array<String>>): String {
    var countX = 0
    var countO = 0
    var winsX = false
    var winsO = false
    var totalCount = 0

    for (i in 0..2) {
        for (j in 0..2) {
            if (str[i][j] == "X") countX++
            if (str[i][j] == "O") countO++
            if (countO == 3) winsO = true
            if (countX == 3) winsX = true
        }
        totalCount += (countO + countX)
        countX = 0
        countO = 0
        for (k in str.indices) {
            if (str[k][i] == "X") countX++
            if (str[k][i] == "O") countO++
            if (countO == 3) winsO = true
            if (countX == 3) winsX = true
        }
        countX = 0
        countO = 0
    }

    if (str[0][0] == "X" && str[1][1] == "X" && str[2][2] == "X" ||
        str[0][2] == "X" && str[1][1] == "X" && str[2][0] == "X") winsX = true
    if (str[0][0] == "O" && str[1][1] == "O" && str[2][2] == "O" ||
        str[0][2] == "O" && str[1][1] == "O" && str[2][0] == "O") winsO = true

    val analize = (
            if (winsX) {
                "X wins"
            } else if (winsO) {
                "O wins"
            } else if (totalCount == 9) {
                "Draw"
            } else ""
            )
    return analize
}

fun playerInput(arrayGrid: Array<Array<String>>, player: String) {
    var x = -1
    var y = -1
    var error = ""
    var loop = true
    do {
        val scanner = Scanner(System.`in`)
        print("Enter the coordinates: ")
        try {
            x = scanner.nextInt()
            y = scanner.nextInt()
        } catch (e: InputMismatchException) {
            error = "You should enter numbers!"
        }
        if (error.isNotEmpty()) {
            println(error)
            error = ""
        } else if (x !in 1..3 || y !in 1..3) {
            println("Coordinates should be from 1 to 3!")
        } else if (arrayGrid[x - 1][y - 1] == "X" || arrayGrid[x - 1][y - 1] == "O") {
            println("This cell is occupied! Choose another one!")
        } else {
            arrayGrid[x - 1][y - 1] = player
            loop = false
        }
    } while (loop)
}

fun main() {
    var arrayGrid = Array(3, { Array(3, { " " }) })
    var player = "X"
    while (analyze(arrayGrid).isEmpty()) {
        printGrid(arrayGrid)
        playerInput(arrayGrid, player)
        player = if (player == "X") "O" else "X"
    }
    printGrid(arrayGrid)
    println(analyze(arrayGrid))


}