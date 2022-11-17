package com.pje.sansomatchingwalkingmateapi.model.keyword;

import com.pje.sansomatchingwalkingmateapi.entity.Keyword;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordFriendYouWantListItem {

    private String valuesTypeFriendYouWant1;
    private String valuesTypeFriendYouWant2;
    private String valuesTypeFriendYouWant3;
    private String valuesTypeFriendYouWant4;
    private String valuesTypeFriendYouWant5;
    private String valuesTypeFriendYouWant6;

    private KeywordFriendYouWantListItem(KeywordFriendYouWantListItemBuilder builder) {
        this.valuesTypeFriendYouWant1 = builder.valuesTypeFriendYouWant1;
        this.valuesTypeFriendYouWant2 = builder.valuesTypeFriendYouWant2;
        this.valuesTypeFriendYouWant3 = builder.valuesTypeFriendYouWant3;
        this.valuesTypeFriendYouWant4 = builder.valuesTypeFriendYouWant4;
        this.valuesTypeFriendYouWant5 = builder.valuesTypeFriendYouWant5;
        this.valuesTypeFriendYouWant6 = builder.valuesTypeFriendYouWant6;
    }

    public static class KeywordFriendYouWantListItemBuilder implements CommonModelBuilder<KeywordFriendYouWantListItem> {
        private final String valuesTypeFriendYouWant1;
        private final String valuesTypeFriendYouWant2;
        private final String valuesTypeFriendYouWant3;
        private final String valuesTypeFriendYouWant4;
        private final String valuesTypeFriendYouWant5;
        private final String valuesTypeFriendYouWant6;

        public KeywordFriendYouWantListItemBuilder(Keyword keyword) {
            this.valuesTypeFriendYouWant1 = keyword.getValuesTypeFriendYouWant1().getName();
            this.valuesTypeFriendYouWant2 = keyword.getValuesTypeFriendYouWant2().getName();
            this.valuesTypeFriendYouWant3 = keyword.getValuesTypeFriendYouWant3().getName();
            this.valuesTypeFriendYouWant4 = keyword.getValuesTypeFriendYouWant4().getName();
            this.valuesTypeFriendYouWant5 = keyword.getValuesTypeFriendYouWant5().getName();
            this.valuesTypeFriendYouWant6 = keyword.getValuesTypeFriendYouWant6().getName();
        }

        @Override
        public KeywordFriendYouWantListItem build() {
            return new KeywordFriendYouWantListItem(this);
        }
    }
}
