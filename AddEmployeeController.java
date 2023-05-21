package DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
public class AddEmployeeController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label Address;

    @FXML
    private Label Birth_date;

    @FXML
    private TextField E_Address;

    @FXML
    private Label E_ID;

    @FXML
    private Label E_name;

    @FXML
    private TextField Employee_ID;

    @FXML
    private TextField Employee_name;

    @FXML
    private TextField sallary;

    @FXML
    private TextField Job;

    @FXML
    private TextField Marital_status;

    @FXML
    private TextField branch_Id;

    @FXML
    private Label Month;

    @FXML
    private Label Phone;

    @FXML
    private Label Please_fill_all_required_fields;

    @FXML
    private Label Working_hours;

    @FXML
    private DatePicker birth_date;

    @FXML
    private Label job_title;

    @FXML
    private Label marital_status;

    @FXML
    private DatePicker month;

    @FXML
    private Label new_Employee;

    @FXML
    private TextField phone;
    @FXML
    private TextField pword;

    @FXML
    private TextField w_hours;
    @FXML
    void backk(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("sample1.fxml"));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.setTitle("Employee Interface");
        this.stage.setResizable(false);
        this.stage.show(); // when user clicks on button back, then the interface named Company Interface will appear

    }

    // Error when you forget  to fill one or more  required fields
    @FXML
    void Done(ActionEvent event) throws ClassNotFoundException, SQLException {
        //an error message when one or more text field are empty
        if (Employee_name.getText().isEmpty() ||E_Address.getText().isEmpty() ||Job.getText().isEmpty()
                ||sallary.getText().isEmpty() || Employee_ID.getText().isEmpty() || phone.getText().isEmpty() ||birth_date.getValue()==null ||
                 Marital_status.getText().isEmpty() || month.getValue()==null|| w_hours.getText().isEmpty()||
                 branch_Id.getText().isEmpty() || pword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(" Empty Field, Please fill all required fields");
            alert.showAndWait();
            return;
        } else {
            String employee_name1,e_Address,jobb,Hourly_rate1,employee_IDd,phone1,birth_datee,Marit_status,month1,hours1,pass1,bid;
            employee_name1= Employee_name .getText();
            e_Address= E_Address .getText();
            jobb= Job .getText();
            Hourly_rate1= sallary .getText();
            employee_IDd= Employee_ID .getText();
            phone1= phone .getText();
            birth_datee= birth_date.getValue().toString();
            Marit_status= Marital_status .getText();
            month1=month.getValue().toString();
            hours1=w_hours.getText();
            pass1=pword.getText();
            bid=branch_Id.getText();


            String string = "insert into Employee values(employee_IDd,employee_name1, e_Address ,jobb , hours1  ,phone1,birth_datee,  Marit_status,month1 ,Hourly_rate1,pass1,bid);";
            Connector.a.connectDB();
            Statement s = Connector.a.connectDB().createStatement();

            ResultSet r = s.executeQuery("Select Branch_ID from Branch where Branch_ID = " + bid);
            int cnt = 0;
            while (r.next()) {
                cnt++;
            }
            if (cnt == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("The Branch_ID doesn't exist");
                alert.showAndWait();
                return;
            }

           r = s.executeQuery("Select E_ID from Cashier where E_ID  = " + employee_IDd);
            cnt = 0;
            while (r.next()) {
                cnt++;
            }
            if (cnt == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("The Employee_ID doesn't exist");
                alert.showAndWait();
                return;
            }

        r = s.executeQuery("Select E_ID from Employee where E_ID  = " + employee_IDd);
        cnt = 0;
        while (r.next()) {
            cnt++;
        }
        if (cnt != 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("The Employee_ID already exists");
            alert.showAndWait();
            return;
        }
            System.out.println(string);
            s.executeUpdate(string);
            Connector.a.connectDB().close();
            //*** Empty the text field
            Employee_name.clear();
            E_Address.clear();
            Job.clear();
            Employee_ID.clear();
            phone.clear();
            birth_date.getEditor().clear();
            month.getEditor().clear();
            Marital_status.clear();
            w_hours.clear();
            sallary.clear();
            pword.clear();
            branch_Id.clear();
        }
        }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


