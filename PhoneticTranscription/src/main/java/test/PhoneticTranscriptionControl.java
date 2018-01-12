package test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import debug.Debug;
import macro.PhoneticTranscriptionMacro;
public class PhoneticTranscriptionControl {
	//声母数组
	private String initial_consonant_patterns[];
	//韵母
	private String syllablea_vowels[];
	
	public PhoneticTranscriptionControl() {
		initial_consonant_patterns = PhoneticTranscriptionMacro.initial_consonant_pattern.split(",");
		syllablea_vowels=PhoneticTranscriptionMacro.syllablea_vowel.split(",");
	}
	private void initial_consonant_pattern_analyse() {
		//判断是否为双音节
		
	}
	public String[] getInitial_consonant_patterns() {
		return initial_consonant_patterns;
	}

	public String[] getSyllablea_vowels() {
		return syllablea_vowels;
	}

	/**
	 *  处理双音节
	 */
	@Test
	public void test()	
	{   
		String pronunciation="zhshchshzhch";
		String pattern ="^[(zh)(ch)(sh)]{2}[^(zh)(ch)(sh)]*";
		showMatcher(pattern,pronunciation);
	}
	
	@Test
	public void ObjectTest() {
		PhoneticTranscriptionControl phoneticTranscriptionControl = new PhoneticTranscriptionControl();
		Debug.debug(phoneticTranscriptionControl.getInitial_consonant_patterns());
		Debug.debug(phoneticTranscriptionControl.getSyllablea_vowels());
	}
	
	private void showMatcher(String pattern,String input) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(input);
		while(m.find() ) {
			Debug.debug(m.group());
		}
	}
	

}
