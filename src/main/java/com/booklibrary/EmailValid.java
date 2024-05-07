package com.booklibrary;
import java.util.regex.Pattern;

public class EmailValid
{
	/**
	 * @param email
	 * @return
	 */
	public boolean isValid(String email)
	{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
							"[a-zA-Z0-9_+&*-]+)*@" +
							"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
							"A-Z]{2,7}$";
							
		Pattern pat = Pattern.compile(emailRegex);
        if(pat.matcher(email).matches()){
            return true;  
        }
        return false;
        
	
	}
}
