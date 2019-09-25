package junit.test;

import static junit.framework.Assert.assertEquals;

import javax.mail.Message;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import person.daizhongde.migration.spring.service.AccountEmailService;
import person.daizhongde.migration.spring.service.impl.AccountEmailServiceImpl;

public class AccountEmailServiceTest
{
//    private GreenMail greenMail;

    @Before
    public void startMailServer()
        throws Exception
    {
//        greenMail = new GreenMail( ServerSetup.POP3 );
//        greenMail.setUser( "447022608@qq.com", "jjk55555" );
//        greenMail.start();
    }

    @Test
    public void testSendMail()
        throws Exception
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "applicationContext.xml" );
        AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean( "accountEmailService" );

        String subject = "Test Subject";
        String htmlText = "<h3>Test</h3>";
//        accountEmailService.sendMail( "test2@juvenxu.com", subject, htmlText );
//    	String subject = j_username+"'s nt account";
//        String htmlText = "account:<br>username:"+j_username+"<br>password:"+j_password;
        accountEmailService.sendMail("413881461@qq.com", subject,  htmlText );
	
		
//        greenMail.waitForIncomingEmail( 2000, 1 );// 86548780
//
//        Message[] msgs = greenMail.getReceivedMessages();
//        assertEquals( 1, msgs.length );
//        assertEquals( subject, msgs[0].getSubject() );
//        assertEquals( htmlText, GreenMailUtil.getBody( msgs[0] ).trim() );
    }

//    @After
//    public void stopMailServer()
//        throws Exception
//    {
//        greenMail.stop();
//    }
}
