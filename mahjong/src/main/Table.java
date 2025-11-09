package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tiles.TileType;

public class Table {
    private List<TileType> wall; // 牌山
    private List<TileType> discard; // 打牌
    private List<TileType> discardTiles; // 捨て牌
    private TileType dora; // ドラ表示牌
    private int turn; // 巡目
    private int honba; // ◯本場
    private int riichiStick; // リーチ棒の本数
    private int pon; // ポン。playersTableみたいに各人のテーブルに持たせる？
    private int chii; // チー。playersTableみたいに各人のテーブルに持たせる？
    private int kan; // カン。playersTableみたいに各人のテーブルに持たせる？

    public static void sortHand(List<TileType> hand) {
        /**
         * 手牌を萬子、筒子、索子、風牌、三元牌の順でソートする
         * 
         * @param ソート前の手牌リスト
         * @return ソート後の手牌リスト
         */
        Collections.sort(hand);
    }

    public static List<TileType> createWall() {
        /**
         * 136枚の牌を格納した牌山リストを生成する
         * 
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

    /**
     * シャッフルされた牌山リストを生成する
     * 
     * @return
     */
    public static List<TileType> createShuffledWall() {
        List<TileType> wall = createWall();

        // 牌山をシャッフル
        Collections.shuffle(wall);

        return wall;
    }

    /**
     * 牌山から指定された枚数（配牌）を取り出し、牌山から削除する
     * 
     * @param wall  シャッフルされた牌山リスト
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
        sortHand(hand);

        return hand;
    }

}
