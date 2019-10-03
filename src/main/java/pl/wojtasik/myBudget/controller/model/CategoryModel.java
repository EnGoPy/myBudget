package pl.wojtasik.myBudget.controller.model;

public class CategoryModel {

    private int categoryId;
    private String categoryName;

    public CategoryModel(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryModel(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
