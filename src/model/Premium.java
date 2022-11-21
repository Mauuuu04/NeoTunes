import java.util.ArrayList;

public class Premium extends Consumer {

    private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
    private ArrayList<Song> purchasedSongs = new ArrayList<Song>();

    public Premium(String nickname, String vincDate, String id) {
        super(nickname, vincDate, id);
    }

    /**
     * Method to get the playlist name
     * 
     * @param playlist index of the playlist ArrayList
     * @return playlist name
     */
    public String getPlaylistName(int playlist) {
        return playlists.get(playlist).getPlaylistName();
    }

    /**
     * @param name     audio name
     * @param opName   original poster name
     * @param playlist playlist index
     * @return Audio
     */
    public Audio getAudio(String name, String opName, int playlist) {
        return playlists.get(playlist).getAudio(name, opName);
    }

    /**
     * Method to get the total of playlist of the user
     * 
     * @return total number of playlists created
     */
    public int getTotalPlaylists() {
        return playlists.size();
    }

    /**
     * Method to get the total of audios a specific playlist has
     * 
     * @param playlist index of the ArrayList
     * @return number of audios
     */
    public int getPlaylistTotalElements(int playlist) {
        return playlists.get(playlist).getTotalElements();
    }

    /**
     * Method to get the type of a specific audio (podcast or song)
     * 
     * @param playlist playlist index
     * @param index    audio index
     * @return int with the audio type
     */
    public int getPlaylistElementType(int playlist, int index) {
        return playlists.get(playlist).getElementType(index);
    }

    /**
     * Method to get the name and the name of the original poster of a specific
     * audio
     * 
     * @param playlist playlist index
     * @param index    audio index
     * @return name and op name of the audio
     */
    public String getPlaylistElement(int playlist, int index) {
        return playlists.get(playlist).getAudioInfo(index);
    }

    /**
     * Method to add an audio to a playlist
     * 
     * @param audio
     * @param playlist playlist index
     */
    public void addAudioToPlaylist(Audio audio, int playlist) {
        playlists.get(playlist).addAudioContent(audio);
    }

    /**
     * Method to remove audio from playlist
     * 
     * @param index    audio index
     * @param playlist playlist index
     */
    public void removeAudioFromPlaylist(int index, int playlist) {
        playlists.get(playlist).removeAudio(index);
    }

    /**
     * Method to create playlist
     * 
     * @param name name of the playlist
     */
    public void createPlaylist(String name) {
        playlists.add(new Playlist(getNickname(), name));
    }

    /**
     * Method to move an audio
     * 
     * @param playlist playlist index
     * @param audioPos audio index
     * @param newPos   new position
     */
    public void moveAudio(int playlist, int audioPos, int newPos) {
        playlists.get(playlist).moveAudio(audioPos, newPos);
    }

    /**
     * Method to buy song
     * 
     * @param name      song name
     * @param opName    name of the original poster
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
        purchasedSongs.add(song);
        song.refreshTimesSold();
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

        if (purchasedSongs.size() > 0) {
            for (int i = 0; i < purchasedSongs.size(); i++) {
                if (purchasedSongs.get(i).getAudioName().equals(name)
                        && purchasedSongs.get(i).getOpName().equals(opName)) {
                    bought = true;
                }
            }
        }

        return bought;
    }
}
