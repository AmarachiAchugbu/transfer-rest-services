package com.example.restservices.controller;

import com.example.restservices.exception.TransactionException;
import com.example.restservices.models.Balances;
import com.example.restservices.models.Transactions;
import com.example.restservices.models.Transfers;
import com.example.restservices.repository.BalanceRepository;
import com.example.restservices.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransferController {
    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/transfer")
    @Transactional(rollbackFor = { SQLException.class })
    public void transfer(@RequestBody Transfers transfer) {
        List<Transactions> transactions = transactionRepository.findAll();
        if (transactions.size() > 0) {
            Pageable pageable = PageRequest.of(1, 1);
            List<Transactions> lastTransactionList = transactionRepository.getLastTransaction(pageable);
            Transactions lastTransaction = lastTransactionList.get(0);
            Date lastDate = lastTransaction.getCreatedAt();

            if (((System.currentTimeMillis() - lastDate.getTime()) < 60 * 1000) && (transfer.getFrom() == lastTransaction.getAccountNumber()) && (transfer.getAmount() == lastTransaction.getAmount())) {
                throw new TransactionException("A duplicate transaction is detected.");
            }
        }

        Balances balanceFrom = balanceRepository.getOne(transfer.getFrom());
        Balances balanceTo = balanceRepository.getOne(transfer.getTo());

        if (balanceFrom.getBalance() - transfer.getAmount() < 0) {
            throw new TransactionException("Insufficient Balance");
        } else
            balanceFrom.setBalance(balanceFrom.getBalance() - transfer.getAmount());

        balanceTo.setBalance(balanceTo.getBalance() + transfer.getAmount());
        balanceRepository.save(balanceFrom);
        balanceRepository.save(balanceTo);

        Transactions transactionFrom = new Transactions();
        transactionFrom.setAccountNumber(transfer.getFrom());
        transactionFrom.setAmount(transfer.getAmount());
        transactionRepository.save(transactionFrom);

        Transactions transactionTo = new Transactions();
        transactionTo.setAccountNumber(transfer.getTo());
        transactionTo.setAmount(transfer.getAmount());
        transactionRepository.save(transactionTo);
    }
}
