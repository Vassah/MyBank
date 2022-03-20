package TP_PROJECT.Models.TransactionModels;
import TP_PROJECT.Models.*;

public class OtherBankTransaction extends Transaction{
    public User MyBankUser;
    public long SenderAccountNumber;
    public long RecieverAccountNumber;
    public SendRecieveStatus status;

    public OtherBankTransaction()
    {

    }

    public String ToString()
    {
        return String.format("Номер счёта отправителя: %d \n Номер счёта получателя: $d \n Время отправки: %t", SenderAccountNumber, RecieverAccountNumber, time);
    }
}
