package lr1

import lr1.BinarySearchTree

typealias BstInt = BinarySearchTree<Int, Int>

fun main() {
  var bstInt = BstInt()
  var tree =
      (0..9).shuffled().fold(null) { tree: BinarySearchTree<Int, Int>.Node?, e: Int ->
        bstInt.add(tree, e, e)
      }
  bstInt.print(tree!!)
  println("------------------")
  tree = bstInt.add(tree, 3, 4)
  bstInt.print(tree)
  println("------------------")
  println(bstInt.search(tree, 3))
}
