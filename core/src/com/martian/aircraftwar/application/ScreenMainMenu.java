package com.martian.aircraftwar.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenMainMenu implements Screen {

    final AircraftWarGame game;
    OrthographicCamera camera;
    Music wait;

    public ScreenMainMenu(AircraftWarGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 768);
        wait = Gdx.audio.newMusic(Gdx.files.internal("videos/Choose.mp3"));
    }

    @Override
    public void show() {
        wait.play();
        wait.setLooping(true);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 0.5f);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        //开始绘图
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Aircraft War!!!", 100, 400);
        game.font.draw(game.batch, "Click anywhere to continue", 100, 350);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new ScreenGame(game));
            dispose();
        }

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
        wait.dispose();
    }
}
