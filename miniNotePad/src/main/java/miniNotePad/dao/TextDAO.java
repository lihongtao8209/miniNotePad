package miniNotePad.dao;

public interface TextDAO {
	void create(String file);
	String read(String file);
	void save(String file,String text);
}
