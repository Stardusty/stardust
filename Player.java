/*William Moore
 *
 * For use inside of a Match. Different from Account, as a Player is an instance of one of the two sides within a Match. Only exists within Matches, contains all the information for one side of a match.
 */ 
public class Player
{
    private Card[] p_hand; //Player's Hand
    private ECard[] p_armory; //Player's Armory. An Armory is where all of a Player's unused Equips currently are.
    private UCard[] p_attack; //Array used to organize an attacking force.
    private DCard p_Out1;
    private DCard p_Out2;
    private DCard p_Out3;
    private DCard p_Strong1;
    private DCard p_Strong2;
    private DCard p_Keep;
    private Deck p_deck;
    private String p_Name; //Name of the player, imported from an account.
    private int p_r_Gun; //r in this situation stands for the player's resources in each corresponding category.
    private int p_r_GP;
    private int p_r_M;
    private int p_r_S;
    private int p_r_W;
    private int p_r_C;
    private int p_r_Star;
    private int p_Control; //Sets the level of control for a user. 0 Being no control, 1 being the level of control over the player's play phase and cleanup phase, and 2 being their combat phase.
    private boolean p_isAI; //whether or not the player is an AI

	public Player(Set set, string name, bool ai)
	{
        p_hand = new Card[10];
        p_armory = new ECard[10];
        p_attack = new UCard[5];
        p_deck = set.getDeck();
        p_deck.shuffle();
        p_Name = name;
        p_r_Gun = 0;
        p_r_GP = 0;
        p_r_M = 0;
        p_r_S = 0;
        p_r_W = 0;
        p_r_C = 0;
        p_r_Star = 0;
        p_Control = 0;
        p_isAI = ai;
        p_Out1 = set.getOut1();
        p_Out2 = set.getOut2();
        p_Out3 = set.getOut3();
        p_Strong1 = set.getStrong1();
        p_Strong2 = set.getStrong2();
        p_Keep = set.getKeep();
	}

    public int getR_Gun()
    {
        return p_r_Gun;
    }

    public int getR_GP()
    {
        return p_r_GP;
    }

    public int getR_M()
    {
        return p_r_M;
    }

    public int getR_S()
    {
        return p_r_S;
    }

    public int getR_W()
    {
        return p_r_W;
    }

    public int getR_C()
    {
        return p_r_C;
    }

    public int getR_Star()
    {
        return p_r_Star;
    }

    public int getControl()
    {
        return p_Control;
    }

    public void setControl(int c)
    {
        p_Control = c;
    }

    public ECard[] getArmory()
    {
        return p_armory;
    }

    public DCard getOut1()
    {
        return p_Out1;
    }

    public DCard getOut2()
    {
        return p_Out2;
    }

    public DCard getOut3()
    {
        return p_Out3;
    }

    public DCard getStrong1()
    {
        return p_Strong1;
    }

    public DCard getStrong2()
    {
        return p_Strong2;
    }

    public DCard getKeep()
    {
        return p_Keep;
    }

    public void setR_Gun(int newval)
    {
        p_r_Gun = newval;
    }

    public void setR_GP(int newval)
    {
        p_r_GP = newval;
    }

    public void setR_M(int newval)
    {
        p_r_M = newval;
    }

    public void setR_S(int newval)
    {
        p_r_S = newval;
    }

    public void setR_W(int newval)
    {
        p_r_W = newval;
    }

    public void setR_C(int newval)
    {
        p_r_C = newval;
    }
    public void setR_Star(int newval)
    {
        p_r_Star = newval;
    } 
  
    /*William Moore
     * 
     * Checks whether or not the card can be played by comparing it to the player's resources.
     * card: The Card that is checked against the Player's resources.
     * returns: True if the card can be played, False if it cannot.
     */ 
    public boolean playCheckR(Card card)
    {
        if ((p_r_Gun - card.getPV_Gun()) < 0)
            return false;
        if ((p_r_GP - card.getPV_GP()) < 0)
            return false;
        if ((p_r_M - card.getPV_M()) < 0)
            return false;
        if ((p_r_S - card.getPV_S()) < 0)
            return false;
        if ((p_r_W - card.getPV_W()) < 0)
            return false;
        if ((p_r_C - card.getPV_C()) < 0)
            return false;
        if ((p_r_Star - card.getPV_Star()) < 0)
            return false;
        return true;
    }

    /*William Moore
     * 
     * Returns whether or not the Player can draw, and if it can, where it may draw to.
     * returns: The first available index in p_hand, or -1 if no such position is available.
     */
    public int CanDraw()
    {
        for(int i = 0; i < p_hand.Length; i++)
        {
            if(p_hand[i] == null)
            {
                p_hand[i] = p_deck.getCards()[p_deck.getCCount()];
                return i;
            }
        }
        return -1;
    }
    /*William Moore
     * 
     * Adds a card from the top of the deck to the hand, then reduces the deck's current index in order to simulate removing the top card of a deck.
     * iDrawTo: The index of p_hand where the Player is drawing to.
     */
    public void Draw(int iDrawTo)
    {
        p_hand[iDrawTo] = p_deck.getCards()[p_deck.getCCount()];
        p_deck.getCards()[p_deck.getCCount()] = null;
        p_deck.reduceCount();
    }
    /*William Moore
     * 
     * Moves a card from the armory to a unit.
     * equip: Equip Card to be equipped to a unit.
     * unit: Unit Card being equipped.
     */
    public void Equip(ECard equip, UCard unit)
    {
        if(unit.getEquip() == null)
        {
            ECard temp = equip;
            unit.setEquip(equip);
            unit.setAP(unit.getAP() + equip.getAPBuff());
            unit.setArmor(unit.getArmor() + equip.getArmBuff());
            unit.setHP(unit.getHP() + equip.getHPBuff());
            equip = null;
        }
    }

    /* William Moore
     * 
     * Removes an equip card from a unit card and sends it back to the armory. Will kill the card if it causes its hp to drop below 0.
     * unit: The unit card to be unequipped.
     */

    public void Unequip(UCard unit)
    {
        if(unit.getEquip() != null && getArmorySpot() != -1)
        {
            ECard temp = unit.getEquip();
            unit.setAP(unit.getAP() - temp.getAPBuff());
            unit.setArmor(unit.getArmor() - temp.getArmBuff());
            unit.setHP(unit.getHP() - temp.getHPBuff());
            unit.setEquip(null);
            p_armory[getArmorySpot()] = temp;
            if (unit.checkDeath())
                kill(unit);
        }
    }

    /*William Moore
     * 
     * Updates what Defence cards can be targetted by an opponent by checking if the two defences prior to that defence have been destroyed.
     */
 
    public void updateDCards()
    {
        if((p_Out1 == null) && (p_Out2 == null))
        {
            if (p_Strong1 != null)
                p_Strong1.setTarget(true);
        }
        if ((p_Out2 == null) && (p_Out3 == null))
        {
            if (p_Strong2 != null)
                p_Strong2.setTarget(true);
        }
        if ((p_Strong1 == null) && (p_Strong2 == null))
        {
            if (p_Keep != null)
                p_Keep.setTarget(true);
        }
    }
    /*William Moore
     * 
     * Checks whether or not a spot in the armory is available, if there is, returns what spot is available.
     * returns: First available index in p_armory, or -1 if armory is full.
     */
    public int getArmorySpot()
    {
        for(int i = 0; i < 10; i++)
        {
            if (p_armory[i] == null)
                return i;
        }
        return -1;
    }

    /*William Moore
     * 
     * Subtracts a Card's playcosts from a Player's resource count. Usually used when a Card is played.
     * card: Card whose play values are being subtracted from the Player.
     */
    public void subtractPVCost(Card card)
    {
        p_r_Gun -= card.getPV_Gun();
        p_r_GP -= card.getPV_GP();
        p_r_M -= card.getPV_M();
        p_r_S -= card.getPV_S();
        p_r_W -= card.getPV_W();
        p_r_C -= card.getPV_C();
        p_r_Star -= card.getPV_Star();
    }

    /*William Moore
     * 
     *Removes a Defence Card from play.
     * target: DCard to be destroyed.
     */
    public void destroyDCard(DCard target)
    {
        //Line of code to prompt user to move cards out of area. !!!***!!!
        target = null;
        updateDCards();
    }

    /*William Moore
     *
     *Kills a Unit Card, and moves its Equip Card to the Armory is he owns one.
     *deathrow: UCard to be destroyed.
     */
    public void kill(UCard deathrow)
    {
        if (deathrow.getEquip() != null && getArmorySpot() != -1)
        {
            ECard temp = deathrow.getEquip();
            p_armory[getArmorySpot()] = temp;
        }
        deathrow = null;
    }

    /*William Moore
     * 
     * Moves a unit from one defence to another, as long as they are not full.
     * unit: Unit Card to be moved
     * to: destination of that card
     */
    public void moveUnit(UCard unit, DCard to)
    {
        if(to.getOpenSpot() != -1)
        {
            UCard temp = unit;
            to.getUnits()[to.getOpenSpot()] = temp;
            unit = null;
        }
    }
}
