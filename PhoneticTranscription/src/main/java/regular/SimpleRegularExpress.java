package regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import debug.Debug;

public class SimpleRegularExpress {

	public SimpleRegularExpress() {
		
	}	
	
   public String trim(String inputStr) {
		inputStr=inputStr.replaceAll("[\\s\r\n]", "");
		return inputStr;
	}
	
	public void showMatchGroup(String regularExpress,String input)
	{
		Pattern p = Pattern.compile(regularExpress);
		Matcher m = p.matcher(input);
		while(m.find() ) {
			Debug.debug(m.group());
		}
	}
	
	public void showMatchGroupInfo(String regularExpress,String input)
	{
		Pattern p = Pattern.compile(regularExpress);
		Matcher m = p.matcher(input);
		while(m.find() ) {
			Debug.debugInfo(m.group());
		}
	}
	
	@Test
	public void test() {
		SimpleRegularExpress simpleRegularExpress = new SimpleRegularExpress();
		String inputStr="\n\n123 \n abc \r test \n \r\r ABC \r\n";
		inputStr=simpleRegularExpress.trim(inputStr);
		Debug.debugInfo(inputStr);
	}
}
