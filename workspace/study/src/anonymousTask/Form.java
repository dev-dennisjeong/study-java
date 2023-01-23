package anonymousTask;

//제출 양식
//모든 지점이 공유하는 틀
//작성은 지점들이 하기 때문에 form은 interface로 구현
public interface Form {
	public String[] getMenu();
	public void sell(String menu);
}
