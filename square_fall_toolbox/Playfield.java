package square_fall_toolbox;

import java.util.ArrayList;
import java.util.Stack;

public class Playfield {
	private int[][] playfield;
	
	public Playfield(int[][] p) {
		this.playfield = p;
	}
	
	public Playfield(Playfield p) {
		this.playfield = new int[p.yLength()][p.xLength()];
		
		for(int i = 0; i<p.yLength(); i++) {
			for(int j = 0; j<p.xLength(i); j++) {
				this.playfield[i][j] = p.getRow(j, i);
			}
		}
	}

	public int[][] getPlayfield() {
		return playfield;
	}

	public void setPlayfield(int[][] playfield) {
		this.playfield = playfield;
	}
	
	public int getRow(int x, int y) {
		return this.playfield[y][x];
	}
	public void setRow(int x, int y, int value) {
		this.playfield[y][x] = value;
	}
	
	public int xLength() {
		return playfield[0].length;
	}
	public int xLength(int y) {
		return playfield[y].length;
	}
	public int yLength() {
		return playfield.length;
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for(int i = 0; i<playfield.length; i++) {
			for(int j = 0; j<playfield[i].length; j++) {
				buff.append(playfield[i][j] + " ");
			}
		buff.append("\n");
		}
		return buff.toString();
	}
	
	public boolean equals(Playfield p) {
		boolean result = true;
		for(int i = 0; i<playfield.length; i++) {
			for(int j = 0; j<playfield[i].length; j++) {
				if(!(playfield[i][j] == p.getRow(j, i))){
					result = false;
				}
			}
		}
		return result;
	}
	
	public ArrayList<Integer> rle() {
		int old_value = playfield[0][0];
		int counter = 0;
		Stack<Integer> rle_pile = new Stack<>();
		for(int i = 0; i<playfield.length; i++) {
			for(int j = 0; j<playfield[i].length; j++) {
				if(old_value == playfield[i][j]) {
					counter++;
				}
				else {
					rle_pile.push(counter);
					rle_pile.push(old_value);
					counter = 1;
					old_value = playfield[i][j];
				}
			}
		}
		// push the last occurrence
		rle_pile.push(counter);
		rle_pile.push(old_value);
		return new ArrayList<Integer>(rle_pile);
	}
	public ArrayList<ArrayList<Integer>> find_zone(int x,int y,ArrayList<ArrayList<Integer>> input) {
		ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		
		row.add(x);
		row.add(y);
		output.add(row);
		
		if(input.contains(row)) {
			return output;
		}
		else {
			input.add(row);
				
			if(x>0 && this.getRow(x, y) == this.getRow(x-1, y)) {
				this.find_zone(x-1,y,input);
			}

			if(x<this.xLength(y)-1 && this.getRow(x, y) == this.getRow(x+1, y)) {
				this.find_zone(x+1,y,input);
			}

			if(y>0 && this.getRow(x, y) == this.getRow(x, y-1)) {
				this.find_zone(x,y-1,input);
			}
			
			if(y<this.yLength()-1 && this.getRow(x, y) == this.getRow(x, y+1)) {
				this.find_zone(x,y+1,input);
			}
			return input;
		}
	}
	
	public void deleteZone(int x,int y) {
		ArrayList<ArrayList<Integer>> zone = this.find_zone(x, y, new ArrayList<ArrayList<Integer>>());
		zone.forEach((n)->this.setRow(n.get(0), n.get(1), 0));
	}
	
	public void xFall() {
		for(int i = 0; i<playfield.length; i++) {
			for(int j = 1; j<playfield[i].length; j++) {
				if(playfield[i][j-1] == 0) {
					playfield[i][j-1] = playfield[i][j];
					playfield[i][j] = 0;
				}
			}
		}
	}
	public void yFall() {
		for(int i = playfield.length -1; i>0; i--) {
			for(int j = 0; j<playfield[i].length; j++) {
				if(playfield[i][j] == 0) {
					playfield[i][j] = playfield[i-1][j];
					playfield[i-1][j] = 0;
				}
			}
		}
	}
	
}
