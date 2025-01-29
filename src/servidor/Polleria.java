
package servidor;

public class Polleria {
    private int pollos = 100;
    private int patatas = 100;

    public int getPollos() {
        return pollos;
    }

    public void setPollos(int pollos) {
        this.pollos -= pollos;
    }

    public int getPatatas() {
        return patatas;
    }

    public void setPatatas(int patatas) {
        this.patatas -= patatas;
    }

}