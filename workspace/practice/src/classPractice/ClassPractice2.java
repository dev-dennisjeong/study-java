package classPractice;

public class ClassPractice2 {
	
//  최대값, 최소값 구하기
  void getMaxMin(int[] arData, Result result) {
	  result.max = arData[0];
	  result.min = arData[1];
	  
	  for (int i = 0; i < arData.length; i++) {
		if(result.max < arData[i]) {
			result.max = arData[i];
		}
		if(result.min > arData[i]) {
			result.min = arData[i];
		}
	}
  }
	
//  학생번호, 국어점수, 영어점수, 수학점수를 입력받은 후 총 합과 평균 구하기
  void getTotalAvg(Student student) {
	  student.total = student.kor + student.math + student.eng;
	  student.avg = student.total / 3.0;
  }
  

	public static void main(String[] args) {
		ClassPractice2 cp2 = new ClassPractice2();
		Result result = new Result();
		Student student = new Student(1, 50, 60, 70);
		cp2.getTotalAvg(student);
		System.out.println(student.total);
		System.out.println(student.avg);
		
		int[] arData = {9, 14, 6, 7, 33};
		cp2.getMaxMin(arData, result);
		System.out.println(result.max);
		System.out.println(result.min);
	}
}
