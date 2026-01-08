package com.example.eau.event;


import java.time.LocalDateTime;

public class SurconsommationEvent {
    private String pompeId;
    private Double consommationObservee;
    private Double seuil;
    private LocalDateTime timestamp;
    private String message;
    
    // Constructors
    public SurconsommationEvent() {}
    
    public SurconsommationEvent(String pompeId, Double consommationObservee, 
                               Double seuil, String message) {
        this.pompeId = pompeId;
        this.consommationObservee = consommationObservee;
        this.seuil = seuil;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getPompeId() { return pompeId; }
    public void setPompeId(String pompeId) { this.pompeId = pompeId; }
    
    public Double getConsommationObservee() { return consommationObservee; }
    public void setConsommationObservee(Double consommationObservee) { 
        this.consommationObservee = consommationObservee; 
    }
    
    public Double getSeuil() { return seuil; }
    public void setSeuil(Double seuil) { this.seuil = seuil; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    @Override
    public String toString() {
        return "SurconsommationEvent{" +
                "pompeId='" + pompeId + '\'' +
                ", consommationObservee=" + consommationObservee +
                ", seuil=" + seuil +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}