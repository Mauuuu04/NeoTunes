import java.util.ArrayList;
import java.util.Collections;

public class Playlist {

    private String creatorName;
    private String name;

    private ArrayList<Audio> content = new ArrayList<Audio>(20);

    public Playlist(String creatorName, String name) {
        this.creatorName = creatorName;
        this.name = name;
    }

    /**
     * Method to get the creator name
     * 
     * @return creator name
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * Method to get the playlist name
     * 
     * @return playlist name
     */
    public String getPlaylistName() {
        return name;
    }

    /**
     * Method to get playlist total elements
     * 
     * @return total elements
     */
    public int getTotalElements() {
        return content.size();
    }

    /**
     * Method to get a specific audio type
     * 
     * @param index
     * @return audio type
     */
    public int getElementType(int index) {
        int type = 0;

        if (content.get(index) instanceof Song) {
            type = 1;
        } else
            type = 2;

        return type;
    }

    /**
     * Method to get an audio
     * 
     * @param name   audio name
     * @param opName op name
     * @return Audio
     */
    public Audio getAudio(String name, String opName) {
        return content.get(searchAudioContent(name, opName));
    }

    /**
     * Method to get audio name and op name
     * 
     * @param index
     * @return audio name an op name
     */
    public String getAudioInfo(int index) {
        return content.get(index).getAudioName() + "-" + content.get(index).getOpName();
    }

    /**
     * Method to search the index of an audio using the audio name and the op name
     * 
     * @param name
     * @param opName
     * @return audio index
     */
    public int searchAudioContent(String name, String opName) {
        boolean found = false;
        int pos = -1;

        for (int i = 0; i < content.size() && !found; i++) {
            if (content.get(i).getAudioName().equalsIgnoreCase(name)
                    && content.get(i).getOpName().equalsIgnoreCase(opName)) {
                pos = i;
                found = true;
            }
        }

        return pos;
    }

    /**
     * Method to add audio to the playlist
     * 
     * @param audioContent audio
     */
    public void addAudioContent(Audio audioContent) {
        content.add(audioContent);
    }

    /**
     * Method to move audio from one position to another
     * 
     * @param audioPos audio index
     * @param newPos   new position
     */
    public void moveAudio(int audioPos, int newPos) {
        Collections.swap(content, audioPos, newPos);
    }

    /**
     * Method to remove an audio
     * 
     * @param index
     */
    public void removeAudio(int index) {
        content.remove(content.get(index));
    }

}
