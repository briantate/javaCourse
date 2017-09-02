package whackamole;

import java.util.Scanner;
import java.util.Random;

public class WhackAMole {
	//variables
	public int score = 0;
	public int molesLeft;
	public int attemptsLeft;
	public int gridDimension;
	public char[][] moleGrid;
	
	//game constructor
	WhackAMole(int numAttempts, int gridDimension) {
		attemptsLeft = numAttempts;
		this.gridDimension = gridDimension;
		moleGrid = new char[gridDimension][gridDimension];
		for(int i = 0; i<gridDimension; i++) {
			for(int j = 0; j<gridDimension; j++) {
				moleGrid[j][i] = '*';
			}
		}
		
	}
	
	//Methods
	public boolean place(int x, int y) {		
		if(moleGrid[x][y] == '*') {
			System.out.print("place a mole at x:" + x + " y:" + y + "\r\n");
			moleGrid[x][y] = 'M';
			molesLeft++;
			return true;
		}
		else {
			System.out.print("can't place\r\n");
			return false;
		}
			
	}
	
	public void whack(int x, int y) {
		//System.out.print("whacking...\r\n");
		if(moleGrid[x][y] == 'M'){
			System.out.print("Nice Whack!\r\n");
			moleGrid[x][y] = 'W';
			score++;
			molesLeft--;
		}
		else {
			System.out.print("You missed!\r\n");
		}
		attemptsLeft--;
		System.out.print(attemptsLeft + " attempts left\r\n" + molesLeft + " moles left\r\n");
		
	}
	
	public void printGridToUser() {
		//print grid without exposing moles
		//System.out.print("print grid to user\r\n");
		for(int i = 0; i<moleGrid.length; i++) {
			for(int j = 0; j<moleGrid.length; j++) {
				if(moleGrid[j][i] == 'W')
					System.out.print(moleGrid[j][i] + " ");
				else
					System.out.print("* ");
			}
			System.out.print("\r\n");
		}
	}
	
	public void printGrid() {
		//print the full array
		//System.out.print("print grid full array\r\n");
		for(int i = 0; i<moleGrid.length; i++) {
			for(int j = 0; j<moleGrid.length; j++) {
				System.out.print(moleGrid[j][i] + " ");
			}
			System.out.print("\r\n");
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean gameOver = false;
		int x = 0,y = 0;
		int attempts = 50;
		int gridSize = 10;
		
		//initialize the game
		WhackAMole myWhackAMole = new WhackAMole(attempts,gridSize);
		
		Random myRand = new Random();
		
		boolean result;
		//mole placement
		for(int i = 0; i < 10; i++) {
			do {
				result = myWhackAMole.place(myRand.nextInt(gridSize), myRand.nextInt(gridSize));
			}while (!result);
		}


		
		System.out.print("you have " + attempts + " attempts to win the game\r\n");
		
		//game play
		while(!gameOver) {			
			myWhackAMole.printGridToUser();
			
			//scan for input
			System.out.print("enter x:");
			x= scanner.nextInt();
			System.out.print("enter y:");
			y= scanner.nextInt();
			if(x ==-1 || y ==-1) {
				gameOver = true;
				System.out.print("QUITTER!\r\n");
				break;
			}
			if( x<gridSize && x>=0 && y<gridSize && y>=0) { //check input range
				myWhackAMole.whack(x, y);
				if(myWhackAMole.attemptsLeft == 0) {
					gameOver = true;
					System.out.print("YOU LOSE!\r\n");
				}
				if(myWhackAMole.molesLeft == 0) {
					gameOver = true;
					System.out.print("YOU WIN!\r\n");
				}
			}
			else {
				System.out.print("coordinates out of range\r\n");
			}
		}
		scanner.close();
		myWhackAMole.printGrid();
		System.out.print("GAME OVER!\r\n");
		return;		
	}
}


