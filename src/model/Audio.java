public class Audio {

    private String name;
    private String opName;
    private String picURL;
    private String length;
    private int numRep;

    public Audio(String name, String opName, String picURL, String length, int numRep) {
        this.name = name;
        this.opName = opName;
        this.picURL = picURL;
        this.length = length;
        this.numRep = numRep;
    }

    /**
     * Method to get the audio name
     * 
     * @return audio name
     */
    public String getAudioName() {
        return name;
    }

    /**
     * Method to get the audio op name
     * 
     * @return op name
     */
    public String getOpName() {
        return opName;
    }

    /**
     * Method to get the audio name and the name of the op
     * 
     * @return audio name and op name
     */
    public String getAudioInfo() {
        return getAudioName() + " - " + getOpName();
    }

    /**
     * Method to get the pic url
     * 
     * @return pic url
     */
    public String getPicURL() {
        return picURL;
    }

    /**
     * Method to get the audio length
     * 
     * @return length
     */
    public String getLength() {
        return length;
    }

    /**
     * Method to get the number of reproductions
     * 
     * @return number of reproductions
     */
    public int getNumberOfReps() {
        return numRep;
    }

    /**
     * Method to update the number of reproductions
     */
    public void refreshNumberOfReps() {
        numRep++;
    }

}
