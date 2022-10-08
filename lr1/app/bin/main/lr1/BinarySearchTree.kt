package lr1

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
  ): Pair<Node?, Node?> {// 3 goto
    var prev = root
    var curr = root
    var found = false;
    while (curr != null && !found) {// 1
      if (key == curr.key) {// 2
        found = true;
      } else {
        prev = curr.also { curr = if (key < curr!!.key) curr?.left else curr?.right }// 3
      }
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

  fun add(root: Node?, key: Key, value: Data): Node {// 7/8 goto
    var (prev, _) = _search(root, key)// 3
    var newNode = Node(key, value, null, null)
    if (root == null || prev == null) {// 4
      return newNode
    } else {
      if (key.compareTo(prev.key) < 0) {// 5
        prev.left = _assign(prev.left, newNode)// 7
      } else {
        prev.right = _assign(prev.right, newNode)// 7/8
      }
      return root
    }
  }

  // 2 goto
  private fun _assign(node: Node?, newNode: Node) = node?.apply { value = newNode.value } ?: newNode

  fun print(root: Node) =
      traverse(root, 0) { n, depth -> println("\t".repeat(depth) + "${n.key}: ${n.value}") }
}