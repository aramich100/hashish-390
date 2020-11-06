package com.example.hashish.Database;

public class WeedClassInfo {

    private int light;
    private int water;
    private int humidity;
    private int temperature;

    public WeedClassInfo(){

    }
    public WeedClassInfo(int light, int water, int humidity, int temperature){
        this.light = light;
        this.water = water;
        this.humidity = humidity;
        this.temperature = temperature;

    }


    public void setlight(int light){
        this.light = light;
    }

    public void setWater(int water){
        this.water = water;
    }

    public void setHumidity(int humidity){
        this.humidity = humidity;
    }

    public void setTemperature(int temperature){
        this.temperature = temperature;
    }

    public int getLight() {
        return light;
    }

    public int getWater(){
        return water;
    }
    public int getHumidity(){
        return humidity;
    }
    public int getTemperature(){
        return temperature;
    }
    public String toString(){
        return light + "\n" + water +"\n" + humidity + "\n" + temperature + "%";
    }

}
