package view.action_panels;

import actions.WstAction;
import config.WstConfiguration;
import view.action_panels.AbstractActionPanel;

/**
 * View for creating a new trail in the database.
 * @author Matthew Phillips
 */
public class CreatePanel extends AbstractActionPanel {

    public CreatePanel() {
        super(true);
        addHeader(WstAction.CREATE.value());
        addSubheader("Enter trail information to create a new trail");
        init();
    }

    @Override
    protected boolean executeQuery() {
        return false;
    }
}
