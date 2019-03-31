package ua.procamp;


import ua.procamp.exception.EmptyStackException;

import java.util.Objects;

public class LinkedStack<T> implements Stack<T> {

    private Node<T> head;

    private int size;

    @Override
    public void push(T element) {
        Node<T> newHead = new Node<>(element);
        newHead.next = this.head;
        this.head = newHead;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T headValue = this.head.value;
        this.head = this.head.next;
        size--;

        return headValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(this.head);
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }
}
