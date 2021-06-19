package com.bay1.assessment.validation;

import com.bay1.assessment.domain.AuthorizationRecord;

import java.math.BigInteger;
import java.time.LocalDate;

public class RecordValidator {


  public static boolean requiredFieldsPresent(AuthorizationRecord authorizationRecord) {
    return authorizationRecord.getPan() != null
             && authorizationRecord.getExpirationDate() != null
             && authorizationRecord.getTransactionAmount() != null;
  }

  public static boolean validAuthorization(AuthorizationRecord authorizationRecord) {
    var valid = false;
    if (authorizationRecord.getZipCode() != null
          && new BigInteger(authorizationRecord.getTransactionAmount()).intValue() < 20000
          && isValidDate(authorizationRecord.getExpirationDate())) {
      valid = true;
    } else if (authorizationRecord.getZipCode() == null
                 && new BigInteger(authorizationRecord.getTransactionAmount()).intValue() < 10000
                 && isValidDate(authorizationRecord.getExpirationDate())) {
      valid = true;
    }
    return valid;
  }

  public static boolean isValidDate(LocalDate expirationDate) {
    return expirationDate.isAfter(LocalDate.now());
  }
}
