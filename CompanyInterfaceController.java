package DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class CompanyInterfaceController implements Initializable {

    @FXML
    private TableView<Company> CompanyTable;

    @FXML
    private TableColumn<Company, Integer> Company_ID;

    @FXML
    private TableColumn<Company, String> Company_name;

    @FXML
    private TableColumn<Company, String> Company_phone;

    @FXML
    private TableColumn<Company, String> Typesof_product;
    @FXML
    private Label search2;

    @FXML
    private TextField txt2;
    private Stage stage;
    private Scene scene;
    private Parent root;
    ObservableList<Company> list = FXCollections.observableArrayList();
    public CompanyInterfaceController(){
    }


    @FXML
    void Back(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("FirstPage.fxml"));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.setTitle(" Al Shini Supermarket");
        this.stage.setResizable(false);
        this.stage.show(); // when user clicks on button back, then the Main Page Interface will appear
    }

    @FXML
    void Delete(ActionEvent event) {
        ObservableList<Company> selectedRow = (ObservableList<DataBase.Company>) CompanyTable.getSelectionModel().getSelectedItems(); // To identify a specific customer
        ArrayList<Company> row = new ArrayList<>(selectedRow);
        if (row.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setContentText("Please select the row to be deleted!");
            alert.showAndWait();
            return;
        }
        try {
            Connector.a.connectDB();
            Connector.a.ExecuteStatement("Delete from Company where Company_ID = " + row.get(0).getCompany_ID() + ";");
            Connector.a.connectDB().close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int selectedRowID = CompanyTable.getSelectionModel().getSelectedIndex();
        CompanyTable.getItems().remove(selectedRowID);
    }

    @FXML
    void Update(ActionEvent event)throws IOException{
        this.root = FXMLLoader.load(this.getClass().getResource("UpdateCompany.fxml"));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(new Scene(this.root));
        this.stage.setTitle("Update Company");
        this.stage.setResizable(false);
        this.stage.show();
    }
    @FXML
    void Add(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("AddCompany.fxml"));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(new Scene(this.root));
        this.stage.setTitle("Insert Company");
        this.stage.setResizable(false);
        this.stage.show();
    }


    public void Search(TableView<Company> CompanyTable, TextField txt2, ObservableList<Company> list) {
        //wrap the observationalList "list" in a FilteredList (initially display all data)
        FilteredList<Company> filterList = new FilteredList<>(list, b -> true);
        //set the filter predict whenever the text field changes
        txt2.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList.setPredicate(company -> {

                //if the txt2 is empty, display all Companies
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(company.getCompany_ID()).indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (company.getCompany_phone().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;

                } else if (company.getCompany_name().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;

                } else if (company.getTypesof_product().toLowerCase().indexOf(lowerCaseFilter) > -1){
                    return true;
                }else
                    return false;// no match found
            });
        });

        SortedList<Company> sortedData = new SortedList<>(filterList);

        //Bind sorted result with table view "patientTable"
        sortedData.comparatorProperty().bind(CompanyTable.comparatorProperty());

        //Apply filtered and sorted data to the table View.setItems(sortedData);

        CompanyTable.setItems(sortedData);
    }

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        readData();
        Company_ID.setCellValueFactory(new PropertyValueFactory<Company, Integer>("Company_ID"));
        Company_phone.setCellValueFactory(new PropertyValueFactory<Company, String>("Company_phone"));
        Company_name.setCellValueFactory(new PropertyValueFactory<Company, String>("Company_name"));
        Typesof_product.setCellValueFactory(new PropertyValueFactory<Company, String>("Typesof_product"));
        CompanyTable.setItems(list);
        Search(CompanyTable,txt2,list);

    }
    public void readData() {
        try {
            Connector.a.connectDB();
            Statement statement = Connector.a.connectDB().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Company");
            while (resultSet.next()) {
                list.add(new Company(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));

            }
            Connector.a.connectDB().close(); //always close connection
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}



