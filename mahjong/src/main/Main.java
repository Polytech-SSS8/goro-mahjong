package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import tiles.TileType;

public class Main {
	public static void main(String[] args) {
		System.out.println("麻雀ver0.1.3だよ");
		System.out.println("一雀頭四面子を作れたらあがりだよ");
		System.out.println("例:[一, 一, 九, 九, 九, ①, ②, ③, 1, 2, 3, 白, 白, 白]");
		System.out.println("テンパイも多分教えてあげられるよ");
		System.out.println("はじめる？");
		System.out.println("(press y/n)");
		boolean isOk = false;
		Scanner scn = new Scanner(System.in);
		do {
			String str = scn.nextLine();
			if (str.equals("y")) {
				System.out.println("りょうかい！");
				System.out.println("\n牌は14枚あるよ\n1～14を入力して切りたい牌を指定してね");
				break;
			} else if (str.equals("n")) {
				System.out.println("ほげほげ");
				scn.close();
				System.exit(0);
			} else {
				System.out.println("無効な文字を受け取ったよ");
				isOk = true;
			}
		} while (isOk);

		System.out.println("あなたの手牌はこれ");
		List<TileType> wall = new ArrayList<>();
		List<TileType> hand = new ArrayList<>();
		wall = Table.createShuffledWall();
		hand = Table.dealHand(wall, 14);

		Judge j = new Judge();
		j.judgeHand(hand);
		System.out.println(hand);

		while (!j.isAgari()) {
			tumogiri(hand, wall);
			j.judgeHand(hand);
			if (j.isTempai()) {
				System.out.println("テンパイ！");
			}
		}
		System.out.println("あがり！");

		scn.close();
	}

	public static void tumogiri(List<TileType> hand, List<TileType> wall) {
		System.out.println("なに切る？");
		int nanikiru = 13;

		do {

			try {
				Scanner scn = new Scanner(System.in);
				int i = scn.nextInt() - 1;
				if (i < 0 || i >= 14) {
					System.out.println("無効な数字だよ");
					continue;
				} else {
					nanikiru = i;
					break;
				}
			} catch (InputMismatchException a) {
				System.out.println("無効な入力だよ");
			}
		} while (true);
		System.out.println(hand.get(nanikiru) + "を切るよ");
		hand.remove(nanikiru);

		Table.sortHand(hand);
		hand.addAll(Table.dealHand(wall, 1));
		System.out.println(hand.getLast() + "をツモってきたよ");
		System.out.println(hand);

		System.out.println();
	}

}
