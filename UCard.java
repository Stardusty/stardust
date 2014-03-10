/*William Moore 
 * 
 * Represents a Unit Card, in itself never used, only specific unit cards will be used.
 */
public abstract class UCard : Card
{
    private int c_HPM; //Maximum HP
    private int c_HP; //Current HP
    private int c_AP; //Attack Power
    private int c_Armor; // Armor Value
    private int c_M_Gun; //"M" stands for Mining Value, Gun is Gunpowder, GP is Gold Pieces, M is Magic, S is Steel, W is Wood, C is Cloth, Star is Star
    private int c_M_GP;
    private int c_M_M;
    private int c_M_S;
    private int c_M_W;
    private int c_M_C;
    private int c_M_Star;
    private boolean c_Mining; //States whether or not the card is set to a mining state.
    private boolean c_Usable; //Whether ot not this card is available to attack.
    private String c_Race;
    private String c_Class;
    private String c_Nick; //Nickname, for displaying on the card.
    private ECard c_Equip; //Card Equipped to this card.
    public UCard(string name, string text, string edition, int code, bool foil, int cn, int pv_Gun, int pv_GP, int pv_M, int pv_S, int pv_W, int pv_C, int pv_Star, int sv, int bv, int rar, Image full, Image small, Image thumb, int hpm, int hp, int ap, int armor, int m_Gun, int m_GP, int m_M, int m_S, int m_W, int m_C, int m_Star, String race, String cclass, String nick, Bitmap img)
	{
        super(name, text, edition, code, foil, cn, pv_Gun, pv_GP, pv_M, pv_S, pv_W, pv_C, pv_Star, sv, bv, rar, full, small, thumb);
        c_HPM = hpm;
        c_HP = hp;
        c_AP = ap;
        c_Armor = armor;
        c_M_Gun = m_Gun;
        c_M_GP = m_GP;
        c_M_M = m_M;
        c_M_S = m_S;
        c_M_W = m_W;
        c_M_C = m_C;
        c_M_Star = m_Star;
        c_Race = race;
        c_Class = cclass;
        c_Mining = true;
        c_Usable = true;
        c_Equip = null;
        c_Nick = nick;
	}

    /*William Moore
     * 
     * Plays the card, virtual method in case specific cards have effects that trigger when the card is played.
     * match: The match within which this card is played.
     * user: the Player who is playing the card.
     * opponent: the Player who is not playing the card. Only used if the method is overridden.
     */
    public virtual void Play(Match match, Player user, Player opponent)
    {
        if (user.playCheckR(this))
        {
            DCard target = ;//DCard selection method
            UCard temp = this;
            target.getUnits()[target.getOpenSpot()] = temp;
            user.subtractPVCost(this);
        }
    }

    /*William Moore
    * 
    * The special move of a unit card. For subclasses to override to pass an actual Special method that effects the game.
    */
    public abstract void Special()
    {

    }

    public int getAP()
    {
        return c_AP;
    }

    public int getArmor()
    {
        return c_Armor;
    }

    public int getHP()
    {
        return c_HP;
    }

    public void setAP(int ap)
    {
        c_AP = ap;
    }

    public void setArmor(int arm)
    {
        c_Armor = arm;
    }

    public void setHP(int hp)
    {
        c_HP = hp;
    }

    public int getM_Gun()
    {
        return c_M_Gun;
    }


    public int getM_GP()
    {
        return c_M_GP;
    }

    public int getM_M()
    {
        return c_M_M;
    }

    public int getM_S()
    {
        return c_M_S;
    }

    public int getM_W()
    {
        return c_M_W;
    }

    public int getM_C()
    {
        return c_M_C;
    }

    public int getM_Star()
    {
        return c_M_Star;
    }

    public boolean getMining()
    {
        return c_Mining;
    }

    public ECard getEquip()
    {    
        return c_Equip;
    }

    public void setEquip(ECard equip)
    {
        c_Equip = equip;
    }

    /*William Moore
    * 
    * Checks whether or not the target unit card is dead.
     * Returns: True if the Card has 0 or less HP, False if the Card has more than 0 HP.
    */
    public boolean checkDeath()
    {
        if (c_HP > 0)
            return false;
        else
            return true;
    }

}
