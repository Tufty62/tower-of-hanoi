package Game;
import java.util.ArrayList;

public class Hanoi {

    private final ArrayList<Peg> pegs = new ArrayList<>();
    private final int discs;

    public Hanoi(int num_pegs, int discs) {
        for (int i = 0; i < num_pegs; i++) {
            this.pegs.add(new Peg());
        }

        this.discs = discs;

        for (int i = discs; i > 0; i--) {
            this.pegs.getFirst().add_disc(i);
        }
    }

    public void move_disc(Peg peg_from, Peg peg_to, int disc) {

        if (!this.pegs.contains(peg_from) || !this.pegs.contains(peg_to)) {
            System.err.println("Peg does not exist");
            return;
        } 

        if (disc > this.discs || disc < 1) {
            System.err.println("This disc does not exist");
            return;
        }

        if (peg_from == peg_to) {
            System.err.println("Redundant Move");
            return;
        }

        if (peg_from.get_top_disc() == -1) {
            System.err.println("No disc exsists on this peg");
            return;
        }

        if (disc != peg_from.get_top_disc()) {
            System.err.println("Can't move this disc");
            return;
        }

        if (peg_to.can_add_disc(disc)){
            peg_from.remove_disc();
            peg_to.add_disc(disc);
        }
    }
}