package base;
// Represente les objets qu'on met dans la pile de calcul
// Nécessaire pour faire des retours en arrière du type 5+2 -> 7, le joueur veut retourner en arrière : 7 -> 5, 2
// Le tas contient des int et des operateurs, chiant à manipuler mais nécessaire

public class Items {
    // Immuable
    private OperatorOrInt value;
    private final boolean have_parent;
    private Items parent1;
    private Items parent2;

    public Items(int value){
        this.value = new OperatorOrInt(value);
        this.have_parent = false;

    }

    public Items(Operator value){
        this.value = new OperatorOrInt(value);
        this.have_parent = false;

    }

    public Items(int value, Items parent1, Items parent2){
        this.value = new OperatorOrInt(value);
        this.have_parent = true;
        this.parent1 = parent1;
        this.parent2 = parent2;

    }


    public boolean getHaveParent() {
        return this.have_parent;
    }

    public OperatorOrInt getValue(){
        return this.value;
    }

    public Items getParent1() throws NoSuchFieldException {

        if (this.have_parent){
            return this.parent1;
        }
        else{
            throw new NoSuchFieldException("This Item doesn't have parents");
        }

    }

    public Items getParent2() throws NoSuchFieldException {

        if (this.have_parent){
            return this.parent2;
        }
        else{
            throw new NoSuchFieldException("This Item doesn't have parents");
        }

    }

    public String toString(){
        if (this.have_parent) {
            return String.format("Items(value: %s, parent1: %s, parent2: %s)", value, parent1, parent2);
        }
        else {
            return String.format("Items(value: %s)", value);
        }
    }

}
