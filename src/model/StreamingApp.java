import java.util.ArrayList;
import java.util.Random;

public class StreamingApp {

    private Random r = new Random();
    private ArrayList<Client> clients = new ArrayList<Client>(50);
    private ArrayList<Audio> audioContent = new ArrayList<Audio>(50);
    private ArrayList<String> activityLog = new ArrayList<String>();

    public StreamingApp() {
    }

    /**
     * Method to get user name
     * 
     * @param user user index
     * @return user name
     */
    public String getUserName(int user) {
        return clients.get(user).getNickname();
    }

    /**
     * Method to get user ID
     * 
     * @param user user index
     * @return user ID
     */
    public String getUserID(int user) {
        return ((Consumer) clients.get(user)).getID();
    }

    /**
     * Method to get audio index
     * 
     * @param audio
     * @return audio index
     */
    public int getAudioIndex(Audio audio) {
        boolean found = false;
        int pos = -1;

        for (int i = 0; i < audioContent.size() && !found; i++) {
            if (audioContent.get(i) == audio) {
                pos = i;
                found = true;
            }
        }
        return pos;
    }

    /**
     * Method to get audio info
     * 
     * @param index audio index
     * @return audio info
     */
    public String getAudioInfo(int index) {
        return audioContent.get(index).getAudioInfo();
    }

    /**
     * Method to get the total reproductions
     * 
     * @return total reproductions
     */
    public int getTotalReps() {
        int total = 0;

        for (int i = 0; i < audioContent.size(); i++) {
            total += audioContent.get(i).getNumberOfReps();
        }

        return total;
    }

    /**
     * Method to get original poster index in the clients list
     * 
     * @param audioIndex
     * @return op index
     */
    public int getOriginalPoster(int audioIndex) {
        int pos = -1;
        boolean found = false;

        for (int i = 0; i < clients.size() && !found; i++) {
            if (clients.get(i) instanceof Producer) {
                if (clients.get(i).getNickname().equalsIgnoreCase(audioContent.get(audioIndex).getOpName())) {
                    pos = i;
                    found = true;
                }
            }
        }

        return pos;
    }

    /**
     * Method to get playlist size
     * 
     * @param user     user index
     * @param playlist playlist index
     * @return playlist size
     */
    public int getPlaylistTotalElements(int user, int playlist) {
        int elements = 0;

        if (clients.get(user) instanceof Standard) {
            elements = ((Standard) clients.get(user)).getPlaylistTotalElements(playlist);
        } else
            elements = ((Premium) clients.get(user)).getPlaylistTotalElements(playlist);

        return elements;
    }

    /**
     * Method to get a audio name and the audio op name
     * 
     * @param user     user index
     * @param playlist playlist index
     * @param index    audio index
     * @return audio name and op name
     */
    public String getPlaylistElement(int user, int playlist, int index) {
        String element = null;

        if (clients.get(user) instanceof Standard) {
            element = ((Standard) clients.get(user)).getPlaylistElement(playlist, index);
        } else
            element = ((Premium) clients.get(user)).getPlaylistElement(playlist, index);

        return element;
    }

    /**
     * Method to get the total number of playlists of a specific user
     * 
     * @param user user index
     * @return total playlists
     */
    public int getConsumerNumberOfPlaylists(int user) {
        int numPlaylists = 0;

        if (clients.get(user) instanceof Standard) {
            numPlaylists = ((Standard) clients.get(user)).getTotalPlaylists();
        } else
            numPlaylists = ((Premium) clients.get(user)).getTotalPlaylists();

        return numPlaylists;
    }

    /**
     * Method to get the name of a user playlist
     * 
     * @param user     user index
     * @param playlist playlist index
     * @return playlist name
     */
    public String getConsumerPlaylist(int user, int playlist) {
        String display = "";

        if (clients.get(user) instanceof Standard) {
            display = ((Standard) clients.get(user)).getPlaylistName(playlist);
        } else
            display = ((Premium) clients.get(user)).getPlaylistName(playlist);

        return display;
    }

    /**
     * Method to get the producer type
     * 
     * @param name user name
     * @return type
     */
    public int getProducerType(String name) {
        int type = -1;

        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i) instanceof Artist) {
                type = 1;
            }
            if (clients.get(i) instanceof ContentCreator) {
                type = 2;
            }
        }

        return type;
    }

    /**
     * Method to get the index in the activity log of a user
     * 
     * @param id
     * @return user index in the activity log
     */
    public int getUserLogIndex(String name) {
        int pos = -1;
        boolean found = false;

        for (int i = 0; i < activityLog.size() && !found; i++) {
            String[] data = activityLog.get(i).split("-");
            if (data[0].equalsIgnoreCase(name)) {
                pos = i;
                found = true;
            }
        }

        return pos;
    }

    /**
     * Method to get user played songs
     * 
     * @param name user name
     * @return number of songs played
     */
    public int getUserPlayedSongs(String name) {
        String[] data = activityLog.get(getUserLogIndex(name)).split("-");

        return Integer.parseInt(data[data.length - 1]);
    }

    /**
     * Method to get current registered users
     * 
     * @return current users
     */
    public int getCurrentUsers() {
        return clients.size();
    }

    /**
     * Method to get most listened genre
     * 
     * @return most listened genre
     */
    public String getMostListenedGenre() {
        int[] genre = new int[4];

        for (int i = 0; i < audioContent.size(); i++) {
            if (audioContent.get(i) instanceof Song) {
                switch (((Song) audioContent.get(i)).getSongGenre()) {
                    case 1:
                        genre[0] += ((Song) audioContent.get(i)).getNumberOfReps();
                        break;
                    case 2:
                        genre[1] += ((Song) audioContent.get(i)).getNumberOfReps();
                        break;
                    case 3:
                        genre[2] += ((Song) audioContent.get(i)).getNumberOfReps();
                        break;
                    case 4:
                        genre[3] += ((Song) audioContent.get(i)).getNumberOfReps();
                        break;
                }
            }
        }

        String genreName = "";
        int max = 0;
        int pos = -1;

        for (int i = 0; i < genre.length; i++) {
            if (genre[i] > max) {
                max = genre[i];
                pos = i;
            }
        }

        if (pos == 0)
            genreName = "Rock";
        if (pos == 1)

            genreName = "Pop";
        if (pos == 2)
            genreName = "Trap";
        if (pos == 3)
            genreName = "House";

        String display = (max == 1) ? genreName + " reproducido una vez."
                : genreName + " reproducido " + max + " veces.";

        return display;
    }

    /**
     * Method to get user most listened genre
     * 
     * @return user most listened genre
     */
    public String getUserMostListenedGenre() {
        int user = r.nextInt(getCurrentUsers());

        while (clients.get(user) instanceof Consumer == false) {
            user = r.nextInt(getCurrentUsers());
        }

        int[] genre = ((Consumer) clients.get(user)).getGenreReps();

        String genreName = "";
        int max = 0;
        int pos = -1;

        for (int i = 0; i < genre.length; i++) {
            if (genre[i] > max) {
                max = genre[i];
                pos = i;
                if (pos == 0)
                    genreName = "Rock";
                if (pos == 1)
                    genreName = "Pop";
                if (pos == 2)
                    genreName = "Trap";
                if (pos == 3)
                    genreName = "House";
            }
        }

        String display = (max == 1)
                ? "El genero más escuchado por el usuario '" + getUserName(user) + "' es " + genreName
                        + " reproducido una vez."
                : "El genero más escuchado por el usuario '" + getUserName(user) + "' es " + genreName + " reproducido "
                        + max + " veces.";

        return display;
    }

    /**
     * Method to most listened category
     * 
     * @return most listened category
     */
    public String getMostListenedCategory() {
        int[] category = new int[4];

        for (int i = 0; i < audioContent.size(); i++) {
            if (audioContent.get(i) instanceof Podcast) {
                switch (((Podcast) audioContent.get(i)).getCategory()) {
                    case 1:
                        category[0] += ((Podcast) audioContent.get(i)).getNumberOfReps();
                        break;
                    case 2:
                        category[1] += ((Podcast) audioContent.get(i)).getNumberOfReps();
                        break;
                    case 3:
                        category[2] += ((Podcast) audioContent.get(i)).getNumberOfReps();
                        break;
                    case 4:
                        category[3] += ((Podcast) audioContent.get(i)).getNumberOfReps();
                        break;
                }
            }
        }

        String categoryName = "";
        int max = 0;
        int pos = -1;

        for (int i = 0; i < category.length; i++) {
            if (category[i] > max) {
                max = category[i];
                pos = i;
            }
        }

        if (pos == 0)
            categoryName = "Política";
        if (pos == 1)
            categoryName = "Entretenimiento";
        if (pos == 2)
            categoryName = "Videojuegos";
        if (pos == 3)
            categoryName = "Moda";

        String display = (max == 1) ? categoryName + " reproducida una vez."
                : categoryName + " reproducida " + max + " veces.";

        return display;
    }

    /**
     * Method to get a specific user most listened category
     * 
     * @return most listened category
     */
    public String getUserMostListenedCategory() {
        int user = r.nextInt(getCurrentUsers());

        while (clients.get(user) instanceof Consumer == false) {
            user = r.nextInt(getCurrentUsers());
        }

        int[] category = ((Consumer) clients.get(user)).getCategoryReps();

        String categoryName = "";
        int max = 0;
        int pos = -1;

        for (int i = 0; i < category.length; i++) {
            if (category[i] > max) {
                max = category[i];
                pos = i;
            }
        }

        if (pos == 0)
            categoryName = "Política";
        if (pos == 1)
            categoryName = "Entretenimiento";
        if (pos == 2)
            categoryName = "Videojuegos";
        if (pos == 3)
            categoryName = "Moda";

        String display = (max == 1)
                ? "La categoría más escuchada por el usuario '" + getUserName(user) + "' es " + categoryName
                        + " reproducida una sola vez."
                : "La categoría más escuchada por el usuario '" + getUserName(user) + "' es " + categoryName
                        + " reproducido "
                        + max + " veces.";

        return display;
    }

    /**
     * Method to get most sold song by genre
     * 
     * @param genre
     * @return most sold song by genre
     */
    public String getMostSoldSongByGenre(int genre) {
        double totalPrice = 0;
        int total = 0;

        for (int i = 0; i < audioContent.size(); i++) {
            if (audioContent.get(i) instanceof Song) {
                if (((Song) audioContent.get(i)).getSongGenre() == genre) {
                    total += ((Song) audioContent.get(i)).getTimesSold();
                    totalPrice += ((Song) audioContent.get(i)).getPrice()
                            * ((Song) audioContent.get(i)).getTimesSold();
                }
            }
        }

        return "Se vendieron " + total + " canciones, dejando una ganancia de $" + totalPrice;
    }

    /**
     * Method to count the number of songs in the platform
     * 
     * @return total songs count
     */
    public int countSongs() {
        int count = 0;
        if (audioContent.size() > 0) {
            for (int i = 0; i < audioContent.size(); i++) {
                if (audioContent.get(i) instanceof Song) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Method to count the number of podcasts in the platform
     * 
     * @return total podcasts count
     */
    public int countPodcasts() {
        int count = 0;
        if (audioContent.size() > 0) {
            for (int i = 0; i < audioContent.size(); i++) {
                if (audioContent.get(i) instanceof Podcast) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Method to count the number of artists in the platform
     * 
     * @return total artists count
     */
    public int countArtists() {
        int count = 0;
        if (clients.size() > 0) {
            for (int i = 0; i < audioContent.size(); i++) {
                if (clients.get(i) instanceof Artist) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Method to count the number of content creators in the platform
     * 
     * @return total content creators count
     */
    public int countContentCreators() {
        int count = 0;
        if (clients.size() > 0) {
            for (int i = 0; i < audioContent.size(); i++) {
                if (clients.get(i) instanceof ContentCreator) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Method to find the most sold song
     * 
     * @return most sold song
     */
    public String mostSoldSong() {
        int max = 0;
        int pos = 0;

        for (int i = 0; i < audioContent.size(); i++) {
            if (audioContent.get(i) instanceof Song) {
                if (((Song) audioContent.get(i)).getTimesSold() > max) {
                    max = ((Song) audioContent.get(i)).getTimesSold();
                    pos = i;
                }
            }
        }

        String name = ((Song) audioContent.get(pos)).getAudioName();
        double price = ((Song) audioContent.get(pos)).getPrice() * audioContent.get(pos).getNumberOfReps();
        String display = "";

        if (((Song) audioContent.get(pos)).getTimesSold() == 0) {
            display = "No hay canciones vendidas";
        } else {
            display = (max == 1)
                    ? "La canción '" + name + "' es la más vendida. Comprada 1 vez sumando un total de $" + price + "."
                    : "La canción '" + name + "' es la más vendida. Comprada " + max + " veces sumando un total de $"
                            + price + ".";
        }

        return display;
    }

    /**
     * Method to update a user played songs
     * 
     * @param userID user ID
     */
    public void updateUserPlayedSongs(String userID) {
        String[] userLog = activityLog.get(getUserLogIndex(userID)).split("-");
        activityLog.set(getUserLogIndex(userID), userID + "-" + (Integer.parseInt(userLog[userLog.length - 1]) + 1));
    }

    /**
     * Method to get the size of the audio list
     * 
     * @return list size
     */
    public int currentUploadedAudioContent() {
        return audioContent.size();
    }

    /**
     * Method to search an audio
     * 
     * @param name   audio name
     * @param opName op name
     * @return audio index
     */
    public int searchAudioContent(String name, String opName) {
        boolean found = false;
        int pos = -1;

        for (int i = 0; i < audioContent.size() && !found; i++) {
            if (audioContent.get(i).getAudioName().equalsIgnoreCase(name)
                    && audioContent.get(i).getOpName().equalsIgnoreCase(opName)) {
                pos = i;
                found = true;
            }
        }

        return pos;
    }

    /**
     * Method to check if the user is consumer
     * 
     * @param user user index
     * @return whether the user is consumer or not
     */
    public boolean isConsumer(int user) {
        boolean isConsumer = false;

        if (clients.get(user) instanceof Consumer) {
            isConsumer = true;
        }

        return isConsumer;
    }

    /**
     * Method to check if the user is standard
     * 
     * @param index user index
     * @return whether the user is standard or not
     */
    public boolean isStandardUser(int index) {
        boolean isStandard = false;

        if (clients.get(index) instanceof Standard) {
            isStandard = true;
        }

        return isStandard;
    }

    /**
     * Method to check if the audio is a podcast
     * 
     * @param index audio index
     * @return whether the audio is podcast or not
     */
    public boolean isAudioPodcast(int index) {
        boolean isPodcast = false;

        if (audioContent.get(index) instanceof Podcast) {
            isPodcast = true;
        }

        return isPodcast;
    }

    /**
     * Method to check if a playlist is empty or not
     * 
     * @param user     user index
     * @param playlist playlist index
     * @return whether the playlist is empty or not
     */
    public boolean playlistHasElements(int user, int playlist) {
        boolean hasElements = true;

        if (getPlaylistTotalElements(user, playlist) == 0) {
            hasElements = false;
        }

        return hasElements;
    }

    /**
     * Method to check if a user already exists
     * 
     * @param name user name
     * @return whether the user already exists or not
     */
    public boolean checkUserExistence(String name) {
        boolean exists = false;

        for (int i = 0; i < clients.size() && !exists; i++) {
            if (clients.get(i).getNickname().equalsIgnoreCase(name))
                exists = true;
        }

        return exists;
    }

    /**
     * Method to check if an ID is already used
     * 
     * @param id user ID
     * @return whether the ID is already used or not
     */
    public boolean alreadyUsedID(String id) {
        boolean alreadyUsed = false;

        for (int i = 0; i < clients.size() && !alreadyUsed; i++) {
            if (clients.get(i) instanceof Consumer && ((Consumer) clients.get(i)).getID().equals(id)) {
                alreadyUsed = true;
            }
        }

        return alreadyUsed;
    }

    /**
     * Method to check if an audio already exists in the list
     * 
     * @param name   audio name
     * @param opName op name
     * @return whether the audio exists or not
     */
    public boolean audioAlreadyExists(String name, String opName) {
        boolean alreadyExists = false;

        if (audioContent.size() > 1) {
            for (int i = 0; i < audioContent.size() && !alreadyExists; i++) {
                if ((audioContent.get(i)).getAudioName().equalsIgnoreCase(name)
                        && ((audioContent.get(i)).getOpName().equalsIgnoreCase(opName))) {
                    alreadyExists = true;
                }
            }
        }

        return alreadyExists;
    }

    /**
     * Method to generate a matrix with pseudo-randomly generated values
     * 
     * @param columns number of columns
     * @param rows    number of rows
     * @return matrix
     */
    public int[][] generateMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = r.nextInt(10);
            }
        }

        return matrix;
    }

    /**
     * Method to get playlist type
     * 
     * @param user     user index
     * @param playlist playlist index
     * @return playlist type
     */
    public int playlistType(int user, int playlist) {
        int type = 0;
        int songs = 0;
        int podcasts = 0;

        if (getPlaylistTotalElements(user, playlist) != 0) {
            for (int i = 0; i < getPlaylistTotalElements(user, playlist); i++) {
                if (clients.get(user) instanceof Standard) {
                    if (((Standard) clients.get(user)).getPlaylistElementType(playlist, i) == 1) {
                        songs++;
                    } else
                        podcasts++;
                } else if (((Premium) clients.get(user)).getPlaylistElementType(playlist, i) == 1) {
                    songs++;
                } else
                    podcasts++;
            }

            if (songs != 0) {
                if (podcasts == 0) {
                    type = 1;
                } else
                    type = 3;
            } else
                type = 2;
        }

        return type;
    }

    /**
     * Method to find the hidden code in a 6x6 matrix
     * 
     * @param matrix   6x6 matrix
     * @param playlist playlist index
     * @param client   user index
     * @return hidden code
     */
    public String findHiddenCode(int[][] matrix, int playlist, int client) {
        String code = "";
        int cont = 0;

        switch (playlistType(client, playlist)) {
            case 1:
                for (int i = 0; i < matrix.length; i++) {
                    if (i == 0 || i == matrix.length - 1) {
                        for (int j = 0; j < matrix[i].length; j++) {
                            code += matrix[i][(matrix[i].length - 1) - j];
                        }
                    } else
                        code += matrix[i][i];
                }
                break;
            case 2:
                for (int i = 0; i < matrix.length; i++) {
                    if (i == Math.floor((matrix.length - 1) / 2)) {
                        for (int j = 0; j < matrix[i].length; j++) {
                            code += matrix[i][j];
                        }
                    } else if (i == Math.floor(matrix.length / 2)) {
                        for (int j = 0; j < matrix[i].length; j++) {
                            code += matrix[i][(matrix.length - 1) - j];
                        }
                    } else {
                        code += matrix[i][0];
                    }
                }
                break;
            case 3:
                boolean sixteenChar = false;
                for (int j = (matrix.length - 1); j >= 0 && !sixteenChar; j--) {
                    if (code.length() == 16) {
                        sixteenChar = true;
                    }
                    for (int i = (matrix.length - 1); i >= 0; i--) {
                        cont = i + j;
                        if (cont % 2 != 0 && cont > 1) {
                            code += matrix[i][j];
                        }
                    }
                }

                break;
        }

        return code;
    }

    /**
     * Method to add client
     * 
     * @param name         user name
     * @param registerDate register date
     * @param var          ID if client is consumer or pic url if producer
     * @param op           selected option
     * @return result of the operation
     */
    public String addClient(String name, String registerDate, String var, int op) {
        String message = "";

        if (op == 1 || op == 2) {

            if (op == 1) {
                clients.add(new Standard(name, registerDate, var));
            } else {
                clients.add(new Premium(name, registerDate, var));
            }
            message = "Cliente añadido exitosamente!";

            activityLog.add(name + "-" + 0);

        } else if (op == 3 || op == 4) {

            if (op == 3) {
                clients.add(new Artist(name, registerDate, var));
            } else {
                clients.add(new ContentCreator(name, registerDate, var));
            }
            message = "Cliente añadido exitosamente!";
        }

        return message;
    }

    /**
     * @param name        podcast name
     * @param opName      op name
     * @param description description
     * @param picURL      pic url
     * @param length      podcast length
     * @param category    podcast category
     * @return result of the operation
     */
    public String addAudioContent(String name, String opName, String description, String picURL, String length,
            int category) {
        String message = null;

        if (audioAlreadyExists(name, opName)) {
            message = "Un podcast de características iguales ya existe";
        } else {
            Audio podcast = new Podcast(name, opName, description, picURL, length, 0, category);
            audioContent.add(podcast);
            message = "Podcast agregado exitosamente!";
        }

        return message;
    }

    /**
     * Method to add an audio
     * 
     * @param name      audio name
     * @param opName    op name
     * @param albumName album name
     * @param picURL    pic url
     * @param length    audio length
     * @param price     song price
     * @param genre     song genre
     * @return result of the operation
     */
    public String addAudioContent(String name, String opName, String albumName, String picURL, String length,
            double price,
            int genre) {
        String message = null;

        if (audioAlreadyExists(name, opName)) {
            message = "Una canción de características iguales ya existe";
        } else {
            Audio song = new Song(name, opName, albumName, picURL, length, price, genre, 0);
            audioContent.add(song);
            message = "Canción agregada exitosamente!";
        }

        return message;
    }

    /**
     * Method to buy a song
     * 
     * @param user  user index
     * @param index audio index
     * @return result of the operation
     */
    public String buyAudioContent(int user, int index) {

        String name = ((Song) audioContent.get(index)).getAudioName();
        String opName = ((Song) audioContent.get(index)).getOpName();
        String albumName = ((Song) audioContent.get(index)).getAlbumName();
        String picURL = ((Song) audioContent.get(index)).getPicURL();
        String length = ((Song) audioContent.get(index)).getLength();
        double price = ((Song) audioContent.get(index)).getPrice();
        int genre = ((Song) audioContent.get(index)).getSongGenre();
        int numRep = ((Song) audioContent.get(index)).getNumberOfReps();

        String message = "";

        if (clients.get(user) instanceof Standard) {
            if (((Standard) clients.get(user)).alreadyBought(name, opName)) {
                message = "Ya ha comprado esta canción";
            } else {
                if (((Standard) clients.get(user)).getAvailableSongPos() == -1) {
                    message = "No puede comprar más canciones. Tiene 100/100 canciones.";
                } else {
                    ((Standard) clients.get(user)).buySong(name, opName, albumName, picURL, length, price, genre,
                            numRep);
                    ((Song) audioContent.get(index)).refreshTimesSold();
                    message = "Canción comprada exitosamente. Tiene "
                            + (100 - ((Standard) clients.get(user)).getSongAvailability())
                            + "/100";
                }
            }
        } else {
            if (((Premium) clients.get(index)).alreadyBought(name, opName)) {
                message = "Ya ha comprado esta canción";
            } else {
                ((Premium) clients.get(user)).buySong(name, opName, albumName, picURL, length, price, genre, numRep);
                ((Song) audioContent.get(index)).refreshTimesSold();
                message = "Canción comprada exitosamente!";
            }
        }

        return message;
    }

    /**
     * Method to play an ad
     * 
     * @return pseudo-randomly chosen ad
     */
    public String playAd() {
        String[] ads = { "Nike - Just Do It.", "Coca-Cola - Open Happiness.",
                "M&Ms - Melts in Your Mouth, Not in Your Hand" };

        return ads[r.nextInt(ads.length - 1) + 1];
    }

    /**
     * @param user user index
     * @param name playlist name
     * @return result of the operation
     */
    public String createPlaylist(int user, String name) {
        String message = "";

        if (clients.get(user) instanceof Standard) {
            if (((Standard) clients.get(user)).getPlaylistAvailability() == 0) {
                message = "No puede crear más playlists, borre o edite una de las playlists existentes. 20/20 playlists creadas.";
            } else {
                ((Standard) clients.get(user)).createPlaylist(name);
                message = "Se ha creado la playlist exitosamente. "
                        + (20 - ((Standard) clients.get(user)).getPlaylistAvailability()) + "/20 playlists creadas.";
            }
        } else {
            ((Premium) clients.get(user)).createPlaylist(name);
            message = "Se ha creado la playlist exitosamente!";
        }

        return message;
    }

    /**
     * Method to add an audio to a playlist
     * 
     * @param user     user index
     * @param playlist playlist index
     * @param index    audio index
     * @return result of the operation
     */
    public String addAudioToPlaylist(int user, int playlist, int index) {

        if (clients.get(user) instanceof Standard) {
            ((Standard) clients.get(user)).addAudioToPlaylist(audioContent.get(index), playlist);
        } else
            ((Premium) clients.get(user)).addAudioToPlaylist(audioContent.get(index), playlist);

        return "Audio agregado exitosamente!";
    }

    /**
     * Method to remove an audio from a playlist
     * 
     * @param user     user index
     * @param playlist playlist index
     * @param index    audio index
     * @return result of the operation
     */
    public String removeAudioFromPlaylist(int user, int playlist, int index) {
        String message = "";

        if (clients.get(user) instanceof Standard) {
            ((Standard) clients.get(user)).removeAudioFromPlaylist(index, playlist);
        } else
            ((Premium) clients.get(user)).removeAudioFromPlaylist(index, playlist);

        return message;
    }

    /**
     * Method to move an audio
     * 
     * @param user     user index
     * @param playlist playlist index
     * @param audioPos audio index
     * @param newPos   new position
     * @return result of the operation
     */
    public String moveAudio(int user, int playlist, int audioPos, int newPos) {

        if (clients.get(user) instanceof Standard) {
            ((Standard) clients.get(user)).moveAudio(playlist, audioPos, newPos);
        } else {
            ((Premium) clients.get(user)).moveAudio(playlist, audioPos, newPos);
        }

        return "Audio movido de la posición " + audioPos + " a la posición " + newPos + " exitosamente!";
    }

    /**
     * Method to play audio
     * 
     * @param audioIndex audio index
     * @param user       user index
     * @return String with the name and op name of the chosen song
     */
    public String playAudio(int audioIndex, int user) {
        String[] data = getAudioInfo(audioIndex).split("-");
        audioContent.get(audioIndex).refreshNumberOfReps();
        updateUserPlayedSongs(getUserName(user));
        int type = -1;

        if (audioContent.get(audioIndex) instanceof Song) {
            type = ((Song) audioContent.get(audioIndex)).getSongGenre();
            ((Consumer) clients.get(user)).updateGenreReps(type - 1);
        } else {
            type = ((Podcast) audioContent.get(audioIndex)).getCategory();
            ((Consumer) clients.get(user)).updateCategoryReps(type - 1);
        }

        ((Producer) clients.get(getOriginalPoster(audioIndex))).refreshNumberOfReps();

        return "Escuchando " + data[0] + "de" + data[1];
    }

    /**
     * Method to play audio
     * 
     * @param user     user index
     * @param playlist playlist index
     * @param index    audio index
     * @return String with the name and op name of the chosen song
     */
    public String playAudio(int user, int playlist, int index) {
        String[] data = getPlaylistElement(user, playlist, index).split("-");
        int audio;

        updateUserPlayedSongs(getUserName(user));

        int type = -1;

        if (clients.get(user) instanceof Standard) {
            audio = getAudioIndex(((Standard) clients.get(user)).getAudio(data[0], data[data.length - 1], playlist));
            audioContent.get(audio).refreshNumberOfReps();

            if (audioContent.get(audio) instanceof Song) {
                type = ((Song) audioContent.get(audio)).getSongGenre();
                ((Consumer) clients.get(user)).updateGenreReps(type - 1);
            } else {
                type = ((Podcast) audioContent.get(audio)).getCategory();
                ((Consumer) clients.get(user)).updateCategoryReps(type - 1);
            }

            ((Producer) clients.get(getOriginalPoster(audio))).refreshNumberOfReps();
        } else {
            audio = getAudioIndex(((Premium) clients.get(user)).getAudio(data[0], data[data.length - 1], playlist));
            audioContent.get(audio).refreshNumberOfReps();

            if (audioContent.get(audio) instanceof Song) {
                type = ((Song) audioContent.get(audio)).getSongGenre();
                ((Consumer) clients.get(user)).updateGenreReps(type - 1);
            } else {
                type = ((Podcast) audioContent.get(audio)).getCategory();
                ((Consumer) clients.get(user)).updateCategoryReps(type - 1);
            }

            ((Producer) clients.get(getOriginalPoster(audio))).refreshNumberOfReps();
        }

        return "Escuchando " + data[0] + " de " + data[1];
    }

    /**
     * Method to create a position array. Used to create an array filled with "-1"
     * instead of "0"
     * 
     * @param size
     * @return position array
     */
    public int[] createPositionArray(int size) {
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }

        return array;
    }

    /**
     * @param positionArray array of int values
     * @param position      integer value to compare
     * @return whether if the occurrence in the specified position already exists in
     *         the array
     */
    public boolean alreadyExists(int[] positionArray, int position) {
        boolean match = false;
        int i = 0;

        while (i < positionArray.length && !match) {
            if (positionArray[i] != -1) {
                if (positionArray[i] == position) {
                    match = true;
                }
            }
        }

        return match;
    }

    /**
     * Method to get the top 5 artists
     * 
     * @return int[] array with the top 5 artists
     */
    public String[] topArtists() {
        int[] posArray = createPositionArray(5);
        String[] top = new String[5];
        int max = 0;
        int pos = 0;
        for (int i = 0; i < posArray.length; i++) {
            for (int j = 0; j < clients.size(); j++) {
                if (clients.get(i) instanceof Artist) {
                    if (alreadyExists(posArray, j)) {
                        j++;
                    } else {
                        if (((Producer) clients.get(i)).getNumberOfReps() > max) {
                            max = ((Producer) clients.get(i)).getNumberOfReps();
                            pos = j;
                        }
                    }
                }
            }
            posArray[i] = pos;
            top[i] = i + ") " + getUserName(pos);
        }

        return top;
    }

    /**
     * Method to get the top 5 content creators
     * 
     * @return String[] array with the top 5 content creators
     */
    public String[] topContentCreators() {
        int[] posArray = createPositionArray(5);
        String[] top = new String[5];
        int max = 0;
        int pos = 0;
        for (int i = 0; i < posArray.length; i++) {
            for (int j = 0; j < clients.size(); j++) {
                if (clients.get(i) instanceof ContentCreator) {
                    if (alreadyExists(posArray, j)) {
                        j++;
                    } else {
                        if (((Producer) clients.get(i)).getNumberOfReps() > max) {
                            max = ((Producer) clients.get(i)).getNumberOfReps();
                            pos = j;
                        }
                    }
                }
            }
            posArray[i] = pos;
            top[i] = i + ") " + getUserName(pos);
        }

        return top;
    }

    /**
     * Method to get the top 10 songs
     * 
     * @return String[] array with the top 10 songs
     */
    public String[] topSongs() {
        int[] posArray = createPositionArray(10);
        String[] top = new String[5];
        int max = 0;
        int pos = 0;
        for (int i = 0; i < posArray.length; i++) {
            for (int j = 0; j < clients.size(); j++) {
                if (clients.get(i) instanceof ContentCreator) {
                    if (alreadyExists(posArray, j)) {
                        j++;
                    } else {
                        if (((Producer) clients.get(i)).getNumberOfReps() > max) {
                            max = ((Producer) clients.get(i)).getNumberOfReps();
                            pos = j;
                        }
                    }
                }
            }
            posArray[i] = pos;
            top[i] = (((Song) audioContent.get(pos)).getNumberOfReps() == 1)
                    ? getAudioInfo(i) + ((Song) audioContent.get(pos)).getSongGenreName() + "-"
                            + "- 1 reproducción"
                    : getAudioInfo(i) + "-" + ((Song) audioContent.get(pos)).getSongGenreName() + "-"
                            + ((Song) audioContent.get(pos)).getNumberOfReps()
                            + " reproducciones";
        }

        return top;
    }

    /**
     * Method to get the top 10 podcasts
     * 
     * @return String[] array with the top 10 podcasts
     */
    public String[] topPodcasts() {
        int[] posArray = createPositionArray(10);
        String[] top = new String[5];
        int max = 0;
        int pos = 0;
        for (int i = 0; i < posArray.length; i++) {
            for (int j = 0; j < audioContent.size(); j++) {
                if (audioContent.get(i) instanceof Podcast) {
                    if (alreadyExists(posArray, j)) {
                        j++;
                    } else {
                        if (((Song) audioContent.get(i)).getNumberOfReps() > max) {
                            max = ((Song) audioContent.get(i)).getNumberOfReps();
                            pos = j;
                        }
                    }
                }
            }
            posArray[i] = pos;
            top[i] = (((Podcast) audioContent.get(pos)).getNumberOfReps() == 1)
                    ? getAudioInfo(i) + ((Podcast) audioContent.get(pos)).getCategoryName() + "-"
                            + "- 1 reproducción"
                    : getAudioInfo(i) + "-" + ((Podcast) audioContent.get(pos)).getCategoryName() + "-"
                            + ((Podcast) audioContent.get(pos)).getNumberOfReps()
                            + " reproducciones";
        }

        return top;
    }
}