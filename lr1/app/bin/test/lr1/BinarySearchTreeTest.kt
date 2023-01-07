package lr1

import kotlin.test.assertEquals
import org.junit.Test

class BinarySearchTreeTests {
  // Тести за методом еквівалентних розбиттів #1

  fun assertTreeEquals(expected: BstIntNode, res: BstIntNode) {
    var resStr = ""
    var expectedStr = ""

    bstInt.traverse(res, 0) { n, _ -> resStr += n.key }
    bstInt.traverse(expected, 0) { n, _ -> expectedStr += n.key }

    // comparing strings instead of trees directly
    // because we didn't override the comparison,
    // so it just checks if they're the same object, which they aren't
    assertEquals(expectedStr, resStr)
  }

  @Test
  fun test1() {
    val root = bstInt.add(null, 3, 3)

    val res = bstInt.add(root, 5, 5)
    var expected = bstInt.Node(3, 3, null, bstInt.Node(5, 5, null, null))

    assertTreeEquals(expected, res)
  }

  // Тести за методом еквівалентних розбиттів #2
  @Test
  fun test2() {
    val root = bstInt.add(null, 3, 3)

    val res = bstInt.add(root, 1, 5)
    var expected = bstInt.Node(3, 3, bstInt.Node(1, 5, null, null), null)

    assertTreeEquals(expected, res)
  }

  // Тести за методом еквівалентних розбиттів #3
  // Тести за методом припущення про помилку #1
  @Test
  fun test3() {
    val root = bstInt.add(null, 3, 3)

    val res = bstInt.add(root, 3, 4)
    var expected = bstInt.Node(3, 4, null, null)

    assertTreeEquals(expected, res)
  }

  // Тести за методом еквівалентних розбиттів #4
  @Test
  fun test4() {
    val root = bstInt.add(null, 3, 3)

    val res = bstInt.add(root, 3, 1)
    var expected = bstInt.Node(3, 1, null, null)

    assertTreeEquals(expected, res)
  }

  // Тести за методом припущення про помилку #2
  @Test
  fun test5() {
    val root =
        bstInt.Node(
            3,
            3,
            bstInt.Node(2, 2, null, null),
            bstInt.Node(4, 4, null, null),
        )

    val res = bstInt.add(root, 5, 5)
    var expected =
        bstInt.Node(
            3,
            3,
            bstInt.Node(2, 2, null, null),
            bstInt.Node(4, 4, null, bstInt.Node(5, 5, null, null)),
        )

    assertTreeEquals(expected, res)
  }

  // Тести за методом припущення про помилку #3
  @Test
  fun test6() {
    val root =
        bstInt.Node(
            3,
            3,
            bstInt.Node(2, 2, null, null),
            bstInt.Node(4, 4, null, null),
        )

    val res = bstInt.add(root, 1, 1)
    var expected =
        bstInt.Node(
            3,
            3,
            bstInt.Node(2, 2, bstInt.Node(1, 1, null, null), null),
            bstInt.Node(4, 4, null, null),
        )

    assertTreeEquals(expected, res)
  }

  // Тести за методом припущення про помилку #4
  @Test
  fun test7() {
    val root =
        bstInt.Node(
            3,
            3,
            bstInt.Node(1, 1, null, null),
            bstInt.Node(4, 4, null, null),
        )

    val res = bstInt.add(root, 2, 2)
    var expected =
        bstInt.Node(
            3,
            3,
            bstInt.Node(1, 1, null, bstInt.Node(2, 2, null, null)),
            bstInt.Node(4, 4, null, null),
        )

    assertTreeEquals(expected, res)
  }

  // Тести за методом припущення про помилку #5
  @Test
  fun test8() {
    val root =
        bstInt.Node(
            3,
            3,
            bstInt.Node(2, 2, null, null),
            bstInt.Node(5, 5, null, null),
        )

    val res = bstInt.add(root, 4, 4)
    var expected =
        bstInt.Node(
            3,
            3,
            bstInt.Node(2, 2, null, null),
            bstInt.Node(5, 5, bstInt.Node(4, 4, null, null), null),
        )

    assertTreeEquals(expected, res)
  }
}
