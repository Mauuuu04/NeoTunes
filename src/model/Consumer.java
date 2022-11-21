
public class Consumer extends Client {

    private String id;
    private int[] genreReps = new int[4];
    private int[] categoryReps = new int[4];

    public Consumer(String nickname, String vincDate, String id) {
        super(nickname, vincDate);
        this.id = id;
    }

    /**
     * Method to get user ID
     * 
     * @return user ID
     */
    public String getID() {
        return id;
    }

    /**
     * Method to get the reproductions of each genre
     * 
     * @return int[] with the reproductions of each genre
     */
    public int[] getGenreReps() {
        return genreReps;
    }

    /**
     * Method to update the reproductions of a specific genre
     * 
     * @param genre genre
     */
    public void updateGenreReps(int genre) {
        genreReps[genre]++;
    }

    /**
     * Method to get the reproductions of each category
     * 
     * @return int[] with the reproductions of each category
     */
    public int[] getCategoryReps() {
        return categoryReps;
    }

    /**
     * Method to update the reproductions of a specific category
     * 
     * @param category
     */
    public void updateCategoryReps(int category) {
        categoryReps[category]++;
    }

}
