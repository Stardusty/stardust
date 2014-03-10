/*William Moore
 * 
 * An instance of a Match or game. Where most calculations for a specific game instance take place.
 */ 
public class Match
{
    private Player m_Player1;
    private Player m_Player2;
    private boolean m_P_Lock; //For use in a next phase button, which allows players to end phases that do not automatically execute.
    private int m_Turn; //Turn number.
    private int m_Phase; //An int correspnding to the current phase.
    private Random m_Random;
    private String m_PhaseString; //Current phase. For displaying to the User.

	public Match(Player player1, Player player2)
	{
        m_Player1 = player1;
        m_Player2 = player2;
        m_Turn = 1;
        m_Random = new Random();
        for (int i = 0; i < 7; i++)
        {
            m_Player1.Draw(m_Player1.CanDraw());
            m_Player2.Draw(m_Player2.CanDraw());
        }
        if (m_Random.Next(100) > 50)
            m_Phase = 6; 
        else
            m_Phase = 1;
        m_PhaseString = "";
        m_P_Lock = true;
	}
    /*William Moore
     * 
     * Advances to the next phase, cycles back to the first phase if the last phase is reached.
     */
    public void nextPhase()
    {
        m_Phase++;
        if (m_Phase == 10)
        {
            m_Phase = 0;
        }
        switch (m_Phase)
        {
            case 0:
                drawPhase(m_Player1);
                break;
            case 1:
                playPhase(m_Player1);
                break;
            case 2:
                combatPhase(m_Player1);
                break;
            case 3:
                cleanupPhase(m_Player1);
                break;
            case 4:
                endPhase(m_Player1);
                break;
            case 5:
                drawPhase(m_Player2);
                break;
            case 6:
                playPhase(m_Player2);
                break;
            case 7:
                combatPhase(m_Player2);
                break;
            case 8:
                cleanupPhase(m_Player2);
                break;
            case 9:
                endPhase(m_Player2);
                break;

        }
    }

    public int getPhase()
    {
        return m_Phase;
    }
    public Player getP1()
    {
        return m_Player1;
    }

    public Player getP2()
    {
        return m_Player2;
    }

    public void setP_Lock(bool p_lock)
    {
        m_P_Lock = p_lock;
    }

    /*William Moore
     * 
     * Handles the Drawing Phase of a turn.
     * curPlayer: The Player whose turn it is.
     */ 
    public void drawPhase(Player curPlayer)
    {
        m_PhaseString = "Draw Phase";
        curPlayer.setControl(0);
        while (curPlayer.CanDraw() == -1)
            break; //replace with loop that requires player to discard
        curPlayer.Draw(curPlayer.CanDraw());
        nextPhase();
    }
    /*William Moore
     * 
     * Handles the Play Phase of a turn.
     * curPlayer: The Player whose turn it is.
     */ 
    public void playPhase(Player curPlayer)
    {
        m_PhaseString = "Play Phase";
        curPlayer.setControl(1);
        while(m_P_Lock)
        {

        }
        m_P_Lock = true;
        curPlayer.setControl(0);
        nextPhase();
    }
    /*William Moore
     * 
     * Handles the Combat Phase of a turn.
     * curPlayer: The Player whose turn it is.
     */ 
    public void combatPhase(Player curPlayer)
    {
        m_PhaseString = "Combat Phase";
        curPlayer.setControl(2);
        while(m_P_Lock)
        {

        }
        m_P_Lock = true;
        curPlayer.setControl(0);
        nextPhase();
    }
    /*William Moore
     * 
     * Handles the Cleanup Phase of a turn.
     * curPlayer: The Player whose turn it is.
     */ 
    public void cleanupPhase(Player curPlayer)
    {
        m_PhaseString = "Cleanup Phase";
        curPlayer.setControl(1);
        while (m_P_Lock)
        {

        }
        m_P_Lock = true;
        curPlayer.setControl(0);
        nextPhase();
    }

    /*William Moore
     * 
     * Handles the End Phase of a turn.
     * curPlayer: The Player whose turn it is.
     */ 
    public void endPhase(Player curPlayer)
    {
        m_PhaseString = "End Phase";
        curPlayer.setControl(0);
        int[] vals = new int[7];
        for (int i = 0; i < 7; i ++)
        {
            vals[i] = 0;
        }

        if(curPlayer.getOut1() != null)
        {
            for(int i = 0; i < 7; i++)
            {
                vals[i] += curPlayer.getOut1().getM_Vals()[i];
            }
        }

        if (curPlayer.getOut2() != null)
        {
            for (int i = 0; i < 7; i++)
            {
                vals[i] += curPlayer.getOut2().getM_Vals()[i];
            }
        }

        if (curPlayer.getOut3() != null)
        {
            for (int i = 0; i < 7; i++)
            {
                vals[i] += curPlayer.getOut1().getM_Vals()[i];
            }
        }

        if (curPlayer.getStrong1() != null)
        {
            for (int i = 0; i < 7; i++)
            {
                vals[i] += curPlayer.getOut1().getM_Vals()[i];
            }
        }

        if (curPlayer.getStrong2() != null)
        {
            for (int i = 0; i < 7; i++)
            {
                vals[i] += curPlayer.getOut1().getM_Vals()[i];
            }
        }

        if (curPlayer.getKeep() != null)
        {
            for (int i = 0; i < 7; i++)
            {
                vals[i] += curPlayer.getKeep().getM_Vals()[i];
            }
        }

        m_Turn++;
        nextPhase();
    }

    /* Phase List
     * Draw Phase
     * Play Phase
     * Combat Phase
     * Cleanup Phase
     * End Phase
     */

    /*William Moore
     * 
     * handles an attack between the two Players. Will destroy the attack site if the attack is a success.
     * attack: the array filled with the UCards of the attacking player.
     * defend: the array filled with the UCards of the defending player.
     * site: the DCard where the battle is ocurring. The attacker's target, and the defender's property.
     * attacker: The player attacking.
     * defender: The player defending.
     */ 
    public void superCombat(UCard[] attack, UCard[] defend, DCard site, Player attacker, Player defender)
    {
        int a_index = 0;
        int d_index = 0;
        int a_bonus = site.getABonus();
        int d_bonus = site.getDBonus();
        boolean initial = false;
        while(initial == false)
        {
            if(a_index == 5 || attack[a_index] == null)
            {
                site.setHPcur(site.getHP());
                attacker.kill(attack[0]);
                break;
            }
            site.setHPcur(site.getHPcur() - attack[a_index].getAP());
            if(site.getHPcur() <= 0)
            {
                initial = true;
            }
            else
                a_index++;
        }
        while (attack[a_index] != null && defend[d_index] != null && initial)
        {
            if (a_index == 5 || attack[a_index] == null)
            {
                site.setHPcur(site.getHP());
                break;
            }
            if (d_index == 5 || defend[d_index] == null)
            {
                for (int i = 0; i < 5; i++)
                {
                    if (defend[i] != null && defend[i].checkDeath())
                        defender.kill(defend[i]);
                }
                defender.destroyDCard(site);
                break;
            }
            smallCombat(attack[a_index], defend[d_index], site.getABonus(), site.getDBonus());
            if (attack[a_index].checkDeath())
                attacker.kill(attack[a_index]);
            if (defend[d_index].checkDeath())
                defender.kill(defend[d_index]);
            if (attack[a_index].checkDeath() || !(defend[d_index].checkDeath()))
                a_index++;
            if (defend[d_index].checkDeath())
                d_index++;
        }
        for(int i = 0; i < 5; i++)
        {
            if (attack[i] != null && attack[i].checkDeath())
                attacker.kill(attack[i]);
            if (defend[i] != null && defend[i].checkDeath())
                defender.kill(defend[i]);
        }
    }
    /*William Moore
     *
     * Handles the combat between two cards.
     * attacker: The UCard that is attacking.
     * defender: The UCard that is defending.
     * a: The attack bonus for the defender.
     * d: The armor bonus for the defender.
     */
    public void smallCombat(UCard attacker, UCard defender, int a, int d)
    {
        int attackerDMG = attacker.getAP() - defender.getArmor() - d;
        if (attackerDMG < 0)
            attackerDMG = 0;
        int defenderDMG = defender.getAP() + a - attacker.getArmor();
        if (defenderDMG < 0)
            defenderDMG = 0;
        attacker.setHP(attacker.getHP() - defenderDMG);
        defender.setHP(defender.getHP() - attackerDMG);
    }

    /*William Moore
     * 
     * Calls the updateDCards method for both Players, then checks whether to see their Keep is destroyed. If their Keep is destroyed, calls upon concludeMatch
     * account1: The account corresponding to Player 1.
     * account2: The account corresponding to Player 2.
     */
    public void checkDCard(Account account1, Account account2)
    {
        m_Player1.updateDCards();
        m_Player2.updateDCards();
        if (m_Player1.getKeep() == null)
            concludeMatch(account2, account1);
        if (m_Player2.getKeep() == null)
            concludeMatch(account1, account2);

    }

    /*William Moore
     * 
     * Concludes the match and handles new score calculations for each of the participating accounts.
     * winner: Account corresponding to the winning player.
     * loser: Account corresponding to the losing player.   
     */
    public void concludeMatch(Account winner, Account loser)
    {
        winner.win();
        loser.lose();
        int temp = winner.getScore() - loser.getScore();
        temp = (int) Math.Exp((double) (-temp/100)); //For changing the score to be added or subtracted based on the score of each account.
        winner.setScore(winner.getScore() + temp + 10);
        loser.setScore(loser.getScore() - temp - 7);
    }
}
