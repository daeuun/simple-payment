package com.simpleply.banking.adapter.out.external.bank;

import com.simplepay.common.ExternalSystemAdapter;
import com.simpleply.banking.adapter.out.persistence.SpringDataRegisteredBankAccountRepository;
import com.simpleply.banking.application.port.out.RequestBankAccountInfoPort;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort {

    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
        // 실제 외부 은행에 http 통신해서 실제 은행 계좌정보를 가져오고
        // 실제 은행 계좌 -> BankAccount
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }
}
