/**
 * Project Name:bms-core
 * File Name:ArbServiceImpl.java
 * Package Name:com.snto.bms.service
 * Date:2014-9-25下午3:16:10
 *
*/

package person.daizhongde.migration.spring.service.impl;

import java.text.MessageFormat;
import java.util.concurrent.Semaphore;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import person.daizhongde.migration.hibernate.dao.TPubSeqtableDAO;
import person.daizhongde.migration.hibernate.pojo.TPubSeqtable;
import person.daizhongde.migration.spring.service.PubService;
import person.daizhongde.migration.util.JobSemaphore;
import person.daizhongde.migration.util.ReflectUtils;
import person.daizhongde.migration.util.SeqSemaphore;

/**
 * Date:     2014-12-23 下午3:16:10 <br/>
 * @author   daizd
 * @since    JDK 1.7
 */
public class PubServiceImpl implements PubService{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private TPubSeqtableDAO seqDAO;
	
	public String get14ByteCode(String prefix, String tableName ){
		tableName  = tableName.toLowerCase();
		
		if( StringUtils.isEmpty(prefix) ){
			TPubSeqtable seq = seqDAO.findById( tableName );			
			prefix = seq.getPrefix();
		}
		
//		lock(tableName);
//		SeqSemaphore.mig_com_ins.acquire();
		int val = seqDAO.sqlQuerySequenceNEXTVAL2(tableName, true );
//		SeqSemaphore.mig_com_ins.release();
//		unlock(tableName);
		
		//因为取序列的方法可能更新了日期，所以重新查询,这里查询结果中的日期就是数据库的当前日期
		TPubSeqtable seq2 = seqDAO.findById( tableName );
		//查询数据库当前时间
		String sdate = seq2.getSeqTime().replaceAll("[-]", "");
		//两位字母+六位日期+6位序列,序列每天重置一次
		String ret = prefix + sdate + MessageFormat.format("{0,number,000000}", val);
		
		return ret;
	}
	
	public String get14ByteCode( String tableName ){
		tableName  = tableName.toLowerCase();
		
		TPubSeqtable seq = seqDAO.findById( tableName );			
		String prefix = seq.getPrefix();
		
//		lock(tableName);
//		SeqSemaphore.mig_com_ins.acquire();
		int val = seqDAO.sqlQuerySequenceNEXTVAL2(tableName, true );
//		SeqSemaphore.mig_com_ins.release();
//		unlock(tableName);
		
		//因为取序列的方法可能更新了日期，所以重新查询,这里查询结果中的日期就是数据库的当前日期
		TPubSeqtable seq2 = seqDAO.findById( tableName );
		//查询数据库当前时间
		String sdate = seq2.getSeqTime().replaceAll("[-]", "");
		//两位字母+六位日期+6位序列,序列每天重置一次
		String ret = prefix + sdate + MessageFormat.format("{0,number,000000}", val);
		
		return ret;
	}
	private void unlock(String tableName){
		Semaphore semp = (Semaphore)ReflectUtils.getFieldValue( tableName, new SeqSemaphore() );
		try {
			semp.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void lock(String tableName){
		Semaphore semp = (Semaphore)ReflectUtils.getFieldValue( tableName, new SeqSemaphore() );
		semp.release();
	}
	
	public String get10ByteCode(String prefix, String tableName ){
		tableName  = tableName.toLowerCase();
		if( StringUtils.isEmpty(prefix) ){
			TPubSeqtable seq = seqDAO.findById( tableName );
			prefix = seq.getPrefix();
		}
		
		int val = seqDAO.sqlQuerySequenceNEXTVAL2(tableName, false );

//		log.debug("prefix:"+prefix+", val:"+val);
		return prefix + MessageFormat.format("{0,number,00000000}", val);
	}
	
	public String get10ByteCode(String tableName ){
		tableName  = tableName.toLowerCase();
		TPubSeqtable seq = seqDAO.findById( tableName );
		String prefix = seq.getPrefix();
		
		if(prefix==null || "".equals(prefix)){
			prefix = "FF";
		}
//		lock(tableName);
//		SeqSemaphore.mig_com_ins.acquire();
		int val = seqDAO.sqlQuerySequenceNEXTVAL2(tableName, false );
//		SeqSemaphore.mig_com_ins.release();
//		unlock(tableName);
		
//		log.debug("prefix:"+prefix+", val:"+val);
		return prefix + MessageFormat.format("{0,number,00000000}", val);
	}
	
	public void setSeqDAO(TPubSeqtableDAO seqDAO) {
		this.seqDAO = seqDAO;
	}
	
	public TPubSeqtableDAO getSeqDAO() {
		return seqDAO;
	}

	public static PubService getFromApplicationContext(
			ApplicationContext ctx) {
		return (PubService) ctx.getBean("pubService");
	}
}

