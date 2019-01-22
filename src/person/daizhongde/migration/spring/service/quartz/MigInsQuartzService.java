package person.daizhongde.migration.spring.service.quartz;

import java.util.List;

import org.quartz.SchedulerException;

import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.migration.hibernate.pojo.MigInsQuartz;

import person.daizhongde.virtue.spring.BaseService;

public abstract interface MigInsQuartzService  extends BaseService
{
  public abstract void addMigInsQuartz(MigInsQuartz paramMigInsQuartz) throws SchedulerException;

  public abstract void deleteMigInsQuartz(Integer jobInsId) throws SchedulerException;

  public abstract MigInsQuartz getMigInsQuartzByCastIdAndJobGroup(String castId, String jobGroup);

 /* public abstract List<String> getMinuteList();

  public abstract List<String> getHourList();

  public abstract List<String> getSetHourList();

  public abstract List<Integer> getMonthList();

  public abstract List<Integer> getDayList();*/

  public abstract void deleteMigInsQuartz(String castId, String jobGroup) throws SchedulerException;
  
  public abstract int add( String jdata, TAuthorityUser user,String remoteAddr ) throws SchedulerException;
  public abstract int modify( String jdata, TAuthorityUser user, String remoteAddr ) throws SchedulerException;
  public abstract int delete( String jdata, TAuthorityUser user, String remoteAddr ) throws SchedulerException;
}
