package person.daizhongde.migration.hibernate.vo;

public class SalaryVo {

	/** 姓名 */
	private String name;

	/** 员工编号   eg:  0086 */
	private String employee_no;
	/** 证件号  eg:  430722198710286115 */
	private String employee_idcard;
	

	/** 年月 eg:2018年02月 */
	private String yyyyMM;
	/** 计算期  eg:  2018/02/01-2018/02/28 */
	private String duration;

	
	/** 实发工资 */
	private double nSfgz;
	/** 本月工资 */
	private double nBygz;
	/** 税前补款 */
	private double nSqbk;
	/** 社保公积金个人扣款 */
	private double nSbgjjgrkk;
	/** 税后扣款 */
	private double nShkk;
	
	/** 基本工资 */
	private double nJbgz;
	/** 岗位工资 */
	private double nGwgz;
	/** 价值贡献绩效实发工资 */
	private double nJzgxjxsfgz;
	/** 补贴合计 */
	private double nBthj;
	

	/** 其他扣款（纳税） */
	private double nQtkk_ns;
	/** 考勤扣款 */
	private double nKqkk;
	/** 养老个人扣款 */
	private double nEndowment;
	/** 失业个人扣款 */
	private double nSygrkk;
	/** 医疗个人扣款 */
	private double nMedical;
	/** 公积金个人扣款 */
	private double nGjjgrkk;
	/** 个人所得税 */
	private double nGrsds;
	/** 其他扣款（免税） */
	private double nQtkk_ms=0;
	
	

	/** 年资津贴 */
	public double nNzjt;
	/** 加薪 (在doc中含在了奖励中)*/
	public double salary_increase;
	/** 开门红 */
	public double nKmh;
	/** 绩效 */
	public double nJiXiao;
	/** 防寒暑费 */
	public double nFhsf;
	/** 节日费 */
	public double nJrf;
//	/** 奖励 */
//	public double nJl;
	/** 应发合计 */
	public double nYfhj;
	/** 企业年金 */
	public double nQynj;
	/** 工会费 */
	public double nGhf;
	/** 房租费 */
	public double nFzf;
	/** 电费 */
	public double nDf;
	/** 物业费 */
	public double nWyf;
	/** 移动电话费 */
	public double nYddhf;
	/** 专项扣除 */
	public double nZxkc;
	/** 其他 */
	public double nQt;
	/** 扣税基数 */
	public double nKsjs;
	/** 代扣税 */
	public double nDks;
	/** 代付税 */
	public double nDfx;
	/** 绩效扣税 */
	public double nJxks;
	/** 扣税合计 */
	public double nKshj;
	/** 补扣退个税 */
	public double nBktgs;
	/** 本月扣税 */
	public double nByks;
	/** 扣款合计 */
	public double nKkhj;
	/** 实际扣款合计 */
	public double nSjkkhj;
	/** 实发合计 */
	public double nSfhj;
	/** 签名 */
	public String cSign;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYyyyMM() {
		return yyyyMM;
	}
	public void setYyyyMM(String yyyyMM) {
		this.yyyyMM = yyyyMM;
	}
	public String getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}
	public String getEmployee_idcard() {
		return employee_idcard;
	}
	public void setEmployee_idcard(String employee_idcard) {
		this.employee_idcard = employee_idcard;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public double getnSfgz() {
		return nSfgz;
	}
	public void setnSfgz(double nSfgz) {
		this.nSfgz = nSfgz;
	}
	public double getnBygz() {
		return nBygz;
	}
	public void setnBygz(double nBygz) {
		this.nBygz = nBygz;
	}
	public double getnSqbk() {
		return nSqbk;
	}
	public void setnSqbk(double nSqbk) {
		this.nSqbk = nSqbk;
	}
	public double getnSbgjjgrkk() {
		return nSbgjjgrkk;
	}
	public void setnSbgjjgrkk(double nSbgjjgrkk) {
		this.nSbgjjgrkk = nSbgjjgrkk;
	}
	public double getnShkk() {
		return nShkk;
	}
	public void setnShkk(double nShkk) {
		this.nShkk = nShkk;
	}
	public double getnJbgz() {
		return nJbgz;
	}
	public void setnJbgz(double nJbgz) {
		this.nJbgz = nJbgz;
	}
	public double getnGwgz() {
		return nGwgz;
	}
	public void setnGwgz(double nGwgz) {
		this.nGwgz = nGwgz;
	}
	public double getnJzgxjxsfgz() {
		return nJzgxjxsfgz;
	}
	public void setnJzgxjxsfgz(double nJzgxjxsfgz) {
		this.nJzgxjxsfgz = nJzgxjxsfgz;
	}
	public double getnBthj() {
		return nBthj;
	}
	public void setnBthj(double nBthj) {
		this.nBthj = nBthj;
	}
	
	public double getnQtkk_ns() {
		return nQtkk_ns;
	}
	public void setnQtkk_ns(double nQtkk_ns) {
		this.nQtkk_ns = nQtkk_ns;
	}
	public double getnKqkk() {
		return nKqkk;
	}
	public void setnKqkk(double nKqkk) {
		this.nKqkk = nKqkk;
	}
	public double getnEndowment() {
		return nEndowment;
	}
	public void setnEndowment(double nEndowment) {
		this.nEndowment = nEndowment;
	}
	public double getnSygrkk() {
		return nSygrkk;
	}
	public void setnSygrkk(double nSygrkk) {
		this.nSygrkk = nSygrkk;
	}
	public double getnMedical() {
		return nMedical;
	}
	public void setnMedical(double nMedical) {
		this.nMedical = nMedical;
	}
	public double getnGjjgrkk() {
		return nGjjgrkk;
	}
	public void setnGjjgrkk(double nGjjgrkk) {
		this.nGjjgrkk = nGjjgrkk;
	}
	public double getnGrsds() {
		return nGrsds;
	}
	public void setnGrsds(double nGrsds) {
		this.nGrsds = nGrsds;
	}
	public double getnQtkk_ms() {
		return nQtkk_ms;
	}
	public void setnQtkk_ms(double nQtkk_ms) {
		this.nQtkk_ms = nQtkk_ms;
	}
	public double getnNzjt() {
		return nNzjt;
	}
	public void setnNzjt(double nNzjt) {
		this.nNzjt = nNzjt;
	}
	
	
}
