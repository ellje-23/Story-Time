import java.util.ArrayList;

/** A room */
public class Room {

    /** This room's name. */
    private String name;

    /** A description of this room. */
    private String description;

    /** A list of this room's exits, as strings. */
    private ArrayList<String> exits;

    /** A list of this room's neighbors, as Room types. */
    private ArrayList<Room> neighbors;

    /** This room's treasure. */
    private Treasure treasure;

    /** This room's weapon. */
    private Weapon weapon;

    /** This room's monster. */
    private Monster monster;

    /**
     * @param name          This room's name
     * @param description   A description of this room
     */
    public Room(String name, String description) {
        // Initializing the room's name and description
        this.name = name;
        this.description = description;
    }

    /** Returns a description of this room. */
    public String getDescription() { return description; }

    /** Returns this room's name. */
    public String getName() { return name; }

    /** Returns the list of exits for this room as a string. */
    public String listExits() { return exits.toString(); }

    /**
     * @param direction     The direction in which the neighbor is
     * @param name          The name of the neighboring room
     */
    public void addNeighbor(String direction, Room name) {
        // Initializing the array lists if they haven't been before
        if ((exits == null) && (neighbors == null)) {
            this.exits = new ArrayList<String>();
            this.neighbors = new ArrayList<Room>();
        }
       // Adding the direction to `exits`
        exits.add(direction);

        // Adding the corresponding room of the exit direction to `neighbors`
        neighbors.add(name);
    }

    /**
     * @param direction     The direction of the neighboring room
     */
    public Room getNeighbor(String direction) {
        // Checking to see if the direction is a possible exit
        if (exits.contains(direction)) {
            // Getting the index of the direction and it's corresponding room
            int index = exits.indexOf(direction);
            return neighbors.get(index);
        }
        // Returning null as direction isn't a possibility
        else
            return null;
    }

    /**
     * Sets the room's monster.
     * @param monster           The monster's name
     */
    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    /** Gets the room's monster. */
    public Monster getMonster() {
        return monster;
    }

    /**
     * Sets the room's treasure.
     * @param treasure          The treasure's name
     */
    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }

    /** Gets the room's treasure. */
    public Treasure getTreasure() {
        return treasure;
    }

    /**
     * Sets the room's weapon.
     * @param weapon           The weapon's name
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /** Gets the room's weapon. */
    public Weapon getWeapon() {
        return weapon;
    }
}

