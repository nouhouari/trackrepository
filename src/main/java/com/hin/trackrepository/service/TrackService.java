/* 
 * Copyright (c) 2017 Nourreddine HOUARI (houarinourredine@gmail.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.hin.trackrepository.service;

import com.hin.trackrepository.dto.ResultPage;
import com.hin.trackrepository.dto.TrackDTO;
import com.hin.trackrepository.entity.Track;
import com.hin.trackrepository.repository.TrackRepository;
import com.hin.trackrepository.transform.TrackTransformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author NourreddineHouari
 *
 */
@Component
public class TrackService {

	@Autowired
	TrackRepository trackRepository;
	
	@Autowired
	TrackTransformer trackTransformer;
	
	/**
	 * 
	 * @return all events
	 */
	public ResultPage<TrackDTO> findAll(int page, int size){
		final Page<Track> tracks = trackRepository.findAll(new PageRequest(page, size));
		
		ResultPage<TrackDTO> tracksDTOPage = new ResultPage<TrackDTO>();
		tracksDTOPage.setTotalElements(tracks.getTotalElements());
		tracksDTOPage.setNumber(tracks.getNumber());
		tracksDTOPage.setNumberOfElements(tracks.getNumberOfElements());
		tracksDTOPage.setSize(tracks.getSize());
		tracksDTOPage.setTotalElements(tracks.getTotalElements());
		tracksDTOPage.setTotalPages(tracks.getTotalPages());
		tracksDTOPage.setList(convert(tracks.getContent()));
		
		return tracksDTOPage;
	}

	/**
	 * Convert list of entity to list of dto.
	 * @param content
	 * @return
	 */
	private List<TrackDTO> convert(List<Track> content) {
		List<TrackDTO> result = new ArrayList<TrackDTO>();
		content.forEach(e -> {
		  TrackDTO dto = new TrackDTO();
			trackTransformer.convertEntityToDTO(e, dto);
			result.add(dto);
		});
		return result;
	}

	/**
	 * Save an event.
	 * @param e
	 */
	public void save(Track e) {
		trackRepository.save(e);
	}

	/**
	 * Find by identifier.
	 * @param id
	 * @return
	 */
	public TrackDTO findOne(Long id) {
	  TrackDTO trackDTO = null;
		final Track track = trackRepository.findOne(id);
		if(track != null){
			trackDTO = new TrackDTO();
			trackTransformer.convertEntityToDTO(track, trackDTO);
		}
		return trackDTO;
	}

	/**
	 * Delete an event by using the id
	 * @param id
	 * @return
	 */
	public void delete(Long id) {
		trackRepository.delete(id);
	}

	/**
	 * 
	 * @param eventDTO
	 */
	public TrackDTO update(TrackDTO dto) {
	  Track entity = new Track();
		trackTransformer.convertDTOToEntity(dto, entity);
		trackRepository.save(entity);
		trackTransformer.convertEntityToDTO(entity, dto);
		return dto;
	}

	/**
	 * Save event DTO.
	 * @param dto
	 * @return
	 */
	public TrackDTO save(TrackDTO dto) {
	  Track entity = new Track();
		trackTransformer.convertDTOToEntity(dto, entity);
		entity.setId(null);
		save(entity);
		trackTransformer.convertEntityToDTO(entity, dto);
		return dto;
	}
}
