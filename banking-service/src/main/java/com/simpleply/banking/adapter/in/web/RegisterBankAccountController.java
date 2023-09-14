package com.simpleply.banking.adapter.in.web;

import com.simpleply.banking.application.port.in.RegisterBankAccountCommand;
import com.simpleply.banking.application.port.in.RegisterBankAccountUseCase;
import com.simpleply.banking.domain.RegisteredBankAccount;
import com.simplepay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping("/banking/account/register")
    RegisteredBankAccount registeredBankAccount(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .linkedStatusIsValid(true)
                .build();

        RegisteredBankAccount registeredBankAccount = registerBankAccountUseCase.registeredBankAccount(command);
        if (registeredBankAccount == null) {
            // TODO:: Error Handling
            return null;
        }
        return registerBankAccountUseCase.registeredBankAccount(command);
    }
}
