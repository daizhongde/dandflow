--权限管理查询用户模块
select * from t_authority_module a where a.n_mid in 
( 
  select distinct b.n_mid from t_authority_rmrelation b where b.c_rid in 
  (
      select c.c_rid from t_authority_role c where c.c_rname in
      (
             select d.c_rname from t_authority_user d where d.n_uid=?
      )
  )
)
--权限管理查询用户模块
select * from t_authority_module a where a.n_mid in (select distinct b.n_mid from t_authority_rmrelation b where b.c_rid in (select c.c_rid from t_authority_role c where c.c_rname in (select d.c_rname from t_authority_user d where d.n_uid=?)))


/**
GRANT ALL PRIVILEGES ON *.* TO 'migdb'@'%' IDENTIFIED BY 'easeaseas' WITH GRANT OPTION;
grant execute on tool.* to 'migdb'@’%’ IDENTIFIED BY 'easeaseas';
grant execute on tool.findModuleByUserId to migdb@’192.168.0.%’;**/

grant execute on function tool.f_getChildInstList to 'migdb'@'%';
grant execute on function tool.f_getChildModuleList to 'migdb'@'%';
grant execute on function tool.f_getChildUserList to 'migdb'@'%';
grant execute on function tool.f_getInstCodeById to 'migdb'@'%';
grant execute on function tool.f_getInstNameById to 'migdb'@'%';
grant execute on function tool.f_getModuleIdListByUserId to 'migdb'@'%';
grant execute on function tool.f_getPInstIdById to 'migdb'@'%';
grant execute on function tool.f_getPModuleList to 'migdb'@'%';
grant execute on function tool.f_getPUserIdById to 'migdb'@'%';
grant execute on function tool.f_getTheirsPModuleList to 'migdb'@'%';

