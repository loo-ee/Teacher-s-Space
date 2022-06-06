package com.jannlouie.Config;

public class LinkedList<T> {
    /*
    This class is an implementation of a linked list that uses a template.
    The data structure is somehow similar to the 'Vector' when creating an
    instance.
    */
    private class NodeList<E> {
        private E element;
        private NodeList<E> next;

        private NodeList() {}
    }

    private NodeList<T> head;

    public LinkedList() {
        head = null;
    }

    public void appendList(T val) {
        NodeList<T> newNode;
        NodeList<T> nodePtr = head;

        newNode = new NodeList<T>();
        newNode.element = val;
        newNode.next = null;

        if (head == null) {
            head = newNode;
        }
        else {
            while (nodePtr.next != null) {
                nodePtr = nodePtr.next;
            }
            nodePtr.next = newNode;
        }
    }

    public T returnNode(String toString) {
        /*
        This method receives a 'toString()' of the class T,
        which in any case could be any class.
        */
        NodeList<T> nodePtr = head;
        T targetElement = null;

        while(nodePtr != null) {
            String nodeHolder = nodePtr.element.toString();

            if (nodeHolder.compareTo(toString) == 0) {
                targetElement = nodePtr.element;
            }
            nodePtr = nodePtr.next;
        }

        // The method then returns the class instance if found.
        return targetElement;
    }

    public T returnNode(int index) {
        /*
        This method is the same as the previous but this one uses an index of int.
        This method is primarily used when a for loop is accessing the elements of
        a list.
        */
        NodeList<T> nodePtr = head;
        NodeList<T> previousNode = null;

        for (int i = 0; i <index+1; i++) {
            previousNode = nodePtr;
            nodePtr = nodePtr.next;
        }

        assert previousNode != null;
        return previousNode.element;
    }

    public void insertList(T val) {
        /*
        This method is used to append the list alphabetically.
        It breaks the program so it's not used.
        */
        NodeList<T> nodePtr = head;
        NodeList<T> previousNode = null;
        NodeList<T> newNode = new NodeList<>();
        boolean onLoop = true;

        newNode.element = val;
        newNode.next = null;

        if (head == null) {
            head = newNode;
        }
        else {
            while (nodePtr != null && onLoop) {
                String elementHolder = nodePtr.element.toString();
                String valHolder = val.toString();

                if (elementHolder.compareTo(valHolder) < 0) {
                    previousNode = nodePtr;
                    nodePtr = nodePtr.next;
                }
                else {
                    onLoop = false;
                }
            }

            if (previousNode == null) {
                head = newNode;
            }
            else {
                previousNode.next = newNode;
            }
            newNode.next = nodePtr;
        }
    }

    public boolean checkIfNull() {
        return head == null;
    }

    public int getListSize() {
        NodeList<T> nodePtr = head;
        int size = 0;

        while(nodePtr != null) {
            size += 1;
            nodePtr = nodePtr.next;
        }
        return size;
    }

    public void displayListContents() {
        NodeList<T> nodePtr = head;
        int counter = 1;

        if (head == null) {
            System.out.println("\nNO DATA");
            return;
        }

        while (nodePtr != null) {
            System.out.println("Student #" + (counter++) + ":\n" + nodePtr.element);
            nodePtr = nodePtr.next;
        }
    }
 
    public void deleteNode(T val) {
        NodeList<T> nodePtr = null;
        NodeList<T> previousNode = null;
        
        if (head.element == val) {
            head = head.next;
        }
        else {
            nodePtr = head;

            /*
            The loop searches for the element that has the same 'toString()' value
            with the given argument of class T.
            */
            while (nodePtr != null && nodePtr.element != val) {
                previousNode = nodePtr;
                nodePtr = nodePtr.next;
            }

            // If found, the method then deletes the node or the element
            if (nodePtr != null) {
                previousNode.next = nodePtr.next;
            }
        }
    }

    public boolean validateNode(String combination) {
        /*
        This method validates an element if it exists in the list.
        It then returns a boolean value.
        */
        NodeList<T> nodePtr = head;

        while (nodePtr != null) {
            if (nodePtr.element.toString().compareTo(combination) == 0) {
                return true;
            }
            nodePtr = nodePtr.next;
        }
        return false;
    }

    public void clearList() {
        // This method traverses the list and deletes all the elements starting from the last.
        NodeList<T> nodePtr = head;

        while (head != null) {
            if (nodePtr.next == null) {
                head = null;
            }
            else {
                nodePtr = head.next;
                head = null;
                head = nodePtr;
            }
        }
    }
}
