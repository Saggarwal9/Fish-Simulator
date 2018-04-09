import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Back-End class responsible for calling all the functions for simulation to set-up and update.
 * @author Shubham
 * @version 2.0
 */
public class SwimSimulation {
	PApplet processing;
	int fishNo;
	int foodNo;
	int[][] fishPositions;
	int[][] foodPositions;
	int[][] hookPositions;
	Fish[] fish;
	Hook hook;
	ArrayList<SimObject> object;
	String fileName=null;


	/**
	 * Constructor responsible for fetching a random file contents and assigning them to the respective variables.
	 * @param reference contains the reference to the data object passed.
	 * @Exception catches FileNotFound Exception when the file could not be found and runs a default program with 4 fishes, 6 foods and 1 hook at random positions
	 * @exception catches FileSystemException when a program doesn't load properly.
	 */
	public SwimSimulation(PApplet reference){
		processing=reference;
		SimObject.setProcessing(reference);
		try {
			fileName=getDirectory();
			updateParameters(fileName);

			fish= new Fish[fishNo];
			object= new ArrayList<SimObject>();
			for(int i=0; i<fishNo; i++)
				object.add(new Fish(fishPositions[i][0],fishPositions[i][1]));
			for(int i=0;i<foodNo;i++)
				object.add(new Food(foodPositions[i][0],foodPositions[i][1]));

			object.add(new Hook(hookPositions[0][0],hookPositions[0][1]));
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			fishNo=4;
			foodNo=6;
			fish= new Fish[4];
			object= new ArrayList<SimObject>();
			for(int i=0;i<4;i++)
				object.add(new Fish());

			for(int i=0;i<6;i++)
				object.add(new Food());

			object.add(new Hook());

		}catch(FileSystemException e) {
			System.out.println(e.getMessage());
		}


	}

	/**
	 * Responsible for simulating the Fish,Food and Hook behavior in the tank.
	 */
	public void update() {
		processing.background(0,255,255);
		int randomNumber= Utility.randomInt(20);
		if(randomNumber==0) {
			int x=Utility.randomInt(processing.width);
			object.add(new Food(x,0));
		}

		for (int i = 0; i < object.size(); i++) {
			object.get(i).update();
		}



		for(int i=0; i< object.size(); i++) {
			for(int j=0;j< object.size();j++) {
				object.get(i).tryToInteract(object.get(j));
				if(object.get(j).shouldBeRemoved()==true) {
					object.remove(j--);
				}
			}
		}

	}


	/**
	 * Calls the hook's class mouseclick event handler method.
	 * @param mouseX contains the X coordinate of the mouse-click to be passed to hook's handleClick method.
	 * @param mouseY contains the Y coordinate of the mouse-click to be passed to hook's handleClick method.
	 */
	public void handleClick(int mouseX, int mouseY) {
		for(int i=0;i<object.size();i++) {
			if(object.get(i) instanceof Hook)
				((Hook) object.get(i)).handleclick(mouseX, mouseY);
		}
	}

	/**
	 * Method responsible for fetching and returning a random SSF file containing the variable data.
	 * @return a string containing a random file directory selected from the base SSD file.
	 * @throws FileNotFoundException is thrown if the SSD file can not be found.
	 */
	public static String getDirectory() throws FileNotFoundException{
		String filePath= "FileOptisons.ssf";
		File file=new File(filePath);
		Scanner scanner= null;
		String[] primaryPath= null;
		String secondaryPath= null;
		try {
			scanner = new Scanner(file);
			String s1= scanner.nextLine();
			primaryPath= s1.split(";");
			secondaryPath=primaryPath[Utility.randomInt(primaryPath.length)];
			secondaryPath=secondaryPath.replace('\\',File.separatorChar).replace('/',File.separatorChar); //Replaces the '\' character with the file separator symbol.
			secondaryPath=secondaryPath.replace('\t',' ');//Replaces the possible TAB space before/after the file name with a space
			secondaryPath=secondaryPath.trim();
		}catch(NullPointerException e) {
			System.out.println("WARNING: Number of " + e.getMessage() + " does not match number of " + e.getMessage() + " positions. ");
		}catch(Exception e) {
			throw new FileNotFoundException("WARNING: Could not find or open the FileOptions.ssf file.");
		}
		scanner.close();
		return secondaryPath;
	}

	/**
	 * Responsible for reading the SSF file for the number and position of each fish,food and hook objects and assigning it to their respective variables.
	 * @param x contains the random file path fetched from the base SSD file.
	 * @throws FileSystemException is thrown if there's any problem reading the file.
	 * @throws FileNotFoundException is thrown if the SSF file can not be found.
	 */
	public void updateParameters(String x) throws FileSystemException,FileNotFoundException{
		int actualFood=0;
		int actualFish=0;
		File file=new File(x);
		Scanner scanner= null;
		String[] fp= null;
		String temp= null;
		String[] temp2= null;
		int inFish=0;
		int inHook=0;
		int inFood=0;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String s1= scanner.nextLine();
				//System.out.println(s1);
				fp= s1.split(":");
				fp[0]=fp[0].trim();
				fp[0]=fp[0].toLowerCase();

				switch(fp[0]) {

				case "hook": 
					inHook+=1;
					hookPositions = new int[1][2];
					temp = scanner.nextLine();
					//System.out.println("\n" +temp);
					while(temp.isEmpty())
						temp=scanner.nextLine();
					temp2=temp.split(",");
					hookPositions[0][0]=Integer.parseInt(temp2[0].trim());
					hookPositions[0][1]=Integer.parseInt(temp2[1].trim());
					break;

				case "fish":
					inFish+=1;
					fishNo=Integer.parseInt(fp[1].trim());
					fishPositions= new int[fishNo][2];
					for(int i=0;i<fishNo; i++) {
						temp = scanner.nextLine();
						temp=temp.trim();
						//System.out.println("\n" +temp);
						while(temp.isEmpty())
							temp=scanner.nextLine();
						temp2=temp.split(",");
						fishPositions[i][0]=Integer.parseInt(temp2[0].trim());
						fishPositions[i][1]=Integer.parseInt(temp2[1].trim());
						actualFish=actualFish+1;
					}
					break;

				case "food":
					inFood+=1;
					foodNo=Integer.parseInt(fp[1].trim());
					foodPositions = new int[foodNo][2];
					for(int i=0;i<foodNo; i++) {
						temp = scanner.nextLine();
						temp=temp.trim();
						//System.out.println("\n" +temp);
						while(temp.isEmpty())
							temp=scanner.nextLine();
						temp2=temp.split(",");
						foodPositions[i][0]=Integer.parseInt(temp2[0].trim());
						foodPositions[i][1]=Integer.parseInt(temp2[1].trim());
						actualFood=actualFood+1;
					}
					break;

				}


			}
			if(inFish == 0 || inFood ==0) throw new FileSystemException("Missing specification for the number and initial positions of fishes, foods, or hook."); 

		}catch(FileNotFoundException e) {
			throw new FileNotFoundException("WARNING: Could not find or open the " + x + " file.");
		}catch(NumberFormatException e) {
			System.out.println("WARNING: Failed to load objects and positions from file.");
			if(actualFood!=foodNo) throw new NullPointerException("Food");
			if(actualFish!=fishNo) throw new NullPointerException("Fish");
		}catch(Exception e) {
			System.out.println("WARNING: Failed to load objects and positions from file.");
		}

	}
}

