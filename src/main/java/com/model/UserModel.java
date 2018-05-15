
public class UserModel {

    private static String user_id;
    private static String user_pass;
    private static String user_firstname;
    /*public class user_id {
        public String getUser_id() {
            return user_id;
        }
        public void setUser_id(String user_id){
            UserModel.user_id = user_id;
        }

    }*/

    public void setUser_id(String user_id) {
        UserModel.user_id = user_id;
    }

    public void setUser_pass(String user_pass) {
        UserModel.user_pass = user_pass;
    }

    public void setUser_firstname(String user_firstname) {
        UserModel.user_firstname = user_firstname;
    }

   public String getUser_id() {
        return user_id;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public String getUser_firstname() {
        return user_firstname;
    }
    public void update() {
        String db_user_pass = getUser_pass();
        String db_user_firstname = getUser_firstname();
        DatabaseController.updateModel(db_user_pass, db_user_firstname);
    }

}

