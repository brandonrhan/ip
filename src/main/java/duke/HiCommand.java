package duke;

/**
 * Command to handle keyword hi
 */
public class HiCommand extends Command {

    HiCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * Greets the user
     *
     * @return A greeting message
     */
    @Override
    String runCommand() {
        return ui.greet();
    }
}
