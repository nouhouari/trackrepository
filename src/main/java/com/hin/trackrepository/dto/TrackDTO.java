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
package com.hin.trackrepository.dto;

import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author NourreddineHouari
 *
 */
public class TrackDTO {
  
	@JsonProperty("id")
	private Long id;
	@JsonProperty("ei")
	private String externalId;
	@JsonProperty("geo")
	private String geom;
	@JsonProperty("ct")
	private Date createdDate;
	@JsonProperty("ut")
	private Date updateDate;
	@JsonProperty("sp")
	private double speed;
	@JsonProperty("he")
	private double heading;
	@JsonProperty("al")
	private double altitude;
	@JsonProperty("ex")
	private HashMap<String, String> extensions;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
 * @return the externalId
 */
public String getExternalId() {
	return externalId;
}

/**
 * @param externalId the externalId to set
 */
public void setExternalId(String externalId) {
	this.externalId = externalId;
}

/**
   * @return the geom
   */
  public String getGeom() {
    return geom;
  }

  /**
   * @param geom the geom to set
   */
  public void setGeom(String geom) {
    this.geom = geom;
  }

  /**
   * @return the createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * @return the updateDate
   */
  public Date getUpdateDate() {
    return updateDate;
  }

  /**
   * @param updateDate the updateDate to set
   */
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  /**
   * @return the speed
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * @param speed the speed to set
   */
  public void setSpeed(double speed) {
    this.speed = speed;
  }

  /**
   * @return the heading
   */
  public double getHeading() {
    return heading;
  }

  /**
   * @param heading the heading to set
   */
  public void setHeading(double heading) {
    this.heading = heading;
  }

  /**
   * @return the altitude
   */
  public double getAltitude() {
    return altitude;
  }

  /**
   * @param altitude the altitude to set
   */
  public void setAltitude(double altitude) {
    this.altitude = altitude;
  }

  /**
   * @return the extensions
   */
  public HashMap<String, String> getExtensions() {
    return extensions;
  }

  /**
   * @param extensions the extensions to set
   */
  public void setExtensions(HashMap<String, String> extensions) {
    this.extensions = extensions;
  }
}
