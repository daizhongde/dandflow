--数据字典
select t.*  from T_pub_DICTIONARY t  where parentid=11
--
select t.* from T_PMS_DICTIONARY t where id between 11000 and 11999
--update T_PMS_DICTIONARY set parentid=11 where id between 11000 and 12000

select * from T_PMS_DICTIONARY where value like '%执行状态%'
--任务类型,'F'：非叶子节点,‘S’：叶子节点
--任务状态,'0':未执行,'1':正在执行,'2':执行完成,'3':暂停执行,'4':跳过执行,'-1':执行出错
--LOCK_STATUS '0':未锁定,'1'锁定

