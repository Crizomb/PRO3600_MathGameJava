package base;


class Node {
    public Node parent;
    public Items item;

    public boolean isBottom;

    public Node(Items value){
        this.item = value;
        this.isBottom = true;
    }

    public Node(Items value, Node parent){
        this.item = value;
        this.parent = parent;
        this.isBottom = false;
    }

}

public class Stack{
    private Node currentNode;

    public Stack(){this.currentNode = null; }
    public Stack(Items value){ this.currentNode = new Node(value); }

    public void Push(Items value){
        Node old = this.currentNode;
        this.currentNode = new Node(value, old);
    }

    public Items Pop() throws NullPointerException{
        Node old = this.currentNode;
        this.currentNode = this.currentNode.parent;
        return old.item;
    }

    public Items Pop(Items elem) {
        return elem; //à remplir
    }


    public boolean onlyOneElem(){
        return ((this.currentNode != null) && (this.currentNode.parent == null));
    }

    public boolean isEmpty() {
        return (this.currentNode == null);
    }

    public boolean twoFirstNumbers() {
        //Vérifie si les deux objets en haut de la pile sont des nombres
        return ( (currentNode.item.getValue().isInt())&&(currentNode.parent.item.getValue().isInt()) );
    }


}

