package com.bay1.assessment.domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.bay1.assessment.constant.AuthorizationMappingConstants.AUTHORIZATION_RESPONSE;

public class AuthorizationRecord {
  private String bitmap;
  private String pan;
  private Integer panWidth;
  private LocalDate expirationDate;
  private String transactionAmount;
  private String responseCode;
  private String cardholderName;
  private Integer cardholderNameWidth;
  private Integer zipCode;

  public String getBitmap() {
    return bitmap;
  }

  public void setBitmap(String bitmap) {
    this.bitmap = bitmap;
  }

  public String getPan() {
    return pan;
  }

  public void setPan(String pan) {
    this.pan = pan;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(String transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  public String getCardholderName() {
    return cardholderName;
  }

  public void setCardholderName(String cardholderName) {
    this.cardholderName = cardholderName;
  }

  public Integer getZipCode() {
    return zipCode;
  }

  public void setZipCode(Integer zipCode) {
    this.zipCode = zipCode;
  }

  public Integer getPanWidth() {
    return panWidth;
  }

  public void setPanWidth(Integer panWidth) {
    this.panWidth = panWidth;
  }

  public Integer getCardholderNameWidth() {
    return cardholderNameWidth;
  }

  public void setCardholderNameWidth(Integer cardholderNameWidth) {
    this.cardholderNameWidth = cardholderNameWidth;
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();
    sb.append(AUTHORIZATION_RESPONSE);
    sb.append(this.bitmap);
    sb.append(this.pan != null ? this.panWidth + this.pan : "");
    var dateTimeFormatter = DateTimeFormatter.ofPattern("MMyy").withZone(ZoneId.of("UTC"));
    sb.append(this.expirationDate != null ? this.expirationDate.format(dateTimeFormatter) : "");
    sb.append(this.transactionAmount != null ? this.transactionAmount : "");
    sb.append(this.responseCode != null ? this.responseCode : "");
    sb.append(this.cardholderName != null ? this.cardholderNameWidth + this.cardholderName : "");
    sb.append(this.zipCode != null ? this.zipCode.toString() : "");
    return sb.toString();

  }

}
