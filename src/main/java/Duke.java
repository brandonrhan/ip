import java.util.Scanner;
public class Duke {

    enum functions {
        todo,
        event,
        deadline,
        list,
        mark,
        unmark,
        delete,
        bye
    }

    public static void main(String[] args) throws DukeException {

        BH bh = new BH();
        bh.greet();
        bh.run();

    }
}
