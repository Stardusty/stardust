/*William Moore
 * 
 * An Action Card, One time use cards, superclass for specific Action Cards.
 */ 
public abstract class ACard : Card
{
    public ACard(string name, string text, string edition, int code, bool foil, int cn, int pv_Gun, int pv_GP, int pv_M, int pv_S, int pv_W, int pv_C, int pv_Star, int sv, int bv, int rar, Image full, Image small, Image thumb)
	{
        super(name, text, edition, code, foil, cn, pv_Gun, pv_GP, pv_M, pv_S, pv_W, pv_C, pv_Star, sv, bv, rar, full, small, thumb);
	}

    /*William Moore
     * 
     * The effect of the Action Card.
     */
    public abstract void Special()
    {

    }
    /*William Moore
     * 
     * Attempts to Play an Action card.
     * match: Match within which the card is being played.
     *user: Player playing the card.
     * opponent: Player not playing the card.
     */
    public abstract void Play(Match match, Player user, Player opponent)
    {

    }
}
