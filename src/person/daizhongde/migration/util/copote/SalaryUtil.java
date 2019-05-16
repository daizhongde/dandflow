package person.daizhongde.migration.util.copote;

import java.util.Set;
import java.util.Vector;

import person.daizhongde.migration.hibernate.vo.SalaryVo;

public class SalaryUtil {
	public static void switchCaseColNameAndValue(String colName, String value, MinRYVo vo, SalaryVo o,
			Set<String> nameSet,
			Vector<String> nameVec) {
		if(null==value||"".equalsIgnoreCase(value.trim())||"null".equalsIgnoreCase(value.trim())){
			value="0";
		}
		colName = colName.replaceAll("[']","");
		switch (colName) {
		case "人员编号":
			vo.employee_no = value;
			value = "0".equalsIgnoreCase(value)?"":value;
			o.setEmployee_no(value);
			// System.out.print("人员编号|")
			break;
		case "姓名":
			nameSet.add(value);
			nameVec.add(value);
			vo.name = value;
			o.setName(value);
			// System.out.print("姓名|")
			break;
		case "身份证号码":
			vo.employee_idcard = value;
			value = "0".equalsIgnoreCase(value)?"":value;
			o.setEmployee_idcard(value);
			// System.out.print("身份证号码|")
			break;
		case "身份证号":
			vo.employee_idcard = value;
			value = "0".equalsIgnoreCase(value)?"":value;
			o.setEmployee_idcard(value);
			// System.out.print("身份证号|")
			break;
		case "基本工资":
			o.setnJbgz(Double.valueOf(value));
			// System.out.print("基本工资|")
			break;
		case "岗位工资":
			o.setnGwgz(Double.valueOf(value));
			// System.out.print("岗位工资|")
			break;
		case "年资津贴":
			o.setnNzjt(Double.valueOf(value));
			// System.out.print("年资津贴|")
			break;

		case "加薪":// 即doc中的奖励
			o.salary_increase = Double.valueOf(value);
			// System.out.print("加薪|")
			break;
		case "开门红":
			o.nKmh = Double.valueOf(value);
			// System.out.print("开门红|")
			break;
		case "绩效"://绩效实发工资(湘邮绩效就一块，没有基本绩效)
			o.nJiXiao = Double.valueOf(value);
			o.setnJzgxjxsfgz(o.nJiXiao);
			// System.out.print("绩效|")
			break;
		case "防寒暑费":
			o.nFhsf = Double.valueOf(value);
			// System.out.print("防寒暑费|")
			break;
		case "节日费":
			o.nJrf = Double.valueOf(value);
			// System.out.print("节日|")
			break;
//		case "奖励":
//			o.nJl = Double.valueOf(value);
			// System.out.print("奖励|")
//			break;
		case "应发合计":
			o.nYfhj = Double.valueOf(value);
			// System.out.print("应发合计|")
			break;
			
	    /* ########   下面主要为扣款    ########     */
		case "基本养老金":
			o.setnEndowment(Double.valueOf(value));
			// System.out.print("基本养老|")
			break;
		case "医疗保险金":
			o.setnMedical(Double.valueOf(value));
			// System.out.print("医疗保险|")
			break;
		case "失业保险金":
			o.setnSygrkk(Double.valueOf(value));
			// System.out.print("失业保险|")
			break;
		case "住房公积金":
			o.setnGjjgrkk(Double.valueOf(value));
			// System.out.print("住房公积金|")
			break;
		case "企业年金"://即：企业年金个人扣款
			o.nQynj = Double.valueOf(value);
			// System.out.print("企业年金|")
			break;
		case "工会费":
			o.nGhf = Double.valueOf(value);
			// System.out.print("工会费|")
			break;
		case "房租费":
			o.nFzf = Double.valueOf(value);
			// System.out.print("房租费|")
			break;
		case "电费":
			o.nDf = Double.valueOf(value);
			// System.out.print("电费|")
			break;
		case "物业费":
			o.nWyf = Double.valueOf(value);
			// System.out.print("物业费|")
			break;
		case "移动电话费":
			o.nYddhf = Double.valueOf(value);
			// System.out.print("移动电话费|")
			break;
		case "专项扣除项目":
			o.nZxkc = Double.valueOf(value);
			// System.out.print("专项扣除|")
			break;
		case "其他":
			o.nQt = Double.valueOf(value);
			// System.out.print("其他|")
			break;
		case "扣税基数":
			o.nKsjs = Double.valueOf(value);
			// System.out.print("扣税基数|")
			break;
		case "代扣税":
			o.nDks = Double.valueOf(value);
			// System.out.print("代扣税|")
			break;
		case "代付税":
			o.nDfx = Double.valueOf(value);
			// System.out.print("代付税|")
			break;
		case "绩效扣税":
			o.nJxks = Double.valueOf(value);
			// System.out.print("绩效扣税|")
			break;
		case "扣税合计":
			o.nKshj = Double.valueOf(value);
			o.setnGrsds(o.nKshj);
			// System.out.print("扣税合计|")
			break;
		case "补扣退个税":
			o.nBktgs = Double.valueOf(value);
			// System.out.print("补扣退个税|")
			break;
		case "本月扣税":
			o.nByks = Double.valueOf(value);
			// System.out.print("本月扣税|")
			break;
		case "扣款合计":
			o.nKkhj = Double.valueOf(value);
			// System.out.print("扣款合计|")
			break;
		case "实际扣款合计":
			o.nSjkkhj = Double.valueOf(value);
			// System.out.print("实际扣款合计|")
			break;
		case "实发合计":
			o.nSfhj = Double.valueOf(value);
			o.setnSfgz(o.nSfhj);
			// System.out.print("实发合计|")
			break;
		case "签名":
			o.cSign = value;
			// System.out.print("签名|")
			break;			
		default:
			// System.out.print("default 不能识别的列名：" + value )
			break;
		}
	}

	/**
	 * 把列头和行数据转换成html表格
	 * 
	 * @param celltitle
	 * @param salaryArr
	 * @return
	 */
	public static String convert2HTMLTable(String[] celltitle, String[] salaryArr) {
		StringBuilder sb = new StringBuilder(
//				"<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Insert title here</title></head><body>"
				"<style> table {  border-collapse:collapse;  border:1px solid black;  } td {  height:20px;  border:1px solid black; }</style>");
		

		sb.append("*************注*意*：*试*运*行*期*过*后*正*文*不*再*显*示*工*资*信*息*************<br/>");
		sb.append("<table cellpadding=\"0\" cellspacing=\"0\" border=\"1\" width=\"1400px\">");
		sb.append("<tr>");
		for (int i = 0; i < celltitle.length; i++) {
			String w = "";
			if(celltitle[i].equalsIgnoreCase("签名")){
				w=" width=\"80px\"";
			}

			String v = salaryArr[i];
			v = (null==v||"null".equalsIgnoreCase(v)||"".equalsIgnoreCase(v.trim())?"0":v);
			
			if(celltitle[i].equalsIgnoreCase("签名")
				|| celltitle[i].equalsIgnoreCase("部门")
				|| celltitle[i].equalsIgnoreCase("人员类别")
				|| celltitle[i].startsWith("身份证")){
					continue;
			}
			if(!celltitle[i].equalsIgnoreCase("人员编号")
					&&!celltitle[i].equalsIgnoreCase("姓名")
					&&!celltitle[i].startsWith("身份证")
					&&!celltitle[i].equalsIgnoreCase("签名")
					&&!celltitle[i].equalsIgnoreCase("部门")
					&&!celltitle[i].equalsIgnoreCase("人员类别")){
				if(Double.valueOf(v)==0){
					continue;
				}
			}
			sb.append("<td"+w+">" + celltitle[i] + "</td>");
		}
		sb.append("</tr>");
		sb.append("<tr>");
		for (int i = 0; i < celltitle.length; i++) {
			String v = salaryArr[i];
			v = (null==v||"null".equalsIgnoreCase(v)||"".equalsIgnoreCase(v.trim())?"0":v);
			
			String w = "";

			if(celltitle[i].equalsIgnoreCase("签名")
				|| celltitle[i].equalsIgnoreCase("部门")
				|| celltitle[i].equalsIgnoreCase("人员类别")
				|| celltitle[i].startsWith("身份证")){
					continue;
			}
			if(!celltitle[i].equalsIgnoreCase("人员编号")
					&&!celltitle[i].equalsIgnoreCase("姓名")
					&&!celltitle[i].startsWith("身份证")
					&&!celltitle[i].equalsIgnoreCase("签名")
					&&!celltitle[i].equalsIgnoreCase("部门")
					&&!celltitle[i].equalsIgnoreCase("人员类别")){
				if(Double.valueOf(v)==0){
					continue;
				}
				w=" align=\"right\"";
			}
			sb.append("<td"+w+">" + v + "</td>");
		}
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<br/>*************注*意*：*试*运*行*期*过*后*正*文*不*再*显*示*工*资*信*息*************");
//		sb.append("</body></html>");

//		System.out.println(sb.toString());
		return sb.toString();

	}
}
