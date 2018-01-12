package test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestSplit {
	private String pronunciation = "{\r\n" + 
			"	a:  [ {a,0},\r\n" + 
			"	    {a,1},\r\n" + 
			"		{a,2},\r\n" + 
			"		{a,3},\r\n" + 
			"		{a,4} \r\n" + 
			"		]\r\n" + 
			"}";
	//声母
	//private  String initial_consonant_pattern="";
	//韵母 syllablea vowel
	//音调 tone
	//笔画
	//五笔
	public TestSplit() {
		//initial_consonant_pattern="";
	}
	/**
	 *  处理花括号
	 */
	@Test
	public void test()	
	{
		//去空格
		pronunciation=pronunciation.replaceAll("[\\s]", "");
		String pattern ="[a-zA-Z]:\\[[\\S\\s]*\\]";
		showMatcher(pattern,pronunciation);
		//零宽度正预测先行断言,获取:前面的内容
		String initial_consonant_pattern = "[a-zA-Z](?=:)";
		showMatcher(initial_consonant_pattern,pronunciation);
		//
		//匹配:[   {a,0},   {a,  1}  ] 结果:[{a,0},{a,1}]
	    pattern = "\\[[\\s]*(\\{.,.\\}\\,*)*[\\s]*\\]";
		showMatcher(pattern,pronunciation);
		//匹配:{a,1}
		pattern = "\\{.,.\\}";
		showMatcher(pattern,pronunciation);
	}
	private void showMatcher(String pattern,String input) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(pronunciation);
		while(m.find() ) {
			debug(m.group());
		}
	}
	
	private void debug(String info) {
		System.out.println(info);
	}
}
