import java.util.ArrayList;
import java.util.List;

class StudentListModel {
    static ArrayList<String> studentList_id;

    static void setStudentList_id(ArrayList<String> studentList_id){
        StudentListModel.studentList_id = studentList_id;
    }

    static ArrayList<String> getStudentList_id(){
        return studentList_id;
    }

    void load(){
        DatabaseController.loadStudentListModel();
    }


}
