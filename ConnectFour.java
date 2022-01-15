package cha.MAIN;

import java.util.Scanner;

public class ConnectFour {
	static final int R = 1, B = 2;
	static int NUM_COLUMNS = 3;
	static int NUM_IN_ROW = 3;
	static Scanner input = new Scanner(System.in);
	static int firstplayer;
	static int p1 = 0, p2 = 0, ties = 0;
	static long cnt = 0;

	public static void main(String[] args) {
		int b = 0;
		while (b == 0){
			System.out.println("Type 3 for 3x3 board. Type 4 for 4x4 board");
			String boardDimensions = input.next();
			int s = Integer.parseInt(boardDimensions);
			if(s == 4) {
				NUM_COLUMNS = 4;
				NUM_IN_ROW = 4;
				b = 1;
			}
			if(s == 3) {
				b = 1;
			}
			if(s != 3) {
				if(s != 4) {
					System.out.println("Please input valid board dimensions.");
				}
			}
		}
		if(NUM_COLUMNS == 3) {
			for (int i = 0; i < 3; i++) {
				int[][] list = new int[NUM_COLUMNS][NUM_COLUMNS];
				firstplayer = R;
				p1=0;p2=0;cnt=0;ties=0;
			    switch(i) {
			    case 0: list[2][0]=R; break; //  bottom 1
			    case 1: list[2][1]=R; break; //  bot 2
			    case 2: list[2][2]=R; break; //  bot 3
			    }
				Play(list , B);

				System.out.println ("Net Wins for column " + (i + 1) + ": " + (p1-p2));
				System.out.println ("Number of recursion calls: " + cnt);
				System.out.println ("Red Wins:"+p1 +" Blue Wins:"+p2+ " ties:" + ties);
				System.out.println ("******************");
			}
		}
		if (NUM_COLUMNS == 4) {
			for (int i = 0; i < 4; i++) {
				int[][] list = new int[NUM_COLUMNS][NUM_COLUMNS];
				firstplayer = R;
				p1=0;p2=0;cnt=0;ties=0;
			    switch(i) {
			    case 0: list[3][0]=R; break; //  bottom 1
			    case 1: list[3][1]=R; break; //  bot 2
			    case 2: list[3][2]=R; break; //  bot 3
			    case 3: list[3][3]=R; break; //  bot 4
			    }
				Play(list , B);

				System.out.println ("Net Wins for column " + (i + 1) + ": " + (p1-p2));
				System.out.println ("Number of recursion calls: " + cnt);
				System.out.println ("Red Wins:"+p1 +" Blue Wins:"+p2+ " ties:" + ties);
				System.out.println ("******************");
			}
		}
	}
	public static int Play(int[][] inlist, int clr) {
		cnt++;
		int res=checkBoard(inlist,clr);
		// 0 - board full, 1- X wins  2 = O wins   3-keep playing
		if (res < 3) { 
			if (res == 0) {
				ties++;
				return 0;
			}
			else {
				if (res == firstplayer) {
					p1++; 
					return 1;
				} 
				else {
					p2++; 
					return -1;
				}
			}
		}
		res = 0;

		// for each space that can be the next move
		//    make a copy of board (next lines)	

		//   update the board for this move
		
		for (int col = 0; col<NUM_COLUMNS;col++  ){
			if (inlist[0][col] == 0) {
				int[][] clonelist = new int[NUM_COLUMNS][NUM_COLUMNS];
				for (int x = 0;x <NUM_COLUMNS;x++  ){
					for (int y = 0; y<NUM_COLUMNS;y++  ){
						clonelist[x][y] = inlist[x][y] ;
					}
				}
				
				clonelist[0][col] = clr;
				
				int a = 1;
				int row = 0;
				while(a == 1) {
					if(row + 1 < NUM_COLUMNS) {
						if(clonelist[row + 1][col] == 0) {
							clonelist[row][col] = 0;
							clonelist[row + 1][col] = clr;
							row++;
						}
						else {
							a = 0;
							row = 0;
						}
					}
					else {
						a = 0;
					}
				}
				
				Play(clonelist, 3- clr);
			}
		}

		//  recursively call Play
		return res;
	}
	public static boolean isFull(int[][] inlist){
		boolean empty = true;
		for (int i = 0 ; i<NUM_COLUMNS ; i++ ) {
			for (int i2 = 0 ; i2<NUM_COLUMNS ; i2++ ) {
				if (inlist[i][i2] ==0   ) { empty = false; break;} 
			}
		}
		return empty;
	}
	public static int checkBoard(int[][] inlist ,int clr){
		int chkclr = 3-clr;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[i][j] == chkclr) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[j][i] == chkclr) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		int colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  {return chkclr;}	 
			}  else {
				colcnt =0;
			}
		}
		colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[NUM_COLUMNS-1-i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
			}  else {
				colcnt =0;
			}
		}
		if (isFull(inlist)) {  return 0; 
		} else {
			return 3;
		}


	}
	public static void printlist(int[][] inlist) {
		for (int i =0; i<inlist.length; i++) {
			for (int j =0; j<inlist.length; j++) {
				System.out.print(inlist[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}