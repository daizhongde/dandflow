package person.daizhongde.migration.spring.service;

import java.util.List;
import java.util.Map;

import person.daizhongde.migration.hibernate.pojo.MigCodedetailDefine;

import person.daizhongde.virtue.spring.BaseService;

/**
 * p1:jobstart update job's prepos为null的processes
 * p2:
 * @author daizd
 *
 */
public interface MigCodedetailDefineService extends BaseService{
  public abstract void newStaticPara(MigCodedetailDefine mpd); 
  public abstract void updateStaticParaValue(Map<String,String> paras); 
  public abstract void delStaticPara(String para); 
  public abstract List<MigCodedetailDefine> findAllStaticPara(); 
  
  /**
   * 查询字典列表.
   *
   * @param dictionary 查询条件.
   * @return 返回符合条件的字典列表.
   */
  List<MigCodedetailDefine> queryList(MigCodedetailDefine dictionary);
}