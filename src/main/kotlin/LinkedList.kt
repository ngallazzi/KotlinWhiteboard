/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

class LinkedList<T>(vararg list: T) : Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T> {

  var head: Node<T>? = null
  var tail: Node<T>? = null
    private set

  init {
    for (element in list) {
      this.add(element)
    }
  }

  override var size = 0
    private set

  override fun remove(element: T): Boolean {
    val iterator = iterator()
    while (iterator.hasNext()) {
      val item = iterator.next()
      if (item == element) {
        iterator.remove()
        return true
      }
    }
    return false
  }

  override fun removeAll(elements: Collection<T>): Boolean {
    var result = false
    for (item in elements) {
      result = remove(item) || result
    }
    return result
  }

  override fun retainAll(elements: Collection<T>): Boolean {
    var result = false
    val iterator = this.iterator()
    while (iterator.hasNext()) {
      val item = iterator.next()
      if (!elements.contains(item)) {
        iterator.remove()
        result = true
      }
    }
    return result
  }

  override fun contains(element: T): Boolean {
    for (item in this) {
      if (item == element) return true
    }
    return false
  }

  override fun containsAll(elements: Collection<T>): Boolean {
    for (searched in elements) {
      if (!contains(searched)) return false
    }
    return true
  }

  override fun isEmpty(): Boolean {
    return size == 0
  }

  override fun add(element: T): Boolean {
    append(element)
    return true
  }

  override fun addAll(elements: Collection<T>): Boolean {
    for (element in elements) {
      append(element)
    }
    return true
  }

  override fun clear() {
    head = null
    tail = null
    size = 0
  }

  override fun iterator(): MutableIterator<T> {
    return LinkedListIterator(this)
  }

  override fun toString(): String {
    return if (isEmpty()) {
      "Empty list"
    } else {
      head.toString()
    }
  }

  fun push(value: T): LinkedList<T> {
    head = Node(value = value, next = head)
    if (tail == null) {
      tail = head
    }
    size++
    return this
  }

  fun append(value: T) {
    if (isEmpty()) {
      push(value)
      return
    }
    tail?.next = Node(value = value)

    tail = tail?.next
    size++
  }

  fun nodeAt(index: Int): Node<T>? {
    var currentNode = head
    var currentIndex = 0

    while (currentNode != null && currentIndex < index) {
      currentNode = currentNode.next
      currentIndex++
    }

    return currentNode
  }

  fun insert(value: T, afterNode: Node<T>): Node<T> {
    if (tail == afterNode) {
      append(value)
      return tail!!
    }
    val newNode = Node(value = value, next = afterNode.next)
    afterNode.next = newNode
    size++
    return newNode
  }

  fun pop(): T? {
    if (!isEmpty()) size--
    val result = head?.value
    head = head?.next
    if (isEmpty()) {
      tail = null
    }

    return result
  }

  fun removeLast(): T? {
    val head = head ?: return null

    if (head.next == null) return pop()

    size--

    var prev = head
    var current = head

    var next = current.next
    while (next != null) {
      prev = current
      current = next
      next = current.next
    }

    prev.next = null
    tail = prev
    return current.value
  }

  fun removeAfter(node: Node<T>): T? {
    val result = node.next?.value

    if (node.next == tail) {
      tail = node
    }

    if (node.next != null) {
      size--
    }

    node.next = node.next?.next
    return result
  }
}
