/**
 * Graphical program resembling a fish-tank, consisting of fish, fish food and a hook.
 * @author Shubham Aggarwal
 * Version: 3.0
 */
public class Main {
	private static SwimSimulation s1;

	/**
	 * Starts the simulation
	 * @param args contains the supplied command-line arguments
	 */
	public static void main(String[] args) {
		Utility.startSimulation();
	}



	/**
	 * Sets up the position of the objects in the tank.
	 * @param d1 is the Data object used by the JAR file.
	 */
	public static void setup(Data d1) {
		s1=new SwimSimulation(d1.processing);	
	}

	/**
	 * Updates the position of the objects in the tank.
	 * @param d1 is the Data object used by the JAR file.
	 */

	public static void update(Data d1){
		s1.update();	
	}

	/**
	 * Called by Utility function to update mouse coordinates when clicked.
	 * Calls handeClick method of a static SwimSimulation object.
	 * @param data contains the hook position array to be updated.
	 * @param mouseX contains the X-Coordinate of the mouse.
	 * @param mouseY contains the Y-Coordinate of the mouse.
	 */
	public static void onClick(Data data, int mouseX, int mouseY)
	{
		s1.handleClick(mouseX, mouseY);
	}

}
