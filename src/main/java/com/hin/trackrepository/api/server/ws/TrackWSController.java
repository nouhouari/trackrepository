
package com.hin.trackrepository.api.server.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.hin.trackrepository.dto.TrackDTO;

@Controller
public class TrackWSController {

  @MessageMapping("/track")
  @SendTo("/topic/track")
  public TrackDTO greeting(TrackDTO t) throws Exception {
     return t;
  }
  
}
