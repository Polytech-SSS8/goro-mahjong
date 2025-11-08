package main;

import java.util.ArrayList;
import java.util.List;

import tiles.TileType;

/**
 * Judgeインスタンスを定義するクラスです。
 * 今のところ手役の判定結果を格納する可変フィールドを持ち、インスタンス化して使います
 * しかし今後はPlayerクラスやTableクラスに情報を持たせたいので、フィールドを定数化してstaticなクラスにします
 */
public class Judge {
	private boolean isAgari;
	private boolean isTempai;
	private List<String> yaku;
	private List<TileType> hand;
	private int han;
	private int point;
	// private int[] handCounts;

	// コンストラクタ
	public Judge() {
		super();
		this.isAgari = false;
		this.isTempai = false;
		this.hand = null;
		this.han = 0;
		this.point = 0;
	}

	private Judge(boolean isAgari, List<String> yaku, List<TileType> hand, int han, int point) {
		super();
		this.isAgari = isAgari;
		this.hand = hand;
		this.han = han;
		this.point = point;
	}

	/**
	 * Judgeインスタンスに手牌の上がり判定結果を備えさせるメソッドです
	 * 
	 * @param 手牌のList
	 * 
	 */
	public void judgeHand(List<TileType> hand) {
		this.hand = hand;
		this.isTempai = judgeTempai(newHandCounts());
		this.isAgari = judgeAgari(newHandCounts());

	}

	@Override
	public String toString() {
		String agari = this.isAgari == true ? "あがり！" : "notあがり";
		return agari;

		// toStringはとりあえずあがりの有無を返す感じで
	}

	/**
	 * handCount配列の中で、1以上の値を持つ最初のインデックス（牌）を見つけます
	 */
	private int findFirstTile(int[] handCount) {
		for (int i = 0; i < handCount.length; i++) {
			if (handCount[i] > 0) {
				return i;
			}
		}
		return -1; // すべての牌が0枚の場合
	}

	/**
	 * このインスタンスのhandフィールドから上がり判定に必要なint型handCounts配列を作成するメソッドです
	 * 
	 * @return int型handCounts配列(要素数34)
	 */
	public int[] newHandCounts() {
		int[] handCounts = new int[34];
		for (TileType h : this.hand) {
			handCounts[h.getId()]++;
		}
		return handCounts;
	}

	/**
	 * 引数の手牌Listから上がり判定に必要なint型handCounts配列を作成するメソッドです
	 * 
	 * @param hand
	 * @return int型handCounts配列(要素数34)
	 */
	public int[] newHandCounts(List<TileType> hand) {
		int[] handCounts = new int[34];
		for (TileType h : hand) {
			handCounts[h.getId()]++;
		}
		return handCounts;
	}

	/**
	 * 引数のツモ前手牌int型配列がテンパイしているかを判定するメソッド
	 * judgeTempai(int[] handCount)の補助メソッドです
	 * 
	 * @param ツモ前手牌のint型配列(34)
	 * @return テンパイならtrue
	 */
	public boolean judgeTempai13(int[] handCount13) {
		for (int i = 0; i < 34; i++) {
			if (handCount13[i] < 4) {
				int[] handCount14 = handCount13.clone();
				handCount14[i]++;

				if (judgeAgari(handCount14)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 引数の手牌int型配列がテンパイしているかを判定するメソッドです
	 * 
	 * @param 手牌のint型配列(34)
	 * @return テンパイならtrue
	 * 
	 */
	public boolean judgeTempai(int[] handCount) {
		this.isTempai = false;
		int[] cnt = new int[34];
		for (int i = 0; i < cnt.length; i++) {
			cnt = handCount;
			if (cnt[i] >= 1) {
				int[] handCount13 = handCount.clone();
				handCount13[i]--;
				if (judgeTempai13(handCount13)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 手牌が基本形であがっているかを判定するメソッドです。
	 * 
	 * @param 手牌IDのint型配列
	 * @return 上がり形が成立していればtrue
	 */
	public boolean judgeAgari(int[] handCount) {
		int[] cnt = new int[34];
		// cnt = handCount;
		this.isAgari = false;
		for (int i = 0; i < cnt.length; i++) {
			cnt = handCount;
			if (cnt[i] >= 2) {
				cnt[i] -= 2;
				if (is4Mentsu(cnt)) {
					return true;
				}
			}
		}
		return false;
	}

	// TODO: 動くけどgeminiに文句言われてる
	/**
	 * 4面子の有無を判定するメソッドです。
	 * 雀頭を抜いた形の手牌を引数にとります。
	 * 
	 * @param 雀頭を抜いた手牌IDのint型配列
	 * @return 4面子があればtrue
	 */
	public boolean is4Mentsu(int[] cnt) {
		int Max = cnt[0];
		int MaxTile = 0;
		for (int i = 0; i < cnt.length; i++) {
			if (Max < cnt[i]) {
				Max = cnt[i];
				MaxTile = i;
			}
		}
		if (Max >= 3) {
			cnt[MaxTile] -= 3;
			// if(handCounts[MaxTile] <0) {return false;}
			is4Mentsu(cnt);
		}
		if (// 違う牌種で順子と判定するのを避けるため
		MaxTile != 7
				&& MaxTile != 8
				&& MaxTile != 16
				&& MaxTile != 17
				&& MaxTile <= 24
				&& cnt[MaxTile + 1] >= 1 && cnt[MaxTile + 2] >= 1) {
			cnt[MaxTile] -= 1;
			cnt[MaxTile + 1] -= 1;
			cnt[MaxTile + 2] -= 1;
			is4Mentsu(cnt);
		}

		for (int j = 0; j < cnt.length; j++) {
			if (cnt[j] >= 1) {
				return false;
			}
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

	public boolean isAgari() {
		return this.isAgari;
	}

	public boolean isTempai() {
		return this.isTempai;
	}

	public List<String> getYaku() {
		return this.yaku;
	}

	public int getHan() {
		return this.han;
	}

	public int getPoint() {
		return this.point;
	}

}