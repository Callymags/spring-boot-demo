package com.example.demo.model;


// WARM UP:
// 1. Create a simple model (POJO)
// 2. Add id, category name, and description fields
// 3. Add constructor
// 4. Add getters and setters
// 5. Add toString()

public class CategoryL1 {
    private Long categoryId;
    private String categoryName;
    private String categoryDesc;

    public CategoryL1(){}

    public CategoryL1(Long categoryId, String categoryName, String categoryDesc){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }

    public Long getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }

    public String getCategoryDesc(){
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc){
        this.categoryDesc = categoryDesc;
    }

    @Override
    public String toString(){
        return "CategoryL1{categoryId=" + categoryId + ", categoryName='" + categoryName + '\'' + ", categoryDesc='" + categoryDesc + '\'' + '}';
    }
}
