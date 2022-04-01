package com.Vassah.MyBank.Models.TransactionModels;

import java.util.Collection;

public abstract class ITransactionRepository {

    static Collection<Transaction> Transactions;

    boolean Add(Transaction tr)
    {
        return true;
    }

    Transaction FindById(long id)
    {
        return new MyBankOnlyTransaction();
    }

}
