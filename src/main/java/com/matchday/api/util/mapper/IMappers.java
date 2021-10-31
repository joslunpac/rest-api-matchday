package com.matchday.api.util.mapper;

import com.matchday.api.model.dto.CityDto;
import com.matchday.api.model.dto.CompetitionDto;
import com.matchday.api.model.dto.CountryDto;
import com.matchday.api.model.dto.GameDto;
import com.matchday.api.model.dto.SongDto;
import com.matchday.api.model.dto.SportDto;
import com.matchday.api.model.dto.StadiumDto;
import com.matchday.api.model.dto.StateDto;
import com.matchday.api.model.dto.TeamDto;
import com.matchday.api.model.entity.City;
import com.matchday.api.model.entity.Competition;
import com.matchday.api.model.entity.Country;
import com.matchday.api.model.entity.Game;
import com.matchday.api.model.entity.Song;
import com.matchday.api.model.entity.Sport;
import com.matchday.api.model.entity.Stadium;
import com.matchday.api.model.entity.State;
import com.matchday.api.model.entity.Team;

public interface IMappers {

    public CityDto convertToDto(City city);

    public City convertToEntity(CityDto city);

    public CompetitionDto convertToDto(Competition competition);

    public Competition convertToEntity(CompetitionDto competition);

    public CountryDto convertToDto(Country country);

    public Country convertToEntity(CountryDto country);

    public GameDto convertToDto(Game game);

    public Game convertToEntity(GameDto game);

    public SongDto convertToDto(Song song);

    public Song convertToEntity(SongDto song);

    public SportDto convertToDto(Sport sport);

    public Sport convertToEntity(SportDto sport);

    public StadiumDto convertToDto(Stadium stadium);

    public Stadium convertToEntity(StadiumDto stadium);

    public StateDto convertToDto(State state);

    public State convertToEntity(StateDto state);

    public TeamDto convertToDto(Team team);

    public Team convertToEntity(TeamDto team);

}
