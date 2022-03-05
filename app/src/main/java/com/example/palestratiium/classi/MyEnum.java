package com.example.palestratiium.classi;

import com.example.palestratiium.R;

public enum MyEnum{
    TUTTI("TUTTI"),
    PETTO("PETTO"),
    DORSO("DORSO"),
    GAMBE("GAMBE"),
    TRICIPITI("TRICIPITI"),
    BICIPITI("BICIPITI"),
    SPALLE("SPALLE");

    private String friendlyName;

     MyEnum(String friendlyName){
        this.friendlyName = friendlyName;
    }


    @Override public String toString(){
        return friendlyName;
    }
}