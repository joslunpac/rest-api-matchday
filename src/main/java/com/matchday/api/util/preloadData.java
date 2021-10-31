package com.matchday.api.util;

import java.util.ArrayList;
import java.util.List;

import com.matchday.api.model.entity.City;
import com.matchday.api.model.entity.Competition;
import com.matchday.api.model.entity.Country;
import com.matchday.api.model.entity.Role;
import com.matchday.api.model.entity.Sport;
import com.matchday.api.model.entity.Stadium;
import com.matchday.api.model.entity.State;
import com.matchday.api.model.entity.Team;
import com.matchday.api.model.entity.User;
import com.matchday.api.model.enumerate.RoleEnum;
import com.matchday.api.service.CityService;
import com.matchday.api.service.CompetitionService;
import com.matchday.api.service.CountryService;
import com.matchday.api.service.RoleService;
import com.matchday.api.service.SportService;
import com.matchday.api.service.StadiumService;
import com.matchday.api.service.StateService;
import com.matchday.api.service.TeamService;
import com.matchday.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class runs every time the application is run, and will take care of to
 * insert in the BD the data necessary for the correct operation of the same
 */

@Component
public class preloadData implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    CountryService countryService;

    @Autowired
    StateService stateService;

    @Autowired
    CityService cityService;

    @Autowired
    SportService sportService;

    @Autowired
    CompetitionService competitionService;

    @Autowired
    StadiumService stadiumService;

    @Autowired
    TeamService teamService;

    @Override
    public void run(String... args) throws Exception {
        // ROLES
        if (roleService.count() == 0) {
            // If there are still no roles in DB, we insert them
            Role roleAdmin = new Role(RoleEnum.ROLE_ADMIN);
            Role roleUser = new Role(RoleEnum.ROLE_USER);
            roleService.save(roleAdmin);
            roleService.save(roleUser);
        }
        
        // USERS
        if (userService.count() == 0) {
            // If there are still no users in DB, we insert them
            List<Role> rolesAdmin = new ArrayList<>();
            List<Role> rolesUser = new ArrayList<>();
            
            rolesAdmin.add(roleService.findByName(RoleEnum.ROLE_USER).get());
            rolesAdmin.add(roleService.findByName(RoleEnum.ROLE_ADMIN).get());
            rolesUser.add(roleService.findByName(RoleEnum.ROLE_USER).get());
            
            userService.save(new User("admin", "admin", "admin@matchday.com", passwordEncoder.encode("admin"), rolesAdmin));
            userService.save(new User("user", "user", "user@matchday.com", passwordEncoder.encode("user"), rolesUser));
        }

        // COUNTRIES
        if (countryService.count() == 0) {
            // If there are still no countries in DB, we insert them
            countryService.save(new Country("USA", "United States"));
            countryService.save(new Country("CAN", "Canada"));
        }

        // STATES
        if (stateService.count() == 0) {
            // If there are still no states in DB, we insert them
            // USA
            stateService.save(new State("AK", "Alaska", countryService.findByAcronym("USA").get()));
            stateService.save(new State("AZ", "Arizona", countryService.findByAcronym("USA").get()));
            stateService.save(new State("AR", "Arkansas", countryService.findByAcronym("USA").get()));
            stateService.save(new State("CA", "California", countryService.findByAcronym("USA").get()));
            stateService.save(new State("CO", "Colorado", countryService.findByAcronym("USA").get()));
            stateService.save(new State("CT", "Connecticut", countryService.findByAcronym("USA").get()));
            stateService.save(new State("DE", "Delaware", countryService.findByAcronym("USA").get()));
            stateService.save(new State("FL", "Florida", countryService.findByAcronym("USA").get()));
            stateService.save(new State("GA", "Georgia", countryService.findByAcronym("USA").get()));
            stateService.save(new State("HI", "Hawaii", countryService.findByAcronym("USA").get()));
            stateService.save(new State("ID", "Idaho", countryService.findByAcronym("USA").get()));
            stateService.save(new State("IL", "Illinois", countryService.findByAcronym("USA").get()));
            stateService.save(new State("IN", "Indiana", countryService.findByAcronym("USA").get()));
            stateService.save(new State("IA", "Iowa", countryService.findByAcronym("USA").get()));
            stateService.save(new State("KS", "Kansas", countryService.findByAcronym("USA").get()));
            stateService.save(new State("KY", "Kentucky", countryService.findByAcronym("USA").get()));
            stateService.save(new State("LA", "Louisiana", countryService.findByAcronym("USA").get()));
            stateService.save(new State("ME", "Maine", countryService.findByAcronym("USA").get()));
            stateService.save(new State("MD", "Maryland", countryService.findByAcronym("USA").get()));
            stateService.save(new State("MA", "Massachusetts", countryService.findByAcronym("USA").get()));
            stateService.save(new State("MI", "Michigan", countryService.findByAcronym("USA").get()));
            stateService.save(new State("MN", "Minnesota", countryService.findByAcronym("USA").get()));
            stateService.save(new State("MS", "Mississippi", countryService.findByAcronym("USA").get()));
            stateService.save(new State("MO", "Missouri", countryService.findByAcronym("USA").get()));
            stateService.save(new State("MT", "Montana", countryService.findByAcronym("USA").get()));
            stateService.save(new State("NE", "Nebraska", countryService.findByAcronym("USA").get()));
            stateService.save(new State("NV", "Nevada", countryService.findByAcronym("USA").get()));
            stateService.save(new State("NH", "New Hampshire", countryService.findByAcronym("USA").get()));
            stateService.save(new State("NJ", "New Jersey", countryService.findByAcronym("USA").get()));
            stateService.save(new State("NM", "New Mexico", countryService.findByAcronym("USA").get()));
            stateService.save(new State("NY", "New York", countryService.findByAcronym("USA").get()));
            stateService.save(new State("NC", "North Carolina", countryService.findByAcronym("USA").get()));
            stateService.save(new State("ND", "North Dakota", countryService.findByAcronym("USA").get()));
            stateService.save(new State("OH", "Ohio", countryService.findByAcronym("USA").get()));
            stateService.save(new State("OK", "Oklahoma", countryService.findByAcronym("USA").get()));
            stateService.save(new State("OR", "Oregon", countryService.findByAcronym("USA").get()));
            stateService.save(new State("PA", "Pennsylvania", countryService.findByAcronym("USA").get()));
            stateService.save(new State("RI", "Rhode Island", countryService.findByAcronym("USA").get()));
            stateService.save(new State("SC", "South Carolina", countryService.findByAcronym("USA").get()));
            stateService.save(new State("SD", "South Dakota", countryService.findByAcronym("USA").get()));
            stateService.save(new State("TN", "Tennessee", countryService.findByAcronym("USA").get()));
            stateService.save(new State("TX", "Texas", countryService.findByAcronym("USA").get()));
            stateService.save(new State("UT", "Utah", countryService.findByAcronym("USA").get()));
            stateService.save(new State("VT", "Vermont", countryService.findByAcronym("USA").get()));
            stateService.save(new State("VA", "Virginia", countryService.findByAcronym("USA").get()));
            stateService.save(new State("WA", "Washington", countryService.findByAcronym("USA").get()));
            stateService.save(new State("WV", "West Virginia", countryService.findByAcronym("USA").get()));
            stateService.save(new State("WI", "Wisconsin", countryService.findByAcronym("USA").get()));
            stateService.save(new State("WY", "Wyoming", countryService.findByAcronym("USA").get()));
            // CAN
            stateService.save(new State("AB", "Alberta", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("BC", "British Columbia", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("MB", "Manitoba", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("NB", "New Brunswick", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("NL", "Newfoundland and Labrador", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("NT", "Northwest Territories", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("NS", "Nova Scotia", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("NU", "Nunavut", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("ON", "Ontario", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("PE", "Prince Edward Island", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("QC", "Quebec", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("SK", "Saskatchewan", countryService.findByAcronym("CAN").get()));
            stateService.save(new State("YT", "Yukon", countryService.findByAcronym("CAN").get()));
        }

        // CITIES
        if (cityService.count() == 0) {
            // If there are still no countries in DB, we insert them
            // USA
            cityService.save(new City("Anaheim", stateService.findByAcronym("CA").get()));
            cityService.save(new City("Arlington", stateService.findByAcronym("TX").get()));
            cityService.save(new City("Atlanta", stateService.findByAcronym("GA").get()));
            cityService.save(new City("Austin", stateService.findByAcronym("TX").get()));
            cityService.save(new City("Baltimore", stateService.findByAcronym("MD").get()));
            cityService.save(new City("Boston", stateService.findByAcronym("MA").get()));
            cityService.save(new City("Bradenton", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Buffalo", stateService.findByAcronym("NY").get()));
            cityService.save(new City("Carson", stateService.findByAcronym("CA").get()));
            cityService.save(new City("Charlotte", stateService.findByAcronym("NC").get()));
            cityService.save(new City("Chester", stateService.findByAcronym("PA").get()));
            cityService.save(new City("Chicago", stateService.findByAcronym("IL").get()));
            cityService.save(new City("Cincinnati", stateService.findByAcronym("OH").get()));
            cityService.save(new City("Clearwater", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Cleveland", stateService.findByAcronym("OH").get()));
            cityService.save(new City("Columbus", stateService.findByAcronym("OH").get()));
            cityService.save(new City("Commerce City", stateService.findByAcronym("CO").get()));
            cityService.save(new City("Cumberland", stateService.findByAcronym("GA").get()));
            cityService.save(new City("Dallas", stateService.findByAcronym("TX").get()));
            cityService.save(new City("Denver", stateService.findByAcronym("CO").get()));
            cityService.save(new City("Detroit", stateService.findByAcronym("MI").get()));
            cityService.save(new City("Dunedin", stateService.findByAcronym("FL").get()));
            cityService.save(new City("East Rutherford", stateService.findByAcronym("NJ").get()));
            cityService.save(new City("Elmont", stateService.findByAcronym("NY").get()));
            cityService.save(new City("Fort Lauderdale", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Fort Myers", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Foxborough", stateService.findByAcronym("MA").get()));
            cityService.save(new City("Frisco", stateService.findByAcronym("TX").get()));
            cityService.save(new City("Glendale", stateService.findByAcronym("AZ").get()));
            cityService.save(new City("Goodyear", stateService.findByAcronym("AZ").get()));
            cityService.save(new City("Green Bay", stateService.findByAcronym("WI").get()));
            cityService.save(new City("Harrison", stateService.findByAcronym("NJ").get()));
            cityService.save(new City("Houston", stateService.findByAcronym("TX").get()));
            cityService.save(new City("Indianapolis", stateService.findByAcronym("IN").get()));
            cityService.save(new City("Inglewood", stateService.findByAcronym("CA").get()));
            cityService.save(new City("Jacksonville", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Jupiter", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Kansas City [KS]", stateService.findByAcronym("KS").get()));
            cityService.save(new City("Kansas City [MO]", stateService.findByAcronym("MO").get()));
            cityService.save(new City("Landover", stateService.findByAcronym("MD").get()));
            cityService.save(new City("Lakeland", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Los Angeles", stateService.findByAcronym("CA").get()));
            cityService.save(new City("Memphis", stateService.findByAcronym("TN").get()));
            cityService.save(new City("Mesa", stateService.findByAcronym("AZ").get()));
            cityService.save(new City("Miami", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Miami Gardens", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Milwaukee", stateService.findByAcronym("WI").get()));
            cityService.save(new City("Minneapolis", stateService.findByAcronym("MN").get()));
            cityService.save(new City("Nashville", stateService.findByAcronym("TN").get()));
            cityService.save(new City("Newark", stateService.findByAcronym("NJ").get()));
            cityService.save(new City("New Orleans", stateService.findByAcronym("LA").get()));
            cityService.save(new City("New York City (Brooklyn)", stateService.findByAcronym("NY").get()));
            cityService.save(new City("New York City (Manhattan)", stateService.findByAcronym("NY").get()));
            cityService.save(new City("New York City (Queens)", stateService.findByAcronym("NY").get()));
            cityService.save(new City("New York City (The Bronx)", stateService.findByAcronym("NY").get()));
            cityService.save(new City("North Port", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Oakland", stateService.findByAcronym("CA").get()));
            cityService.save(new City("Oklahoma City", stateService.findByAcronym("OK").get()));
            cityService.save(new City("Orchard Park", stateService.findByAcronym("NY").get()));
            cityService.save(new City("Orlando", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Paradise", stateService.findByAcronym("NV").get()));
            cityService.save(new City("Peoria", stateService.findByAcronym("AZ").get()));
            cityService.save(new City("Philadelphia", stateService.findByAcronym("PA").get()));
            cityService.save(new City("Phoenix", stateService.findByAcronym("AZ").get()));
            cityService.save(new City("Pittsburgh", stateService.findByAcronym("PA").get()));
            cityService.save(new City("Portland", stateService.findByAcronym("OR").get()));
            cityService.save(new City("Port Charlotte", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Port St. Lucie", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Raleigh", stateService.findByAcronym("NC").get()));
            cityService.save(new City("Sacramento", stateService.findByAcronym("CA").get()));
            cityService.save(new City("Saint Paul", stateService.findByAcronym("MN").get()));
            cityService.save(new City("Salt Lake City", stateService.findByAcronym("UT").get()));
            cityService.save(new City("Salt River Pima–Maricopa Indian Community", stateService.findByAcronym("AZ").get()));
            cityService.save(new City("San Antonio", stateService.findByAcronym("TX").get()));
            cityService.save(new City("San Diego", stateService.findByAcronym("CA").get()));
            cityService.save(new City("San Francisco", stateService.findByAcronym("CA").get()));
            cityService.save(new City("San Jose", stateService.findByAcronym("CA").get()));
            cityService.save(new City("Sandy", stateService.findByAcronym("UT").get()));
            cityService.save(new City("Santa Clara", stateService.findByAcronym("CA").get()));
            cityService.save(new City("Sarasota", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Scottsdale", stateService.findByAcronym("AZ").get()));
            cityService.save(new City("Seattle", stateService.findByAcronym("WA").get()));
            cityService.save(new City("St. Louis", stateService.findByAcronym("MO").get()));
            cityService.save(new City("St. Petersburg", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Sunrise", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Surprise", stateService.findByAcronym("AZ").get()));
            cityService.save(new City("Tampa", stateService.findByAcronym("FL").get()));
            cityService.save(new City("Tempe", stateService.findByAcronym("AZ").get()));
            cityService.save(new City("WashingtonD.C.", stateService.findByAcronym("MD").get()));
            cityService.save(new City("West Palm Beach", stateService.findByAcronym("FL").get()));
            // CAN
            cityService.save(new City("Calgary", stateService.findByAcronym("AB").get()));
            cityService.save(new City("Edmonton", stateService.findByAcronym("AB").get()));
            cityService.save(new City("Montreal", stateService.findByAcronym("QC").get()));
            cityService.save(new City("Ottawa", stateService.findByAcronym("ON").get()));
            cityService.save(new City("Toronto", stateService.findByAcronym("ON").get()));
            cityService.save(new City("Vancouver", stateService.findByAcronym("BC").get()));
            cityService.save(new City("Winnipeg", stateService.findByAcronym("MB").get()));
        }

        // SPORTS
        if (sportService.count() == 0) {
            // If there are still no sports in DB, we insert them
            sportService.save(new Sport("Baseball"));
            sportService.save(new Sport("Basketball"));
            sportService.save(new Sport("Football"));
            sportService.save(new Sport("Hockey"));
            sportService.save(new Sport("Soccer"));
        }

        // COMPETITIONS
        if (competitionService.count() == 0) {
            // If there are still no competitions in DB, we insert them
            // FATHERS USA
            competitionService.save(new Competition("MLB", "Major League Baseball", null, sportService.findByName("Baseball").get()));
            competitionService.save(new Competition("NBA", "National Basketball Association", null, sportService.findByName("Basketball").get()));
            competitionService.save(new Competition("NFL", "National Football League", null, sportService.findByName("Football").get()));
            competitionService.save(new Competition("NHL", "National Hockey League", null, sportService.findByName("Hockey").get()));
            competitionService.save(new Competition("MLS", "Major League Soccer", null, sportService.findByName("Soccer").get()));
            // SONS USA
        }

        // STADIUMS
        if (stadiumService.count() == 0) {
            // If there are still no stadiums in DB, we insert them
            // USA
            stadiumService.save(new Stadium("Allegiant Stadium", null, cityService.findByName("Paradise").get()));
            stadiumService.save(new Stadium("Allianz Field", null, cityService.findByName("Saint Paul").get()));
            stadiumService.save(new Stadium("Amalie Arena", null, cityService.findByName("Tampa").get()));
            stadiumService.save(new Stadium("American Airlines Arena", null, cityService.findByName("Miami").get()));
            stadiumService.save(new Stadium("American Airlines Center", null, cityService.findByName("Dallas").get()));
            stadiumService.save(new Stadium("American Family Field", null, cityService.findByName("Milwaukee").get()));
            stadiumService.save(new Stadium("American Family Fields of Phoenix", null, cityService.findByName("Phoenix").get()));
            stadiumService.save(new Stadium("Amway Center", null, cityService.findByName("Orlando").get()));
            stadiumService.save(new Stadium("Angel Stadium", null, cityService.findByName("Anaheim").get()));
            stadiumService.save(new Stadium("Arrowhead Stadium", null, cityService.findByName("Kansas City [MO]").get()));
            stadiumService.save(new Stadium("AT&T Center", null, cityService.findByName("San Antonio").get()));
            stadiumService.save(new Stadium("AT&T Stadium", null, cityService.findByName("Arlington").get()));
            stadiumService.save(new Stadium("Audi Field", null, cityService.findByName("WashingtonD.C.").get()));
            stadiumService.save(new Stadium("Ball Arena", null, cityService.findByName("Denver").get()));
            stadiumService.save(new Stadium("Bank of America Stadium", null, cityService.findByName("Charlotte").get()));
            stadiumService.save(new Stadium("Banc of California Stadium", null, cityService.findByName("Los Angeles").get()));
            stadiumService.save(new Stadium("Barclays Center", null, cityService.findByName("New York City (Brooklyn)").get()));
            stadiumService.save(new Stadium("BayCare Ballpark", null, cityService.findByName("Clearwater").get()));
            stadiumService.save(new Stadium("BBVA Stadium", null, cityService.findByName("Houston").get()));
            stadiumService.save(new Stadium("BB&T Center", null, cityService.findByName("Sunrise").get()));
            stadiumService.save(new Stadium("Bridgestone Arena", null, cityService.findByName("Nashville").get()));
            stadiumService.save(new Stadium("Busch Stadium", null, cityService.findByName("St. Louis").get()));
            stadiumService.save(new Stadium("Capital One Arena", null, cityService.findByName("WashingtonD.C.").get()));
            stadiumService.save(new Stadium("Caesars Superdome", null, cityService.findByName("New Orleans").get()));
            stadiumService.save(new Stadium("Camelback Ranch-Glendale", null, cityService.findByName("Glendale").get()));
            stadiumService.save(new Stadium("Chase Center", null, cityService.findByName("San Francisco").get()));
            stadiumService.save(new Stadium("Chase Field", null, cityService.findByName("Phoenix").get()));
            stadiumService.save(new Stadium("Charlotte Sports Park", null, cityService.findByName("Port Charlotte").get()));
            stadiumService.save(new Stadium("Children's Mercy Park", null, cityService.findByName("Kansas City [KS]").get()));
            stadiumService.save(new Stadium("Citi Field", null, cityService.findByName("New York City (Queens)").get()));
            stadiumService.save(new Stadium("Citizens Bank Park", null, cityService.findByName("Philadelphia").get()));
            stadiumService.save(new Stadium("Climate Pledge Arena", null, cityService.findByName("Seattle").get()));
            stadiumService.save(new Stadium("Clover Park", null, cityService.findByName("Port St. Lucie").get()));
            stadiumService.save(new Stadium("Comerica Park", null, cityService.findByName("Detroit").get()));
            stadiumService.save(new Stadium("CoolToday Park", null, cityService.findByName("North Port").get()));
            stadiumService.save(new Stadium("Coors Field", null, cityService.findByName("Denver").get()));
            stadiumService.save(new Stadium("Dick's Sporting Goods Park", null, cityService.findByName("Commerce City").get()));
            stadiumService.save(new Stadium("Dignity Health Sports Park", null, cityService.findByName("Carson").get()));
            stadiumService.save(new Stadium("Dodger Stadium", null, cityService.findByName("Los Angeles").get()));
            stadiumService.save(new Stadium("DRV PNK Stadium", null, cityService.findByName("Fort Lauderdale").get()));
            stadiumService.save(new Stadium("Ed Smith Stadium", null, cityService.findByName("Sarasota").get()));
            stadiumService.save(new Stadium("Empower Field at Mile High", null, cityService.findByName("Denver").get()));
            stadiumService.save(new Stadium("Enterprise Center", null, cityService.findByName("St. Louis").get()));
            stadiumService.save(new Stadium("Exploria Stadium", null, cityService.findByName("Orlando").get()));
            stadiumService.save(new Stadium("FedExField", null, cityService.findByName("Landover").get()));
            stadiumService.save(new Stadium("FedExForum", null, cityService.findByName("Memphis").get()));
            stadiumService.save(new Stadium("Fenway Park", null, cityService.findByName("Boston").get()));
            stadiumService.save(new Stadium("FirstEnergy Stadium", null, cityService.findByName("Cleveland").get()));
            stadiumService.save(new Stadium("Fiserv Forum", null, cityService.findByName("Milwaukee").get()));
            stadiumService.save(new Stadium("FITTEAM Ballpark of the Palm Beaches", null, cityService.findByName("West Palm Beach").get()));
            stadiumService.save(new Stadium("Ford Field", null, cityService.findByName("Detroit").get()));
            stadiumService.save(new Stadium("Footprint Center", null, cityService.findByName("Phoenix").get()));
            stadiumService.save(new Stadium("Gainbridge Fieldhouse", null, cityService.findByName("Indianapolis").get()));
            stadiumService.save(new Stadium("George M. Steinbrenner Field", null, cityService.findByName("Tampa").get()));
            stadiumService.save(new Stadium("Gila River Arena", null, cityService.findByName("Glendale").get()));
            stadiumService.save(new Stadium("Gillette Stadium", null, cityService.findByName("Foxborough").get()));
            stadiumService.save(new Stadium("Globe Life Field", null, cityService.findByName("Arlington").get()));
            stadiumService.save(new Stadium("Golden 1 Center", null, cityService.findByName("Sacramento").get()));
            stadiumService.save(new Stadium("Goodyear Ballpark", null, cityService.findByName("Goodyear").get()));
            stadiumService.save(new Stadium("Great American Ball Park", null, cityService.findByName("Cincinnati").get()));
            stadiumService.save(new Stadium("Guaranteed Rate Field", null, cityService.findByName("Chicago").get()));
            stadiumService.save(new Stadium("Hammond Stadium", null, cityService.findByName("Fort Myers").get()));
            stadiumService.save(new Stadium("Hard Rock Stadium", null, cityService.findByName("Miami Gardens").get()));
            stadiumService.save(new Stadium("Heinz Field", null, cityService.findByName("Pittsburgh").get()));
            stadiumService.save(new Stadium("Highmark Stadium", null, cityService.findByName("Orchard Park").get()));
            stadiumService.save(new Stadium("Hohokam Stadium", null, cityService.findByName("Mesa").get()));
            stadiumService.save(new Stadium("Honda Center", null, cityService.findByName("Anaheim").get()));
            stadiumService.save(new Stadium("JetBlue Park at Fenway South", null, cityService.findByName("Fort Myers").get()));
            stadiumService.save(new Stadium("Kauffman Stadium", null, cityService.findByName("Kansas City [MO]").get()));
            stadiumService.save(new Stadium("KeyBank Center", null, cityService.findByName("Buffalo").get()));
            stadiumService.save(new Stadium("Lambeau Field", null, cityService.findByName("Green Bay").get()));
            stadiumService.save(new Stadium("LECOM Park", null, cityService.findByName("Bradenton").get()));
            stadiumService.save(new Stadium("Levi's Stadium", null, cityService.findByName("Santa Clara").get()));
            stadiumService.save(new Stadium("Lincoln Financial Field", null, cityService.findByName("Philadelphia").get()));
            stadiumService.save(new Stadium("Little Caesars Arena", null, cityService.findByName("Detroit").get()));
            stadiumService.save(new Stadium("LoanDepot Park", null, cityService.findByName("Miami").get()));
            stadiumService.save(new Stadium("Lower.com Field", null, cityService.findByName("Columbus").get()));
            stadiumService.save(new Stadium("Lucas Oil Stadium", null, cityService.findByName("Indianapolis").get()));
            stadiumService.save(new Stadium("Lumen Field", null, cityService.findByName("Seattle").get()));
            stadiumService.save(new Stadium("Madison Square Garden", null, cityService.findByName("New York City (Manhattan)").get()));
            stadiumService.save(new Stadium("Mercedes-Benz Stadium", null, cityService.findByName("Atlanta").get()));
            stadiumService.save(new Stadium("MetLife Stadium", null, cityService.findByName("East Rutherford").get()));
            stadiumService.save(new Stadium("Minute Maid Park", null, cityService.findByName("Houston").get()));
            stadiumService.save(new Stadium("Moda Center", null, cityService.findByName("Portland").get()));
            stadiumService.save(new Stadium("M&T Bank Stadium", null, cityService.findByName("Baltimore").get()));
            stadiumService.save(new Stadium("Nationals Park", null, cityService.findByName("WashingtonD.C.").get()));
            stadiumService.save(new Stadium("Nationwide Arena", null, cityService.findByName("Columbus").get()));
            stadiumService.save(new Stadium("Nissan Stadium", null, cityService.findByName("Nashville").get()));
            stadiumService.save(new Stadium("NRG Stadium", null, cityService.findByName("Houston").get()));
            stadiumService.save(new Stadium("Oracle Park", null, cityService.findByName("San Francisco").get()));
            stadiumService.save(new Stadium("Oriole Park at Camden Yards", null, cityService.findByName("Baltimore").get()));
            stadiumService.save(new Stadium("Paul Brown Stadium", null, cityService.findByName("Cincinnati").get()));
            stadiumService.save(new Stadium("Paycom Center", null, cityService.findByName("Oklahoma City").get()));
            stadiumService.save(new Stadium("PayPal Park", null, cityService.findByName("San Jose").get()));
            stadiumService.save(new Stadium("Peoria Sports Complex", null, cityService.findByName("Peoria").get()));
            stadiumService.save(new Stadium("Petco Park", null, cityService.findByName("San Diego").get()));
            stadiumService.save(new Stadium("PNC Arena", null, cityService.findByName("Raleigh").get()));
            stadiumService.save(new Stadium("PNC Park", null, cityService.findByName("Pittsburgh").get()));
            stadiumService.save(new Stadium("PPG Paints Arena", null, cityService.findByName("Pittsburgh").get()));
            stadiumService.save(new Stadium("Progressive Field", null, cityService.findByName("Cleveland").get()));
            stadiumService.save(new Stadium("Providence Park", null, cityService.findByName("Portland").get()));
            stadiumService.save(new Stadium("Prudential Center", null, cityService.findByName("Newark").get()));
            stadiumService.save(new Stadium("Publix Field at Joker Marchant Stadium", null, cityService.findByName("Lakeland").get()));
            stadiumService.save(new Stadium("Q2 Stadium", null, cityService.findByName("Austin").get()));
            stadiumService.save(new Stadium("Raymond James Stadium", null, cityService.findByName("Tampa").get()));
            stadiumService.save(new Stadium("Red Bull Arena", null, cityService.findByName("Harrison").get()));
            stadiumService.save(new Stadium("RingCentral Coliseum", null, cityService.findByName("Oakland").get()));
            stadiumService.save(new Stadium("Rio Tinto Stadium", null, cityService.findByName("Sandy").get()));
            stadiumService.save(new Stadium("Rocket Mortgage FieldHouse", null, cityService.findByName("Cleveland").get()));
            stadiumService.save(new Stadium("Roger Dean Chevrolet Stadium", null, cityService.findByName("Jupiter").get()));
            stadiumService.save(new Stadium("Rogers Place", null, cityService.findByName("Edmonton").get()));
            stadiumService.save(new Stadium("Salt River Fields at Talking Stick", null, cityService.findByName("Salt River Pima–Maricopa Indian Community").get()));
            stadiumService.save(new Stadium("SAP Center at San Jose", null, cityService.findByName("San Jose").get()));
            stadiumService.save(new Stadium("Scottsdale Stadium", null, cityService.findByName("Scottsdale").get()));
            stadiumService.save(new Stadium("Sloan Park", null, cityService.findByName("Mesa").get()));
            stadiumService.save(new Stadium("Smoothie King Center", null, cityService.findByName("New Orleans").get()));
            stadiumService.save(new Stadium("SoFi Stadium", null, cityService.findByName("Inglewood").get()));
            stadiumService.save(new Stadium("Soldier Field", null, cityService.findByName("Chicago").get()));
            stadiumService.save(new Stadium("Spectrum Center", null, cityService.findByName("Charlotte").get()));
            stadiumService.save(new Stadium("Staples Center", null, cityService.findByName("Los Angeles").get()));
            stadiumService.save(new Stadium("State Farm Arena", null, cityService.findByName("Atlanta").get()));
            stadiumService.save(new Stadium("State Farm Stadium", null, cityService.findByName("Glendale").get()));
            stadiumService.save(new Stadium("Subaru Park", null, cityService.findByName("Chester").get()));
            stadiumService.save(new Stadium("Surprise Stadium", null, cityService.findByName("Surprise").get()));
            stadiumService.save(new Stadium("Target Center", null, cityService.findByName("Minneapolis").get()));
            stadiumService.save(new Stadium("Target Field", null, cityService.findByName("Minneapolis").get()));
            stadiumService.save(new Stadium("TD Ballpark", null, cityService.findByName("Dunedin").get()));
            stadiumService.save(new Stadium("TD Garden", null, cityService.findByName("Boston").get()));
            stadiumService.save(new Stadium("Tempe Diablo Stadium", null, cityService.findByName("Tempe").get()));
            stadiumService.save(new Stadium("TIAA Bank Field", null, cityService.findByName("Jacksonville").get()));
            stadiumService.save(new Stadium("Toyota Center", null, cityService.findByName("Houston").get()));
            stadiumService.save(new Stadium("Toyota Stadium", null, cityService.findByName("Frisco").get()));
            stadiumService.save(new Stadium("TQL Stadium", null, cityService.findByName("Cincinnati").get()));
            stadiumService.save(new Stadium("Tropicana Field", null, cityService.findByName("St. Petersburg").get()));
            stadiumService.save(new Stadium("Truist Park", null, cityService.findByName("Cumberland").get()));
            stadiumService.save(new Stadium("T-Mobile Arena", null, cityService.findByName("Paradise").get()));
            stadiumService.save(new Stadium("T-Mobile Park", null, cityService.findByName("Seattle").get()));
            stadiumService.save(new Stadium("UBS Arena", null, cityService.findByName("Elmont").get()));
            stadiumService.save(new Stadium("United Center", null, cityService.findByName("Chicago").get()));
            stadiumService.save(new Stadium("U.S. Bank Stadium", null, cityService.findByName("Minneapolis").get()));
            stadiumService.save(new Stadium("Vivint Arena", null, cityService.findByName("Salt Lake City").get()));
            stadiumService.save(new Stadium("Wells Fargo Center", null, cityService.findByName("Philadelphia").get()));
            stadiumService.save(new Stadium("Wrigley Field", null, cityService.findByName("Chicago").get()));
            stadiumService.save(new Stadium("Xcel Energy Center", null, cityService.findByName("Saint Paul").get()));
            stadiumService.save(new Stadium("Yankee Stadium", null, cityService.findByName("New York City (The Bronx)").get()));
            // CAN
            stadiumService.save(new Stadium("BC Place", null, cityService.findByName("Vancouver").get()));
            stadiumService.save(new Stadium("BMO Field", null, cityService.findByName("Toronto").get()));
            stadiumService.save(new Stadium("Canada Life Centre", null, cityService.findByName("Winnipeg").get()));
            stadiumService.save(new Stadium("Canadian Tire Centre", null, cityService.findByName("Ottawa").get()));
            stadiumService.save(new Stadium("Centre Bell", null, cityService.findByName("Montreal").get()));
            stadiumService.save(new Stadium("Rogers Arena", null, cityService.findByName("Vancouver").get()));
            stadiumService.save(new Stadium("Rogers Centre", null, cityService.findByName("Toronto").get()));
            stadiumService.save(new Stadium("Saputo Stadium", null, cityService.findByName("Montreal").get()));
            stadiumService.save(new Stadium("Scotiabank Saddledome", null, cityService.findByName("Calgary").get()));
            stadiumService.save(new Stadium("Scotiabank Arena", null, cityService.findByName("Toronto").get()));
        }

        // TEAMS
        if (teamService.count() == 0) {
            // If there are still no teams in DB, we insert them
            // USA
            teamService.save(new Team("ARI", "Arizona Diamondbacks", "Diamondbacks", "A71930", "E3D4AD", "000000", cityService.findByName("Phoenix").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Chase Field").get(), stadiumService.findByName("Salt River Fields at Talking Stick").get()));
            teamService.save(new Team("ATL", "Atlanta Braves", "Braves", "CE1141", "13274F", "EAAA00", cityService.findByName("Cumberland").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Truist Park").get(), stadiumService.findByName("CoolToday Park").get()));
            teamService.save(new Team("BAL", "Baltimore Orioles", "Orioles", "DF4601", "000000", null, cityService.findByName("Baltimore").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Oriole Park at Camden Yards").get(), stadiumService.findByName("Ed Smith Stadium").get()));
            teamService.save(new Team("BOS", "Boston Red Sox", "Red Sox", "BD3039", "0C2340", "FFFFFF", cityService.findByName("Boston").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Fenway Park").get(), stadiumService.findByName("JetBlue Park at Fenway South").get()));
            teamService.save(new Team("CHC", "Chicago Cubs", "Cubs", "0E3386", "CC3433", null, cityService.findByName("Chicago").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Wrigley Field").get(), stadiumService.findByName("Sloan Park").get()));
            teamService.save(new Team("CWS", "Chicago White Sox", "White Sox", "27251F", "C4CED4", "FFFFFF", cityService.findByName("Chicago").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Guaranteed Rate Field").get(), stadiumService.findByName("Camelback Ranch-Glendale").get()));
            teamService.save(new Team("CIN", "Cincinnati Reds", "Reds", "C6011F", "000000", null, cityService.findByName("Cincinnati").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Great American Ball Park").get(), stadiumService.findByName("Goodyear Ballpark").get()));
            teamService.save(new Team("CLE", "Cleveland Indians", "Indians", "0C2340", "E31937", null, cityService.findByName("Cleveland").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Progressive Field").get(), stadiumService.findByName("Goodyear Ballpark").get()));
            teamService.save(new Team("COL", "Colorado Rockies", "Rockies", "33006F", "C4CED4", "000000", cityService.findByName("Denver").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Coors Field").get(), stadiumService.findByName("Salt River Fields at Talking Stick").get()));
            teamService.save(new Team("DET", "Detroit Tigers", "Tigers", "0C2340", "FA4616", null, cityService.findByName("Detroit").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Comerica Park").get(), stadiumService.findByName("Publix Field at Joker Marchant Stadium").get()));
            teamService.save(new Team("HOU", "Houston Astros", "Astros", "002D62", "EB6E1F", "F4911E", cityService.findByName("Houston").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Minute Maid Park").get(), stadiumService.findByName("FITTEAM Ballpark of the Palm Beaches").get()));
            teamService.save(new Team("KC", "Kansas City Royals", "Royals", "004687", "BD9B60", null, cityService.findByName("Kansas City [MO]").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Kauffman Stadium").get(), stadiumService.findByName("Surprise Stadium").get()));
            teamService.save(new Team("LAA", "Los Angeles Angels", "Angels", "003263", "BA0021", "862633", cityService.findByName("Anaheim").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Angel Stadium").get(), stadiumService.findByName("Tempe Diablo Stadium").get()));
            teamService.save(new Team("LAD", "Los Angeles Dodgers", "Dodgers", "005A9C", "EF3E42", "A5ACAF", cityService.findByName("Los Angeles").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Dodger Stadium").get(), stadiumService.findByName("Camelback Ranch-Glendale").get()));
            teamService.save(new Team("OAK", "Oakland Athletics", "Athletics", "003831", "EFB21E", "A2AAAD", cityService.findByName("Oakland").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("RingCentral Coliseum").get(), stadiumService.findByName("Hohokam Stadium").get()));
            teamService.save(new Team("MIA", "Miami Marlins", "Marlins", "00A3E0", "EF3340", "000000", cityService.findByName("Miami").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("LoanDepot Park").get(), stadiumService.findByName("Roger Dean Chevrolet Stadium").get()));
            teamService.save(new Team("MIL", "Milwaukee Brewers", "Brewers", "FFC52F", "12284B", null, cityService.findByName("Milwaukee").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("American Family Field").get(), stadiumService.findByName("American Family Fields of Phoenix").get()));
            teamService.save(new Team("MIN", "Minnesota Twins", "Twins", "002B5C", "D31145", "B9975B", cityService.findByName("Minneapolis").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Target Field").get(), stadiumService.findByName("Hammond Stadium").get()));
            teamService.save(new Team("NYM", "New York Mets", "Mets", "002D72", "FF5910", null, cityService.findByName("New York City (Queens)").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Citi Field").get(), stadiumService.findByName("Clover Park").get()));
            teamService.save(new Team("NYY", "New York Yankees", "Yankees", "0C2340", "C4CED3", null, cityService.findByName("New York City (The Bronx)").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Yankee Stadium").get(), stadiumService.findByName("George M. Steinbrenner Field").get()));
            teamService.save(new Team("PHI", "Philadelphia Phillies", "Phillies", "E81828", "002D72", null, cityService.findByName("Philadelphia").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Citizens Bank Park").get(), stadiumService.findByName("BayCare Ballpark").get()));
            teamService.save(new Team("PIT", "Pittsburgh Pirates", "Pirates", "27251F", "FDB827", null, cityService.findByName("Pittsburgh").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("PNC Park").get(), stadiumService.findByName("LECOM Park").get()));
            teamService.save(new Team("SD", "San Diego Padres", "Padres", "2F241D", "FFC425", "002D62", cityService.findByName("San Diego").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Petco Park").get(), stadiumService.findByName("Peoria Sports Complex").get()));
            teamService.save(new Team("SF", "San Francisco Giants", "Giants", "FD5A1E", "27251F", "EFD19F", cityService.findByName("San Francisco").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Oracle Park").get(), stadiumService.findByName("Scottsdale Stadium").get()));
            teamService.save(new Team("SEA", "Seattle Mariners", "Mariners", "0C2C56", "005C5C", "C4CED4", cityService.findByName("Seattle").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("T-Mobile Park").get(), stadiumService.findByName("Peoria Sports Complex").get()));
            teamService.save(new Team("STL", "St. Louis Cardinals", "Cardinals", "C41E3A", "0C2340", "FEDB00", cityService.findByName("St. Louis").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Busch Stadium").get(), stadiumService.findByName("Roger Dean Chevrolet Stadium").get()));
            teamService.save(new Team("TB", "Tampa Bay Rays", "Rays", "092C5C", "8FBCE6", "F5D130", cityService.findByName("St. Petersburg").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Tropicana Field").get(), stadiumService.findByName("Charlotte Sports Park").get()));
            teamService.save(new Team("TEX", "Texas Rangers", "Rangers", "003278", "C0111F", null, cityService.findByName("Arlington").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Globe Life Field").get(), stadiumService.findByName("Surprise Stadium").get()));
            teamService.save(new Team("WSH", "Washington Nationals", "Nationals", "AB0003", "14225A", "FFFFFF", cityService.findByName("WashingtonD.C.").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Nationals Park").get(), stadiumService.findByName("FITTEAM Ballpark of the Palm Beaches").get()));
            teamService.save(new Team("ATL", "Atlanta Hawks", "Hawks", "E03A3E", "C1D32F", "26282A", cityService.findByName("Atlanta").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("State Farm Arena").get(), null));
            teamService.save(new Team("BOS", "Boston Celtics", "Celtics", "007A33", "BA9653", "FFFFFF", cityService.findByName("Boston").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("TD Garden").get(), null));
            teamService.save(new Team("BKN", "Brooklyn Nets", "Nets", "000000", "FFFFFF", null, cityService.findByName("New York City (Brooklyn)").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Barclays Center").get(), null));
            teamService.save(new Team("CHA", "Charlotte Hornets", "Hornets", "1D1160", "00788C", "A1A1A4", cityService.findByName("Charlotte").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Spectrum Center").get(), null));
            teamService.save(new Team("CHI", "Chicago Bulls", "Bulls", "CE1141", "000000", null, cityService.findByName("Chicago").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("United Center").get(), null));
            teamService.save(new Team("CLE", "Cleveland Cavaliers", "Cavaliers", "860038", "041E42", "FDBB30", cityService.findByName("Cleveland").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Rocket Mortgage FieldHouse").get(), null));
            teamService.save(new Team("DAL", "Dallas Mavericks", "Mavericks", "00538C", "002B5E", "B8C4CA", cityService.findByName("Dallas").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("American Airlines Center").get(), null));
            teamService.save(new Team("DEN", "Denver Nuggets", "Nuggets", "0E2240", "FEC524", "8B2131", cityService.findByName("Denver").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Ball Arena").get(), null));
            teamService.save(new Team("DET", "Detroit Pistons", "Pistons", "C8102E", "1D42BA", "BEC0C2", cityService.findByName("Detroit").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Little Caesars Arena").get(), null));
            teamService.save(new Team("GSW", "Golden State Warriors", "Warriors", "1D428A", "FFC72C", null, cityService.findByName("San Francisco").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Chase Center").get(), null));
            teamService.save(new Team("HOU", "Houston Rockets", "Rockets", "CE1141", "000000", "C4CED4", cityService.findByName("Houston").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Toyota Center").get(), null));
            teamService.save(new Team("IND", "Indiana Pacers", "Pacers", "002D62", "FDBB30", "BEC0C2", cityService.findByName("Indianapolis").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Gainbridge Fieldhouse").get(), null));
            teamService.save(new Team("LAC", "Los Angeles Clippers", "Clippers", "C8102E", "1D428A", "BEC0C2", cityService.findByName("Los Angeles").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Staples Center").get(), null));
            teamService.save(new Team("LAL", "Los Angeles Lakers", "Lakers", "552583", "FDB927", "000000", cityService.findByName("Los Angeles").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Staples Center").get(), null));
            teamService.save(new Team("MEM", "Memphis Grizzlies", "Grizzlies", "5D76A9", "12173F", "F5B112", cityService.findByName("Memphis").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("FedExForum").get(), null));
            teamService.save(new Team("MIA", "Miami Heat", "Heat", "98002E", "F9A01B", "000000", cityService.findByName("Miami").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("American Airlines Arena").get(), null));
            teamService.save(new Team("MIL", "Milwaukee Bucks", "Bucks", "00471B", "EEE1C6", "000000", cityService.findByName("Milwaukee").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Fiserv Forum").get(), null));
            teamService.save(new Team("MIN", "Minnesota Timberwolves", "Timberwolves", "0C2340", "236192", "78BE20", cityService.findByName("Minneapolis").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Target Center").get(), null));
            teamService.save(new Team("NOP", "New Orleans Pelicans", "Pelicans", "0C2340", "C8102E", "85714D", cityService.findByName("New Orleans").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Smoothie King Center").get(), null));
            teamService.save(new Team("NYK", "New York Knicks", "Knicks", "006BB6", "F58426", "BEC0C2", cityService.findByName("New York City (Manhattan)").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Madison Square Garden").get(), null));
            teamService.save(new Team("OKC", "Oklahoma City Thunder", "Thunder", "007AC1", "EF3B24", "002D62", cityService.findByName("Oklahoma City").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Paycom Center").get(), null));
            teamService.save(new Team("ORL", "Orlando Magic", "Magic", "0077C0", "C4CED4", "000000", cityService.findByName("Orlando").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Amway Center").get(), null));
            teamService.save(new Team("PHI", "Philadelphia 76ers", "76ers", "006BB6", "ED174C", "002B5C", cityService.findByName("Philadelphia").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Wells Fargo Center").get(), null));
            teamService.save(new Team("PHX", "Phoenix Suns", "Suns", "1D1160", "E56020", "000000", cityService.findByName("Phoenix").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Footprint Center").get(), null));
            teamService.save(new Team("POR", "Portland Trail Blazers", "Trail Blazers", "E03A3E", "000000", null, cityService.findByName("Portland").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Moda Center").get(), null));
            teamService.save(new Team("SAC", "Sacramento Kings", "Kings", "5A2D81", "63727A", "000000", cityService.findByName("Sacramento").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Golden 1 Center").get(), null));
            teamService.save(new Team("SAS", "San Antonio Spurs", "Spurs", "C4CED4", "000000", null, cityService.findByName("San Antonio").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("AT&T Center").get(), null));
            teamService.save(new Team("UTA", "Utah Jazz", "Jazz", "002B5C", "00471B", "F9A01B", cityService.findByName("Salt Lake City").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Vivint Arena").get(), null));
            teamService.save(new Team("WAS", "Washington Wizards", "Wizards", "002B5C", "E31837", "C4CED4", cityService.findByName("WashingtonD.C.").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Capital One Arena").get(), null));
            teamService.save(new Team("ARI", "Arizona Cardinals", "Cardinals", "97233F", "000000", "FFB612", cityService.findByName("Glendale").get(), sportService.findByName("Football").get(), stadiumService.findByName("State Farm Stadium").get(), null));
            teamService.save(new Team("ATL", "Atlanta Falcons", "Falcons", "A71930", "000000", "A5ACAF", cityService.findByName("Atlanta").get(), sportService.findByName("Football").get(), stadiumService.findByName("Mercedes-Benz Stadium").get(), null));
            teamService.save(new Team("BAL", "Baltimore Ravens", "Ravens", "241773", "000000", "9E7C0C", cityService.findByName("Baltimore").get(), sportService.findByName("Football").get(), stadiumService.findByName("M&T Bank Stadium").get(), null));
            teamService.save(new Team("BUF", "Buffalo Bills", "Bills", "00338D", "C60C30", null, cityService.findByName("Orchard Park").get(), sportService.findByName("Football").get(), stadiumService.findByName("Highmark Stadium").get(), null));
            teamService.save(new Team("CAR", "Carolina Panthers", "Panthers", "0085CA", "101820", "BFC0BF", cityService.findByName("Charlotte").get(), sportService.findByName("Football").get(), stadiumService.findByName("Bank of America Stadium").get(), null));
            teamService.save(new Team("CHI", "Chicago Bears", "Bears", "0B162A", "C83803", "", cityService.findByName("Chicago").get(), sportService.findByName("Football").get(), stadiumService.findByName("Soldier Field").get(), null));
            teamService.save(new Team("CIN", "Cincinnati Bengals", "Bengals", "FB4F14", "000000", null, cityService.findByName("Cincinnati").get(), sportService.findByName("Football").get(), stadiumService.findByName("Paul Brown Stadium").get(), null));
            teamService.save(new Team("CLE", "Cleveland Browns", "Browns", "311D00", "FF3C00", "FFFFFF", cityService.findByName("Cleveland").get(), sportService.findByName("Football").get(), stadiumService.findByName("FirstEnergy Stadium").get(), null));
            teamService.save(new Team("DAL", "Dallas Cowboys", "Cowboys", "003594", "041E42", "869397", cityService.findByName("Arlington").get(), sportService.findByName("Football").get(), stadiumService.findByName("AT&T Stadium").get(), null));
            teamService.save(new Team("DEN", "Denver Broncos", "Broncos", "FB4F14", "002244", null, cityService.findByName("Denver").get(), sportService.findByName("Football").get(), stadiumService.findByName("Empower Field at Mile High").get(), null));
            teamService.save(new Team("DET", "Detroit Lions", "Lions", "0076B6", "B0B7BC", "000000", cityService.findByName("Detroit").get(), sportService.findByName("Football").get(), stadiumService.findByName("Ford Field").get(), null));
            teamService.save(new Team("GB", "Green Bay Packers", "Packers", "203731", "FFB612", null, cityService.findByName("Green Bay").get(), sportService.findByName("Football").get(), stadiumService.findByName("Lambeau Field").get(), null));
            teamService.save(new Team("HOU", "Houston Texans", "Texans", "03202F", "A71930", null, cityService.findByName("Houston").get(), sportService.findByName("Football").get(), stadiumService.findByName("NRG Stadium").get(), null));
            teamService.save(new Team("IND", "Indianapolis Colts", "Colts", "002C5F", "A2AAAD", null, cityService.findByName("Indianapolis").get(), sportService.findByName("Football").get(), stadiumService.findByName("Lucas Oil Stadium").get(), null));
            teamService.save(new Team("JAX", "Jacksonville Jaguars", "Jaguars", "101820", "D7A22A", "006778", cityService.findByName("Jacksonville").get(), sportService.findByName("Football").get(), stadiumService.findByName("TIAA Bank Field").get(), null));
            teamService.save(new Team("KC", "Kansas City Chiefs", "Chiefs", "E31837", "FFB81C", null, cityService.findByName("Kansas City [MO]").get(), sportService.findByName("Football").get(), stadiumService.findByName("Arrowhead Stadium").get(), null));
            teamService.save(new Team("LV", "Las Vegas Raiders", "Raiders", "000000", "A5ACAF", null, cityService.findByName("Paradise").get(), sportService.findByName("Football").get(), stadiumService.findByName("Allegiant Stadium").get(), null));
            teamService.save(new Team("LAC", "Los Angeles Chargers", "Chargers", "0080C6", "FFC20E", "FFFFFF", cityService.findByName("Inglewood").get(), sportService.findByName("Football").get(), stadiumService.findByName("SoFi Stadium").get(), null));
            teamService.save(new Team("LAR", "Los Angeles Rams", "Rams", "003594", "FFA300", "FFD100", cityService.findByName("Inglewood").get(), sportService.findByName("Football").get(), stadiumService.findByName("SoFi Stadium").get(), null));
            teamService.save(new Team("MIA", "Miami Dolphins", "Dolphins", "008E97", "FC4C02", "005778", cityService.findByName("Miami Gardens").get(), sportService.findByName("Football").get(), stadiumService.findByName("Hard Rock Stadium").get(), null));
            teamService.save(new Team("MIN", "Minnesota Vikings", "Vikings", "4F2683", "FFC62F", null, cityService.findByName("Minneapolis").get(), sportService.findByName("Football").get(), stadiumService.findByName("U.S. Bank Stadium").get(), null));
            teamService.save(new Team("NE", "New England Patriots", "Patriots", "002244", "C60C30", "B0B7BC", cityService.findByName("Foxborough").get(), sportService.findByName("Football").get(), stadiumService.findByName("Gillette Stadium").get(), null));
            teamService.save(new Team("NO", "New Orleans Saints", "Saints", "D3BC8D", "101820", null, cityService.findByName("New Orleans").get(), sportService.findByName("Football").get(), stadiumService.findByName("Caesars Superdome").get(), null));
            teamService.save(new Team("NYG", "New York Giants", "Giants", "0B2265", "A71930", "A5ACAF", cityService.findByName("East Rutherford").get(), sportService.findByName("Football").get(), stadiumService.findByName("MetLife Stadium").get(), null));
            teamService.save(new Team("NYJ", "New York Jets", "Jets", "125740", "000000", "FFFFFF", cityService.findByName("East Rutherford").get(), sportService.findByName("Football").get(), stadiumService.findByName("MetLife Stadium").get(), null));
            teamService.save(new Team("PHI", "Philadelphia Eagles", "Eagles", "004C54", "A5ACAF", "ACC0C6", cityService.findByName("Philadelphia").get(), sportService.findByName("Football").get(), stadiumService.findByName("Lincoln Financial Field").get(), null));
            teamService.save(new Team("PIT", "Pittsburgh Steelers", "Steelers", "FFB612", "101820", null, cityService.findByName("Pittsburgh").get(), sportService.findByName("Football").get(), stadiumService.findByName("Heinz Field").get(), null));
            teamService.save(new Team("SF", "San Francisco 49ers", "49ers", "AA0000", "B3995D", null, cityService.findByName("Santa Clara").get(), sportService.findByName("Football").get(), stadiumService.findByName("Levi's Stadium").get(), null));
            teamService.save(new Team("SEA", "Seattle Seahawks", "Seahawks", "002244", "69BE28", "A5ACAF", cityService.findByName("Seattle").get(), sportService.findByName("Football").get(), stadiumService.findByName("Lumen Field").get(), null));
            teamService.save(new Team("TB", "Tampa Bay Buccaneers", "Buccaneers", "D50A0A", "34302B", "B1BABF", cityService.findByName("Tampa").get(), sportService.findByName("Football").get(), stadiumService.findByName("Raymond James Stadium").get(), null));
            teamService.save(new Team("TEN", "Tennessee Titans", "Titans", "0C2340", "4B92DB", "C8102E", cityService.findByName("Nashville").get(), sportService.findByName("Football").get(), stadiumService.findByName("Nissan Stadium").get(), null));
            teamService.save(new Team("WAS", "Washington Football Team", "Football Team", "773141", "FFB612", null, cityService.findByName("Landover").get(), sportService.findByName("Football").get(), stadiumService.findByName("FedExField").get(), null));
            teamService.save(new Team("ANA", "Anaheim Ducks", "Ducks", "F47A38", "B9975B", "C1C6C8", cityService.findByName("Anaheim").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Honda Center").get(), null));
            teamService.save(new Team("ARI", "Arizona Coyotes", "Coyotes", "8C2633", "E2D6B5", "111111", cityService.findByName("Glendale").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Gila River Arena").get(), null));
            teamService.save(new Team("BOS", "Boston Bruins", "Bruins", "FFB81C", "000000", null, cityService.findByName("Boston").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("TD Garden").get(), null));
            teamService.save(new Team("BUF", "Buffalo Sabres", "Sabres", "002654", "FCB514", "ADAFAA", cityService.findByName("Buffalo").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("KeyBank Center").get(), null));
            teamService.save(new Team("CAR", "Carolina Hurricanes", "Hurricanes", "CC0000", "000000", "A2AAAD", cityService.findByName("Raleigh").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("PNC Arena").get(), null));
            teamService.save(new Team("CHI", "Chicago Blackhawks", "Blackhawks", "CF0A2C", "FFD100", "001970", cityService.findByName("Chicago").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("United Center").get(), null));
            teamService.save(new Team("COL", "Colorado Avalanche", "Avalanche", "6F263D", "236192", "A2AAAD", cityService.findByName("Denver").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Ball Arena").get(), null));
            teamService.save(new Team("CBJ", "Columbus Blue Jackets", "Blue Jackets", "002654", "CE1126", "A4A9AD", cityService.findByName("Columbus").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Nationwide Arena").get(), null));
            teamService.save(new Team("DAL", "Dallas Stars", "Stars", "006847", "8F8F8C", "111111", cityService.findByName("Dallas").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("American Airlines Center").get(), null));
            teamService.save(new Team("DET", "Detroit Red Wings", "Red Wings", "CE1126", "FFFFFF", null, cityService.findByName("Detroit").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Little Caesars Arena").get(), null));
            teamService.save(new Team("FLA", "Florida Panthers", "Panthers", "041E42", "C8102E", "B9975B", cityService.findByName("Sunrise").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("BB&T Center").get(), null));
            teamService.save(new Team("LAK", "Los Angeles Kings", "Kings", "111111", "A2AAAD", "FFFFFF", cityService.findByName("Los Angeles").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Staples Center").get(), null));
            teamService.save(new Team("MIN", "Minnesota Wild", "Wild", "A6192E", "154734", "EAAA00", cityService.findByName("Saint Paul").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Xcel Energy Center").get(), null));
            teamService.save(new Team("NSH", "Nashville Predators", "Predators", "FFB81C", "041E42", "FFFFFF", cityService.findByName("Nashville").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Bridgestone Arena").get(), null));
            teamService.save(new Team("NJD", "New Jersey Devils", "Devils", "CE1126", "000000", "FFFFFF", cityService.findByName("Newark").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Prudential Center").get(), null));
            teamService.save(new Team("NYI", "New York Islanders", "Islanders", "00539B", "F47D30", null, cityService.findByName("Elmont").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("UBS Arena").get(), null));
            teamService.save(new Team("NYR", "New York Rangers", "Rangers", "0038A8", "CE1126", "FFFFFF", cityService.findByName("New York City (Manhattan)").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Madison Square Garden").get(), null));
            teamService.save(new Team("PHI", "Philadelphia Flyers", "Flyers", "F74902", "000000", "FFFFFF", cityService.findByName("Philadelphia").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Wells Fargo Center").get(), null));
            teamService.save(new Team("PIT", "Pittsburgh Penguins", "Penguins", "000000", "FCB514", "CFC493", cityService.findByName("Pittsburgh").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("PPG Paints Arena").get(), null));
            teamService.save(new Team("SJS", "San Jose Sharks", "Sharks", "006D75", "EA7200", "000000", cityService.findByName("San Jose").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("SAP Center at San Jose").get(), null));
            teamService.save(new Team("SEA", "Seattle Kraken", "Kraken", "001628", "99D9D9", "E9072B", cityService.findByName("Seattle").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Climate Pledge Arena").get(), null));
            teamService.save(new Team("STL", "St. Louis Blues", "Blues", "002F87", "FCB514", "041E42", cityService.findByName("St. Louis").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Enterprise Center").get(), null));
            teamService.save(new Team("TBL", "Tampa Bay Lightning", "Lightning", "002868", "FFFFFF", null, cityService.findByName("Tampa").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Amalie Arena").get(), null));
            teamService.save(new Team("VGK", "Vegas Golden Knights", "Knights", "B4975A", "333F42", "C8102E", cityService.findByName("Paradise").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("T-Mobile Arena").get(), null));
            teamService.save(new Team("WSH", "Washington Capitals", "Capitals", "041E42", "C8102E", "FFFFFF", cityService.findByName("WashingtonD.C.").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Capital One Arena").get(), null));
            teamService.save(new Team("ATL", "Atlanta United FC", "Atlanta", "80000A", "221F1F", "A19060", cityService.findByName("Atlanta").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Mercedes-Benz Stadium").get(), null));
            teamService.save(new Team("ATX", "Austin FC", "Austin", "00B140", "000000", "FFFFFF", cityService.findByName("Austin").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Q2 Stadium").get(), null));
            teamService.save(new Team("CHI", "Chicago Fire FC", "Chicago", "06134E", "C2002F", "FFB600", cityService.findByName("Chicago").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Soldier Field").get(), null));
            teamService.save(new Team("COL", "Colorado Rapids", "Colorado", "862633", "8BB8E8", "8D9093", cityService.findByName("Commerce City").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Dick's Sporting Goods Park").get(), null));
            teamService.save(new Team("CLB", "Columbus Crew", "Columbus", "FEF200", "231F20", null, cityService.findByName("Columbus").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Lower.com Field").get(), null));
            teamService.save(new Team("DC", "D.C. United", "D.C.", "EE1A39", "231F20", null, cityService.findByName("WashingtonD.C.").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Audi Field").get(), null));
            teamService.save(new Team("DAL", "FC Dallas", "Dallas", "D11241", "003E7E", "D1D5D8", cityService.findByName("Frisco").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Toyota Stadium").get(), null));
            teamService.save(new Team("CIN", "FC Cincinnati", "Cincinnati", "FE5000", "003087", "041E42", cityService.findByName("Cincinnati").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("TQL Stadium").get(), null));
            teamService.save(new Team("HOU", "Houston Dynamo FC", "Houston", "F4911E", "92C3F1", "231F20", cityService.findByName("Houston").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("BBVA Stadium").get(), null));
            teamService.save(new Team("MIA", "Inter Miami CF", "Miami", "F7B5CD", "231F20", null, cityService.findByName("Fort Lauderdale").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("DRV PNK Stadium").get(), null));
            teamService.save(new Team("LA", "LA Galaxy", "LA", "00245D", "0065A4", "FFD200", cityService.findByName("Carson").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Dignity Health Sports Park").get(), null));
            teamService.save(new Team("LAFC", "Los Angeles FC", "LAFC", "000000", "C39E6D", "55565A", cityService.findByName("Los Angeles").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Banc of California Stadium").get(), null));
            teamService.save(new Team("MIN", "Minnesota United FC", "Minnesota", "585958", "231F20", "DF2426", cityService.findByName("Saint Paul").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Allianz Field").get(), null));
            teamService.save(new Team("NSH", "Nashville SC", "Nashville", "E8E03F", "181D52", null, cityService.findByName("Nashville").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Nissan Stadium").get(), null));
            teamService.save(new Team("NE", "New England Revolution", "New England", "E51938", "002B5C", null, cityService.findByName("Foxborough").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Gillette Stadium").get(), null));
            teamService.save(new Team("NYC", "New York City FC", "New York City", "6CACE4", "041E42", "F15524", cityService.findByName("New York City (The Bronx)").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Yankee Stadium").get(), null));
            teamService.save(new Team("RBNY", "New York Red Bulls", "New York", "E31351", "002F65", "FFC425", cityService.findByName("Harrison").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Red Bull Arena").get(), null));
            teamService.save(new Team("ORL", "Orlando City SC", "Orlando", "61259E", "FFE293", null, cityService.findByName("Orlando").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Exploria Stadium").get(), null));
            teamService.save(new Team("PHI", "Philadelphia Union", "Philadelphia", "002D55", "5090CD", "B38707", cityService.findByName("Chester").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Subaru Park").get(), null));
            teamService.save(new Team("POR", "Portland Timbers", "Portland", "00482B", "D69A00", null, cityService.findByName("Portland").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Providence Park").get(), null));
            teamService.save(new Team("RSL", "Real Salt Lake", "Salt Lake", "B30838", "013A81", "F5E700", cityService.findByName("Sandy").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Rio Tinto Stadium").get(), null));
            teamService.save(new Team("SJ", "San Jose Earthquakes", "San Jose", "1F1F1F", "30457A", "A42A35", cityService.findByName("San Jose").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("PayPal Park").get(), null));
            teamService.save(new Team("SEA", "Seattle Sounders FC", "Seattle", "236192", "658D1B", "1D252D", cityService.findByName("Seattle").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Lumen Field").get(), null));
            teamService.save(new Team("SKC", "Sporting Kansas City", "Kansas City", "93B1D7", "002A5C", "A0A1A5", cityService.findByName("Kansas City [KS]").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Children's Mercy Park").get(), null));
            // CAN
            teamService.save(new Team("TOR", "Toronto Blue Jays", "Blue Jays", "134A8E", "1D2D5C", "E8291C", cityService.findByName("Toronto").get(), sportService.findByName("Baseball").get(), stadiumService.findByName("Rogers Centre").get(), stadiumService.findByName("TD Ballpark").get()));
            teamService.save(new Team("TOR", "Toronto Raptors", "Raptors", "CE1141", "000000", "A1A1A4", cityService.findByName("Toronto").get(), sportService.findByName("Basketball").get(), stadiumService.findByName("Scotiabank Arena").get(), null));
            teamService.save(new Team("CGY", "Calgary Flames", "Flames", "C8102E", "F1BE48", "111111", cityService.findByName("Calgary").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Scotiabank Saddledome").get(), null));
            teamService.save(new Team("EDM", "Edmonton Oilers", "Oilers", "041E42", "FF4C00", null, cityService.findByName("Edmonton").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Rogers Place").get(), null));
            teamService.save(new Team("MTL", "Montreal Canadiens", "Canadiens", "AF1E2D", "192168", null, cityService.findByName("Montreal").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Centre Bell").get(), null));
            teamService.save(new Team("OTT", "Ottawa Senators", "Senators", "C52032", "C2912C", "000000", cityService.findByName("Ottawa").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Canadian Tire Centre").get(), null));
            teamService.save(new Team("TOR", "Toronto Maple Leafs", "Maple Leafs", "00205B", "FFFFFF", null, cityService.findByName("Toronto").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Scotiabank Arena").get(), null));
            teamService.save(new Team("VAN", "Vancouver Canucks", "Canucks", "00205B", "00843D", "041C2C", cityService.findByName("Vancouver").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Rogers Arena").get(), null));
            teamService.save(new Team("WPG", "Winnipeg Jets", "Winnipeg Jets", "041E42", "004C97", "AC162C", cityService.findByName("Winnipeg").get(), sportService.findByName("Hockey").get(), stadiumService.findByName("Canada Life Centre").get(), null));
            teamService.save(new Team("MTL", "CF Montréal", "Montréal", "2B63AD", "373536", "BBC3C6", cityService.findByName("Montreal").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("Saputo Stadium").get(), null));
            teamService.save(new Team("TOR", "Toronto FC", "Toronto", "AB1E2D", "3F4743", "A3AAAD", cityService.findByName("Toronto").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("BMO Field").get(), null));
            teamService.save(new Team("VAN", "Vancouver Whitecaps FC", "Vancouver", "04265C", "94C2E4", "84868C", cityService.findByName("Vancouver").get(), sportService.findByName("Soccer").get(), stadiumService.findByName("BC Place").get(), null));
        }
    }

}