/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.VO;

/**
 *
 * @author sada3260
 */
public class CategoryVO {
    private String category_id;
    private String category_name;
    private String category_isDeleted;
    
    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_isDeleted() {
        return category_isDeleted;
    }

    public void setCategory_isDeleted(String category_isDeleted) {
        this.category_isDeleted = category_isDeleted;
    }
    
}
