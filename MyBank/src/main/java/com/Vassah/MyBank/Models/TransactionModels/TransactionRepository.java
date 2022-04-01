package com.Vassah.MyBank.Models.TransactionModels;

import java.util.ArrayList;

public class TransactionRepository extends ITransactionRepository {

    public ArrayList<Transaction> Transactions;

    public TransactionRepository(){
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
            if (tr.id == id) return tr;
        }
        return new MyBankOnlyTransaction();
    }

    
}
