package com.api.travel.Service;

import com.api.travel.Entity.Card;
import com.api.travel.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;
    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }
}
