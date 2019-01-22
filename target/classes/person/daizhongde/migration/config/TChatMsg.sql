/** a variable, like javascript

--@JavaScript var TChatMsg = {};
--@JavaScript TChatMsg.Query = {};
--@JavaScript TChatMsg.Add = {};
--@JavaScript TChatMsg.Update = {};
--@JavaScript TChatMsg.Read = {};
--@JavaScript TChatMsg.Del = {};

--@JavaScript TChatMsg.Combobox = {};
--@JavaScript TChatMsg.Nest = {};
--@JavaScript TChatMsg.Export = {};

ten TChatMsg's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	TChatMsg.Query.query.SQL        TChatMsg.Query.query.HQL        TChatMsg.Query.query.JPQL    
	TChatMsg.Read.read.SQL          TChatMsg.Read.read.HQL          TChatMsg.Read.read.JPQL      
	TChatMsg.Combobox.combobox.SQL  TChatMsg.Combobox.combobox.HQL  TChatMsg.Combobox.combobox.JPQL
	TChatMsg.Nest.nest.SQL          TChatMsg.Nest.nest.HQL          TChatMsg.Nest.nest.JPQL      
	TChatMsg.Export.export.SQL      TChatMsg.Export.export.HQL      TChatMsg.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var TChatMsg = {};
--@JavaScript TChatMsg.Query = {};
--@JavaScript TChatMsg.Add = {};
--@JavaScript TChatMsg.Update = {};
--@JavaScript TChatMsg.Read = {};
--@JavaScript TChatMsg.Del = {};

--@JavaScript TChatMsg.Combobox = {};
--@JavaScript TChatMsg.Nest = {};
--@JavaScript TChatMsg.Export = {};

--@JavaScript TChatMsg.Query.query.SQL
select t1.N_MID "n_mid",t1.N_UID "n_uid",t1.C_MSG "c_msg",t1.D_MSTIME "d_mstime" 
from tool.t_chat_msg t1

/* tableData HQL   */ 
--@JavaScript TChatMsg.Query.query.HQL

--@JavaScript TChatMsg.Query.query.JPQL

--@JavaScript TChatMsg.Query.queryOfflineMsg.SQL
select t1.N_MID "n_mid",t1.N_UID "n_uid",t1.C_MSG "c_msg",
	--t1.D_MSTIME "d_mstime",
	date_format(t1.D_MSTIME, '%Y-%m-%d %H:%i:%S') "d_mstime",
     C_USEX sex, C_ULOGNAME userlogname, C_UNAME username
from tool.t_chat_msg t1
left outer join t_authority_user t2
  on t1.N_UID = t2.N_UID
where t1.D_MSTIME> (
    select CASE WHEN t3.C_LOGOUTT IS NULL THEN t3.C_UCTIME ELSE t3.C_LOGOUTT END C_LOGOUTT
      from t_authority_user t3
     where t3.N_UID= :n_uid )
     
-- TChatMsg.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript TChatMsg.Read.read.SQL
select t1.N_MID "n_mid",t1.N_UID "n_uid",t1.C_MSG "c_msg",t1.D_MSTIME "d_mstime" 
from tool.t_chat_msg t1


-- TChatMsg.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript TChatMsg.Read.read.HQL
-- TChatMsg.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TTChatMsg t1 left outer join t1.NMparent p

--@JavaScript TChatMsg.Read.read.JPQL

-- SQL for select TChatMsg.Combobox.combobox.data
--@JavaScript TChatMsg.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select TChatMsg.Combobox.combobox.data
--@JavaScript TChatMsg.Combobox.combobox.HQL

--@JavaScript TChatMsg.Combobox.combobox.JPQL

-- TChatMsg.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript TChatMsg.Export.export.SQL
select t1.N_MID "n_mid",t1.N_UID "n_uid",t1.C_MSG "c_msg",t1.D_MSTIME "d_mstime" 
from tool.t_chat_msg t1
       
--@JavaScript TChatMsg.Export.export.HQL

--@JavaScript TChatMsg.Export.export.JPQL

--@JavaScript TChatMsg.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript TChatMsg.Nest.nest.HQL

--@JavaScript TChatMsg.Nest.nest.JPQL
