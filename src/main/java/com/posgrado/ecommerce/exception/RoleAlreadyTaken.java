package com.posgrado.ecommerce.exception;

public class RoleAlreadyTaken extends RuntimeException{

  public  final static String MESSAGE_ERROR = "Role with name: %s already exists";
  public RoleAlreadyTaken(String role) {

    super(String.format(MESSAGE_ERROR,role));
  }

}
