package main;

import java.util.List;

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

    // コンストラクタ
    public Player() {
        super();
    }

    // メソッド
    @Override
    public TileType discard(List<TileType> wall) {
        return null;
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

}
