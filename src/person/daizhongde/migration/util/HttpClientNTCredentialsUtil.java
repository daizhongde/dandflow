package person.daizhongde.migration.util;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import person.daizhongde.virtue.util.test.Printer;

/**
 * @author youthflies
 * yeetrack.com
 */
public class HttpClientNTCredentialsUtil
{
//	private String username = "daizd";
//	private String password = "jjk55555";
//	private String url = "home1.asiainfo.com";
//	private String domain = "ai";
	
	private int statusCode;
	private String msg;
	
    public boolean check(String domain, String username, String password)
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        List<String> authpref = new ArrayList<String>();
        authpref.add(AuthPolicy.NTLM);
        httpclient.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF,    authpref);
        //参数分别为用户名、密码、服务器url、工作域名称
//        NTCredentials creds = new NTCredentials("daizd", "jjk55555","home1.asiainfo.com","ai");
        NTCredentials creds = new NTCredentials( username, password, "home1.asiainfo.com", domain);
        httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);

        //设置要连接的目标名称、端口
//        HttpHost target = new HttpHost("home1.asiainfo.com", 80, "http");

        // Make sure the same context is used to execute logically related requests
//        HttpContext localContext = new BasicHttpContext();

        // Execute a cheap method first. This will trigger NTLM authentication
        HttpGet httpget = new HttpGet("http://home1.asiainfo.com/cas/CASAuthorization.aspx");
//        HttpGet httpget = new HttpGet("https://sso.asiainfo.com/login");
//        HttpGet httpget = new HttpGet("https://oa.asiainfo.com");//Error
        
        
        //下面是为请求加上一些header信息，来伪装浏览器
        httpget.addHeader("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.8,en-US;q=0.5,en;q=0.3");
        httpget.addHeader("Accept","image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, */*");
        httpget.addHeader("DNT","1");
        httpget.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.2; WOW64; Trident/6.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729)");
        httpget.addHeader("Accept-Encoding","gzip, deflate");

        statusCode = 0;
        try
        {

            HttpResponse response = httpclient.execute(httpget);
            System.out.println( response.getStatusLine().getStatusCode() );
            statusCode = response.getStatusLine().getStatusCode();

        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            msg = e.getLocalizedMessage();
        } catch (java.net.ConnectException e)
        {
        	msg = e.getLocalizedMessage();
            e.printStackTrace();
        } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = e.getLocalizedMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = e.getLocalizedMessage();
		}
        return statusCode==200?true:false;
        
    }   
	
    public String getJSESSIONID(String domain, String username, String password)
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        List<String> authpref = new ArrayList<String>();
        authpref.add(AuthPolicy.NTLM);
        httpclient.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF,    authpref);
        //参数分别为用户名、密码、服务器url、工作域名称
//        NTCredentials creds = new NTCredentials("daizd", "jjk55555","home1.asiainfo.com","ai");
        NTCredentials creds = new NTCredentials( username, password, "home1.asiainfo.com", domain);
        httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);

        //设置要连接的目标名称、端口
//        HttpHost target = new HttpHost("home1.asiainfo.com", 80, "http");

        // Make sure the same context is used to execute logically related requests
//        HttpContext localContext = new BasicHttpContext();

        // Execute a cheap method first. This will trigger NTLM authentication
        HttpGet httpget = new HttpGet("http://home1.asiainfo.com/cas/CASAuthorization.aspx");
//        HttpGet httpget = new HttpGet("https://sso.asiainfo.com/login");
//        HttpGet httpget = new HttpGet("https://oa.asiainfo.com");//Error
        
        
        //下面是为请求加上一些header信息，来伪装浏览器
        httpget.addHeader("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.8,en-US;q=0.5,en;q=0.3");
        httpget.addHeader("Accept","image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, */*");
        httpget.addHeader("DNT","1");
        httpget.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.2; WOW64; Trident/6.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729)");
        httpget.addHeader("Accept-Encoding","gzip, deflate");

//		httpget.addHeader("请求", "POST /user/employeeSeachList HTTP/1.1");
//		httpget.addHeader("x-requested-with", "XMLHttpRequest");
//		httpget.addHeader("Accept-Language", "zh-cn");
//		httpget.addHeader("Referer", "https://oa.asiainfo.com/user/employeeSeach");
//		httpget.addHeader("Accept", "text/plain, */*; q=0.01");
//		httpget.addHeader("Content-Type", "application/x-www-form-urlencoded");
//		httpget.addHeader("Accept-Encoding", "gzip, deflate");
//		httpget.addHeader("User-Agent",
//						"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; InfoPath.3; GWX:RESERVED)");
//		httpget.addHeader("Host", "oa.asiainfo.com");
//		httpget.addHeader("Connection", "Keep-Alive");
//		httpget.addHeader("Cache-Control", "no-cache");
        
        String JSESSIONID = "";
        try
        {

            HttpResponse response = httpclient.execute(httpget);
//            System.out.println( response.getStatusLine().getStatusCode() );
//            statusCode = response.getStatusLine().getStatusCode();
//          org.apache.http.Header[] heads= response.getAllHeaders();
//          Printer.printJSON(heads);
            JSESSIONID = response.getLastHeader("Set-Cookie").getElements()[0].getValue();
//            System.out.println("JSESSIONID:"+JSESSIONID);
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return JSESSIONID;
        
    }   

    public int getStatusCode() {
		return statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public static void main(String[] args)
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        List<String> authpref = new ArrayList<String>();
        authpref.add(AuthPolicy.NTLM);
        httpclient.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF,    authpref);
        //参数分别为用户名、密码、服务器url、工作域名称
        NTCredentials creds = new NTCredentials("daizd", "jjk55555","home1.asiainfo.com","ai");
        httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);

        //设置要连接的目标名称、端口
//        HttpHost target = new HttpHost("home1.asiainfo.com", 80, "http");

        // Make sure the same context is used to execute logically related requests
//        HttpContext localContext = new BasicHttpContext();

        // Execute a cheap method first. This will trigger NTLM authentication
        HttpGet httpget = new HttpGet("http://home1.asiainfo.com/cas/CASAuthorization.aspx");
//        HttpGet httpget = new HttpGet("https://sso.asiainfo.com/login");
//        HttpGet httpget = new HttpGet("https://oa.asiainfo.com");//Error
        
        
        //下面是为请求加上一些header信息，来伪装浏览器
        httpget.addHeader("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.8,en-US;q=0.5,en;q=0.3");
        httpget.addHeader("Accept","image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, */*");
        httpget.addHeader("DNT","1");
        httpget.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.2; WOW64; Trident/6.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729)");
        httpget.addHeader("Accept-Encoding","gzip, deflate");

        try
        {

            HttpResponse response = httpclient.execute(httpget);
//            HttpEntity entity = response.getEntity();
//            
//            System.out.println(EntityUtils.toString(entity));
//            System.out.println(entity.getContentType());
            System.out.println( response.getStatusLine().getStatusCode() );
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   
}  