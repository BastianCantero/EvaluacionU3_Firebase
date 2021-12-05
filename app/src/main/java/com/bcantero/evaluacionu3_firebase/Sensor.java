package com.bcantero.evaluacionu3_firebase;

public class Sensor {

    private String dateSensor;
    private String id_Sensor;
    private String locationSensor;
    private String observationSensor;
    private String sensorName;
    private String typeSensor;
    private String valueSensor;

    public Sensor() {
    }

    public Sensor(String dateSensor, String id_Sensor, String locationSensor, String observationSensor, String sensorName, String typeSensor, String valueSensor) {
        this.dateSensor = dateSensor;
        this.id_Sensor = id_Sensor;
        this.locationSensor = locationSensor;
        this.observationSensor = observationSensor;
        this.sensorName = sensorName;
        this.typeSensor = typeSensor;
        this.valueSensor = valueSensor;
    }

    public String getDateSensor() {
        return dateSensor;
    }

    public void setDateSensor(String dateSensor) {
        this.dateSensor = dateSensor;
    }

    public String getId_Sensor() {
        return id_Sensor;
    }

    public void setId_Sensor(String id_Sensor) {
        this.id_Sensor = id_Sensor;
    }

    public String getLocationSensor() {
        return locationSensor;
    }

    public void setLocationSensor(String locationSensor) {
        this.locationSensor = locationSensor;
    }

    public String getObservationSensor() {
        return observationSensor;
    }

    public void setObservationSensor(String observationSensor) {
        this.observationSensor = observationSensor;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getTypeSensor() {
        return typeSensor;
    }

    public void setTypeSensor(String typeSensor) {
        this.typeSensor = typeSensor;
    }

    public String getValueSensor() {
        return valueSensor;
    }

    public void setValueSensor(String valueSensor) {
        this.valueSensor = valueSensor;
    }
}