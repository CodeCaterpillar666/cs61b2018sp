package synthesizer;

import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last++] = x;
        if (last == capacity) {
            last = 0;
        }
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T result = rb[first];
        rb[first++] = null;
        if (first == capacity) {
            first = 0;
        }
        fillCount -= 1;
        return result;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("The ArrayRingBuffer is empty");
        }
        return rb[first];
    }



    @Override
    public Iterator<T> iterator() {
        return new BQIterator<T>();
    }

    private class BQIterator<T> implements Iterator<T> {
        private int ptr;

        BQIterator() {
            ptr = 0;
        }

        @Override
        public boolean hasNext() {
            return ptr != capacity();
        }

        @Override
        public T next() {
            T result = (T) rb[ptr];
            ptr++;
            return (T) result;
        }
    }

}
