package src.models;

public class SpendItem
{
    public SpendItem(String name, double spent)
    {
        setName(name);

        if(spent >= 0)
            setSpent(spent);
        else
            setSpent(DEFAULT_SPENT);
    }

    public String getName() { return m_Name; }
    public double getSpent() { return m_Spent; }

    public void setName(String name) { m_Name = name; } 
    public void setSpent(double spent) { m_Spent = spent; } 

    public void addSpent(double spent) { m_Spent += spent; } 
    public void removeSpent(double spent) { m_Spent -= spent; } 

    /* ---------------------------------------- Private ---------------------------------------- */

    private static final double DEFAULT_SPENT = 0;

    private String  m_Name;
    private double m_Spent;
}
