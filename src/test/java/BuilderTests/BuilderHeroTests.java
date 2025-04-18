package BuilderTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import patterns.builder.Hero;

import java.util.Arrays;


public class BuilderHeroTests {
    @Test
    void builderHeroChangeTest(){
        Hero astarion = Hero.builder()
                .name("Astarion")
                .className("rogue")
                .race("highElf")
                .strength(8)
                .dexterity(17)
                .constitution(14)
                .wisdom(13)
                .intelligence(13)
                .charisma(10)
                .isEvil(true)
                .expModifier(1.5)
                .startEquip(Arrays.asList("dagger", "light armor", "healing potion"))
                .build();

        Assertions.assertNotNull(astarion);
        Assertions.assertEquals("rogue", astarion.getClassName());
    }

    @Test
    void builderHeroAddAnotherHeroTest (){
        Hero shadowHeart = Hero.builder()
                .name("ShadowHeart")
                .className("cleric")
                .race("halfElf")
                .strength(13)
                .dexterity(13)
                .constitution(14)
                .intelligence(10)
                .wisdom(17)
                .charisma(8)
                .expModifier(1.2)
                .isEvil(false)
                .startEquip(Arrays.asList("mace", "medium armor", "shield"))
                .build();
        Hero karlach = Hero.builder()
                .className("Barbarian")
                .race("Tiefling")
                .sex('f')
                .name("Karlach")
                .build();

        Assertions.assertNotNull(karlach);
        Assertions.assertEquals("Tiefling", karlach.getRace());
        Assertions.assertEquals("mace", shadowHeart.getStartEquip().get(0));
    }
}
