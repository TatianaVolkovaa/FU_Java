package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private final String name;
    private final String sex;
    private final int age;
    private static HashMap<Long,User> users = new HashMap<Long,User>();
    private static long countId = 0;

    User(String name, String sex, int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    public static List<User> getUsers(){
        try{
            return new ArrayList<User>(users.values());
        }
        catch(NullPointerException e){
            return new ArrayList<User>();
        }


    }
    public static List<User> getUsersBySex(String sex){
        List<User> sortedUsers =new ArrayList<>();
        for(Map.Entry<Long,User> entry : users.entrySet()){
            if(entry.getValue().sex.equals(sex)){
                sortedUsers.add(entry.getValue());
            }
        }
        return sortedUsers;
    }
    public static int  getCount(){
        return users.size();
    }

    public  static int getCountBySex(String sex){
        return  getUsersBySex(sex).size();
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || getClass()  != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && name.equals(user.name) && sex.equals(user.sex);
    }
    private boolean hasUser(){
        for(User user: users.values()){
            if(user.equals(this)){
                return true;
            }
        }
        return false;
    }
    public static void addUser(User user){
        countId++;
        users.put(countId,user);
    }


}
