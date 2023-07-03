package packageEx;
import java.util.Scanner;

public class Admin {
    private int id;
    private String pw;
    
    public void setAdmin(int id, String pw) {
        this.id = id;
        this.pw = pw;
    }
    
    public boolean checkAdmin(int inputId, String inputPw) {
        return id == inputId && pw.equals(inputPw);
    }

	
	}

    