
/*William Moore
 * 
 * Defence Card, in itself never instanciated, areas in which UCards are contained. Different from most cards as a Player starts with them played.
 */
public abstract class DCard extends Card
{
    private int c_DType; //1 Represents an Outpost, 2 Represents a Stronghold, 3 Represents a Keep
    private int c_ABonus; //The Attack Bonus for defenders in this position
    private int c_DBonus; //The Armor Bonus for this position
    private int c_HP; // The Health of this location
    private int c_HPcur; //Current Health of this location
    private int c_GunBonus; //The resource production bonuses for mining in this area.
    private int c_GPBonus;
    private int c_MBonus;
    private int c_SBonus;
    private int c_WBonus;
    private int c_CBonus;
    private int c_StarBonus;
    private UCard[] c_Units;
    private bool c_Target;

    public DCard(string name, string text, string edition, int code, bool foil, int cn, int pv_Gun, int pv_GP, int pv_M, int pv_S, int pv_W, int pv_C, int pv_Star, int sv, int bv, int rar, Image full, Image small, Image thumb, int dt, int ab, int db, int hp, int gunb, int gpb, int mb, int sb, int wb, int cb, int starb)
	{
        super(name, text, edition, code, foil, cn, pv_Gun, pv_GP, pv_M, pv_S, pv_W, pv_C, pv_Star, sv, bv, rar, full, small, thumb);
        c_DType = dt;
        c_ABonus = ab;
        c_DBonus = db;
        c_HP = hp;
        c_HPcur = hp;
        c_GunBonus = gunb;
        c_GPBonus = gpb;
        c_MBonus = mb;
        c_SBonus = sb;
        c_WBonus = wb;
        c_CBonus = cb;
        c_StarBonus = starb;
        c_Units = new UCard[10];
        if (c_DType == 1)
            c_Target = true;
        else
            c_Target = false;

	}
    /*William Moore
     * 
     * Gets the mining values of all resources produced in the Defence this turn.
     * returns: an array of size 7, that contains ints which represent the amount of resources mined per term in their corresponding type.
     */
    public int[] getM_Vals()
    {
        int[] vals = new int[7];
        boolean bonus = false;
        for (int i = 0; i < 7; i++)
        {
            vals[i] = 0;
        }

        for (int i = 0; i < 10; i++)
        {
            if (c_Units[i] != null && c_Units[i].getMining())
            {
                bonus = true;
                vals[0] += c_Units[i].getM_Gun();
                vals[1] += c_Units[i].getM_GP();
                vals[2] += c_Units[i].getM_M();
                vals[3] += c_Units[i].getM_S();
                vals[4] += c_Units[i].getM_W();
                vals[5] += c_Units[i].getM_C();
                vals[6] += c_Units[i].getM_Star();
            }
        }

        if(bonus == true)
        {
            vals[0] += c_GunBonus;
            vals[1] += c_GPBonus;
            vals[2] += c_MBonus;
            vals[3] += c_SBonus;
            vals[4] += c_WBonus;
            vals[5] += c_CBonus;
            vals[6] += c_StarBonus;
        }
        return vals;
    }

    public boolean getTarget()
    {
        return c_Target;
    }

    public int getABonus()
    {
        return c_ABonus;
    }

    public int getDBonus()
    {
        return c_DBonus;
    }

    public int getHP()
    {
        return c_HP;
    }

    public int getHPcur()
    {
        return c_HPcur;
    }

    public void setHPcur(int hp)
    {
        c_HPcur = hp;
    }

    public Card[] getUnits()
    {
        return c_Units;
    }

    public void setTarget(bool set)
    {
        c_Target = set;
    }
    /*William Moore
     * 
     * Special of the Card, to be overridden if such a special exists.
     */
    public void Special()
    {

    }

    /*William Moore
     * 
     * Checks whether or not there is an open spot in the defence, if there is, returns the first available open spot.
     * returns: First open index in c_Units, or -1 if c_Units is full.
     */
    public int getOpenSpot()
    {
        for (int i = 0; i < 10; i++)
            if (c_Units[i] == null)
                return i;
        return -1;
    }
    /*William Moore
     * 
     * TRASH METHOD: Taken from the abstract Play method of Card.
     */
    public void Play(Match match, Player user, Player opponent)
    {

    }
}
