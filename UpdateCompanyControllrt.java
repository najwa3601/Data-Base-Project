package DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateCompanyControllrt  {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField c_id;

    @FXML
    private TextField c_name;

    @FXML
    private TextField c_phone;

    @FXML
    private TextField type;

    @FXML
    void Back2(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("sample2.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.setTitle("Company Interface");
        this.stage.setResizable(false);
        this.stage.show();

    }
    @FXML
    void Clear2() {
        c_id.setText(null);
        c_name.setText(null);
        c_phone.setText(null);
        type.setText(null);
    }
    void Done2(ActionEvent event)throws SQLException, ClassNotFoundException {

        String id = c_id.getText();

        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill the Company-id text field!!");
            alert.showAndWait();
        } else {

            Connector.a.connectDB();
            Statement s = Connector.a.connectDB().createStatement();
            ResultSet r = s.executeQuery("Select Company_ID from Company where Company_ID = " + id);
            int cnt = 0;
            while (r.next()) {
                cnt++;
            }
            if (cnt == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("The Company ID doesn't exist");
                alert.showAndWait();
                return;
            }

            // it will reach this line when the c_ID text field is full, and Bid exists
            if(!(c_name.getText().isEmpty())){
                String name = c_name.getText();
                String query = "UPDATE Company SET Company_name = '" + name + "' WHERE Company_ID='" + id + "'";
                Connector.a.connectDB();
                Connector.a.connectDB().createStatement();
                Connector.a.connectDB().createStatement().executeUpdate(query);
                Connector.a.connectDB().close();
                Connector.a.connectDB().createStatement().close();
            }
            if(!(c_phone.getText().isEmpty())){
                String name1 = c_phone.getText();
                String query = "UPDATE Company SET Company_phone = '" + name1 + "' WHERE Company_ID='" + id + "'";
                Connector.a.connectDB();
                Connector.a.connectDB().createStatement();
                Connector.a.connectDB().createStatement().executeUpdate(query);
                Connector.a.connectDB().close();
                Connector.a.connectDB().createStatement().close();
            }
            if(!(type.getText().isEmpty())){
                String name2 = type.getText();
                String query = "UPDATE Company SET Typesof_product = '" + name2 + "' WHERE Company_ID='" + id + "'";
                Connector.a.connectDB();
                Connector.a.connectDB().createStatement();
                Connector.a.connectDB().createStatement().executeUpdate(query);
                Connector.a.connectDB().close();
                Connector.a.connectDB().createStatement().close();
            }
            }
        Clear2();
        }

}














