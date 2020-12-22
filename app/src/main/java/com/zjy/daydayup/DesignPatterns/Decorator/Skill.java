package com.zjy.daydayup.DesignPatterns.Decorator;

/**
 * Created by Hugh on 2018/6/22.
 */

public class Skill implements Hero {

    private final Hero hero;
    private final String skillName;

    public Skill(Hero hero, String skillName) {
        this.hero = hero;
        this.skillName = skillName;
    }

    @Override
    public void learnSkill() {
        if (hero != null){
            hero.learnSkill();
        }
    }
}
