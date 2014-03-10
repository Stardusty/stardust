import java.util.Random;

/*William Moore
 * 
 * A Deck of cards, exists within a set, and within players. Contains no DCards.
 */
public class Deck
{
    private Card[] d_cards;
    private int d_cCount; //Index of the top card on the deck. Starts out as the length of the deck -1, and decrements each time a card is drawn.
    private Random d_shuffler;

	public Deck(Card[] cards)
	{
        d_cards = cards;
        for(int i = 0; i < cards.Length; i++)
            if(d_cards[i] == null)
            {
                d_cCount = i;
                break;
            }
	}

    public int getCCount()
    {
        return d_cCount;
    }
    /*William Moore
     * 
     * Decrements d_cCount by 1.
     */
    public void reduceCount()
    {
        d_cCount--;
    }

    public Card[] getCards()
    {
        return d_cards;
    }
    /*William Moore
     * 
     * Shuffles the deck by swapping.
     */
    public void shuffle()
    {
        for (int i = d_cCount - 1; i > 0; i--)
        {
            int index = d_shuffler.Next(i + 1);
            Card a = d_cards[index];
            d_cards[index] = d_cards[i];
            d_cards[i] = a;
        }
    }
}
