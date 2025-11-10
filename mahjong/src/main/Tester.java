package main;

import java.util.ArrayList;
import java.util.List;

import tiles.TileType;

/**
 * あがり形を指定してJudgeクラスをテストするクラスです。
 */
public class Tester {

	public static void main(String[] args) {

		// あがり形を指定して判定テスト
		List<TileType> agari = new ArrayList<>();

		String a = "一一二二三三四四五五六六七八";
		String[] ab = a.split("");
		for (int i = 0; i < 14; i++) {
			agari.add(TileType.getTile(ab[i]));
		}
		System.out.println(agari);

		int[] counts = new int[34];
		for (int i = 0; i < 34; i++) {
			counts[i] = 4;
		}

		System.out.println("--↓上がり形のcnt配列");
		int[] cntAgari = new int[34];
		for (TileType h : agari) {
			cntAgari[h.getId()]++;
		}
		for (int i : cntAgari) {
			System.out.print(i);
		}
		System.out.println();

		// judgeHand(agari)で返ってきたリストをシスアウト
		System.out.println("--↓[あがり、てんぱい、役]");
		System.out.println(Judge.judgeHand(agari));
	}
}
