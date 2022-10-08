package lr1

typealias Board = MutableList<MutableList<Boolean>>

class NQueen {
  private val _n = 4

  fun printBoard(board: Board): Unit {
    for (row in board) {
      for (cell in row) {
        print(" $cell ")
      }
      println()
    }
  }

  /* check if queen can be placed on board[row][col].
  this function is called when "col" queens are already
   placeed in columns from 0 to col -1. So we need
   to check only left side for attacking queens */
  fun isSafe(board: Board, row: Int, col: Int): Boolean { // many gotos
    var _row = (0 until col).map { i: Int -> Pair(row, i) }
    val colsReverse = col downTo 0
    val upperDiagonal = (row downTo 0).zip(colsReverse)
    val lowerDiagonal = (row until _n).zip(colsReverse)

    return listOf(_row, upperDiagonal, lowerDiagonal).all { indices -> _checkCells(board, indices) }
  }

  private fun _checkCells(board: Board, indices: List<Pair<Int, Int>>) =
      !indices.map { c -> board[c.first][c.second] }.any { i -> i }

  fun solve(board: Board): Boolean {// 6+
		if (board.size != _n || board.any { i -> i.size != _n }) { // 1
      throw Exception("incorrect size")
    }

		return _solve(board, 0)
	}

  private fun _solve(board: Board, col: Int): Boolean {
    /* base case: all queens are placed */
    if (col >= _n) { // 2
      return true
    }

    /* try placing queen in all cells of this column one by one */
    for (i in (0 until _n).filter { i -> isSafe(board, i, col) }) { // 3, 4, 5+
      board[i][col] = true

      if (_solve(board, col + 1)) { // 6+
        return true
      }

      board[i][col] = false // backtrack
    }

    return false
  }
}
