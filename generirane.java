package telerik;

import java.util.Random;
import java.util.Scanner;



public class generirane {
	public boolean isVisited[][] = new boolean[7][7];
	public char maze[][] = new char[7][7];
	public int playersCurrentRow;
	public int playersCurrentColumn;
	public String command;
	public boolean isExit = false;
	public int playersMovesCount = 0;
	HighScoreBoard board;
	

	void initializeMaze(){
		Random randomgenerator = new Random();	
		// Generates a new maze until at least one solution is found
		do{
		for(int row=0;row<7;row++){
			for(int column=0;column<7;column++){
				isVisited[row][column]=false;
				if(randomgenerator.nextInt(2)==1){
					maze[row][column] = 'X';
				}
				else {
					maze[row][column] = '-';
				}
			}
		}
		}
		while(isSolvable(3, 3)==false);
		playersCurrentRow = 3;
		playersCurrentColumn = 3;
		
		
		maze[playersCurrentRow][playersCurrentColumn] = '*';
		printMaze();
	}	
	public void initializeScoreBoard(){
		board = new HighScoreBoard();
	}	
	
	
	public boolean isSolvable(int row, int col){
		if((row==6)||(col==6)||(row==0)||(col==0)){
			isExit = true;
			return isExit;
		}
		int[] row_values = {-1, 1, 0, 0};
		int[] col_values = {0, 0, -1, 1};
			
		for(int i = 0; i < 4; i++){
			if((maze[row+row_values[i]][col+col_values[i]]=='-')){
				if((isVisited[row+row_values[i]][col+col_values[i]]==false)) {
					isVisited[row][col] = true;
					isSolvable(row+row_values[i], col+col_values[i]);
				}
			}
		}
		return isExit;
	}
	void printMaze(){
		for(int row=0;row<7;row++){
			for(int column=0;column<7;column++){
				System.out.print(maze[row][column]+" ");
			}
			{
				System.out.println();
			}
		}
	}	
	public void inputCommand(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your next move : L(left), " +
				"R(right), U(up), D(down) ");
		command = scanner.next();
		int size = command.length();
			if(command.equals("restart")){
                isExit = false;
                initializeMaze();
            }
            else if(command.equals("top")){
                if(board.list.size()>0){
                    board.printBoard(board.list);
                }
                else{
                    System.out.println("The High score board is empty!");
                }
            }
            else if(size>1){
                System.out.println("Invalid command!");
            }
			
            else if (command.equals("exit")) {
    			System.out.println("Good bye!");
    			System.exit(0);
    		}
			
            else {
                movePlayer(command.charAt(0));
            } 
	}	
	public  void movePlayer(char firstLetter){
		String valid_input = "LlRrUuDd";
		int[] direction_changes_LR = {-1, 1, 0, 0};
		int[] direction_changes_UD = {0, 0, -1, 1};
		int letter_value = valid_input.indexOf(firstLetter);
		
		int directionX = 0;
		int directionY = 0;
		
		if (letter_value >= 0){
			directionX = direction_changes_LR[letter_value/2];
			directionY = direction_changes_UD[letter_value/2];
			if (maze[playersCurrentRow+directionY][playersCurrentColumn + directionX] != 'X') {
				swapCells(playersCurrentRow, playersCurrentRow + directionY,
						playersCurrentColumn, playersCurrentColumn + directionX);
				playersCurrentColumn += directionX;
				playersCurrentRow += directionY;
				playersMovesCount++;
			}
		}
		else{
			System.out.println("Invalid move!");
		}
	}
		
	
	void swapCells(int currentRow, int newRow, int currentColumn, int newColumn){
		boolean evaluate=true;//evaluate()
		if(evaluate) {
			char previousCell = maze[currentRow][currentColumn];
			maze[currentRow][currentColumn] = maze[newRow][newColumn];
			maze[newRow][newColumn] = previousCell;
			System.out.println();
			printMaze();
		}
	}
}