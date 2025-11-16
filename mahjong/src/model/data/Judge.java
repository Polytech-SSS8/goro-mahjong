package model.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 手牌の役を判定するJudgeクラスです。
 * 判定情報を格納したArrayListを返すstaticなメソッドを持ちます。
 */
public class Judge {

	private boolean isAgari;
	private boolean isTempai;
	private List<Hands> hands;
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

	private Judge(boolean isAgari, List<String> hands, List<TileType> hand, int han, int point) {
		super();
		this.isAgari = isAgari;
		this.hand = hand;
		this.han = han;
		this.point = point;
	}

	// メソッド
	/**
	 * 判定結果の全情報を格納したListを返す静的メソッドです。
	 * 
	 * @param 手牌のList
	 * @return 判定結果List
	 */
	public static List<Integer> judgeHand(List<TileType> hand) {
		int agariTileId = hand.getLast().getId();
		int[] cnt = newHandCount(hand);
		List<Integer> judge = new ArrayList<Integer>();
		judge.add(judgeAgari(cnt));
		if (judge.getFirst() == 1) {

		}
		judge.add(judgeTempai(cnt));
		judge.add(tanyao(cnt));
		judge.add(sevenPairs(cnt));

		return judge;
	}

	// TODO
	/**
	 * すべての待ちパターンを調べます。戻り値は未定です。
	 */
	public static void judgeMachi() {

	}

	/**
	 * リャンメンかどうかを調べます。
	 * 
	 * @param 手牌のカウント配列
	 * @param あがり牌のID
	 * @retuern リャンメンならtrue
	 */
	public static boolean isRyammen(int[] handCount, int agariTileId) {
		if (agariTileId < 27) {
			// あがり、〇、〇パターン
			if (agariTileId % 9 != 7 || agariTileId % 9 != 8) {
				int[] cnt = handCount.clone();
				cnt[agariTileId]--;
				cnt[agariTileId + 1]--;
				cnt[agariTileId + 2]--;
				if (judgeAgari(cnt) == 1) {
					return true;
				}
			}
			// 〇、〇、あがりパターン
			if (agariTileId % 9 != 0 || agariTileId % 9 != 1) {
				int[] cnt = handCount.clone();
				cnt[agariTileId]--;
				cnt[agariTileId + 1]--;
				cnt[agariTileId + 2]--;
				if (judgeAgari(cnt) == 1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * カンチャンかどうかを調べます。
	 */
	public static boolean isKanchan(int[] handCount, int agariTileId) {
		if (agariTileId < 27) {
			if (agariTileId % 9 != 0 && agariTileId % 9 != 9) {
				int[] cnt = handCount.clone();
				cnt[agariTileId - 1]--;
				cnt[agariTileId]--;
				cnt[agariTileId + 1]--;
				if (judgeAgari(cnt) == 1) {
					return true;
				}
			}
		}
		return false;
	}

	// TODO
	/**
	 * シャンポンかどうか調べます。
	 */
	public static boolean isShampon(int[] handCount, int agariTileId) {
		if (handCount[agariTileId] >= 3) {
			int[] cnt = handCount.clone();
			cnt[agariTileId] -= 3;
			if (judgeAgari(cnt) == 1) {
				return true;
			}
		}
		return false;
	}

	public static boolean isPenchan() {
		return false;
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
	private static int findFirstTile(int[] handCount) {
		for (int i = 0; i < handCount.length; i++) {
			if (handCount[i] > 0) {
				return i;
			}
		}
		return -1; // すべての牌が0枚の場合
	}

	/**
	 * 引数の手牌Listから上がり判定に必要なint型handCount配列を作成するメソッドです
	 * 
	 * @param hand
	 * @return int型handCounts配列(要素数34)
	 */
	private static int[] newHandCount(List<TileType> hand) {
		int[] handCount = new int[34];
		for (TileType h : hand) {
			handCount[h.getId()]++;
		}
		return handCount;
	}

	/**
	 * 引数のツモ前手牌int型配列がテンパイしているかを判定するメソッド
	 * judgeTempai(int[] handCount)の補助メソッドです
	 * 
	 * @param ツモ前手牌のint型配列(34)
	 * @return テンパイならtrue
	 */
	public static boolean judgeTempai13(int[] handCount13) {
		for (int i = 0; i < 34; i++) {
			if (handCount13[i] < 4) {
				int[] handCount14 = handCount13.clone();
				handCount14[i]++;

				if (judgeAgari(handCount14) == 1) {
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
	public static int judgeTempai(int[] handCount) {
		int[] cnt = new int[34];
		for (int i = 0; i < cnt.length; i++) {
			cnt = handCount;
			if (cnt[i] >= 1) {
				int[] handCount13 = handCount.clone();
				handCount13[i]--;
				if (judgeTempai13(handCount13)) {
					return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * 雀頭を抜いた形の手牌カウント配列から、残りの面子数を返すメソッドです。
	 * 
	 * @param 手牌カウント配列(雀頭抜き)
	 * @return 残りの面子数
	 */
	public static int remainMentsu(int[] handCount) {
		int remainMentsu = 4;
		int tiles = Arrays.stream(handCount).sum();
		remainMentsu = tiles / 3;
		return remainMentsu;
	}

	/**
	 * 手牌が基本形であがっているかを判定するメソッドです。
	 * 雀頭候補を二枚抜き出し、残り4面子を再起呼び出しで処理します。
	 * 
	 * @param 手牌IDのint型配列
	 * @return 上がり形が成立していればtrue
	 */
	public static int judgeAgari(int[] handCount) {
		if (sevenPairs(handCount) == 1) {
			return 1;
		}
		for (int i = 0; i < handCount.length; i++) {
			if (handCount[i] >= 2) {
				int[] cnt = handCount.clone();
				cnt[i] -= 2;
				int remainMentsu = remainMentsu(cnt);
				if (is4MentsuRecursive(cnt, remainMentsu)) {
					return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * 【再帰メソッド】残りの手牌で指定された数の面子を作れるかを判定します。
	 * judgeAgariメソッドの補助メソッドです。
	 * * @param handCount 雀頭や面子を抜いた後の手牌カウント配列 (コピーを渡すこと推奨)
	 * 
	 * @param requiredMenzen 残り必要な面子の数
	 * @return 成立していれば true
	 */
	public static boolean is4MentsuRecursive(int[] handCount, int requiredMenzen) {
		// 1. ベースケース: 必要な面子が0になったら成功
		if (requiredMenzen == 0) {
			return true;
		}

		// 2. 処理開始位置の決定 (残っている最初の牌)
		int startTileId = findFirstTile(handCount);

		// 牌が残っていないのに必要な面子がある場合は失敗
		if (startTileId == -1) {
			return false;
		}

		// **********************************************
		// 3. 刻子 (コーツ) 抜き出しを試行
		// **********************************************
		if (handCount[startTileId] >= 3) {
			// コピーを作成し、刻子を抜き出す
			int[] cntForKotsu = handCount.clone();
			cntForKotsu[startTileId] -= 3;

			// 残りの面子 requiredMenzen-1 が作れるか再帰的にチェック
			if (is4MentsuRecursive(cntForKotsu, requiredMenzen - 1)) {
				return true; // 成功
			}
		}

		// **********************************************
		// 4. 順子 (シュンツ) 抜き出しを試行
		// **********************************************
		// 数牌かつ 9牌ではない (IDが7, 8, 16, 17, 25, 26ではない) ことをチェック
		if (startTileId < 27 && (startTileId % 9) < 7) {
			int nextTile1 = startTileId + 1;
			int nextTile2 = startTileId + 2;

			if (handCount[nextTile1] >= 1 && handCount[nextTile2] >= 1) {
				// 順子を抜き出すための新しいコピー
				int[] cntForShuntsu = handCount.clone();

				cntForShuntsu[startTileId] -= 1;
				cntForShuntsu[nextTile1] -= 1;
				cntForShuntsu[nextTile2] -= 1;

				// 残りの面子 requiredMenzen-1 が作れるか再帰的にチェック
				if (is4MentsuRecursive(cntForShuntsu, requiredMenzen - 1)) {
					return true; // 成功
				}
			}
		}

		// 5. 刻子も順子も抜き出せなかった場合は失敗
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
	public static boolean is4Mentsu(int[] /* cnt */ outJantouCnt) {
		int[] cnt = outJantouCnt.clone();
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

	public static boolean is4Chow(int[] handCount, int requiredChow, int agariTile) {
		if (requiredChow == 0) {
			return true;
		}
		int startTileId = findFirstTile(handCount);
		if (startTileId == -1) {
			return false;
		}
		if (startTileId < 27 && (startTileId % 9) < 7) {
			int nextTile1 = startTileId + 1;
			int nextTile2 = startTileId + 2;

			if (handCount[nextTile1] >= 1 && handCount[nextTile2] >= 1) {
				// 順子を抜き出すための新しいコピー
				int[] cntChow = handCount.clone();

				cntChow[startTileId] -= 1;
				cntChow[nextTile1] -= 1;
				cntChow[nextTile2] -= 1;

				// 残りの面子 requiredMenzen-1 が作れるか再帰的にチェック
				if (is4Chow(cntChow, requiredChow - 1, agariTile)) {
					return true; // 成功
				}
			}
		}
		return false;
	}

	public static int pinfu(int[] handCount, int agariTile) {
		return 0;
	}

	/**
	 * 断么九を判定するメソッドです。
	 * 
	 * @param 手牌カウント配列
	 * @return 成立なら1、非成立なら0
	 */
	public static int tanyao(int[] handCount) {
		int[] cnt = handCount.clone();
		if (cnt[0] == 0 && cnt[8] == 0 && cnt[9] == 0 && cnt[17] == 0 && cnt[18] == 0 &&
				cnt[26] == 0 && cnt[27] == 0 && cnt[28] == 0 && cnt[29] == 0 && cnt[30] == 0 &&
				cnt[31] == 0 && cnt[32] == 0 && cnt[33] == 0) {
			return 1;
		} else {
			// TODO: removeIf()を使ってラムダ式で消す条件とか決めてできるらしーぞ
			return 0;
		}
	}

	/**
	 * 七対子を判定するメソッドです。
	 * 
	 * @param 手牌カウント配列
	 * @return 成立なら1、非成立なら0
	 */
	public static int sevenPairs(int[] handCount) {
		int toitsu = 0;
		for (int i = 0; i < handCount.length; i++) {
			if (handCount[i] == 2) {
				toitsu++;
			}
			if (toitsu == 7) {
				return 1;
			}
		}
		return 0;
	}
}
