package com.Vassah.MyBank.Repositories;

import java.util.Collection;
import com.Vassah.MyBank.Model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Collection<Transaction> Transactions();

    boolean Add(Transaction tr);

    Transaction FindById(long id);

}
