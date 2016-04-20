package com.in.VO;
import java.util.ArrayList;
/**
 *
 * @author Sandi
 */
//Till now thsi VO has not been used.This VO will basically be used for code optimization so that the application goes
//faster and less database calls are made for obtaining category list from the database.
public class CurrentCategoryListVO {
    private ArrayList<CategoryVO> categoryList;

    public ArrayList<CategoryVO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<CategoryVO> categoryList) {
        this.categoryList = categoryList;
    }
    
}
