package com.game.cardgame.gameplay;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.flextrade.jfixture.FixtureCollections;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.MultipleCount;
import com.game.cardgame.card.dto.CardDto;
import com.game.cardgame.card.enums.Status;
import com.game.cardgame.card.interfaces.CardService;
import com.game.cardgame.deck.dto.DeckDto;
import com.game.cardgame.deck.interfaces.DeckService;
import com.game.cardgame.gameplay.bll.GamePlayServiceImpl;
import com.game.cardgame.player.interfaces.PlayerService;
import com.game.cardgame.tools.interfaces.RandomGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GamePlayServiceTest {

	@Mock
    DeckService deckService;

    @Mock
    CardService cardService;

    @Mock
    PlayerService playerService;

    @Mock
    RandomGenerator randomGenerator;
	
	@InjectMocks
	GamePlayServiceImpl sut;

	protected JFixture fixture = new JFixture();
    protected FixtureCollections fixtureCollections;
	
    public GamePlayServiceTest() {
        fixture.customise().circularDependencyBehaviour().omitSpecimen();
        MultipleCount multipleCount = new MultipleCount(3);
        this.fixtureCollections = new FixtureCollections(fixture, multipleCount);
    }

	@Test
	public void dealCard_WhenCardsAreLeft_Deal() {
		Long gameId = fixture.create(Long.class);
		Long playerId = fixture.create(Long.class);
		DeckDto deck = fixture.create(DeckDto.class);
        CardDto card = fixture.create(CardDto.class);
		doReturn(deck).when(deckService).getDeckInPlay(gameId);
		doReturn(card).when(cardService).getNextCard(deck.getId());
		ArgumentCaptor<CardDto> captor = ArgumentCaptor.forClass(CardDto.class);
					
		sut.dealCard(gameId, playerId);

		verify(cardService).update(captor.capture());
		assertThat(playerId).isEqualTo(captor.getValue().getOwner());
		assertThat(Status.Dealt).isEqualTo(captor.getValue().getStatus());
		assertThat(0).isEqualTo(captor.getValue().getDeckOrder());
	}

	@Test
	public void dealCard_WhenDeckIsEmpty_DontUpdate() {
		Long gameId = fixture.create(Long.class);
		Long playerId = fixture.create(Long.class);
		DeckDto deck = fixture.create(DeckDto.class);
        CardDto card = null;
		doReturn(deck).when(deckService).getDeckInPlay(gameId);
		doReturn(card).when(cardService).getNextCard(deck.getId());
					
		sut.dealCard(gameId, playerId);
		verify(cardService, never()).update(Mockito.any(CardDto.class));
	}

	@Test
	public void shuffleDeck_always_shuffle() {
		Long deckId = fixture.create(Long.class);
		Collection<CardDto> cardCollection = fixtureCollections.createCollection(CardDto.class);
		List<CardDto> cards = new ArrayList<CardDto>(cardCollection);
		doReturn(cards).when(cardService).getDecksCards(deckId);
		Collection<Integer> randomCollection = fixtureCollections.createCollection(Integer.class);
		Integer[] random = randomCollection.toArray(new Integer[randomCollection.size()]);
		doReturn(random).when(randomGenerator).getRandomNumbers(cards.size());
		ArgumentCaptor<CardDto> captor = ArgumentCaptor.forClass(CardDto.class);
					
		sut.shuffleDeck(deckId);

		verify(cardService, times(cards.size())).update(captor.capture());
		List<CardDto> values = captor.getAllValues();
		for (Integer i = 0; i < values.size(); i++) {
			assertThat(random[i]).isEqualTo(values.get(i).getDeckOrder());
        }
	}
}

