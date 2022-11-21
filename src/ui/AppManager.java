import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AppManager {

    private static DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static ArrayList<String> purchaseLog = new ArrayList<String>();
    private static Scanner s = new Scanner(System.in);
    private static StreamingApp app = new StreamingApp();

    /**
     * @param args
     */
    public static void main(String[] args) {

        test();
        int op = 0;
        boolean finish = false;

        do {
            System.out.println("Seleccione una de las siguientes opciones:");
            System.out.println("");
            System.out.println("    1) Registrar usuario, tanto como consumidores como productores");
            System.out.println("    2) Añadir canción o podcast");
            System.out.println("    3) Crear playlist");
            System.out.println("    4) Editar playlist");
            System.out.println("    5) Compartir playlist");
            System.out.println("    6) Reproducir audio");
            System.out.println("    7) Comprar canción");
            System.out.println("    8) Desplegar reporte de datos");
            System.out.println("    0) Cerrar menú");
            System.out.println("");
            op = s.nextInt();
            s.nextLine();

            switch (op) {
                case 1:
                    registerClient();
                    break;
                case 2:
                    addAudioContent();
                    break;
                case 3:
                    createPlaylist();
                    break;
                case 4:
                    editPlaylist();
                    break;
                case 5:
                    sharePlaylist();
                    break;
                case 6:
                    playAudio();
                    break;
                case 7:
                    buySong();
                    break;
                case 8:
                    displayDataReport();
                    break;
                case 0:
                    finish = true;
                    break;
            }

        } while (finish == false);

    }

    /**
     * Method to register clients. Used to register both main types of clients:
     * Consumers and Producers.
     */
    public static void registerClient() {

        System.out.println("Digite el nombre:");
        String name = s.nextLine();
        System.out.println("");

        System.out.println("¿Qué tipo de cliente es?");
        System.out.println("");
        System.out.println("Consumidor:");
        System.out.println("    1) Estándar");
        System.out.println("    2) Premium");
        System.out.println("Productor:");
        System.out.println("    3) Artista");
        System.out.println("    4) Creador de Contenido");
        System.out.println("");
        int type = s.nextInt();

        LocalDate ld = LocalDate.now();
        String registerDate = ld.format(dt);

        while (type < 1 || type > 4) {
            System.out.println("Seleccione una opción válida:");
            type = s.nextInt();
        }

        s.nextLine();

        if (type == 1 || type == 2) {
            System.out.println("Digite el ID:");
            String id = s.nextLine();
            System.out.println(app.addClient(name, registerDate, id, type));
            System.out.println("");
        } else {
            System.out.println("Digite el URL de la imagen:");
            String picURL = s.nextLine();
            System.out.println(app.addClient(name, registerDate, picURL, type));
            System.out.println("");
        }
    }

    /**
     * Method to display all registered consumers with their numerical ID
     */
    public static void consumerClientList() {
        System.out.println("Digite el ID del usuario que desea usar:");
        System.out.println("");
        for (int i = 0; i < app.getCurrentUsers(); i++) {
            if (app.isConsumer(i)) {
                System.out.println("ID '" + i + "'" + ": " + app.getUserName(i));
            }
        }
    }

    /**
     * Method to display all registered producers with their numerical ID
     */
    public static void producerClientList() {
        System.out.println("Digite el ID del usuario que desea usar:");
        System.out.println("");
        for (int i = 0; i < app.getCurrentUsers(); i++) {
            if (!app.isConsumer(i)) {
                System.out.println("ID '" + i + "'" + ": " + app.getUserName(i));
            }
        }
    }

    /**
     * Method to display a list of a specific user playlists
     * 
     * @param user position of the specified user in the clients ArrayList
     */
    public static void playlistList(int user) {
        System.out.println("Digite el ID de la playlist que desea usar:");
        System.out.println("");
        for (int i = 0; i < app.getConsumerNumberOfPlaylists(user); i++) {
            System.out.println("ID '" + i + "'" + ": " + app.getConsumerPlaylist(user, i));
        }
    }

    public static void test() {
        LocalDate ld = LocalDate.now();
        String registerDate = ld.format(dt);

        app.addClient("Andrés Cepeda", registerDate, "sadadsadad", 3);
        app.addClient("Manuel Medrano", registerDate, "sdasdasdwwwwww", 3);
        app.addClient("Natalia Angulo", registerDate, "231232131", 1);
        app.addClient("Pedro Navaja", registerDate, "432432434", 2);
        app.addClient("Chuck Merd", registerDate, "xzcczvcxv", 4);
        app.addClient("Andrés Medicis", registerDate, "sadadssadsadad", 4);
        app.addClient("Manuel Cedro", registerDate, "ssssasdxxcccxz", 3);

        app.addAudioContent("Embrujo", "Andrés Cepeda", "Tour", "adkasnkodsndsa",
                "3:14", 200, 1); // cancion
        app.addAudioContent("Mentira", "Manuel Medrano", "Poeta", "sadasdada",
                "2:14", 200, 1); // cancion
        app.addAudioContent("Estado de los videojuegos", "Chuck Merd",
                "Recorrido de la historia", "sadasdfasfaf",
                "1:34:45", 3); // podcast

        app.createPlaylist(2, "popsito");
        app.createPlaylist(2, "pod");
        app.addAudioToPlaylist(2, 0, 0);
        app.addAudioToPlaylist(2, 0, 2);
        app.addAudioToPlaylist(2, 1, 2);

        System.out.println(app.getPlaylistElement(2, 0, 1));
    }

    /**
     * Method to add songs or podcasts to the app. Uses two overloaded methods
     * "addAudioContent" to register each type of audio.
     */
    public static void addAudioContent() {
        String opName = null;
        String var = null;
        boolean exists = false;

        System.out.println("¿Desea ver la lista de usuarios productores?");
        System.out.println("Digite '1' para ver la lista o cualquier otro número para omitirla:");
        int seeList = s.nextInt();
        System.out.println("");

        while (!exists) {

            if (seeList == 1) {
                producerClientList();
                System.out.println("");
            }

            s.nextLine();
            System.out.println("Escriba el ID del productor:");
            int user = s.nextInt();
            opName = app.getUserName(user);

            if (app.getProducerType(opName) != -1) {
                exists = true;
            }

            System.out.println("");
        }

        int type = app.getProducerType(opName);
        s.nextLine();

        System.out.println("Digite el nombre del audio:");
        String audioName = s.nextLine();
        System.out.println("Inserte el URL de la imagen:");
        String picURL = s.nextLine();

        System.out.println("Digite la duración:");
        System.out.println("Horas:");
        int hours = s.nextInt();

        System.out.println("Minutos:");
        int minutes = s.nextInt();

        while (minutes < 0 || minutes > 60) {
            System.out.println("Digite un número de minutos válidos:");
            minutes = s.nextInt();
        }
        System.out.println("Segundos:");
        int seconds = s.nextInt();

        while (seconds < 0 || seconds > 60) {
            System.out.println("Digite un número de segundos válidos:");
            seconds = s.nextInt();
        }

        String length = (hours == 0) ? minutes + ":" + seconds : hours + ":" + minutes + ":" + seconds;
        int audioType = 0;
        s.nextLine();

        if (type == 1) {
            System.out.println("Digite el nombre del album de la canción:");
            var = s.nextLine();

            System.out.println("Seleccione el género de la canción:");
            System.out.println("    1) Rock");
            System.out.println("    2) Pop");
            System.out.println("    3) Trap");
            System.out.println("    4) House");
            audioType = s.nextInt();

            while (audioType < 1 || audioType > 4) {
                System.out.println("Seleccione una opción válida:");
                audioType = s.nextInt();
            }

            System.out.println("Digite el precio de la canción:");
            double price = s.nextDouble();
            System.out.println("");

            System.out.println(app.addAudioContent(audioName, opName, var, picURL, length, price, audioType));
        } else {
            System.out.println("Seleccione la categoría del podcast:");
            System.out.println("    1) Política");
            System.out.println("    2) Entretenimiento");
            System.out.println("    3) Videojuegos");
            System.out.println("    4) Moda");
            System.out.println("");
            audioType = s.nextInt();

            while (audioType < 1 || audioType > 4) {
                System.out.println("Seleccione una opción válida:");
                audioType = s.nextInt();
            }

            System.out.println("Digite la descripción del podcast:");
            var = s.nextLine();
            System.out.println("");
            System.out.println(app.addAudioContent(audioName, opName, var, picURL, length, audioType));
            System.out.println("");
        }
    }

    /**
     * Method to create playlists. Need user ID input to assign the playlist to that
     * user.
     */
    public static void createPlaylist() {
        consumerClientList();
        int user = s.nextInt();
        s.nextLine();
        System.out.println("Digite el nombre de la playlist:");
        String name = s.nextLine();
        System.out.println(app.createPlaylist(user, name));
        System.out.println("");
    }

    /**
     * Method to edit playlist that offer three options:
     * - Add audio
     * - Move audio: Moves one audio to an specified position. E.g. Swap audio in
     * position 1 to 2
     * - Remove audio
     */
    public static void editPlaylist() {

        consumerClientList();
        int user = s.nextInt();

        playlistList(user);
        int playlist = s.nextInt();
        System.out.println("");

        while (playlist < 0 || playlist > app.getConsumerNumberOfPlaylists(user)) {
            System.out.println("Seleccione una playlist válida:");
            playlist = s.nextInt();
            System.out.println("");
        }

        System.out.println("    1) Añadir audio");
        System.out.println("    2) Mover elemento");
        System.out.println("    3) Eliminar elemento");
        System.out.println("");
        int op = s.nextInt();
        System.out.println("");

        while (op < 1 || op > 3) {
            System.out.println("Seleccione una opción válida:");
            op = s.nextInt();
            System.out.println("");
        }

        switch (op) {
            case 1:
                for (int i = 0; i < app.currentUploadedAudioContent(); i++) {
                    System.out.println("ID '" + i + "'" + ": " + app.getAudioInfo(i));
                }

                System.out.println("");
                System.out.println("Seleccione el audio que desea agregar:");
                int audio = s.nextInt();
                System.out.println("");

                while (audio < 0 || audio > app.currentUploadedAudioContent()) {
                    System.out.println("Seleccione una opción válida:");
                    audio = s.nextInt();
                    System.out.println("");
                }

                System.out.println(app.addAudioToPlaylist(user, playlist, audio));
                System.out.println("");

                break;

            case 2:
                for (int i = 0; i < app.getPlaylistTotalElements(user, playlist); i++) {
                    System.out.println("ID '" + i + "': " + app.getPlaylistElement(user, playlist, i));
                }

                System.out.println("");
                System.out.println("Seleccione el audio que desea mover:");
                audio = s.nextInt();

                while (audio < 0 || audio > app.getPlaylistTotalElements(user, playlist)) {
                    System.out.println("Seleccione una opción válida:");
                    audio = s.nextInt();
                    System.out.println("");
                }

                System.out.println("¿A qué posición desea moverlo?");
                int pos = s.nextInt();

                while (pos < 0 || pos > app.getPlaylistTotalElements(user, playlist)) {
                    System.out.println("Seleccione una posición válida:");
                    pos = s.nextInt();
                    System.out.println("");
                }

                System.out.println(app.moveAudio(user, playlist, audio, pos));
                System.out.println("");

                break;

            case 3:
                for (int i = 0; i < app.getPlaylistTotalElements(user, playlist); i++) {
                    System.out.println("ID '" + i + "': " + app.getPlaylistElement(user, playlist, i));
                }

                System.out.println("");
                System.out.println("Seleccione el audio que desea eliminar:");
                audio = s.nextInt();

                while (audio < 0 || audio > app.getPlaylistTotalElements(user, playlist)) {
                    System.out.println("Seleccione una opción válida:");
                    audio = s.nextInt();
                    System.out.println("");
                }

                System.out.println(app.removeAudioFromPlaylist(user, playlist, audio));
                System.out.println("");

                break;
        }
    }

    /**
     * Method to share playlists. Uses a 6x6 pseudo-randomly generated matrix of
     * integer values to create a 16 digit code that depends on whether the playlist
     * has only songs, podcasts or has both. (@see docs/code.jpg)
     */
    public static void sharePlaylist() {

        int[][] matrix = app.generateMatrix(6, 6);
        System.out.println("");
        consumerClientList();
        int client = s.nextInt();
        playlistList(client);
        int playlist = s.nextInt();
        System.out.println("");

        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix[j].length; i++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }

        System.out.println("");
        System.out.println(app.findHiddenCode(matrix, playlist, client));
        System.out.println("");
    }

    /**
     * Method to play songs and podcasts. Will play ads to if the user playing an
     * audio is a standard consumer.
     */
    public static void playAudio() {

        consumerClientList();
        System.out.println("");
        int user = s.nextInt();
        System.out.println("");

        while ((user < 0 || user > app.getCurrentUsers()) || !app.isConsumer(user)) {
            System.out.println("Seleccione un usuario válido:");
            user = s.nextInt();
            System.out.println("");
        }

        System.out.println("Seleccione una de las siguientes opciones:");
        System.out.println("    1) Reproducir canción o podcast individual");
        System.out.println("    2) Reproducir playlist");
        System.out.println("");
        int op = s.nextInt();
        System.out.println("");

        while (op != 1 && op != 2) {
            System.out.println("Seleccione una opción válida:");
            op = s.nextInt();
            System.out.println("");
        }

        String[] data = new String[2];

        switch (op) {
            case 1:
                for (int i = 0; i < app.currentUploadedAudioContent(); i++) {
                    System.out.println("ID '" + i + "': " + app.getAudioInfo(i));
                }

                System.out.println("");
                int audioID = s.nextInt();
                System.out.println("");

                while (audioID < 0 || audioID > app.currentUploadedAudioContent()) {
                    System.out.println("Seleccione una opción válida:");
                    audioID = s.nextInt();
                    System.out.println("");
                }

                if (app.isStandardUser(user)) {
                    if (app.isAudioPodcast(audioID) || app.getUserPlayedSongs(app.getUserName(user)) % 2 == 0) {
                        System.out.println(app.playAd());
                        System.out.println("");

                    }
                }

                System.out.println(app.playAudio(audioID, user));

                for (int j = 0; j < 8; j++) {
                    System.out.print(". ");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println("");
                System.out.println("");

                break;
            case 2:
                playlistList(user);
                System.out.println("");
                int playlist = s.nextInt();

                while (playlist < 0 || playlist > app.getPlaylistTotalElements(user, playlist)) {
                    System.out.println("Seleccione una playlist válida:");
                    playlist = s.nextInt();
                    System.out.println("");
                }

                System.out.println("Reproduciendo '" + app.getConsumerPlaylist(user, playlist) + "'");
                System.out.println(".\n.\n.\n");
                int playedSongs = 0;

                for (int i = 0; i < app.getPlaylistTotalElements(user, playlist); i++) {

                    if (app.isStandardUser(user)) {
                        data = app.getPlaylistElement(user, playlist, i).split("-");
                        if (app.isAudioPodcast(app.searchAudioContent(data[0], data[1]))) {
                            System.out.println(app.playAd());
                        } else {
                            playedSongs++;
                            if (playedSongs % 2 == 0) {
                                System.out.println(app.playAd());
                            }
                        }
                    }

                    System.out.println(app.playAudio(user, playlist, i));

                    for (int j = 0; j < 8; j++) {
                        System.out.print(". ");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    System.out.println("");
                }

                break;
        }

    }

    /**
     * Method to buy songs. Has some restrictions if the user is a standard
     * consumer.
     */
    public static void buySong() {

        consumerClientList();
        System.out.println("");
        int user = s.nextInt();
        System.out.println("");

        while ((user < 0 || user > app.getCurrentUsers()) || !app.isConsumer(user)) {
            System.out.println("Seleccione un usuario válido:");
            user = s.nextInt();
            System.out.println("");
        }

        System.out.println("¿Qué canción desea comprar?");
        System.out.println("");

        for (int i = 0; i < app.currentUploadedAudioContent(); i++) {
            if (!app.isAudioPodcast(i)) {
                System.out.println("ID '" + i + "': " + app.getAudioInfo(i));
            }
        }

        System.out.println("");
        int song = s.nextInt();
        System.out.println("");

        while (song < 0 || song > app.currentUploadedAudioContent()) {
            System.out.println("Seleccione una canción válida:");
            song = s.nextInt();
            System.out.println("");

        }

        System.out.println(app.buyAudioContent(user, song));
        if (!app.buyAudioContent(user, song).equals("No puede comprar más canciones. Tiene 100/100 canciones.")) {
            LocalDate ld = LocalDate.now();
            String purchaseDate = ld.format(dt);
            purchaseLog.add(app.getUserID(user) + "-" + app.getAudioInfo(song) + "-" + purchaseDate);
        }

    }

    /**
     * Method to display some statistics of the app:
     * - Accumulated total of reproductions
     * - Most listened genre of all the platform and of a random consumer user
     * - Most listened category of all the platform and of a random consumer user
     * - Top 5 artists and content creators
     * - Top 10 songs and podcasts
     * - Of each genre, the number of sales and the revenue of those sales
     * - Best seller song
     */
    public static void displayDataReport() {
        System.out.println("Número total de reproducciones en toda la plataforma: " + app.getTotalReps());
        System.out.println(app.getMostListenedGenre());
        System.out.println(app.getUserMostListenedGenre());
        System.out.println(app.getMostListenedCategory());
        System.out.println(app.getUserMostListenedCategory());
        System.out.println("");

        if (app.countArtists() < 5) {
            System.out.println("No hay suficientes artistas para mostrar el top 5.");
        } else {
            String[] topArtists = app.topArtists();
            System.out.println("Top 5 Artistas:");
            System.out.println("");

            for (int i = 0; i < topArtists.length; i++) {
                System.out.println((i + 1) + ") " + topArtists[i]);
            }
            System.out.println("");
        }

        if (app.countContentCreators() < 5) {
            System.out.println("No hay suficientes creadores de contenido para mostrar el top 5.");
        } else {
            String[] topContentCreators = app.topContentCreators();
            System.out.println("Top 5 Creadores de Contenido:");
            System.out.println("");

            for (int i = 0; i < topContentCreators.length; i++) {
                System.out.println((i + 1) + ") " + topContentCreators[i]);
            }
            System.out.println("");
        }

        if (app.countSongs() < 10) {
            System.out.println("No hay suficientes canciones para mostrar el top 10.");
        } else {
            String[] topSongs = app.topSongs();
            System.out.println("Top 5 Canciones:");
            System.out.println("");

            for (int i = 0; i < topSongs.length; i++) {
                System.out.println((i + 1) + ") " + topSongs[i]);
            }
        }
        if (app.countPodcasts() < 10) {
            System.out.println("No hay suficientes podcasts para mostrar el top 10.");
        } else {
            String[] topPodcasts = app.topPodcasts();
            System.out.println("Top 5 Podcasts:");
            System.out.println("");

            for (int i = 0; i < topPodcasts.length; i++) {
                System.out.println((i + 1) + ") " + topPodcasts[i]);
            }
        }

        System.out.println("");
        String[] genre = { "Rock", "Pop", "Trap", "House" };

        for (int i = 0; i < genre.length; i++) {
            System.out.println(genre[i] + ": " + app.getMostSoldSongByGenre(i + 1));
        }
        System.out.println("");
        System.out.println(app.mostSoldSong());
        System.out.println("");
    }

}
