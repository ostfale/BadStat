package de.ostfale.jbad.badstat.parser.internal.match;

import de.ostfale.jbad.badstat.tournament.internal.Discipline;

public final class TournamentDoubleMatch extends TournamentMatch {

    @Override
    public Discipline getDiscipline() {
        return Discipline.DOUBLE;
    }
}
