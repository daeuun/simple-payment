package com.simpleply.banking.application.port.in;

import com.simpleply.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {

    RegisteredBankAccount registeredBankAccount(RegisterBankAccountCommand command);
}
