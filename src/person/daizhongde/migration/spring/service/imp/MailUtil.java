package person.daizhongde.migration.spring.service.imp;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import person.daizhongde.migration.exception.AccountEmailException;
import person.daizhongde.virtue.util.character.CharacterConvert;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


//@Component
public class MailUtil {
    private String HOST = "smtp.exmail.qq.com";
    private Integer PORT = 465;
    private String USERNAME;// = "daizhongde@copote.com";
    private String PASSWORD;// = "DZd123456";
    private String emailForm;// = "daizhongde@copote.com";
    private String timeout = "12000";//default 25000
    private String personal;// = "戴忠德";
    private JavaMailSenderImpl mailSender;

    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    public JavaMailSenderImpl createMailSender(String USERNAME, String PASSWORD, String personal) {
        this.USERNAME=USERNAME;
        this.emailForm=USERNAME;
        this.PASSWORD=PASSWORD;
        this.personal=personal;
        
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("UTF-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", timeout);
        p.setProperty("mail.smtp.auth", "false");// false 公司邮箱这里都可以，如果是qq邮箱还只能设置false
        p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sender.setJavaMailProperties(p);
        mailSender = sender;
        return sender;
    }
    /**
     * 发送邮件
     *
     * @param to      接受人
     * @param subject 主题
     * @param html    发送内容
     * @throws MessagingException           异常
     * @throws UnsupportedEncodingException 异常
     */
    public void sendMail(String to, String subject, String html) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(emailForm, personal);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
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
            MimeMessage msg = mailSender.createMimeMessage();
            /*  添加邮件正文（无附件方式一） */
//    		msg.setContent(htmlText, "text/html;charset=UTF-8");
            /*  添加邮件正文（无附件方式二）  */
//          msgHelper.setText( htmlText, true );
            
            MimeMessageHelper msgHelper = new MimeMessageHelper( msg, true, "UTF-8");
            
            msgHelper.setFrom( emailForm, personal );
            msgHelper.setTo( to );//邮箱 eg:  p_daizhongde@hngytobacco.com
            msgHelper.setSubject( subject );

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
                
                /* MimeUtility.encodeText文件名解决中文乱码  */
                String fname1 = MimeUtility.encodeText(attachNameArr[i],"UTF-8",null);
//                String fname2 = MimeUtility.encodeWord(attachNameArr[i],"UTF-8",null);
                
                part.setFileName( fname1 );// MimeUtility.encodeWord文件名解决中文乱码
                part.setDataHandler(new DataHandler(fds));
                multipart.addBodyPart(part);
                i++;
            }
            msg.setContent(multipart);
            

            mailSender.send( msg );
        } catch ( MessagingException e )
        {
            throw new AccountEmailException( "Faild to send mail.MessagingException：", e );
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new AccountEmailException( "Faild to send mail.UnsupportedEncodingException：", e );
		}
    }

    /**
     * 附件名与本地文件名不一致，此方法只适用于一个附件的情况，入参有文件流
     * <p>
     *   适用于要求以不生成临时文件的方式发送附件的情况<br>
     * 特别注意：<br>
     *    <b>以流的形式发不需要 MimeUtility.encodeWord  文件名中文无乱码</>
     * @param to
     * @param subject
     * @param htmlText
     * @param attachPathArr
     * @param attachName
     * @throws AccountEmailException
     */
    public void sendMail( String to, String subject, String htmlText, 
    		ByteArrayInputStream attachInputStream, 
    		String mimeType,
    		String attachName)
         throws AccountEmailException
    {
        try
        {
            MimeMessage msg = mailSender.createMimeMessage();
            /*  添加邮件正文（无附件方式一） */
//    		msg.setContent(htmlText, "text/html;charset=UTF-8");
            /*  添加邮件正文（无附件方式二）  */
//          msgHelper.setText( htmlText, true );
            
            MimeMessageHelper msgHelper = new MimeMessageHelper( msg, true, "UTF-8");
            
            msgHelper.setFrom( emailForm, personal );
            msgHelper.setTo( to );//邮箱 eg:  p_daizhongde@hngytobacco.com
            msgHelper.setSubject( subject );

            /*  添加邮件正文（带附件） */
            MimeMultipart mime =(MimeMultipart) createContent(
					htmlText,attachInputStream, 
					mimeType, 
					attachName );//参数为正文内容和附件流
//			MimeBodyPart stream = new MimeBodyPart();
			
//			MimeBodyPart part=(MimeBodyPart) createAttachment("D:/test/1.jpg");//可增加多个附件
//			MimeMultipart mime=new MimeMultipart("mixed");
//			mime.addBodyPart(contentPart);
//			mime.addBodyPart(part);//可增加多个附件
			msg.setContent(mime);
			
            mailSender.send( msg );
        } 
        catch ( MessagingException e )
        {
            throw new AccountEmailException( "Faild to send mail.MessagingException：", e );
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new AccountEmailException( "Faild to send mail.UnsupportedEncodingException：", e );
		}
    }
//    private Part createContent1(String content,ByteArrayInputStream inputstream, 
//    		String mimeType,
//    		String attachName){
//		MimeBodyPart contentPart=null;
//		try {
//			contentPart=new MimeBodyPart();
//			MimeMultipart contentMultipart=new MimeMultipart("related");
//			MimeBodyPart htmlPart=new MimeBodyPart();
//			htmlPart.setContent(content, "text/html;charset=UTF-8");
////			htmlPart.setContent(content, "text/html;charset=gbk");
//			contentMultipart.addBodyPart(htmlPart);
//			//附件部分
//			MimeBodyPart excelBodyPart=new MimeBodyPart();
//			DataSource dataSource=new ByteArrayDataSource(inputstream, mimeType );
//            DataHandler dataHandler=new DataHandler(dataSource);
//            excelBodyPart.setDataHandler(dataHandler);
//            excelBodyPart.setFileName(MimeUtility.encodeText( attachName ));
////			excelBodyPart.setDataHandler(new DataHandler(fileDs));
////			excelBodyPart.setFileName(fileDs.getName());
////			excelBodyPart.setContentID("excel");
//			contentMultipart.addBodyPart(excelBodyPart);
//			contentPart.setContent(contentMultipart);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return contentPart;
//	}
    /**
     * 组装MimeMultipart
     * <p>
     * 特别注意：
     *    以流的形式发不需要 MimeUtility.encodeWord  文件名中文无乱码
     * @param content
     * @param inputstream
     * @param mimeType
     * @param attachName
     * @return
     */
    private MimeMultipart createContent(String content,
    		ByteArrayInputStream inputstream, 
    		String mimeType,
    		String attachName){
    	MimeMultipart multipart=new MimeMultipart("mixed");
//    	MimeMultipart multipart=new MimeMultipart("related");
//    	MimeMultipart multipart=new MimeMultipart("alternative ");
		try {
            
            /*  添加邮件正文（带附件） */
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent( content, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            
            // 添加附件 
            MimeBodyPart part = new MimeBodyPart();
			DataSource dataSource=new ByteArrayDataSource(inputstream, mimeType );
			
            DataHandler dataHandler=new DataHandler(dataSource);

//			CharacterConvert.testCharSet(attachName);
//            String fname1 = MimeUtility.encodeText(attachName,"UTF-8",null);
//            String fname2 = MimeUtility.encodeWord(attachName,"UTF-8",null);

//			CharacterConvert.testCharSet(fname1);
			
			
            //以流的形式发不需要 MimeUtility.encodeWord  文件名中文无乱码
            part.setFileName(attachName);
            part.setDataHandler(dataHandler);
        
            multipart.addBodyPart(part); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return multipart;
	}

//    /**
//     * 发送带附件的邮件
//     *
//     * @param to      接受人
//     * @param subject 主题
//     * @param html    发送内容
//     * @param filePath  附件路径
//     * @throws MessagingException           异常
//     * @throws UnsupportedEncodingException 异常
//     */
//    public void sendAttachmentMail(String to, String subject, String html, String filePath) 
//    		throws MessagingException, UnsupportedEncodingException {
//        String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
//    	this.sendAttachmentMail(to, subject, html, filePath, fileName);;
//    }

//    /**
//     * 发送带附件的邮件
//     *
//     * @param to      接受人
//     * @param subject 主题
//     * @param html    发送内容
//     * @param filePath  附件路径
//     * @throws MessagingException           异常
//     * @throws UnsupportedEncodingException 异常
//     */
//    public void sendAttachmentMail(String to, String subject, String html,
//    		String filePath, String fName) 
//    				throws MessagingException, UnsupportedEncodingException {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        // 设置utf-8或GBK编码，否则邮件会有乱码
//        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//        messageHelper.setFrom(emailForm, personal);
//        messageHelper.setTo(to);
//        messageHelper.setSubject(subject);
//        messageHelper.setText(html, true);
//        FileSystemResource file=new FileSystemResource(new File(filePath));
////        String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
//        messageHelper.addAttachment(fName,file);
//        mailSender.send(mimeMessage);
//    }
}

