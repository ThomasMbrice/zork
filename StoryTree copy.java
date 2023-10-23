package HW5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StoryTree {
    StoryTreeNode root;
	
	StoryTreeNode cursor;
	
	GameState state;
	
	public StoryTree() {
		root = new StoryTreeNode(-1);
		root.setMessage("Hello, welcome to Zork!");
		root.setPosition("root");
		root.setOption("root");
		cursor = root;
		state = GameState.GAME_NOT_OVER;
	}
	
	public static StoryTree readTree(String filename) throws FileNotFoundException { 
		    
		try {
			File file = new File(filename);
            Scanner reader = new Scanner(file);
			StoryTree t1 = new StoryTree();
            while (reader.hasNext()) {
                String s1 = reader.nextLine();
				System.out.println(s1);
				int[] i1 = new int[2];
				int counter = 0;
				for(int i = 0; i < s1.length()-1; i++){
					if(s1.charAt(i) == '|'){
					i1[counter] = i;
					counter++;
					}
				}
				String pos, opt, mes;
				pos = s1.substring(0,i1[0]);
				opt = s1.substring(i1[0], i1[1]);
				mes = s1.substring(i[1], s1.length());
				int cont = loseCont(mes);
				cont = winCont(mes);
				StoryTreeNode s2 = new StoryTreeNode(cont);
				s2.setPosition(pos);
				s2.setMessage(mes);
				s2.setOption(opt);
				t1 = authNode(s2, t1);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR! Reading reservation file!");
            e.printStackTrace();
        }
		return t1;
	}
	
	public static void saveTree(String filename, StoryTree tree) throws IOException { 
		File Old_File=new File(filename);
		Old_File.delete();
		FileWriter New_File = new FileWriter(filename);
		String newContent;
		//System.out.println("L" + newContent);

	try {
		StoryTreeNode p1 = tree.root;
		FileWriter Overwritten_File = new FileWriter(New_File, false);
		newContent =  p1.middleChild.getPosition() + "|" + p1.middleChild.getOption() +"|" + p1.middleChild.getMessage() + "\n";
		p1 = p1.middleChild;
		int e = 0;
		newContent = recursiveStringer(tree, p1, e);
		Overwritten_File.write(newContent);
        Overwritten_File.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		w.close();
	}
	
	public GameState getGameState() {
		return state;
	}
	public String getCursorPosition() {
		return cursor.getPosition();
	}
	public String getCursorMessage() {
		return cursor.getMessage();
	}
	public String[][] getOptions(){
		String[][] opp = new String[3][1];
		opp[0][0] = cursor.leftChild.getPosition();
		opp[0][1] = cursor.leftChild.getOption();
		opp[1][0] = cursor.middleChild.getPosition();;
		opp[1][1] = cursor.middleChild.getOption();
		opp[2][0] = cursor.rightChild.getPosition();
		opp[2][1] = cursor.rightChild.getOption();
		return opp;
		
	}
	
	public void setCursorMessage(String message) {
		cursor.setMessage(message);
	}
	public void setCursorOption(String option) {
		cursor.setOption(option);
	}
	public void resetCursor() {
		cursor = root;
	}
	public void setCondition(int i) {
		if(cursor.isLeaf() != true) {
			if(i == 0) {
				cursor.setConditionNode(i);
			}
			if(i == 1) {
				cursor.setConditionNode(i);
			}
				}
		}
	public void startStory() {
	resetCursor();
	cursor = cursor.middleChild;
	}

	public void selectChild(String position) {
		int i = position.length();
		char ch = position.charAt(i);
		int in = ch-0;
			if(in == 1) {
				cursor = cursor.leftChild;
			}
			else {
				if(in == 2) {
					cursor = cursor.middleChild;
				}
				else {
					if(in == 3) {
						cursor = cursor.rightChild;
					}
				}
			}
	}
	
	public void addChild(String option, String message, int i) {
		int top = 0;
		StoryTreeNode newNode = new StoryTreeNode(i);
		newNode.setMessage(message);
		newNode.setOption(option);
		
		if(cursor.leftChild == null) {
			cursor.leftChild = newNode;
			cursor.leftChild.setPosition(cursor.getPosition() + "-1");
			top++;
		}
		else {
		if(cursor.middleChild == null) {
			cursor.middleChild = newNode;
			cursor.middleChild.setPosition(cursor.getPosition() + "-2");
			top++;
			}
		else {
			if(cursor.rightChild == null) {
				cursor.rightChild = newNode;
				cursor.rightChild.setPosition(cursor.getPosition() + "-3");
				top++;
				}
			}
		}
		if(top == 0) {
			System.out.println("Error tree is full");  														//error message
		}
		if(top == 1) {
			System.out.println("Child added.");
		}
		
	}
	
	public StoryTreeNode removeChild(String position) {
		char ch = cursor.getPosition().charAt(cursor.getPosition().length());
		StoryTreeNode newNode = null;
		int in = ch - 0;
		int i = 0;
		if(in == 1) {
			newNode = cursor.leftChild;
			cursor.leftChild = null;
			i++;
		}
		else {
			if(in == 2) {
				newNode = cursor.middleChild;
				cursor.middleChild = null;
				i++;
			}
			else {
				if(in == 3) {
					newNode = cursor.rightChild;
					cursor.rightChild = null;
					i++;
				}
			}
		}
		if(i ==0) {
			return null;
		}
		else {
			return newNode;
		}
	}
	
	public static StoryTree stepToTheLeft(StoryTree t1, StoryTreeNode n1, int z) {		
		if(z = 1){						
		n1 = t1.root;
		n1 = n1.middleChild;
		z = 0;
		}
		if(n1.leftChild == null && (n1.middleChild != null || n1.rightChild != null)){
			if(n1.middleChild != null){
				n1.leftChild = n1.middleChild;
				String st = n1.leftChild.getPosition();
				st = st.substring(0, st.length() -2);
				st = st.concat("-1");
				n1.leftChild.setPosition(st);
				if(n1.rightChild != null){
					n1.middleChild = n1.rightChild;
					String std = n1.middleChild.getPosition();
					std = std.substring(0, std.length() -2);
					std = std.concat("-2");
					n1.middleChild.setPosition(std);
					StoryTreeNode z1 = n1.middleChild;
					stepToTheLeft(t1, z1,0);
				}
			}
			n1 = n1.leftChild;
			stepToTheLeft(t1, n1,0);
		}
	}
	
	
	public static int loseCont(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(i+4 <= s.length()) {
				String st = s.substring(i, i+4);
				st = st.toLowerCase();
				if(st.equals("lose")) {
					return 1;
				}
			}
		}
		return -1;
	
	}

	public static int winCont(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(i+3 <= s.length()) {
				String st = s.substring(i, i+3);
				st = st.toLowerCase();
				if(st.equals("win")) {
					return 0;
				}
			}
		}
		return -1;
	}

	private static StoryTree authNode(StoryTreeNode s1, StoryTree t1){
		t1.cursor = t1.root;
		if(t1.cursor.middleChild == null){
			t1.cursor.middleChild = s1;
			return t1;
		}
		else{
			String s = s1.getPosition();									// find parent????
			for(int i = 2; i < s.length()-1; i++){
				String z = s.charAt(i);
				t1.cursor = t1.selectChild(s);
				i++;
			}
			char z = s.charAt(s.length());
			String z1 = z-0;
			if(z1 == 1){
				t1.cursor.leftChild = s1;
			}
			if(z1 == 2){
				t1.cursor.middleChild = s1;
			}
			if(z1 == 3){
				t1.cursor.rightChild = s1;
			}
		}
		return t1;
	}

	private static String recursiveStringer(StoryTree t1, StoryTreeNode p1, int con){
		String bigString;
		if(p1.leftChild != null || check == 0){
			bigString = p1.leftChild.getPosition() + "|" + p1.leftChild.getOption() + "|" + p1.leftChild.getMessage() + "\n";
			if(p1.leftChild.leftChild != null){
				p1 = p1.leftChild;
				recursiveStringer(t1, p1, con);
			}
			else{
				if(p1.middleChild != null){
					bigString = p1.middleChild.getPosition() + "|" + 
					p1.middleChild.getOption() + "|" + p1.middleChild.getMessage() + "\n";
					p1 = p1.middleChild;
					recursiveStringer(t1, p1, con);
				}
				else{
					if(p1.rightChild != null){
					bigString = p1.rightChild.getPosition() + "|" + 
					p1.rightChild.getOption() + "|" + p1.rightChild.getMessage() + "\n";
					p1 = p1.rightChild;
					recursiveStringer(t1, p1, con);
					}
				}
			}
		}
		return bigStirng;
	}
}
