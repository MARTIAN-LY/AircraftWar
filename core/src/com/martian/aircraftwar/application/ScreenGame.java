package com.martian.aircraftwar.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.martian.aircraftwar.aircraft.AbstractAircraft;
import com.martian.aircraftwar.aircraft.BossEnemy;
import com.martian.aircraftwar.aircraft.BossEnemyFactory;
import com.martian.aircraftwar.aircraft.EliteEnemyFactory;
import com.martian.aircraftwar.aircraft.HeroAircraft;
import com.martian.aircraftwar.aircraft.MobEnemyFactory;
import com.martian.aircraftwar.bullet.BaseBullet;
import com.martian.aircraftwar.bullet.HeroBullet;
import com.martian.aircraftwar.data.TmpScore;
import com.martian.aircraftwar.prop.AbstractProp;
import com.martian.aircraftwar.prop.BombProp;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;


public abstract class ScreenGame implements Screen {

    public float BG_WIDTH = 512;
    public float BG_HEIGHT = 768;

    //图片

    private final AircraftWarGame game;
    private final OrthographicCamera camera;
    private final Music bg_music;
    private final Sound hit_sound;
    private final Sound hero_hit_sound;
    private final Sound prop_sound;
    private final Sound bombSound;
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
    protected Texture background;
    protected int basicScore;

    private Button stopButton;
    private Dialog stopDialog;
    private boolean skipFlag = false;

    private Button musicButton;
    public static boolean haveMusic = true;

    private ImageTextButton healButton;

    public ScreenGame(AircraftWarGame game) {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);

        this.game = game;

        //背景图片宽高
        bg_width = BG_WIDTH;
        bg_height = BG_HEIGHT;
        bottom = bg_height;

        // load music
        bg_music = Gdx.audio.newMusic(Gdx.files.internal("videos/bg_music.mp3"));
        hit_sound = Gdx.audio.newSound(Gdx.files.internal("videos/bullet_hit.wav"));
        hero_hit_sound = Gdx.audio.newSound(Gdx.files.internal("videos/hero_hit.wav"));
        prop_sound = Gdx.audio.newSound(Gdx.files.internal("videos/get_supply.wav"));
        bombSound = Gdx.audio.newSound(Gdx.files.internal("videos/bomb_explosion.wav"));
        haveMusic = true;

        //load aircraft
        bitmapFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        HeroAircraft.refresh();
        hero = HeroAircraft.getInstance();
        touchPos = new Vector3();
        enemies = new LinkedList<>();
        bullets = new LinkedList<>();
        props = new LinkedList<>();
        enemies.clear();
        bullets.clear();
        props.clear();
        lastEnemyGen = 0;
        lastHeroShoot = 0;
        lastEnemyShoot = 0;
        mobEnemyFactory = new MobEnemyFactory();
        eliteEnemyFactory = new EliteEnemyFactory();
        bossEnemyFactory = new BossEnemyFactory();


        // camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, bg_width, bg_height);

        //load buttons & dialogs

        if (game.communicate.isHaveHeal()) {
            ImageTextButton.ImageTextButtonStyle healButtonStyle = new ImageTextButton.ImageTextButtonStyle();
            healButtonStyle.font = bitmapFont;
            healButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/healbutton1.jpg"))));
            healButtonStyle.imageChecked = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/healbutton2.jpg"))));
            healButton = new ImageTextButton("", healButtonStyle);
            healButton.setSize(60, 60);
            healButton.setBounds(0, 0, 60, 60);
            game.stage.addActor(healButton);
            healButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (healButton.isChecked()) {
                        healButton.setDisabled(true);
                        hero.addHp(5);
                        Runnable r = () ->
                        {
                            for (int i = 9; i >= 1; i--) {
                                healButton.setText(i + "s");
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            healButton.setText("");
                            healButton.setDisabled(false);
                            healButton.setChecked(false);
                        };
                        new Thread(r).start();
                    }
                }
            });
        }

        Button.ButtonStyle stopStyle = new Button.ButtonStyle();
        stopStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/stopbutton1.jpg"))));
        stopStyle.checked = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/stopbutton2.jpg"))));
        stopButton = new Button(stopStyle);
        game.stage.addActor(stopButton);
        stopButton.setBounds(bg_width - 60, bg_height - 60, 50, 50);
        Button.ButtonStyle musicStyle = new Button.ButtonStyle();
        musicStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/musicbutton1.jpg"))));
        musicStyle.checked = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/musicbutton2.jpg"))));
        musicButton = new Button(musicStyle);
        game.stage.addActor(musicButton);
        musicButton.setBounds(bg_width - 120, bg_height - 60, 50, 50);

        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        skin.add("gray", new Texture(pixmap));
        pixmap.setColor(Color.LIGHT_GRAY);
        pixmap.fill();
        skin.add("light_gray", new Texture(pixmap));
        BitmapFont font = new BitmapFont();
        Window.WindowStyle windowStyle = new Window.WindowStyle(font, Color.RED, skin.getDrawable("light_gray"));
        stopDialog = new Dialog("stop", windowStyle);
        stopDialog.setBounds(bg_width / 2 - 60, bg_height / 2 - 30, 120, 60);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.RED);
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle(skin.getDrawable("gray"), skin.getDrawable("light_gray"), null);
        TextButton.TextButtonStyle textbuttonStyle = new TextButton.TextButtonStyle(skin.getDrawable("gray"), skin.getDrawable("light_gray"), null, font);
        skin.add("default", buttonStyle);
        skin.add("default", textbuttonStyle);
        skin.add("default", windowStyle);
        skin.add("default", labelStyle);
        TextButton backButton = new TextButton("Back", skin);
        TextButton exitButton = new TextButton("Exit", skin);
        backButton.setBounds(10, 10, 40, 20);
        exitButton.setBounds(120 - 50, 10, 40, 20);
        stopDialog.addActor(backButton);
        stopDialog.addActor(exitButton);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stopButton.setChecked(false);
                skipFlag = false;
                stopDialog.hide();
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hero.vanish();
                stopButton.setChecked(false);
                skipFlag = false;
                stopDialog.hide();
            }
        });
        game.stage.addActor(stopDialog);
        stopDialog.hide();

        stopButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (stopButton.isChecked()) {
                    skipFlag = true;
                    stopDialog.show(game.stage);
                    stopDialog.setBounds(bg_width / 2 - 60, bg_height / 2 - 30, 120, 60);
                } else {
                    skipFlag = false;
                }
            }
        });
        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (musicButton.isChecked()) {
                    haveMusic = false;
                    bg_music.pause();
                } else {
                    haveMusic = true;
                    bg_music.play();
                }
            }
        });
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

        if (!skipFlag) {
            addEnemyAction();
            shootAction();
            moveAction();
            crashCheckAction();
            clearAction();
            gameOverCheck();
        }
        //开始绘图
        game.batch.begin();
        drawAction();
        game.batch.end();
        game.stage.act();
        game.stage.draw();
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


    protected void crashCheckAction() {
        // Todo: 我方获得道具，道具生效
        for (AbstractProp prop : props) {
            if (!prop.notValid()) {
                if (hero.notValid()) {
                    // 英雄机已被其他子弹击毁，不再检测
                    continue;
                }
                if (hero.crash(prop)) {
                    if (haveMusic) {
                        prop_sound.play();
                    }
                    if (prop instanceof BombProp) {
                        if(haveMusic)
                        {
                            bombSound.play();
                        }
                        score += basicScore * ((BombProp) prop).effect(enemies, bullets);
                    } else prop.effect();
                    prop.vanish();
                }
            }
        }
        for (AbstractAircraft enemyAircraft : enemies) {
            // 英雄机 与 敌机 相撞，均损毁
            if (hero.notValid()) {
                continue;
            }
            if (enemyAircraft.crash(hero)) {
                enemyAircraft.vanish();
                hero.decreaseHp(Integer.MAX_VALUE);
            }
        }
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : bullets) {
            if (bullet.notValid() || bullet instanceof HeroBullet) {
                continue;
            }
            if (hero.notValid()) {
                // 英雄机已被其他子弹击毁，不再检测
                continue;
            }
            if (hero.crash(bullet)) {
                // 英雄机撞击到敌机子弹
                // 英雄机损失一定生命值
                hero.decreaseHp(bullet.getPower());
                bullet.vanish();
                if (haveMusic) {
                    hero_hit_sound.play();
                }
            }
        }
        for (BaseBullet bullet : bullets) {
            if (bullet.notValid() || !(bullet instanceof HeroBullet)) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemies) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    if (haveMusic) {
                        hit_sound.play();
                    }
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        score += 10 * basicScore;
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
            camera.unproject(touchPos);
            Rectangle tmp = new Rectangle(touchPos.x, touchPos.y, 10, 10);
            Rectangle b1 = new Rectangle(stopButton.getX(), stopButton.getY(), stopButton.getWidth(), stopButton.getHeight());
            Rectangle b2 = new Rectangle(musicButton.getX(), musicButton.getY(), musicButton.getWidth(), musicButton.getHeight());
            if (tmp.overlaps(b1) || tmp.overlaps(b2)) {
                return;
            }
            if (game.communicate.isHaveHeal()) {
                Rectangle b3 = new Rectangle(healButton.getX(), healButton.getY(), healButton.getWidth(), healButton.getHeight());
                if (tmp.overlaps(b3)) {
                    return;
                }
            }
            //把变化告知给camera
            hero.x = touchPos.x - hero.getWidth() / 2;
            hero.y = touchPos.y - hero.getHeight() / 2;
        }
    }

    protected void enemyMove() {
        for (AbstractAircraft enemy : enemies) {
            enemy.forward();

            if (enemy.x + enemy.height <= 0) {
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
        game.batch.draw(background, 0, bottom - bg_height);
        game.batch.draw(background, 0, bottom);
        bitmapFont.draw(game.batch, "分数:" + score, 10, bg_height - 5);
        bitmapFont.draw(game.batch, "血量:" + hero.getHp(), 10, bg_height - bitmapFont.getCapHeight() - 10);
        if (!havBoss && BossInterval <= 1000)
            bitmapFont.draw(game.batch, "Boss机将在" + (BossInterval - score + lasBossScore) + "分后来临", 10, bg_height - 2 * bitmapFont.getCapHeight() - 15);
        //绘制英雄机
        game.batch.draw(hero.getImage(), hero.x, hero.y);

        //绘制敌机
        for (AbstractAircraft enemy : enemies) {
            game.batch.draw(enemy.getImage(), enemy.x, enemy.y);
        }

        //绘制子弹
        for (BaseBullet bullet : bullets) {
            game.batch.draw(bullet.getImage(), bullet.x, bullet.y);
        }

        //绘制道具
        for (AbstractProp prop : props) {
            game.batch.draw(prop.getImage(), prop.x, prop.y);
        }

    }

    protected void addEnemyAction() {
        if (TimeUtils.nanoTime() - lastEnemyGen > 1000000000 && enemies.size() < enemyMaxNumber) {
            if (score - lasBossScore >= BossInterval && !havBoss) {
                havBoss = true;
                enemies.add(bossEnemyFactory.createEnemy());
            } else if (MathUtils.random(0, 100) <= eliteEnemyRate) {
                enemies.add(eliteEnemyFactory.createEnemy());
            } else {
                enemies.add(mobEnemyFactory.createEnemy());
            }
            lastEnemyGen = TimeUtils.nanoTime();
        }
    }

    protected void shootAction() {
        if (TimeUtils.nanoTime() - lastHeroShoot > 500000000) {
            bullets.addAll(hero.shoot());
            lastHeroShoot = TimeUtils.nanoTime();
        }
        if (TimeUtils.nanoTime() - lastEnemyShoot > 1000000000) {
            for (AbstractAircraft enemy : enemies) {
                bullets.addAll(enemy.shoot());
            }
            lastEnemyShoot = TimeUtils.nanoTime();
        }
    }

    protected void clearAction() {
        for (Iterator<AbstractAircraft> iterator = enemies.iterator(); iterator.hasNext(); ) {
            AbstractAircraft aircraft = iterator.next();
            //超出屏幕或not valid
            if (aircraft.y + aircraft.height < 0 || aircraft.notValid()) {
                if (aircraft instanceof BossEnemy) {
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

    public void gameOverCheck() {
        if (hero.notValid()) {
            TmpScore.score = score;
            TmpScore.mode = game.mode;
            TmpScore.setDate(new Date());
            if (game.mode == 3) {
                game.communicate.gotoPkResult();
            } else {
                game.communicate.gotoOnceScore();
            }
        }
    }
}
