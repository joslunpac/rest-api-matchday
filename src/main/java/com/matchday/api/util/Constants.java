package com.matchday.api.util;

public class Constants {
	// Security
	public static final String SECRET_KEY = "SecretKeyMacthDay";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER = "Authorization";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days

	// Uris
	public static final String V1 = "/v1";
	public static final String ADMIN = "/admin";

	// Parameters de las uris
	public static final String DEFAULT_PAGE = "0";
	public static final String DEFAULT_SIZE = "6";

	// Entities
	public static final String CITY = "City";
	public static final String CITIES = "Cities";
	public static final String COMPETITION = "Competition";
	public static final String COMPETITIONS = "Competitions";
	public static final String COUNTRY = "Country";
	public static final String COUNTRIES = "Countries";
	public static final String GAME = "Game";
	public static final String GAMES = "Games";
	public static final String SONG = "Song";
	public static final String SONGS = "Songs";
	public static final String SPORT = "Sport";
	public static final String SPORTS = "Sports";
	public static final String STADIUM = "Stadium";
	public static final String STADIUMS = "Stadiums";
	public static final String STATE = "State";
	public static final String STATES = "States";
	public static final String TEAM = "Team";
	public static final String TEAMS = "Teams";
	public static final String USER = "User";
	public static final String USERS = "Users";
	public static final String ROLE = "Role";
	public static final String ROLES = "Roles";

	// Exceptions
	public static final String WITH_ID = " with id ";
	public static final String WITH_NAME = " with name ";
	public static final String NOT_FOUND = " not found";
	public static final String ALREADY_EXISTS = " it already exists";

	// OpenApi
	public static final String OA_ENDPOINT = "Endpoint of ";
	public static final String OA_GET_ALL = "Get the complete list of ";
	public static final String OA_GET = "Get one ";
	public static final String OA_CREATE = "Create one ";
	public static final String OA_UPDATE = "Update one ";
	public static final String OA_DELETE = "Delete one ";
	public static final String OA_AUTHENTICATE = "Authenticate a ";
	public static final String OA_REGISTER = "Register a ";

}
