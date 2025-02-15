package duke;

/**
 * Represent a todo task
 * It corresponds to a task todo with a string of task and the time
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class Todo extends Task {

    /**
     * Constructor of Todo
     * @param task description of task
     */
    Todo(String task) {
        super(task, "T"
        );
    }

    /**
     * Represents a Todo object using String
     *
     * @return String contains detail of the Todo
     */
    @Override
    public String toString() {
        return super.toString() + "  " + this.getTag();
    }
}
