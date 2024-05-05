import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player implements Contract {
    // Attributes
    protected ArrayList<String> inventory;
    protected Place currentPlace;
    protected ArrayList<Place> map; // map of complete game
    private int playerSize; // the size of the player for pulling some Alice-in-Wonderland-type magic tricks
    private int playerEnergy; // The energy level of the player
    private List<String> actions;
    private List<String> actionArgs;

    public Player(Place currentPlace, ArrayList<Place> map) {
        this.currentPlace = currentPlace;
        this.map = map;
        this.inventory = new ArrayList<String>();
        this.playerSize = 3;
        this.playerEnergy = 100;
        this.actions = new ArrayList<String>();
        this.actionArgs = new ArrayList<String>();
        System.out.println("You have made a Player!");
    }

    /**
     * Grab an item and store it in inventory
     * 
     * @param item the item to grab
     */
    public void grab(String item) {
        this.inventory.add(item);
        this.recordAction("grab");
        this.recordArgs(item);
        System.out.println("You have grabbed a " + item);
    }

    /**
     * Drop an item and remove it from inventory. Checks to see if the item is in
     * inventory first
     * 
     * @param item the item to drop
     * @return the text that the computer will print to the user after dropping.
     */
    public String drop(String item) {
        // TO DO: use Item() class instead of String().
        String speech = "";
        if (this.inventory.contains(item)) {
            this.inventory.remove(item);
            speech = "You have dropped " + item + ".";
            this.recordAction("drop");
            this.recordArgs(item);
        } else {
            speech = item + " is not in your inventory, you cannot drop it.";
        }
        System.out.println(speech);
        return (speech);
    }

    /**
     * Examine an item.
     * 
     * @param item the item to examine
     */
    public void examine(String item) {
        String itemDescription = "This is a placeholder description for when I implement this skeleton code in my final project. In reality, this will be an attribute of the item containing a string description.";
        System.out.println(itemDescription);
    }

    /**
     * OVERLOADED TO USE MY DEFINED CLASS: Examine an item.
     * 
     * @param item the item to examine
     */
    public void examine(Item item) {
        System.out.println(item.longDesc);
    }

    /**
     * Use an item.
     * 
     * @param item the item to use
     */
    public void use(String item) {
        String result = "This is a placeholder textual result for when I implement this skeleton code in my final project.";
        System.out.println(result);
    }

    /**
     * OVERLOADED TO USE MY DEFINED CLASS:Use an item.
     * 
     * @param item the item to use
     */
    public void use(Item item) {
        System.out.println(item.use());
    }

    /**
     * Change the currentPlace.
     * 
     * @param item the direction to walk
     */
    public boolean walk(String direction) {
        boolean validDirection = this.currentPlace.connections.containsKey(direction);
        Place newPlace = this.currentPlace.moveDirection(direction);
        this.currentPlace = newPlace;
        this.exhaust(1); // walking is somewhat tiring
        if (validDirection) {
            this.recordAction("walk");
            this.recordArgs(direction);
        }
        System.out.println("Your current location is: " + this.currentPlace.name);
        return (validDirection);
    }

    /**
     * Change the currentPlace via teleportation (for after the finding of a magical
     * item).
     * 
     * @param x the x coordinate of the place to teleport to
     * @param y the y coordinate of the place to teleport to
     */
    public boolean fly(int x, int y) {
        boolean success = false;
        for (Place place : map) {
            if (place.x == x && place.y == y) {
                success = true;
                break;
            }
        }
        this.exhaust(10); // flying is very tiring
        return (success);
    }

    /**
     * Shrink the player down in order to meet certain exploration requirements
     * (small spaces, passageways, etc)
     * 
     * @return the new player's size for printing if desired.
     */
    public Number shrink() {
        if (this.playerSize == 1) { // enforce minimum size
            throw new RuntimeException();
        }
        this.playerSize -= 1;
        this.exhaust(10); // shrinking is very tiring
        this.recordAction("shrink");
        this.recordArgs("");
        return (this.playerSize);

    }

    /**
     * Grow player in order to meet certain exploration requirements (large jumps,
     * wall climbing, etc)
     * 
     * @return the new player's size for printing if desired.
     */
    public Number grow() {
        if (this.playerSize > 4) { // enforce maximum size
            throw new RuntimeException();
        }
        this.playerSize += 1;
        this.exhaust(10); // growing in size is very tiring
        this.recordAction("grow");
        this.recordArgs("");
        return (this.playerSize);
    }

    /**
     * Exhausts the player, called after certain taxing actions.
     * 
     * @param amt the amount to exhaust the player.
     */
    private void exhaust(int amt) {
        this.playerEnergy -= amt;
        if (this.playerEnergy < 5) {
            System.out.println("You are so tired that you can't stay awake. Sweet dreams!");
            this.rest();
        } else if (this.playerEnergy < 10) {
            System.out.println("Listen, you really need to sleep now.");
        } else if (this.playerEnergy < 20) {
            System.out.println("You are getting tired, please sleep.");
        }
    }

    /**
     * Rests the player (representative of sleeping), resetting their energy level
     * to full.
     */
    public void rest() {
        this.playerEnergy = 100;
    }

    /**
     * Undoes the last "undoable" action by this player. An undoable action
     * includes: grabbing / dropping an item, walking, shrinking, and growing. Any
     * other action is something that does not make sense to undo. Also adds energy
     * back to the player if exhaust() was called.
     * 
     * @param
     */
    public void undo() {
        String lastAction = this.actions.get(this.actions.size() - 1);
        String lastArg = this.actionArgs.get(this.actions.size() - 1);
        if (lastAction == "grab") {
            this.drop(lastArg);
            // Remove the last 2 (because we called an undoable action in undoing the first
            // action) elements from the actions(Args) lists
            this.actions.removeLast();
            this.actions.removeLast();
            this.actionArgs.removeLast();
            this.actionArgs.removeLast();
        } else if (lastAction == "drop") {
            this.grab(lastArg);
            this.actions.removeLast();
            this.actions.removeLast();
            this.actionArgs.removeLast();
            this.actionArgs.removeLast();
        } else if (lastAction == "walk") {
            if (lastArg == "north") {
                this.walk("south");
            } else if (lastArg == "south") {
                this.walk("north");
            } else if (lastArg == "east") {
                this.walk("west");
            } else if (lastArg == "west") {
                this.walk("east");
            }
            this.actions.removeLast();
            this.actions.removeLast();
            this.actionArgs.removeLast();
            this.actionArgs.removeLast();
            this.playerEnergy += 2; // add 2 energy back to reflect the undoing
        } else if (lastAction == "shrink") {
            this.grow();
            this.actions.removeLast();
            this.actions.removeLast();
            this.actionArgs.removeLast();
            this.actionArgs.removeLast();
            this.playerEnergy += 10; // add 10 energy back to reflect the undoing
        } else if (lastAction == "grow") {
            this.shrink();
            this.actions.removeLast();
            this.actions.removeLast();
            this.actionArgs.removeLast();
            this.actionArgs.removeLast();
            this.playerEnergy += 10; // add 10 energy back to reflect the undoing
        }
    }

    public void lookAround() {
        System.out.println(this.currentPlace);
    }

    /**
     * Records an action to the list of precent recorded actions. If the list is 10
     * long, moves every item in the list over one and adds the action in the last
     * place, thus deleting the 10th-most-recent entry
     * 
     * @param args the string name of the function that was called
     */
    private void recordAction(String action) {
        if (this.actions.size() < 10) {
            this.actions.add(action);
        } else {
            // traverse the list and move elements to right
            for (int j = 0; j < 10; j++) {
                this.actions.set(j, this.actions.get(j + 1));
            }
            this.actions.add(action);
        }
    }

    /**
     * Records the argument of the most recent action to the list of precent
     * recorded agrs. If the list is 10
     * long, moves every item in the list over one and adds the agr in the last,
     * place, thus deleting the 10th-most-recent entry. If no arg was used in the
     * function call, "" is filled in.
     * 
     * @param args the string name of the arg to the function that was called
     */
    private void recordArgs(String args) {
        if (this.actionArgs.size() < 10) {
            this.actionArgs.add(args);
        } else {
            // traverse the list and move elements to left
            for (int j = 0; j < 10; j++) {
                this.actionArgs.set(j, this.actionArgs.get(j + 1));
            }
            this.actionArgs.add(args);
        }
    }

    /**
     * Prints the 10 most recently called "undoable" actions in least recent - most
     * recent order.
     */
    public void printActions() {
        for (int i = 0; i <= this.actions.size() - 1; i++) {
            System.out.println("Action " + (i + 1) + ": " +
                    this.actions.get(i) + "(" + this.actionArgs.get(i) + ")");
        }
    }

    public static void main(String[] args) {
        Place livingRm = new Place(
                new ArrayList<Item>(
                        Arrays.asList(new Item("Coffee table"), new Item("Chair"),
                                new Item("Chair"), new Item("Chair"), new Item("Rug"),
                                new Item("Fireplace"))),
                "Living Room",
                "A living room with three chairs, a rug, and a table.",
                "A cozy living room that contains three faded red checkered chairs, a worn beige rug, and an oak coffee table, still stained with the remnants of someone's bad morning. A fireplace lies unlit against the east wall.");
        Place kitchenRm = new Place(
                new ArrayList<Item>(
                        Arrays.asList(new Item("Stove"), new Item("Sink"),
                                new Item("Pantry Cabinet"))),
                "Kitchen",
                "A kitchen with a stove, sink, and pantry cabinet",
                "A tile-floored kitchen. Against the west wall is a stove. The north wall is taken up by a large sink basin and an empty pantry cabinet");
        livingRm.addConnection("south", kitchenRm);
        Player me = new Player(livingRm, new ArrayList<Place>(Arrays.asList(kitchenRm, livingRm)));
        me.lookAround();
        me.walk("north");
        me.walk("south");
        me.grab("coffee");
        me.grab("cat!");
        me.drop("cat!");
        me.printActions();
        me.undo();
        me.printActions();
    }

}
