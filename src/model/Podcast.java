public class Podcast extends Audio {

    private String description;
    private int cat;

    private Category category;

    public Podcast(String name, String opName, String description, String picURL, String length, int numRep, int cat) {
        super(name, opName, picURL, length, numRep);
        this.cat = cat;

        switch (cat) {
            case 1:
                category = Category.POLITICS;
                break;
            case 2:
                category = Category.ENTERTAINMENT;
                break;
            case 3:
                category = Category.VIDEOGAMES;
                break;
            case 4:
                category = Category.FASHION;
                break;
        }

    }

    /**
     * Method to get the podcast name
     * 
     * @return podcast name
     */
    @Override
    public String getAudioName() {
        return super.getAudioName();
    }

    /**
     * Method to get the podcast op name
     * 
     * @return op name
     */
    @Override
    public String getOpName() {
        return super.getOpName();
    }

    /**
     * Method to get the number of reproductions
     * 
     * @return number of reproductions
     */
    @Override
    public int getNumberOfReps() {
        return super.getNumberOfReps();
    }

    /**
     * Method to get the numeric value of the podcast category
     * 
     * @return category index
     */
    public int getCategory() {
        return cat;
    }

    /**
     * Method to get the category name
     * 
     * @return category name
     */
    public String getCategoryName() {
        return category.getCategoryName();
    }

    @Override
    /**
     * Method to update the number of reproductions
     */
    public void refreshNumberOfReps() {
        super.refreshNumberOfReps();
    }

}
