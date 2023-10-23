package HW5;

import java.io.IOException;
import java.util.Scanner;

public class Zork {
    static Scanner input = new Scanner(System.in);
	static String data;
	static StoryTree tree;
	static int control = 0;
	
	public static void main(String[] args) throws IOException {
		if(control == 0) {
		System.out.println("Hello and Welcome to Zork!");
		System.out.println();
		System.out.println("Please enter the file name:");
		
		data = input.nextLine();
		tree = new StoryTree();
		tree = StoryTree.readTree(data);
		System.out.println();
		System.out.println("Loading game from file...");
		System.out.println();
		System.out.println("File loaded!");
		System.out.println();
		}
		control = 1;
		while(true) {
			System.out.println("Would you like to edit (E), play (P) or quit (Q)?");
            if(input.hasNext()){
			String s = input.nextLine();
			s.toLowerCase();
			char ch = s.charAt(0);
			switch(ch) {
			case'q':
				System.out.println("Game being saved to " + data + "...");
				StoryTree.saveTree(data, tree);
				System.out.println();
				System.out.println("Save successful!");
				System.out.println();
				System.out.println("Program terminating normally.");
				System.exit(0);
				break;
			case'e': 
				editTree();
				break;
			case'p': 
				playTree();
				break;
			default: 
				System.out.println("invalid option");
				break;
			    }
		    }
        }
	}
	
	public static void menu() {
		System.out.println();
		System.out.println("V: View the cursor's position, option and message.");
		System.out.println("S: Select a child of this cursor (options are 1, 2, and 3).");
		System.out.println("O: Set the option of the cursor.");
		System.out.println("M: Set the message of the cursor.");
		System.out.println("A: Add a child StoryNode to the cursor.");
		System.out.println("D: Delete one of the cursor's children and all its descendants.");
		System.out.println("R: Move the cursor to the root of the tree.");									 
		System.out.println("Q: Quit editing and return to main menu.");
		System.out.println();
		System.out.println("Select a menu option: ");
	}
	
	public static void playTree() {
		System.out.println("Let's play!");
		tree.startStory();
		while(tree.cursor.isLeaf() != true) {
			System.out.println(tree.cursor.getOption());
			String b [][] = tree.getOptions();
			int ex = 0;
			if(b[0][1] != null) {
				System.out.println("1)" + b[0][1]);
				ex = 1;
			}
			if(b[1][1] != null) {
				System.out.println("2)" + b[1][1]);
				ex = 2;
			}
			if(b[2][1] != null) {
				System.out.println("3)" + b[2][1]);
				ex = 3;
			}
			System.out.println("Please make a choice.");
			int in = input.nextInt();	
			if(in < 0 || in > ex) {
				System.out.println("invalid option");
			}
			else {
				String pos = tree.cursor.getPosition().concat("-" + in);
				tree.selectChild(pos);
			if(tree.cursor.isWinningNode()) {
				System.out.println(tree.getCursorMessage());
			}
			if(tree.cursor.isLosingNode()) {
				System.out.println(tree.getCursorMessage());
				}
			}
		}
	}
	
	public static void editTree() {
		int exit = 0;
		while(exit != 1) {
			menu();
			String s = input.nextLine();
			s.toLowerCase();
			char ch = s.charAt(0);
			switch(ch) {
			case'v':
				System.out.println();
				System.out.println("Position: " + tree.cursor.getPosition());
				System.out.println("Option: " + tree.cursor.getOption());
				System.out.println("Message:" + tree.cursor.getMessage());
				break;
			case's':
				int bigarr1[] = optionShow();  
				System.out.println("Please select a child: " + bigarr1.toString());				
				int in = input.nextInt();
				if(in > 3 || in < 0) {
					System.out.println("Error. No child " + in +" for the current node.");
				}
				else {
					tree.selectChild(tree.cursor.getPosition().concat("-"+in));
				}
				break;
			case'o':
				System.out.println("Please enter a new option:");
				String st = input.nextLine();
				tree.cursor.setOption(st);
				break;
			case'm':
				System.out.println("Please enter a new Message:");
				String std = input.nextLine();
				tree.cursor.setMessage(std);
				int z =winCont(std);
				z = loseCont(std);
				tree.setCondition(z);
				break;
			case'a':													//how wil first node be added to midlle to make psudeo node
				System.out.println("Enter an option:");
				String nf = input.nextLine();
				System.out.println("Enter a message:");
				String nfl = input.nextLine();
				System.out.println();
				int ine = winCont(nfl);
				ine = loseCont(nfl);
				tree.addChild(nf, nfl, ine);
				break;
			case'd':
				int bigarr2[] = optionShow();
				System.out.println("Please select a child: " + bigarr2.toString());				 
				int ze = input.nextInt();
				if(ze > 3 || ze < 0) {
					System.out.println("Error. No child " + ze +" for the current node.");
				}
				System.out.println();
				System.out.println("Subtree deleted.");
				tree.removeChild(tree.cursor.getPosition().concat("-" + ze)); 		
				StoryTreeNode n1;
				tree = stepToTheLeft(tree, n1, 1);
				break;
			case'r':
				tree.resetCursor();
				if(tree.cursor.middleChild != null) {
					tree.cursor = tree.cursor.middleChild;
				}
				System.out.println("Cursor moved to root.");
				break;
			case'q':
				exit = 1;
				break;
			default:
				System.out.println("invalid option");
				break;
			}
		}
	}

	public static int[] optionShow() {
		int op = 0;
		if(tree.cursor.leftChild != null) {
			op++;
		}
		if(tree.cursor.middleChild != null) {
			op++;
		}
		if(tree.cursor.rightChild != null) {
			op++;
		}
		int[] i = new int[op];
		for(int e = 0; e < op; e++) {
			i[e] = e+1;
		}
		return i;
	}
	
}
