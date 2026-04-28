package com.example.banking;

public class MyQueue<T> {
    private Node<T> front, rear;

    public MyQueue() {
        this.front = this.rear = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.rear == null) {
            this.front = this.rear = newNode;
            return;
        }
        this.rear.next = newNode;
        this.rear = newNode;
    }

    public T poll() {
        if (this.front == null) return null;
        T data = this.front.data;
        this.front = this.front.next;
        if (this.front == null) this.rear = null;
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        int count = 0;
        Node<T> temp = front;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}