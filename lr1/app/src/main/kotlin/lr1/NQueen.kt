package lr1

import kotlin.collections.toMutableList

typealias Board = MutableList<MutableList<Boolean>>

class NQueen {
	val _n = 4

	fun printSolution(board: Board): Unit {
		for (row in board) {
			for (cell in row) {
				print(" $cell ")
			}
			println()
		}
	}

	/* A utility function to check if a queen can
	be placed on board[row][col]. Note that this
	function is called when "col" queens are already
	placeed in columns from 0 to col -1. So we need
	to check only left side for attacking queens */
	fun isSafe(board: Board, row: Int, col: Int): Boolean {
		var _row = (0 until col).map { i: Int -> Pair(row, i) }
		val colsReverse = col downTo 0
		val upperDiagonal = (row downTo 0).zip(colsReverse)
		val lowerDiagonal = (row until _n).zip(colsReverse)

		return listOf(_row, upperDiagonal, lowerDiagonal).all { indices -> _checkCells(board, indices) }
	}

	private fun _checkCells(board: Board, indices: List<Pair<Int, Int>>): Boolean {
		for (pair in indices) {
			if (board[pair.first][pair.second] == true) {
				return false
			}
		}
		return true
	}

	fun solve(board: Board) = _solve(board, 0)

	/* A recursive utility function to solve N
	Queen problem */
	private fun _solve(board: Board, col: Int): Boolean {
		if (board.size != _n || board.any { i -> i.size != _n}) {
			throw Exception("incorrect size")
		}

		/* base case: If all queens are placed
		then return true */
		if (col >= _n) {
			return true
		}

		/* Consider this column and try placing
		this queen in all rows one by one */
		for (i in 0 until _n) {
			/* Check if the queen can be placed on
			board[i][col] */
			if (isSafe(board, i, col)) {
				/* Place this queen in board[i][col] */
				board[i][col] = true

				/* recur to place rest of the queens */
				if (_solve(board, col + 1)) {
					return true
				}

				/* If placing queen in board[i][col]
				doesn't lead to a solution then
				remove queen from board[i][col] */
				board[i][col] = false// BACKTRACK
			}
		}

		/* If the queen can not be placed in any row in
		this column col, then return false */
		return false
	}
}
// This code is contributed by Abhishek Shankhadhar
