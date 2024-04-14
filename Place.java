import java.util.ArrayList;
import java.util.Hashtable;

public class Place {
    public ArrayList<Item> items; // an arraylist of interactable objects within a given place
    public Hashtable<String, Place> connections;
    public String name;
    public String shortDesc;
    public String longDesc;
    public int x; // the x coord of a given Place within the overall grid of places
    public int y; // the y coord of a given Place within the overall grid of places



    public Place(ArrayList<Item> items,
                String name, String shortDesc, String longDesc) {
        this.items = items; 
        this.connections = new Hashtable<String, Place>(); 
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
    }

    public Place moveDirection(String direction) {
        if (this.connections.containsKey(direction)) {
            System.out.println("You have moved " + direction);
            return(this.connections.get(direction)); //return the place that is in that direction
        } else {
            System.out.println(direction + " is not a valid direction. Try looking around again to see where you might go!");
            return(this);
        }
    }
    public String toString(){
        String walls = "";
        for (String direction : this.connections.keySet()) {
            walls += direction + "\n";
        }
        return(this.longDesc + "This " + this.name + " has doorways on the following walls: \n" + walls);
    }

    public void addConnection(String direction, Place place) {
        this.connections.put(direction, place);
    }
}
