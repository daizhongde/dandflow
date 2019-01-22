
package person.daizhongde.migration.spring.service.wsclient.cominterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the person.daizhongde.migration.spring.service.wsclient.cominterface package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExecuteSqlResponseRetMsg_QNAME = new QName("", "retMsg");
    private final static QName _LegalityAuditSzMsg_QNAME = new QName("", "szMsg");
    private final static QName _EXCUTESQLCONFIGExecSql_QNAME = new QName("", "exec-sql");
    private final static QName _EXCUTESQLCONFIGJobId_QNAME = new QName("", "job-id");
    private final static QName _EXCUTESQLCONFIGTaskId_QNAME = new QName("", "task-id");
    private final static QName _EXCUTESQLCONFIGParallelNum_QNAME = new QName("", "parallel-num");
    private final static QName _EXCUTESQLCONFIGIsCheck_QNAME = new QName("", "isCheck");
    private final static QName _EXCUTESQLCONFIGDstConn_QNAME = new QName("", "dst-conn");
    private final static QName _EXCUTESQLCONFIGComPara_QNAME = new QName("", "com-para");
    private final static QName _EXCUTESQLCONFIGJobInsId_QNAME = new QName("", "job-ins-id");
    private final static QName _OUTDATABASECONFIGIsClearTable_QNAME = new QName("", "isClearTable");
    private final static QName _OUTDATABASECONFIGGroup_QNAME = new QName("", "group");
    private final static QName _OUTDATABASECONFIGBusiness_QNAME = new QName("", "business");
    private final static QName _EXELINUXBINCONFIGIswait_QNAME = new QName("", "iswait");
    private final static QName _EXELINUXBINCONFIGHostIp_QNAME = new QName("", "host-ip");
    private final static QName _EXELINUXBINCONFIGCommand_QNAME = new QName("", "command");
    private final static QName _EXELINUXBINCONFIGHostConn_QNAME = new QName("", "host-conn");
    private final static QName _LOADDATACONFIGBackupPath_QNAME = new QName("", "backup-path");
    private final static QName _LOADDATACONFIGIsTruncateTable_QNAME = new QName("", "isTruncateTable");
    private final static QName _LOADDATACONFIGSuccessPath_QNAME = new QName("", "success-path");
    private final static QName _LOADDATACONFIGLoadfileOver_QNAME = new QName("", "loadfile-over");
    private final static QName _LOADDATACONFIGErrorPath_QNAME = new QName("", "error-path");
    private final static QName _LOADDATACONFIGDelimiter_QNAME = new QName("", "delimiter");
    private final static QName _LOADDATACONFIGIsWait_QNAME = new QName("", "isWait");
    private final static QName _LOADDATACONFIGInputPath_QNAME = new QName("", "input-path");
    private final static QName _LOADDATACONFIGDealPath_QNAME = new QName("", "deal-path");
    private final static QName _SPLITCONFIGIsplitLine_QNAME = new QName("", "isplit-line");
    private final static QName _SPLITCONFIGOverFilename_QNAME = new QName("", "over-filename");
    private final static QName _SPLITCONFIGOutputPath_QNAME = new QName("", "output-path");
    private final static QName _OUTDATAFILECONFIGIsClearFile_QNAME = new QName("", "isClearFile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: person.daizhongde.migration.spring.service.wsclient.cominterface
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FieldAudit }
     * 
     */
    public FieldAudit createFieldAudit() {
        return new FieldAudit();
    }

    /**
     * Create an instance of {@link FIELDAUDITCONFIG }
     * 
     */
    public FIELDAUDITCONFIG createFIELDAUDITCONFIG() {
        return new FIELDAUDITCONFIG();
    }

    /**
     * Create an instance of {@link OutDataFileResponse }
     * 
     */
    public OutDataFileResponse createOutDataFileResponse() {
        return new OutDataFileResponse();
    }

    /**
     * Create an instance of {@link OutDataBaseResponse }
     * 
     */
    public OutDataBaseResponse createOutDataBaseResponse() {
        return new OutDataBaseResponse();
    }

    /**
     * Create an instance of {@link LoadDataResponse }
     * 
     */
    public LoadDataResponse createLoadDataResponse() {
        return new LoadDataResponse();
    }

    /**
     * Create an instance of {@link ControllerResponse }
     * 
     */
    public ControllerResponse createControllerResponse() {
        return new ControllerResponse();
    }

    /**
     * Create an instance of {@link SplitFileResponse }
     * 
     */
    public SplitFileResponse createSplitFileResponse() {
        return new SplitFileResponse();
    }

    /**
     * Create an instance of {@link SplitFile }
     * 
     */
    public SplitFile createSplitFile() {
        return new SplitFile();
    }

    /**
     * Create an instance of {@link SPLITCONFIG }
     * 
     */
    public SPLITCONFIG createSPLITCONFIG() {
        return new SPLITCONFIG();
    }

    /**
     * Create an instance of {@link ExeLinuxBinResponse }
     * 
     */
    public ExeLinuxBinResponse createExeLinuxBinResponse() {
        return new ExeLinuxBinResponse();
    }

    /**
     * Create an instance of {@link LegalityAuditResponse }
     * 
     */
    public LegalityAuditResponse createLegalityAuditResponse() {
        return new LegalityAuditResponse();
    }

    /**
     * Create an instance of {@link ExecuteSqlResponse }
     * 
     */
    public ExecuteSqlResponse createExecuteSqlResponse() {
        return new ExecuteSqlResponse();
    }

    /**
     * Create an instance of {@link OutDataBase }
     * 
     */
    public OutDataBase createOutDataBase() {
        return new OutDataBase();
    }

    /**
     * Create an instance of {@link OUTDATABASECONFIG }
     * 
     */
    public OUTDATABASECONFIG createOUTDATABASECONFIG() {
        return new OUTDATABASECONFIG();
    }

    /**
     * Create an instance of {@link LegalityAudit }
     * 
     */
    public LegalityAudit createLegalityAudit() {
        return new LegalityAudit();
    }

    /**
     * Create an instance of {@link AUDITDATACONFIG }
     * 
     */
    public AUDITDATACONFIG createAUDITDATACONFIG() {
        return new AUDITDATACONFIG();
    }

    /**
     * Create an instance of {@link ConsistencyAuditResponse }
     * 
     */
    public ConsistencyAuditResponse createConsistencyAuditResponse() {
        return new ConsistencyAuditResponse();
    }

    /**
     * Create an instance of {@link ExeLinuxBin }
     * 
     */
    public ExeLinuxBin createExeLinuxBin() {
        return new ExeLinuxBin();
    }

    /**
     * Create an instance of {@link EXELINUXBINCONFIG }
     * 
     */
    public EXELINUXBINCONFIG createEXELINUXBINCONFIG() {
        return new EXELINUXBINCONFIG();
    }

    /**
     * Create an instance of {@link ConsistencyAudit }
     * 
     */
    public ConsistencyAudit createConsistencyAudit() {
        return new ConsistencyAudit();
    }

    /**
     * Create an instance of {@link CONSISTENCYAUDITCONFIG }
     * 
     */
    public CONSISTENCYAUDITCONFIG createCONSISTENCYAUDITCONFIG() {
        return new CONSISTENCYAUDITCONFIG();
    }

    /**
     * Create an instance of {@link FieldAuditResponse }
     * 
     */
    public FieldAuditResponse createFieldAuditResponse() {
        return new FieldAuditResponse();
    }

    /**
     * Create an instance of {@link Controller }
     * 
     */
    public Controller createController() {
        return new Controller();
    }

    /**
     * Create an instance of {@link CONTROLSIGNAL }
     * 
     */
    public CONTROLSIGNAL createCONTROLSIGNAL() {
        return new CONTROLSIGNAL();
    }

    /**
     * Create an instance of {@link ExecuteSql }
     * 
     */
    public ExecuteSql createExecuteSql() {
        return new ExecuteSql();
    }

    /**
     * Create an instance of {@link EXCUTESQLCONFIG }
     * 
     */
    public EXCUTESQLCONFIG createEXCUTESQLCONFIG() {
        return new EXCUTESQLCONFIG();
    }

    /**
     * Create an instance of {@link LoadData }
     * 
     */
    public LoadData createLoadData() {
        return new LoadData();
    }

    /**
     * Create an instance of {@link LOADDATACONFIG }
     * 
     */
    public LOADDATACONFIG createLOADDATACONFIG() {
        return new LOADDATACONFIG();
    }

    /**
     * Create an instance of {@link OutDataFile }
     * 
     */
    public OutDataFile createOutDataFile() {
        return new OutDataFile();
    }

    /**
     * Create an instance of {@link OUTDATAFILECONFIG }
     * 
     */
    public OUTDATAFILECONFIG createOUTDATAFILECONFIG() {
        return new OUTDATAFILECONFIG();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "retMsg", scope = ExecuteSqlResponse.class)
    public JAXBElement<String> createExecuteSqlResponseRetMsg(String value) {
        return new JAXBElement<String>(_ExecuteSqlResponseRetMsg_QNAME, String.class, ExecuteSqlResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "retMsg", scope = OutDataBaseResponse.class)
    public JAXBElement<String> createOutDataBaseResponseRetMsg(String value) {
        return new JAXBElement<String>(_ExecuteSqlResponseRetMsg_QNAME, String.class, OutDataBaseResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUDITDATACONFIG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "szMsg", scope = LegalityAudit.class)
    public JAXBElement<AUDITDATACONFIG> createLegalityAuditSzMsg(AUDITDATACONFIG value) {
        return new JAXBElement<AUDITDATACONFIG>(_LegalityAuditSzMsg_QNAME, AUDITDATACONFIG.class, LegalityAudit.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "retMsg", scope = ExeLinuxBinResponse.class)
    public JAXBElement<String> createExeLinuxBinResponseRetMsg(String value) {
        return new JAXBElement<String>(_ExecuteSqlResponseRetMsg_QNAME, String.class, ExeLinuxBinResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "retMsg", scope = LegalityAuditResponse.class)
    public JAXBElement<String> createLegalityAuditResponseRetMsg(String value) {
        return new JAXBElement<String>(_ExecuteSqlResponseRetMsg_QNAME, String.class, LegalityAuditResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "exec-sql", scope = EXCUTESQLCONFIG.class)
    public JAXBElement<String> createEXCUTESQLCONFIGExecSql(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGExecSql_QNAME, String.class, EXCUTESQLCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-id", scope = EXCUTESQLCONFIG.class)
    public JAXBElement<String> createEXCUTESQLCONFIGJobId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobId_QNAME, String.class, EXCUTESQLCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task-id", scope = EXCUTESQLCONFIG.class)
    public JAXBElement<String> createEXCUTESQLCONFIGTaskId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGTaskId_QNAME, String.class, EXCUTESQLCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parallel-num", scope = EXCUTESQLCONFIG.class)
    public JAXBElement<String> createEXCUTESQLCONFIGParallelNum(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGParallelNum_QNAME, String.class, EXCUTESQLCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isCheck", scope = EXCUTESQLCONFIG.class)
    public JAXBElement<String> createEXCUTESQLCONFIGIsCheck(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGIsCheck_QNAME, String.class, EXCUTESQLCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dst-conn", scope = EXCUTESQLCONFIG.class)
    public JAXBElement<String> createEXCUTESQLCONFIGDstConn(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGDstConn_QNAME, String.class, EXCUTESQLCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "com-para", scope = EXCUTESQLCONFIG.class)
    public JAXBElement<String> createEXCUTESQLCONFIGComPara(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGComPara_QNAME, String.class, EXCUTESQLCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-ins-id", scope = EXCUTESQLCONFIG.class)
    public JAXBElement<String> createEXCUTESQLCONFIGJobInsId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobInsId_QNAME, String.class, EXCUTESQLCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CONTROLSIGNAL }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "szMsg", scope = Controller.class)
    public JAXBElement<CONTROLSIGNAL> createControllerSzMsg(CONTROLSIGNAL value) {
        return new JAXBElement<CONTROLSIGNAL>(_LegalityAuditSzMsg_QNAME, CONTROLSIGNAL.class, Controller.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CONSISTENCYAUDITCONFIG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "szMsg", scope = ConsistencyAudit.class)
    public JAXBElement<CONSISTENCYAUDITCONFIG> createConsistencyAuditSzMsg(CONSISTENCYAUDITCONFIG value) {
        return new JAXBElement<CONSISTENCYAUDITCONFIG>(_LegalityAuditSzMsg_QNAME, CONSISTENCYAUDITCONFIG.class, ConsistencyAudit.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-id", scope = OUTDATABASECONFIG.class)
    public JAXBElement<String> createOUTDATABASECONFIGJobId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobId_QNAME, String.class, OUTDATABASECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task-id", scope = OUTDATABASECONFIG.class)
    public JAXBElement<String> createOUTDATABASECONFIGTaskId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGTaskId_QNAME, String.class, OUTDATABASECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isClearTable", scope = OUTDATABASECONFIG.class)
    public JAXBElement<String> createOUTDATABASECONFIGIsClearTable(String value) {
        return new JAXBElement<String>(_OUTDATABASECONFIGIsClearTable_QNAME, String.class, OUTDATABASECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parallel-num", scope = OUTDATABASECONFIG.class)
    public JAXBElement<String> createOUTDATABASECONFIGParallelNum(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGParallelNum_QNAME, String.class, OUTDATABASECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isCheck", scope = OUTDATABASECONFIG.class)
    public JAXBElement<String> createOUTDATABASECONFIGIsCheck(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGIsCheck_QNAME, String.class, OUTDATABASECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "group", scope = OUTDATABASECONFIG.class)
    public JAXBElement<String> createOUTDATABASECONFIGGroup(String value) {
        return new JAXBElement<String>(_OUTDATABASECONFIGGroup_QNAME, String.class, OUTDATABASECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "com-para", scope = OUTDATABASECONFIG.class)
    public JAXBElement<String> createOUTDATABASECONFIGComPara(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGComPara_QNAME, String.class, OUTDATABASECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-ins-id", scope = OUTDATABASECONFIG.class)
    public JAXBElement<String> createOUTDATABASECONFIGJobInsId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobInsId_QNAME, String.class, OUTDATABASECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "business", scope = OUTDATABASECONFIG.class)
    public JAXBElement<String> createOUTDATABASECONFIGBusiness(String value) {
        return new JAXBElement<String>(_OUTDATABASECONFIGBusiness_QNAME, String.class, OUTDATABASECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "retMsg", scope = LoadDataResponse.class)
    public JAXBElement<String> createLoadDataResponseRetMsg(String value) {
        return new JAXBElement<String>(_ExecuteSqlResponseRetMsg_QNAME, String.class, LoadDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OUTDATABASECONFIG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "szMsg", scope = OutDataBase.class)
    public JAXBElement<OUTDATABASECONFIG> createOutDataBaseSzMsg(OUTDATABASECONFIG value) {
        return new JAXBElement<OUTDATABASECONFIG>(_LegalityAuditSzMsg_QNAME, OUTDATABASECONFIG.class, OutDataBase.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "iswait", scope = EXELINUXBINCONFIG.class)
    public JAXBElement<String> createEXELINUXBINCONFIGIswait(String value) {
        return new JAXBElement<String>(_EXELINUXBINCONFIGIswait_QNAME, String.class, EXELINUXBINCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "host-ip", scope = EXELINUXBINCONFIG.class)
    public JAXBElement<String> createEXELINUXBINCONFIGHostIp(String value) {
        return new JAXBElement<String>(_EXELINUXBINCONFIGHostIp_QNAME, String.class, EXELINUXBINCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-id", scope = EXELINUXBINCONFIG.class)
    public JAXBElement<String> createEXELINUXBINCONFIGJobId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobId_QNAME, String.class, EXELINUXBINCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task-id", scope = EXELINUXBINCONFIG.class)
    public JAXBElement<String> createEXELINUXBINCONFIGTaskId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGTaskId_QNAME, String.class, EXELINUXBINCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "command", scope = EXELINUXBINCONFIG.class)
    public JAXBElement<String> createEXELINUXBINCONFIGCommand(String value) {
        return new JAXBElement<String>(_EXELINUXBINCONFIGCommand_QNAME, String.class, EXELINUXBINCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isCheck", scope = EXELINUXBINCONFIG.class)
    public JAXBElement<String> createEXELINUXBINCONFIGIsCheck(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGIsCheck_QNAME, String.class, EXELINUXBINCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "com-para", scope = EXELINUXBINCONFIG.class)
    public JAXBElement<String> createEXELINUXBINCONFIGComPara(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGComPara_QNAME, String.class, EXELINUXBINCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-ins-id", scope = EXELINUXBINCONFIG.class)
    public JAXBElement<String> createEXELINUXBINCONFIGJobInsId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobInsId_QNAME, String.class, EXELINUXBINCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "host-conn", scope = EXELINUXBINCONFIG.class)
    public JAXBElement<String> createEXELINUXBINCONFIGHostConn(String value) {
        return new JAXBElement<String>(_EXELINUXBINCONFIGHostConn_QNAME, String.class, EXELINUXBINCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "backup-path", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGBackupPath(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGBackupPath_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task-id", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGTaskId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGTaskId_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isTruncateTable", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGIsTruncateTable(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGIsTruncateTable_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isCheck", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGIsCheck(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGIsCheck_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "success-path", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGSuccessPath(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGSuccessPath_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-ins-id", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGJobInsId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobInsId_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "loadfile-over", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGLoadfileOver(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGLoadfileOver_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "business", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGBusiness(String value) {
        return new JAXBElement<String>(_OUTDATABASECONFIGBusiness_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "error-path", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGErrorPath(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGErrorPath_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "delimiter", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGDelimiter(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGDelimiter_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-id", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGJobId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobId_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isWait", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGIsWait(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGIsWait_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "input-path", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGInputPath(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGInputPath_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "deal-path", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGDealPath(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGDealPath_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parallel-num", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGParallelNum(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGParallelNum_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "com-para", scope = LOADDATACONFIG.class)
    public JAXBElement<String> createLOADDATACONFIGComPara(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGComPara_QNAME, String.class, LOADDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EXELINUXBINCONFIG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "szMsg", scope = ExeLinuxBin.class)
    public JAXBElement<EXELINUXBINCONFIG> createExeLinuxBinSzMsg(EXELINUXBINCONFIG value) {
        return new JAXBElement<EXELINUXBINCONFIG>(_LegalityAuditSzMsg_QNAME, EXELINUXBINCONFIG.class, ExeLinuxBin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-id", scope = AUDITDATACONFIG.class)
    public JAXBElement<String> createAUDITDATACONFIGJobId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobId_QNAME, String.class, AUDITDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task-id", scope = AUDITDATACONFIG.class)
    public JAXBElement<String> createAUDITDATACONFIGTaskId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGTaskId_QNAME, String.class, AUDITDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parallel-num", scope = AUDITDATACONFIG.class)
    public JAXBElement<String> createAUDITDATACONFIGParallelNum(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGParallelNum_QNAME, String.class, AUDITDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isCheck", scope = AUDITDATACONFIG.class)
    public JAXBElement<String> createAUDITDATACONFIGIsCheck(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGIsCheck_QNAME, String.class, AUDITDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "com-para", scope = AUDITDATACONFIG.class)
    public JAXBElement<String> createAUDITDATACONFIGComPara(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGComPara_QNAME, String.class, AUDITDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-ins-id", scope = AUDITDATACONFIG.class)
    public JAXBElement<String> createAUDITDATACONFIGJobInsId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobInsId_QNAME, String.class, AUDITDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "business", scope = AUDITDATACONFIG.class)
    public JAXBElement<String> createAUDITDATACONFIGBusiness(String value) {
        return new JAXBElement<String>(_OUTDATABASECONFIGBusiness_QNAME, String.class, AUDITDATACONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPLITCONFIG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "szMsg", scope = SplitFile.class)
    public JAXBElement<SPLITCONFIG> createSplitFileSzMsg(SPLITCONFIG value) {
        return new JAXBElement<SPLITCONFIG>(_LegalityAuditSzMsg_QNAME, SPLITCONFIG.class, SplitFile.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "retMsg", scope = OutDataFileResponse.class)
    public JAXBElement<String> createOutDataFileResponseRetMsg(String value) {
        return new JAXBElement<String>(_ExecuteSqlResponseRetMsg_QNAME, String.class, OutDataFileResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OUTDATAFILECONFIG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "szMsg", scope = OutDataFile.class)
    public JAXBElement<OUTDATAFILECONFIG> createOutDataFileSzMsg(OUTDATAFILECONFIG value) {
        return new JAXBElement<OUTDATAFILECONFIG>(_LegalityAuditSzMsg_QNAME, OUTDATAFILECONFIG.class, OutDataFile.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-id", scope = CONTROLSIGNAL.class)
    public JAXBElement<String> createCONTROLSIGNALJobId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobId_QNAME, String.class, CONTROLSIGNAL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task-id", scope = CONTROLSIGNAL.class)
    public JAXBElement<String> createCONTROLSIGNALTaskId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGTaskId_QNAME, String.class, CONTROLSIGNAL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-ins-id", scope = CONTROLSIGNAL.class)
    public JAXBElement<String> createCONTROLSIGNALJobInsId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobInsId_QNAME, String.class, CONTROLSIGNAL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LOADDATACONFIG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "szMsg", scope = LoadData.class)
    public JAXBElement<LOADDATACONFIG> createLoadDataSzMsg(LOADDATACONFIG value) {
        return new JAXBElement<LOADDATACONFIG>(_LegalityAuditSzMsg_QNAME, LOADDATACONFIG.class, LoadData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "retMsg", scope = ControllerResponse.class)
    public JAXBElement<String> createControllerResponseRetMsg(String value) {
        return new JAXBElement<String>(_ExecuteSqlResponseRetMsg_QNAME, String.class, ControllerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FIELDAUDITCONFIG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "szMsg", scope = FieldAudit.class)
    public JAXBElement<FIELDAUDITCONFIG> createFieldAuditSzMsg(FIELDAUDITCONFIG value) {
        return new JAXBElement<FIELDAUDITCONFIG>(_LegalityAuditSzMsg_QNAME, FIELDAUDITCONFIG.class, FieldAudit.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EXCUTESQLCONFIG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "szMsg", scope = ExecuteSql.class)
    public JAXBElement<EXCUTESQLCONFIG> createExecuteSqlSzMsg(EXCUTESQLCONFIG value) {
        return new JAXBElement<EXCUTESQLCONFIG>(_LegalityAuditSzMsg_QNAME, EXCUTESQLCONFIG.class, ExecuteSql.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "backup-path", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGBackupPath(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGBackupPath_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task-id", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGTaskId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGTaskId_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isplit-line", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGIsplitLine(String value) {
        return new JAXBElement<String>(_SPLITCONFIGIsplitLine_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isCheck", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGIsCheck(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGIsCheck_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-ins-id", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGJobInsId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobInsId_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "business", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGBusiness(String value) {
        return new JAXBElement<String>(_OUTDATABASECONFIGBusiness_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "delimiter", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGDelimiter(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGDelimiter_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-id", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGJobId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobId_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isWait", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGIsWait(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGIsWait_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "input-path", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGInputPath(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGInputPath_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "deal-path", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGDealPath(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGDealPath_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "over-filename", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGOverFilename(String value) {
        return new JAXBElement<String>(_SPLITCONFIGOverFilename_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parallel-num", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGParallelNum(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGParallelNum_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "output-path", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGOutputPath(String value) {
        return new JAXBElement<String>(_SPLITCONFIGOutputPath_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "com-para", scope = SPLITCONFIG.class)
    public JAXBElement<String> createSPLITCONFIGComPara(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGComPara_QNAME, String.class, SPLITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "delimiter", scope = FIELDAUDITCONFIG.class)
    public JAXBElement<String> createFIELDAUDITCONFIGDelimiter(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGDelimiter_QNAME, String.class, FIELDAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-id", scope = FIELDAUDITCONFIG.class)
    public JAXBElement<String> createFIELDAUDITCONFIGJobId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobId_QNAME, String.class, FIELDAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isWait", scope = FIELDAUDITCONFIG.class)
    public JAXBElement<String> createFIELDAUDITCONFIGIsWait(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGIsWait_QNAME, String.class, FIELDAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task-id", scope = FIELDAUDITCONFIG.class)
    public JAXBElement<String> createFIELDAUDITCONFIGTaskId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGTaskId_QNAME, String.class, FIELDAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parallel-num", scope = FIELDAUDITCONFIG.class)
    public JAXBElement<String> createFIELDAUDITCONFIGParallelNum(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGParallelNum_QNAME, String.class, FIELDAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isCheck", scope = FIELDAUDITCONFIG.class)
    public JAXBElement<String> createFIELDAUDITCONFIGIsCheck(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGIsCheck_QNAME, String.class, FIELDAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "com-para", scope = FIELDAUDITCONFIG.class)
    public JAXBElement<String> createFIELDAUDITCONFIGComPara(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGComPara_QNAME, String.class, FIELDAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-ins-id", scope = FIELDAUDITCONFIG.class)
    public JAXBElement<String> createFIELDAUDITCONFIGJobInsId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobInsId_QNAME, String.class, FIELDAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "business", scope = FIELDAUDITCONFIG.class)
    public JAXBElement<String> createFIELDAUDITCONFIGBusiness(String value) {
        return new JAXBElement<String>(_OUTDATABASECONFIGBusiness_QNAME, String.class, FIELDAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-id", scope = CONSISTENCYAUDITCONFIG.class)
    public JAXBElement<String> createCONSISTENCYAUDITCONFIGJobId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobId_QNAME, String.class, CONSISTENCYAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task-id", scope = CONSISTENCYAUDITCONFIG.class)
    public JAXBElement<String> createCONSISTENCYAUDITCONFIGTaskId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGTaskId_QNAME, String.class, CONSISTENCYAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parallel-num", scope = CONSISTENCYAUDITCONFIG.class)
    public JAXBElement<String> createCONSISTENCYAUDITCONFIGParallelNum(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGParallelNum_QNAME, String.class, CONSISTENCYAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isCheck", scope = CONSISTENCYAUDITCONFIG.class)
    public JAXBElement<String> createCONSISTENCYAUDITCONFIGIsCheck(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGIsCheck_QNAME, String.class, CONSISTENCYAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "com-para", scope = CONSISTENCYAUDITCONFIG.class)
    public JAXBElement<String> createCONSISTENCYAUDITCONFIGComPara(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGComPara_QNAME, String.class, CONSISTENCYAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-ins-id", scope = CONSISTENCYAUDITCONFIG.class)
    public JAXBElement<String> createCONSISTENCYAUDITCONFIGJobInsId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobInsId_QNAME, String.class, CONSISTENCYAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "business", scope = CONSISTENCYAUDITCONFIG.class)
    public JAXBElement<String> createCONSISTENCYAUDITCONFIGBusiness(String value) {
        return new JAXBElement<String>(_OUTDATABASECONFIGBusiness_QNAME, String.class, CONSISTENCYAUDITCONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "retMsg", scope = ConsistencyAuditResponse.class)
    public JAXBElement<String> createConsistencyAuditResponseRetMsg(String value) {
        return new JAXBElement<String>(_ExecuteSqlResponseRetMsg_QNAME, String.class, ConsistencyAuditResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "retMsg", scope = FieldAuditResponse.class)
    public JAXBElement<String> createFieldAuditResponseRetMsg(String value) {
        return new JAXBElement<String>(_ExecuteSqlResponseRetMsg_QNAME, String.class, FieldAuditResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "retMsg", scope = SplitFileResponse.class)
    public JAXBElement<String> createSplitFileResponseRetMsg(String value) {
        return new JAXBElement<String>(_ExecuteSqlResponseRetMsg_QNAME, String.class, SplitFileResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "task-id", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGTaskId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGTaskId_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isCheck", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGIsCheck(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGIsCheck_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-ins-id", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGJobInsId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobInsId_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "business", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGBusiness(String value) {
        return new JAXBElement<String>(_OUTDATABASECONFIGBusiness_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "delimiter", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGDelimiter(String value) {
        return new JAXBElement<String>(_LOADDATACONFIGDelimiter_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isClearFile", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGIsClearFile(String value) {
        return new JAXBElement<String>(_OUTDATAFILECONFIGIsClearFile_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job-id", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGJobId(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGJobId_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "host-ip", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGHostIp(String value) {
        return new JAXBElement<String>(_EXELINUXBINCONFIGHostIp_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parallel-num", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGParallelNum(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGParallelNum_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "output-path", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGOutputPath(String value) {
        return new JAXBElement<String>(_SPLITCONFIGOutputPath_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "group", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGGroup(String value) {
        return new JAXBElement<String>(_OUTDATABASECONFIGGroup_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "com-para", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGComPara(String value) {
        return new JAXBElement<String>(_EXCUTESQLCONFIGComPara_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "host-conn", scope = OUTDATAFILECONFIG.class)
    public JAXBElement<String> createOUTDATAFILECONFIGHostConn(String value) {
        return new JAXBElement<String>(_EXELINUXBINCONFIGHostConn_QNAME, String.class, OUTDATAFILECONFIG.class, value);
    }

}
