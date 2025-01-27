package src.models;

import java.util.ArrayList;

public class Statement
{
    public Statement(String name) 
    { 
        m_ItemList = new ArrayList<>(); 
        m_TotalSpent = 0;
        m_Name = name;
    }

    public void addItem(String name, double spent)
    {
        int index = getItem(name);

        if(index >= 0)
            m_ItemList.get(index).addSpent(spent);
        else
            m_ItemList.add(new SpendItem(name, spent));

        if(spent > 0)
            m_TotalSpent += spent;
    }
    public void addItem(String name) { addItem(name, -1); }

    public void addRent() { addItem("Rent", RENT); }

    public String getName() { return m_Name; }
    public ArrayList<SpendItem> getSpendingList() { return m_ItemList; }
    public double getTotalSpent() { return m_TotalSpent; }

    /* ---------------------------------------- Private ---------------------------------------- */

    private static final double RENT = 948;

    private ArrayList<SpendItem> m_ItemList;
    private double m_TotalSpent;
    private String m_Name;

    private int getItem(String name)
    {
        int index = 0;

        for(SpendItem item : m_ItemList)
        {
            if(item.getName().equals(name))
                return index;
            else
                index++;
        }

        return -1;
    }
}
