package TP_PROJECT.Models;

import TP_PROJECT.Models.TransactionModels.*;
import TP_PROJECT.Models.AccountModels.*;

public class User {
    public long id;
    public Transaction[] Transactions;
    public Account[] Accounts;
    public String FirstName;
    public String LastName;
    public String MiddleName;
    
    public User()
    {

    }

    public String shortName() { return LastName + String.valueOf(FirstName.toCharArray()[0]) + ".";}
    public String fullName() { return FirstName + MiddleName + LastName; }

}
