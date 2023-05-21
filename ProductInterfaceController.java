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
public class ProductInterfaceController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Product> Product;
    @FXML
    private TableColumn<Product, Integer> Company_ID;

    @FXML
    private TableColumn<Product, String> Expiry_Date;

    @FXML
    private TableColumn<Product, Integer> Market_Price;

    @FXML
    private TableColumn<Product, Integer> Net_profit;

    @FXML
    private TableColumn<Product, Integer> Product_ID;

    @FXML
    private TableColumn<Product, Integer> Quantity;

    @FXML
    private Label Searchbox;

    @FXML
    private TableColumn<Product, Integer> cost_price;

    @FXML
    private TableColumn<Product, String> name;

    @FXML
    private TableColumn<Product, String> type;
    @FXML
    private TextField txt90;


    ObservableList<Product> list = FXCollections.observableArrayList();

    @FXML
    public void readData(){
        try {
            Connector.a.connectDB();
            Statement statement = Connector.a.connectDB().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Product");
            while(resultSet.next()){ // bnlf satr satr 3al data
                list.add(new Product(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Integer.parseInt(resultSet.getString(4)),
                        Integer.parseInt(resultSet.getString(5)),
                        Integer.parseInt(resultSet.getString(6)),
                        resultSet.getString(7),
                        Integer.parseInt(resultSet.getString(8))
                ));
            }
            Connector.a.connectDB().close(); //always close connection
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Back(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("FirstPage.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.setTitle("Shini Extra");
        this.stage.setResizable(false);
        this.stage.show(); // when user clicks on button back, then the Main Page Interface will appear
    }

    @FXML
    void Delete(ActionEvent event) throws IOException {
        ObservableList <Product> selectedRow = Product.getSelectionModel().getSelectedItems(); // To identify a specific Product
        ArrayList<Product> row = new ArrayList<>(selectedRow);
        if (row.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("A specific line must be selected to be deleted!");
            alert.showAndWait();
            return;
        }
        try {
            Connector.a.connectDB();
            Connector.a.ExecuteStatement("Delete from Product where Product_ID = " + row.get(0).getProduct_ID() + ";");
            Connector.a.connectDB().close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Product stands for the name of the table view of the Product in scene builder
        int selectedRowID = Product.getSelectionModel().getSelectedIndex();
        Product.getItems().remove(selectedRowID);

    }

    @FXML
    void Insert(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(this.getClass().getResource("ProductInsert.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.stage.setScene(new Scene(this.root));
        this.stage.setTitle("Insert Product");
        this.stage.setResizable(false);
        this.stage.show();
    }
    @Override
    public void initialize(java.net.URL location, ResourceBundle resourceBundle) {
        readData();
        Product_ID.setCellValueFactory(new PropertyValueFactory<Product,Integer>("Product_ID"));
        name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<Product,String>("type"));
        cost_price.setCellValueFactory(new PropertyValueFactory<Product,Integer>("cost_price"));
        Market_Price.setCellValueFactory(new PropertyValueFactory<Product,Integer>("Market_Price"));
        Net_profit.setCellValueFactory(new PropertyValueFactory<Product,Integer>("Net_profit"));
        Expiry_Date.setCellValueFactory(new PropertyValueFactory<Product,String>("Expiry_Date"));
        Company_ID.setCellValueFactory(new PropertyValueFactory<Product,Integer>("Company_ID"));
        Product.setItems(list);
        Searchbox(Product,txt90,list);
    }

    public void Searchbox(TableView<Product> Product, TextField txt90, ObservableList<Product> list) {
        //wrap the observationalList "list" in a FilteredList (initially display all data)
        FilteredList<Product> filterList = new FilteredList<>(list, b -> true);
        //set the filter predict whenever the text field changes
        txt90.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList.setPredicate(product -> {


                //if the txt90 is empty, display all Products
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(product.getProduct_ID()).indexOf(lowerCaseFilter) > -1) {
                    return true;

                } else if (product.getName().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;}
                else if (product.getType().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;
                }else if (String.valueOf(product.getCost_price()).indexOf(lowerCaseFilter) > -1) {
                    return true;
                }else if (String.valueOf(product.getMarket_Price()).indexOf(lowerCaseFilter) > -1) {
                    return true;
                }else if (String.valueOf(product.getNet_profit()).indexOf(lowerCaseFilter) > -1) {
                    return true;
                }else if (product.getExpiry_Date().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                        return true;
                }else if (String.valueOf(product.getCompany_ID()).indexOf(lowerCaseFilter) > -1) {
                        return true;

                }else
                    return false;// no match found

            });
        });

        SortedList<Product> sortedData = new SortedList<>(filterList);

        sortedData.comparatorProperty().bind(Product.comparatorProperty());

        //Apply filtered and sorted data to the table View.setItems(sortedData);

        Product.setItems(sortedData);
    }


}
