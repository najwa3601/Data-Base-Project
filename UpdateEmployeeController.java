package DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateEmployeeController  {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField E_ID;

    @FXML
    private TextField E_address;

    @FXML
    private TextField Job;

    @FXML
    private TextField Marital_status;

    @FXML
    private ChoiceBox<String> choose1;

    @FXML
    private DatePicker date1;

    @FXML
    private TextField employee_name;

    @FXML
    private TextField salary;

    @FXML
    private TextField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField working_hours;

    @FXML
    private TextField bran_id;

    @FXML


    static ObservableList<String> listDate = FXCollections.observableArrayList(
            "Birth_date","Monthdate");

    @FXML
    void Back1(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("sample1.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.setTitle("Employee Interface");
        this.stage.setResizable(false);
        this.stage.show();
    }
    @FXML
    void Clear1() {
        E_ID.setText(null);
        E_address.setText(null);
        date1.getEditor().clear();
        Job.setText(null);
        Marital_status.setText(null);
        employee_name.setText(null);
        salary.setText(null);
        password.setText(null);
        phone.setText(null);
        working_hours.setText(null);
        bran_id.setText(null);
        choose1.setValue(null);
    }


    @FXML
    void Done1(ActionEvent event)throws SQLException, ClassNotFoundException {

            String id = E_ID.getText();

            if (id.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill the E-id text field!!");
                alert.showAndWait();
            } else {

                Connector.a.connectDB();
                Statement s = Connector.a.connectDB().createStatement();
                ResultSet r = s.executeQuery("Select E_ID from Employee where E_ID = " + id);
                int cnt = 0;
                while (r.next()) {
                    cnt++;
                }
                if (cnt == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("The Employee ID doesn't exist");
                    alert.showAndWait();
                    return;
                }
                if(!(Job.getText().isEmpty())){
                    String name4 = Job.getText();
                    String query = "UPDATE Employee SET job_title = '" + name4 + "' WHERE   E_ID='" + id + "'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();
                }
                if(!(E_address.getText().isEmpty())){
                    String name11 = E_address.getText();
                    String query = "UPDATE Employee SET Address = '" + name11 + "' WHERE   E_ID='" + id + "'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();
                }
                if(!(Marital_status.getText().isEmpty())){
                    String name5 = Marital_status.getText();
                    String query = "UPDATE Employee SET marital_status = '" + name5 + "' WHERE E_ID='" + id + "'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();
                }
                if(!(employee_name.getText().isEmpty())){
                    String name6 = employee_name.getText();
                    String query = "UPDATE Employee SET E_name = '" + name6 + "' WHERE E_ID='" + id + "'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();
                }
                if(!(salary.getText().isEmpty())){
                    String name7 = salary.getText();
                    String query = "UPDATE Employee SET Salary = '" + name7 + "' WHERE E_ID='" + id + "'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();
                }
                if(!(password.getText().isEmpty())){
                    String name8 = password.getText();
                    String query = "UPDATE Employee  SET E_Password = '" + name8 + "' WHERE E_ID='" + id + "'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();
                }
                //phone
                if(!(phone.getText().isEmpty())){
                    String name9 = phone.getText();
                    String query = "UPDATE Employee SET Phone = '" + name9 + "' WHERE CE_ID='" + id + "'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();
                }

                if(!(working_hours.getText().isEmpty())){
                    String name10 = working_hours.getText();
                    String query = "UPDATE Employee SET Working_hours = '" + name10 + "' WHERE E_ID='" + id + "'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();
                }
                if(!(bran_id.getText().isEmpty())){
                    String name10 = bran_id.getText();
                    String query = "UPDATE Employee SET Branch_ID = '" + name10 + "' WHERE E_ID='" + id + "'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();
                }
                if(!(String.valueOf(choose1.getValue())== "Birth_date")){
                    String bDate = date1.getValue().toString().trim();
                    String query = "UPDATE Employee SET Birth_date = '" + bDate  + "' WHERE E_ID='"+id  +"'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();

                }

                if(!(String.valueOf(choose1.getValue())== "Monthdate")){
                    String mDate = date1.getValue().toString().trim();
                    String query = "UPDATE Employee SET Monthdate = '" + mDate + "' WHERE E_ID='"+id +"'";
                    Connector.a.connectDB();
                    Connector.a.connectDB().createStatement();
                    Connector.a.connectDB().createStatement().executeUpdate(query);
                    Connector.a.connectDB().close();
                    Connector.a.connectDB().createStatement().close();

                }





                }
        Clear1();



            }


    }




