package seedu.savenus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.savenus.logic.commands.exceptions.CommandException;
import seedu.savenus.model.Model;
import seedu.savenus.model.food.Food;
import seedu.savenus.model.food.FoodComparator;

/**
 * Sorts all the foods in the $aveNUS menu based on given criterion.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "You are only allowed to input the following fields:\n"
            + "NAME, PRICE, CATEGORY, DESCRIPTION, LOCATION, OPENING_HOURS, RESTRICTIONS";
    public static final String MESSAGE_SUCCESS = "Sort Success!";

    private List<String> fields;

    /**
     * Create a simple Sort Command.
     * @param fields the list of fields.
     */
    public SortCommand(List<String> fields) {
        this.fields = fields;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Food> foodList = model.getFilteredFoodList();
        SortedList<Food> sortedList = foodList.sorted(new FoodComparator(fields));
        model.setFoods(sortedList);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
