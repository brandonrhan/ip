package duke;

/**
 * Executes user commands
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a Parser
     *
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
     * Executes the user input and return the result after running the corresponding commands
     *
     * @param input User command
     * @return Result after the execution of user command
     * @throws DukeException If wrong input is detected
     */
    String execute(String input) throws DukeException {

        assert(input.length() > 0);
        try {
            String[] inputArray = input.split(" ", 2);
            String commandType = inputArray[0].toLowerCase();
            switch (commandType) {
            case "hi":
                return new HiCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "bye":
                return new ByeCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "list":
                return new ListCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "mark":
                return new MarkCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "unmark":
                return new UnmarkCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "todo":
                return new TodoCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "deadline":
                return new DeadlineCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "event":
                return new EventCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "delete":
                return new DeleteCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "check":
                return new CheckCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "find":
                return new FindCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "tag":
                return new TagCommand(this.ui, this.taskList, this.storage, input).runCommand();
            case "untag":
                return new UntagCommand(this.ui, this.taskList, this.storage, input).runCommand();
            default:
                return ui.sayWrongInput();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new DukeException(ui.echo("Duke exception!!!"));
        }
    }
}
