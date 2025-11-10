package main;

import java.util.ArrayList;
import java.util.List;

import tiles.TileType;

public class Tester {

	public static void main(String[] args) {
		
		//あがり形を指定して判定可能かテスト
		List<TileType> agari = new ArrayList<>();
		agari.add(TileType.MAN2);
		agari.add(TileType.MAN3);
		agari.add(TileType.MAN4);
		agari.add(TileType.MAN6);
		agari.add(TileType.MAN7);
		agari.add(TileType.MAN8);
		agari.add(TileType.PIN4);
		agari.add(TileType.PIN5);
		agari.add(TileType.SOU4);
		agari.add(TileType.SOU4);
		agari.add(TileType.SOU5);
		agari.add(TileType.SOU7);
		agari.add(TileType.SOU8);
		agari.add(TileType.SOU9);

		System.out.println("--↓あがり形の例");
		for (TileType h : agari) {
			System.out.print(h.getName2());
		}
		System.out.println();

		int[] counts = new int[34];
		for (int i = 0; i < 34; i++) {
			counts[i] = 4;
		}

		System.out.println("上がり形の配列を表示"); 
		int[] cntAgari = new int[34];
		for (TileType h : agari) {
			cntAgari[h.getId()]++;
		}
		for (int i : cntAgari) {
			System.out.print(i);
		}
		System.out.println();

		//System.out.println(hand);

		// 残りの牌で4面子の判定(再起関数)
		//judgeHand(agari)で返ってきたリストをシスアウト
		System.out.println(Judge.judgeHand(agari));
		
		
		/*System.out.println("--↓あがりになってたらあがり");
		System.out.println(j);
		System.out.println("--↓trueになってたらテンパイ");
		System.out.println(j.isTempai());
		
		System.out.println("--↓断么九って書いてたらOK");
		System.out.println(j.getHands());*/
	}
}
