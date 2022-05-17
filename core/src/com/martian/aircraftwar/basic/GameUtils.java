package com.martian.aircraftwar.basic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GameUtils {


    public static final float BG_WIDTH;
    public static final float BG_HEIGHT;
    public static final int ENEMY_BULLET;
    public static final int HERO_BULLET;


    //图片
    public static Texture BACKGROUND_IMAGE_1;
    public static Texture BACKGROUND_IMAGE_2;
    public static Texture BACKGROUND_IMAGE_3;
    public static Texture HERO_IMAGE;
    public static Texture MOB_IMAGE;
    public static Texture ELITE_IMAGE;
    public static Texture BOSS_IMAGE;
    public static Texture ENEMY_BULLET_IMAGE;
    public static Texture HERO_BULLET_IMAGE;
    public static Texture HEAL_PROP_IMAGE;
    public static Texture FIRE_PROP_IMAGE;
    public static Texture BOMB_PROP_IMAGE;

    static {
        BACKGROUND_IMAGE_1 = new Texture(Gdx.files.internal("images/bg1.jpg"));
        BACKGROUND_IMAGE_2 = new Texture(Gdx.files.internal("images/bg2.jpg"));
        BACKGROUND_IMAGE_3 = new Texture(Gdx.files.internal("images/bg3.jpg"));
        HERO_IMAGE = new Texture(Gdx.files.internal("images/hero.png"));
        MOB_IMAGE = new Texture(Gdx.files.internal("images/mob.png"));
        ELITE_IMAGE = new Texture(Gdx.files.internal("images/elite.png"));
        BOSS_IMAGE = new Texture(Gdx.files.internal("images/boss.png"));
        ENEMY_BULLET_IMAGE = new Texture(Gdx.files.internal("images/bullet_enemy.png"));
        HERO_BULLET_IMAGE = new Texture(Gdx.files.internal("images/bullet_hero.png"));
        HEAL_PROP_IMAGE = new Texture(Gdx.files.internal("images/prop_blood.png"));
        FIRE_PROP_IMAGE = new Texture(Gdx.files.internal("images/prop_bullet.png"));
        BOMB_PROP_IMAGE = new Texture(Gdx.files.internal("images/prop_bomb.png"));

        BG_WIDTH = BACKGROUND_IMAGE_1.getWidth();
        BG_HEIGHT = BACKGROUND_IMAGE_1.getHeight();
        ENEMY_BULLET = 1;
        HERO_BULLET = 0;
    }
}
