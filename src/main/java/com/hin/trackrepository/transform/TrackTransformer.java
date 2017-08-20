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
package com.hin.trackrepository.transform;

import com.hin.trackrepository.dto.TrackDTO;
import com.hin.trackrepository.entity.Track;
import com.hin.trackrepository.message.TrackMessage;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import org.springframework.stereotype.Component;

/**
 * @author NourreddineHouari
 *
 */
@Component
public class TrackTransformer {

  private WKTReader wkt = new WKTReader();

  private GeometryFactory gf = new GeometryFactory();

  /**
   * Convert from entity to DTO
   * 
   * @param entity
   * @param dto
   */
  public void convertEntityToDTO(Track entity, TrackDTO dto) {
    dto.setAltitude(entity.getAltitude());
    dto.setCreatedDate(entity.getCreatedDate());
    dto.setExtensions(entity.getExtensions());
    if (entity.getGeom() != null) {
      dto.setGeom(entity.getGeom().toText());
    }
    dto.setHeading(entity.getHeading());
    dto.setId(entity.getId());
    dto.setSpeed(entity.getSpeed());
    dto.setUpdateDate(entity.getUpdateDate());
  }

  /**
   * Convert from entity to DTO
   * 
   * @param entity
   * @param dto
   */
  public void convertDTOToEntity(TrackDTO dto, Track entity) {
    if (dto.getGeom() != null) {
      try {
        entity.setGeom(wkt.read(dto.getGeom()));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    entity.setAltitude(dto.getAltitude());
    entity.setCreatedDate(dto.getCreatedDate());
    entity.setExtensions(dto.getExtensions());
    entity.setHeading(dto.getHeading());
    entity.setId(dto.getId());
    entity.setSpeed(dto.getSpeed());
    entity.setUpdateDate(dto.getUpdateDate());
  }

  public void convertProtoToEntity(TrackMessage message, Track entity) {
    
    Coordinate coordinate = new Coordinate(message.getLongitude(), message.getLatitude());
    Point p = gf.createPoint(coordinate);
    entity.setGeom(p);
    
    entity.setAltitude(message.getAltitude());
    // entity.setCreatedDate(message.getCreatedDate());
    // entity.setExtensions(message.getExtensions());
    entity.setHeading(message.getHeading());
    entity.setExternalId(message.getId());
    entity.setSpeed(message.getSpeed());
    // entity.setUpdateDate(message.getUpdateDate());
  }

}
