package DataBase;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FirstPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(600);
        primaryStage.setTitle("Shini Extra");
        primaryStage.setScene(scene);
        primaryStage.show();
        //*********************************
        FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("sample1.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 320, 240);
        primaryStage.setTitle("Employee Interface");
        primaryStage.setScene(scene2);
        primaryStage.show();

        //*********************************
       /* FXMLLoader fxmlLoader3 = new FXMLLoader(Main.class.getResource("sample2.fxml"));
        Scene scene3 = new Scene(fxmlLoader3.load(), 320, 240);
        primaryStage.setTitle("Company Interface");
        primaryStage.setScene(scene3);
        primaryStage.show();

        //**********************************
        FXMLLoader fxmlLoader4 = new FXMLLoader(Main.class.getResource("AddCompany.fxml"));
        Scene scene4 = new Scene(fxmlLoader4.load(), 320, 240);
        primaryStage.setTitle("Add Company Interface");
        primaryStage.setScene(scene4);
        primaryStage.show();

        //***********************************

        FXMLLoader fxmlLoader5 = new FXMLLoader(Main.class.getResource("AddEmployee.fxml"));
        Scene scene5 = new Scene(fxmlLoader5.load(), 320, 240);
        primaryStage.setTitle("Add Employee Interface");
        primaryStage.setScene(scene5);
        primaryStage.show();
        //*************************************
        FXMLLoader fxmlLoader6 = new FXMLLoader(Main.class.getResource("UpdateEmployee.fxml"));
        Scene scene6 = new Scene(fxmlLoader6.load(), 320, 240);
        primaryStage.setTitle("Edit Employee Interface");
        primaryStage.setScene(scene6);
        primaryStage.show();
        //*************************************
        FXMLLoader fxmlLoader7 = new FXMLLoader(Main.class.getResource("UpdateCompany.fxml"));
        Scene scene7 = new Scene(fxmlLoader7.load(), 320, 240);
        primaryStage.setTitle("Edit Company Interface");
        primaryStage.setScene(scene7);
        primaryStage.show();


    }*/


    }
    public static void main(String[] args) {
        launch();
    }
}









