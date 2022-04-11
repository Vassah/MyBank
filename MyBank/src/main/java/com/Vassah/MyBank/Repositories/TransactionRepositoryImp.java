package com.Vassah.MyBank.Repositories;

import java.util.ArrayList;

public class TransactionRepositoryImp implements TransactionRepository {

    public ArrayList<Transaction> Transactions;

    public TransactionRepositoryImp(){
        Transactions = new ArrayList<Transaction>();
    }

    public boolean Add(Transaction tr)
    {
        Transactions.add(tr);
        return true;

    }

    public Transaction FindById(long id)
    {
        for (Transaction tr : Transactions)
        {
            if (tr.Id == id) return tr;
        }
        return new Transaction();
    }

    
}
