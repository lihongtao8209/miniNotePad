package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

import debug.Debug;
import regular.SimpleRegularExpress;

public class Configure {
	
	private String content="";
	
	public Configure() {
		
	}

	private void read() throws IOException {
		String oneLineContent="";
		FileReader fileRead = new FileReader(".\\src\\main\\java\\file\\configure.txt");
		BufferedReader bufferReader = new BufferedReader(fileRead);
		while ((oneLineContent = bufferReader.readLine()) != null) {
			content = content + oneLineContent + "\n";
		}
	}
	
	@Test
	public void filter() {
		SimpleRegularExpress simpleRegularExpress=new SimpleRegularExpress();
		String inputStr="[a ]";
		inputStr=simpleRegularExpress.trim(inputStr);
		Debug.debug(inputStr);
	}
	
	public String getContent() {
		return content;
	}
	
	@Test
	public void Test() {
		Configure configure = new Configure();
		try {
			configure.read();
			Debug.debug(configure.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
