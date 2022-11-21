public enum Genre {
    ROCK("Rock"), POP("Pop"), TRAP("Trap"), HOUSE("House");

    private String name;

    private Genre(String name) {
        this.name = name;
    }

    /**
     * Method to get the genre name
     * 
     * @return genre name
     */
    public String getGenreName() {
        return name;
    }
}
