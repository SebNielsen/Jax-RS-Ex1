/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Quote;
import exception.QuoteNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author sebastiannielsen
 */
public class Facade {

    static Random random = new Random();
    private static int id = 0;
    private static Quote q1 = new Quote(++id, "Friends are kisses blown to us by angels");
    private static Quote q2 = new Quote(++id, "Do not take life too seriously. You will never get out of it alive");
    private static Quote q3 = new Quote(++id, "Behind every great man, is a woman rolling her eyes");

    static Map<Integer, Quote> quotes = new HashMap() {
        {
            put(q1.getId(), q1);
            put(q2.getId(), q2);
            put(q3.getId(), q3);
        }
    };

    public static Quote createQuote(Quote quote) {
        for (int i = 1; i < quotes.size() + 2; i++) {

            if (!quotes.containsKey(i)) {
                quote.setId(i);
                quotes.put(quote.getId(), quote);
                break;
            }
        }
        return quotes.get(quote.getId());
    }

    public static Quote getQuote(Integer id) throws QuoteNotFoundException {
        if (quotes.containsKey(id)) {
            return quotes.get(id);
        } else {
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
    }

    public static Collection<Quote> getQuotes() throws QuoteNotFoundException {
        if (quotes.size() > 0) {
            return quotes.values();
        } else {
            throw new QuoteNotFoundException("No Quotes Created yet");
        }
    }

    public static Quote getRandomQuote() throws QuoteNotFoundException  {
        if (1 == 1) {
            return quotes.get(1);
        } else {
            //throw new QuoteNotFoundException("No Quotes Created yet");
        }
        return quotes.get(1);
    }

    public static Quote updateQuote(Integer id, Quote quote) throws QuoteNotFoundException {
        if (quotes.containsKey(id)) {
            quote.setId(id);
            quotes.replace(id, quote);
            return quotes.get(id);
        } else {
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
    }

    public static void deleteQuote(int id) {
        quotes.remove(id);
    }

}
