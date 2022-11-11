package com.pje.sansomatchingwalkingmateapi.lib;

public class CommonCheck {
    public static boolean checkPhone(String phone) {
        String pattern = "^[0-9]{3}+-[0-9]{4}+-[0-9]{4}$";
        return phone.matches(pattern);
    }

    public static boolean checkTel(String tel) {
        String pattern = "^[0-9]{4}+-[0-9]{4}$|^[0-9]{3}+-[0-9]{4}+-[0-9]{4}$|^[0-9]{3}+-[0-9]{3}+-[0-9]{4}$|^[0-9]{2}+-[0-9]{3}+-[0-9]{4}$|^[0-9]{2}+-[0-9]{4}+-[0-9]{4}$";
        return tel.matches(pattern);
    }

    public static boolean checkUsername(String username) {
        String pattern = "^[a-zA-Z]{1}[a-zA-Z0-9]{4,19}$";
        return username.matches(pattern);
    }
}
