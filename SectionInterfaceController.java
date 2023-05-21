package DataBase;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SectionInterfaceController implements Initializable {
    @FXML
    private TableView<Section> Section;
    @FXML
    private Label search80;
    @FXML
    private TextField txt80;
    @FXML
    private TableColumn<Section, Integer> manager_id;

    @FXML
    private TableColumn<Section, String> name;

    @FXML
    private TableColumn<Section, Integer> sid;
    private Stage stage;
    private Scene scene;
    private Parent root;
    ObservableList<Section> list = FXCollections.observableArrayList();

    @FXML
    public void readData(){
        try {
            Connector.a.connectDB();
            Statement statement = Connector.a.connectDB().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Section");
            while(resultSet.next()){
                list.add(new Section(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3))
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
    void Delete_Recording(ActionEvent event) throws IOException {
        ObservableList <Section> selectedRow = Section.getSelectionModel().getSelectedItems(); // To identify a specific Section
        ArrayList<Section> row = new ArrayList<>(selectedRow);
        if (row.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("A specific line must be selected to be deleted!");
            alert.showAndWait();
            return;
        }
        try {
            Connector.a.connectDB();
            Connector.a.ExecuteStatement("Delete from Section where sid = " + row.get(0).getSid() + ";");
            Connector.a.connectDB().close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Section stands for the name of the table view of the Section in scene builder
        int selectedRowID = Section.getSelectionModel().getSelectedIndex();
        Section.getItems().remove(selectedRowID);
    }


    @FXML
    void Insert(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("SectionInsert.fxml")));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.stage.setScene(new Scene(this.root));
        this.stage.setTitle("Insert Section");
        this.stage.setResizable(false);
        this.stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readData();
        name.setCellValueFactory(new PropertyValueFactory<Section,String>("name"));
        manager_id.setCellValueFactory(new PropertyValueFactory<Section,Integer>("manager_id"));
        sid.setCellValueFactory(new PropertyValueFactory<Section,Integer>("sid"));
        Section.setItems(list);
        search80(Section,txt80,list);

    }
    public void search80(TableView<Section> Section, TextField txt80, ObservableList<Section> list) {
        //wrap the observationalList "list" in a FilteredList (initially display all data)
        FilteredList<Section> filterList = new FilteredList<>(list, b -> true);
        //set the filter predict whenever the text field changes
        txt80.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList.setPredicate(section -> {


                //if the txt80 is empty, display all Products
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(section.getSid()).indexOf(lowerCaseFilter) > -1) {
                    return true;

                } else if (section.getName().toLowerCase().indexOf(lowerCaseFilter) > -1) {
                    return true;}
                else if (String.valueOf(section.getManager_id()).indexOf(lowerCaseFilter) > -1) {
                    return true;

                }else
                    return false;// no match found

            });
        });

        SortedList<Section> sortedData = new SortedList<>(filterList);

        sortedData.comparatorProperty().bind(Section.comparatorProperty());

        //Apply filtered and sorted data to the table View.setItems(sortedData);

        Section.setItems(sortedData);
    }

}
