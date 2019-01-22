package junit.test;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import person.daizhongde.virtue.configutils.ConfigDocument_SQL;
import person.daizhongde.virtue.util.test.Printer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import person.daizhongde.authority.hibernate.pojo.TAuthorityModule_YUI2_Menu;
import person.daizhongde.migration.hibernate.pojo.MigJobPara;
import person.daizhongde.migration.util.JobSemaphore;
import person.daizhongde.migration.util.ReflectUtils;
import person.daizhongde.migration.util.SeqSemaphore;
import person.daizhongde.migration.util.ThreadResource;

public class Test {

	public static void main(String args[]){
		String hostConn = "@{H$IP}";
		hostConn = hostConn.replace("@{H$IP}", "cier");
		System.out.println("hostConn:"+hostConn);
	}

}