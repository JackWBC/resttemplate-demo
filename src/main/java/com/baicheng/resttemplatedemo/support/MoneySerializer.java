package com.baicheng.resttemplatedemo.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.money.Money;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @author baicheng
 * @description
 * @create 2019-03-25 11:24
 */
@JsonComponent
public class MoneySerializer extends StdSerializer<Money> {
    private static final long serialVersionUID = -8126802486653989597L;

    protected MoneySerializer() {
        super(Money.class);
    }

    @Override
    public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(money.getAmount());
    }
}
