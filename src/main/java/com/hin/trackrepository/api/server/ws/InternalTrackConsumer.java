
package com.hin.trackrepository.api.server.ws;

import com.hin.trackrepository.dto.TrackDTO;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class InternalTrackConsumer {

  @MessageMapping("/track")
  @SendTo("/topic/track")
  public TrackDTO greeting(TrackDTO t) throws Exception {
     return t;
  }
  
}
