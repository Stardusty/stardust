/*William Moore
 * 
 * An Equip Card, in itself this class is never instanciated, only specific Equip Cards will be.
 */
public abstract class ECard extends Card
{
    private int c_ArmBuff;
    private int c_APBuff;
    private int c_HPBuff;
    public ECard(string name, string text, string edition, int code, bool foil, int cn, int pv_Gun, int pv_GP, int pv_M, int pv_S, int pv_W, int pv_C, int pv_Star, int sv, int bv, int rar, Image full, Image small, Image thumb, int armbuff, int apbuff, int hpbuff)
        : base(name, text, edition, code, foil, cn, pv_Gun, pv_GP, pv_M, pv_S, pv_W, pv_C, pv_Star, sv, bv, rar, full, small, thumb)
	{
        c_ArmBuff = armbuff;
        c_APBuff = apbuff;
        c_HPBuff = hpbuff;
	}
    /*William Moore
     * 
     *Attemps to play an Equip Card into the Armory.
     *match: The Match all actions are ocurring.
     *user: Player using the card.
     *opponent: Player not using the card.
     */ 
    public void Play(Match match, Player user, Player opponent)
    {
        if (user.playCheckR(this))
        {
            if(user.getArmorySpot() != -1)
            {
                ECard temp = this;
                user.getArmory()[user.getArmorySpot()] = temp;
                user.subtractPVCost(this);
            }
        }
    }
    /*William Moore
     * 
     * Special move of the card. To be overidden if such a special exists.
     */
    public void Special()
    {

    }

    public int getArmBuff()
    {
        return c_ArmBuff;
    }

    public int getAPBuff()
    {
        return c_APBuff;
    }

    public int getHPBuff()
    {
        return c_HPBuff;
    }
}
