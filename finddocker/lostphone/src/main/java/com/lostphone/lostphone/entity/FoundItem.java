package com.lostphone.lostphone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "found_items")
public class FoundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemType;
    private String brand;
    private String imei;
    private String finderUsername;
    private String finderphone;
    public String getFinderphone() {
        return finderphone;
    }

    public void setFinderphone(String finderphone) {
        this.finderphone = finderphone;
    }
    private String documentType;
    private String documentPath;
    
    private boolean matched = false;   // ✅ ADD THIS

    // getters & setters
    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
    private String status;

    // ✅ GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;   // ✅ FIXED
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getFinderUsername() {
        return finderUsername;
    }

    public void setFinderUsername(String finderUsername) {
        this.finderUsername = finderUsername;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRecovered(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRecovered'");
    }
}
