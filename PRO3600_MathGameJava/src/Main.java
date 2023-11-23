import base.Items;
import base.Operator;
import base.Stack;


class Test {
    public static void testItems(){
        Items a = new Items(5);
        boolean b = a.getHaveParent();
        System.out.println(String.valueOf(b));
    }

    public static void testStack(){
        Items a = new Items(5);
        Items b = new Items(3);
        Items c = new Items(2);
        Items d = new Items(Operator.ADD);


        Stack stack = new Stack();
        stack.Push(a);
        stack.Push(b);
        stack.Push(c);
        stack.Push(d);

        Items test = stack.Pop();
        Items test2 = stack.Pop();
        Items test3 = stack.Pop();
        Items test4 = stack.Pop();

        System.out.println(test.getValue().getOperator().getString());
        System.out.println(String.valueOf(test2.getValue().getIntValue()));
        System.out.println(String.valueOf(test3.getValue().getIntValue()));
        System.out.println(String.valueOf(test4.getValue().getIntValue()));

    }
}

public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Test.testStack();
    }
}