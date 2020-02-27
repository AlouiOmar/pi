package edu.velo.gui;

import edu.velo.entities.Stat;
import edu.velo.services.AnnonceServices;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

public class StatTwo extends Application {
    private List<Stat> lndv =new ArrayList<Stat>();
  @Override public void start(Stage stage) {
    final SwingNode chartSwingNode = new SwingNode();
    final SwingNode chartSwingNode2 = new SwingNode();
    Stage s1=new Stage();
    Stage s2=new Stage();
    chartSwingNode.setContent(
      new ChartPanel(
        generatePieChart()
              
      )      
    );
 chartSwingNode2.setContent(
      new ChartPanel(
        generatePieChart2()
              
      )      
    );
    stage.setScene(
      new Scene(
        new StackPane(    
          chartSwingNode
        )    
      )
    );

    
    stage.show();
  }

  private JFreeChart generatePieChart() {
      AnnonceServices ads=new AnnonceServices();
      
      lndv=ads.StatisticNrv();
        ObservableList<PieChart.Data> chartndv =  FXCollections.observableArrayList();
        lndv.forEach(v->{
            
                chartndv.add(new PieChart.Data(v.getNom(), v.getNbr()));
                
        });    
    DefaultPieDataset dataSet = new DefaultPieDataset();
     lndv.forEach((Stat v)->{
            
//        dataSet.setValue((v.getNom(), v.getNbr());
    
        }); 
     
     for(int i=0;i<lndv.size();i++){
        String nom= lndv.get(i).getNom();
        int nbr=lndv.get(i).getNbr();
        double nbrr=(double)nbr;
        
       dataSet.setValue(nom, nbrr);
     }
//    dataSet.setValue("China",        1344);
//    dataSet.setValue("India",        1241.0);
//    dataSet.setValue("United States", 310.5);

    return ChartFactory.createPieChart(
      "Nombre des annoces publiées par Type", dataSet, true, true, false
    );
  }  
 private JFreeChart generatePieChart2() {
      AnnonceServices ads=new AnnonceServices();
      
      lndv=ads.StatisticNrv();
        ObservableList<PieChart.Data> chartndv =  FXCollections.observableArrayList();
        lndv.forEach(v->{
            
                chartndv.add(new PieChart.Data(v.getNom(), v.getNbr()));
                
        });    
    DefaultPieDataset dataSet = new DefaultPieDataset();
     lndv.forEach((Stat v)->{
            
//        dataSet.setValue((v.getNom(), v.getNbr());
    
        }); 
     
     for(int i=0;i<lndv.size();i++){
        String nom= lndv.get(i).getNom();
        int nbr=lndv.get(i).getNbr();
        double nbrr=(double)nbr;
        
       dataSet.setValue(nom, nbrr);
     }
//    dataSet.setValue("China",        1344);
//    dataSet.setValue("India",        1241.0);
//    dataSet.setValue("United States", 310.5);

    return ChartFactory.createPieChart(
      "Population 2011", dataSet, true, true, false
    );
  }  
  public static void main(String[] args) { launch(args); }
}