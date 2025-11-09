package main;

import java.util.List;

import tiles.TileType;

/**
 * 麻雀の面子(CP含むゲームの参加者)が持つべきメソッドを定義するインターフェースです。
 */
public interface Ments {
    /**
     * 打牌するメソッドです。
     * 
     * @param 手牌List
     * @return 打牌
     */
    TileType discard(List<TileType> hand);

    /**
     * 副露をするか判断するクラスです。
     * 
     * @param 他家の打牌
     */
    void call(TileType discard);

    /**
     * ポンをするメソッドです。
     * 
     * @param 他家の打牌
     * @return ポンした手牌リスト
     */
    List<TileType> pon(TileType discard);

    /**
     * チーをするメソッドです。
     * 
     * @param 他家の打牌
     * @return チーした手牌リスト
     */
    List<TileType> chii(TileType discard);

    /**
     * カンをするメソッドです。
     * 
     * @param 他家の打牌
     * @return カンした手牌リスト
     */
    List<TileType> kan(TileType discard);

}
