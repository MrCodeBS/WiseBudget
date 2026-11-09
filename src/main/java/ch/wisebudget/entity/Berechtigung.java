package ch.wisebudget.entity;

public enum Berechtigung {
    LESEN(1),
    SCHREIBEN(2),
    BESITZER(3);

    private final int stufe;

    Berechtigung(int stufe) {
        this.stufe = stufe;
    }

    public boolean hatMindestens(Berechtigung erforderlich) {
        return this.stufe >= erforderlich.stufe;
    }
}
