package com.martian.aircraftwar.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.martian.aircraftwar.aircraft.AbstractAircraft;
import com.martian.aircraftwar.aircraft.HeroAircraft;
import com.martian.aircraftwar.aircraft.MobEnemyFactory;
import com.martian.aircraftwar.basic.GameUtils;
import com.martian.aircraftwar.bullet.BaseBullet;

import java.util.Iterator;
import java.util.LinkedList;


public class ScreenGame implements Screen {

    private final AircraftWarGame game;
    private final OrthographicCamera camera;
    private final Music bg_music;
    private final Sound hit_sound;
    private final float bg_width;
    private final float bg_height;
    private float bottom;

    private long lastEnemyGen;
    private long lastHeroShoot;
    private long lastEnemyShoot;

    private HeroAircraft hero;
    //三维向量，感知触摸、点击的位置，移动英雄机
    private final Vector3 touchPos;

    private MobEnemyFactory mobEnemyFactory;

    private LinkedList<AbstractAircraft> enemies;
    private LinkedList<BaseBullet> bullets;

    public ScreenGame(AircraftWarGame game) {
        this.game = game;

        //背景图片宽高
        bg_width = GameUtils.BG_WIDTH;
        bg_height = GameUtils.BG_HEIGHT;
        bottom = bg_height;

        // load music
        bg_music = Gdx.audio.newMusic(Gdx.files.internal("videos/bg_music.mp3"));
        hit_sound = Gdx.audio.newSound(Gdx.files.internal("videos/bullet_hit.wav"));

        hero = HeroAircraft.getInstance();
        touchPos = new Vector3();
        enemies = new LinkedList<>();
        bullets = new LinkedList<>();
        lastEnemyGen = 0;
        lastHeroShoot = 0;
        lastEnemyShoot = 0;
        mobEnemyFactory = new MobEnemyFactory();

        // camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, bg_width, bg_height);
    }

    @Override
    public void show() {
        bg_music.play();
        bg_music.setLooping(true);
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 0.5f);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        addEnemyAction();
        shootAction();
        moveAction();
        crashCheckAction();
        clearAction();

        //开始绘图
        game.batch.begin();
        drawAction();
        game.batch.end();
    }



    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        bg_music.dispose();
    }


    private void crashCheckAction() {

    }

    private void moveAction() {
        backgroundMove();
        heroMove();
        enemyMove();
        bulletMove();
    }

    private void backgroundMove() {
        bottom -= Gdx.graphics.getDeltaTime() * 100;
        if (bottom <= 0) {
            bottom = bg_height;
        }
    }

    private void heroMove() {
        //如果屏幕被点击
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            //把变化告知给camera
            camera.unproject(touchPos);
            hero.x = touchPos.x;
            hero.y = touchPos.y;
        }
    }

    private void enemyMove() {
        for (AbstractAircraft enemy : enemies) {
            enemy.forward();

            if (enemy.x + enemy.height <= 0){
                enemy.vanish();
            }
        }
    }

    private void bulletMove() {
        for (BaseBullet bullet : bullets) {
            bullet.forward();
        }
    }

    private void drawAction() {

        //绘制背景及得分
        game.font.draw(game.batch, "Score", 0, bg_height - 20);
        game.batch.draw(GameUtils.BACKGROUND_IMAGE_1, 0, bottom - bg_height);
        game.batch.draw(GameUtils.BACKGROUND_IMAGE_1, 0, bottom);

        //绘制英雄机
        game.batch.draw(hero.getImage(), hero.x, hero.y);

        //绘制敌机
        for (AbstractAircraft enemy : enemies) {
            game.batch.draw(enemy.getImage(), enemy.x, enemy.y);
        }

        //绘制子弹
        for (BaseBullet bullet : bullets) {
            game.batch.draw(bullet.getImage(),bullet.x,bullet.y);
        }
    }

    private void addEnemyAction() {
        if (TimeUtils.nanoTime() - lastEnemyGen > 1000000000){
            enemies.add(mobEnemyFactory.createEnemy());
            lastEnemyGen = TimeUtils.nanoTime();
        }
    }

    private void shootAction(){
        if (TimeUtils.nanoTime() - lastHeroShoot > 1000000000){
            bullets.addAll(hero.shoot());
            lastEnemyShoot = TimeUtils.nanoTime();
        }
    }

    private void clearAction(){

        for (Iterator<AbstractAircraft> iterator = enemies.iterator(); iterator.hasNext(); ) {
            AbstractAircraft aircraft = iterator.next();
            //超出屏幕
            if (aircraft.y + aircraft.height < 0) {
                iterator.remove();
            }
        }

        for (Iterator<BaseBullet> iterator = bullets.iterator(); iterator.hasNext(); ) {
            BaseBullet bullet = iterator.next();
            //超出屏幕
            if (bullet.y + bullet.height < 0) {
                iterator.remove();
            }
        }

    }

}
