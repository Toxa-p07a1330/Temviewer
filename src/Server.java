public class Server
{
    public static void main(String[] args) {
        TelnetServer telnetServer = new TelnetServer();
        telnetServer.run(23);
        try {
            Thread.sleep(100*60*60*24);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        telnetServer.stop();
    }
}
