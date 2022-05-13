package com.Vassah.MyBank.Repositories;

import java.util.Collection;
import java.time.OffsetDateTime;
import com.Vassah.MyBank.Model.Transaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    //@Query("Select tr from TransactionRepository where tr.recieverAccountNumber = accountNumber or tr.senderAccountNumber = accountNumber")
    //Collection<Transaction> ByAccountNumber(long accountNumber);

    //@Query("Select tr from TransactionRepository where tr.proccesTime between begin and end")
    //Collection<Transaction> ByPeriod(OffsetDateTime begin, OffsetDateTime end);
}
