/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.VO;

import java.sql.Blob;

/**
 *
 * @author sada3260
 */
public class EcardVO {
    private String ecardName;
    private String ecardId;
    private String ecardDescription;
    private String subCategoryId;
    private String ecard_isDeleted;
    private Blob ecardImageFile;
    private String ecardContributorName;

    public String getEcardDescription() {
        return ecardDescription;
    }

    public void setEcardDescription(String ecardDescription) {
        this.ecardDescription = ecardDescription;
    }

    public String getEcardId() {
        return ecardId;
    }

    public void setEcardId(String ecardId) {
        this.ecardId = ecardId;
    }

    public Blob getEcardImageFile() {
        return ecardImageFile;
    }

    public void setEcardImageFile(Blob ecardImageFile) {
        this.ecardImageFile = ecardImageFile;
    }

    public String getEcardName() {
        return ecardName;
    }

    public void setEcardName(String ecardName) {
        this.ecardName = ecardName;
    }

    public String getEcard_isDeleted() {
        return ecard_isDeleted;
    }

    public void setEcard_isDeleted(String ecard_isDeleted) {
        this.ecard_isDeleted = ecard_isDeleted;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getEcardContributorName() {
        return ecardContributorName;
    }

    public void setEcardContributorName(String ecardContributorName) {
        this.ecardContributorName = ecardContributorName;
    }
    
}