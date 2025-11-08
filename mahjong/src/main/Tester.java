package main;

import java.util.ArrayList;
import java.util.List;

import tiles.TileType;

public class Tester {

	public static void main(String[] args) {
		List<TileType> wall = Table.createShuffledWall();
		List<TileType> hand = Table.dealHand(wall, 13);
		Table.sortHand(hand);
		System.out.println("--↓手牌を配ってソートした結果");
		for (TileType h : hand) {
			System.out.print(h.getName2());
		}
		System.out.println();

		List<TileType> agari = new ArrayList<>();
		agari.add(TileType.MAN2);
		agari.add(TileType.MAN2);
		agari.add(TileType.MAN7);
		agari.add(TileType.MAN8);
		agari.add(TileType.MAN9);
		agari.add(TileType.PIN2);
		agari.add(TileType.PIN2);
		agari.add(TileType.PIN2);
		agari.add(TileType.PIN7);
		agari.add(TileType.PIN8);
		agari.add(TileType.SOU6);
		agari.add(TileType.SOU7);
		agari.add(TileType.SOU8);
		agari.add(TileType.PIN9);

		System.out.println("--↓あがり形の例");
		for (TileType h : agari) {
			System.out.print(h.getName2());
		}
		System.out.println();

		int[] counts = new int[34];
		for (int i = 0; i < 34; i++) {
			counts[i] = 4;
		}

		// 雀頭の固定メソッド
		int[] handCounts = new int[34];
		for (TileType h : agari) {
			handCounts[h.getId()]++;
		}

		for (int i : counts) {
			System.out.print(i);
		}
		System.out.println();
		for (int i : handCounts) {
			System.out.print(i);
		}
		System.out.println();

		System.out.println(hand);

		// 残りの牌で4面子の判定(再起関数)
		//
		Judge j = new Judge();
		j.judgeHand(agari);
		System.out.println("--↓あがりになってたらあがり");
		System.out.println(j);
		System.out.println("--↓trueになってたらテンパイ");
		System.out.println(j.isTempai());
	}
}
