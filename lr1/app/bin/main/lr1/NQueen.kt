package lr1

import kotlin.collections.reversed

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

  /* check if queen can be placed on board[row][col] */
  fun isSafe(board: Board, row: Int, col: Int): Boolean { // many gotos
    val _row = (0 until _n).map { i -> row to i }
    val _col = (0 until _n).map { i -> i to col }

    val (colsBefore, colsAfter) = _splitRange(0, col, _n)
    val (rowsBefore, rowsAfter) = _splitRange(0, row, _n)

    // LR - left right
    // UD - up down
    val diagLU = rowsBefore.reversed().zip(colsBefore.reversed())
    val diagRU = rowsAfter.zip(colsBefore.reversed())
    val diagLD = rowsBefore.reversed().zip(colsAfter)
    val diagRD = rowsAfter.zip(colsAfter)

    val cells = listOf(_row, _col, diagLU, diagRU, diagLD, diagRD).flatten()

    return !_anyQueensInCells(board, cells)
  }

  private fun _splitRange(start: Int, split: Int, end: Int) =
      (start until split) to (split + 1 until end)

  private fun _anyQueensInCells(board: Board, indices: List<Pair<Int, Int>>) =
      indices.map { c -> board[c.first][c.second] }.any { i -> i }

  fun solve(board: Board): Boolean { // 6+
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

    val rows = (0 until _n)
    val colIndices = rows.map { i -> Pair(i, col) }
    if (_anyQueensInCells(board, colIndices)) {
      return _solve(board, col + 1)
    }

    val safeCells = rows.filter { i -> isSafe(board, i, col) } // 3, 4, 5+
    for (i in safeCells) {
      board[i][col] = true

      if (_solve(board, col + 1)) { // 6+
        return true
      }

      board[i][col] = false // backtrack
    }

    return false
  }
}
