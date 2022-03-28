package Config;

public class LinkedList<T> {
    class NodeList<E> {
        E element;
        NodeList<E> next;

        NodeList() {}
    }
    NodeList<T> head = new NodeList<>();
    String course;

    public LinkedList() {
        head = null;
    }

    public String getCourse() {
        return this.course;
    }

    public void appendList(T val) {
        NodeList<T> newNode;
        NodeList<T> nodePtr;

        newNode = new NodeList<T>();
        newNode.element = val;
        newNode.next = null;

        if (head == null) {
            head = newNode;
        }
        else {
            nodePtr = head;

            while (nodePtr.next != null) {
                nodePtr = nodePtr.next;
            }
            nodePtr.next = newNode;
        }
    }

    public T returnNode(int index) {
        NodeList<T> nodePtr = null;
        NodeList<T> previousNode = null;
        nodePtr = head;

        for (int i = 0; i <index+1; i++) {
            previousNode = nodePtr;
            nodePtr = nodePtr.next;
        }
        return previousNode.element;
    }

    public T returnNode(String toString) {
        NodeList<T> nodePtr = head;
        T targetElement = null;

        while(nodePtr != null) {
            String nodeHolder = nodePtr.element.toString();

            if (nodeHolder.compareTo(toString) == 0) {
                targetElement = nodePtr.element;
            }
        }
        return targetElement;
    }

    public void insertList(int index, T val) {
        NodeList<T> nodePtr;
        NodeList<T> previousNode;
        NodeList<T> newNode = new NodeList<T>();

        newNode.element = val;
        newNode.next = null;

        nodePtr = head;
        previousNode = null;

        for (int i=0; i<index; i++) {
            previousNode = nodePtr;
            nodePtr = nodePtr.next;
        }

        if (previousNode == null) {
            head = newNode;
        }
        else {
            previousNode.next = newNode;
        }
        newNode.next = nodePtr;
    }

    public void insertList(T val) {
        NodeList<T> nodePtr = null;
        NodeList<T> previousNode = null;
        NodeList<T> newNode = new NodeList<T>();
        boolean onLoop = true;

        newNode.element = val;
        newNode.next = null;

        if (head == null) {
            head = newNode;
            newNode.next = null;
        }
        else {
            nodePtr = head;
            previousNode = null;

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
                newNode.next = nodePtr;
            }
            else {
                previousNode.next = newNode;
                newNode.next = nodePtr;
            }
        }
    }

    public boolean checkIfNull() {
        if (head == null) {
            return true;
        }
        return false;
    }

    public int getListSize() {
        NodeList<T> nodePtr = null;
        int size = 0;

        nodePtr = head;

        while(nodePtr != null) {
            size += 1;
            nodePtr = nodePtr.next;
        }
        return size;
    }

    public void displayListContents() {
        NodeList<T> nodePtr = null;
        int counter = 1;

        if (head == null) {
            System.out.println("\nNO DATA");
            return;
        }

        nodePtr = head;

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

            while (nodePtr != null && nodePtr.element != val) {
                previousNode = nodePtr;
                nodePtr = nodePtr.next;
            }
                
            if (nodePtr != null) {
                previousNode.next = nodePtr.next;
            }
        }
    }

    public boolean validateNode(String name) {
        NodeList<T> nodePtr = null;

        nodePtr = head;

        while (nodePtr != null) {
            if (nodePtr.element.toString().compareTo(name) == 0) {
                return true;
            }
        }
        return false;
    }

    public void clearList() {
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
