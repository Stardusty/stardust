/*William Moore
    * 
    * Contains a deck, as well as 6 defences. The cards a player enters a game with, one is tied to each account and can be modified outside games if it is a non AI account.
    */
public class Set 
{
    private Deck s_Deck; //The main deck within the set.
    private DCard s_Out1; //Outpost 1
    private DCard s_Out2; //Outpost 2
    private DCard s_Out3; //Outpost 3
    private DCard s_Strong1; //Stronghold 1
    private DCard s_Strong2; //Stronghold 2
    private DCard s_Keep; //Stronghold 3
	public Set(Deck deck, DCard out1, DCard out2, DCard out3, DCard strong1, DCard strong2, DCard keep)
	{
        s_Deck = deck;
        s_Out1 = out1;
        s_Out2 = out2;
        s_Out3 = out3;
        s_Strong1 = strong1;
        s_Strong2 = strong2;
        s_Keep = keep;
	}

    public Deck getDeck()
    {
        return s_Deck;
    }

    public DCard getOut1()
    {
        return s_Out1;
    }

    public DCard getOut2()
    {
        return s_Out2;
    }

    public DCard getOut3()
    {
        return s_Out3;
    }

    public DCard getStrong1()
    {
        return s_Strong1;
    }

    public DCard getStrong2()
    {
        return s_Strong2;
    }

    public DCard getKeep()
    {
        return s_Keep;
    }
    /*William Moore
     * 
     * Using the codes of the cards in the set, returns a string containing all of them for importing and exporting decks.
     * 
     */
    public String toString()
    {
        String temp = "";
        temp = temp + s_Out1.getCode();
        temp = temp + " " + s_Out2.getCode();
        temp = temp + " " + s_Out3.getCode();
        temp = temp + " " + s_Strong1.getCode();
        temp = temp + " " + s_Strong2.getCode();
        temp = temp + " " + s_Keep.getCode();
        for(int i = 0; i < s_Deck.getCards().Length; i++)
        {
            temp = temp + " " + s_Deck.getCards()[i].getCode();
        }
        return temp;
    }

    /*William Moore
     * 
     * Takes a string and converts this set into the set containing cards to the corresponding strings. Returns whether or not the conversion was successful.
     * cheese: String to be converted into a set.
     * returns: Whether the set was set to the set given by cheese
     */

    public boolean stringToSet(String cheese) //TO BE FINISHED
    {
        if (cheese.Length < 251)
            return false;
        
    }
}
