package TP_PROJECT.Models.TransactionModels;
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
        return String.Format("Номер счёта отправителя: %d \n Номер счёта получателя: $d \n Время отправки: %t", SenderAccount.Number, RecieverAccount.Number, time);
    }
}
public enum SendRecieveStatus
{
    Send,
    Recieve
}