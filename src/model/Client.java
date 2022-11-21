public class Client {

    private String nickname;
    private String vincDate;

    public Client(String nickname, String vincDate) {
        this.nickname = nickname;
        this.vincDate = vincDate;
    }

    /**
     * Method to get the client nickname
     * 
     * @return String with the client nickname
     */
    public String getNickname() {
        return nickname;
    }

}
