package tictactoe

import kotlin.math.abs
import kotlin.math.absoluteValue

fun printGrid(str: List<String>): Unit {
    println("---------")
    for (i in 0..2) {
        println("| ${str[i].chunked(1).joinToString(" ")} |")
    }
    println("---------")
}

fun analyze(str: List<String>): Unit {
    var countX = 0
    var countO = 0
    var winsX = false
    var winsO = false
    var totalCount = 0
    var count =0

    for (i in 0..2) {
        for (j in str[i]) {
            if (j == 'X') countX++
            if (j == 'O') countO++
            if (countO == 3) winsO = true
            if (countX == 3) winsX = true
        }
        totalCount += (countO + countX)
        count += (countX - countO)
        countX = 0
        countO = 0
        for (k in str.indices) {
            if (str[k][i] == 'X') countX++
            if (str[k][i] == 'O') countO++
            if (countO == 3) winsO = true
            if (countX == 3) winsX = true
        }
        countX = 0
        countO = 0
    }

    if (str[0][0] == 'X' && str[1][1] == 'X' && str[2][2] == 'X' || str[0][2] == 'X' && str[1][1] == 'X' && str[2][0] == 'X') winsX = true
    if (str[0][0] == 'O' && str[1][1] == 'O' && str[2][2] == 'O' || str[0][2] == 'O' && str[1][1] == 'O' && str[2][0] == 'O') winsO = true

    print(
        if (abs(count) > 1 || (winsO && winsX)) {
            "Impossible"
        } else if (winsX) {
            "X wins"
        } else if (winsO) {
            "O wins"
        } else if (totalCount == 9) {
            "Draw"
        } else "Game not finished"
    )
}

fun main() {
    print("Enter cells: ")
    val inputStr = readLine()!!.chunked(3)
    printGrid(inputStr)
    analyze(inputStr)
}