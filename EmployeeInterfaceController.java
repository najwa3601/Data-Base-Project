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
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeInterfaceController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    ObservableList<Employee> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Employee, String> Address;

    @FXML
    private TableColumn<Employee, String> Birth_date;
    @FXML
    private TableColumn<Employee, Integer> E_ID;
    @FXML
    private TableColumn<Employee, Integer> Working_hours;
    @FXML
    private TableColumn<Employee, String> Monthdate;

    @FXML
    private TableColumn<Employee, String> E_name;

    @FXML
    private TableView<Employee> EmployeeTable;
    @FXML
    private TableColumn<Employee, String> Phone;
    @FXML
    private TableColumn<Employee, Integer> Salary;
    @FXML
    private TableColumn<Employee, Integer> Branch_ID;
    @FXML
    private TableColumn<Employee, String> job_title;

    @FXML
    private TableColumn<Employee, String> marital_status;
    @FXML
    private TableColumn<Employee, String> Password;
    @FXML
    private Label search;
    @FXML
    private TextField txt3;

    public EmployeeInterfaceController(){
    }
    @FXML
    void back(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("FirstPage.fxml"));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.setTitle(" Al Shini Supermarket");
        this.stage.setResizable(false);
        this.stage.show(); // when user clicks on button back, then the Main Page Interface will appear
    }

    @FXML
    void delete(ActionEvent event) {
        ObservableList<Employee> selectedRow = (ObservableList<DataBase.Employee>) EmployeeTable.getSelectionModel().getSelectedItems(); // To identify a specific customer
        ArrayList<Employee> row = new ArrayList<>(selectedRow);
        if (row.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setContentText("Please select the row to be deleted!");
            alert.showAndWait();
            return;
        }
        try {
            Connector.a.connectDB();
            Connector.a.ExecuteStatement("Delete from Employee where E_ID = " + row.get(0).getE_ID() + ";");
            Connector.a.connectDB().close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int selectedRowID = EmployeeTable.getSelectionModel().getSelectedIndex();
        EmployeeTable.getItems().remove(selectedRowID);

    }

    @FXML
    void Edit(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("UpdateEmployee.fxml"));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(new Scene(this.root));
        this.stage.setTitle("Update Employee");
        this.stage.setResizable(false);
        this.stage.show();

    }

    @FXML
    void Insert(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("AddEmployee.fxml"));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(new Scene(this.root));
        this.stage.setTitle("Insert Employee");
        this.stage.setResizable(false);
        this.stage.show();
    }
    @FXML
    void Calculate1(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("EmployeeSalary.fxml"));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(new Scene(this.root));
        this.stage.setTitle("Calculate  Employee Salary");
        this.stage.setResizable(false);
        this.stage.show();
    }

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        readData();
        E_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("E_name"));
        Address.setCellValueFactory(new PropertyValueFactory<Employee, String>("Address"));
        job_title.setCellValueFactory(new PropertyValueFactory<Employee, String>("job_title"));
        Working_hours.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Working_hours"));
        E_ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("E_ID"));
        Phone.setCellValueFactory(new PropertyValueFactory<Employee, String>("Phone"));
        Birth_date.setCellValueFactory(new PropertyValueFactory<Employee, String>("Birth_date"));
        marital_status.setCellValueFactory(new PropertyValueFactory<Employee, String>("marital_status"));
        Monthdate.setCellValueFactory(new PropertyValueFactory<Employee, String>("Monthdate"));
        Salary.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Salary"));
        Password.setCellValueFactory(new PropertyValueFactory<Employee, String>("Password"));
        Branch_ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Branch_ID"));
        EmployeeTable.setItems(list);
        search(EmployeeTable, txt3, list);

    }
    public void search(TableView<Employee> EmployeeTable, TextField txt3, ObservableList<Employee> list) {
        //wrap the observationalList "list" in a FilteredList (initially display all data)
        FilteredList<Employee> filterList = new FilteredList<>(list, b -> true);
        //set the filter predict whenever the text field changes
        txt3.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList.setPredicate(employee -> {


                //if the txt3 is empty, display all employees
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(employee.getE_ID()).indexOf(lowerCaseFilter) > -1) {
                    return true; //means we find match in patient ID

                } else if (employee.getAddress().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (employee.getJob_title().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (employee.getBirth_date().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (employee.getPhone().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (employee.getMonthdate().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (String.valueOf(employee.getWorking_hours()).indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (String.valueOf(employee.getE_name()).indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (String.valueOf(employee.getPassword()).indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (String.valueOf(employee.getBranch_ID()).indexOf(lowerCaseFilter) > -1) {
                    return true;
                } else if (String.valueOf(employee.getMarital_status()).indexOf(lowerCaseFilter) > -1) {
                    return true;
                }else
                    return false;// no match found

            });
        });

        SortedList<Employee> sortedData = new SortedList<>(filterList);

        //Bind sorted result with table view "Employee"
        sortedData.comparatorProperty().bind(EmployeeTable.comparatorProperty());

        //Apply filtered and sorted data to the table View.setItems(sortedData);

        EmployeeTable.setItems(sortedData);
    }


    public void readData() {
        try {
            Connector.a.connectDB();
            Statement statement = Connector.a.connectDB().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Employee");
            while (resultSet.next()) {
                list.add(new Employee(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        Integer.parseInt(resultSet.getString(5)),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        Integer.parseInt(resultSet.getString(10)),
                        resultSet.getString(11),
                        Integer.parseInt(resultSet.getString(12))

                ));
            }
            Connector.a.connectDB().close(); //always close connection
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }





}




