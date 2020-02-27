/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Time;

/**
     *
     * @author ezzedine
 */
public class Circuit {
    private int id ;
    private String name ,difficulty ;
    private float  distance ;
    private String begin ,end, pause ;
    private Time duration;
    
    public Circuit() {
    }

    public Circuit(int id, String name, float distance, String begin, String end, String pause, Time duration, String difficulty) {
        this.name = name;
        this.difficulty = difficulty;
        this.distance = distance;
        this.begin = begin;
        this.end = end;
        this.pause = pause;
        this.duration = duration;
    }

    public Circuit(int id, String name, String difficulty, float distance, String begin, String end, String pause, Time duration) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.distance = distance;
        this.begin = begin;
        this.end = end;
        this.pause = pause;
        this.duration = duration;
    }
    
  
    public Circuit(String ariana, int i, int i0, int i1, float f, Time valueOf, String medium) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Circuit(int i, Circuit p, int i0, int i1, int i2, float f, Time valueOf, String medium) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   /* public Circuit(int i, String la3b_djer, int i0, int i1, int i2, int i3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getBegin() {
        return begin;
    }

    
    public String getEnd() {
        return end;
    }

    

    public String getPause() {
        return pause;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setPause(String pause) {
        this.pause = pause;
    }

 
    @Override
    public String toString() {
        return "Circuit{" + "name=" + name  + ", begin=" + begin + ", end=" + end + ", pause=" + ", distance=" + distance + pause+ ", duration=" + duration + ", difficulty=" + difficulty + '}';
    }


   
}
    
    

