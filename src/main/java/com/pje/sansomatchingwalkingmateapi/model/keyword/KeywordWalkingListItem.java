package com.pje.sansomatchingwalkingmateapi.model.keyword;

import com.pje.sansomatchingwalkingmateapi.entity.Keyword;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordWalkingListItem {
    private String valuesTypeWalking1;
    private String valuesTypeWalking2;
    private String valuesTypeWalking3;
    private String valuesTypeWalking4;
    private String valuesTypeWalking5;
    private String valuesTypeWalking6;

    private KeywordWalkingListItem(KeywordWalkingListItemBuilder builder) {
        this.valuesTypeWalking1 = builder.valuesTypeWalking1;
        this.valuesTypeWalking2 = builder.valuesTypeWalking2;
        this.valuesTypeWalking3 = builder.valuesTypeWalking3;
        this.valuesTypeWalking4 = builder.valuesTypeWalking4;
        this.valuesTypeWalking5 = builder.valuesTypeWalking5;
        this.valuesTypeWalking6 = builder.valuesTypeWalking6;

    }

    public static class KeywordWalkingListItemBuilder implements CommonModelBuilder<KeywordWalkingListItem> {
        private final String valuesTypeWalking1;
        private final String valuesTypeWalking2;
        private final String valuesTypeWalking3;
        private final String valuesTypeWalking4;
        private final String valuesTypeWalking5;
        private final String valuesTypeWalking6;

        public KeywordWalkingListItemBuilder(Keyword keyword) {
            this.valuesTypeWalking1 = keyword.getValuesTypeWalking1().getName();
            this.valuesTypeWalking2 = keyword.getValuesTypeWalking2().getName();
            this.valuesTypeWalking3 = keyword.getValuesTypeWalking3().getName();
            this.valuesTypeWalking4 = keyword.getValuesTypeWalking4().getName();
            this.valuesTypeWalking5 = keyword.getValuesTypeWalking5().getName();
            this.valuesTypeWalking6 = keyword.getValuesTypeWalking6().getName();
        }

        @Override
        public KeywordWalkingListItem build() {
            return new KeywordWalkingListItem(this);
        }
    }
}
