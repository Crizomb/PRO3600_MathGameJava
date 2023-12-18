import base.Items;
import base.ItemsStack;
import base.Operator;
import base.Player;

import java.util.Stack;


class Test {
    public static void testItems(){
        Items a = new Items(5);
        boolean b = a.getHaveParent();
        System.out.println(b);
    }

    public static void testStack(){
        Items a = new Items(5);
        Items b = new Items(3);
        Items c = new Items(2);
        Items d = new Items(Operator.ADD);


        ItemsStack stack = new ItemsStack();
        stack.push(a);
        stack.push(b);
        stack.push(c);
        stack.push(d);

        Items test = stack.pop();
        Items test2 = stack.pop();
        Items test3 = stack.pop();
        Items test4 = stack.pop();

        System.out.println(test.getValue().getOperator());
        System.out.println(test2.getValue().getIntValue());
        System.out.println(test3.getValue().getIntValue());
        System.out.println(test4.getValue().getIntValue());

    }

    public static void testPlayerLogic() throws Exception {


        Player player = new Player();
        System.out.println(player);

        Items first_elem = player.inventory.get(0);
        Items second_elem = player.inventory.get(1);
        Items third_elem = player.inventory.get(2);
        player.push_number_in_stack(first_elem);
        player.push_number_in_stack(second_elem);
        player.push_operator_in_stack(new Items(Operator.ADD));

        // attack += inventory[0] + inventory[1]
        player.updateAttack();

        player.push_number_in_stack(third_elem);
        player.push_operator_in_stack(new Items(Operator.ADD));
        player.updateAttack();

        // player has changed
        System.out.println(player);
        // last
        System.out.println(player.stack);




    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Test.testStack();
        System.out.println("----------");
        Test.testPlayerLogic();
    }
}