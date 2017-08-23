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
package com.hin.trackrepository.controller;

import com.hin.trackrepository.dto.ResultPage;
import com.hin.trackrepository.dto.TrackDTO;
import com.hin.trackrepository.service.TrackService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NourreddineHouari
 *
 */
@RestController
@RequestMapping("track")
public class TrackController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrackController.class);
	
	@Autowired
	TrackService trackService ;
	
	@RequestMapping(path="", 
			consumes=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.POST)
	@ResponseBody
	public TrackDTO createTrack(@RequestBody TrackDTO track){
		return trackService.save(track);
	}
	
	@RequestMapping(path="", 
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.PUT)
	@ResponseBody
	public TrackDTO updateTrack(@RequestBody TrackDTO track){
	  TrackDTO dto = trackService.save(track);
		return dto;
	}

	@RequestMapping(path="", 
			method=RequestMethod.GET)
	@ResponseBody
	public ResultPage<TrackDTO> getTracksPage(
			@RequestParam(name="page", defaultValue="0") Integer page, 
			@RequestParam(name="size", defaultValue="5") Integer size){
		return trackService.findAll(page, size);
	}
	
	@RequestMapping(path="/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.GET)
	@ResponseBody
	public TrackDTO findTrackById(@PathVariable("id") Long id){
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("Find track by id : " + id);
		}
		return trackService.findOne(id);
	}
	
	@RequestMapping(path="/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.DELETE)
	@ResponseBody
	public void deleteTrack(@PathVariable("id") Long id){
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("Delete track by id : " + id);
		}
		trackService.delete(id);
	}
}
