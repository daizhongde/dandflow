package person.daizhongde.migration.spring.service.wsclient;

import org.apache.cxf.phase.AbstractPhaseInterceptor;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.helpers.DOMUtils;

import org.apache.cxf.phase.Phase;

import javax.xml.parsers.*;
import javax.xml.namespace.QName;
import org.w3c.dom.*;

import java.util.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class AddHeaderOutInterceptor
	extends AbstractPhaseInterceptor<SoapMessage>
{
	private String name;
	private String pass;
	public AddHeaderOutInterceptor(String name, String pass)
	{
		//子类总会调用父类的构造器，
		//使用super显式调用父类指定的构造器。
		//指定该拦截器在哪个阶段被激发
		super(Phase.PREPARE_SEND);
		this.name = name;
		this.pass = pass;
	}

	//处理消息
	public void handleMessage(SoapMessage message)
	{
		//获取SOAP消息的全部头(即soap:Header元素全部子元素)
		List<Header> headers = message.getHeaders();
		// 创建Document对象。
		Document doc = DOMUtils.createDocument();
		//创建了两个元素
		Element userId = doc.createElement("userId");
		Element userPass = doc.createElement("userPass");
		userId.setNodeValue(name);
		userPass.setNodeValue(pass);
//		userPass.setTextContent(pass);
		//创建AuthHeader元素
		Element auth = doc.createElement("AuthHeader");
		auth.appendChild(userId);
		auth.appendChild(userPass);
		QName qname = new QName("crazyit");
		SoapHeader authHeader = new SoapHeader(qname , auth);
		//添加了Header
		headers.add(authHeader);
		/**
			也就是向<soap:Header.../>元素中添加了如下元素:
			<AuthHeader>
				<userId>crazyit</userId>
				<userPass>crazyit.org</userPass>
			</AuthHeader>
		*/
	}
}
