package square_fall_toolbox;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		int[][] essai = {
				{0,0,0,0,0},
				{1,1,1,1,0},
				{2,1,2,2,1},
				{4,4,4,4,1}
		};
		int[][] essai2 = {
				{0,0,0,0,0},
				{1,1,1,1,0},
				{2,1,2,2,1},
				{4,4,4,4,1}
		};
		Playfield pop = new Playfield(essai);
		//pop.setPlayfield(essai);
		System.out.println(pop.toString());
		System.out.println("row  1,3 : "+pop.getRow(1, 3));
		
		Playfield pop2 = new Playfield(pop);
		System.out.println("equals " + pop.equals(pop2));
		
		ArrayList<Integer> result = pop.rle();
		//int result = pop.getRow(1, 1);
		System.out.println(result);
		ArrayList<ArrayList<Integer>> trololo = new ArrayList<ArrayList<Integer>>();
		System.out.println(pop.xLength());
		System.out.println(pop.yLength());
		System.out.println(pop.find_zone(1, 3,trololo));
		pop.deleteZone(1, 3);
		System.out.println(pop.toString());
		pop.xFall();
		System.out.println(pop.toString());
		pop.yFall();
		System.out.println(pop.toString());		

		System.out.println("equals " + pop.equals(pop2));
		System.out.println(pop2.toString());	
				
	}
}
