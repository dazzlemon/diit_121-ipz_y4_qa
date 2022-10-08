class BinarySearchTree<Key : Comparable<Key>, Data> {
  inner class Node(
      val key: Key,
      var value: Data,
      var left: Node?,
      var right: Node?,
  )

  fun search(
      root: Node?,
      key: Key,
  ) = _search(root, key).second?.value

  private fun _search(
      root: Node?,
      key: Key,
  ): Pair<Node?, Node?> {
    var prev = root
    var curr = root
    while (curr != null && key != curr.key) {
      prev = curr.also { curr = if (key < curr!!.key) curr?.left else curr?.right }
    }
    return Pair(prev, curr)
  }

  fun traverse(n: Node?, depth: Int, f: (n: Node, depth: Int) -> Unit): Unit {
    if (n != null) {
      f(n, depth)
      traverse(n.left, depth + 1, f)
      traverse(n.right, depth + 1, f)
    }
  }

  fun add(root: Node?, key: Key, value: Data): Node {
    var (prev, _) = _search(root, key)
    var newNode = Node(key, value, null, null)
    if (root == null || prev == null) {
      return newNode
    } else {
      if (key.compareTo(prev.key) < 0) {
        prev.left = _assign(prev.left, newNode)
      } else {
        prev.right = _assign(prev.right, newNode)
      }
      return root
    }
  }

  private fun _assign(node: Node?, newNode: Node) = node?.apply { value = newNode.value } ?: newNode

  fun print(root: Node) =
      traverse(root, 0) { n, depth -> println("\t".repeat(depth) + "${n.key}: ${n.value}") }
}

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
