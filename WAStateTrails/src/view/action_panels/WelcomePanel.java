package view.action_panels;

import actions.WstAction;
import config.WstConfiguration;
import view.action_panels.AbstractActionPanel;

/**
 * View for the welcome screen.
 * @author Matthew Phillips
 */
public class WelcomePanel extends AbstractActionPanel {

    public WelcomePanel() {
        super(false);
        addHeader(WstAction.WELCOME.value());
        addSubheader("Please select an action you want to take");
        init();
    }

    @Override
    protected boolean executeQuery() {
        return false;
    }
}
