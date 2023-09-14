package com.simpleply.banking.application.port.out;

import com.simpleply.banking.adapter.out.external.bank.BankAccount;
import com.simpleply.banking.adapter.out.external.bank.GetBankAccountRequest;

public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
