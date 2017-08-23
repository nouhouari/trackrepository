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
package com.hin.trackrepository.api.client.kafka;

import com.hin.trackrepository.dto.TrackDTO;
import com.hin.trackrepository.entity.Track;
import com.hin.trackrepository.message.TrackMessage;
import com.hin.trackrepository.repository.TrackRepository;
import com.hin.trackrepository.transform.TrackTransformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class TrackConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(TrackConsumer.class);
  
  @Autowired
  private TrackRepository repository;
  
  @Autowired
  private TrackTransformer transformer;
  
  @Autowired
  private SimpMessagingTemplate broker;
  
  @KafkaListener(topics = "${kafka.topic.track}"  )
  private void consume(TrackMessage track){
    if(LOGGER.isDebugEnabled()){
      LOGGER.debug("received track payload='{}'", track);      
    }
    // Check if track exists
    Track entity = repository.findByexternalId(track.getId());
    if(entity == null){
      // Track doesn'exist. Create a new
      entity = new Track();
    }
    // Filter lat/lon =0
    if(!(track.getLatitude() == 0 && track.getLongitude() == 0)){
      // Convert Proto to entity
      transformer.convertProtoToEntity(track, entity);
      // Save entity
      Track savedEntity = repository.save(entity);
      // Convert to DTO
      TrackDTO dto = new TrackDTO();
      transformer.convertEntityToDTO(savedEntity, dto);
      // Broadcast DTO
      send(dto);
    }
  }
  
  /**
   * Send the DTO message to /topic/track Topic.
   * @param t Track to broadcast
   */
  public void send(TrackDTO dto){
    broker.convertAndSend("/topic/track", dto);
  }
}
