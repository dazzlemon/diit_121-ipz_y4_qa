package lr1

typealias BstInt = BinarySearchTree<Int, Int>

typealias BstIntNode = BinarySearchTree<Int, Int>.Node

var bstInt = BstInt()

fun printSeparator() = println("-----------------------------")

fun printTree(root: BstIntNode) {
  bstInt.print(root)
  printSeparator()
}

fun main() {
  var tree = (0..9).shuffled().fold(null) { tree: BstIntNode?, e: Int -> bstInt.add(tree, e, e) }!!

  var _print = { printTree(tree) }
  _print()

  tree = bstInt.add(tree, 3, 4)
  _print()

  println(bstInt.search(tree, 3))
  printSeparator()

  println(tree.key)
  printSeparator()

  bstInt.add(tree, tree.key, -1)
  _print()

  val board = MutableList(4) { _ -> MutableList(4) { _ -> false } }
  board[1][3] = true
  val nq = NQueen()

  if (nq.solve(board)) {
    nq.printBoard(board)
  } else {
    print("solution doesn't exist")
  }
}
