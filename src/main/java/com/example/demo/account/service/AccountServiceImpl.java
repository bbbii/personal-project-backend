package com.example.demo.account.service;

import com.example.demo.account.controller.form.AccountLoginRequestForm;
import com.example.demo.account.controller.form.AccountLoginResponseForm;
import com.example.demo.account.entity.MemberAccount;
import com.example.demo.account.repository.AccountRepository;
import com.example.demo.account.service.request.AccountRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    final private AccountRepository accountRepository;

    @Override
    public Boolean checkEmailDuplication(String email) {
        Optional<MemberAccount> maybeAccount = accountRepository.findByEmail(email);

        if (maybeAccount.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean signUp(AccountRegisterRequest request) {
        accountRepository.save(request.toAccount());

        return true;
    }

    @Override
    public AccountLoginResponseForm login(AccountLoginRequestForm requestForm){
        final Optional<MemberAccount> memberAccount =
                accountRepository.findByEmail(requestForm.getEmail());
        if (memberAccount.isEmpty()) {
            log.info("로그인 실패!");
            return new AccountLoginResponseForm(null);
        }
        MemberAccount account = memberAccount.get();
        if (account.getPassword().equals(requestForm.getPassword())) {
            log.info("로그인 성공!");
            return new AccountLoginResponseForm(UUID.randomUUID());
        }
        return new AccountLoginResponseForm(null);
    }
}