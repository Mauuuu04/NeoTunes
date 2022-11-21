public class Standard extends Consumer {

    private Playlist[] playlists = new Playlist[20];
    private Song[] purchasedSongs = new Song[100];

    public Standard(String nickname, String vincDate, String id) {
        super(nickname, vincDate, id);
    }

    /**
     * Method to get the playlist name
     * 
     * @param playlist playlist index
     * @return playlist name
     */
    public String getPlaylistName(int playlist) {
        return playlists[playlist].getPlaylistName();
    }

    /**
     * Method to get an audio
     * 
     * @param name     audio name
     * @param opName   op name
     * @param playlist playlist index
     * @return Audio
     */
    public Audio getAudio(String name, String opName, int playlist) {
        return playlists[playlist].getAudio(name, opName);
    }

    /**
     * Method to get the availability of the playlist array
     * 
     * @return number of available positions
     */
    public int getPlaylistAvailability() {
        int counter = 0;

        for (int i = 0; i < playlists.length; i++) {
            if (playlists[i] == null)
                counter++;
        }

        return counter;
    }

    /**
     * Method to get the availability of the songs array
     * 
     * @return number of available positions
     */
    public int getSongAvailability() {
        int counter = 0;

        for (int i = 0; i < purchasedSongs.length; i++) {
            if (purchasedSongs[i] == null)
                counter++;
        }

        return counter;
    }

    /**
     * Method to get an available position of the playlist array
     * 
     * @return available position
     */
    public int getAvailablePlaylistPos() {
        int pos = -1;
        boolean found = false;

        for (int i = 0; i < playlists.length && !found; i++) {
            if (playlists[i] == null) {
                pos = i;
                found = true;
            }
        }

        return pos;
    }

    /**
     * Method to get an available position of the song array
     * 
     * @return available position
     */
    public int getAvailableSongPos() {
        int pos = -1;
        boolean found = false;

        for (int i = 0; i < purchasedSongs.length && !found; i++) {
            if (purchasedSongs[i] == null) {
                pos = i;
                found = true;
            }
        }

        return pos;
    }

    /**
     * Method to get the total of playlists
     * 
     * @return total playlists
     */
    public int getTotalPlaylists() {
        int numPlaylists = 0;

        for (int i = 0; i < playlists.length; i++) {
            if (playlists[i] != null) {
                numPlaylists++;
            }
        }

        return numPlaylists;
    }

    /**
     * Method to create a playlist
     * 
     * @param name playlist name
     */
    public void createPlaylist(String name) {
        playlists[getAvailablePlaylistPos()] = new Playlist(getNickname(), name);
    }

    /**
     * Method to get the total elements of a playlist
     * 
     * @param playlist playlist index
     * @return total elements
     */
    public int getPlaylistTotalElements(int playlist) {
        return playlists[playlist].getTotalElements();
    }

    /**
     * Method to get a specific audio type
     * 
     * @param playlist playlist index
     * @param index    audio index
     * @return audio type
     */
    public int getPlaylistElementType(int playlist, int index) {
        return playlists[playlist].getElementType(index);
    }

    /**
     * Method to get an audio name and op name
     * 
     * @param playlist playlist index
     * @param index    audio index
     * @return audio name and op name
     */
    public String getPlaylistElement(int playlist, int index) {
        return playlists[playlist].getAudioInfo(index);
    }

    /**
     * Method to add an audio to a playlist
     * 
     * @param audio    audio
     * @param playlist playlist index
     */
    public void addAudioToPlaylist(Audio audio, int playlist) {
        playlists[playlist].addAudioContent(audio);
    }

    /**
     * Method to remove an audio from a playlist
     * 
     * @param index    audio index
     * @param playlist playlist index
     */
    public void removeAudioFromPlaylist(int index, int playlist) {
        playlists[playlist].removeAudio(index);
    }

    /**
     * Method to buy a song
     * 
     * @param name      song name
     * @param opName    op name
     * @param albumName album name
     * @param picURL    pic url
     * @param length    song length
     * @param price     song price
     * @param genre     song genre
     * @param numRep    number of reproductions (initial value = 0)
     */
    public void buySong(String name, String opName, String albumName, String picURL, String length, double price,
            int genre, int numRep) {
        Song song = new Song(name, opName, albumName, picURL, length, price, genre, numRep);
        purchasedSongs[getAvailableSongPos()] = song;
        song.refreshTimesSold();
    }

    /**
     * Method to move an audio
     * 
     * @param playlist playlist index
     * @param audioPos audio index
     * @param newPos   new position
     */
    public void moveAudio(int playlist, int audioPos, int newPos) {
        playlists[playlist].moveAudio(audioPos, newPos);
    }

    /**
     * Method to check if a song is already purchased
     * 
     * @param name   song name
     * @param opName op name
     * @return boolean value (true if the song is already purchased)
     */
    public boolean alreadyBought(String name, String opName) {
        boolean bought = false;

        if (purchasedSongs.length != 0) {
            for (int i = 0; i < purchasedSongs.length; i++) {
                if (purchasedSongs[i] != null) {
                    if (purchasedSongs[i].getAudioName().equals(name) && purchasedSongs[i].getOpName().equals(opName)) {
                        bought = true;
                    }
                }
            }
        }

        return bought;
    }
}
