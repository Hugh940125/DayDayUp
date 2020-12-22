package com.zjy.daydayup.DesignPatterns.Decorator;

import android.util.Log;

/**
 * Created by Hugh on 2018/6/22.
 *
 */

public class Skill_E extends Skill {

    private final String skillName;

    public Skill_E(Hero hero, String skillName) {
        super(hero, skillName);
        this.skillName = skillName;
    }

    @Override
    public void learnSkill() {
        super.learnSkill();
        Log.e("学会了",skillName+"技能");
    }
}
