package de.ostfale.jbad.badstat.parser.internal.team;

import de.ostfale.jbad.badstat.player.internal.GenderType;

public record Player(
        String playerName,
        String playerId,
        GenderType gender
) {
}
