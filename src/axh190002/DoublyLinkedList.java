/**
 * IDSA Short Project 1
 * Team Members:
 * Adarsh Raghupati  NetID: axh190002
 * Keerti Keerti     NetID: kxk190012
 */

package axh190002;


import java.util.Iterator;
import java.util.Scanner;

public class DoublyLinkedList<T> extends SinglyLinkedList<T> {
    /**
     * Class Entry holds a single node of the list with pointer to previous node
     * @param <E>
     */
    static class Entry<E> extends SinglyLinkedList.Entry<E> {
        Entry<E> prev;
        Entry(E x, SinglyLinkedList.Entry<E> next, Entry<E> prev) {
            super(x, next);
            this.prev = prev;
        }
    }

    /**
     * Creates DoublyLinkedList with dummy head
     */
    public DoublyLinkedList(){
        super();
        head = new Entry<T>(null, null,null);
        tail = head;
    }

    /**
     *
     * @return DoublyLinkedList iterator
     */
    public Iterator<T> iterator () {
        return new DLLIterator();
    }

    /**
     * Class DLLIterator extends SLLIterator and provides methods hasPrev(), prev() and add()
     */
    protected class DLLIterator extends SLLIterator implements Iterator<T> {

        public DLLIterator(){
            super();
        }

        /**
         * Checks if there is a previous element with respect to current position of iterator
         * @return
         */
        public boolean hasPrev(){
            if(((DoublyLinkedList.Entry) cursor).prev == head)
                return false;
            return ((DoublyLinkedList.Entry) cursor).prev != null;
        }

        /**
         *
         * @return Moves the iterator to previous element and returns it
         */
        public T prev(){
            if(hasPrev()){
                cursor = prev;
                prev = ((Entry<T>) cursor).prev;
                ready = true;
            }

            return cursor.element;
        }

        /**
         * Adds element before the element that will be returned by a call to next().
         * @param addElem
         */
        public void add(T addElem){
            cursor.next = new Entry<T>(addElem,cursor.next,((DoublyLinkedList.Entry<T>)cursor));
            prev = cursor;
            if(tail == cursor){
                tail = cursor.next;
            }
            cursor = cursor.next;
            size++;
            ready = false;
        }


    }

    /**
     * Appends new element at the end of the list
     * @param x
     */
    public void add(T x){
        super.add(new Entry<T>(x,null,((DoublyLinkedList.Entry<T>)tail)));
    }

    public static void main(String[] args){

        int n = 10;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        for(int i=1; i<=n; i++) {
            dll.add(Integer.valueOf(i));
        }
        dll.printList();

        Iterator<Integer> it = dll.iterator();
        System.out.println(" Press 1: Next Element Press 2: Check whether previous exists Pres 3: Get previous Element Press 4: Add new element before the node returned by next call to next()");
        Scanner in = new Scanner(System.in);

        whileloop:
        while(in.hasNext()) {
            int com = in.nextInt();
            switch(com) {
                case 1:  // Move to next element and print it
                    if (it.hasNext()) {
                        System.out.println("Next Element is: "+it.next());
                    } else {
                        System.out.println("End of list reached");
                        break whileloop;
                    }
                    break;
                case 2: //Check whether Previous element exists from the cursor location
                    if(((DoublyLinkedList<Integer>.DLLIterator)it).hasPrev())
                    {
                        System.out.println("Previous Element exists");
                    }
                    else
                    {
                        System.out.println("Previous element does not exist");
                    }
                    break;

                case 3: //Move to Previous element from the cursor location and print it
                    if(((DoublyLinkedList<Integer>.DLLIterator)it).hasPrev())
                    {
                        System.out.println("Previous Element is: "+((DoublyLinkedList<Integer>.DLLIterator)it).prev());
                    }
                    else
                    {
                        System.out.println(" Start of list reached. There is no previous element");
                    }
                    break;

                case 4:  // Add element before the element that will be returned by a call to next().
                    Scanner addin = new Scanner(System.in);
                    ((DoublyLinkedList<Integer>.DLLIterator)it).add(addin.nextInt());
                    dll.printList();
                    break;
                default:  // Exit loop
                    break whileloop;
            }
        }
        dll.printList();
    }

}
