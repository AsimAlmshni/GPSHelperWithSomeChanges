package com.najah.edu.DatABasE;

/**
 * Created by Asim Almshni on 23-Apr-17.
 */

public  class value {
    public static String e1;
    public static String p1;

    public void setemail(String email){
        e1 = email ;
    }

    public void setpass(String email){
        p1 = email ;
    }


    public String getemail(){
        return e1;
    }


    public String getpass(){
        return p1;
    }


}
