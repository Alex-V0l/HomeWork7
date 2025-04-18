package patterns.builder;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Hero {
    private String name;
    private String className;
    private String race;
    private int strength;
    private int dexterity;
    private int constitution;
    private int wisdom;
    private int intelligence;
    private int charisma;
    private boolean isEvil;
    private double expModifier;
    private List<String> startEquip;
    private char sex;
}

