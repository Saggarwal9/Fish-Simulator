/**
 * Parent class for Hook,Fish,Food classes handling all the base function
 * @author Shubham
 * @version: 1.0
 */
public class SimObject {
	protected int x;
	protected int y;
	protected PImage Image; 
	static protected PApplet processing;
	protected boolean flag= false; //flag to check whether a SimObject needs to be removed

	/**
	 * Constructor initializing the base variables having a string argument
	 * @param imagePath contains the URL for the image to be loaded in the simulator
	 */
	public SimObject(String imagePath) {
		if(processing==null) throw new IllegalStateException("SimObject.setProcessing() must be called before constructing any SimObjects.");
		Image=processing.loadImage("images" + java.io.File.separator + imagePath);
		y=Utility.randomInt(processing.height);
		x=Utility.randomInt(processing.width);

	}

	/**
	 * Overloaded constructor initializing the base variables having a string and two integer arguments.
	 * @param imagePath contains the URL for the image to be loaded in the simulator
	 * @param x contains the x-coordinate of the image to be placed
	 * @param y contains the y-coordinate of the image to be placed
	 */
	public SimObject(String imagePath, int x, int y) {
		this(imagePath);
		this.x=x;
		this.y=y;
	}

	/**
	 * initialize the PApplet reference that is used by all SimObjects
	 * @param processing is the passed data object reference
	 */
	public static void setProcessing(PApplet processing) {
		SimObject.processing=processing;
	}

	/**
	 * Base update function for Hook,Fish,Food to be overriden
	 */
	public void update() {

	}

	/**
	 * Base tryToInteract function for hook,Fish,Food to be overriden
	 * @param other contains the other object the function calling object interacts with
	 */
	public void tryToInteract(SimObject other) { 

	}

	/**
	 * returns the distance from this SimObject to position x, y
	 * @param x coordinate of the object
	 * @param y coordinate of the object
	 * @return the floating distance between the SimObject and position x,y passed
	 */
	public float distanceTo(int x, int y) { 
		float d= (float)Math.sqrt(((this.x-x)*(this.x-x)) + ((this.y-y)*(this.y-y)));
		return d;
	}

	/**
	 * Describes whether an object should stay or be removed in the simulator.
	 * @return the value of boolean variable flag
	 */
	public boolean shouldBeRemoved() {
		return flag;
	}

}
