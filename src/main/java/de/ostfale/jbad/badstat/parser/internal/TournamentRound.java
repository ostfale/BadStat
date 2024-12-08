package de.ostfale.jbad.badstat.parser.internal;

public record TournamentRound(
        String roundName,
        String roundDate,
        String roundCourt,
        Integer roundDuration
) {
}
