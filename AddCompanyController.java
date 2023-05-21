package DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class AddCompanyController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField C_ID;

    @FXML
    private TextField C_name;

    @FXML
    private TextField C_phone;

    @FXML
    private Label Company_ID;

    @FXML
    private Label Company_name;

    @FXML
    private Label Company_phone;


    @FXML
    private TextField product;


    @FXML
    void Back(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("sample2.fxml"));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.setTitle("Company Interface");
        this.stage.setResizable(false);
        this.stage.show(); // when user clicks on button back, then the interface named Company Interface will appear


    }

    @FXML
    void Done(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        //an error message when one or more text field are empty
        if(this.C_ID.getText().isEmpty() || this.C_phone.getText().isEmpty() || this.C_name.getText().isEmpty() || this.product.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(" Empty Field !!, Please fill all required fields");
            alert.setContentText(" Try again ");
            alert.showAndWait();
            return;
        } else {
            String ID, phone, name,bbl;
            ID= C_ID.getText();
            phone = C_phone.getText();
            name = C_name.getText();
            bbl= product.getText();

            String string = "insert into Company values(ID + phone + name + + bbl + )";
            Connector.a.connectDB();
            Statement s = Connector.a.connectDB().createStatement();

            ResultSet r = s.executeQuery("Select Company_ID from Product where Company_ID = " + ID);
            int cnt = 0;
            while (r.next()) {
                cnt++;
            }
            if (cnt == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("The Company_ID doesn't exist");
                alert.showAndWait();
                return;
            }

            r = s.executeQuery("select Company_ID from Company where Company_ID = " + ID);
            cnt = 0;
            while (r.next()) {
                cnt++;
            }
            if (cnt != 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("The Company_ID already exists");
                alert.showAndWait();
                return;
            }
            // It will reach this line iff all the inserted data is correct
            System.out.println(string);
            s.executeUpdate(string);
            Connector.a.connectDB().close();
            C_ID.setText(null);
            C_name.setText(null);
            C_phone.setText(null);
            bbl.isEmpty();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
