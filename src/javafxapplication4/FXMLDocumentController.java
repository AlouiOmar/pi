/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import entities.Station;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import services.ServiceStation;
import utile.MaConnection;

/**
 * FXML Controller class
 *
 * @author ezzedine
 */
public class FXMLDocumentController implements Initializable {

    
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private TextArea text1;
    Connection cnx = MaConnection.getInstance().getConnection();

    ServiceStation SS;
    @FXML
    private TextField longitude;
    @FXML
    private TextField lattitude;
    @FXML
    private TextField name;
     ObservableList<Station> data;
    @FXML
    private TableView<Station> table ;
    @FXML
    private TableColumn<Station, Integer> idCol;
    @FXML
    private TableColumn<Station, String> nameCol;
    @FXML
    private TableColumn<Station, Float> latCol;
    @FXML
    private TableColumn<Station, Float> lonCol;
    @FXML
    private Button idAfficher;

    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButtonToTable();
        // TODO
    }


  

    @FXML
    private void add(ActionEvent event) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "INSERT INTO `station` "
                + "(`id`, `name`, `lat`,`lon`)"
                + " VALUES (NULL, '" + name.getText() + "',"
                + " '" + lattitude.getText() + "',"
                + " '" + longitude.getText() + "')";
        stm.executeUpdate(query);
        System.out.println("iii");
    }

    @FXML
    private void idAfficherAction(ActionEvent event) throws SQLException {
         SS = new ServiceStation();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            latCol.setCellValueFactory(new PropertyValueFactory<>("lat"));
            lonCol.setCellValueFactory(new PropertyValueFactory<>("lon"));
            List<Station> ls = SS.getStations();
            data = FXCollections.observableArrayList(ls);
            table.setItems(data);
    }
    
    
    
    private void refrechTable() throws SQLException {
        data.removeAll();
        List<Station> lp = SS.getStations();
        data = FXCollections.observableArrayList(lp);
        table.setItems(data);
    }


    private void addButtonToTable() {
        TableColumn<Station, Void> colBtn = new TableColumn("delete");
       Callback<TableColumn<Station, Void>, TableCell<Station, Void>> cellFactory
                = new Callback<TableColumn<Station, Void>, TableCell<Station, Void>>() {
                    @Override
                    public TableCell<Station, Void> call(final TableColumn<Station, Void> param) {
                        final TableCell<Station, Void> cell = new TableCell<Station, Void>() {
                            private final Button btn = new Button("delete");

                            {
                                btn.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);\n"
                                        + "    -fx-background-radius: 30;\n"
                                        + "    -fx-background-insets: 0;\n"
                                        + "    -fx-text-fill: white;");
                                btn.setOnAction(
                                        (ActionEvent event) -> {
                                            Station p = getTableView().getItems().get(getIndex());
                                            try {
                                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                                alert.setTitle("Confirmation Dialog");
                                                alert.setHeaderText("Are you sure to delete " + p.getName());
                                                alert.setContentText("Are you ok with this?");
                                                Optional<ButtonType> result = alert.showAndWait();
                                                if (result.get() == ButtonType.OK) {
                                                    SS.deleteStation(p);
                                                    refrechTable();
                                                }
                                            } catch (Exception e) {
                                            }
                                            System.out.println("selectedData: " + p);
                                        }
                                );
                            }

                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(btn);
                                }
                            }
                        };
                        return cell;
                    }
                };
        TableColumn<Station, Void> colBtnUpdate = new TableColumn("update");
        Callback<TableColumn<Station, Void>, TableCell<Station, Void>> cellFactory2
                = new Callback<TableColumn<Station, Void>, TableCell<Station, Void>>() {
                    @Override
                    public TableCell<Station, Void> call(final TableColumn<Station, Void> param) {
                        final TableCell<Station, Void> cell = new TableCell<Station, Void>() {
                            private final Button btn = new Button("update");

                            {
                                btn.setStyle("-fx-background-color: \n"
                                        + "        #c3c4c4,\n"
                                        + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
                                        + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
                                        + "    -fx-background-radius: 30;\n"
                                        + "    -fx-background-insets: 0,1,1;\n"
                                        + "    -fx-text-fill: black;\n"
                                        + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
                                btn.setOnAction(
                                        (ActionEvent event) -> {
                                            Station p = getTableView().getItems().get(getIndex());
                                            try {
                                                TextInputDialog dialog = new TextInputDialog("");
                                                dialog.setTitle("Text Input Dialog");
                                                dialog.setHeaderText("Update the price of "+p.getName());
                                                dialog.setContentText("Please enter the new price:");
                                               

                                                // Traditional way to get the response value.
                                                Optional<String> result = dialog.showAndWait();
                                                if (result.isPresent()){
                                                    SS.updateStation(null);
                                                    refrechTable();
                                                    System.out.println("Your name: " + result.get());
                                                }
                                            } catch (Exception e) {
                                            }
                                            System.out.println("selectedData: " + p);
                                        }
                                );
                            }

                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(btn);
                                }
                            }
                        };
                        return cell;
                    }
                };
        colBtn.setCellFactory(cellFactory);
        colBtnUpdate.setCellFactory(cellFactory2);
        table.getColumns().addAll(colBtn, colBtnUpdate);

    }
    
    public class WebViewDemo extends Application {
 
    @Override
    public void start(final Stage stage) {
 
        Button buttonURL = new Button("Load Page https://eclipse.org");
        Button buttonHtmlString = new Button("Load HTML String");
        Button buttonHtmlFile = new Button("Load File maps");
 
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
 
        buttonURL.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                String url = "https://eclipse.org";
                // Load a page from remote url.
                webEngine.load(url);
            }
        });
 
        buttonHtmlString.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                String html = "<html><h1>Hello</h1><h2>Hello</h2></html>";
                // Load HTML String
                webEngine.loadContent(html);
            }
        });
        buttonHtmlFile.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                try {
                    File file = new File("C:\\Users\\ezzedine\\Documents\\NetBeansProjects\\test\\JavaFXApplication8\\src\\javafxapplication8\\googlemap.html");
                    URL url = file.toURI().toURL();
                    // file:/C:/test/a.html
                    System.out.println("Local URL: " + url.toString());
                    webEngine.load(url.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
 
            }
        });
 
        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);
        root.getChildren().addAll(buttonURL, buttonHtmlString, buttonHtmlFile, browser);
 
        Scene scene = new Scene(root);
 
        stage.setTitle("JavaFX WebView (o7planning.org)");
        stage.setScene(scene);
        stage.setWidth(450);
        stage.setHeight(300);
 
        stage.show();
    }
 
    public /*static*/ void main(String[] args) {
        launch(args);
    }
 
}

    }


