public class UserModel {

    private String user_id;
    private String user_pass;
    private String user_firstname;

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }




    /*public UserModel(String user_id, String user_pass, String user_firstname) {
        this.user_id = user_id;
        this.user_pass = user_pass;
        this.user_firstname = user_firstname;

        System.out.println(user_id);
        System.out.println(user_pass);
        System.out.println(user_firstname);

    }*/

}
