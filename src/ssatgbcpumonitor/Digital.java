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
public class Digital {
    
    private Timeline timeline; 
    private KeyFrame keyframe; 
    private String display = "--.--%"; 
    
    
     public void setTimeline(Timeline timeline){
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
     
    public void start(){
        timeline.play(); 
    }
    
    public void stop(){
        timeline.pause();
    }
    
    public void reset(){
       display = "--.--%";  
           
    }
    
    
    
    
}
