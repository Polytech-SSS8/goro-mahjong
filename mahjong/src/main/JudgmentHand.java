package main;

import java.util.ArrayList;
import java.util.List;

import tiles.TileType;

public class JudgmentHand {
	private boolean isAgari;
	private List<String> hand;
	private int han;
	private int point;
	private int[] handCounts;
	
	
	//コンストラクタ
	private JudgmentHand(boolean isAgari, List<String> hand, int han, int point) {
		super();
		this.isAgari = isAgari;
		this.hand = hand;
		this.han = han;
		this.point = point;
	}
	
	
	//
	public static JudgmentHand judgeHand (List<TileType> hand) {
		List<String> hands = new ArrayList<String>();
		JudgmentHand j = new JudgmentHand(isAgari(hand),hands,1,1000);
		
		return j;
	}
	
	@Override
	public String toString() {
		String agari = this.isAgari == true ? "あがり！" : "notあがり";
		return agari;
		
		//toStringはとりあえずあがりの有無を返す感じで
	}
	
	/**
	 * 手牌が基本形であがっているかを判定するメソッドです。
	 * 
	 * @param 手牌のList
	 * @return 上がり形が成立していればtrue
	 */
	public static boolean isAgari(List<TileType> hand) {
		int[] handCounts= new int[34];
		for(TileType h : hand) {
			handCounts[h.getId()]++;
		}
		for(int i = 0; i<handCounts.length;i++) {
			if(i >= 2) {
				handCounts[i] -=2;
				if (is4Mentsu(hand,handCounts) == true) {return true;
				}else {
					for(int j = 0; j<handCounts.length; j++) {
						handCounts[j] = 0;
					}
					for(TileType h : hand) {
						handCounts[h.getId()]++;
					}
				}
			}
		}return false;
	}
	
	/**
	 * 4面子の有無を判定するメソッドです。
	 * 雀頭を抜いた形の手牌を引数にとります。
	 * @param 雀頭を抜いた手牌IDのint型配列
	 * @return 4面子があればtrue
	 */
	public static boolean is4Mentsu(List<TileType> hand, int[] handCounts) {
		int Max = handCounts[0];
		int MaxTile = 0;
		for(int i = 0; i<handCounts.length; i++) {
			if(Max < handCounts[i]) {
				Max = handCounts[i];
				MaxTile = i;}
		}
			if (Max >= 3) {
				handCounts[MaxTile] -=3;
				//if(handCounts[MaxTile] <0) {return false;}
				is4Mentsu(hand,handCounts);
			}
			if(//TODO さすがに冗長すぎるしわかりづらい
				MaxTile != 7
					&& MaxTile != 8
					&& MaxTile != 16
					&& MaxTile != 17
					&& MaxTile < 25
					&& handCounts[MaxTile+1]>=1 && handCounts[MaxTile+2]>=1) {
				handCounts[MaxTile]-=1;
				handCounts[MaxTile+1]-=1;
				handCounts[MaxTile+2]-=1;
				is4Mentsu(hand,handCounts);
			}
			
		for(int j = 0;j<handCounts.length;j++) {
			if(handCounts[j]>=1) {return false;}
		}
		return true;
	}
	
	
	public static String Pinfu() {
		
		return "平和";
	}
	
	public static String Tanyao() {
		return "タンヤオ";
	}
	
	public static String ipeko() {
		return "一盃口";
	}
	
	

}