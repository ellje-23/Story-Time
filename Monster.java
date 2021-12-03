/** A monster. */
public class Monster {

    /** A description of this monster. */
    private String description;

    /** This monster's name. */
    private String name;

    /** The value of this player's armor. */
    private int armor;

    /**
     * @param name        This monster's name
     * @param armor       The value of this player's armor
     * @param description A description of this monster
     */
    public Monster(String name, int armor, String description) {
        this.name = name;
        this.description = description;
        this.armor = armor;
    }

    /** Returns a description of this monster. */
    public String getDescription() { return description; }

    /** Returns this monster's name. */
    public String getName() { return name; }

    /** Returns the value of this player's armor. */
    public int getArmor() { return armor; }
}