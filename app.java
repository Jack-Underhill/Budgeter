import src.controllers.*;

public class app 
{
    public static void main(String[] args)
    {
        int balance = 9000;
        String statementsFolderPath = "bin/statements/";

        IViewController c = new ConsoleViewController();
        c.init(balance);
        c.run(statementsFolderPath);
    }
}
