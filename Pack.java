using System;
using System.Drawing;
/*William Moore
 *
 * A Pack of cards. Does not contain any cards itself, but rather a method to generate 8 cards, then disappear. Exists in shops and account inventories.
 */ 
public class Pack
{
    private String pk_Name;
    private Random pk_Random;
    private int pk_buy; //Buy Value
    private int pk_sell; //Sell Value
    private int pk_CommonSize; //Amount of Common Cards in this pack
    private int pk_UncommonSize; //Amount of Uncommon Cards in this pack
    private int pk_RareSize; //Amount of Rare Cards in this pack
    private int pk_SuperSize; //Amount of Super Rare Cards in this pack
    private int pk_UltraSize; //Amount of Ultra Rare Cards in this Pack
    private Card[] pk_CommonList; //An array full of Common Cards in this pack
    private Card[] pk_UncommonList; //An array full of Uncommon Cards in this pack
    private Card[] pk_RareList; //An array full of Rare Cards in this pack
    private Card[] pk_SuperList; //An array full of Super Rare Cards in this pack
    private Card[] pk_UltraList; //An array full of Ultra Rare Cards in this pack
    private Image pk_Image; //The image for this pack
	public Pack(string name, int buy, int sell, Card[] common, Card[] uncommon, Card[] rare, Card[] super, Card[] ultra, Image image)
	{
        pk_Name = name;
        pk_Random = new Random();
        pk_buy = buy;
        pk_sell = sell;
        pk_CommonList = common;
        pk_UncommonList = uncommon;
        pk_RareList = rare;
        pk_SuperList = super;
        pk_UltraList = ultra;
        pk_CommonSize = pk_CommonList.Length;
        pk_UncommonSize = pk_UncommonList.Length;
        pk_RareSize = pk_RareList.Length;
        pk_SuperSize = pk_SuperList.Length;
        pk_UltraSize = pk_UltraList.Length;
        pk_Image = image;
	}

    /*William Moore
     * 
     * Returns 8 cards from this pack, randomly selected from different lists to match their rarity.
     * returns: An array of Cards of size 8, containing 5 Common Cards, 2 Uncommon Cards, and 1 Rare, Super Rare, or Ultra Rare Card.
     */
    public Card[] OpenPack()
    {
        Card[] cards = new Card[8];
        cards[0] = pk_CommonList[pk_Random.Next(pk_CommonSize)];
        cards[1] = pk_CommonList[pk_Random.Next(pk_CommonSize)];
        cards[2] = pk_CommonList[pk_Random.Next(pk_CommonSize)];
        cards[3] = pk_CommonList[pk_Random.Next(pk_CommonSize)];
        cards[4] = pk_CommonList[pk_Random.Next(pk_CommonSize)];
        cards[5] = pk_UncommonList[pk_Random.Next(pk_UncommonSize)];
        cards[6] = pk_UncommonList[pk_Random.Next(pk_UncommonSize)];
        int temp = pk_Random.Next(100);
        if (temp < 70)
            cards[7] = pk_RareList[pk_Random.Next(pk_RareSize)];
        else
        {
            if (temp < 95)
                cards[7] = pk_SuperList[pk_Random.Next(pk_SuperSize)];
            else
                cards[7] = pk_UltraList[pk_Random.Next(pk_UltraSize)];
        }
        return cards;
    }
}
