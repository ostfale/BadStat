package de.ostfale.jbad.badstat.parser.internal.match;

import de.ostfale.jbad.badstat.tournament.internal.Discipline;

abstract sealed class TournamentMatch permits TournamentSingleMatch, TournamentDoubleMatch, TournamentMixedMatch {

    abstract Discipline getDiscipline();

    protected String roundName = "";
    protected String roundDate = "";
    protected String roundCourt = "";
    protected String roundDuration = "";
}
