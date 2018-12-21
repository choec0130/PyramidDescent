import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PuzzleSolver {
	private static int product;
	private static Node rootNode;
	private static StringBuilder sb;
	public static void main(String[] args) {
		System.out.println("Add the path to your .txt file.");
		Scanner inputScanner = new Scanner(System.in);
		String filePath = inputScanner.nextLine();
		File file = new File(filePath);
		while (!file.exists()) {
			System.out.println("File doesn't exist. Try again.");
			String retryFilePath = inputScanner.nextLine();
			file = new File(retryFilePath);
		}
		sb = new StringBuilder();

	    try {

	    	Scanner myScanner = new Scanner(file);
	    	String targetString = myScanner.nextLine();
	    	//System.out.println(targetString);
	    	product = Integer.parseInt(targetString.replaceAll("[\\D]", ""));
	    	rootNode = new Node(Integer.parseInt(myScanner.nextLine()));
	
	    	List<Node> nodes = new LinkedList<Node>();
	    	nodes.add(rootNode);
	    	int index = 0;
	    	Node currentNode = nodes.get(index);

	        while (myScanner.hasNextLine()) {
	
	        	String line = myScanner.nextLine();
	        	
	        	line = line.replaceAll("[^0-9]+", " ");
	        	
	        	//row has all the elements in that particular row of the tree
	        	List<String> row = Arrays.asList(line.trim().split(" "));
		    	for (int i = 0; i < row.size(); i++) {
		    		Node thisNode = new Node(Integer.parseInt(row.get(i)));
		    	
		    		if (currentNode.left == null) {
		    			currentNode.left = thisNode;
		    		
		    		} else if(currentNode.right == null){
		    			currentNode.right = thisNode;
		    			
		    		}
		    		nodes.add(thisNode);
		    		if(currentNode.left != null && currentNode.right != null && i != 0 && i != row.size()-1) {
		    			index++;
		    			currentNode = nodes.get(index);
		    			currentNode.left = thisNode;
		    		
		    		} else if(currentNode.left != null && currentNode.right != null){ //we are at the first node in the row
		    			index++;
		    			currentNode = nodes.get(index);
		    			
		    			
		    		}
		    	}
		    
	           
	        }
	        myScanner.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	  
	    search(rootNode, product, rootNode.val, sb);
	  
	   
	} 
		
	static class Node {
		int val;
		Node left;
		Node right;
		Node(int val) {
			this.val = val;
		}
	}
	
	static String search(Node root, int target, int currentProduct, StringBuilder string) {
	
		if(target == currentProduct) {
			System.out.println(string.toString());
			return string.toString();
		} 
		if(root.left != null){
			
			search (root.left, target, currentProduct * root.left.val, new StringBuilder(string.toString() + "L"));
			
		} 
		if(root.right != null) {
	
			search (root.right, target, currentProduct * root.right.val, new StringBuilder(string.toString() + "R"));
			
		} 
		
		return null;
	}
	
}
