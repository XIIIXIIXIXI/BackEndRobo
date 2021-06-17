package com.example.demo.model;
import java.util.UUID;
//this class is to give a user an unique ID
//this class is taken from the internet https://stackoverflow.com/questions/2178992/how-to-generate-unique-id-in-java-integer

public class IdGenerator {
    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }
}
