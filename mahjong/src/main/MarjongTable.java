package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tiles.TileType;

public class MarjongTable {
	private int riichiStick;
	private int Kan;
	private int Pon;
	private int chii;
	
	
	public static void sortHand(List<TileType>hand){
		/**
		 * 手牌を萬子、筒子、索子、風牌、三元牌の順でソートする
		 * @param ソート前の手牌リスト
		 * @return ソート後の手牌リスト
		 */
		Collections.sort(hand);
	}
	
	public static List<TileType> createWall() {
		/**
	     * 136枚の牌を格納した牌山（山牌）リストを生成する
	     * @return シャッフル前の牌山リスト
	     */
        List<TileType> wall = new ArrayList<>();
        
        // 34種類の牌すべてを取得
        for (TileType tile : TileType.values()) {
            // 各牌を4枚ずつ追加
            for (int i = 0; i < 4; i++) {
                wall.add(tile);
            }
        }
        return wall;
    }
	
	public static List<TileType> createShuffledWall() {
        List<TileType> wall = createWall();
        
        // 牌山をシャッフル
        Collections.shuffle(wall);
        
        return wall;
    }
	
	/**
     * 牌山から指定された枚数（配牌）を取り出し、牌山から削除する
     * @param wall シャッフルされた牌山リスト
     * @param count 取り出す牌の枚数
     * @return 取り出した手牌リスト
     */
    public static List<TileType> dealHand(List<TileType> wall, int count) {
        if (wall.size() < count) {
            throw new IllegalArgumentException("牌山に残っている牌が不足しています。");
        }
        
        // 牌山の先頭から指定枚数のリストをサブリストとして取得
        List<TileType> hand = new ArrayList<>(wall.subList(0, count));
        
        // 牌山から取り出した牌を削除
        // 注意: subListのclear()は元のリストからも要素を削除します
        wall.subList(0, count).clear();
        
        return hand;
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
				if (is4Mentsu(handCounts) == true) {return true;
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
	public static boolean is4Mentsu(int[] handCounts) {
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
				is4Mentsu(handCounts);
			}
			try {
			if(handCounts[MaxTile+1]>=1 && handCounts[MaxTile+2]>=1) {
				handCounts[MaxTile]-=1;
				handCounts[MaxTile+1]-=1;
				handCounts[MaxTile+2]-=1;
				is4Mentsu(handCounts);
			}}catch(ArrayIndexOutOfBoundsException a) {}
			
		for(int j = 0;j<handCounts.length;j++) {
			if(handCounts[j]>=1) {return false;}
		}
		return true;
	}

}
