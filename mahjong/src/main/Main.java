package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tiles.TileType;

public class Main {
	public static void main(String[] args) {
		System.out.println("麻雀だよ");
		System.out.println("はじめる？");
		boolean isOk = false;
		Scanner scn = new Scanner(System.in);
			do {
			String str =scn.nextLine();
			if (str.equals("y")) {
					System.out.println("りょうかい！");
					break;
				}else if(str.equals("n")) {
					System.out.println("ほげほげ");
					System.exit(0);;
				}else {
					System.out.println("無効な文字を受け取ったよ");
					isOk = true;
			}
		}while(isOk);
		
		System.out.println("配牌はこれ");
		List<TileType> hand = new ArrayList<>();
		hand=MarjongTable.dealHand(MarjongTable.createShuffledWall(),14);
		MarjongTable.sortHand(hand);
		System.out.println(hand);
		
		System.out.println("何きる？");
		int nanikiru = 13;
		do {
			int i = scn.nextInt();
			if(i < 0 || i>=14) {
				System.out.println("無効な数字だよ");
			}else {
				nanikiru = i;
				break;
			}
		}while(true);
			System.out.println(hand.get(nanikiru)+ "を切るよ");
			hand.remove(nanikiru);
		
		
		
		
		System.out.println();
	}

}
