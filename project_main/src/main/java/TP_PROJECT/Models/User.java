package TP_PROJECT.Models;

public class User {
    public long id;
    public Transaction[] Transactions;
    public Account[] Accounts;
    public String FirstName;
    public String LastName;
    public String MiddleName;
    
    public User(){

    }

    public String ShortName() { return LastName + String.ValueOf(FirstName[0]) + ".";}
    public String FullName() { return FirstName + MiddleName + LastName; }

}
