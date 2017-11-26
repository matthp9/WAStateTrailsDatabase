# WAStateTrailsDatabase

Authors:
Matthew Phillips
Hasnah Said
Bryan Sands
Jin Byoun 

*** User Interface Structure ***

- Program’s main method is in Driver.java (entry point class).

- Driver creates a GUI frame, which switches between panels that extend AbstractActionPanel. Check out AbstractActionPanel.java to learn more.

- Each of these panels represents one action the user can take.

- When the user clicks submit, a query can be executed. These queries should be defined in the subclasses of AbstractActionPanel.

- You can get what the user has typed into the inputs by calling protected methods within each panel, like getUserTrailName(). These can be used in queries.

- When you get done querying, re-populate the display table by calling the populate superclass method. You must pass back a TableModel with data filled in. The table should then automatically update.

Example program flow:

1… User in seeing the CreatePanel view. He/she fills in fields and clicks submit model.

2. executeQuery is called. A query runs to insert the data into the database.

3. A boolean should be passed back from executeQuery to represent whether the query was a success or not.

4. (Optional) Display an error message in the UI if the query failed.