package de.ostfale.jbad.badstat.parser.internal.match;

import de.ostfale.jbad.badstat.tournament.internal.Discipline;

public final class TournamentSingleMatch extends TournamentMatch {

    @Override
    public Discipline getDiscipline() {
        return Discipline.SINGLE;
    }
}
