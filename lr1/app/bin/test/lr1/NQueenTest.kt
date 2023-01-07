package lr1

import kotlin.test.assertEquals
import kotlin.test.assertFails
import org.junit.Test

class NQueenTests {
  // Тести за методом еквівалентних розбиттів #1
  @Test
  fun test1() {
    val board = MutableList(4) { _ -> MutableList(4) { _ -> false } }

    NQueen().solve(board)

    assertEquals(
        mutableListOf(
            mutableListOf(false, false, true, false),
            mutableListOf(true, false, false, false),
            mutableListOf(false, false, false, true),
            mutableListOf(false, true, false, false),
        ),
        board
    )
  }

  // Тести за методом еквівалентних розбиттів #2
  // Тести за методом припущення про помилку #1
  @Test
  fun test2() {
    val board = MutableList(3) { _ -> MutableList(4) { _ -> false } }

    assertFails { NQueen().solve(board) }
  }

  // Тести за методом припущення про помилку #2
  @Test
  fun test3() {
    val board = MutableList(3) { _ -> MutableList(3) { _ -> false } }

    assertFails { NQueen().solve(board) }
  }

  // Тести за методом припущення про помилку #3
  @Test
  fun test4() {
    val board = MutableList(4) { _ -> MutableList(4) { _ -> false } }
    board[0][1] = true

    NQueen().solve(board)

    assertEquals(
        mutableListOf(
            mutableListOf(false, true, false, false),
            mutableListOf(false, false, false, true),
            mutableListOf(true, false, false, false),
            mutableListOf(false, false, true, false),
        ),
        board
    )
  }

  // Тести за методом припущення про помилку #4
  @Test
  fun test5() {
    val board =
        mutableListOf(
            mutableListOf(false, true, true, false),
            mutableListOf(false, false, false, true),
            mutableListOf(true, false, false, false),
            mutableListOf(false, false, true, false),
        )

    assertEquals(false, NQueen().solve(board))
  }
}
