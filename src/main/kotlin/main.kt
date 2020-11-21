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



fun main() {
    val list = LinkedList(1, 2, 3, 4, 5)
    val other = LinkedList(-1, 0, 2, 2, 7)
    println(list.merge(other))
}

fun <T> LinkedList<T>.reverse(): LinkedList<T> {
    val swap = LinkedList<T>()
    var next = this.head
    while (next != null) {
        swap.push(next.value)
        next = next.next
    }
    return swap
}

fun LinkedList<Int>.merge(list: LinkedList<Int>): LinkedList<Int> {
    var left = this.head
    var right = list.head
    val destination = LinkedList<Int>()
    while (left!!.next != null && right!!.next != null) {
        if (left.value < right.value) {
            destination.append(left.value)
            left = left.next
        } else {
            destination.append(right.value)
            right = right.next
        }
    }

    while (left?.value != null) {
        destination.append(left.value)
        left = left.next
    }
    while (right?.value != null) {
        destination.append(right.value)
        right = right.next
    }

    return destination
}