package com.helpers.string;

import com.enumerators.CurrencyEnum;
import com.enumerators.ProviderEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class StringHelper {

    public @NotNull String getCurrencyRate(@NotNull ProviderEnum provider, @NotNull CurrencyEnum currency,
                                         @NotNull Float buy, @NotNull Float sale) {
        String PATTERN = "{\"providerType\":\"%s\",\"currencyType\":\"%s\",\"buy\":\"%s\",\"sale\":\"%s\"},";
        return  String.format(PATTERN, provider, currency, buy, sale);
    }

}