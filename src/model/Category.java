public enum Category {
    POLITICS("Política"), ENTERTAINMENT("Entretenimiento"), VIDEOGAMES("Videojuegos"), FASHION("Moda");

    private String name;

    private Category(String name) {
        this.name = name;
    }

    /**
     * Method to return the category name
     * 
     * @return category name
     */
    public String getCategoryName() {
        return name;
    }
}
