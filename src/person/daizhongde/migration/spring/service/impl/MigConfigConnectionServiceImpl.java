package person.daizhongde.migration.spring.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import person.daizhongde.migration.exception.BusinessException;
import person.daizhongde.migration.hibernate.dao.MigConfigConnectionDAO;
import person.daizhongde.migration.hibernate.pojo.MigConfigConnection;
import person.daizhongde.migration.spring.service.MigConfigConnectionService;

import java.util.List;

/**
 * 字典查询服务类.
 * Created by y144747 on 14-10-9.
 */
public class MigConfigConnectionServiceImpl implements MigConfigConnectionService {


	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MigConfigConnectionDAO dataDAO;
	
    public void save(MigConfigConnection dictionary) {

//        log.debug("进入字典信息保存方法");
//        String code = dictionary.getCode();
//        String value = dictionary.getValue();
//        String group = dictionary.getType();
//        if (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(group)) {
//            try {
//            	dataDAO.save( dictionary );
//            } catch (Exception ae) {
//            	log.error("保存字典信息出错.");
//                throw new BusinessException("保存字典信息出错。");
//            }
//        } else {
//            log.error("字典信息 code=" + code + ",value=" + value + ",group=" + group);
//            throw new BusinessException("需要保存的信息不能为空");
//        }
//        log.debug("成功保存字典信息.");

    }

    @Override
    public void update(MigConfigConnection dictionary) {
        long id = dictionary.getId();
        if (id !=  0 ) {
//        	dataDAO.updateByPrimaryKeySelective(dictionary);
        } else {
            log.error("系统无法检索到更新对象.");
            throw new BusinessException("系统无法检索到更新对象");
        }

    }

    @Override
    public MigConfigConnection query(MigConfigConnection dictionary) {
        return null;
    }


    @Override
    public List<MigConfigConnection> queryList(MigConfigConnection dictionary) {

        return dataDAO.findByExample(dictionary);
    }


    @Override
    public List<MigConfigConnection> queryAllList() {

        return null;
    }

	public void setDataDAO(MigConfigConnectionDAO dataDAO) {
		this.dataDAO = dataDAO;
	}
    
}
