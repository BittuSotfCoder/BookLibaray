package com.booklibrary;

public class GEN_OTP {
    public static String generateOTP(){
        int randamNo=(int) (Math.random()*9000)+1000;
        String Otp=String.valueOf(randamNo);
        return Otp;

    }
}
