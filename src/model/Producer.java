public class Producer extends Client {

    private int numReps;

    public Producer(String nickname, String vincDate, String picURL) {
        super(nickname, vincDate);
    }

    /**
     * Method to get the total number of reproductions
     * 
     * @return number of reproductions
     */
    public int getNumberOfReps() {
        return numReps;
    }

    /**
     * Method to refresh the number of the reproductions of the audio. Each use
     * increases the counter by one.
     */
    public void refreshNumberOfReps() {
        numReps++;
    }
}
