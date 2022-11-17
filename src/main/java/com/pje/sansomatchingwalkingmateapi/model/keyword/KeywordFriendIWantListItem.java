package com.pje.sansomatchingwalkingmateapi.model.keyword;

import com.pje.sansomatchingwalkingmateapi.entity.Keyword;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordFriendIWantListItem {
    private String valuesTypeFriendIWant1;
    private String valuesTypeFriendIWant2;
    private String valuesTypeFriendIWant3;
    private String valuesTypeFriendIWant4;
    private String valuesTypeFriendIWant5;
    private String valuesTypeFriendIWant6;

    private KeywordFriendIWantListItem(KeywordFriendIWantListItemBuilder builder) {
        this.valuesTypeFriendIWant1 = builder.valuesTypeFriendIWant1;
        this.valuesTypeFriendIWant2 = builder.valuesTypeFriendIWant2;
        this.valuesTypeFriendIWant3 = builder.valuesTypeFriendIWant3;
        this.valuesTypeFriendIWant4 = builder.valuesTypeFriendIWant4;
        this.valuesTypeFriendIWant5 = builder.valuesTypeFriendIWant5;
        this.valuesTypeFriendIWant6 = builder.valuesTypeFriendIWant6;
    }

    public static class KeywordFriendIWantListItemBuilder implements CommonModelBuilder<KeywordFriendIWantListItem> {
        private final String valuesTypeFriendIWant1;
        private final String valuesTypeFriendIWant2;
        private final String valuesTypeFriendIWant3;
        private final String valuesTypeFriendIWant4;
        private final String valuesTypeFriendIWant5;
        private final String valuesTypeFriendIWant6;

        public KeywordFriendIWantListItemBuilder(Keyword keyword) {
            this.valuesTypeFriendIWant1 = keyword.getValuesTypeFriendIWant1().getName();
            this.valuesTypeFriendIWant2 = keyword.getValuesTypeFriendIWant2().getName();
            this.valuesTypeFriendIWant3 = keyword.getValuesTypeFriendIWant3().getName();
            this.valuesTypeFriendIWant4 = keyword.getValuesTypeFriendIWant4().getName();
            this.valuesTypeFriendIWant5 = keyword.getValuesTypeFriendIWant5().getName();
            this.valuesTypeFriendIWant6 = keyword.getValuesTypeFriendIWant6().getName();
        }

        @Override
        public KeywordFriendIWantListItem build() {
            return new KeywordFriendIWantListItem(this);
        }
    }
}
