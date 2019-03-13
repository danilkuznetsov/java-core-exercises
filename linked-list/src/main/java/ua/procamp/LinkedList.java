package ua.procamp;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private Node<T> head;

    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        List<T> list = new LinkedList<>();
        for (T el : elements) {
            list.add(el);
        }
        return list;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {

        Node<T> newNode = new Node<>(element, null);

        if (this.head == null) {
            this.head = newNode;
        } else {
            findNode(size - 1).next = newNode;
        }

        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {

        if (index == 0) {
            this.head = new Node<>(element, this.head);
        } else {
            Node<T> prevNode = findNode(index - 1);
            Node<T> newNode = new Node<>(element, prevNode.next);
            prevNode.next = newNode;
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        findNode(index).value = element;
    }


    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        return findNode(index).value;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) {

        if (index == 0) {
            Node<T> newHead = this.head.next;
            clearNode(this.head);
            this.head = newHead;
        } else {
            Node<T> prevNode = findNode(index - 1);
            Node<T> removedNode = prevNode.next;
            prevNode.next = removedNode.next;
            clearNode(removedNode);
        }

        size--;
    }

    private Node<T> findNode(int index) {

        checkIndex(index);

        Node<T> node = this.head;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {

        Node<T> node = this.head;

        while (node != null) {

            if (node.value.equals(element)) {
                return true;
            }

            node = node.next;
        }

        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {

        Node<T> node = this.head;
        while (node != null) {
            Node<T> tmpNext = node.next;
            clearNode(node);
            node = tmpNext;
        }

        this.head = null;
        size = 0;
    }

    private void clearNode(Node<T> node) {
        if (node == null) {
            return;
        }

        node.next = null;
        node.value = null;
    }

    private static class Node<T> {

        private T value;

        private Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
