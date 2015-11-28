package cd.synah.androidhack.model;

/**
 * Created by Michelo on 27/11/15.
 */
public class User {

    private String username;
    private String password;
    private String title;


    public User(){
        //Null constructeur
    }

    public User(String usern, String pwd, String titre){
        this.username=usern;
        this.password=pwd;
        this.title=titre;
    }

    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username=username;}

    public String getPassword(){return this.password;}
    public void setPassword(String username){this.password=password;}


    public String getTitle(){return this.title;}
    public void setTitle(String title){this.title=title;}


}
