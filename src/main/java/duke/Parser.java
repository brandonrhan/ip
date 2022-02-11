package duke;

import java.time.LocalDate;

/**
 * Execute user commands
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor of Parser
     * @param ui the ui of Duke
     * @param taskList the tasklist to store all the tasks
     * @param storage the storage to save all the tasks
     */
    Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * if input starts with list, print out all tasks in the list
     * if input starts with mark, mark the task as done
     * if input starts with unmark, unmark the task as not done
     * if input starts with todo, deadline or event, create a corresponding task and add to list
     * if input starts with delete, delete the corresponding task
     * if input starts with check, check all the task on the same date
     * @param input User command
     * @return String after the execution of user command
     * @throws DukeException if wrong input is detected
     */
    String execute(String input) throws DukeException {
        assert(input.length() > 0);
        String output;
        try {
            String[] inputArray = input.split(" ", 2);
            String s1 = inputArray[0].toLowerCase();
            if (s1.equals("bye")) {
                output = ui.echo("GoodBye! Thanks for using B.H!");
            } else if (s1.equals("list")) {
                output = ui.echo(this.taskList.getList());
            } else if (s1.equals("mark")) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                if (index < this.taskList.getListSize()) {
                    output = ui.echo("Well done! \n" + this.taskList.mark(index));
                } else {
                    output = ui.echo("Index out of range");
                }
            } else if (s1.equals("unmark")) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                output = ui.echo("Oh no! \n" + this.taskList.unMark(index));
            } else if (s1.equals("todo")) {
                String task = inputArray[1];
                Task newTask = new Todo(task);
                this.taskList.addToList(newTask);
                output = ui.echo("Task added: " + newTask.toString() + "\n"
                        + "Now you have " + this.taskList.getListSize() + " tasks in the list");
            } else if (s1.equals("deadline")) {
                String s = inputArray[1];
                String[] arr = s.split("/by");
                String task = arr[0];
                String time = arr[1];
                Task newTask = new Deadline(task, time);
                this.taskList.addToList(newTask);
                output = ui.echo("Task added:" + newTask.toString() + "\n"
                        + "Now you have " + this.taskList.getListSize() + " tasks in the list");
            } else if (s1.equals("event")) {
                String s = inputArray[1];
                String[] arr = s.split("/at");
                String task = arr[0];
                String time = arr[1];
                Task newTask = new Event(task, time);
                this.taskList.addToList(newTask);
                output = ui.echo("Task added:" + newTask.toString() + "\n"
                        + "Now you have " + this.taskList.getListSize() + " tasks in the list");
            } else if (s1.equals("delete")) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                output = ui.echo("Okay, I have remove this task:\n"
                        + this.taskList.deleteTask(index));
            } else if (s1.equals("check")) {
                LocalDate date = LocalDate.parse(inputArray[1]);
                output = ui.echo(this.taskList.checkDate(date));
            } else if (s1.equals("find")) {
                String[] arr = input.split(" ", 2);
                String word = arr[1];
                output = ui.echo(this.taskList.checkWord(word));
            } else {
                output = ui.echo("Wrong input, please check again");
            }
            this.storage.save(this.taskList.getArrayList());
            return output;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new DukeException(ui.echo("Duke exception!!!"));
        }
    }
}
