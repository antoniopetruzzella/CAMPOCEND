package com.android.ggallery.museodellemarionette;

import java.util.ArrayList;

public class Room {

    public String Uuid;
    public String Name;
    private Integer Minor;
    private String VideoFilename;
    private ArrayList<String> AudioFileNames;

    public Room(String uuid, String name, Integer minor, String videoFilename, ArrayList<String> audioFileNames){

        Uuid=uuid;
        Minor=minor;
        Name=name;
        VideoFilename=videoFilename;
        AudioFileNames=audioFileNames;


    }

    public String getUuid(){

        return Uuid;
    }

    public String getName(){


        return Name;
    }

    public Integer getMinor(){


        return Minor;
    }

    public String getVideoFilename(){

        return VideoFilename;
    }

    public ArrayList<String>getAudioFileNames(){

        return AudioFileNames;

    }

}