package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.EmailNotification;

public interface EmailService {

  void send(EmailNotification emailNotification);

}
