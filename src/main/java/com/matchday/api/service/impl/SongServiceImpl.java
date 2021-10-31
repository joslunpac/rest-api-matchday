package com.matchday.api.service.impl;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Song;
import com.matchday.api.repository.SongRepository;
import com.matchday.api.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SongServiceImpl implements SongService {

	@Autowired
	SongRepository songRepository;

	@Override
	public long count() {
		return songRepository.count();
	}

	@Override
	public List<Song> findAll(Sort sort) {
		return songRepository.findAll(sort);
	}

	@Override
	public Optional<Song> findById(Long id) {
		return songRepository.findById(id);
	}

	@Override
	@Transactional
	public Song save(Song song) {
		return songRepository.save(song);
	}

	@Override
	public void deleteById(Long id) {
		songRepository.deleteById(id);

	}

	@Override
	public boolean existsById(Long id) {
		return songRepository.existsById(id);
	}

}
