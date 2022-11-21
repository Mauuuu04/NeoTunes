public class Song extends Audio {

    private String albumName;
    private double price;
    private int genre;
    private int timesSold = 0;

    private Genre songGenre;

    public Song(String name, String opName, String albumName, String picURL, String length, double price, int genre,
            int numRep) {
        super(name, opName, picURL, length, numRep);
        this.genre = genre;
        this.albumName = albumName;
        this.price = price;

        switch (genre) {
            case 1:
                songGenre = Genre.ROCK;
                break;
            case 2:
                songGenre = Genre.POP;
                break;
            case 3:
                songGenre = Genre.TRAP;
                break;
            case 4:
                songGenre = Genre.HOUSE;
                break;
        }
    }

    /**
     * Method to get the song name
     * 
     * @return song name
     */
    @Override
    public String getAudioName() {
        return super.getAudioName();
    }

    /**
     * Method to get the song op name
     * 
     * @return song op name
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
     * Method to get the pic url
     * 
     * @return pic url
     */
    @Override
    public String getPicURL() {
        return super.getPicURL();
    }

    /**
     * Method to get the song length
     * 
     * @return song length
     */
    @Override
    public String getLength() {
        return super.getLength();
    }

    /**
     * Method to get the album name
     * 
     * @return album name
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * Method to get the song genre
     * 
     * @return song genre
     */
    public int getSongGenre() {
        return genre;
    }

    /**
     * Method to get the genre name
     * 
     * @return genre name
     */
    public String getSongGenreName() {
        return songGenre.getGenreName();
    }

    /**
     * Method to get the song price
     * 
     * @return song price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method to get the number of times sold
     * 
     * @return times sold
     */
    public int getTimesSold() {
        return timesSold;
    }

    @Override
    /**
     * Method to update the number of reproductions
     */
    public void refreshNumberOfReps() {
        super.refreshNumberOfReps();
    }

    /**
     * Method to update the number of times sold
     */
    public void refreshTimesSold() {
        timesSold++;
    }

}
