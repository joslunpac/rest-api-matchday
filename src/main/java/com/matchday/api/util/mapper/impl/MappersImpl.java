package com.matchday.api.util.mapper.impl;

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
import com.matchday.api.util.mapper.IMappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappersImpl implements IMappers {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CityDto convertToDto(City city) {
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public City convertToEntity(CityDto city) {
        return modelMapper.map(city, City.class);
    }

    @Override
    public CompetitionDto convertToDto(Competition competition) {
        return modelMapper.map(competition, CompetitionDto.class);
    }

    @Override
    public Competition convertToEntity(CompetitionDto competition) {
        return modelMapper.map(competition, Competition.class);
    }

    @Override
    public CountryDto convertToDto(Country country) {
        return modelMapper.map(country, CountryDto.class);
    }

    @Override
    public Country convertToEntity(CountryDto country) {
        return modelMapper.map(country, Country.class);
    }

    @Override
    public GameDto convertToDto(Game game) {
        return modelMapper.map(game, GameDto.class);
    }

    @Override
    public Game convertToEntity(GameDto game) {
        return modelMapper.map(game, Game.class);
    }

    @Override
    public SongDto convertToDto(Song song) {
        return modelMapper.map(song, SongDto.class);
    }

    @Override
    public Song convertToEntity(SongDto song) {
        return modelMapper.map(song, Song.class);
    }

    @Override
    public SportDto convertToDto(Sport sport) {
        return modelMapper.map(sport, SportDto.class);
    }

    @Override
    public Sport convertToEntity(SportDto sport) {
        return modelMapper.map(sport, Sport.class);
    }

    @Override
    public StadiumDto convertToDto(Stadium stadium) {
        return modelMapper.map(stadium, StadiumDto.class);
    }

    @Override
    public Stadium convertToEntity(StadiumDto stadium) {
        return modelMapper.map(stadium, Stadium.class);
    }

    @Override
    public StateDto convertToDto(State state) {
        return modelMapper.map(state, StateDto.class);
    }

    @Override
    public State convertToEntity(StateDto state) {
        return modelMapper.map(state, State.class);
    }

    @Override
    public TeamDto convertToDto(Team team) {
        return modelMapper.map(team, TeamDto.class);
    }

    @Override
    public Team convertToEntity(TeamDto team) {
        return modelMapper.map(team, Team.class);
    }

}
