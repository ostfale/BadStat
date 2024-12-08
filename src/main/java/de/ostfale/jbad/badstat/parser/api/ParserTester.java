package de.ostfale.jbad.badstat.parser.api;

import de.ostfale.jbad.badstat.parser.internal.CookieDialogHandler;
import de.ostfale.jbad.badstat.parser.internal.TournamentHeaderInfo;
import org.htmlunit.html.HtmlDivision;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParserTester {

    private static final Logger log = LoggerFactory.getLogger(ParserTester.class);


    private static final String BASE_URL = "https://www.turnier.de/player-profile/bd337124-44d1-42c1-9c30-8bed91781a9b/tournaments";
    private static final String FIRST_MODULE_CARD = "//*[@id=\"tabcontent\"]/div[1]";
    private static final String MATCH_GROUP = "//*[@id=\"TournamentMatchList_8FE45CBC-7603-47EA-A189-2CD5A6FC65054\"]";


    public static void main(String[] args) {
        new ParserTester().readAllTournamentsForPlayer();
    }

    public List<HtmlElement> getModuleCardElements(HtmlPage page) {
        return page.getByXPath("//*[@id=\"tabcontent\"]//div[contains(@class, 'module--card')]");
    }



    public void test() {
        var cookieHander = new CookieDialogHandler();
        HtmlPage page = cookieHander.loadWebsite(BASE_URL);

        // read all module cards
        List<HtmlDivision> moduleCards = page.getByXPath("//div[contains(@class, 'module module--card')]");

        moduleCards.forEach(element -> {

            // read tournament header 
            HtmlElement mediaContent = element.getFirstByXPath(".//div[contains(@class, 'media__content')]");
            if (mediaContent != null) {
                System.out.println("\n\n" + mediaContent.asNormalizedText());
            }


            // read all matches per card
            List<HtmlElement> matchgroups = element.getByXPath(".//ol");

            matchgroups.forEach(matchgroup -> {

                // read all h4 elements with class "module-divider"
                List<HtmlElement> dividers = element.getByXPath(".//h4[contains(@class, 'module-divider')]");
                dividers.forEach(divider -> {

                            System.out.println("Discipline -> " + divider.asNormalizedText());
                        }
                );

                // read which round
                List<HtmlElement> roundName = matchgroup.getByXPath(".//ul[contains(@class, 'match__header-title')]");
                roundName.forEach(round -> System.out.println("\tRound -> " + round.asNormalizedText()));
            });

            System.out.println("MatchGroups Size: " + matchgroups.size());
        });
    }


    public void readAllTournamentsForPlayer() {
        log.info("Reading all tournaments for player for URL: {}", BASE_URL);
        var cookieHandler = new CookieDialogHandler();
        HtmlPage page = cookieHandler.loadWebsite(BASE_URL);

        // read all tournaments for the given year
        List<HtmlDivision> tournamentsListDivs = getPlayersTournaments(page);

        // loop over all tournament (dive: moduleCard)
        tournamentsListDivs.forEach(tournamentDiv -> {
            var headerInfo = getTournamentHeaderInfo(tournamentDiv);
            log.info("Tournament -> {}", headerInfo);

            // read all disciplines as list
            var disciplineNames = getDisciplineName(tournamentDiv);
            System.out.println("\nDisciplines: " + disciplineNames);
        });
    }


    private TournamentHeaderInfo getTournamentHeaderInfo(HtmlDivision moduleContent) {
        final String TOURNAMENT_NAME = ".//h4[contains(@class, 'media__title media__title--medium')]";
        final String TOURNAMENT_ORGANISATION = ".//small[contains(@class, 'media__subheading')]";
        final String TOURNAMENT_DATE = ".//small[contains(@class, 'media__subheading media__subheading--muted')]";

        HtmlElement tournamentNameElement = moduleContent.getFirstByXPath(TOURNAMENT_NAME);
        HtmlElement tournamentOrgElement = moduleContent.getFirstByXPath(TOURNAMENT_ORGANISATION);
        HtmlElement tournamentDateElement = moduleContent.getFirstByXPath(TOURNAMENT_DATE);

        var orgaAndLocation = tournamentOrgElement.asNormalizedText().split("\\|");

        var tournamentName = tournamentNameElement.asNormalizedText();
        var tournamentOrganisation = orgaAndLocation[0];
        var tournamentLocation = orgaAndLocation[1];
        var tournamentId = "?";
        var tournamentDate= tournamentDateElement.asNormalizedText();

        return new TournamentHeaderInfo(tournamentName, tournamentOrganisation, tournamentLocation, tournamentId, tournamentDate);
    }

    private List<String> getDisciplineName(HtmlDivision moduleContent) {
        List<HtmlElement> disciplineNameElements = moduleContent.getByXPath(".//h4[contains(@class, 'module-divider')]");
        return disciplineNameElements.stream().map(HtmlElement::asNormalizedText).toList();
    }

    private List<HtmlDivision> getPlayersTournaments(HtmlPage page) {
        final String FIRST_MODULE_CARD = "//div[contains(@class, 'module module--card')]";
        List<HtmlDivision> moduleCards = page.getByXPath(FIRST_MODULE_CARD);
        log.info("Found {} tournaments", moduleCards.size());
        return moduleCards;
    }

    private String getTournamentName(HtmlDivision moduleContent) {
        HtmlElement mediaContent = moduleContent.getFirstByXPath(".//div[contains(@class, 'media__content')]");
        if (mediaContent != null) {
            var tournamentName = mediaContent.asNormalizedText();
            log.info("Read tournament name -> found  name: {}", tournamentName);
            return tournamentName;
        }
        log.error("Could not find tournament name");
        return null;
    }

}