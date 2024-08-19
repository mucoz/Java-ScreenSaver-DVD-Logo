import java.awt.image.BufferedImage;

public class CircularLinkedList {
    private Node head;
    private Node current;

    private class Node {
        BufferedImage image;
        Node next;
        Node(BufferedImage image) {
            this.image = image;
        }
    }

    void addImage(BufferedImage image) {
        Node newNode = new Node(image);
        if (head == null) {
            head = newNode;
            head.next = head;
            current = head;
        } else {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
    }

    BufferedImage getCurrentImage() {
        return current.image;
    }

    BufferedImage getNextImage() {
        current = current.next;
        return current.image;
    }
}