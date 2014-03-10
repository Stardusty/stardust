using System;
/*William Moore
 * 
 * An Account, two types of Accounts exist, AI accounts, and human Accounts. Human accounts contain all things a User owns, as well as their score and records.
 */
public class Account
{
    private String a_UN; //Username
    private String a_PW; //Password
    private boolean a_isAI; //Represents whether or not this is an AI account
    private int a_GP; //Gold Pieces
    private int a_Wins; //Number of wins
    private int a_Losses; //Number of losses
    private int a_PVP_Wins; //Number of wins from pvp
    private int a_PVP_Losses; //Number of losses from pvp
    private int a_Score; //The player's score
    private int[] a_Cards; //Contains all Normal Cards the player owns.
    private int[] a_FoilCards; //Contains all Foil Cards the player owns
    private int[] a_PackInventory; //Contains Packs the player owns
    private int[] a_MiscInventory; //Contains Tropthies, Awards, Etc.
    private Set a_Set; //The Player's set

	public Account (string UN, string PW) //The constructor in case a new human Account is being created.
	{
        a_UN = UN;
        a_PW = PW;
        a_isAI = false;
        a_GP = 100;
        a_Wins = 0;
        a_Losses = 0;
        a_PVP_Wins = 0;
        a_PVP_Losses = 0;
        a_Score = 500;
        a_Set = new Set(); //to be filled with starter set
	}

    public Account (int score, Set set, string name) //Constructor for an AI Account
    {
        a_isAI = true;
        a_Score = score;
        a_Set = set;
        a_UN = name;
    }

    public boolean getIsAI()
    {
        return a_isAI;
    }
    public void win()
    {
        a_Wins++;
    }

    public void lose()
    {
        a_Losses++;
    }

    public void pvpWin()
    {
        a_PVP_Wins++;
    }

    public void pvpLoss()
    {
        a_PVP_Losses++;
    }

    public int getScore()
    {
        return a_Score;
    }

    public void setScore(int score)
    {
        a_Score = score;
    }

    public void setGP(int gp)
    {
        a_GP = gp;
    }

    public String getName()
    {
        return a_UN;
    }

    public String getPassword()
    {
        return a_PW;
    }
    /*William Moore
     * 
     * Takes an array of Cards and adds them to the user's inventory.
     * pack: array of Cards to be added to the user's inventory.
     */
    public void packTransfer(Card[] pack)
    {
        a_Cards[pack[0].getCode()]++;
        a_Cards[pack[1].getCode()]++;
        a_Cards[pack[2].getCode()]++;
        a_Cards[pack[3].getCode()]++;
        a_Cards[pack[4].getCode()]++;
        a_Cards[pack[5].getCode()]++;
        a_Cards[pack[6].getCode()]++;
        a_Cards[pack[7].getCode()]++;
    }
}
