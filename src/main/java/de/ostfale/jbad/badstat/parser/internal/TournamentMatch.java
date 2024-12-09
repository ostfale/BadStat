package de.ostfale.jbad.badstat.parser.internal;

public record TournamentMatch(
        String roundName,
        String roundDate,
        String roundCourt,
        String roundDuration
) {

    @Override
    public String toString() {
        return "TournamentMatch{" +
                "roundName='" + roundName + '\'' +
                ", roundDate='" + roundDate + '\'' +
                ", roundCourt='" + roundCourt + '\'' +
                ", roundDuration=" + roundDuration +
                '}';
    }
}
//*[@id="TournamentMatchList_ED013E9C-7629-428D-B167-7F30708225514"]/li[3]/div/div[3]/ul/li[2]/span/svg
