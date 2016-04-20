/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.VO;

/**
 *
 * @author sada3260
 */
public class SubCategoryVO {
    private String subCategoryName;
    private String subCategoryId;
    private String categoryId;
    private String emailTemplateId;
    private String subCategory_isDeleted;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getEmailTemplateId() {
        return emailTemplateId;
    }

    public void setEmailTemplateId(String emailTemplateId) {
        this.emailTemplateId = emailTemplateId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategory_isDeleted() {
        return subCategory_isDeleted;
    }

    public void setSubCategory_isDeleted(String subCategory_isDeleted) {
        this.subCategory_isDeleted = subCategory_isDeleted;
    }
    
}
