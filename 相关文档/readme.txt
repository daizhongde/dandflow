environment:
	jdk 1.7.0_71+
	tomcat 8.0.15+

java:
	
struts 2.3.16.3
	SPRING FRAMEWORK 4.1.2
	hibernate 3.6.10
	
	
struts2-json-plugin-2.3.16.3
	struts2-spring-plugin-2.3.16.3   
#struts2-spring-plugin->commons-jci
	commons-jci  1.0


javascript:
	jquery 1.5.2
	
jQuery UI Widget 1.8.12
	jQuery EasyUI 1.3.5
	
CKEditor 3.6    --html editor
	CKFinder 2.0.2.1    
	j
query.md5.js


由于struts2-json-plugin-2.3.16.3默认对oracle中char类型的数据只转化成一个字符
所以select 
char类型的字段时先to_char()

--TO_CHAR (character) converts NCHAR, NVARCHAR2, CLOB, or NCLOB data to the database character set. 

-The value returned is always VARCHAR2.


这个版本是业务参数配置采用tree之前的版本