package com.simpleply.banking.application.service;

import com.simplepay.common.UseCase;
import com.simpleply.banking.adapter.out.external.bank.BankAccount;
import com.simpleply.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.simpleply.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.simpleply.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.simpleply.banking.application.port.in.RegisterBankAccountCommand;
import com.simpleply.banking.application.port.in.RegisterBankAccountUseCase;
import com.simpleply.banking.application.port.out.RegisterBankAccountPort;
import com.simpleply.banking.application.port.out.RequestBankAccountInfoPort;
import com.simpleply.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper mapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

    @Override
    public RegisteredBankAccount registeredBankAccount(RegisterBankAccountCommand command) {
        // 은행 계좌 등록하는 서비스 (비즈니스 로직)
        // command.getMembershipId()

        // 1. 외부 실제 은행에 등록이 가능한 계좌인지 확인한다.
        // 외부 은행에 이 계좌가 정상인지? 확인한다.
        // Biz Logic -> External System
        // Port -> Adapter -> External System
        // Port
        // 실제 외부 은행 계좌 정보를 Get
        BankAccount bankAccountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean accountIsValid = bankAccountInfo.isValid();

        // 2. 등록가능한 계좌라면, 등록한다. 성공하면, 등록에 성공한 등록 정보를 리턴
        // 2-1. 등록가능하지 않은 계좌라면, 에러를 리턴.
        if (accountIsValid) {
            // 등록정보 저장
            RegisteredBankAccountJpaEntity savedAccountInfo = registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId() + ""),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid())
            );
            return mapper.mapToDomainEntity(savedAccountInfo);
        } else {

        }
        return null;
    }
}
