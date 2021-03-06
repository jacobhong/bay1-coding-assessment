package com.bay1.assessment;

import com.bay1.assessment.domain.AuthorizationRecord;
import com.bay1.assessment.domain.Balances;
import com.bay1.assessment.util.BitmapUtil;
import com.bay1.assessment.util.DateUtil;
import com.bay1.assessment.validation.RecordValidator;


import java.math.BigDecimal;

import static com.bay1.assessment.constant.AuthorizationMappingConstants.*;
import static com.bay1.assessment.constant.AuthorizationMappingConstants.AUTHORIZE_MISSING_FIELDS;
import static com.bay1.assessment.util.BitmapUtil.decodeBitmap;

public class AuthorizationParser {
//  private HashMap<String, Balance> balances;
  private int distance = 6;

  public String parseRecord(String input) {
//    balances = new HashMap<>();
    var authorizationRecord = createAuthorizationRecord(input);
//    System.out.println(authorizationRecord.toString());
    return authorizationRecord.toString();
  }

  protected AuthorizationRecord createAuthorizationRecord(String record) {
    var authorizationRecord = new AuthorizationRecord();
    var bitmap = decodeBitmap(record.substring(BITMAP_START_INDEX, BITMAP_END_INDEX));

    setPan(record, authorizationRecord, bitmap);
    setExpirationDate(record, authorizationRecord, bitmap);
    setTransactionAmount(record, authorizationRecord, bitmap);
    setCardHolderName(record, authorizationRecord, bitmap);
    setZipCode(record, authorizationRecord, bitmap);
    setResponseValues(authorizationRecord, bitmap);
    distance = 6;
    return authorizationRecord;
  }

  protected void setPan(String record, AuthorizationRecord authorizationRecord, String bitmap) {
    if (Character.getNumericValue(bitmap.charAt(0)) == 1) {
      var width = Integer.valueOf(record.substring(distance, distance + WIDTH_INDICATOR));
      distance += WIDTH_INDICATOR;
      var pan = record.substring(distance, distance + width);
      authorizationRecord.setPan(pan);
      authorizationRecord.setPanWidth(width);
      if (!Balances.getBalances().containsKey(pan)) {
        BigDecimal initialBalance = new BigDecimal("100000");
        Balances.getBalances().put(pan, initialBalance);
        authorizationRecord.setBalance(initialBalance);
      }
      distance += width;
    }
  }

  protected void setExpirationDate(String record, AuthorizationRecord authorizationRecord, String bitmap) {
    if (Character.getNumericValue(bitmap.charAt(1)) == 1) {
      authorizationRecord.setExpirationDate(DateUtil.dateFromString(record.substring(distance, distance + EXPIRATION_DATE_WIDTH)));
      distance += EXPIRATION_DATE_WIDTH;
    }
  }

  protected void setTransactionAmount(String record, AuthorizationRecord authorizationRecord, String bitmap) {
    if (Character.getNumericValue(bitmap.charAt(2)) == 1) {
      authorizationRecord.setTransactionAmount(record.substring(distance, distance + TRANSACTION_AMOUNT_WIDTH));
      distance += TRANSACTION_AMOUNT_WIDTH;
    }
  }

  protected void setCardHolderName(String record, AuthorizationRecord authorizationRecord, String bitmap) {
    if (Character.getNumericValue(bitmap.charAt(4)) == 1) {
      var width = Integer.valueOf(record.substring(distance, distance + WIDTH_INDICATOR));
      distance += WIDTH_INDICATOR;
      authorizationRecord.setCardholderName(record.substring(distance, distance + width));
      authorizationRecord.setCardholderNameWidth(width);
      distance += width;
    }
  }

  protected void setZipCode(String record, AuthorizationRecord authorizationRecord, String bitmap) {
    if (Character.getNumericValue(bitmap.charAt(5)) == 1) {
      authorizationRecord.setZipCode(Integer.valueOf(record.substring(distance, distance + ZIP_ZODE_WIDTH)));
    }
  }

  protected AuthorizationRecord setResponseValues(AuthorizationRecord authorizationRecord, String bitmap) {
    var bitmapWithResponse =
      BitmapUtil.encodeBitmap(bitmap
                                .substring(RESPONSE_BITMAP_START_INDEX, RESPONSE_BITMAP_END_INDEX)
                                + "1"
                                + bitmap.substring(4));
    authorizationRecord.setBitmap(bitmapWithResponse);
    setResponseCode(authorizationRecord);
    return authorizationRecord;
  }

  protected static void setResponseCode(AuthorizationRecord authorizationRecord) {
    var requiredFieldsArePresent = RecordValidator.requiredFieldsPresent(authorizationRecord);
    var authIsValid = requiredFieldsArePresent && RecordValidator.validAuthorization(authorizationRecord);
    var expirationDateIsValid = requiredFieldsArePresent && RecordValidator.isValidDate(authorizationRecord.getExpirationDate());
    var isBalanceValid = RecordValidator.setBalance(authorizationRecord);
    if (!requiredFieldsArePresent) {
      authorizationRecord.setResponseCode(AUTHORIZE_MISSING_FIELDS);
    } else if (requiredFieldsArePresent && !authIsValid || !isBalanceValid) {
      authorizationRecord.setResponseCode(AUTHORIZE_DECLINE);
    } else if (requiredFieldsArePresent && authIsValid && expirationDateIsValid && isBalanceValid) {
      authorizationRecord.setResponseCode(AUTHORIZE_RESPONSE);
//      setBalance(authorizationRecord);
    }
  }
//
//  protected static void setBalance(AuthorizationRecord authorizationRecord) {
//    if (Balances.getBalances().containsKey(authorizationRecord.getPan())) {
//      BigDecimal balance = Balances.getBalances().get(authorizationRecord.getPan());
//      BigDecimal transAmount = new BigDecimal(authorizationRecord.getTransactionAmount());
//      BigDecimal updatedBalance = balance.subtract(transAmount);
//      if (updatedBalance.intValue() < 0) {
//
//      }
//      Balances.getBalances().put(authorizationRecord.getPan(), updatedBalance);
//    }
//  }

}
