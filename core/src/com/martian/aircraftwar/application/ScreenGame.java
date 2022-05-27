package com.martian.aircraftwar.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.martian.aircraftwar.aircraft.AbstractAircraft;
import com.martian.aircraftwar.aircraft.BossEnemy;
import com.martian.aircraftwar.aircraft.BossEnemyFactory;
import com.martian.aircraftwar.aircraft.EliteEnemy;
import com.martian.aircraftwar.aircraft.EliteEnemyFactory;
import com.martian.aircraftwar.aircraft.HeroAircraft;
import com.martian.aircraftwar.aircraft.MobEnemyFactory;
import com.martian.aircraftwar.basic.AbstractFlyingObject;
import com.martian.aircraftwar.basic.GameUtils;
import com.martian.aircraftwar.bullet.BaseBullet;
import com.martian.aircraftwar.bullet.EnemyBullet;
import com.martian.aircraftwar.bullet.HeroBullet;
import com.martian.aircraftwar.prop.AbstractProp;
import com.martian.aircraftwar.prop.BombProp;

import java.util.Iterator;
import java.util.LinkedList;


public abstract class ScreenGame implements Screen {

    private final AircraftWarGame game;
    private final OrthographicCamera camera;
    private final Music bg_music;
    private final Sound hit_sound;
    private final Sound hero_hit_sound;
    private final Sound prop_sound;
    private BitmapFont bitmapFont;
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
    private EliteEnemyFactory eliteEnemyFactory;
    private BossEnemyFactory bossEnemyFactory;

    private LinkedList<AbstractAircraft> enemies;
    private LinkedList<BaseBullet> bullets;
    private LinkedList<AbstractProp> props;
    private int score = 0;
    private int lasBossScore = 0;
    private boolean havBoss = false;
    protected int enemyMaxNumber;
    protected int BossInterval;
    protected int eliteEnemyRate;

    public ScreenGame(AircraftWarGame game) {
        this.game = game;

        //背景图片宽高
        bg_width = GameUtils.BG_WIDTH;
        bg_height = GameUtils.BG_HEIGHT;
        bottom = bg_height;

        // load music
        bg_music = Gdx.audio.newMusic(Gdx.files.internal("videos/bg_music.mp3"));
        hit_sound = Gdx.audio.newSound(Gdx.files.internal("videos/bullet_hit.wav"));
        hero_hit_sound = Gdx.audio.newSound(Gdx.files.internal("videos/hero_hit.wav"));
        prop_sound = Gdx.audio.newSound(Gdx.files.internal("videos/get_supply.wav"));
        bitmapFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));

        hero = HeroAircraft.getInstance();
        touchPos = new Vector3();
        enemies = new LinkedList<>();
        bullets = new LinkedList<>();
        props = new LinkedList<>();
        lastEnemyGen = 0;
        lastHeroShoot = 0;
        lastEnemyShoot = 0;
        mobEnemyFactory = new MobEnemyFactory();
        eliteEnemyFactory = new EliteEnemyFactory();
        bossEnemyFactory = new BossEnemyFactory();

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
        game.dispose();
    }


    protected void crashCheckAction() {
        // Todo: 我方获得道具，道具生效
        for(AbstractProp prop : props)
        {
            if(!prop.notValid())
            {
                if(hero.notValid())
                {
                    // 英雄机已被其他子弹击毁，不再检测
                    continue;
                }
                if(hero.crash(prop))
                {
                    prop_sound.play();
                    System.out.println("233");
                    if(prop instanceof BombProp)
                    {
                        score += ((BombProp) prop).effect(enemies, bullets);
                    }
                    else prop.effect();
                    prop.vanish();
                }
            }
        }
        for(AbstractAircraft enemyAircraft : enemies)
        {
            // 英雄机 与 敌机 相撞，均损毁
            if(hero.notValid())
            {
                continue;
            }
            if(enemyAircraft.crash(hero))
            {
                enemyAircraft.vanish();
                hero.decreaseHp(Integer.MAX_VALUE);
            }
        }
        // TODO 敌机子弹攻击英雄
        for(BaseBullet bullet : bullets)
        {
            if(bullet.notValid() || !(bullet instanceof EnemyBullet))
            {
                continue;
            }
            if(hero.notValid())
            {
                // 英雄机已被其他子弹击毁，不再检测

                dispose();
            }
            if(hero.crash(bullet))
            {
                // 英雄机撞击到敌机子弹
                // 英雄机损失一定生命值
                hero.decreaseHp(bullet.getPower());
                bullet.vanish();
                hero_hit_sound.play();
            }
        }
        for(BaseBullet bullet : bullets)
        {
            if (bullet.notValid()|| !(bullet instanceof HeroBullet))
            {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemies)
            {
                if (enemyAircraft.notValid())
                {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet))
                {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    hit_sound.play();
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid())
                    {
                        // TODO 获得分数，产生道具补给
                        score += 10;
                        props.addAll(enemyAircraft.dropProp());
                    }
                    break;
                }
            }
        }
    }

    protected void moveAction() {
        backgroundMove();
        heroMove();
        enemyMove();
        bulletMove();
        propMove();
    }

    protected void backgroundMove() {
        bottom -= Gdx.graphics.getDeltaTime() * 100;
        if (bottom <= 0) {
            bottom = bg_height;
        }
    }

    protected void heroMove() {
        //如果屏幕被点击
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            //把变化告知给camera
            camera.unproject(touchPos);
            hero.x = touchPos.x - hero.getWidth() / 2;
            hero.y = touchPos.y - hero.getHeight() / 2;
        }
    }

    protected void enemyMove() {
        for (AbstractAircraft enemy : enemies) {
            enemy.forward();

            if (enemy.x + enemy.height <= 0){
                enemy.vanish();
            }
        }
    }

    protected void bulletMove() {
        for (BaseBullet bullet : bullets) {
            bullet.forward();
        }
    }

    protected void propMove() {
        for (AbstractProp prop : props) {
            prop.forward();
        }
    }

    protected void drawAction() {

        //绘制背景及得分
        game.batch.draw(GameUtils.BACKGROUND_IMAGE_1, 0, bottom - bg_height);
        game.batch.draw(GameUtils.BACKGROUND_IMAGE_1, 0, bottom);
        bitmapFont.draw(game.batch, "分数:"+score, 10, bg_height - 5);
        bitmapFont.draw(game.batch, "血量:"+hero.getHp(), 10, bg_height - bitmapFont.getCapHeight() - 10);
        if(!havBoss && BossInterval <= 300)
            bitmapFont.draw(game.batch, "Boss机将在"+(BossInterval - score + lasBossScore)+"分后来临", 10, bg_height - 2 * bitmapFont.getCapHeight() - 15);
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

        //绘制道具
        for(AbstractProp prop : props)
        {
            game.batch.draw(prop.getImage(), prop.x, prop.y);
        }
    }

    protected void addEnemyAction() {
        if (TimeUtils.nanoTime() - lastEnemyGen > 1000000000 && enemies.size() < enemyMaxNumber){
            if(score - lasBossScore >= BossInterval && !havBoss)
            {
                havBoss = true;
                enemies.add(bossEnemyFactory.createEnemy());
            }
            else if(MathUtils.random(0, 100) <= eliteEnemyRate)
            {
                enemies.add(eliteEnemyFactory.createEnemy());
            }
            else
            {
                enemies.add(mobEnemyFactory.createEnemy());
            }
            lastEnemyGen = TimeUtils.nanoTime();
        }
    }

    protected void shootAction(){
        if (TimeUtils.nanoTime() - lastHeroShoot > 1000000000){
            bullets.addAll(hero.shoot());
            lastHeroShoot = TimeUtils.nanoTime();
        }
        if (TimeUtils.nanoTime() - lastEnemyShoot > 1000000000){
            for(AbstractAircraft enemy : enemies)
            {
                bullets.addAll(enemy.shoot());
            }
            lastEnemyShoot = TimeUtils.nanoTime();
        }
    }

    protected void clearAction(){
        for (Iterator<AbstractAircraft> iterator = enemies.iterator(); iterator.hasNext(); ) {
            AbstractAircraft aircraft = iterator.next();
            //超出屏幕或not valid
            if (aircraft.y + aircraft.height < 0 || aircraft.notValid()) {
                if(aircraft instanceof BossEnemy)
                {
                    lasBossScore = score;
                    havBoss = false;
                }
                iterator.remove();
            }
        }

        //超出屏幕
        bullets.removeIf(bullet -> bullet.y + bullet.height < 0 || bullet.notValid());
        props.removeIf(prop -> prop.y + prop.height < 0 || prop.notValid());
    }

}
