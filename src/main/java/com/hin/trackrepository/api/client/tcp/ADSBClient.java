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
package com.hin.trackrepository.api.client.tcp;

import com.hin.asdb.AircraftListener;
import com.hin.asdb.DecodeFromAdsbExchange;
import com.hin.asdb.model.Aircraft;
import com.hin.trackrepository.message.TrackMessage;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ADSBClient implements AircraftListener {

  @Value("${adsb.server}")
  private String adsbServer;

  @Value("${adsb.port}")
  private int adsbPort;

  @Value("${kafka.topic.track}")
  private String topic;

  @Autowired
  private KafkaTemplate<String, TrackMessage> kafkaTemplate;

  private DecodeFromAdsbExchange decoder;

  @PostConstruct
  private void init() {
    // Create the reader
    decoder = new DecodeFromAdsbExchange(adsbServer, adsbPort);

    // Attach the consumer as listener
    decoder.setListener(this);

    // Start reading TCP ADSB stream
    decoder.start();
  }

  @PreDestroy
  private void stop() {
    decoder.stop();
  }

  @Override
  public void onAircraftReceived(Aircraft aircraft) {
    // Convert Aircraft to Kafka message
    TrackMessage message = convert(aircraft);
    // Send message to topic
    kafkaTemplate.send(topic, Long.toString(System.currentTimeMillis()), message);
    // System.out.println("Published to kafka : " + aircraft.getIcao() + ", topic:" + topic + ", message:" + message);
  }

  /**
   * Convert an Aircraft to a Protobuf TrackMessage
   * 
   * @param aircraft
   *          Aircraft to convert.
   * @return Protobuf message
   */
  private TrackMessage convert(Aircraft aircraft) {
    return TrackMessage.newBuilder().setId(aircraft.getIcao()).setUpdateDate(System.currentTimeMillis()).setLatitude(aircraft.getLat())
        .setLongitude(aircraft.getLong()).setAltitude(aircraft.getAlt()).setSpeed(aircraft.getSpd()).setHeading(aircraft.getTrak()).build();
  }

}
