package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.entity.ConfirmationToken;
import com.posgrado.ecommerce.repository.ConfirmationTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConfirmationTokenServiceImpl implements ConfimationTokenService {

  private ConfirmationTokenRepository confirmationTokenRepository;

  @Override
  public void save(ConfirmationToken confirmationToken) {
    confirmationTokenRepository.save(confirmationToken);
  }

  @Override
  public ConfirmationToken getByToken(String token) {
    return confirmationTokenRepository.findByToken(token)
        .orElseThrow(()-> new EntityNotFoundException("Resource noy found"));
  }

  @Override
  public void setConfirmedAt(ConfirmationToken confirmationToken) {
    confirmationToken.setConfirmedAt(LocalDateTime.now());
    confirmationTokenRepository.save(confirmationToken);
  }


}
