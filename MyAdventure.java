/** Text adventure game. */
public class MyAdventure {

	public static void main(String[] args) {
		new MyAdventure().run();
	}

	/** Damage done by the best weapon the player has picked up. */
	private int bestWeaponDamage;

	/** The room where the player currently is. */
	private Room currentRoom;

	/** Total value of all treasures taken by the player. */
	private int score;

	public MyAdventure() {
		// Create rooms
		Room cafeteria = new Room("The Rat",
				"a musty college dining hall, filled with leftover moldy food and nightmares about finding a table");
		Room chemistryLab = new Room("chemistry lab", "a deserted chemistry lab with the professor's notes on organics crossed out and the word 'basic' scribbled over");
		Room frontOffice = new Room("Rhodes Express", "the old front office with the secretary's head on the table still there to greet you, but without her body");
		Room residenceHall = new Room("Trezevant 215",
				"a ironically small triple dorm room decorated with posters from Vine, one half exposed to the elements and the other covered in brains anf guts");

		// Create connections
		frontOffice.addNeighbor("east", residenceHall);
		residenceHall.addNeighbor("west", frontOffice);
		residenceHall.addNeighbor("south", cafeteria);
		residenceHall.addNeighbor("north", chemistryLab);
		cafeteria.addNeighbor("north", residenceHall);
		cafeteria.addNeighbor("east", chemistryLab);
		chemistryLab.addNeighbor("south", residenceHall);
		chemistryLab.addNeighbor("west", cafeteria);

		// Create and install monsters
		chemistryLab.setMonster(new Monster("zombie", 4, "a human-eating, decaying zombie"));
		residenceHall.setMonster(new Monster("leaf-blower", 2, "a treacherous leaf-blower, decapitating anything that crosses its path"));
		cafeteria.setMonster(new Monster("lunch-timer", 4, "a fear-instilling lunch-timer, that spits goo on prey, causing them to become stuck forever and slowly disintegrate"));

		// Create and install treasures
		chemistryLab.setTreasure(new Treasure("diamond", 20,
				"a huge, glittering diamond that was manufactured in the lab as an experiment"));
		residenceHall.setTreasure(new Treasure("AlooirPods", 30,
				"AirPods, an Apple product to muffle the screams at night"));
		cafeteria.setTreasure(new Treasure("cookie", 50,
				"a delectable cookie plated with gold to make your wallet sing as much as your stomach"));

		// Create and install weapons
		chemistryLab.setWeapon(new Weapon("bomb", 10, "an extremely reactive hydrogen bomb"));
		cafeteria.setWeapon(new Weapon("knife", 7, "an old knife"));
		residenceHall.setWeapon(new Weapon("scissors", 5, "a pair of sharp scissors"));

		// The player starts at Rhodes Express, armed with a letter opener
		currentRoom = frontOffice;
		bestWeaponDamage = 3;
	}

	/**
	 * Attacks the specified monster and prints the result. If the monster is
	 * present, this either kills it (if the player's weapon is good enough) or
	 * results in the player's death (and the end of the game).
	 */
	public void attack(String name) {
		Monster monster = currentRoom.getMonster();
		if (monster != null && monster.getName().equals(name)) {
			if (bestWeaponDamage > monster.getArmor()) {
				StdOut.println("You strike it dead!");
				currentRoom.setMonster(null);
			} else {
				StdOut.println("Your blow bounces off harmlessly.");
				StdOut.println("The " + monster.getName() + " eats your head!");
				StdOut.println();
				StdOut.println("GAME OVER");
				System.exit(0);
			}
		} else {
			StdOut.println("There is no " + name + " here.");
		}
	}

	/** Moves in the specified direction, if possible. */
	public void go(String direction) {
		Room destination = currentRoom.getNeighbor(direction);
		if (destination == null) {
			StdOut.println("You can't go that way from here.");
		} else {
			currentRoom = destination;
		}
	}

	/** Handles a command read from standard input. */
	public void handleCommand(String line) {
		String[] words = line.split(" ");
		if (currentRoom.getMonster() != null
				&& !(words[0].equals("attack") || words[0].equals("look"))) {
			StdOut.println("You can't do that with unfriendlies about.");
			listCommands();
		} else if (words[0].equals("attack")) {
			attack(words[1]);
		} else if (words[0].equals("go")) {
			go(words[1]);
		} else if (words[0].equals("look")) {
			look();
		} else if (words[0].equals("take")) {
			take(words[1]);
		} else {
			StdOut.println("I don't understand that.");
			listCommands();
		}
	}

	/** Prints examples of possible commands as a hint to the player. */
	public void listCommands() {
		StdOut.println("Examples of commands:");
		StdOut.println("  attack zombie");
		StdOut.println("  go north");
		StdOut.println("  look");
		StdOut.println("  take diamond");
	}

	/** Prints a description of the current room and its contents. */
	public void look() {
		StdOut.println("You are in " + currentRoom.getDescription() + ".");
		if (currentRoom.getMonster() != null) {
			StdOut.println("There is "
					+ currentRoom.getMonster().getDescription() + " here.");
		}
		if (currentRoom.getWeapon() != null) {
			StdOut.println("There is "
					+ currentRoom.getWeapon().getDescription() + " here.");
		}
		if (currentRoom.getTreasure() != null) {
			StdOut.println("There is "
					+ currentRoom.getTreasure().getDescription() + " here.");
		}
		StdOut.println("Exits: " + currentRoom.listExits());
	}

	/** Runs the game. */
	public void run() {
		listCommands();
		StdOut.println();
		while (true) {
			StdOut.println("You are in the " + currentRoom.getName() + ".");
			StdOut.print("> ");
			handleCommand(StdIn.readLine());
			StdOut.println();
		}
	}

	/** Attempts to pick up the specified treasure or weapon. */
	public void take(String name) {
		Treasure treasure = currentRoom.getTreasure();
		Weapon weapon = currentRoom.getWeapon();
		if (treasure != null && treasure.getName().equals(name)) {
			currentRoom.setTreasure(null);
			score += treasure.getValue();
			StdOut.println("Your score is now " + score + " out of 100.");
			if (score == 100) {
				StdOut.println();
				StdOut.println("YOU WIN!");
				System.exit(0);
			}
		} else if (weapon != null && weapon.getName().equals(name)) {
			currentRoom.setWeapon(null);
			if (weapon.getDamage() > bestWeaponDamage) {
				bestWeaponDamage = weapon.getDamage();
				StdOut.println("You'll be a more effective fighter with this!");
			}
		} else {
			StdOut.println("There is no " + name + " here.");
		}
	}

}
