package com.games.mario.tools;

import com.games.mario.MarioBros;

public class GameMaths {

    public static float scaleValue(float value) {
        return value / MarioBros.PPM;
    }
    public static float deScaleValue(float value) {
        return value * MarioBros.PPM;
    }

}
