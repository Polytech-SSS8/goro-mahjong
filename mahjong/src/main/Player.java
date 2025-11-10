package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import tiles.TileType;

/**
 * 麻雀の面子の一人、プレイヤーを定義するクラスです。
 * 面子インターフェースをインプリメントします。
 */
public class Player implements Ments {
	private List<TileType> hand;
	private boolean riichi; // リーチ
	private boolean menzen; // メンゼン
	private boolean call; // 副露の有無
	private int[] openedTiles; // 副露または暗槓した手牌コードを格納
	private boolean kan; // カンの有無
	private int point; // 点棒
	private List<Integer> agari;// あがり情報が入ったリスト

	// コンストラクタ
	public Player() {
		super();
		this.hand = new ArrayList<TileType>();
	}

	// メソッド
	public void haipai(List<TileType> haipai) {
		if (this.hand.isEmpty()) {
			this.hand = haipai;
		} else {
			System.out.println("すでに牌は配られてるよ");
		}
	}

	public void agari() {
		System.out.println("あがり！！");
		if (this.agari.get(2) == 1 && this.agari.get(3) == 1) {
			System.out.println("役はタンヤオ、チートイツ！");
			System.exit(0);
		} else if (this.agari.get(2) == 1) {
			System.out.println("役はタンヤオ！");
		} else if (this.agari.get(3) == 1) {
			System.out.println("役はチートイツ！");
		} else {
			System.exit(0);
		}
	}

	public void sortHand(List<TileType> hand) {
		/**
		 * 手牌を萬子、筒子、索子、風牌、三元牌の順でソートする
		 * 
		 * @param ソート前の手牌リスト
		 * @return ソート後の手牌リスト
		 */
		Collections.sort(hand);
	}

	@Override
	public void tsumo(List<TileType> tsumo) {
		sortHand(this.hand);
		this.hand.addAll(tsumo);
		System.out.println(tsumo + "をツモってきたよ");
		this.agari = Judge.judgeHand(this.hand);
		System.out.println(this.hand);
		if (this.agari.get(0) == 1) {
			agari();
		}
		if (this.agari.get(1) == 1) {
			System.out.println("テンパイ！");
		}
	}

	@Override
	/**
	 * プレイヤーが打牌するメソッドです。
	 * 
	 * @return 打牌
	 */
	public List<TileType> discard() {
		if (this.hand.size() <= 0 || this.hand.size() >= 15) {
			throw new IllegalArgumentException("枚数おかしくない？");
		}
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
		System.out.println(this.hand.get(nanikiru) + "を切るよ\n");

		// 牌山の先頭から指定枚数のリストをサブリストとして取得
		List<TileType> discard = new ArrayList<>(this.hand.subList(nanikiru, nanikiru + 1));

		// 牌山から取り出した牌を削除
		// 注意: subListのclear()は元のリストからも要素を削除します
		this.hand.subList(nanikiru, nanikiru + 1).clear();

		return discard;

	}

	@Override
	public void call(TileType discard) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'call'");
	}

	@Override
	public List<TileType> pon(TileType discard) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'pon'");
	}

	@Override
	public List<TileType> chii(TileType discard) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'chii'");
	}

	@Override
	public List<TileType> kan(TileType discard) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'kan'");
	}

	public List<TileType> getHand() {
		return hand;
	}

	public void setHand(List<TileType> hand) {
		this.hand = hand;
	}

	public boolean isRiichi() {
		return riichi;
	}

	public void setRiichi(boolean riichi) {
		this.riichi = riichi;
	}

	public boolean isMenzen() {
		return menzen;
	}

	public void setMenzen(boolean menzen) {
		this.menzen = menzen;
	}

	public boolean isCall() {
		return call;
	}

	public void setCall(boolean call) {
		this.call = call;
	}

	public int[] getOpenedTiles() {
		return openedTiles;
	}

	public void setOpenedTiles(int[] openedTiles) {
		this.openedTiles = openedTiles;
	}

	public boolean isKan() {
		return kan;
	}

	public void setKan(boolean kan) {
		this.kan = kan;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
