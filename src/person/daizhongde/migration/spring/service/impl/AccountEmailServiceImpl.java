package person.daizhongde.migration.spring.service.impl;

import java.io.UnsupportedEncodingException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.FilenameUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import person.daizhongde.migration.exception.AccountEmailException;
import person.daizhongde.migration.spring.service.AccountEmailService;

public class AccountEmailServiceImpl
    implements AccountEmailService
{
    private JavaMailSender javaMailSender;

    private String systemEmail;

    public void sendMail( String to, String subject, String htmlText )
            throws AccountEmailException
        {
            try
            {
                MimeMessage msg = javaMailSender.createMimeMessage();
                /*  添加邮件正文（无附件）  */
//        		msg.setContent(htmlText, "text/html;charset=UTF-8");
        		
                MimeMessageHelper msgHelper = new MimeMessageHelper( msg, true, "UTF-8");

                msgHelper.setFrom( systemEmail );
                msgHelper.setTo( to );//qq邮箱
//                msgHelper.setTo( "p_daizhongde@hngytobacco.com" );
                msgHelper.setSubject( subject );

                /*  添加邮件正文（无附件）  */
                msgHelper.setText( htmlText, true );
                
                javaMailSender.send( msg );
            } catch ( MessagingException e )
            {
                throw new AccountEmailException( "Faild to send mail.MessagingException：", e );
            }
        }
    /**
     * 附件名与本地文件名一致
     * @param to
     * @param subject
     * @param htmlText
     * @param attachPathArr
     * @throws AccountEmailException
     */
    public void sendMail( String to, String subject, String htmlText, String attachPath )
            throws AccountEmailException
    {
    	
    	String[] attachPathArr = new String[1];
    	String[] attachNameArr = new String[1];
    	attachPathArr[0] = attachPath;
        attachNameArr[0] = FilenameUtils.getName(attachPath);
    	
    	this.sendMail(to, subject, htmlText, attachPathArr, attachNameArr);
    }
    /**
     * 附件名与本地文件名致
     * @param to
     * @param subject
     * @param htmlText
     * @param attachPathArr
     * @throws AccountEmailException
     */
    public void sendMail( String to, String subject, String htmlText, String[] attachPathArr )
            throws AccountEmailException
    {
    	
    	String[] attachNameArr = new String[attachPathArr.length];
        for (int i=0; i< attachPathArr.length; i++) {
        	attachNameArr[i] = FilenameUtils.getName(attachPathArr[i]);
        }
    	
    	this.sendMail(to, subject, htmlText, attachPathArr, attachNameArr);
    }
    /**
     * 附件名与本地文件名不一致，此方法只适用于一个附件的情况
     * @param to
     * @param subject
     * @param htmlText
     * @param attachPathArr
     * @param attachName
     * @throws AccountEmailException
     */
    public void sendMail( String to, String subject, String htmlText, String attachPath, String attachName)
            throws AccountEmailException
    {
    	String[] attachPathArr = new String[1];
    	String[] attachNameArr = new String[1];
    	attachPathArr[0] = attachPath;
    	attachNameArr[0] = attachName;
    	
    	this.sendMail(to, subject, htmlText, attachPathArr, attachNameArr);
    }
    
    /**
     * 适用于附件名与本地文件名不一致的情况
     */
    public void sendMail( String to, String subject, String htmlText, String[] attachPathArr, String[] attachNameArr )
        throws AccountEmailException
    {
        try
        {
            MimeMessage msg = javaMailSender.createMimeMessage();
            /*  添加邮件正文（无附件）  */
//    		msg.setContent(htmlText, "text/html;charset=UTF-8");
    		
            MimeMessageHelper msgHelper = new MimeMessageHelper( msg, true, "UTF-8");

            msgHelper.setFrom( systemEmail );
            msgHelper.setTo( to );//qq邮箱
//            msgHelper.setTo( "p_daizhongde@hngytobacco.com" );
            msgHelper.setSubject( subject );

            /*  添加邮件正文（无附件）  */
//            msgHelper.setText( htmlText, true );
            
            /*  添加邮件正文（带附件） */
            MimeMultipart multipart = new MimeMultipart();
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent( htmlText, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            
            // 添加附件
//            String[] paths = { "D:/test2.doc" };
            int i =0;
            for (String filePath : attachPathArr) {
                MimeBodyPart part = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(filePath);
//                String filename = fds.getName();
                part.setFileName(MimeUtility.encodeWord(attachNameArr[i]));// MimeUtility.encodeWord文件名解决中文乱码
                part.setDataHandler(new DataHandler(fds));
                multipart.addBodyPart(part);
                i++;
            }
            msg.setContent(multipart);
            

            javaMailSender.send( msg );
        } catch ( MessagingException e )
        {
            throw new AccountEmailException( "Faild to send mail.MessagingException：", e );
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new AccountEmailException( "Faild to send mail.UnsupportedEncodingException：", e );
		}
    }

    public JavaMailSender getJavaMailSender()
    {
        return javaMailSender;
    }

    public void setJavaMailSender( JavaMailSender javaMailSender )
    {
        this.javaMailSender = javaMailSender;
    }

    public String getSystemEmail()
    {
        return systemEmail;
    }

    public void setSystemEmail( String systemEmail )
    {
        this.systemEmail = systemEmail;
    }
	public static AccountEmailService getFromApplicationContext(
			ApplicationContext ctx) {
		return (AccountEmailService) ctx.getBean("accountEmailService");
	}
}
