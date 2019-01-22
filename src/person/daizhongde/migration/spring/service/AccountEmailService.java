package person.daizhongde.migration.spring.service;

import person.daizhongde.migration.exception.AccountEmailException;

public interface AccountEmailService
{
    void sendMail( String to, String subject, String htmlText )
        throws AccountEmailException;
}
