package src.controllers;

public interface IViewController 
{
    // parent to view options

    public void init(int balance);

    public default void run(String folderPath)
    {
        header();
        statements(folderPath);
        average();
    }

    public void header();

    public void statements(String folderPath);

    public void average();
}
