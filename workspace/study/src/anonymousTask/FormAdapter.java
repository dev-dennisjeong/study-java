package anonymousTask;

//강제성을 없애주기 위한 추상 클래스
//잠실점은 판매방식이 따로 없으므로 FormAdapter로 넘겨준다.
public abstract class FormAdapter implements Form{

	@Override
	public void sell(String menu) {;}

}
