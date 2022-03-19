package TP_PROJECT.Models.TransactionModels;
java.time;

public class MyBankOnlyTransaction extends Transaction{
    User Sender;
    Account SenderAccount;
    User Reciever;
    Account RecieverAccount;

    public MyBankOnlyTransaction()
    {

    }
    
    public String ToString()
    {
        return String.Format("Номер счёта отправителя: %d \n Номер счёта получателя: $d \n ФИО получателя: %s \n Время отправки: %t", SenderAccount.Number, RecieverAccount.Number, Reciever.ShortName(), time);
    }
}