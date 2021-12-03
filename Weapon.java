/** A weapon. */
public class Weapon {

    /** A description of this weapon. */
    private String description;

    /** This weapon's name. */
    private String name;

    /** The value of this weapon's damage. */
    private int damage;

    /**
     * @param name        This weapon's name
     * @param damage      The value of this weapon's damage
     * @param description A description of this weapon
     */
    public Weapon(String name, int damage, String description) {
        this.name = name;
        this.description = description;
        this.damage = damage;
    }

    /** Returns a description of this weapon. */
    public String getDescription() { return description; }

    /** Returns this weapon's name. */
    public String getName() { return name; }

    /** Returns the value of this weapon's damage. */
    public int getDamage() { return damage; }
}
