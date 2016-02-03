package morse.structural;

import morse.behavioral.Duration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Garance on 26/01/2016.
 */
public class Alphabet {
    private static Map<String, Letter> letters = new HashMap<>();

    public Alphabet() {
    }

    public static void addLetter(String letterName, Letter letter) {
        letters.put(letterName, letter);
    }

    public static Letter getMorseLetter(String letter) {
        if (letters == null || letters.isEmpty()) {
            fillAlphabet();
        }
        return (letters.get(letter.toUpperCase()) != null) ? letters.get(letter.toUpperCase()) : letters.get("space");
    }

    public Map<String, Letter> getLetters() {
        return letters;
    }

    public void setLetters(Map<String, Letter> letters) {
        this.letters = letters;
    }

    private static void fillAlphabet() {

        Dash dash = new Dash("dash", Duration.LONG, "-");
        Dot dot = new Dot("dot", Duration.SHORT, ".");
        Space space = new Space("space", Duration.SUPERLONG, " ");

        Letter spaceLetter = new Letter();
        spaceLetter.addMorseUnit(space);
        addLetter("space", spaceLetter);

        Letter a = new Letter();
        a.addMorseUnit(dot);
        a.addMorseUnit(dash);
        addLetter("A", a);

        Letter b = new Letter();
        b.addMorseUnit(dash);
        b.addMorseUnit(dot);
        b.addMorseUnit(dot);
        b.addMorseUnit(dot);
        addLetter("B", b);

        Letter c = new Letter();
        c.addMorseUnit(dash);
        c.addMorseUnit(dot);
        c.addMorseUnit(dash);
        c.addMorseUnit(dot);
        addLetter("C", c);

        Letter d = new Letter();
        d.addMorseUnit(dash);
        d.addMorseUnit(dot);
        d.addMorseUnit(dot);
        addLetter("D", d);

        Letter e = new Letter();
        e.addMorseUnit(dot);
        addLetter("E", e);

        Letter f = new Letter();
        d.addMorseUnit(dot);
        d.addMorseUnit(dot);
        d.addMorseUnit(dash);
        d.addMorseUnit(dot);
        addLetter("F", f);

        Letter g = new Letter();
        g.addMorseUnit(dash);
        g.addMorseUnit(dash);
        g.addMorseUnit(dot);
        addLetter("G", g);

        Letter h = new Letter();
        h.addMorseUnit(dot);
        h.addMorseUnit(dot);
        h.addMorseUnit(dot);
        h.addMorseUnit(dot);
        addLetter("H", h);

        Letter i = new Letter();
        i.addMorseUnit(dot);
        i.addMorseUnit(dot);
        addLetter("I", i);

        Letter j = new Letter();
        j.addMorseUnit(dot);
        j.addMorseUnit(dash);
        j.addMorseUnit(dash);
        j.addMorseUnit(dash);
        addLetter("J", j);

        Letter k = new Letter();
        k.addMorseUnit(dash);
        k.addMorseUnit(dot);
        k.addMorseUnit(dash);
        addLetter("K", k);

        Letter l = new Letter();
        l.addMorseUnit(dot);
        l.addMorseUnit(dash);
        l.addMorseUnit(dot);
        l.addMorseUnit(dot);
        addLetter("L", l);

        Letter m = new Letter();
        m.addMorseUnit(dash);
        m.addMorseUnit(dash);
        addLetter("M", m);

        Letter n = new Letter();
        n.addMorseUnit(dash);
        n.addMorseUnit(dot);
        addLetter("N", n);

        Letter o = new Letter();
        o.addMorseUnit(dash);
        o.addMorseUnit(dash);
        o.addMorseUnit(dash);
        addLetter("O", o);

        Letter p = new Letter();
        p.addMorseUnit(dot);
        p.addMorseUnit(dash);
        p.addMorseUnit(dash);
        p.addMorseUnit(dot);
        addLetter("P", p);

        Letter q = new Letter();
        q.addMorseUnit(dash);
        q.addMorseUnit(dash);
        q.addMorseUnit(dot);
        q.addMorseUnit(dash);
        addLetter("Q", q);

        Letter r = new Letter();
        r.addMorseUnit(dot);
        r.addMorseUnit(dash);
        r.addMorseUnit(dot);
        addLetter("R", r);

        Letter s = new Letter();
        s.addMorseUnit(dot);
        s.addMorseUnit(dot);
        s.addMorseUnit(dot);
        addLetter("S", s);

        Letter t = new Letter();
        t.addMorseUnit(dash);
        addLetter("T", t);

        Letter u = new Letter();
        u.addMorseUnit(dot);
        u.addMorseUnit(dot);
        u.addMorseUnit(dash);
        addLetter("U", u);

        Letter v = new Letter();
        v.addMorseUnit(dot);
        v.addMorseUnit(dot);
        v.addMorseUnit(dot);
        v.addMorseUnit(dash);
        addLetter("V", v);

        Letter w = new Letter();
        w.addMorseUnit(dot);
        w.addMorseUnit(dash);
        w.addMorseUnit(dash);
        addLetter("W", w);

        Letter x = new Letter();
        x.addMorseUnit(dash);
        x.addMorseUnit(dot);
        x.addMorseUnit(dot);
        x.addMorseUnit(dash);
        addLetter("X", x);

        Letter y = new Letter();
        y.addMorseUnit(dash);
        y.addMorseUnit(dot);
        y.addMorseUnit(dash);
        y.addMorseUnit(dash);
        addLetter("Y", y);

        Letter z = new Letter();
        z.addMorseUnit(dash);
        z.addMorseUnit(dash);
        z.addMorseUnit(dot);
        z.addMorseUnit(dot);
        addLetter("Z", z);
    }
}
