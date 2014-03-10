/*William Moore
 *
 * A Node among a binary search tree of user Accounts, for searching and locating accounts in the case of logging in and changing information about an account.
 */ 
public class Node //For creating the binary search tree of all acounts.
{
    private Node lChild; //Left Child in the Tree
    private Node rChild; //Right Child in the Tree
    private Account nodeAccount; //Account contained within this tree
	public Node(Account na)
	{
        nodeAccount = na;
	}

    /*William Moore
     * 
     * Attemps to create a new account within the tree.
     * name: The name of the desired account.
     * password: The password of the desired account.
     * returns: True if the account is successfully created, false if it is not. An account will fail to create if that username is already taken.
     */
    public bool createAccount(string name, string password)
    {
        if (name.Equals(nodeAccount.getName()))
            return false;
        else
            if(name.CompareTo(nodeAccount.getName()) > 0)
            {
                if (rChild == null)
                {
                    rChild = new Node(new Account(name, password));
                    return true;
                }
                else
                    return rChild.createAccount(name, password);
            }
            else
            {
                if (lChild == null)
                {
                    lChild = new Node(new Account(name, password));
                    return true;
                }
                else
                    return lChild.createAccount(name, password);
            }
    }
    /*William Moore
     * 
     * attemps to login a user to a specified node, then heads on to the next node if it fails.
     * name: Username that is attempting to login.
     * password: Password that is being used in the login attempt.
     * returns: True if the username and password match those of this current node, False if the child on the next side is null and the username and password do not work.
     */
    public boolean logIn(string name, string password)
    {
        if (name.Equals(nodeAccount.getName()))
        {
            if (nodeAccount.getPassword().Equals(password))
                return true;
            else
                return false;
        }
        else
        {
            if (name.CompareTo(nodeAccount.getName()) > 0)
            {
                if (rChild == null)
                    return false;
                else
                    return rChild.logIn(name, password);
            }
            else
            {
                if (lChild == null)
                    return false;
                else
                    return lChild.logIn(name, password);
            }
        }
    }
}
