/**
 * Class that handles all the food related methods and variables.
 * @author Shubham Aggarwal
 * Version: 2.0
 */
public class Food extends SimObject{
	static String imagePath="FOOD.png";

	/**
	 * Constructor assigning random X and Y coordinates
	 * @param reference contains the data object passed.
	 */
	public Food() {
		super("FOOD.png");
	}
	/**
	 * constructor assigning specific X and Y coordinates
	 * @param reference contains the data object passed.
	 * @param x contains the x coordinate of the food to be placed and moved
	 * @param y contains the y coordinate of the food to be placed and moved
	 */
	public Food(int x, int y) {
		super("FOOD.png",x,y);
	}

	/**
	 * Responsible for incrementing food position and calling the placing function.
	 */
	@Override
	public void update() {
		move();
		place();
	}


	/**
	 * Places the food in the tank
	 */
	public void place() {
		processing.image(Image, x, y);
	}

	/**
	 * moves the food in the tank
	 */
	public void move() {
		x+= -1;
		if (x < 0) {
			x = (processing.width - 1);
		}
		y+= 1;
		if (y > processing.height-1)
			y=0;
	}


	/**
	 * moves this food to a random position at the top of the screen after getting eaten.
	 */
	public void getEaten() {
		super.flag=true;
	} 
}
