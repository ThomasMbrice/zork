public class StoryTreeNode {
    StoryTreeNode leftChild;
	
	StoryTreeNode middleChild;
	
	StoryTreeNode rightChild;
	
	private String position;
	private String option;
	private String message;
	
	static final String LOSE_MESSAGE = "YOU LOSE";
	static final String WIN_MESSAGE = "YOU WIN";
	int outcome = -1;
	
	public StoryTreeNode(int i) {
		outcome = i;
	}
	
	public void setConditionNode(int i) {
		outcome = 1;
	}
	public boolean isLeaf() {
		if(leftChild == null && middleChild == null && rightChild == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isWinningNode() {
		if(isLeaf() == true && outcome == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isLosingNode() {
		if(isLeaf() == true && outcome == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String m) {
		message = m;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String m) {
		position = m;
	}
	
	public String getOption() {
		return option;
	}
	public void setOption(String m) {
		option = m;
	}
	
}
