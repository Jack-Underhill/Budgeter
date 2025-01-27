package src.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import src.models.*;
import src.view.*;
import src.view.console.SpenderConsole;

public class ConsoleViewController implements IViewController
{
    // Move console view logic into here

    public void init(int balance)
    {
        m_Balance = balance;
        m_StatementList = new ArrayList<>();
        m_Console = new SpenderConsole();
    }

    public void header()
    {
        m_Console.header(m_Balance);
    }

    public void statements(String folderPath)
    {
        FileFilter fileFilter = new FileFilter(folderPath);
        fileFilter.run();

        ArrayList<File> statementFiles = fileFilter.getFiles();
        for(File f : statementFiles)
        {
            loadStatement(folderPath, f.getName());
        }
    }

    public void average()
    {
        if(!m_StatementList.isEmpty())
        {
            Statement avgStatement = new Statement("Average Spendings");

            for(Statement statement : m_StatementList)
            {
                for(SpendItem item : statement.getSpendingList())
                {
                    avgStatement.addItem(item.getName(), item.getSpent());
                }
            }

            viewStatement(avgStatement, m_StatementList.size());
            m_Console.moneyValue("Leftover: ", getNewBalance() / m_StatementList.size());
        }
    }

    /* ---------------------------------------- Private ---------------------------------------- */

    private ArrayList<Statement> m_StatementList;
    private int m_Balance;
    private SpenderConsole m_Console;

    private void loadStatement(String folderPath, String fileName)
    {
        String filePath = folderPath + fileName;
        String line;
        String delimiter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            Statement statement = new Statement(fileName);
            statement.addRent();
            
            while((line = br.readLine()) != null)
            {
                String[] values = line.split(delimiter);
                String category = values[3];

                if(!category.isEmpty() && !category.equals("Category"))
                {
                    Double spent = Double.parseDouble(values[5]) * -1;
                    statement.addItem(category, spent);
                }
            }

            m_StatementList.add(statement);
            viewStatement(statement, 1);
            m_Console.moneyValue("Balance remaining: ", getNewBalance());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void viewStatement(Statement st, int statementCount)
    {
        m_Console.statementStart(st.getName());

        for(SpendItem item : st.getSpendingList())
        {
            m_Console.moneyValue(item.getName(), item.getSpent() / statementCount);
        }

        m_Console.statementEnd(st.getTotalSpent() / statementCount);
    }

    private double getNewBalance()
    {
        double remain = m_Balance;

        for(Statement st : m_StatementList)
        {
            remain -= st.getTotalSpent();
        }

        return remain;
    }
}
