package com.epam.rd.java.basic.practice6.part5;

public class Tree<E extends Comparable<E>> {

    private Node<E> head = null;


    public boolean add(E element) {
        Node<E> start = head;

        if (head == null) {
            head = new Node<>(element, null, null);
            return true;
        }

        return add(element,start);
    }

    private boolean add(E value, Node<E> current) {
        int comp = value.compareTo(current.value);

        if (comp == 0) return false;

        if (comp > 0) {
            if (current.right != null) {
                return add(value, current.right);
            } else {
                current.right = new Node<>(value, null, null);
                return true;
            }
        } else {
            if (current.left != null) {
                return add(value, current.left);
            } else {
                current.left = new Node<>(value,null,null);
                return true;
            }
        }
    }

    public void add(E[] elements) {
        for (E x : elements) {
            add(x);
        }
    }

    public boolean remove(E element) {
        if (head == null) {
            return false;
        } else {
            if (head.value == element) {
                Node<E> auxRoot = new Node<>(null, null, null);
                auxRoot.left = head;
                boolean res = remove(element, head, auxRoot);
                head = auxRoot.left;
                return res;
            } else {
                return remove(element, head, null);
            }

        }

    }

    private boolean remove(E value, Node<E> current, Node<E> parent) {
        if (value.compareTo(current.value) < 0) {
            return checkLeftChild(value, current);
        } else if (value.compareTo(current.value) > 0) {
            return checkRightChild(value, current);
        } else {
            if (current.left != null && current.right != null) {
                current.value = minValue(current.right);
                remove(current.value, current.right, current);
            } else if (parent.left == current) {
                parent.left = (current.left != null) ? current.left : current.right;
            } else if (parent.right == current) {
                parent.right = (current.left != null) ? current.left : current.right;
            }
            return true;
        }
    }

    private E minValue(Node<E> current) {
        if (current.left == null) {
            return current.value;
        } else {
            return minValue(current.left);
        }
    }

    private boolean checkLeftChild(E value, Node<E> current) {
        if (current.left != null) {
            return remove(value, current.left, current);
        } else {
            return false;
        }
    }

    private boolean checkRightChild(E value, Node<E> current) {
        if (current.right != null) {
            return remove(value, current.right, current);
        } else {
            return false;
        }
    }


    public void print() {
        head.printTree();
    }

    private void print(Node<E> current) {

    }


    private static final class Node<E> {

        E value;
        Node<E> left;
        Node<E> right;

        public Node(E value, Node<E> leftChild, Node<E> rightChild) {
            this.value = value;
            this.left = leftChild;
            this.right = rightChild;
        }

        public void printTree() {
            if (right != null) {
                right.printTree( true, "");
            }
            printNodeValue();
            if (left != null) {
                left.printTree( false, "");
            }
        }
        private void printNodeValue() {
            if (value == null) {
                System.out.println("<null>");
            } else {
                System.out.println(value.toString());
            }
            System.out.print('\n');
        }
        private void printTree( boolean isRight, String indent) {
            if (right != null) {
                right.printTree(true, indent + (isRight ? "        " : " |      "));
            }
            System.out.print(indent);
            if (isRight) {
                System.out.print(" /");
            } else {
                System.out.print(" \\");
            }
            System.out.print("----- ");
            printNodeValue();
            if (left != null) {
                left.printTree(false, indent + (isRight ? " |      " : "        "));
            }
        }

    }

}

