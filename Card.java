/*William Moore
 * 
 * Superclass for all the different types of cards.
 */
public abstract class Card
{
    private String c_Name; //Name of the Card
    private String c_Text; //Text on the Card
    private String c_Edition; //Card Pack
    private int c_Code; //Code for Import and Export Methods - 6 Digits Long. Digit 1 - Foil, Digit 2/3 - Pack, Digit 4-6, Pack ID
    private boolean c_Foil; //Whether or not it is foil
    private int c_CN; //Card number inside the pack
    private int c_PV_Gun; //"PV" stands for play value
    private int c_PV_GP;
    private int c_PV_M;
    private int c_PV_S;
    private int c_PV_W;
    private int c_PV_C;
    private int c_PV_Star;
    private int c_SV; //Sell Value
    private int c_BV; //Buy Value
    private int c_Rar; //Rarity
    private Image c_F_Img; //Full size image
    private Image c_S_Img; //Small size image
    private Image c_Thumb; //Image Thumbnail

	public Card(string name, string text, string edition, int code, bool foil, int cn, int pv_Gun, int pv_GP, int pv_M, int pv_S, int pv_W, int pv_C, int pv_Star, int sv, int bv, int rar, Image full, Image small, Image thumb)
	{
        c_Name = name;
        c_Text = text;
        c_Edition = edition;
        c_Code = code;
        c_Foil = foil;
        c_CN = cn;
        c_PV_Gun = pv_Gun;
        c_PV_GP = pv_GP;
        c_PV_M = pv_M;
        c_PV_S = pv_S;
        c_PV_W = pv_W;
        c_PV_C = pv_C;
        c_PV_Star = pv_Star;
        c_SV = sv;
        c_BV = bv;
        c_Rar = rar;
        c_F_Img = full;
        c_S_Img = small;
        c_Thumb = thumb;
	}

    public String getName()
    {
        return c_Name;
    }

    public String getText()
    {
        return c_Text;
    }

    public String getEdition()
    {
        return c_Edition;
    }

    public int getCode()
    {
        return c_Code;
    }

    public boolean getFoil()
    {
        return c_Foil;
    }

    public int getCN()
    {
        return c_CN;
    }

    public int getPV_Gun()
    {
        return c_PV_Gun;
    }

    public int getPV_GP()
    {
        return c_PV_GP;
    }
    
    public int getPV_M()
    {
        return c_PV_M;
    }

    public int getPV_S()
    {
        return c_PV_S;
    }

    public int getPV_W()
    {
        return c_PV_W;
    }

    public int getPV_C()
    {
        return c_PV_C;
    }

    public int getPV_Star()
    {
        return c_PV_Star;
    }
    /*William Moore
     * 
     * Attempts to Play a card.
     * match: Match within a card is being played.
     * user: Player playing the card.
     * opponent: Player not playing the card.
     */
    public abstract void Play(Match match, Player user, Player opponent)
    {
        
    }
    /*William Moore
     * 
     * A Card's special, to be overidden by different cards.
     */
    public abstract void Special()
    {

    }
}
