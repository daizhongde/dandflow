package person.daizhongde.migration.constant;

import person.daizhongde.virtue.configutils.ConfigReader_PROP;

/**
 * load on web server start. 
 * <br>change value need restart server, then the new value become effective
 * <p>globel config don't write getter,but special config like pertable need write getter to support develop mode 
 * @author dzd
 *
 */
public class MIGINIT {
	private final static String CFName = "init.properties";
	
	private final static String _PRODUCTION_MODE = ConfigReader_PROP.findProperty(CFName, "PRODUCTION_MODE");
	public final static boolean PRODUCTION_MODE = _PRODUCTION_MODE==null ? 
			false : Boolean.valueOf( _PRODUCTION_MODE ).booleanValue();
	
	private final static String OS = System.getProperties().getProperty("os.name");
	
	public final static String AUTH_schema = ConfigReader_PROP.findProperty(CFName, "AUTH.schema");
	public final static String AUTH_configFileDirectory = 
			ConfigReader_PROP.findProperty( CFName, 
				"AUTH.configFileDirectory"
			).replaceFirst("[\\\\\\/]+$", "");
	

	public final static String sit_HIP = ConfigReader_PROP.findProperty(CFName, "sit.HIP");
	public final static String sit_MySQLPARAM = ConfigReader_PROP.findProperty(CFName, "sit.MySQLPARAM");
	public final static String sit_DBNAME = ConfigReader_PROP.findProperty(CFName, "sit.DBNAME");
	public final static String uat_HIP = ConfigReader_PROP.findProperty(CFName, "uat.HIP");
	public final static String uat_MySQLPARAM = ConfigReader_PROP.findProperty(CFName, "uat.MySQLPARAM");
	public final static String uat_DBNAME = ConfigReader_PROP.findProperty(CFName, "uat.DBNAME");
	public final static String prod_HIP = ConfigReader_PROP.findProperty(CFName, "prod.HIP");
	public final static String prod_MySQLPARAM = ConfigReader_PROP.findProperty(CFName, "prod.MySQLPARAM");
	public final static String prod_DBNAME = ConfigReader_PROP.findProperty(CFName, "prod.DBNAME");
	
	public final static String src_test_HIP = ConfigReader_PROP.findProperty(CFName, "src.test.HIP");
	public final static String src_test_OraclePARAM = ConfigReader_PROP.findProperty(CFName, "src.test.OraclePARAM");
	public final static String src_prod_HIP = ConfigReader_PROP.findProperty(CFName, "src.prod.HIP");
	public final static String src_prod_OraclePARAM = ConfigReader_PROP.findProperty(CFName, "src.prod.OraclePARAM");
	
	
	
    // Prevent instantiation
    private MIGINIT() {}
    
	public static void main(String args[]) throws NoSuchFieldException, SecurityException{
		System.out.println("ConfigConstant.class.getField(\"TAuthorityModule_query\").getName():"+MIGINIT.class.getField("TAuthorityModule_query").getName());
		System.out.println("ConfigConstant.class.getField(\"TAuthorityModule_query\"):"+MIGINIT.class.getField("TAuthorityModule_query"));

		
	}
}