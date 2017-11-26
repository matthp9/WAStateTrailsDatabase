# WAStateTrailsDatabase

Program’s main method is in Driver.java (entry point class).

Driver creates a GUI frame, which switches between panels that extend AbstractActionPanel. Check out AbstractActionPanel.java to learn more.

Each of these panels represents one action the user can take.

When the user clicks submit, a query can be executed. These queries should be defined in the subclasses of AbstractActionPanel.

You can get what the user has typed into the inputs by calling protected methods within each panel, like getUserTrailName(). These can be used in queries.

When you get done querying, re-populate the display table by calling populate method.