package person.daizhongde.migration.spring.service;

import java.util.List;

import person.daizhongde.migration.hibernate.pojo.MigConfigConnection;

/**
 * 数据字典服务类.
 * Created by y144747 on 14-10-9.
 */
public interface MigConfigConnectionService {

    /**
     * 保存字典信息
     *
     * @param dictionary 需要保存的实体类信息.
     */
    void save(MigConfigConnection dictionary);

    /**
     * 更新字典信息.
     *
     * @param dictionary 需要更新的字典实体对象.
     */
    void update(MigConfigConnection dictionary);

    /**
     * 查询字典实体信息.
     *
     * @param dictionary 查询条件.
     * @return 查询结果实体信息.
     */
    MigConfigConnection query(MigConfigConnection dictionary);

    /**
     * 查询字典列表.
     *
     * @param dictionary 查询条件.
     * @return 返回符合条件的字典列表.
     */
    List<MigConfigConnection> queryList(MigConfigConnection dictionary);

    /**
     * 检索所有的字典信息.
     * @return
     */
    List<MigConfigConnection> queryAllList();

}
