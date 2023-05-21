package DataBase;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.chart.AreaChart;

public class EmployeeSalaryController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label from;

    @FXML
    private DatePicker fromdate;

    @FXML
    private Label to;

    @FXML
    private DatePicker todate;

    @FXML
    private Label txt3;

    @FXML
    private Label txt4;


    @FXML
    void btnback(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("QueriesInterface.fxml"));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.setTitle("Shini Extra");
        this.stage.setResizable(false);
        this.stage.show(); // when user clicks on button back, then the Queries Interface will appear


    }

    public void initialize(java.net.URL location, ResourceBundle resources) {
        XYChart.Series series= new XYChart.Series();
        series.getData().add(new XYChart.Data(1,23));

        try {
            String fDate = fromdate.getValue().toString().trim();
            String tDate = todate.getValue().toString().trim();
            int i = 0;
            Connector.a.connectDB();
            PreparedStatement st = Connector.a.connectDB().prepareStatement("Select Sum(Salary) from Employee e " +
                    "where e.Monthdate>=tDate AND e.Monthdate<=fDate ");
            ResultSet r = st.executeQuery();
            if (r.next()) {
                i = r.getInt(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

