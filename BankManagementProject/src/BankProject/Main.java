package BankProject;

public class Main {
    public static void main(String[] args) {
        String  databaseName = "bankTesting";
        Utils u = new Utils();
        u.createTables(databaseName);
        try {
            new Login(databaseName);
        }
        catch (Exception e )
        {
            System.out.println("error is "+e);
            System.exit(0);
        }
    }
}
