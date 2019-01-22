package person.daizhongde.migration.spring.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.TPubDictionaryDAO;
import person.daizhongde.migration.hibernate.pojo.TPubDictionary;
import person.daizhongde.migration.spring.service.TPubDictionaryService;

import java.util.List;

/**
 * 字典查询服务类.
 * Created by y144747 on 14-10-9.
 */
public class TPubDictionaryServiceImpl implements TPubDictionaryService {


	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private TPubDictionaryDAO dataDAO;
	
    public void save(TPubDictionary dictionary) {

        log.debug("进入字典信息保存方法");
        String code = dictionary.getCode();
        String value = dictionary.getValue();
        String group = dictionary.getType();
        if (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(group)) {
            try {
            	dataDAO.save( dictionary );
            } catch (Exception ae) {
            	log.error("保存字典信息出错.");
                throw new BusinessException("保存字典信息出错。");
            }
        } else {
            log.error("字典信息 code=" + code + ",value=" + value + ",group=" + group);
            throw new BusinessException("需要保存的信息不能为空");
        }
        log.debug("成功保存字典信息.");

    }

    public void update(TPubDictionary dictionary) {
        long id = dictionary.getId();
        if (id !=  0 ) {
//        	dataDAO.updateByPrimaryKeySelective(dictionary);
        } else {
            log.error("系统无法检索到更新对象.");
            throw new BusinessException("系统无法检索到更新对象");
        }

    }

    public TPubDictionary query(TPubDictionary dictionary) {
        return null;
    }

    public List<TPubDictionary> queryList(TPubDictionary dictionary) {

        return dataDAO.queryListByInfo(dictionary);
    }

    public List<TPubDictionary> queryAllList() {

        return null;
    }

	public void setDataDAO(TPubDictionaryDAO dataDAO) {
		this.dataDAO = dataDAO;
	}
    
}
