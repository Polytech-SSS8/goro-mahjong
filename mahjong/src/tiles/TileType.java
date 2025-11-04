package tiles;

public enum TileType{
	// 萬子 (マンズ)
    MAN1(0, "M1", "一"), MAN2(1, "M2", "二"), MAN3(2, "M3", "三"), MAN4(3, "M4", "四"), MAN5(4, "M5", "五"), 
    MAN6(5, "M6", "六"), MAN7(6, "M7", "七"), MAN8(7, "M8", "八"), MAN9(8, "M9", "九"),

    // 筒子 (ピンズ)
    PIN1(9, "P1", "①"), PIN2(10, "P2", "②"), PIN3(11, "P3", "③"), PIN4(12, "P4", "④"), PIN5(13, "P5", "⑤"), 
    PIN6(14, "P6", "⑥"), PIN7(15, "P7", "⑦"), PIN8(16, "P8", "⑧"), PIN9(17, "P9", "⑨"),

    // 索子 (ソウズ)
    SOU1(18, "S1", "1"), SOU2(19, "S2", "2"), SOU3(20, "S3", "3"), SOU4(21, "S4", "4"), SOU5(22, "S5", "5"), 
    SOU6(23, "S6", "6"), SOU7(24, "S7", "7"), SOU8(25, "S8", "8"), SOU9(26, "S9", "9"),

    // 字牌 (ジハイ)
    EAST(27, "E", "東"), SOUTH(28, "S", "南"), WEST(29, "W", "西"), NORTH(30, "N", "北"), 
    HAKU(31, "WHT", "白"), HATSU(32, "GRN", "發"), CHUN(33, "RED", "中");

    // --- フィールド、コンストラクタ、メソッド ---

    private final int id;
    private final String name;//M1,M2...
    private final String name2;//一、二...

    /**
     * Enum定数に対応するIDを設定するコンストラクタ
     * @param id 0から33の牌の一意なID
     * @param name 牌の短い名前（略記）
     * @param name2 牌の表示名（漢字、記号）
     */
    TileType(int id, String name,String name2) {
        this.id = id;
        this.name = name;
        this.name2 = name2;
    }

    /**
     * 牌の一意なIDを取得します。
     * @return 牌のID
     */
    public int getId() {
        return id;
    }
    
    /**
     * 牌の短い名前（略記）を取得します。
     * @return 牌の略記名
     */
    public String getName() {
        return name;
    }
    
    /**
     * 牌の表示名（漢字など）を取得します。
     * @return 牌の表示名
     */
    public String getName2() {
        return name2;
    }
    
}
