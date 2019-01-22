alter table t_authority_module modify N_MID int PRIMARY KEY IDENTITY (10001, 1) NOT NULL;
select @@identity;
select @@identity t_authority_module; 

--重置标识值
DBCC CHECKIDENT('Test_Identity', RESEED)
--重置标识值
DBCC CHECKIDENT('Test_Identity', RESEED, 995)
--3查询标识值
DBCC CHECKIDENT('t_authority_module', NORESEED)
--4隐式插入Id值
INSERT INTO [Test_Identity](Name)
SELECT 'name5'

show table status like 'test_t';