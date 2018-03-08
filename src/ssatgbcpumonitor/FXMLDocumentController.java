/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssatgbcpumonitor;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 *
 * @author Sharoze
 */
public class FXMLDocumentController implements Initializable {
    
   @FXML 
   private ImageView hand; 
    
   @FXML
   private Label digital; 
   
   @FXML 
   private Button start; 
   
   @FXML
   private Button record; 
   
   @FXML 
   private Label record1; 
   
   @FXML 
   private Label record2; 
   
   @FXML 
   private Label record3; 
   
   private Analog analog; 
   
   private Digital dig; 
   
   private double cpu = 0; 
   
   private int count = 1; 
   
   private int temp = 1; 
   
  
   
    private void setupAnalog(){
        analog.setKeyFrame(new KeyFrame(Duration.millis(100), (ActionEvent event) -> {
            analogUpdate(); 
        })); 
        analog.setTimeLine(new Timeline(analog.getKeyFrame()));
        analog.getTimeline().setCycleCount(Animation.INDEFINITE); 
        
    }
    
    private void analogUpdate(){
        cpu = FXMLDocumentController.getCPUUsage() * 100; 
        analog.setRotate();
        hand.setRotate(analog.getRotate() + cpu);
    }
    
    private void setupDigital(){
        dig.setKeyFrame(new KeyFrame(Duration.millis(100), (ActionEvent event) -> {
            digitalUpdate(); 
        }));
        
        dig.setTimeline(new Timeline(dig.getKeyFrame()));
        dig.getTimeline().setCycleCount(Animation.INDEFINITE);
        
    }
   
    private void digitalUpdate(){
        cpu = FXMLDocumentController.getCPUUsage() * 100; 
        
        NumberFormat df = DecimalFormat.getInstance();
        df.setMinimumFractionDigits(1);
        df.setMaximumFractionDigits(2);
        String number = df.format(cpu); 
        
        
        
        digital.setText(number+"%");
        
    }
   
    
    public static double getCPUUsage() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double value = 0;
        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().startsWith("getSystemCpuLoad")
                    && Modifier.isPublic(method.getModifiers())) {
                try {
                    value = (double) method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = 0;
                }
                return value;
            }
        }
        return value;
    }
   
    
    
    @FXML
    private void startbtn(ActionEvent event){
        if("Start".equals(start.getText())){
           analog.getTimeline().play();
            dig.start(); 
            start.setText("Stop");
            record.setText("Record"); 
        }
        else if("Stop".equals(start.getText())){
            analog.getTimeline().stop();
            dig.getTimeline().stop(); 
            start.setText("Stop"); 
            record.setText("Reset"); 
        }
        
        
    }
    
    @FXML
    private void recordbtn(ActionEvent event){
        if("Stop".equals(start.getText()) && "Reset".equals(record.getText())){
            hand.setRotate(-135.0);
            digital.setText("--.--%"); 
            record1.setText("--.--%");
            record2.setText("--.--%");
            record3.setText("--.--%");
            start.setText("Start");
            record.setText("Record"); 
        }
       if("Record".equals(record.getText())){
        
        record.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

               if(count % 3 == 1){
                   record1.setText("Record " + temp + " : "  + digital.getText()  + " " + java.time.LocalTime.now());
                   temp++;
               }
               else if(count % 3 == 2){
                   record2.setText("Record " + temp + " : "  + digital.getText()  + " " + java.time.LocalTime.now()); 
                   temp++;
               }
               else if(count % 3 == 0){
                   record3.setText("Record " + temp + " : "  + digital.getText()  + " " + java.time.LocalTime.now());
                   temp++;
               }
               
               count++; 
           }
          
    });
                
   }
 }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         analog = new Analog(); 
         dig = new Digital(); 
        setupAnalog(); 
        setupDigital(); 
        
    }    
    
}
