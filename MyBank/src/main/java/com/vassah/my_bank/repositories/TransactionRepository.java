package com.vassah.my_bank.repositories;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vassah.my_bank.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    //@Query("Select tr from TransactionRepository where tr.recieverAccountNumber = accountNumber or tr.senderAccountNumber = accountNumber")
    //Collection<Transaction> ByAccountNumber(long accountNumber);

    //@Query("Select tr from TransactionRepository where tr.proccesTime between begin and end")
    //Collection<Transaction> ByPeriod(OffsetDateTime begin, OffsetDateTime end);
}
