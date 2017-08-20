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

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible of storing the pagination result.
 *
 * @author Nourreddine Houari <houarinourredine@gmail.com>
 *
 */
public class ResultPage<T> {
  /**
   * Total amount of elements.
   */
  private long totalElements;
  /**
   * Number of current page.
   */
  private int number;
  /**
   * Number of element in the current page.
   */
  private long numberOfElements;
  /**
   * Page size.
   */
  private long size;
  /**
   * Total number of pages.
   */
  private long totalPages;
  /**
   * List of items in the current page.
   */
  private List<T> list = new ArrayList<>();
  /**
   * @return the totalElements
   */
  public long getTotalElements() {
    return totalElements;
  }
  /**
   * @param totalElements the totalElements to set
   */
  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }
  /**
   * @return the number
   */
  public int getNumber() {
    return number;
  }
  /**
   * @param number the number to set
   */
  public void setNumber(int number) {
    this.number = number;
  }
  /**
   * @return the numberOfElements
   */
  public long getNumberOfElements() {
    return numberOfElements;
  }
  /**
   * @param numberOfElements the numberOfElements to set
   */
  public void setNumberOfElements(long numberOfElements) {
    this.numberOfElements = numberOfElements;
  }
  /**
   * @return the size
   */
  public long getSize() {
    return size;
  }
  /**
   * @param size the size to set
   */
  public void setSize(long size) {
    this.size = size;
  }
  /**
   * @return the totalPages
   */
  public long getTotalPages() {
    return totalPages;
  }
  /**
   * @param totalPages the totalPages to set
   */
  public void setTotalPages(long totalPages) {
    this.totalPages = totalPages;
  }
  /**
   * @return the list
   */
  public List<T> getList() {
    return list;
  }
  /**
   * @param list the list to set
   */
  public void setList(List<T> list) {
    this.list = list;
  }
 
}
