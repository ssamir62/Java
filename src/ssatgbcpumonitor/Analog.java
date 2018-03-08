/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssatgbcpumonitor;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 *
 * @author Sharoze
 */
public class Analog {
    
        
  
    private Timeline timeline; 
    private KeyFrame keyframe; 
    private double rotate; 
    private FXMLDocumentController x; 
    
 
    
    public void setRotate(){
         this.rotate = -135.0; 
    }
    
    public double getRotate(){
        return rotate;
    }
    
    
    public void setTimeLine(Timeline timeline){ 
        this.timeline = timeline; 
    }
    
    public Timeline getTimeline(){
        return timeline;
    }
    
    public void setKeyFrame(KeyFrame keyframe){
        this.keyframe = keyframe;
    }
    
    public KeyFrame getKeyFrame(){
        return keyframe;
    }
    
    
    
    
}
