package lr1

typealias BstInt = BinarySearchTree<Int, Int>

typealias BstIntNode = BinarySearchTree<Int, Int>.Node

var bstInt = BstInt()

fun printTree(root: BstIntNode) {
  bstInt.print(root)
  println("------------------")
}

fun main() {
  var tree = (0..9).shuffled().fold(null) { tree: BstIntNode?, e: Int -> bstInt.add(tree, e, e) }!!
  var _print = { printTree(tree) }
  _print()
  tree = bstInt.add(tree, 3, 4)
  _print()
  println(bstInt.search(tree, 3))
}
