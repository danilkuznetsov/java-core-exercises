package ua.procamp.bst;

import java.util.Objects;
import java.util.function.Consumer;

public class RecursiveBinarySearchTree<T extends Comparable> implements BinarySearchTree<T> {

    private Node<T> head;
    private int size;

    @Override
    public boolean insert(T element) {
        Node<T> newNode = new Node<>(element);

        // if tree is empty, will add new node as head
        if (Objects.isNull(this.head)) {
            this.head = newNode;
            size++;
            return true;
        }

        return insertRecursive(this.head, newNode);
    }

    private boolean insertRecursive(Node<T> current, Node<T> newNode) {
        int comparingResult = comparing(current.value, newNode.value);

        // if we already have value, we won't add it.
        if (comparingResult == 0) {
            return false;
        }

        // looking for a place for inserting
        if (comparingResult > 0) {
            return current.left == null ? insertToLeft(current, newNode) : insertRecursive(current.left, newNode);
        } else {
            return current.right == null ? insertToRight(current, newNode) : insertRecursive(current.right, newNode);
        }

    }

    private boolean insertToRight(Node<T> selected, Node<T> newNode) {
        selected.right = newNode;
        size++;
        return true;
    }

    private boolean insertToLeft(Node<T> current, Node<T> newNode) {
        current.left = newNode;
        size++;
        return true;
    }

    @Override
    public boolean search(T element) {
        return searchRecursive(this.head, element);
    }

    private boolean searchRecursive(Node<T> current, T element) {
        if (Objects.isNull(current)) {
            return false;
        }

        int comparingResult = comparing(current.value, element);

        if (comparingResult == 0) {
            return true;
        }

        if (comparingResult > 0) {
            return searchRecursive(current.left, element);
        } else {
            return searchRecursive(current.right, element);
        }
    }

    private int comparing(T currentElement, T newElement) {
        return currentElement.compareTo(newElement);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return heightRecursive(this.head);
    }

    private int heightRecursive(Node<T> current) {
        if (Objects.isNull(current)) {
            return 0;
        }

        int maxHeight = Math.max(heightRecursive(current.left), heightRecursive(current.right));

        return this.head == current ? maxHeight : maxHeight + 1;
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversalRecursive(this.head, consumer);
    }

    private void inOrderTraversalRecursive(Node<T> current, Consumer<T> consumer) {
        if (Objects.isNull(current)) {
            return;
        }

        inOrderTraversalRecursive(current.left, consumer);
        consumer.accept(current.value);
        inOrderTraversalRecursive(current.right, consumer);
    }

    private static class Node<T> {
        Node<T> left;
        Node<T> right;

        T value;

        Node(T value) {
            this.value = value;
        }
    }
}
