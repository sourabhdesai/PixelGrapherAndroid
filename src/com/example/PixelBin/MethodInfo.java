package com.example.PixelBin;

import android.graphics.drawable.Drawable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class MethodInfo {

    private String name;
    private String description;
    private Drawable graphic;
    private String[] parameters;
    private String outputType;
    private String notes;

    private String infoLink;

    public MethodInfo(String name, String description, Drawable graphic, String[] parameters, String outputType, String notes) {
        this.name = name;
        this.description = description;
        this.graphic = graphic;
        this.parameters = parameters;
        this.outputType = outputType;
        this.notes = notes;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getGraphic() {
        return graphic;
    }

    public void setGraphic(Drawable graphic) {
        this.graphic = graphic;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }
	
}//End of Class---------------------------------------------------------------------------------------------------------------------
