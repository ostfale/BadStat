package de.ostfale.jbad.badstat.parser.internal;

public record TournamentHeaderInfo(
        String tournamentName,
        String tournamentOrganisation,
        String tournamentLocation,
        String tournamentId,
        String tournamentDate
) {

    @Override
    public String toString() {
        return "TournamentHeaderInfo{" +
                "tournamentName='" + tournamentName + '\'' +
                ", tournamentOrganisation='" + tournamentOrganisation + '\'' +
                ", tournamentLocation='" + tournamentLocation + '\'' +
                ", tournamentId='" + tournamentId + '\'' +
                ", tournamentDate='" + tournamentDate + '\'' +
                '}';
    }
}
