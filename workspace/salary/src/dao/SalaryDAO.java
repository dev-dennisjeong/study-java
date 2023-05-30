package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import domain.SalaryVO;

public class SalaryDAO {
//   추가
	public void insert(SalaryVO salaryVO) throws IOException {
		BufferedWriter bufferedWriter = DBConnecter.getAppend();
		String content = new String(Files.readAllBytes(Paths.get(DBConnecter.PATH)));
		String temp = null;
		temp = content.charAt(content.length() - 1) == '\n' ? "" : "\n";
		temp += salaryVO.toString();
		bufferedWriter.write(temp);
		bufferedWriter.close();
	}

//   수정(소득세)
	public void update(SalaryVO salaryVO) throws IOException {
		BufferedReader bufferedReader = DBConnecter.getReader();
		String line = null, temp = "";

		while ((line = bufferedReader.readLine()) != null) {
			if (line.split("   ")[0].equals(insertComma(salaryVO.getSalary()) + "만원")) {
				String data = line.substring(0, line.lastIndexOf("   "));

				temp += data.substring(0, data.lastIndexOf("   ")) + "   " + insertComma(salaryVO.getIncomeTax())
						+ "   " + insertComma(salaryVO.getLocalTax()) + "\n";
				continue;
			}
			temp += line + "\n";
		}
		BufferedWriter bufferedWriter = DBConnecter.getWriter();
		bufferedWriter.write(temp);

		bufferedWriter.close();
		bufferedReader.close();
	}

//   삭제
	public void delete(int salary) throws IOException {
		BufferedReader bufferedReader = DBConnecter.getReader();
		BufferedWriter bufferedWriter = null;
		String line = null, temp = "";

		while ((line = bufferedReader.readLine()) != null) {
			if (line.split("   ")[0].equals(insertComma(salary))) {
				continue;
			}
			temp += line + "\n";
		}

		bufferedWriter = DBConnecter.getWriter();
		bufferedWriter.write(temp);
		bufferedWriter.close();
		bufferedReader.close();
	}

//   조회
	public SalaryVO select(int salary) throws IOException {
		BufferedReader bufferedReader = DBConnecter.getReader();
		String line = null;
		SalaryVO salaryVO = new SalaryVO();

		while ((line = bufferedReader.readLine()) != null) {
			String[] stringDatas = line.split("   ");
			int[] datas = new int[stringDatas.length];

			for (int i = 0; i < stringDatas.length; i++) {
				datas[i] = Integer.parseInt(stringDatas[i].replaceAll(",", ""));
			}

			if (stringDatas[0].equals(insertComma(salary))) {
				salaryVO.setSalary(datas[0]);
				salaryVO.setNetPay(datas[1]);
				salaryVO.setTaxDeductionAmount(datas[2]);
				salaryVO.setPension(datas[3]);
				salaryVO.setHealthInsurance(datas[4]);
				salaryVO.setLongTermCarePay(datas[5]);
				salaryVO.setEmploymentInsurance(datas[6]);
				salaryVO.setIncomeTax(datas[7]);
				salaryVO.setLocalTax(datas[8]);
				break;
			}
		}
		return salaryVO;
	}

//   목록
	public ArrayList<SalaryVO> selectAll() throws IOException {
		BufferedReader bufferedReader = DBConnecter.getReader();
		String line = null;
		ArrayList<SalaryVO> salaries = new ArrayList<SalaryVO>();

		while ((line = bufferedReader.readLine()) != null) {
			SalaryVO salaryVO = new SalaryVO();
			String[] stringDatas = line.split("   ");
			int[] datas = new int[stringDatas.length];

			for (int i = 0; i < stringDatas.length; i++) {
				datas[i] = Integer.parseInt(stringDatas[i].replaceAll(",", ""));
			}

			salaryVO.setSalary(datas[0]);
			salaryVO.setNetPay(datas[1]);
			salaryVO.setTaxDeductionAmount(datas[2]);
			salaryVO.setPension(datas[3]);
			salaryVO.setHealthInsurance(datas[4]);
			salaryVO.setLongTermCarePay(datas[5]);
			salaryVO.setEmploymentInsurance(datas[6]);
			salaryVO.setIncomeTax(datas[7]);
			salaryVO.setLocalTax(datas[8]);

			salaries.add(salaryVO);
		}
		return salaries;
	}

	public static String insertComma(int number) {
		String temp = String.valueOf(number);
		String result = "";

		for (int i = 0; i < temp.length(); i++) {
			if (i != 0 && i % 3 == 0) {
				result = "," + result;
			}
			result = temp.charAt(temp.length() - 1 - i) + result;
		}
		return result;
	}

}
