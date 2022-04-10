package com.Vassah.MyBank.Models.TransactionModels;

import java.util.ArrayList;
import com.Vassah.MyBank.Models.AccountModels.Account;

public class TransactionRepoManager {

    private ITransactionRepository repository;

    public TransactionRepoManager(ITransactionRepository repo)
    {
        repository = repo;
    }

    public boolean Add(Transaction tr)
    {
        repository.Add(tr);
        return true;
    }

    public ArrayList<Transaction> FindByAccount(long accountNumber)
    {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        for (Transaction tr : repository.Transactions) {
            if (tr.SenderAccountNumber == accountNumber || tr.RecieverAccountNumber == accountNumber)
                result.add(tr);
        }
        return result;
    }

    public ArrayList<Transaction> FindByAccount(Account account)
    {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        for (Transaction tr : repository.Transactions) {
            if (tr.SenderAccountNumber == account.Number || tr.RecieverAccountNumber == account.Number)
                result.add(tr);
        }
        return result;
    }

    public Transaction FindById(long id)
    {
        return repository.FindById(id);
    }
}
