public class Hook extends SimObject{


	public Hook() {
		super("HOOK.png");
	}

	/**
	 * Constructor assigning specific X and Y Coordinates
	 * @param x contains the x coordinate of the food to be placed and moved
	 * @param y contains the y coordinate of the food to be placed and moved
	 */
	public Hook(int x, int y) {
		super("HOOK.png",x,y);
	}

	/**
	 * Responsible for decrementing Hook position and placing it.
	 */
	@Override
	public void update() {
		move();
		place();
	}

	/**
	 * Places the hook on the screen.
	 */
	public void place() {
		processing.image(Image, x, y);
		processing.line(x+4, 0, x+4, y-12); //x+4 and y-12 to match the line with the center of the hook
	}

	/**
	 * Responsible for showing the hook moving. Wraps around to the end of window's vertical height when it reaches the top.
	 */
	public void move() {
		y+= -(processing.height + 50 - y)/50;
		if(y<0) {
			y=processing.height-1;
		}
		if(x>processing.width)
			x=0;
	}

	/**
	 * responsible for handling the mouseClickEvent
	 * @param mouseX contains the X-Coordinate of the mouseClick
	 * @param mouseY contains the Y-Coordinate of the mouseClick
	 */
	public void handleclick(int mouseX,int mouseY) {
		x=mouseX;
		y=mouseY;
	}

	/**
	 * Calculates the distance between the hook and fish, and re-spawns the food if the distance is less than or equal to 40.
	 * @param fish references the fish object passed.
	 */
	@Override
	public void tryToInteract(SimObject fish) {
		if(fish instanceof Fish && fish.distanceTo(x, y)<=40)
			((Fish)fish).getCaught();
	}
}


