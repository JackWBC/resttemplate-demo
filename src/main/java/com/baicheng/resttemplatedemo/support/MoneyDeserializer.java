package com.baicheng.resttemplatedemo.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @author baicheng
 * @description
 * @create 2019-03-25 11:27
 */
@JsonComponent
public class MoneyDeserializer extends StdDeserializer<Money> {
    private static final long serialVersionUID = -3920842155741328230L;

    protected MoneyDeserializer() {
        super(Money.class);
    }

    @Override
    public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Money.of(CurrencyUnit.of("CNY"), jsonParser.getDecimalValue());
    }
}
