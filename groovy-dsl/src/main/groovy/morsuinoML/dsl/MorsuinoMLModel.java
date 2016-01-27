package morsuinoML.dsl;

import groovy.lang.Binding;

import java.util.ArrayList;
import java.util.List;

public class MorsuinoMLModel {
    private List<String> morseActuators = new ArrayList<>();
    private List<String> actuatorDeclarations = new ArrayList<>();
    private static final String SHORT = "1.s";
    private static final String LONG = "3.s";
    private static final String SUPERLONG = "7.s";


    private MorsuinoMLAlphabet alphabet = new MorsuinoMLAlphabet();
    private List<MorsuinoMLState> states = new ArrayList<>();
    //This list contains a state name and its duration for the transition. For the whole message

    private String statesDeclaration;
    private String transitionDeclaration;
    private String lowEOS;
    private String highEOS;


    private Binding binding;

    public MorsuinoMLModel(Binding binding) {
        this.binding = binding;
        fillAlphabet();
    }


    @SuppressWarnings("rawtypes")
    public Object generateCode(String appName) {
        //MorsuinoML ne touche pas au code JAVA (kernel) parce qu'il ne veut pas générer ce que
        //ce code permet de générer. Il touche éventuellement à groovuinoML par contre. mais éventuellement.
        String groovuinoMLApp = "";
        //	App app = new App();
        //	app.setName(appName);
        for (String actuatorDeclaration : actuatorDeclarations) {
            groovuinoMLApp += "\n" + actuatorDeclaration;
        }
        groovuinoMLApp += "\n" + statesDeclaration + "\n";

        //states
        groovuinoMLApp += "\n" + transitionDeclaration + "\n";
        groovuinoMLApp += "\nexport \"" + appName + "\"\n";

        return groovuinoMLApp;
    }

    private String setInitialState() {
        //morseActuators contient la liste des noms des actuators, sur lesquels je vais définir les états
        String initialState = "state \"off\" means ";
        states.add(new MorsuinoMLState("off", SHORT, false));
        //TODO: la def de lowEOS et highEOS n'ont rien a faire la dedans
        lowEOS = morseActuators.get(0) + " becomes low";
        highEOS = morseActuators.get(0) + " becomes high";
        if (morseActuators.size() > 1) {
            for (int actus = 1; actus < morseActuators.size(); actus++) {
                lowEOS += " and " + morseActuators.get(actus) + " becomes low";
                highEOS += " and " + morseActuators.get(actus) + " becomes high";
            }
        }
        initialState += lowEOS + "\ninitial off";//TODO: not sure it goes here
        return initialState;
    }

    /**
     * This methods allows us to keep the actuator's name for future reference
     *
     * @param actuator
     */
    public void addActuator(String actuator) {
        morseActuators.add(actuator);
    }

    public void addBuzzerDeclaration(String name, Integer pinNumber) {
        actuatorDeclarations.add("buzzer \"" + name + "\" pin " + pinNumber);
    }

    public void addLedDeclaration(String name, Integer pinNumber) {
        actuatorDeclarations.add(("led \"" + name + "\" pin " + pinNumber));
    }

    public void createMorse(String[] letters) {
        statesDeclaration = "";
        statesDeclaration += setInitialState();

        //Pour chaque lettre du mot, je dois définir une liste d'états, avec leur nom, et aussi aller chercher dans une map correspondante.
        // for (int i = 0 ; i<1 ; i++) {
        for (int i = 0; i < letters.length; i++) {
            MorsuinoMLLetter currL = alphabet.getLetters().get(letters[i].toUpperCase());

            for (MorsuinoMLState currState : currL.getStatesList()) {
                states.add(new MorsuinoMLState(letters[i] + currState.getBaseStateName() + i, currState.getBaseDuration(), currState.isHigh()));
                statesDeclaration += "\nstate \"" + letters[i] + currState.getBaseStateName() + i + "\" means ";
                if (currState.isHigh()) statesDeclaration += highEOS;
                else statesDeclaration += lowEOS;
            }
            //Once we have finished the description of a letter's different states, we must add a transition that will allow us to jump to the other state
            states.add(new MorsuinoMLState("finLettre" + i, LONG, false));
            statesDeclaration += "\nstate \"finLettre" + i + "\" means " + lowEOS;

        }

        transitionDeclaration = "";
        //For each state we have declared, we must now set the transitions.
        //It should be easy because it should be in order
        int i;
        for (i = 0; i < states.size() - 1; i++) {
            transitionDeclaration += "\nfrom " + states.get(i).getBaseStateName() + " to " +
                    states.get(i + 1).getBaseStateName() + " when " + states.get(i + 1).getBaseDuration();
        }
        transitionDeclaration += "\nfrom " + states.get(i).getBaseStateName() + " to " +
                states.get(0).getBaseStateName() + " when " + states.get(0).getBaseDuration();
    }

    private void fillAlphabet() {
        MorsuinoMLState shortOn1 = new MorsuinoMLState("shorton1", SHORT, true);
        MorsuinoMLState shortOff1 = new MorsuinoMLState("shortoff1", SHORT, false);
        MorsuinoMLState shortOn2 = new MorsuinoMLState("shorton2", SHORT, true);
        MorsuinoMLState shortOff2 = new MorsuinoMLState("shortoff2", SHORT, false);
        MorsuinoMLState shortOn3 = new MorsuinoMLState("shorton3", SHORT, true);
        MorsuinoMLState shortOff3 = new MorsuinoMLState("shortoff3", SHORT, false);
        MorsuinoMLState shortOn4 = new MorsuinoMLState("shorton4", SHORT, true);
        MorsuinoMLState shortOff4 = new MorsuinoMLState("shortoff4", SHORT, false);

        MorsuinoMLState longOn1 = new MorsuinoMLState("longon1", LONG, true);
        MorsuinoMLState longOff1 = new MorsuinoMLState("longoff1", SHORT, false);
        MorsuinoMLState longOn2 = new MorsuinoMLState("longon2", LONG, true);
        MorsuinoMLState longOff2 = new MorsuinoMLState("longoff2", SHORT, false);
        MorsuinoMLState longOn3 = new MorsuinoMLState("longon3", LONG, true);
        MorsuinoMLState longOff3 = new MorsuinoMLState("longoff3", SHORT, false);

        MorsuinoMLState silence = new MorsuinoMLState("finMot", SUPERLONG, false);
        MorsuinoMLLetter space = new MorsuinoMLLetter();
        space.addState(silence);
        alphabet.addLetter(" ", space);
        MorsuinoMLLetter a = new MorsuinoMLLetter();
        a.addState(shortOn1);
        a.addState(shortOff1);
        a.addState(longOn1);
        a.addState(longOff1);
        alphabet.addLetter("A", a);

        MorsuinoMLLetter b = new MorsuinoMLLetter();
        b.addState(longOn1);
        b.addState(longOff1);
        b.addState(shortOn1);
        b.addState(shortOff1);
        b.addState(shortOn2);
        b.addState(shortOff2);
        b.addState(shortOn3);
        b.addState(shortOff3);
        alphabet.addLetter("B", b);

        MorsuinoMLLetter c = new MorsuinoMLLetter();
        c.addState(longOn1);
        c.addState(longOff1);
        c.addState(shortOn1);
        c.addState(shortOff1);
        c.addState(longOn2);
        c.addState(longOff2);
        c.addState(shortOn2);
        c.addState(shortOff2);
        alphabet.addLetter("C", c);

        MorsuinoMLLetter d = new MorsuinoMLLetter();
        d.addState(longOn1);
        d.addState(longOff1);
        d.addState(shortOn1);
        d.addState(shortOff1);
        d.addState(shortOn2);
        d.addState(shortOff2);
        alphabet.addLetter("D", d);

        MorsuinoMLLetter e = new MorsuinoMLLetter();
        e.addState(shortOn1);
        e.addState(shortOff1);
        alphabet.addLetter("E", e);

        MorsuinoMLLetter f = new MorsuinoMLLetter();
        f.addState(shortOn1);
        f.addState(shortOff1);
        f.addState(shortOn2);
        f.addState(shortOff2);
        f.addState(longOn1);
        f.addState(longOff1);
        f.addState(shortOn3);
        f.addState(shortOff3);
        alphabet.addLetter("F", f);

        MorsuinoMLLetter g = new MorsuinoMLLetter();
        g.addState(longOn1);
        g.addState(longOff1);
        g.addState(longOn2);
        g.addState(longOff2);
        g.addState(shortOn1);
        g.addState(shortOff1);
        alphabet.addLetter("G", g);

        MorsuinoMLLetter h = new MorsuinoMLLetter();
        h.addState(shortOn1);
        h.addState(shortOff1);
        h.addState(shortOn2);
        h.addState(shortOff2);
        h.addState(shortOn3);
        h.addState(shortOff3);
        h.addState(shortOn4);
        h.addState(shortOff4);
        alphabet.addLetter("H", h);

        MorsuinoMLLetter i = new MorsuinoMLLetter();
        i.addState(shortOn1);
        i.addState(shortOff1);
        i.addState(shortOn2);
        i.addState(shortOff2);
        alphabet.addLetter("I", i);

        MorsuinoMLLetter j = new MorsuinoMLLetter();
        j.addState(shortOn1);
        j.addState(shortOff1);
        j.addState(longOn1);
        j.addState(longOff1);
        j.addState(longOn2);
        j.addState(longOff2);
        j.addState(longOn3);
        j.addState(longOff3);
        alphabet.addLetter("J", j);

        MorsuinoMLLetter k = new MorsuinoMLLetter();
        k.addState(longOn1);
        k.addState(longOff1);
        k.addState(shortOn1);
        k.addState(shortOff1);
        k.addState(longOn2);
        k.addState(longOff2);
        alphabet.addLetter("K", k);

        MorsuinoMLLetter l = new MorsuinoMLLetter();
        l.addState(shortOn1);
        l.addState(shortOff1);
        l.addState(longOn1);
        l.addState(longOff1);
        l.addState(shortOn2);
        l.addState(shortOff2);
        l.addState(shortOn3);
        l.addState(shortOff3);
        alphabet.addLetter("L", l);

        MorsuinoMLLetter m = new MorsuinoMLLetter();
        m.addState(longOn1);
        m.addState(longOff1);
        m.addState(longOn2);
        m.addState(longOff2);
        alphabet.addLetter("M", m);

        MorsuinoMLLetter n = new MorsuinoMLLetter();
        n.addState(longOn1);
        n.addState(longOff1);
        n.addState(shortOn1);
        n.addState(shortOff1);
        alphabet.addLetter("N", n);

        MorsuinoMLLetter o = new MorsuinoMLLetter();
        o.addState(longOn1);
        o.addState(longOff1);
        o.addState(longOn2);
        o.addState(longOff2);
        o.addState(longOn3);
        o.addState(longOff3);
        alphabet.addLetter("O", o);

        MorsuinoMLLetter p = new MorsuinoMLLetter();
        p.addState(shortOn1);
        p.addState(shortOff1);
        p.addState(longOn1);
        p.addState(longOff1);
        p.addState(longOn2);
        p.addState(longOff2);
        p.addState(shortOn2);
        p.addState(shortOff2);
        alphabet.addLetter("P", p);

        MorsuinoMLLetter q = new MorsuinoMLLetter();
        q.addState(longOn1);
        q.addState(longOff1);
        q.addState(longOn2);
        q.addState(longOff2);
        q.addState(shortOn1);
        q.addState(shortOff1);
        q.addState(longOn3);
        q.addState(longOff3);
        alphabet.addLetter("Q", q);

        MorsuinoMLLetter r = new MorsuinoMLLetter();
        r.addState(shortOn1);
        r.addState(shortOff1);
        r.addState(longOn1);
        r.addState(longOff1);
        r.addState(shortOn2);
        r.addState(shortOff2);
        alphabet.addLetter("R", r);

        MorsuinoMLLetter s = new MorsuinoMLLetter();
        s.addState(shortOn1);
        s.addState(shortOff1);
        s.addState(shortOn2);
        s.addState(shortOff2);
        s.addState(shortOn3);
        s.addState(shortOff3);
        alphabet.addLetter("S", s);

        MorsuinoMLLetter t = new MorsuinoMLLetter();
        t.addState(longOn1);
        t.addState(longOff1);
        alphabet.addLetter("T", t);

        MorsuinoMLLetter u = new MorsuinoMLLetter();
        u.addState(shortOn1);
        u.addState(shortOff1);
        u.addState(shortOn2);
        u.addState(shortOff2);
        u.addState(longOn1);
        u.addState(longOff1);
        alphabet.addLetter("U", u);

        MorsuinoMLLetter v = new MorsuinoMLLetter();
        v.addState(shortOn1);
        v.addState(shortOff1);
        v.addState(shortOn2);
        v.addState(shortOff2);
        u.addState(shortOn3);
        u.addState(shortOff3);
        v.addState(longOn1);
        v.addState(longOff1);
        alphabet.addLetter("V", v);

        MorsuinoMLLetter w = new MorsuinoMLLetter();
        w.addState(shortOn1);
        w.addState(shortOff1);
        w.addState(longOn1);
        w.addState(longOff1);
        w.addState(longOn2);
        w.addState(longOff2);
        alphabet.addLetter("W", w);

        MorsuinoMLLetter x = new MorsuinoMLLetter();
        x.addState(longOn1);
        x.addState(longOff1);
        x.addState(shortOn1);
        x.addState(shortOff1);
        x.addState(shortOn2);
        x.addState(shortOff2);
        x.addState(longOn2);
        x.addState(longOff2);
        alphabet.addLetter("X", x);

        MorsuinoMLLetter y = new MorsuinoMLLetter();
        y.addState(longOn1);
        y.addState(longOff1);
        y.addState(shortOn1);
        y.addState(shortOff1);
        y.addState(longOn2);
        y.addState(longOff2);
        y.addState(longOn3);
        y.addState(longOff3);
        alphabet.addLetter("Y", y);

        MorsuinoMLLetter z = new MorsuinoMLLetter();
        z.addState(longOn1);
        z.addState(longOff1);
        z.addState(longOn2);
        z.addState(longOff2);
        z.addState(shortOn1);
        z.addState(shortOff1);
        z.addState(shortOn2);
        z.addState(shortOff2);
        alphabet.addLetter("Z", z);
    }
}
