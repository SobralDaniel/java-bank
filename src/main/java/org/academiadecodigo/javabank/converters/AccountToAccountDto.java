package org.academiadecodigo.javabank.converters;

import org.academiadecodigo.javabank.command.AccountDto;
import org.academiadecodigo.javabank.model.account.Account;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountToAccountDto implements Converter<Account, AccountDto> {

    @Override
    public AccountDto convert(Account account) {

        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }
}
