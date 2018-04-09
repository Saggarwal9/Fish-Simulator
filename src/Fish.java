/**
 * Class that handles all the fish related methods and variables.
 * @author Shubham Aggarwal
 * Version: 2.0
 */
public class Fish extends SimObject {

	/**
	 * Constructor assigning random X and Y coordinates
	 * @param reference is the passed data object reference
	 */
	public Fish() {
		super("FISH.png");

	}

	/**
	 * Constructor assigning the x and y position
	 * @param reference contains the reference to the data object
	 * @param x contains the X-Coordinate of the fish to be placed and moved.
	 * @param y contains the Y-Coordinate of the fish to be placed and moved.
	 */
	public Fish(int x, int y) {
		super("FISH.png",x,y);
	}

	/**
	 * Responsible for incrementing and updating fish position in the tank..
	 */
	@Override
	public void update() {
		moveFish();
		placeFish();
	}

	/**
	 * Responsible for placing the fishes.
	 */
	public void placeFish() {
		processing.image(Image, x, y);
	}

	/**
	 * Responsible for representing the fish movement. Wraps the fish around when it reaches to one end to the other end.
	 */
	public void moveFish() {
		x+= 1;
		if (x >= processing.width) {
			x = 0;
		} 
	}

	/**
	 * Calculates distance between the fish and food and re-spawns the food if the distance is less than or equal to 40.
	 * @param food is the food class object.
	 */
	@Override
	public void tryToInteract(SimObject food) {
		if(food instanceof Food && food.distanceTo(x,y)<=40)
			((Food)food).getEaten();
	}


	/**
	 * moves this fish to a random position along the left edge of the screen
	 */
	public void getCaught() {
		x=0;
	} 
}



