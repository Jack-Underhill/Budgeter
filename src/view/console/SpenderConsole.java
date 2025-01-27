package src.view.console;

public class SpenderConsole 
{
    public void header(int balance)
    {
        System.out.println();
        System.out.println(DASH);
        System.out.println("Balance is $" + balance);
        System.out.println(DASH);
    }

    public void statementStart(String name)
    {
        System.out.println();
        System.out.println();
        System.out.println("Statement of " + name + ":");
        System.out.println(DASH);
    }

    public void statementEnd(double money)
    {
        System.out.println(DASH);
        
        String prompt = "Total spent: ";
        moneyValue(prompt, money);
    }

    public void moneyValue(String prompt, double money)
    {
        String str = alignValues(prompt, money);
        System.out.println(str);
    }

    /* ---------------------------------------- Private ---------------------------------------- */
    
    private final String DASH = "---------------------------------";

    private String alignValues(String prompt, double money)
    {
        for(int i = prompt.length(); i < 25; i++)
        {
            prompt += " ";
        }

        return prompt + doubleToMoney(money);
    }

    private String doubleToMoney(double val)
    {
        return String.format("$%.2f", val);
    }
}
