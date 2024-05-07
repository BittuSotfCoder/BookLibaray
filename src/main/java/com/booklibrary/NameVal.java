package com.booklibrary;

public class NameVal {
    public static boolean FirsNAME(String firstName){
            String fir="[A-Z][a-zA-Z]*";
            return firstName.matches(fir);
        }
        public static boolean LastNAME(String lastName){
        String fir="[A-Z][a-zA-Z]*";
        return lastName.matches(fir);

    }
}
