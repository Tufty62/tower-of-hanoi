import java.util.ArrayList;

public class Peg {

    private final ArrayList<Integer> peg = new ArrayList<>();

    public int get_top_disc() {
        if (this.peg.isEmpty()) {
            return -1;
        } 
        return this.peg.getLast();
    }

    public boolean can_add_disc(int disc) {
        if (this.peg.isEmpty()) {
            return true;
        }

        else if (this.peg.getLast() < disc) {
            System.err.println("Can't place disc there");
            return false;
        }

        return true;
    }

    public void add_disc(int disc) {
        if (can_add_disc(disc)){
            this.peg.add(disc);
        }
    }

    public void remove_disc() {
        this.peg.removeLast();
    }

}
