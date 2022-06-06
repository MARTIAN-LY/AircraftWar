package com.martian.aircraftwar.application;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class AircraftWarGame extends Game {

    int mode;
    CommunicateWithAndroid communicate;
    Stage stage;

    public AircraftWarGame(int mode, CommunicateWithAndroid communicate) {
        this.mode = mode;
        this.communicate = communicate;
    }

    SpriteBatch batch;
    BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new Stage(new StretchViewport(512,768));
        Gdx.input.setInputProcessor(stage);

        if (mode == 0) {
            this.setScreen(new ScreenGameEasy(this));
        } else if (mode == 1) {
            this.setScreen(new ScreenGameNormal(this));
        } else {
            this.setScreen(new ScreenGameHard(this));
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        stage.dispose();
    }
}
